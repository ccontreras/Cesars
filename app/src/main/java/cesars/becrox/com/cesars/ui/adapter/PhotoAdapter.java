package cesars.becrox.com.cesars.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * This adapter creates an {@link ImageView} for every
 * drawable in the array of {@link #mPhotos}. I've seen applications that
 * implement {@link FragmentPagerAdapter} just for showing images and that can
 * be painful for that task, so I just simply create an {@link ImageView} to
 * to show my photos with no need to handle {@link Fragment}s lifecycle.
 *
 * @author cconTreras
 */
public class PhotoAdapter extends PagerAdapter {

  /**
   * The current context.
   */
  private @NonNull Context mContext;

  /**
   * An array of drawable identifiers.
   */
  private @NonNull int[] mPhotos;

  /**
   * Creates a new instance of this adapter.
   *
   * @param context the current context.
   * @param photos an array of drawables identifiers.
   */
  public PhotoAdapter(@NonNull Context context, @NonNull int[] photos) {
    this.mContext = context;
    this.mPhotos = photos;
  }

  @Override public int getCount() {
    return mPhotos.length;
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    ImageView image = new ImageView(mContext);
    image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    image.setScaleType(ImageView.ScaleType.CENTER_CROP);

    Picasso.with(mContext).load(mPhotos[position]).noFade().into(image);

    container.addView(image);
    return image;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView(((View) object));
  }
}