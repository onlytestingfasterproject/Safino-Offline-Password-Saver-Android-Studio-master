package yt.safino.faster;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.EncryptedSharedPreferences;
import java.io.IOException;
import java.security.GeneralSecurityException;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;

public class SettingsActivity extends AppCompatActivity {
private SharedPreferences encryptedPrefs;
private ActivityResultLauncher<Intent> exportLauncher;
private ActivityResultLauncher<Intent> restoreLauncher;
	
	private boolean switch1 = false;
	
	private ArrayList<HashMap<String, Object>> secure_data = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear96;
	private LinearLayout linear2;
	private MaterialCardView linear_item_auth;
	private MaterialCardView linear_password;
	private MaterialCardView linear_date;
	private MaterialCardView linear_backup;
	private MaterialCardView linear_restore;
	private MaterialCardView linear_about;
	private MaterialCardView linear_day_night;
	private MaterialCardView linear_dynamic;
	private MaterialCardView linear_multi_themes;
	private MaterialButton button5;
	private TextView textview1;
	private LinearLayout linear100;
	private LinearLayout linear101;
	private LinearLayout linear102;
	private TextView textview3;
	private TextView textview2;
	private MaterialButtonToggleGroup linear_selection1;
	private MaterialButton button_on1;
	private MaterialButton button_off1;
	private LinearLayout linear104;
	private LinearLayout linear105;
	private LinearLayout linear106;
	private TextView textview4;
	private TextView textview5;
	private MaterialButtonToggleGroup linear107;
	private MaterialButton button_on2;
	private MaterialButton button_off2;
	private LinearLayout linear140;
	private LinearLayout linear141;
	private LinearLayout linear142;
	private TextView textview18;
	private TextView textview19;
	private MaterialButtonToggleGroup linear143;
	private MaterialButton button_on5;
	private MaterialButton button_off5;
	private LinearLayout linear109;
	private LinearLayout linear110;
	private LinearLayout linear111;
	private TextView textview6;
	private TextView textview7;
	private MaterialButton button6;
	private LinearLayout linear113;
	private LinearLayout linear114;
	private LinearLayout linear115;
	private TextView textview8;
	private TextView textview9;
	private MaterialButton button9;
	private LinearLayout linear123;
	private LinearLayout linear124;
	private LinearLayout linear125;
	private TextView textview12;
	private TextView textview13;
	private MaterialButton button15;
	private LinearLayout linear128;
	private LinearLayout linear129;
	private LinearLayout linear130;
	private TextView textview14;
	private TextView textview15;
	private MaterialButtonToggleGroup linear131;
	private MaterialButton button_day;
	private MaterialButton button_syd;
	private MaterialButton button_night;
	private LinearLayout linear133;
	private LinearLayout linear134;
	private LinearLayout linear135;
	private TextView textview16;
	private TextView textview17;
	private MaterialButtonToggleGroup linear136;
	private MaterialButton button_on4;
	private MaterialButton button_off4;
	private LinearLayout linear138;
	private LinearLayout linear82;
	private LinearLayout linear89;
	private MaterialCardView linear_color_default;
	private MaterialCardView linear_color_blue;
	private MaterialCardView linear_color_pink;
	private LinearLayout linear80;
	private MaterialCardView linear81;
	private LinearLayout linear84;
	private MaterialCardView linear85;
	private LinearLayout linear87;
	private MaterialCardView linear88;
	private MaterialCardView linear_color_red;
	private MaterialCardView linear_color_yellow;
	private LinearLayout linear91;
	private MaterialCardView linear92;
	private LinearLayout linear94;
	private MaterialCardView linear95;
	
