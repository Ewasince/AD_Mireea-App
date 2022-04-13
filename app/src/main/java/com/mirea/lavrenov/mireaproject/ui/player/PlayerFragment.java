package com.mirea.lavrenov.mireaproject.ui.player;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.mirea.lavrenov.mireaproject.MainActivity;
import com.mirea.lavrenov.mireaproject.databinding.FragmentPlayerBinding;


public class PlayerFragment extends Fragment {
    public static final String TAG = "PlayerFragment";

    private FragmentPlayerBinding binding;
    private PlayerService playerService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        playerService = new PlayerService();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}