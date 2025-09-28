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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
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
import com.google.android.material.card.*;
import com.google.android.material.textfield.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.EncryptedSharedPreferences;
import java.io.IOException;
import java.security.GeneralSecurityException;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import android.app.KeyguardManager;
import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;

public class AuthActivity extends AppCompatActivity {
private SharedPreferences encryptedPrefs;
	
	private Timer _timer = new Timer();
	
	private boolean question_field = false;
	private boolean password_filed = false;
	private String value = "";
	private double counter = 0;
	private String authentication = "";
	private double uniqueCount = 0;
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private MaterialCardView important_note;
	private TextView textview_title;
	private TextInputLayout textinputlayout_password;
	private MaterialCardView linear_password_checker;
	private TextInputLayout textinputlayout_question;
	private LinearLayout linear68;
	private LinearLayout linear_button;
	private TextView textview1;
	private LinearLayout linear65;
	private LinearLayout linear64;
	private LinearLayout linear63;
	private ImageView imageview22;
	private TextView textview17;
	private TextView textview18;
	private EditText edittext_pass;
	private LinearLayout linear70;
	private LinearLayout linear71;
	private LinearLayout linear72;
	private LinearLayout linear73;
	private LinearLayout linear74;
	private LinearLayout linear75;
	private LinearLayout linear78;
	private ImageView imageview_checker1;
	private TextView textview_checker1;
	private ImageView imageview_checker2;
	private TextView textview_checker2;
	private ImageView imageview_checker3;
	private TextView textview_checker3;
	private ImageView imageview_checker4;
	private TextView textview_checker4;
	private ImageView imageview_checker5;
	private TextView textview_checker5;
	private ImageView imageview_checker6;
	private TextView textview_checker6;
	private EditText edittext_question;
	private Button button_fingerprint;
	private Button button_exit;
	private LinearLayout linear67;
	private Button button_enter;
	
