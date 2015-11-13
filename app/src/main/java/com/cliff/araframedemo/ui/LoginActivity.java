package com.cliff.araframedemo.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cliff.araframedemo.R;
import com.cliff.araframedemo.api.TopicApi;
import com.cliff.araframedemo.api.UserApi;
import com.cliff.araframedemo.api.entity.TopicListResult;
import com.cliff.araframedemo.db.Topic;
import com.cliff.araframedemo.db.User;
import com.cliff.araframedemo.ui.main.activity.MainActivity;
import com.cliff.araframedemo.ui.main.activity.MainActivity_;
import com.cliff.hsj.api.ApiClient;
import com.cliff.hsj.api.OkHttpClientManager;
import com.cliff.hsj.exception.HttpException;
import com.cliff.hsj.utils.AndroidUtils;
import com.cliff.hsj.utils.LogUtils;
import com.cliff.hsj.utils.ToastUtils;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 * 登录页面
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
    @ViewById(R.id.email)
    AutoCompleteTextView mEmailView;
    @ViewById(R.id.password)
    EditText mPasswordView;
    @ViewById(R.id.email_sign_in_button)
    Button mEmailSignInButton;
    @ViewById(R.id.login_progress)
    View mProgressView;
    @ViewById(R.id.login_form)
    View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the login form.
        populateAutoComplete();
    }

    private void populateAutoComplete() {
        if (VERSION.SDK_INT >= 14) {
            // Use ContactsContract.Profile (API 14+)
//            getLoaderManager().initLoader(0, null, this);
        } else if (VERSION.SDK_INT >= 8) {
            // Use AccountManager (API 8+)
            new SetupEmailAutoCompleteTask().execute(null, null);
        }
    }

    @AfterViews
    void setListener() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mEmailView.setText("jack@163.cn");
        mPasswordView.setText("aadfsdfdfs");

        AndroidUtils.showNotify(this, MainActivity_.class, "标题", "提醒测试内容", R.drawable.top_icon, R.drawable.top_icon, 1
        );
    }

    /**
     * 网络请求样例
     */
    void loadTopic() {
        Call<TopicListResult> result = ApiClient.instance().loadService(TopicApi.class).getTopics("&#160;&#160;全部&#160;&#160;", 1, 10, true);
        try {
            printTopics(ApiClient.instance().getData(result).getData());
        } catch (HttpException ex) {
            Log.e("TEST", ex.getMessage());
        }
    }

    /**
     * 文件下载样例
     */
    void testLoadFile() {
        ApiClient.instance().getDownloadDelegate().downloadAsyn("http://file.ynet.com/2/1509/24/10405301-500.jpg", "/mnt/sdcard/ARAF/",
                new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        LogUtils.i("TEST", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtils.i("TEST", response);
                    }
                });
    }

    /**
     * 数据库操作样例
     *
     * @param list
     */
    void printTopics(List<Topic> list) {
        for (Topic tp : list) {
            Log.i("TEST", tp.getId() + "--" + tp.getTitle() + "---" + tp.getAuthor_id());
            try {
                Long id = tp.save();
                if (id.longValue() > 0) {
                    Topic tps = (Topic)
                            tp.findOne(id);
                    Log.d("Test", tps.getId() + "--" + tps.getTpId());
                } else {
                    Log.i("TEST", "db save fail !");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("TEST", "db init fail !");
                break;
            }
        }
    }

    /**
     * 图片加载样例
     */
    private void testPicasso() {
        ImageView imageView = new ImageView(this);
        String url = "";
        View view = new View(this);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        Picasso.with(this).load(url).resize(50, 50).centerCrop().into(imageView);
//这里的placeholder将resource传入通过getResource.getDrawable取资源，所以可以是张图片也可以是color id
//        Picasso.with(this).load(url).placeholder(R.drawable.).error(R.drawable.user_placeholder_error).into(imageView);

//        Picasso.with(this).load(R.drawable.landing_screen).into(imageView);
        Picasso.with(this).load("file:///android_asset/DvpvklR.png").into(imageView);
//        Picasso.with(this).load(new File(...)).into(imageView3);
        //这里显示notification的图片
//        Picasso.with(this).load(Data.URLS[new Random().nextInt(Data.URLS.length)]).resizeDimen(R.dimen.notification_icon_width_height,    R.dimen.notification_icon_width_height).into(remoteViews, R.id.photo, NOTIFICATION_ID, notification);
        //这里是通过设置tag标签，就是当前传过来的context，这样就可以根据这个context tag来pause和resume显示了
//        Picasso.with(this).load(url).placeholder(R.drawable.placeholder).error(R.drawable.error).fit().tag(context).into(view);
        //监听onScrollStateChanged的时候调用执行
//        Picasso.resumeTag(this);
//        picasso.pauseTag(this);

//        Picasso.with(this).load(contactUri).placeholder(R.drawable.contact_picture_placeholder).tag(context).into(holder.icon);

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
            login(email, password);
        }
    }

    @Background
    void login(String email, String password) {
        Call<User> user = ApiClient.instance().loadService(UserApi.class).login(email, password);
        try {
            User u = ApiClient.instance().getData(user);
            afterlogin(u.getEmail());
        } catch (HttpException e) {
            afterlogin(e.getMessage());
        }
    }

    @UiThread
    void afterlogin(String message) {
        showProgress(false);
        ToastUtils.show(this, message);


        Intent openTest = new Intent(this, MainActivity_.class);
        startActivity(openTest);

    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Use an AsyncTask to fetch the user's email addresses on a background thread, and update
     * the email text field with results on the main UI thread.
     */
    class SetupEmailAutoCompleteTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            ArrayList<String> emailAddressCollection = new ArrayList<String>();

            // Get all emails from the user's contacts and copy them to a list.
            ContentResolver cr = getContentResolver();
            Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    null, null, null);
            while (emailCur.moveToNext()) {
                String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract
                        .CommonDataKinds.Email.DATA));
                emailAddressCollection.add(email);
            }
            emailCur.close();

            return emailAddressCollection;
        }

        @Override
        protected void onPostExecute(List<String> emailAddressCollection) {
            addEmailsToAutoComplete(emailAddressCollection);
        }
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

}

