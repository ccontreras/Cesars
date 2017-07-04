package cesars.becrox.com.cesars.ui.bio;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cesars.becrox.com.cesars.R;
import cesars.becrox.com.cesars.ui.BaseActivity;
import cesars.becrox.com.cesars.ui.contact.ContactActivity;
import cesars.becrox.com.cesars.ui.adapter.PhotoAdapter;
import cesars.becrox.com.cesars.util.Utils;
import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;
import me.relex.circleindicator.CircleIndicator;

/**
 * @author cconTreras
 */

public class BioActivity extends BaseActivity {

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

  /*package*/ final int[] mMyPhotos = { R.drawable.me1, R.drawable.me2, R.drawable.me3 };

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    // Add my photos to the pager.
    mPhotosAdapter = new PhotoAdapter(this, mMyPhotos);
    mPhotoViewPager.setAdapter(mPhotosAdapter);
    mPhotoViewPager.setPageTransformer(false, new DefaultTransformer());
    mPhotoIndicator.setViewPager(mPhotoViewPager);

    // Set the default typeface for text views.
    Utils.applyDefaultTypeface(mAboutContainer, getAssets());
  }

  @OnClick(R.id.emailButton) public void openContactMeActivity() {
    startActivity(ContactActivity.getIntent(this));
  }
}
