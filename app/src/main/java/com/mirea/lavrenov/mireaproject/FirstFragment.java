package com.mirea.lavrenov.mireaproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mirea.lavrenov.mireaproject.databinding.FragmentFirstBinding;

import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        preferences = MainActivity.generalActivity.getPreferences(Context.MODE_PRIVATE);
        boolean toast = preferences.getBoolean(SettingsActivity.TOAST_TAG, false);
        String name = preferences.getString(SettingsActivity.NAME_TAG, "Your name");
        binding.checkBox.setChecked(toast);
        binding.editTextTextPersonName.setText(name);



        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(SettingsActivity.TOAST_TAG, binding.checkBox.isChecked());
                editor.putString(SettingsActivity.NAME_TAG, binding.editTextTextPersonName.getText().toString());
                editor.apply();

                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}