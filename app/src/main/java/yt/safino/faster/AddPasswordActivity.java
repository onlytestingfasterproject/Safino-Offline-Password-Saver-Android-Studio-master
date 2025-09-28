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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.EncryptedSharedPreferences;
import java.io.IOException;
import java.security.GeneralSecurityException;
import androidx.core.content.ContextCompat;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AddPasswordActivity extends AppCompatActivity {
private SharedPreferences encryptedPrefs;
private ActivityResultLauncher<Intent> imagePickerLauncher;

	
	private FloatingActionButton _fab;
	private HashMap<String, Object> RecordMap = new HashMap<>();
	private double position = 0;
	private String imgFilePath = "";
	private boolean itDeleted = false;
	private String selectedImageUri = "";
	private String selectedImageUriString = "";
	
	private ArrayList<HashMap<String, Object>> RecordListMap = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private MaterialButton button5;
	private TextView textview1;
	private LinearLayout linear4;
	private TextInputLayout textinputlayout_username;
	private TextInputLayout textinputlayout_account;
	private LinearLayout linear6;
	private TextInputLayout textinputlayout_website;
	private TextInputLayout textinputlayout_notes;
	private RelativeLayout relativelayout_image;
	private TextInputLayout textinputlayout_title;
	private MaterialCardView linear_image_border;
	private MaterialButton linear_plus;
	private ImageView imageview1;
	private EditText edittext_title;
	private EditText edittext_username;
	private EditText edittext_account_name;
	private TextInputLayout textinputlayout_passsword;
	private MaterialButton button6;
	private EditText edittext_password;
	private EditText edittext_url;
	private EditText edittext_notes;
	
	private Intent backintent = new Intent();
	private SharedPreferences save;
	private Calendar date = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.add_password);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		button5 = findViewById(R.id.button5);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		textinputlayout_username = findViewById(R.id.textinputlayout_username);
		textinputlayout_account = findViewById(R.id.textinputlayout_account);
		linear6 = findViewById(R.id.linear6);
		textinputlayout_website = findViewById(R.id.textinputlayout_website);
		textinputlayout_notes = findViewById(R.id.textinputlayout_notes);
		relativelayout_image = findViewById(R.id.relativelayout_image);
		textinputlayout_title = findViewById(R.id.textinputlayout_title);
		linear_image_border = findViewById(R.id.linear_image_border);
		linear_plus = findViewById(R.id.linear_plus);
		imageview1 = findViewById(R.id.imageview1);
		edittext_title = findViewById(R.id.edittext_title);
		edittext_username = findViewById(R.id.edittext_username);
		edittext_account_name = findViewById(R.id.edittext_account_name);
		textinputlayout_passsword = findViewById(R.id.textinputlayout_passsword);
		button6 = findViewById(R.id.button6);
		edittext_password = findViewById(R.id.edittext_password);
		edittext_url = findViewById(R.id.edittext_url);
		edittext_notes = findViewById(R.id.edittext_notes);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				backintent.setClass(getApplicationContext(), PasswordListActivity.class);
				backintent.putExtra("authentication", "yes");
				ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AddPasswordActivity.this);
				startActivity(backintent, options.toBundle());
			}
		});
		
		linear_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (imgFilePath.equals("")) {
					Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					imagePickerLauncher.launch(intent);
				} else {
					_deleteImageFile(imgFilePath);
					Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(AddPasswordActivity.this, edittext_title.getText().toString(), 80);
					imageview1.setImageBitmap(profileImage);
					linear_plus.setIcon(ContextCompat.getDrawable(AddPasswordActivity.this, R.drawable.icon_plus));
					imgFilePath = "";
					itDeleted = true;
				}
			}
		});
		
		edittext_title.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (imgFilePath.equals("")) {
					if (getIntent().hasExtra("position")) {
						if (itDeleted) {
							Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(AddPasswordActivity.this, _charSeq, 80);
							imageview1.setImageBitmap(profileImage);
						} else {
							if (getIntent().getStringExtra("logo").equals("")) {
								Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(AddPasswordActivity.this, _charSeq, 80);
								imageview1.setImageBitmap(profileImage);
							} else {
								imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(getIntent().getStringExtra("logo"), 1024, 1024));
							}
						}
					} else {
						Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(AddPasswordActivity.this, _charSeq, 80);
						imageview1.setImageBitmap(profileImage);
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
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (save.getString("long password generate", "").equals("true")) {
					edittext_password.setText(FasterPasswordValidator.generateStrongPassword(18));
				} else {
					edittext_password.setText(FasterPasswordValidator.generateStrongPassword(12));
				}
			}
		});
		
		edittext_notes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_title.getText().toString().equals("")) {
					textinputlayout_title.setErrorEnabled(true);
					textinputlayout_title.setError("Fill in the required items!");
				} else {
					textinputlayout_title.setErrorEnabled(false);
					if (edittext_account_name.getText().toString().equals("")) {
						textinputlayout_account.setErrorEnabled(true);
						textinputlayout_account.setError("Fill in the required items!");
					} else {
						textinputlayout_account.setErrorEnabled(false);
						if (edittext_password.getText().toString().equals("")) {
							textinputlayout_passsword.setErrorEnabled(true);
							textinputlayout_passsword.setError("Fill in the required items!");
						} else {
							_saveImageToPrivateStorageLater();
							textinputlayout_passsword.setErrorEnabled(false);
							if (!encryptedPrefs.getString("secure_data", "").equals("")) {
								RecordListMap = new Gson().fromJson(encryptedPrefs.getString("secure_data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
							}
							date = Calendar.getInstance();
							RecordMap = new HashMap<>();
							RecordMap.put("logo", imgFilePath);
							RecordMap.put("title", edittext_title.getText().toString());
							RecordMap.put("username", edittext_username.getText().toString());
							RecordMap.put("email", edittext_account_name.getText().toString());
							RecordMap.put("password", edittext_password.getText().toString());
							RecordMap.put("website url", edittext_url.getText().toString());
							RecordMap.put("note", edittext_notes.getText().toString());
							if (save.getString("format", "").equals("12 hour")) {
								RecordMap.put("date", new SimpleDateFormat("E, dd MMM yyyy").format(date.getTime()));
								RecordMap.put("Last updated", new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss a").format(date.getTime()));
							} else {
								RecordMap.put("date", new SimpleDateFormat("E, dd MMM yyyy").format(date.getTime()));
								RecordMap.put("Last updated", new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss").format(date.getTime()));
							}
							if (getIntent().hasExtra("position")) {
								RecordListMap.remove((int)(position));
								RecordListMap.add((int)0, RecordMap);
								encryptedPrefs.edit().putString("secure_data", new Gson().toJson(RecordListMap)).commit();
							} else {
								RecordListMap.add((int)0, RecordMap);
								encryptedPrefs.edit().putString("secure_data", new Gson().toJson(RecordListMap)).commit();
							}
							button5.performClick();
						}
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(AddPasswordActivity.this, "", 80);
		imageview1.setImageBitmap(profileImage);
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
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				backintent.setClass(getApplicationContext(), PasswordListActivity.class);
				backintent.putExtra("authentication", "yes");
				ActivityOptions optionsback = ActivityOptions.makeSceneTransitionAnimation(AddPasswordActivity.this);
				startActivity(backintent, optionsback.toBundle());
			}
		});
		imagePickerLauncher = registerForActivityResult(
		new ActivityResultContracts.StartActivityForResult(),
		result -> {
				if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
						Uri selectedImageUri = result.getData().getData();
						if (selectedImageUri != null) {
								_loadImageToImageViewOnly(selectedImageUri);
									linear_plus.setIcon(ContextCompat.getDrawable(this, R.drawable.icon_delete));
						}
				}
		}
		);
		if (getIntent().hasExtra("position")) {
			edittext_title.setText(getIntent().getStringExtra("title"));
			edittext_username.setText(getIntent().getStringExtra("username"));
			edittext_account_name.setText(getIntent().getStringExtra("email"));
			edittext_password.setText(getIntent().getStringExtra("password"));
			edittext_url.setText(getIntent().getStringExtra("website url"));
			textview1.setText(getIntent().getStringExtra("title"));
			edittext_notes.setText(getIntent().getStringExtra("note"));
			position = Double.parseDouble(getIntent().getStringExtra("position"));
			imgFilePath = getIntent().getStringExtra("logo");
			if (!getIntent().getStringExtra("logo").equals("")) {
				imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(getIntent().getStringExtra("logo"), 1024, 1024));
			}
		}
		itDeleted = false;
		if (imgFilePath.equals("")) {
			linear_plus.setIcon(ContextCompat.getDrawable(this, R.drawable.icon_plus));
		} else {
			linear_plus.setIcon(ContextCompat.getDrawable(this, R.drawable.icon_delete));
		}
	}
	
	public void _changetheme() {
		if (save.contains("multi theme")) {
			if (save.getString("multi theme", "").equals("pink")) {
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _loadImageToImageViewOnly(final Uri _uri) {
		try {
				InputStream inputStream = getContentResolver().openInputStream(_uri);
				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				imageview1.setImageBitmap(bitmap);
				inputStream.close();
				
				selectedImageUriString = _uri.toString();
			    imgFilePath = _uri.toString();
				
				itDeleted = false;
			    linear_plus.setIcon(ContextCompat.getDrawable(this, R.drawable.icon_delete));
				
		} catch (Exception e) {
				Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	public void _saveImageToPrivateStorageLater() {
		if (selectedImageUriString != null && !selectedImageUriString.isEmpty()) {
			    try {
				        Uri uri = Uri.parse(selectedImageUriString);
				        InputStream inputStream = getContentResolver().openInputStream(uri);
				
				        String fileName = "logo_" + System.currentTimeMillis() + ".png";
				
				        File folder = new File(getFilesDir(), "hidden_logos");
				        if (!folder.exists()) {
					            boolean folderCreated = folder.mkdirs();
					            if (!folderCreated) {
						                Toast.makeText(this, "Failed to create folder", Toast.LENGTH_SHORT).show();
						                return;
						            }
					        }
				
				        File file = new File(folder, fileName);
				        OutputStream outputStream = new FileOutputStream(file);
				
				        byte[] buffer = new byte[4096];
				        int length;
				        while ((length = inputStream.read(buffer)) > 0) {
					            outputStream.write(buffer, 0, length);
					        }
				
				        inputStream.close();
				        outputStream.close();
				
				        imgFilePath = file.getAbsolutePath();
				    } catch (Exception e) {
				        Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
				    }
		}
	}
	
	
	public void _deleteImageFile(final String _path) {
		try {
				File file = new File(_path);
				if (file.exists()) {
						if (file.delete()) {
						} else {
								com.google.android.material.snackbar.Snackbar.make(linear1, "Failed to delete image file!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
										@Override
										public void onClick(View _view) {
												
										}
								}).show();
						}
				} else {
						com.google.android.material.snackbar.Snackbar.make(linear1, "Image file not found!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
								@Override
								public void onClick(View _view) {
										
								}
						}).show();
				}
		} catch (Exception e) {
				com.google.android.material.snackbar.Snackbar.make(linear1, "Error deleting image file", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
								
						}
				}).show();
		}
	}
	
}