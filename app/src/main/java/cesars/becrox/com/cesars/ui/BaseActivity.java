package cesars.becrox.com.cesars.ui;

import android.support.v7.app.AppCompatActivity;
import cesars.becrox.com.cesars.util.Utils;

/**
 * This is the base class for all activities.
 *
 * @author cconTreras
 */

public abstract class BaseActivity extends AppCompatActivity {

  @Override public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    // Hide the status bar when the activity gain the focus.
    if (hasFocus) Utils.hideStatusBar(this);
  }
}
