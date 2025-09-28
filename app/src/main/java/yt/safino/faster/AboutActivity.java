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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.*;
import androidx.core.splashscreen.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.security.*;
import com.google.android.material.*;
import com.google.android.material.button.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class AboutActivity extends AppCompatActivity {
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private MaterialButton button5;
	private TextView textview1;
	private LinearLayout linear_card;
	private TextView textview2;
	private RelativeLayout relativelayout1;
	private LinearLayout linear8;
	private ImageView imageview1;
	private MaterialButton button9;
	private MaterialButton button10;
	private MaterialButton button11;
	private TextView textview5;
	private TextView textview6;
	
	private Intent intentYT = new Intent();
	private Intent intentTelegram = new Intent();
	private Intent intentGIT = new Intent();
	private Intent back_intent = new Intent();
	private SharedPreferences save;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.about);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		button5 = findViewById(R.id.button5);
		textview1 = findViewById(R.id.textview1);
		linear_card = findViewById(R.id.linear_card);
		textview2 = findViewById(R.id.textview2);
		relativelayout1 = findViewById(R.id.relativelayout1);
		linear8 = findViewById(R.id.linear8);
		imageview1 = findViewById(R.id.imageview1);
		button9 = findViewById(R.id.button9);
		button10 = findViewById(R.id.button10);
		button11 = findViewById(R.id.button11);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				back_intent.setClass(getApplicationContext(), SettingsActivity.class);
				Bundle opts = ActivityOptions
				    .makeSceneTransitionAnimation(AboutActivity.this)
				    .toBundle();
				startActivity(back_intent, opts);
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intentYT.setAction(Intent.ACTION_VIEW);
				intentYT.setData(Uri.parse(getString(R.string.YouTubLink)));
				startActivity(intentYT);
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intentTelegram.setAction(Intent.ACTION_VIEW);
				intentTelegram.setData(Uri.parse(getString(R.string.TelegraLink)));
				startActivity(intentTelegram);
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intentGIT.setAction(Intent.ACTION_VIEW);
				intentGIT.setData(Uri.parse(getString(R.string.GitHubLink)));
				startActivity(intentGIT);
			}
		});
	}
	
	private void initializeLogic() {
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		WindowInsetsControllerCompat insetsController =
		new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
		
		insetsController.setSystemBarsBehavior(
		WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
		);
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				back_intent.setClass(getApplicationContext(), SettingsActivity.class);
				Bundle opts = ActivityOptions
				    .makeSceneTransitionAnimation(AboutActivity.this)
				    .toBundle();
				startActivity(back_intent, opts);
			}
		});
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