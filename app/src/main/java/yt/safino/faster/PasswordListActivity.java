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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.google.android.material.card.*;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.EncryptedSharedPreferences;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class PasswordListActivity extends AppCompatActivity {
private SharedPreferences encryptedPrefs;
	
	private ExtendedFloatingActionButton _fab;
	private String authentication = "";
	private boolean switch_boolean = false;
	private String searchText = "";
	private boolean isSearch = false;
	
	private ArrayList<HashMap<String, Object>> list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> originalList = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear_toolbar;
	private LinearLayout linear_search_view;
	private ListView listview1;
	private GridView gridview1;
	private TextView textview_status;
	private TextView textview1;
	private MaterialButton button_search;
	private MaterialButton button4;
	private MaterialButton button3;
	private MaterialButton button5;
	private EditText edittext_search;
	private MaterialButton button6;
	
	private SharedPreferences save;
	private Intent intent = new Intent();
	private Intent intent2 = new Intent();
	private AlertDialog.Builder extraquestion_dialog;
	private Intent intent3 = new Intent();
	private AlertDialog.Builder deleteDialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
save = getSharedPreferences("save", Activity.MODE_PRIVATE);
_changetheme();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.password_list);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear_toolbar = findViewById(R.id.linear_toolbar);
		linear_search_view = findViewById(R.id.linear_search_view);
		listview1 = findViewById(R.id.listview1);
		gridview1 = findViewById(R.id.gridview1);
		textview_status = findViewById(R.id.textview_status);
		textview1 = findViewById(R.id.textview1);
		button_search = findViewById(R.id.button_search);
		button4 = findViewById(R.id.button4);
		button3 = findViewById(R.id.button3);
		button5 = findViewById(R.id.button5);
		edittext_search = findViewById(R.id.edittext_search);
		button6 = findViewById(R.id.button6);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		extraquestion_dialog = new AlertDialog.Builder(this);
		deleteDialog = new AlertDialog.Builder(this);
		
		button_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_toggleSearchBar(linear_toolbar, linear_search_view);
				_fab.hide();
				FasterUtils.showKeyboard(PasswordListActivity.this, edittext_search);
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (switch_boolean) {
					button4.setIconResource(R.drawable.icon_view_stream);
					_animateViewHide(listview1, gridview1);
					if (encryptedPrefs.contains("secure_data")) {
						list_map = new Gson().fromJson(encryptedPrefs.getString("secure_data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						gridview1.setAdapter(new Gridview1Adapter(list_map));
					} else {
						textview_status.setVisibility(View.VISIBLE);
					}
					switch_boolean = false;
				} else {
					button4.setIconResource(R.drawable.icon_grid_view);
					_animateViewHide(gridview1, listview1);
					switch_boolean = true;
				}
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), SettingsActivity.class);
				ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PasswordListActivity.this);
				startActivity(intent, options.toBundle());
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FasterUtils.hideKeyboard(PasswordListActivity.this, edittext_search);
				_toggleSearchBar(linear_search_view, linear_toolbar);
				edittext_search.setText("");
				_fab.show();
			}
		});
		
		edittext_search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				_TransitionManager(linear1, 150);
				_runSearch(_charSeq);
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
				edittext_search.setText("");
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent2.setClass(getApplicationContext(), AddPasswordActivity.class);
				ActivityOptions options2 = ActivityOptions.makeSceneTransitionAnimation(PasswordListActivity.this);
				startActivity(intent2, options2.toBundle());
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
		authentication = getIntent().getStringExtra("authentication");
		switch_boolean = true;
		getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				finishAffinity();
			}
		});
		if (encryptedPrefs.getString("failed", "").equals("yes")) {
			MaterialAlertDialogBuilder extraquestion_dialog = new MaterialAlertDialogBuilder(PasswordListActivity.this);
			extraquestion_dialog.setTitle("Warning!");
			extraquestion_dialog.setMessage("Do you want remove extra question to unlock app?");
			extraquestion_dialog.setPositiveButton("Yes, sure", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface _dialog, int _which) {
					        encryptedPrefs.edit().putString("failed", "no").commit();
					    }
			});
			extraquestion_dialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface _dialog, int _which) {
					         
					    }
			});
			extraquestion_dialog.setCancelable(true);
			extraquestion_dialog.create().show();
		}
		textview_status.setVisibility(View.GONE);
		if (encryptedPrefs.contains("secure_data")) {
			if (encryptedPrefs.getString("secure_data", "").equals("[]") || encryptedPrefs.getString("secure_data", "").equals("")) {
				textview_status.setVisibility(View.VISIBLE);
			} else {
				originalList = new Gson().fromJson(encryptedPrefs.getString("secure_data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview1.setAdapter(new Listview1Adapter(originalList));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				gridview1.setAdapter(new Gridview1Adapter(originalList));
			}
		} else {
			textview_status.setVisibility(View.VISIBLE);
		}
	}
	
	public void _animateViewHide(final View _viewToHide, final View _viewToShow) {
		_viewToHide.animate()
		.alpha(0f)
		.scaleX(0.8f)
		.scaleY(0.8f)
		.setDuration(150)
		.setInterpolator(new AccelerateInterpolator())
		.withEndAction(new Runnable() {
				@Override
				public void run() {
						_viewToHide.setVisibility(View.GONE);
						_viewToShow.setAlpha(0f);
						_viewToShow.setScaleX(0.8f);
						_viewToShow.setScaleY(0.8f);
						_viewToShow.setVisibility(View.VISIBLE);
						_viewToShow.animate()
						.alpha(1f)
						.scaleX(1f)
						.scaleY(1f)
						.setDuration(150)
						.setInterpolator(new DecelerateInterpolator())
						.start();
				}
		})
		.start();
		
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
	
	
	public void _toggleSearchBar(final View _fromView, final View _toView) {
		_fromView.animate()
		        .alpha(0f)
		        .translationY(-_fromView.getHeight() / 2f)
		        .scaleX(0.95f)
		        .scaleY(0.95f)
		        .setDuration(250)
		        .setInterpolator(new AccelerateInterpolator())
		        .withEndAction(new Runnable() {
			            @Override
			            public void run() {
				                _fromView.setVisibility(View.GONE);
				                _toView.setAlpha(0f);
				                _toView.setScaleX(0.95f);
				                _toView.setScaleY(0.95f);
				                _toView.setTranslationY(-_toView.getHeight() / 2f);
				                _toView.setVisibility(View.VISIBLE);
				                _toView.animate()
				                    .alpha(1f)
				                    .translationY(0f)
				                    .scaleX(1f)
				                    .scaleY(1f)
				                    .setDuration(250)
				                    .setInterpolator(new DecelerateInterpolator())
				                    .start();
				            }
			        })
		        .start();
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _shareText(final String _text) {
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setType("text/plain");
		sendIntent.putExtra(Intent.EXTRA_TEXT, _text);
		
		Intent shareIntent = Intent.createChooser(sendIntent, null);
		startActivity(shareIntent);
	}
	
	
	public void _runSearch(final String _query) {
		searchText = _query;
		filteredList.clear();
		
		if (!_query.equals("")) {
				isSearch = true;
				for (HashMap<String, Object> item : originalList) {
						String title = item.get("title") + "";
						if (title.toLowerCase().contains(_query.toLowerCase())) {
								filteredList.add(item);
						}
				}
		} else {
				isSearch = false;
				filteredList.addAll(originalList);
		}
		
		if (filteredList.size() == 0) {
				textview_status.setVisibility(View.VISIBLE);
		} else {
				textview_status.setVisibility(View.GONE);
		}
		listview1.setAdapter(new Listview1Adapter(filteredList));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		gridview1.setAdapter(new Gridview1Adapter(filteredList));
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
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.password_list_cus, null);
			}
			
			final com.google.android.material.card.MaterialCardView linear_main = _view.findViewById(R.id.linear_main);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final com.google.android.material.card.MaterialCardView linear_icon_card = _view.findViewById(R.id.linear_icon_card);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final TextView textview_name = _view.findViewById(R.id.textview_name);
			final TextView textview_title = _view.findViewById(R.id.textview_title);
			final TextView textview_date = _view.findViewById(R.id.textview_date);
			
			if (_data.get((int)_position).containsKey("logo")) {
				if (_data.get((int)_position).get("logo").toString().equals("")) {
					Bitmap profileImage = FasterDefaultProfileImageGenerator.generate(
					PasswordListActivity.this, 
					_data.get((int)_position).get("title").toString(), 
					50
					);
					imageview1.setImageBitmap(profileImage);
				} else {
					imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_data.get((int)_position).get("logo").toString(), 1024, 1024));
				}
			}
			if (_data.get((int)_position).containsKey("title")) {
				textview_title.setText(_data.get((int)_position).get("title").toString());
			}
			if (_data.get((int)_position).containsKey("email")) {
				textview_name.setText(_data.get((int)_position).get("email").toString());
			}
			if (_data.get((int)_position).containsKey("date")) {
				textview_date.setText(_data.get((int)_position).get("date").toString());
			}
			linear_main.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isSearch) {
							if (_position >= 0 && _position < filteredList.size()) {
									HashMap<String, Object> clickedItem = filteredList.get(_position);
									int originalIndex = originalList.indexOf(clickedItem);
									
									if (originalIndex != -1) {
								intent3.setClass(getApplicationContext(), ViewPasswordActivity.class);
								intent3.putExtra("position", String.valueOf((long)(originalIndex)));
								intent3.putExtra("title", _data.get((int)_position).get("title").toString());
								intent3.putExtra("email", _data.get((int)_position).get("email").toString());
								intent3.putExtra("date", _data.get((int)_position).get("date").toString());
								intent3.putExtra("logo", _data.get((int)_position).get("logo").toString());
								intent3.putExtra("website url", _data.get((int)_position).get("website url").toString());
								intent3.putExtra("username", _data.get((int)_position).get("username").toString());
								intent3.putExtra("password", _data.get((int)_position).get("password").toString());
								intent3.putExtra("note", _data.get((int)_position).get("note").toString());
								intent3.putExtra("Last updated", _data.get((int)_position).get("Last updated").toString());
								ActivityOptions options3 = ActivityOptions.makeSceneTransitionAnimation(PasswordListActivity.this);
								startActivity(intent3, options3.toBundle());
												}
							}
					} else {
							if (_position >= 0 && _position < originalList.size()) {
							intent3.setClass(getApplicationContext(), ViewPasswordActivity.class);
							intent3.putExtra("position", String.valueOf((long)(_position)));
							intent3.putExtra("title", _data.get((int)_position).get("title").toString());
							intent3.putExtra("email", _data.get((int)_position).get("email").toString());
							intent3.putExtra("date", _data.get((int)_position).get("date").toString());
							intent3.putExtra("logo", _data.get((int)_position).get("logo").toString());
							intent3.putExtra("website url", _data.get((int)_position).get("website url").toString());
							intent3.putExtra("username", _data.get((int)_position).get("username").toString());
							intent3.putExtra("password", _data.get((int)_position).get("password").toString());
							intent3.putExtra("note", _data.get((int)_position).get("note").toString());
							intent3.putExtra("Last updated", _data.get((int)_position).get("Last updated").toString());
							ActivityOptions options3 = ActivityOptions.makeSceneTransitionAnimation(PasswordListActivity.this);
							startActivity(intent3, options3.toBundle());
									}
					}
				}
			});
			linear_main.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					final
					com.google.android.material.bottomsheet.BottomSheetDialog deleteSheet = new com.google.android.material.bottomsheet.BottomSheetDialog(PasswordListActivity.this);
					View deleteSheetDesign = getLayoutInflater().inflate(R.layout.delete_bottom_sheet, null);
					deleteSheet.setContentView(deleteSheetDesign);
					deleteSheet.getWindow().getDecorView().setBackgroundColor(0);
					final MaterialCardView linear_edit = deleteSheetDesign.findViewById(R.id.linear_edit);
					final MaterialCardView linear_share = deleteSheetDesign.findViewById(R.id.linear_share);
					final MaterialCardView linear_delete = deleteSheetDesign.findViewById(R.id.linear_delete);
					linear_edit.setOnClickListener(v -> {
						deleteSheet.dismiss();
						linear_main.performClick();
								        });
					linear_share.setOnClickListener(v -> {
						deleteSheet.dismiss();
						_shareText("Details:\nTitle: ".concat(_data.get((int)_position).get("title").toString().concat("\nEmail: ".concat(_data.get((int)_position).get("email").toString().concat("\nUsername: ".concat(_data.get((int)_position).get("username").toString().concat("\nPassword: ".concat(_data.get((int)_position).get("username").toString().concat("\nWebsite url: ".concat(_data.get((int)_position).get("website url").toString().concat("\nExtra note: ".concat(_data.get((int)_position).get("note").toString()))))))))))));
								        });
					linear_delete.setOnClickListener(v -> {
						deleteSheet.dismiss();
						MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(PasswordListActivity.this);
						deleteDialog.setTitle("Do you want to delete this record?");
						deleteDialog.setMessage("If you delete it, it will be completely erased and you will never be able to access it again, so make a backup before doing so.");
						deleteDialog.setPositiveButton("Yes, sure", new DialogInterface.OnClickListener() {
							    @Override
							    public void onClick(DialogInterface _dialog, int _which) {
								        if (!_data.get((int)_position).get("logo").toString().equals("")) {
									_deleteImageFile(_data.get((int)_position).get("logo").toString());
								}
								if (isSearch) {
										if (_position >= 0 && _position < filteredList.size()) {
												HashMap<String, Object> clickedItem = filteredList.get(_position);
												int originalIndex = originalList.indexOf(clickedItem);
												
												if (originalIndex != -1) {
														originalList.remove(originalIndex);
														encryptedPrefs.edit()
														.putString("secure_data", new Gson().toJson(originalList))
														.apply();
														com.google.android.material.snackbar.Snackbar.make(linear1, "Delete successful!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
																@Override
																public void onClick(View _view) {
																		
																}
														}).show();
														_runSearch(searchText);
												}
										}
								} else {
										if (_position >= 0 && _position < originalList.size()) {
												originalList.remove(_position);
												encryptedPrefs.edit()
												.putString("secure_data", new Gson().toJson(originalList))
												.apply();
												
												filteredList.clear();
												filteredList.addAll(originalList);
												
												com.google.android.material.snackbar.Snackbar.make(linear1, "Delete successful!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
														@Override
														public void onClick(View _view) {
																
														}
												}).show();
												listview1.setAdapter(new Listview1Adapter(originalList));
												((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
										        gridview1.setAdapter(new Gridview1Adapter(originalList));
										}
								}
								if (encryptedPrefs.getString("secure_data", "").equals("[]") || encryptedPrefs.getString("secure_data", "").equals("")) {
									textview_status.setVisibility(View.VISIBLE);
								}
								    }
						});
						deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
							    @Override
							    public void onClick(DialogInterface _dialog, int _which) {
								         
								    }
						});
						deleteDialog.setCancelable(true);
						deleteDialog.create().show();
								        });
					deleteSheet.setCancelable(true);
					deleteSheet.show();
					return true;
				}
			});
			
			return _view;
		}
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.password_grid_view_cus, null);
			}
			
			final com.google.android.material.card.MaterialCardView linear_main = _view.findViewById(R.id.linear_main);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView textview_date = _view.findViewById(R.id.textview_date);
			final com.google.android.material.card.MaterialCardView linear_icon_card = _view.findViewById(R.id.linear_icon_card);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview_title = _view.findViewById(R.id.textview_title);
			final TextView textview_name = _view.findViewById(R.id.textview_name);
			
			if (_data.get((int)_position).containsKey("logo")) {
				if (_data.get((int)_position).get("logo").toString().equals("")) {
					Bitmap profileImage2 = FasterDefaultProfileImageGenerator.generate(
					PasswordListActivity.this, 
					_data.get((int)_position).get("title").toString(), 
					50
					);
					imageview1.setImageBitmap(profileImage2);
				} else {
					imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_data.get((int)_position).get("logo").toString(), 1024, 1024));
				}
			}
			if (_data.get((int)_position).containsKey("title")) {
				textview_title.setText(_data.get((int)_position).get("title").toString());
			}
			if (_data.get((int)_position).containsKey("email")) {
				textview_name.setText(_data.get((int)_position).get("email").toString());
			}
			if (_data.get((int)_position).containsKey("date")) {
				textview_date.setText(_data.get((int)_position).get("date").toString());
			}
			linear_main.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isSearch) {
							if (_position >= 0 && _position < filteredList.size()) {
									HashMap<String, Object> clickedItem = filteredList.get(_position);
									int originalIndex = originalList.indexOf(clickedItem);
									
									if (originalIndex != -1) {
								intent3.setClass(getApplicationContext(), ViewPasswordActivity.class);
								intent3.putExtra("position", String.valueOf((long)(originalIndex)));
								intent3.putExtra("title", _data.get((int)_position).get("title").toString());
								intent3.putExtra("email", _data.get((int)_position).get("email").toString());
								intent3.putExtra("date", _data.get((int)_position).get("date").toString());
								intent3.putExtra("logo", _data.get((int)_position).get("logo").toString());
								intent3.putExtra("website url", _data.get((int)_position).get("website url").toString());
								intent3.putExtra("username", _data.get((int)_position).get("username").toString());
								intent3.putExtra("password", _data.get((int)_position).get("password").toString());
								intent3.putExtra("note", _data.get((int)_position).get("note").toString());
								intent3.putExtra("Last updated", _data.get((int)_position).get("Last updated").toString());
								ActivityOptions options3 = ActivityOptions.makeSceneTransitionAnimation(PasswordListActivity.this);
								startActivity(intent3, options3.toBundle());
										    	}
							}
					} else {
							if (_position >= 0 && _position < originalList.size()) {
							intent3.setClass(getApplicationContext(), ViewPasswordActivity.class);
							intent3.putExtra("position", String.valueOf((long)(_position)));
							intent3.putExtra("title", _data.get((int)_position).get("title").toString());
							intent3.putExtra("email", _data.get((int)_position).get("email").toString());
							intent3.putExtra("date", _data.get((int)_position).get("date").toString());
							intent3.putExtra("logo", _data.get((int)_position).get("logo").toString());
							intent3.putExtra("website url", _data.get((int)_position).get("website url").toString());
							intent3.putExtra("username", _data.get((int)_position).get("username").toString());
							intent3.putExtra("password", _data.get((int)_position).get("password").toString());
							intent3.putExtra("note", _data.get((int)_position).get("note").toString());
							intent3.putExtra("Last updated", _data.get((int)_position).get("Last updated").toString());
							ActivityOptions options3 = ActivityOptions.makeSceneTransitionAnimation(PasswordListActivity.this);
							startActivity(intent3, options3.toBundle());
							}
					}
				}
			});
			linear_main.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					final
					com.google.android.material.bottomsheet.BottomSheetDialog deleteSheet = new com.google.android.material.bottomsheet.BottomSheetDialog(PasswordListActivity.this);
					View deleteSheetDesign = getLayoutInflater().inflate(R.layout.delete_bottom_sheet, null);
					deleteSheet.setContentView(deleteSheetDesign);
					deleteSheet.getWindow().getDecorView().setBackgroundColor(0);
					final MaterialCardView linear_edit = deleteSheetDesign.findViewById(R.id.linear_edit);
					final MaterialCardView linear_share = deleteSheetDesign.findViewById(R.id.linear_share);
					final MaterialCardView linear_delete = deleteSheetDesign.findViewById(R.id.linear_delete);
					linear_edit.setOnClickListener(v -> {
						deleteSheet.dismiss();
						linear_main.performClick();
								        });
					linear_share.setOnClickListener(v -> {
						deleteSheet.dismiss();
						_shareText("Details:\nTitle: ".concat(_data.get((int)_position).get("title").toString().concat("\nEmail: ".concat(_data.get((int)_position).get("email").toString().concat("\nUsername: ".concat(_data.get((int)_position).get("username").toString().concat("\nPassword: ".concat(_data.get((int)_position).get("username").toString().concat("\nWebsite url: ".concat(_data.get((int)_position).get("website url").toString().concat("\nExtra note: ".concat(_data.get((int)_position).get("note").toString()))))))))))));
								        });
					linear_delete.setOnClickListener(v -> {
						deleteSheet.dismiss();
						MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(PasswordListActivity.this);
						deleteDialog.setTitle("Do you want to delete this record?");
						deleteDialog.setMessage("If you delete it, it will be completely erased and you will never be able to access it again, so make a backup before doing so.");
						deleteDialog.setPositiveButton("Yes, sure", new DialogInterface.OnClickListener() {
							    @Override
							    public void onClick(DialogInterface _dialog, int _which) {
								        if (!_data.get((int)_position).get("logo").toString().equals("")) {
									_deleteImageFile(_data.get((int)_position).get("logo").toString());
								}
								if (isSearch) {
										if (_position >= 0 && _position < filteredList.size()) {
												HashMap<String, Object> clickedItem = filteredList.get(_position);
												int originalIndex = originalList.indexOf(clickedItem);
												
												if (originalIndex != -1) {
														originalList.remove(originalIndex);
														encryptedPrefs.edit()
														.putString("secure_data", new Gson().toJson(originalList))
														.apply();
														com.google.android.material.snackbar.Snackbar.make(linear1, "Delete successful!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
																@Override
																public void onClick(View _view) {
																		
																}
														}).show();
														_runSearch(searchText);
												}
										}
								} else {
										if (_position >= 0 && _position < originalList.size()) {
												originalList.remove(_position);
												encryptedPrefs.edit()
												.putString("secure_data", new Gson().toJson(originalList))
												.apply();
												
												filteredList.clear();
												filteredList.addAll(originalList);
												
												com.google.android.material.snackbar.Snackbar.make(linear1, "Delete successful!", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
														@Override
														public void onClick(View _view) {
																
														}
												}).show();
												listview1.setAdapter(new Listview1Adapter(originalList));
												((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
										        gridview1.setAdapter(new Gridview1Adapter(originalList));
										}
								}
								if (encryptedPrefs.getString("secure_data", "").equals("[]") || encryptedPrefs.getString("secure_data", "").equals("")) {
									textview_status.setVisibility(View.VISIBLE);
								}
								    }
						});
						deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
							    @Override
							    public void onClick(DialogInterface _dialog, int _which) {
								         
								    }
						});
						deleteDialog.setCancelable(true);
						deleteDialog.create().show();
								        });
					deleteSheet.setCancelable(true);
					deleteSheet.show();
					return true;
				}
			});
			
			return _view;
		}
	}
}