	private SharedPreferences save;
	private Intent back_intent = new Intent();
	private Intent aboutIntent = new Intent();
	private AlertDialog.Builder appearanceChangerDialog;
	private AlertDialog.Builder appearanceDialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear96 = findViewById(R.id.linear96);
		linear2 = findViewById(R.id.linear2);
		linear_item_auth = findViewById(R.id.linear_item_auth);
		linear_password = findViewById(R.id.linear_password);
		linear_date = findViewById(R.id.linear_date);
		linear_backup = findViewById(R.id.linear_backup);
		linear_restore = findViewById(R.id.linear_restore);
		linear_about = findViewById(R.id.linear_about);
		linear_day_night = findViewById(R.id.linear_day_night);
		linear_dynamic = findViewById(R.id.linear_dynamic);
		linear_multi_themes = findViewById(R.id.linear_multi_themes);
		button5 = findViewById(R.id.button5);
		textview1 = findViewById(R.id.textview1);
		linear100 = findViewById(R.id.linear100);
		linear101 = findViewById(R.id.linear101);
		linear102 = findViewById(R.id.linear102);
		textview3 = findViewById(R.id.textview3);
		textview2 = findViewById(R.id.textview2);
		linear_selection1 = findViewById(R.id.linear_selection1);
		button_on1 = findViewById(R.id.button_on1);
		button_off1 = findViewById(R.id.button_off1);
		linear104 = findViewById(R.id.linear104);
		linear105 = findViewById(R.id.linear105);
		linear106 = findViewById(R.id.linear106);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		linear107 = findViewById(R.id.linear107);
		button_on2 = findViewById(R.id.button_on2);
		button_off2 = findViewById(R.id.button_off2);
		linear140 = findViewById(R.id.linear140);
		linear141 = findViewById(R.id.linear141);
		linear142 = findViewById(R.id.linear142);
		textview18 = findViewById(R.id.textview18);
		textview19 = findViewById(R.id.textview19);
		linear143 = findViewById(R.id.linear143);
		button_on5 = findViewById(R.id.button_on5);
		button_off5 = findViewById(R.id.button_off5);
		linear109 = findViewById(R.id.linear109);
		linear110 = findViewById(R.id.linear110);
		linear111 = findViewById(R.id.linear111);
		textview6 = findViewById(R.id.textview6);
		textview7 = findViewById(R.id.textview7);
		button6 = findViewById(R.id.button6);
		linear113 = findViewById(R.id.linear113);
		linear114 = findViewById(R.id.linear114);
		linear115 = findViewById(R.id.linear115);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		button9 = findViewById(R.id.button9);
		linear123 = findViewById(R.id.linear123);
		linear124 = findViewById(R.id.linear124);
		linear125 = findViewById(R.id.linear125);
		textview12 = findViewById(R.id.textview12);
		textview13 = findViewById(R.id.textview13);
		button15 = findViewById(R.id.button15);
		linear128 = findViewById(R.id.linear128);
		linear129 = findViewById(R.id.linear129);
		linear130 = findViewById(R.id.linear130);
		textview14 = findViewById(R.id.textview14);
		textview15 = findViewById(R.id.textview15);
		linear131 = findViewById(R.id.linear131);
		button_day = findViewById(R.id.button_day);
		button_syd = findViewById(R.id.button_syd);
		button_night = findViewById(R.id.button_night);
		linear133 = findViewById(R.id.linear133);
		linear134 = findViewById(R.id.linear134);
		linear135 = findViewById(R.id.linear135);
		textview16 = findViewById(R.id.textview16);
		textview17 = findViewById(R.id.textview17);
		linear136 = findViewById(R.id.linear136);
		button_on4 = findViewById(R.id.button_on4);
		button_off4 = findViewById(R.id.button_off4);
		linear138 = findViewById(R.id.linear138);
		linear82 = findViewById(R.id.linear82);
		linear89 = findViewById(R.id.linear89);
		linear_color_default = findViewById(R.id.linear_color_default);
		linear_color_blue = findViewById(R.id.linear_color_blue);
		linear_color_pink = findViewById(R.id.linear_color_pink);
		linear80 = findViewById(R.id.linear80);
		linear81 = findViewById(R.id.linear81);
		linear84 = findViewById(R.id.linear84);
		linear85 = findViewById(R.id.linear85);
		linear87 = findViewById(R.id.linear87);
		linear88 = findViewById(R.id.linear88);
		linear_color_red = findViewById(R.id.linear_color_red);
		linear_color_yellow = findViewById(R.id.linear_color_yellow);
		linear91 = findViewById(R.id.linear91);
		linear92 = findViewById(R.id.linear92);
		linear94 = findViewById(R.id.linear94);
		linear95 = findViewById(R.id.linear95);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		appearanceChangerDialog = new AlertDialog.Builder(this);
		appearanceDialog = new AlertDialog.Builder(this);
		
