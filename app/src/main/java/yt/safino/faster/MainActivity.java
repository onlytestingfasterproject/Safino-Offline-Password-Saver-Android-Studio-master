package yt.safino.faster;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.*;
import androidx.core.splashscreen.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.security.*;
import com.google.android.material.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
private boolean shouldKeepSplashVisible = true;
	
	private LinearLayout linear1;
	private ImageView imageview1;
	
	private Intent intent = new Intent();
	private SharedPreferences save;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
            splashScreen.setKeepOnScreenCondition(() -> shouldKeepSplashVisible);
	        _changeactivity();
		super.onCreate(_savedInstanceState);
		} else {
            super.onCreate(_savedInstanceState);
			setContentView(R.layout.main);
			_changeactivity();
		}
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		WindowInsetsControllerCompat insetsController =
		new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
		
		insetsController.setSystemBarsBehavior(
		WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
		);
		if (!save.contains("fingerprint auth")) {
			save.edit().putString("fingerprint auth", "true").commit();
		}
		if (!save.contains("long password generate")) {
			save.edit().putString("long password generate", "false").commit();
		}
		if (!save.contains("appearance")) {
			save.edit().putString("appearance", "syd").commit();
		}
		if (!save.contains("dynamic colors")) {
			save.edit().putString("dynamic colors", "true").commit();
		}
		if (!save.contains("multi theme")) {
			save.edit().putString("multi theme", "default").commit();
		}
		if (!save.contains("format")) {
			save.edit().putString("format", "24 hour").commit();
		}
		if (save.contains("appearance")) {
			if (save.getString("appearance", "").equals("night")) {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
			} else {
				if (save.getString("appearance", "").equals("day")) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
				} else {
					if (save.getString("appearance", "").equals("syd")) {
						AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
					}
				}
			}
		} else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
		}
	}
	
	public void _changeactivity() {
		new Handler(Looper.getMainLooper()).postDelayed(() -> {
			shouldKeepSplashVisible = false;
			intent.setClass(getApplicationContext(), AuthActivity.class);
			intent.putExtra("authentication", "no");
			Bundle opts = ActivityOptions
			    .makeSceneTransitionAnimation(MainActivity.this)
			    .toBundle();
			startActivity(intent, opts);
		}, 1000);
	}
	
	
	public void _changetheme() {
		if (save.contains("multi theme")) {
			if (save.getString("multi theme", "").equals("pink")) {
				setTheme(R.style.AppTheme_Pink);
			} else {
				if (save.getString("multi theme", "").equals("red")) {
					setTheme(R.style.AppTheme_Red);
				} else {
					if (save.getString("multi theme", "").equals("blue")) {
						setTheme(R.style.AppTheme_Blue);
					} else {
						if (save.getString("multi theme", "").equals("yellow")) {
							setTheme(R.style.AppTheme_Yellow);
						} else {
							save.edit().putString("multi theme", "default").commit();
						}
					}
				}
			}
		} else {
			save.edit().putString("multi theme", "default").commit();
		}
	}
	
}