package com.mirea.lavrenov.mireaproject.ui.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mirea.lavrenov.mireaproject.MainActivity;
import com.mirea.lavrenov.mireaproject.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    public FragmentBrowserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BrowserViewModel browserViewModel =
                new ViewModelProvider(this).get(BrowserViewModel.class);

        binding = FragmentBrowserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        MainActivity.bindingWeb = binding;
        WebView webView = binding.webView;
        webView.loadUrl("https://www.google.ru/");


//        final TextView textView = binding.textBrowser;
//        browserViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}