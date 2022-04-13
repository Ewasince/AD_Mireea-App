package com.mirea.lavrenov.mireaproject.ui.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mirea.lavrenov.mireaproject.MainActivity;
import com.mirea.lavrenov.mireaproject.R;
import com.mirea.lavrenov.mireaproject.databinding.FragmentHistoryBinding;
import com.mirea.lavrenov.mireaproject.databinding.FragmentMakeHistoryBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MakeHistoryFragment extends Fragment {
    FragmentMakeHistoryBinding binding;
    MyFileManager fileManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMakeHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fileManager = MainActivity.generalActivity.fileManager;

        binding.makeHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextMakeHistoryName.getText().toString();
                String text = binding.editTextMakeHistoryText.getText().toString();
                fileManager.saveFile(name, text);

                String listNames = fileManager.loadFile(fileManager.listNamesFilename);
                ArrayList<String> listNamesArray = fileManager.openJson(listNames);
                listNamesArray.add(name);
                String serializedJson = fileManager.packJson(listNamesArray);
                fileManager.saveFile(fileManager.listNamesFilename, serializedJson);

                NavHostFragment.findNavController(MakeHistoryFragment.this)
                        .navigate(R.id.action_MakeHistoryFragment_to_HistoryFragment);
            }
        });
        return root;
    }

}