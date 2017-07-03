package cesars.becrox.com.cesars;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cesars.becrox.com.cesars.util.Utils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.my_photos_pager) ViewPager mPhotosViewPager;

  private MyPhotosAdapter mPhotosAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Utils.hideStatusBar(this);

    ButterKnife.bind(this);

    int[] myPhotos = { R.drawable.me1, R.drawable.me2, R.drawable.me3 };
    mPhotosAdapter = new MyPhotosAdapter(this, myPhotos);
    mPhotosViewPager.setAdapter(mPhotosAdapter);

  }

  private class MyPhotosAdapter extends PagerAdapter {

    private @NonNull Context mContext;
    private @DrawableRes int[] mPhotos;

    public MyPhotosAdapter(@NonNull Context context, @DrawableRes int[] photos) {
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

      Picasso.with(mContext).load(mPhotos[position]).resize(600, 400).centerCrop().into(image);

      container.addView(image);
      return image;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(((View) object));
    }
  }
}
