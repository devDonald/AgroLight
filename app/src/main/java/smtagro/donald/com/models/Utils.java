package smtagro.donald.com.models;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    // Method for checking availability of network
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectMgr;
        connectMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectMgr.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Method for dismissing the keyboard
    public void dismissKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    //checking for the validity of email
    public boolean isValidEmail(Context context, CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    //checking for the validity of phone
    public boolean isValidPhoneNumber(Context context, String mobile) {
        String regEx = "^[0-9]{11}$";
        return mobile.matches(regEx);
    }

    public String getFormatedAmount(double amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }
}
