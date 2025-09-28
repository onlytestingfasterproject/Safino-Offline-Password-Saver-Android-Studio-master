package yt.safino.faster;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;

import com.google.android.material.color.DynamicColors;

public class FasterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String dynamicColorPref = sharedPreferences.getString("dynamic colors", null);

        if (dynamicColorPref == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                editor.putString("dynamic colors", "true");
            } else {
                editor.putString("dynamic colors", "unsupported");
            }
            editor.apply();
            dynamicColorPref = sharedPreferences.getString("dynamic colors", "unsupported");
        }

        if ("true".equals(dynamicColorPref) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }

        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));
    }
}