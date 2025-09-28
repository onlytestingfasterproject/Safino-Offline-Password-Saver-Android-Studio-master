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
import android.widget.EditText;
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
import com.google.android.material.card.*;
import com.google.android.material.textfield.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class ViewPasswordActivity extends AppCompatActivity {
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private MaterialButton button5;
	private TextView textview1;
	private MaterialButton button7;
	private LinearLayout linear4;
	private TextInputLayout textinputlayout_username;
	private TextInputLayout textinputlayout_account;
	private TextInputLayout textinputlayout_password;
	private TextInputLayout textinputlayout_website;
	private TextInputLayout textinputlayout_notes;
	private TextView textview2;
	private RelativeLayout relativelayout_image;
	private TextInputLayout textinputlayout_title;
	private MaterialCardView linear_image_border;
	private ImageView imageview1;
	private EditText edittext_title;
	private EditText edittext_username;
	private EditText edittext_account_name;
	private EditText edittext_password;
	private EditText edittext_url;
	private EditText edittext_notes;
	
	private SharedPreferences save;
	private Intent backintent = new Intent();
	private Intent intent3 = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_password);
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
		button7 = findViewById(R.id.button7);
		linear4 = findViewById(R.id.linear4);
		textinputlayout_username = findViewById(R.id.textinputlayout_username);
		textinputlayout_account = findViewById(R.id.textinputlayout_account);
		textinputlayout_password = findViewById(R.id.textinputlayout_password);
		textinputlayout_website = findViewById(R.id.textinputlayout_website);
		textinputlayout_notes = findViewById(R.id.textinputlayout_notes);
		textview2 = findViewById(R.id.textview2);
		relativelayout_image = findViewById(R.id.relativelayout_image);
		textinputlayout_title = findViewById(R.id.textinputlayout_title);
		linear_image_border = findViewById(R.id.linear_image_border);
		imageview1 = findViewById(R.id.imageview1);
		edittext_title = findViewById(R.id.edittext_title);
		edittext_username = findViewById(R.id.edittext_username);
		edittext_account_name = findViewById(R.id.edittext_account_name);
		edittext_password = findViewById(R.id.edittext_password);
		edittext_url = findViewById(R.id.edittext_url);
		edittext_notes = findViewById(R.id.edittext_notes);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				backintent.setClass(getApplicationContext(), PasswordListActivity.class);
				backintent.putExtra("authentication", "yes");
				ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ViewPasswordActivity.this);
				startActivity(backintent, options.toBundle());
			}
		});
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent3.setClass(getApplicationContext(), AddPasswordActivity.class);
				intent3.putExtra("title", getIntent().getStringExtra("title"));
				intent3.putExtra("email", getIntent().getStringExtra("email"));
				intent3.putExtra("date", getIntent().getStringExtra("date"));
				intent3.putExtra("logo", getIntent().getStringExtra("logo"));
				intent3.putExtra("website url", getIntent().getStringExtra("website url"));
				intent3.putExtra("username", getIntent().getStringExtra("username"));
				intent3.putExtra("password", getIntent().getStringExtra("password"));
				intent3.putExtra("note", getIntent().getStringExtra("note"));
				intent3.putExtra("Last updated", getIntent().getStringExtra("Last updated"));
				intent3.putExtra("position", getIntent().getStringExtra("position"));
				ActivityOptions options3 = ActivityOptions.makeSceneTransitionAnimation(ViewPasswordActivity.this);
				startActivity(intent3, options3.toBundle());
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
				backintent.setClass(getApplicationContext(), PasswordListActivity.class);
				backintent.putExtra("authentication", "yes");
				ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ViewPasswordActivity.this);
				startActivity(backintent, options.toBundle());
			}
		});
		edittext_title.setText(getIntent().getStringExtra("title"));
		edittext_username.setText(getIntent().getStringExtra("username"));
		edittext_account_name.setText(getIntent().getStringExtra("email"));
		edittext_password.setText(getIntent().getStringExtra("password"));
		edittext_url.setText(getIntent().getStringExtra("website url"));
		textview1.setText(getIntent().getStringExtra("title"));
		edittext_notes.setText(getIntent().getStringExtra("note"));
		textview2.setText("Last updated ".concat(getIntent().getStringExtra("Last updated")));
		if (getIntent().getStringExtra("note").equals("")) {
			textinputlayout_notes.setVisibility(View.GONE);
		}
		if (getIntent().getStringExtra("username").equals("")) {
			textinputlayout_username.setVisibility(View.GONE);
		}
		if (getIntent().getStringExtra("website url").equals("")) {
			textinputlayout_website.setVisibility(View.GONE);
		}
		if (getIntent().getStringExtra("email").equals("")) {
			textinputlayout_account.setVisibility(View.GONE);
		}
		if (getIntent().getStringExtra("logo").equals("")) {
			Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(ViewPasswordActivity.this, getIntent().getStringExtra("title"), 80);
			imageview1.setImageBitmap(profileImage);
		} else {
			imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(getIntent().getStringExtra("logo"), 1024, 1024));
		}
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