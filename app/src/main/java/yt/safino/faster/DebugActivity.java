package yt.safino.faster;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.*;
import androidx.core.splashscreen.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.security.*;
import com.google.android.material.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class DebugActivity extends AppCompatActivity {
	
	private AlertDialog.Builder errorDialog;
	private SharedPreferences save;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.debug);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		errorDialog = new AlertDialog.Builder(this);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		MaterialAlertDialogBuilder errorDialog = new MaterialAlertDialogBuilder(DebugActivity.this);
		errorDialog.setTitle("The app has been crashed!");
		errorDialog.setMessage("We are sorry, the app crashed due to some problem in the app. Crash report is saved in ".concat(getIntent().getStringExtra("filePath").concat(" You can click on copy button and copy the error and send it to our email. We will find out where it happened. Try again!")));
		errorDialog.setNegativeButton("Copy", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface _dialog, int _which) {
				        FasterUtils.copyToClipboard(DebugActivity.this, getIntent().getStringExtra("error"), getIntent().getStringExtra("error"));
				finishAffinity();
				    }
		});
		errorDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface _dialog, int _which) {
				        finishAffinity();
				    }
		});
		errorDialog.setCancelable(false);
		errorDialog.create().show();
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