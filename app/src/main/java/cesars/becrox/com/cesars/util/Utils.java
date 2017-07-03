package cesars.becrox.com.cesars.util;

import android.app.ActionBar;
import android.app.Activity;
import android.view.View;

/**
 * @author cconTreras
 */

public final class Utils {

  public static void hideStatusBar(Activity activity) {
    View decorView = activity.getWindow().getDecorView();
    // Hide the status bar.
    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    // Remember that you should never show the action bar if the
    // status bar is hidden, so hide that too if necessary.
    ActionBar actionBar = activity.getActionBar();
    if (actionBar != null) actionBar.hide();
  }
}
