package cesars.becrox.com.cesars.ui.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cesars.becrox.com.cesars.R;
import cesars.becrox.com.cesars.ui.BaseActivity;
import cesars.becrox.com.cesars.util.Utils;

/**
 * This activity enables the user to send me a direct
 * email by specifying the subject and content message.
 *
 * @author cconTreras
 */

public class ContactActivity extends BaseActivity {

  @BindView(R.id.toolbar) /*package*/ Toolbar mToolbar;

  /**
   * The {@link EditText} with the subject.
   */
  @BindView(R.id.subjectText) /*package*/ TextView mSubjectText;

  /**
   * The {@link EditText} with the message.
   */
  @BindView(R.id.messageText) /*package*/ TextView mMessageText;

  private String mSubject;
  private String mMessage;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_me);

    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  /**
   * Sends an email to me :)
   */
  @OnClick(R.id.sendButton) public void sendEmail() {
    collectData();
    if (validateData()) {
      Intent emailIntent = Utils.getEmailIntent(this, mSubject, mMessage);
      startActivity(emailIntent);
    }
  }

  /**
   * Validates the data. Before calling this method you should
   * call the {@link #collectData()} to load the data in order
   * to validate.
   */
  private boolean validateData() {
    if (mSubject == null || TextUtils.isEmpty(mSubject)) {
      mSubjectText.setError(getString(R.string.error_field_required));
      return false;
    }

    if (mMessage == null || TextUtils.isEmpty(mMessage)) {
      mMessageText.setError(getString(R.string.error_field_required));
      return false;
    }

    return true;
  }

  /**
   * Collects the data from the form.
   */
  private void collectData() {
    mSubject = mSubjectText.getText().toString().trim();
    mMessage = mMessageText.getText().toString().trim();
  }

  public static Intent getIntent(@NonNull Context context) {
    return new Intent(context, ContactActivity.class);
  }
}