	private Intent intent = new Intent();
	private SharedPreferences save;
	private TimerTask timer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.auth);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		important_note = findViewById(R.id.important_note);
		textview_title = findViewById(R.id.textview_title);
		textinputlayout_password = findViewById(R.id.textinputlayout_password);
		linear_password_checker = findViewById(R.id.linear_password_checker);
		textinputlayout_question = findViewById(R.id.textinputlayout_question);
		linear68 = findViewById(R.id.linear68);
		linear_button = findViewById(R.id.linear_button);
		textview1 = findViewById(R.id.textview1);
		linear65 = findViewById(R.id.linear65);
		linear64 = findViewById(R.id.linear64);
		linear63 = findViewById(R.id.linear63);
		imageview22 = findViewById(R.id.imageview22);
		textview17 = findViewById(R.id.textview17);
		textview18 = findViewById(R.id.textview18);
		edittext_pass = findViewById(R.id.edittext_pass);
		linear70 = findViewById(R.id.linear70);
		linear71 = findViewById(R.id.linear71);
		linear72 = findViewById(R.id.linear72);
		linear73 = findViewById(R.id.linear73);
		linear74 = findViewById(R.id.linear74);
		linear75 = findViewById(R.id.linear75);
		linear78 = findViewById(R.id.linear78);
		imageview_checker1 = findViewById(R.id.imageview_checker1);
		textview_checker1 = findViewById(R.id.textview_checker1);
		imageview_checker2 = findViewById(R.id.imageview_checker2);
		textview_checker2 = findViewById(R.id.textview_checker2);
		imageview_checker3 = findViewById(R.id.imageview_checker3);
		textview_checker3 = findViewById(R.id.textview_checker3);
		imageview_checker4 = findViewById(R.id.imageview_checker4);
		textview_checker4 = findViewById(R.id.textview_checker4);
		imageview_checker5 = findViewById(R.id.imageview_checker5);
		textview_checker5 = findViewById(R.id.textview_checker5);
		imageview_checker6 = findViewById(R.id.imageview_checker6);
		textview_checker6 = findViewById(R.id.textview_checker6);
		edittext_question = findViewById(R.id.edittext_question);
		button_fingerprint = findViewById(R.id.button_fingerprint);
		button_exit = findViewById(R.id.button_exit);
		linear67 = findViewById(R.id.linear67);
		button_enter = findViewById(R.id.button_enter);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		edittext_pass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				_TransitionManager(linear1, 100);
				if (_charSeq.trim().equals("")) {
					textinputlayout_password.setErrorEnabled(true);
					textinputlayout_password.setError("Enter master key");
					if (!encryptedPrefs.contains("question")) {
						_validation(_charSeq);
					}
					password_filed = false;
				} else {
					if (encryptedPrefs.contains("question")) {
						textinputlayout_password.setErrorEnabled(false);
						password_filed = true;
					} else {
						_validation(_charSeq);
						if (FasterPasswordValidator.validate(_charSeq)) {
							textinputlayout_password.setErrorEnabled(false);
							password_filed = true;
						} else {
							textinputlayout_password.setErrorEnabled(false);
							password_filed = false;
						}
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext_question.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.trim().equals("")) {
					textinputlayout_question.setErrorEnabled(true);
					textinputlayout_question.setError("Enter security question");
					question_field = false;
				} else {
					textinputlayout_question.setErrorEnabled(false);
					question_field = true;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		button_fingerprint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Executor executor = ContextCompat.getMainExecutor(AuthActivity.this);
				
				BiometricPrompt biometricPrompt = new BiometricPrompt(AuthActivity.this, executor,
				new BiometricPrompt.AuthenticationCallback() {
						@Override
						public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
								super.onAuthenticationSucceeded(result);
								_ifAuthisOk();
						}
						
						@Override
						public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
								super.onAuthenticationError(errorCode, errString);
								com.google.android.material.snackbar.Snackbar.make(linear1, errString, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
										@Override
										public void onClick(View _view) {
												
										}
								}).show();
						}
						
						@Override
						public void onAuthenticationFailed() {
								super.onAuthenticationFailed();
								com.google.android.material.snackbar.Snackbar.make(linear1, "Authentication Failed!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
										@Override
										public void onClick(View _view) {
												
										}
								}).show();
						}
				});
				
				BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
				.setTitle("Fingerprint to Unlock")
				.setSubtitle("Scan your fingerprint")
				.setNegativeButtonText("Cancel")
				.build();
				
				biometricPrompt.authenticate(promptInfo);
				
			}
		});
		
		button_exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
		
		button_enter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TransitionManager(linear1, 300);
				FasterUtils.hideKeyboard(AuthActivity.this, edittext_pass);
				if (!encryptedPrefs.contains("question")) {
					if (question_field && password_filed) {
						encryptedPrefs.edit().putString("secure_key", edittext_pass.getText().toString()).apply();
						encryptedPrefs.edit().putString("question", edittext_question.getText().toString()).commit();
						textinputlayout_password.setVisibility(View.VISIBLE);
						textinputlayout_question.setVisibility(View.GONE);
						linear_password_checker.setVisibility(View.GONE);
						if (save.getString("fingerprint auth", "").equals("true")) {
							button_fingerprint.setVisibility(View.VISIBLE);
						} else {
							button_fingerprint.setVisibility(View.GONE);
						}
						edittext_question.setText("");
						edittext_pass.setText("");
						textview_title.setText("Unlock With Master Password");
						button_enter.setText("Enter");
					} else {
						com.google.android.material.snackbar.Snackbar.make(linear1, "Empty field not allowed!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
							@Override
							public void onClick(View _view) {
								 
							}
						}).show();
					}
				} else {
					if (password_filed) {
						try {
							if ((counter == 2) || (counter < 2)) {
								if (encryptedPrefs.getString("failed", "").equals("yes")) {
									if (encryptedPrefs.getString("secure_key", "").equals(edittext_pass.getText().toString())) {
										if (encryptedPrefs.getString("question", "").equals(edittext_question.getText().toString())) {
											_ifAuthisOk();
										} else {
											com.google.android.material.snackbar.Snackbar.make(linear1, "Incorrect question!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
												@Override
												public void onClick(View _view) {
													 
												}
											}).show();
										}
									} else {
										counter++;
										com.google.android.material.snackbar.Snackbar.make(linear1, "Incorrect master key!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
											@Override
											public void onClick(View _view) {
												 
											}
										}).show();
									}
								} else {
									if (encryptedPrefs.getString("secure_key", "").equals(edittext_pass.getText().toString())) {
										_ifAuthisOk();
									} else {
										counter++;
										com.google.android.material.snackbar.Snackbar.make(linear1, "Incorrect master key", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
											@Override
											public void onClick(View _view) {
												 
											}
										}).show();
									}
								}
							} else {
								com.google.android.material.snackbar.Snackbar.make(linear1, "You've tried too many times!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
									@Override
									public void onClick(View _view) {
										 
									}
								}).show();
								counter = 0;
								encryptedPrefs.edit().putString("failed", "yes").commit();
								textinputlayout_password.setVisibility(View.VISIBLE);
								textinputlayout_question.setVisibility(View.VISIBLE);
								button_fingerprint.setVisibility(View.GONE);
								textview_title.setText("Unlock With Extra Question");
								button_enter.setText("Enter");
							}
						} catch (Exception e) {
							 
						}
					} else {
						com.google.android.material.snackbar.Snackbar.make(linear1, "Empty field not allowed!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
							@Override
							public void onClick(View _view) {
								 
							}
						}).show();
					}
				}
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
		try {
			try {
					MasterKey masterKey = new MasterKey.Builder(this)
					.setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
					.build();
					
					encryptedPrefs = EncryptedSharedPreferences.create(
					this,
					"secure_prefs",
					masterKey,
					EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
					EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
					);
					
			} catch (GeneralSecurityException | IOException e) {
					Toast.makeText(this, "Encryption setup failed", Toast.LENGTH_SHORT).show();
			}
			
		} catch (Exception e) {
			 
		}
		BiometricManager biometricManager = BiometricManager.from(this);
		
		switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | 
		                                          BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
			    case BiometricManager.BIOMETRIC_SUCCESS:
			        break;
			    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
			        save.edit().putString("fingerprint auth", "unsupported").commit();
			        break;
			    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
			        save.edit().putString("fingerprint auth", "unsupported").commit();
			        break;
			    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
			        save.edit().putString("fingerprint auth", "unsupported").commit();
			        break;
		}
		if (encryptedPrefs.getString("failed", "").equals("yes")) {
			textinputlayout_password.setVisibility(View.VISIBLE);
			textinputlayout_question.setVisibility(View.VISIBLE);
			textview_title.setText("Unlock With Extra Question");
			button_enter.setText("Enter");
			linear_password_checker.setVisibility(View.GONE);
			button_fingerprint.setVisibility(View.GONE);
		} else {
			if (!encryptedPrefs.contains("question")) {
				textinputlayout_password.setVisibility(View.VISIBLE);
				textinputlayout_question.setVisibility(View.VISIBLE);
				textview_title.setText("Set security question");
				button_enter.setText("Next");
				linear_password_checker.setVisibility(View.VISIBLE);
				if (save.getString("fingerprint auth", "").equals("true")) {
					button_fingerprint.setVisibility(View.VISIBLE);
				} else {
					button_fingerprint.setVisibility(View.GONE);
				}
			} else {
				textinputlayout_password.setVisibility(View.VISIBLE);
				textinputlayout_question.setVisibility(View.GONE);
				textview_title.setText("Unlock With Master Password");
				button_enter.setText("Enter");
				linear_password_checker.setVisibility(View.GONE);
				if (save.getString("fingerprint auth", "").equals("true")) {
					button_fingerprint.setVisibility(View.VISIBLE);
				} else {
					button_fingerprint.setVisibility(View.GONE);
				}
			}
		}
		authentication = getIntent().getStringExtra("authentication");
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				finishAffinity();
			}
		});
	}
	
	public void _ifAuthisOk() {
		intent.setClass(getApplicationContext(), PasswordListActivity.class);
		intent.putExtra("authentication", "yes");
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AuthActivity.this);
		startActivity(intent, options.toBundle());
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _validation(final String _text) {
		if (FasterPasswordValidator.minLength(_text)) {
			textview_checker1.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker1.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker1.setImageResource(R.drawable.icon_check);
		} else {
			textview_checker1.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker1.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker1.setImageResource(R.drawable.icon_close);
		}
		if (FasterPasswordValidator.symbol(_text)) {
			textview_checker2.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker2.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker2.setImageResource(R.drawable.icon_check);
		} else {
			textview_checker2.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker2.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker2.setImageResource(R.drawable.icon_close);
		}
		if (FasterPasswordValidator.digit(_text)) {
			textview_checker3.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker3.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker3.setImageResource(R.drawable.icon_check);
		} else {
			textview_checker3.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker3.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker3.setImageResource(R.drawable.icon_close);
		}
		if (FasterPasswordValidator.upperCase(_text)) {
			textview_checker4.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker4.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker4.setImageResource(R.drawable.icon_check);
		} else {
			textview_checker4.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker4.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker4.setImageResource(R.drawable.icon_close);
		}
		if (FasterPasswordValidator.lowerCase(_text)) {
			textview_checker5.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker5.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker5.setImageResource(R.drawable.icon_check);
		} else {
			textview_checker5.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker5.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker5.setImageResource(R.drawable.icon_close);
		}
		int uniqueCount = FasterPasswordValidator.uniqueChars(_text);
		if (uniqueCount >= 6) {
			textview_checker6.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker6.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_primary));
			imageview_checker6.setImageResource(R.drawable.icon_check);
		} else {
			textview_checker6.setTextColor(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker6.setColorFilter(ContextCompat.getColor(AuthActivity.this, R.color.md_theme_error));
			imageview_checker6.setImageResource(R.drawable.icon_close);
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