		linear_backup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_exportBackup();
			}
		});
		
		linear_restore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
				intent.setType("*/*");
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				restoreLauncher.launch(intent);
			}
		});
		
		linear_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				aboutIntent.setClass(getApplicationContext(), AboutActivity.class);
				Bundle opts3 = ActivityOptions
				    .makeSceneTransitionAnimation(SettingsActivity.this)
				    .toBundle();
				startActivity(aboutIntent, opts3);
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				back_intent.setClass(getApplicationContext(), PasswordListActivity.class);
				back_intent.putExtra("authentication", "yes");
				Bundle opts = ActivityOptions
				    .makeSceneTransitionAnimation(SettingsActivity.this)
				    .toBundle();
				startActivity(back_intent, opts);
			}
		});
		
		button_on1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("fingerprint auth", "true").commit();
				_selection();
			}
		});
		
		button_off1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("fingerprint auth", "false").commit();
				_selection();
			}
		});
		
		button_on2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("long password generate", "true").commit();
				_selection();
			}
		});
		
		button_off2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("long password generate", "false").commit();
				_selection();
			}
		});
		
		button_on5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("format", "12 hour").commit();
				_selection();
			}
		});
		
		button_off5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("format", "24 hour").commit();
				_selection();
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear_backup.performClick();
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear_restore.performClick();
			}
		});
		
		button15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear_about.performClick();
			}
		});
		
		button_day.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("appearance", "day").commit();
				_selection();
				MaterialAlertDialogBuilder appearanceChangerDialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
				appearanceChangerDialog.setTitle("Restart required!");
				appearanceChangerDialog.setMessage("You will need to exit the app and manually re-enter it. This will allow you to apply theme perfectly.");
				appearanceChangerDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						        finishAffinity();
						    }
				});
				appearanceChangerDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						         
						    }
				});
				appearanceChangerDialog.setCancelable(true);
				appearanceChangerDialog.create().show();
			}
		});
		
		button_syd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("appearance", "syd").commit();
				_selection();
				MaterialAlertDialogBuilder appearanceChangerDialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
				appearanceChangerDialog.setTitle("Restart required!");
				appearanceChangerDialog.setMessage("You will need to exit the app and manually re-enter it. This will allow you to apply theme perfectly.");
				appearanceChangerDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						        finishAffinity();
						    }
				});
				appearanceChangerDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						         
						    }
				});
				appearanceChangerDialog.setCancelable(true);
				appearanceChangerDialog.create().show();
			}
		});
		
		button_night.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("appearance", "night").commit();
				_selection();
				MaterialAlertDialogBuilder appearanceChangerDialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
				appearanceChangerDialog.setTitle("Restart required!");
				appearanceChangerDialog.setMessage("You will need to exit the app and manually re-enter it. This will allow you to apply theme perfectly.");
				appearanceChangerDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						        finishAffinity();
						    }
				});
				appearanceChangerDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						         
						    }
				});
				appearanceChangerDialog.setCancelable(true);
				appearanceChangerDialog.create().show();
			}
		});
		
		button_on4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("dynamic colors", "true").commit();
				_selection();
				MaterialAlertDialogBuilder appearanceChangerDialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
				appearanceChangerDialog.setTitle("Restart required!");
				appearanceChangerDialog.setMessage("You will need to exit the app and manually re-enter it. This will allow you to apply dynamic colors perfectly.");
				appearanceChangerDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						        finishAffinity();
						    }
				});
				appearanceChangerDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						         
						    }
				});
				appearanceChangerDialog.setCancelable(true);
				appearanceChangerDialog.create().show();
			}
		});
		
		button_off4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("dynamic colors", "false").commit();
				_selection();
				MaterialAlertDialogBuilder appearanceDialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
				appearanceDialog.setTitle("Restart required!");
				appearanceDialog.setMessage("You will need to exit the app and manually re-enter it. This will allow you to apply dynamic colors perfectly.");
				appearanceDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						        finishAffinity();
						    }
				});
				appearanceDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface _dialog, int _which) {
						         
						    }
				});
				appearanceDialog.setCancelable(true);
				appearanceDialog.create().show();
			}
		});
		
		linear_color_default.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("multi theme", "default").commit();
				Intent intent = getIntent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear_color_blue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("multi theme", "blue").commit();
				Intent intent = getIntent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear_color_pink.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("multi theme", "pink").commit();
				Intent intent = getIntent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear_color_red.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("multi theme", "red").commit();
				Intent intent = getIntent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear_color_yellow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				save.edit().putString("multi theme", "yellow").commit();
				Intent intent = getIntent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
	}
	
	private void initializeLogic() {
		_check();
		_selection();
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				back_intent.setClass(getApplicationContext(), PasswordListActivity.class);
				back_intent.putExtra("authentication", "yes");
				Bundle opts = ActivityOptions
				    .makeSceneTransitionAnimation(SettingsActivity.this)
				    .toBundle();
				startActivity(back_intent, opts);
			}
		});
		exportLauncher = registerForActivityResult(
		    new ActivityResultContracts.StartActivityForResult(),
		    result -> {
			        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
				            Uri uri = result.getData().getData();
				            try {
					                File zipFile = new File(getCacheDir(), "backup.sf");
					                InputStream in = new FileInputStream(zipFile);
					                OutputStream out = getContentResolver().openOutputStream(uri);
					
					                byte[] buffer = new byte[4096];
					                int length;
					                while ((length = in.read(buffer)) > 0) {
						                    out.write(buffer, 0, length);
						                }
					
					                in.close();
					                out.close();
					
					                com.google.android.material.snackbar.Snackbar.make(
					                    linear96,
					                    "Export successful!",
					                    com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
					                ).setAction("", new View.OnClickListener() {
						                    @Override
						                    public void onClick(View _view) {
							                    }
						                }).show();
					            } catch (Exception e) {
					                com.google.android.material.snackbar.Snackbar.make(
					                    linear96,
					                    "Save failed: " + e.getMessage(),
					                    com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
					                ).setAction("", new View.OnClickListener() {
						                    @Override
						                    public void onClick(View _view) {
							                    }
						                }).show();
					            }
				        }
			    }
		);
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		WindowInsetsControllerCompat insetsController =
		new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
		
		insetsController.setSystemBarsBehavior(
		WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
		);
		restoreLauncher = registerForActivityResult(
		    new ActivityResultContracts.StartActivityForResult(),
		    result -> {
			        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
				            Uri uri = result.getData().getData();
				            _restoreBackup(uri);
				        }
			    }
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
	}
	public void unzip(String zipFilePath, String targetDir) throws IOException {
		    ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
		    ZipEntry ze;
		    while ((ze = zis.getNextEntry()) != null) {
			        File file = new File(targetDir, ze.getName());
			        if (ze.isDirectory()) {
				            file.mkdirs();
				        } else {
				            File parent = file.getParentFile();
				            if (!parent.exists()) parent.mkdirs();
				
				            FileOutputStream fos = new FileOutputStream(file);
				            byte[] buffer = new byte[4096];
				            int len;
				            while ((len = zis.read(buffer)) > 0) {
					                fos.write(buffer, 0, len);
					            }
				            fos.close();
				        }
			        zis.closeEntry();
			    }
		    zis.close();
	}
	
	public void copyFile(File src, File dest) throws IOException {
		    InputStream in = new FileInputStream(src);
		    OutputStream out = new FileOutputStream(dest);
		    byte[] buffer = new byte[4096];
		    int length;
		    while ((length = in.read(buffer)) > 0) {
			        out.write(buffer, 0, length);
			    }
		    in.close();
		    out.close();
	}
	public void deleteRecursive(File fileOrDirectory) {
		    if (fileOrDirectory.isDirectory()) {
			        for (File child : fileOrDirectory.listFiles()) {
				            deleteRecursive(child);
				        }
			    }
		    fileOrDirectory.delete();
	}
	public void zipDirectory(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
			for (File file : folder.listFiles()) {
					if (file.isDirectory()) {
							zipDirectory(file, parentFolder + "/" + file.getName(), zos);
							continue;
					}
					FileInputStream fis = new FileInputStream(file);
					ZipEntry zipEntry = new ZipEntry(parentFolder + "/" + file.getName());
					zos.putNextEntry(zipEntry);
					
					byte[] bytes = new byte[4096];
					int length;
					while ((length = fis.read(bytes)) >= 0) {
							zos.write(bytes, 0, length);
					}
					fis.close();
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
	
	
	public void _check() {
		if (save.getString("dynamic colors", "").equals("false")) {
			if (save.getString("multi theme", "").equals("pink")) {
				linear_color_default.setChecked(false);
				linear_color_pink.setChecked(true);
				linear_color_blue.setChecked(false);
				linear_color_red.setChecked(false);
				linear_color_yellow.setChecked(false);
			} else {
				if (save.getString("multi theme", "").equals("red")) {
					linear_color_default.setChecked(false);
					linear_color_pink.setChecked(false);
					linear_color_blue.setChecked(false);
					linear_color_red.setChecked(true);
					linear_color_yellow.setChecked(false);
				} else {
					if (save.getString("multi theme", "").equals("blue")) {
						linear_color_default.setChecked(false);
						linear_color_pink.setChecked(false);
						linear_color_blue.setChecked(true);
						linear_color_red.setChecked(false);
						linear_color_yellow.setChecked(false);
					} else {
						if (save.getString("multi theme", "").equals("yellow")) {
							linear_color_default.setChecked(false);
							linear_color_pink.setChecked(false);
							linear_color_blue.setChecked(false);
							linear_color_red.setChecked(false);
							linear_color_yellow.setChecked(true);
						} else {
							save.edit().putString("multi theme", "default").commit();
							linear_color_default.setChecked(true);
							linear_color_pink.setChecked(false);
							linear_color_blue.setChecked(false);
							linear_color_red.setChecked(false);
							linear_color_yellow.setChecked(false);
						}
					}
				}
			}
		} else {
			if (save.getString("dynamic colors", "").equals("unsupported")) {
				if (save.getString("multi theme", "").equals("pink")) {
					linear_color_default.setChecked(false);
					linear_color_pink.setChecked(true);
					linear_color_blue.setChecked(false);
					linear_color_red.setChecked(false);
					linear_color_yellow.setChecked(false);
				} else {
					if (save.getString("multi theme", "").equals("red")) {
						linear_color_default.setChecked(false);
						linear_color_pink.setChecked(false);
						linear_color_blue.setChecked(false);
						linear_color_red.setChecked(true);
						linear_color_yellow.setChecked(false);
					} else {
						if (save.getString("multi theme", "").equals("blue")) {
							linear_color_default.setChecked(false);
							linear_color_pink.setChecked(false);
							linear_color_blue.setChecked(true);
							linear_color_red.setChecked(false);
							linear_color_yellow.setChecked(false);
						} else {
							if (save.getString("multi theme", "").equals("yellow")) {
								linear_color_default.setChecked(false);
								linear_color_pink.setChecked(false);
								linear_color_blue.setChecked(false);
								linear_color_red.setChecked(false);
								linear_color_yellow.setChecked(true);
							} else {
								save.edit().putString("multi theme", "default").commit();
								linear_color_default.setChecked(true);
								linear_color_pink.setChecked(false);
								linear_color_blue.setChecked(false);
								linear_color_red.setChecked(false);
								linear_color_yellow.setChecked(false);
							}
						}
					}
				}
			} else {
				save.edit().putString("multi theme", "default").commit();
				linear_color_default.setChecked(false);
				linear_color_pink.setChecked(false);
				linear_color_blue.setChecked(false);
				linear_color_red.setChecked(false);
				linear_color_yellow.setChecked(false);
			}
		}
	}
	
	
	public void _selection() {
		if (save.getString("long password generate", "").equals("true")) {
			button_on2.setChecked(true);
		} else {
			button_off2.setChecked(true);
		}
		if (save.getString("format", "").equals("12 hour")) {
			button_on5.setChecked(true);
		} else {
			button_off5.setChecked(true);
		}
		if (save.getString("appearance", "").equals("day")) {
			button_day.setChecked(true);
		} else {
			if (save.getString("appearance", "").equals("syd")) {
				button_syd.setChecked(true);
			} else {
				button_night.setChecked(true);
			}
		}
		if (save.getString("dynamic colors", "").equals("unsupported")) {
			_check();
			button_off4.setChecked(false);
			button_on4.setChecked(false);
			button_off4.setEnabled(false);
			button_on4.setEnabled(false);
			linear_multi_themes.setEnabled(true);
			linear_color_default.setEnabled(true);
			linear_color_blue.setEnabled(true);
			linear_color_pink.setEnabled(true);
			linear_color_red.setEnabled(true);
			linear_color_yellow.setEnabled(true);
		} else {
			if (save.getString("dynamic colors", "").equals("true")) {
				_check();
				button_on4.setChecked(true);
				linear_multi_themes.setEnabled(false);
				linear_color_default.setEnabled(false);
				linear_color_blue.setEnabled(false);
				linear_color_pink.setEnabled(false);
				linear_color_red.setEnabled(false);
				linear_color_yellow.setEnabled(false);
			} else {
				_check();
				button_off4.setChecked(true);
				linear_multi_themes.setEnabled(true);
				linear_color_default.setEnabled(true);
				linear_color_blue.setEnabled(true);
				linear_color_pink.setEnabled(true);
				linear_color_red.setEnabled(true);
				linear_color_yellow.setEnabled(true);
			}
		}
		if (save.getString("fingerprint auth", "").equals("unsupported")) {
			button_on1.setChecked(false);
			button_off1.setChecked(false);
			button_on1.setEnabled(false);
			button_off1.setEnabled(false);
		} else {
			if (save.getString("fingerprint auth", "").equals("true")) {
				button_on1.setChecked(true);
			} else {
				button_off1.setChecked(true);
			}
		}
	}
	
	
	public void _exportBackup() {
		try {
				String secureDataJson = encryptedPrefs.getString("secure_data", "[]");
				
				File folder = new File(getFilesDir(), "hidden_logos");
				if (!folder.exists()) folder.mkdirs();
				
				File jsonFile = new File(folder, "secure_data.json");
				FileWriter writer = new FileWriter(jsonFile);
				writer.write(secureDataJson);
				writer.close();
				
				File sfFile = new File(getCacheDir(), "backup.sf");
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(sfFile));
				zipDirectory(folder, folder.getName(), zos);
				zos.close();
				
				Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.setType("application/octet-stream"); 
				intent.putExtra(Intent.EXTRA_TITLE, "backup.sf");
				exportLauncher.launch(intent);
				
		} catch (Exception e) {
				com.google.android.material.snackbar.Snackbar.make(linear96, "Export failed: " + e.getMessage(), com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
								
						}
				}).show();
		}
	}
	
	
	public void _restoreBackup(final Uri _uri) {
		try {
				InputStream in = getContentResolver().openInputStream(_uri);
				File tempSf = new File(getCacheDir(), "restore_temp.sf");
				OutputStream out = new FileOutputStream(tempSf);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
				}
				in.close();
				out.close();
				
				File extractFolder = new File(getCacheDir(), "restore_temp_folder");
				if (extractFolder.exists()) deleteRecursive(extractFolder);
				extractFolder.mkdirs();
				unzip(tempSf.getAbsolutePath(), extractFolder.getAbsolutePath());
				
				File jsonFile = new File(extractFolder, "hidden_logos/secure_data.json");
				if (!jsonFile.exists()) {
						com.google.android.material.snackbar.Snackbar.make(linear96, "Missing secure_data.json in backup", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
								@Override
								public void onClick(View _view) {
										
								}
						}).show();
						return;
				}
				
				BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) sb.append(line);
				reader.close();
				
				encryptedPrefs.edit().putString("secure_data", sb.toString()).apply();
				
				File srcFolder = new File(extractFolder, "hidden_logos");
				File destFolder = new File(getFilesDir(), "hidden_logos");
				if (!destFolder.exists()) destFolder.mkdirs();
				
				for (File file : srcFolder.listFiles()) {
						if (!file.getName().equals("secure_data.json")) {
								copyFile(file, new File(destFolder, file.getName()));
						}
				}
				
				com.google.android.material.snackbar.Snackbar.make(linear96, "Restore Completed!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
								
						}
				}).show();
		} catch (Exception e) {
				com.google.android.material.snackbar.Snackbar.make(linear96, "Restore failed: " + e.getMessage(), com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
								
						}
				}).show();
		}
	}
	
}