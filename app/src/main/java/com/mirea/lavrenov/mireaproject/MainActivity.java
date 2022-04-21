package com.mirea.lavrenov.mireaproject;

import static com.mirea.lavrenov.mireaproject.ui.camera.CameraFragment.REQUEST_CODE_PERMISSION_CAMERA;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.lavrenov.mireaproject.databinding.ActivityMainBinding;
import com.mirea.lavrenov.mireaproject.databinding.FragmentHistoryBinding;
import com.mirea.lavrenov.mireaproject.databinding.FragmentIpBinding;
import com.mirea.lavrenov.mireaproject.ui.calculator.CalculatorFragment;
import com.mirea.lavrenov.mireaproject.ui.history.MyFileManager;
import com.mirea.lavrenov.mireaproject.ui.player.PlayerFragment;
import com.mirea.lavrenov.mireaproject.ui.player.PlayerService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    //    private FragmentIpBinding bindingIp;
    private FragmentHistoryBinding bindingHistory;
    private Display display;
    private SharedPreferences preferences;
    private TextView textViewIp;
    private boolean permissionGeo = false;
    private final int REQUEST_CODE_GEO = 102;
    private boolean isPermissionGeo = false;
    public MyFileManager fileManager;
    public static StartActivity startActivity;
    public static CalculatorFragment calcFragment;
    public static MainActivity generalActivity;
//    public static boo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        bindingIp = FragmentIpBinding.inflate(getLayoutInflater());//??

        preferences = getPreferences(MODE_PRIVATE);
        if (preferences.getBoolean(SettingsActivity.TOAST_TAG, false)) {
            String name = preferences.getString(SettingsActivity.NAME_TAG, "user");
            Toast.makeText(this, "Welcome back, " + name + "!", Toast.LENGTH_SHORT).show();
        }


        generalActivity = this;
        fileManager = new MyFileManager();

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_calculator, R.id.nav_browser, R.id.nav_camera, R.id.nav_history, R.id.nav_microphone, R.id.nav_player, R.id.nav_sensors)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        int storagePermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            fileManager.isWork = true;
        } else {
            // Выполняется запрос к пользователь на получение необходимых разрешений
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_CAMERA);
        }


        int geoPermissionStatusFine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int geoPermissionStatusCoarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (geoPermissionStatusFine == PackageManager.PERMISSION_GRANTED
                && geoPermissionStatusCoarse == PackageManager.PERMISSION_GRANTED) {
            permissionGeo = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_GEO);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void makeToast(String text, Context context) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClick(View view) {
        calcFragment.onClick(view);
    }

    public void onClickPlay(View view) {
        startService(
                new Intent(MainActivity.this, PlayerService.class));
    }

    public void onClickPause(View view) {
        Log.i(PlayerFragment.TAG, "pause");
    }

    public void onClickStop(View view) {
        Log.i(PlayerFragment.TAG, "stop");
        stopService(
                new Intent(MainActivity.this, PlayerService.class));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
// производится проверка полученного результата от пользователя на запрос разрешения Camera
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_CAMERA:
                // permission granted
                fileManager.isWork = grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
            case REQUEST_CODE_GEO:
                isPermissionGeo = grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED;
                break;
        }
    }

    public void onClickSettings(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

//    TextView textView;
//    Button button;
//    EditText editText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        textView = findViewById(R.id.textView);
//        button = findViewById(R.id.button_ip);
//        editText = findViewById(R.id.editTextTextPersonName);
//    }


}