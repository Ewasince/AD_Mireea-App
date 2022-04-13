package com.mirea.lavrenov.mireaproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mirea.lavrenov.mireaproject.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySettingsBinding binding;
    public static final String TOAST_TAG = "toast";
    public static final String NAME_TAG = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void onClickSignOut(View view) {
//        StartActivity.sourceContext = this;

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        MainActivity.startActivity.signOut(view);
    }
}