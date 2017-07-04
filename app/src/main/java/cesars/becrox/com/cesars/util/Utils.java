package cesars.becrox.com.cesars.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cesars.becrox.com.cesars.R;

/**
 * @author cconTreras
 */

public final class Utils {

  /**
   * This is the path for the base typeface used across the app.
   */
  private static final String DEFAULT_TYPEFACE_PATH = "fonts/SuezOne-Regular.ttf";

  /**
   * This references the default {@link Typeface} instance to
   * set for all text widgets.
   */
  private static Typeface sDefaultTypeface;

  /**
   * Hides the status bar.
   *
   * https://developer.android.com/training/system-ui/status.html
   *
   * @param activity the current activity.
   */
  public static void hideStatusBar(Activity activity) {
    View decorView = activity.getWindow().getDecorView();
    // Hide the status bar.
    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    // Remember that you should never show the action bar if the
    // status bar is hidden, so hide that too if necessary.
    ActionBar actionBar = activity.getActionBar();
    if (actionBar != null) actionBar.hide();
  }

  /**
   * This method searches for {@link TextView}s widgets in a {@link ViewGroup}
   * to set the default {@link Typeface} to it when found.
   *
   * @param viewGroup the {@link ViewGroup} to search in.
   * @param assetManager the {@link AssetManager} to load the {@link Typeface}.
   */
  public static void applyDefaultTypeface(ViewGroup viewGroup, AssetManager assetManager) {
    View view;
    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      view = viewGroup.getChildAt(i);
      if (view instanceof ViewGroup) {
        applyDefaultTypeface(((ViewGroup) view), assetManager);
      } else if (view instanceof TextView) {
        ((TextView) view).setTypeface(getDefaultTypeface(assetManager));
      }
    }
  }

  /**
   * This method initializes, loads and returns the {@link Typeface}.
   *
   * @param assetManager the {@link AssetManager} to load the font.
   * @return a {@link Typeface} instance.
   */
  public static Typeface getDefaultTypeface(@NonNull AssetManager assetManager) {
    if (sDefaultTypeface == null) {
      synchronized (Utils.class) {
        if (sDefaultTypeface == null) {
          sDefaultTypeface = Typeface.createFromAsset(assetManager, DEFAULT_TYPEFACE_PATH);
        }
      }
    }
    return sDefaultTypeface;
  }

  /**
   * Creates the {@link Intent} to send the email.
   *
   * @param context the current context.
   * @param subject the email subject.
   * @param message the content message.
   * @return the {@link Intent} to use in order to send the email.
   */
  public static Intent getEmailIntent(@NonNull Context context, String subject, String message) {
    Intent email = new Intent(Intent.ACTION_SEND);
    email.putExtra(Intent.EXTRA_EMAIL, new String[] { context.getString(R.string.my_email) });
    email.putExtra(Intent.EXTRA_SUBJECT, subject);
    email.putExtra(Intent.EXTRA_TEXT, message);
    email.setType("message/rfc822");
    return Intent.createChooser(email, context.getString(R.string.title_email_client_chooser));
  }
}
