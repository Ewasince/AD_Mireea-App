package com.mirea.lavrenov.mireaproject.ui.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mirea.lavrenov.mireaproject.MainActivity;
import com.mirea.lavrenov.mireaproject.R;
import com.mirea.lavrenov.mireaproject.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;
    MyFileManager fileManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HistoryFragment.this)
                        .navigate(R.id.action_HistoryFragmet_to_MakeHistoryFragment);
            }
        });
        fileManager = MainActivity.generalActivity.fileManager;

        initializeHistory();

        return root;
    }

    private void initializeHistory() {
        ArrayList<Story> stories = new ArrayList<>();

        ArrayList<String> namesOfFiles = fileManager.getArrayNames();
        for (String name: namesOfFiles){
            String text = fileManager.loadFile(name);
            stories.add(new Story(name, text));
        }

        RecyclerView recyclerView = binding.recycleView;
        HistoryAdapter adapter = new HistoryAdapter(getContext(), stories);
        recyclerView.setAdapter(adapter);
    }

}