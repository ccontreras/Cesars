package cesars.becrox.com.cesars;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cesars.becrox.com.cesars.util.Utils;
import com.squareup.picasso.Picasso;
import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;
import me.relex.circleindicator.CircleIndicator;

/**
 * @author cconTreras
 */

public class MainActivity extends AppCompatActivity {

  /**
   * This is the {@link ViewPager} that will render
   * all the images about me.
   */
  @BindView(R.id.photoPager) /*package*/ VerticalViewPager mPhotoViewPager;

  /**
   * This is the {@link CircleIndicator} for the photos that is displayed
   * at the right.
   */
  @BindView(R.id.indicator) /*package*/ CircleIndicator mPhotoIndicator;

  /**
   * This is a reference to the {@link ViewGroup} that contains
   * information about me.
   */
  @BindView(R.id.aboutContainer) /*package*/ ViewGroup mAboutContainer;

  /**
   * An instance of {@link PhotoAdapter} to using with
   * the {@link #mPhotoViewPager}.
   */
  /*package*/ PhotoAdapter mPhotosAdapter;

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    // Load photos.
    int[] photos = { R.drawable.me1, R.drawable.me2, R.drawable.me3 };
    mPhotosAdapter = new PhotoAdapter(this, photos);
    mPhotoViewPager.setAdapter(mPhotosAdapter);
    mPhotoViewPager.setPageTransformer(false, new DefaultTransformer());
    mPhotoIndicator.setViewPager(mPhotoViewPager);

    Utils.applyDefaultTypeface(mAboutContainer, getAssets());
  }

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) Utils.hideStatusBar(this);
  }

  /**
   * This adapter creates an {@link ImageView} for every
   * drawable in the array of {@link #mPhotos}. I've seen applications that
   * implement {@link FragmentPagerAdapter} just for showing images and that can
   * be painful for that task, so I just simply create an {@link ImageView} to
   * to show my photos with no need to handle {@link Fragment}s lifecycles.
   */
  private class PhotoAdapter extends PagerAdapter {

    /**
     * The current context.
     */
    private @NonNull Context mContext;

    /**
     * An array of drawable identifiers.
     */
    private @DrawableRes int[] mPhotos;

    /**
     * Creates a new instance of this adapter.
     *
     * @param context the current context.
     * @param photos an array of drawables identifiers.
     */
    /*package*/ PhotoAdapter(@NonNull Context context, @DrawableRes int[] photos) {
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

      Picasso.with(mContext)
          .load(mPhotos[position])
          .noFade()
          .into(image);

      container.addView(image);
      return image;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(((View) object));
    }
  }
}
