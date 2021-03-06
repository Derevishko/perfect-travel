package travel.avg.travel.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Helper {
    public static String HOST = "http://fvmapi.westeurope.cloudapp.azure.com/";

    SharedPreferences pref;
    static SharedPreferences.Editor editor;
    Context context;
    int MODE_PRIVATE = 0;
    private static final String PREFS_NAME= "checker";
    private static final String PREFS_CHECK = "check";

    private static final String PREFS_KEY = "key";

    public Helper(Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(PREFS_CHECK, isFirstTime);
        editor.commit();
    }

    public  String getToken() {
        return "ID "+pref.getString(PREFS_KEY,"");
    }

    public  void setToken(String token) {
        editor.putString(PREFS_KEY, token);
        setFirstTimeLaunch(false);
    }
}
