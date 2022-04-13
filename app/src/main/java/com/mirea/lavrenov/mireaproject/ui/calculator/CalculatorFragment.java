package com.mirea.lavrenov.mireaproject.ui.calculator;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.mirea.lavrenov.mireaproject.MainActivity;
import com.mirea.lavrenov.mireaproject.R;
import com.mirea.lavrenov.mireaproject.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {

    private FragmentCalculatorBinding  binding;
    Display display;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalculatorViewModel calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        MainActivity.calcFragment = this;

        display = new Display(binding.textDisplay);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @SuppressLint("NonConstantResourceId")
//    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_0:
                display.add("0");
                break;
            case R.id.button_1:
                display.add("1");
                break;
            case R.id.button_2:
                display.add("2");
                break;
            case R.id.button_3:
                display.add("3");
                break;
            case R.id.button_4:
                display.add("4");
                break;
            case R.id.button_5:
                display.add("5");
                break;
            case R.id.button_6:
                display.add("6");
                break;
            case R.id.button_7:
                display.add("7");
                break;
            case R.id.button_8:
                display.add("8");
                break;
            case R.id.button_9:
                display.add("9");
                break;
            case R.id.button_clear:
                display.clear();
                break;
            case R.id.button_add:
                display.calculate("+");
                break;
            case R.id.button_subtract:
                display.calculate("-");
                break;
            case R.id.button_multiply:
                display.calculate("*");
                break;
            case R.id.button_divide:
                display.calculate("/");
                break;
            case R.id.button_equals:
                display.equals_();
                break;

        }
    }

    static class Display {
        public TextView display;

        public Display(TextView display) {
            this.display = display;
        }

        void add(String digit) {
            String newText = display.getText() + digit;
            display.setText(newText);
        }

        void clear() {
            display.setText("");
            lastOperation = null;
            lastNumber = null;
        }

        void equals_() {
            if (lastNumber != null) {
                int presentNumber = getValueDisplay();
                int result = calculate_(lastNumber, presentNumber, lastOperation);
                clear();
                display.setText(String.valueOf(result));
            }

        }

        private Integer lastNumber = null;
        private String lastOperation = null;

        void calculate(String operation) {
            if (lastNumber == null) {
                int lastNumber = getValueDisplay();
                clear();
                this.lastNumber = lastNumber;
                lastOperation = operation;
            } else {
                int presentNumber = getValueDisplay();
                int result = calculate_(lastNumber, presentNumber, lastOperation);
                clear();
                lastNumber = result;
                lastOperation = operation;
            }
        }

        private int calculate_(int number1, int number2, String operation) {
            try {
                switch (operation) {
                    case "+":
                        return number1 + number2;
                    case "-":
                        return number1 - number2;
                    case "*":
                        return number1 * number2;
                    case "/":
                        return number1 / number2;
                    default:
                        return 0;
                }
            } catch (Exception e) {
                return 0;
            }
        }

        int getValueDisplay() {
            try {
                Object charset = display.getText();
                String text = String.valueOf(charset);

//                int return_ = Integer.getInteger(text);
                int return_ = Integer.parseInt(text);
                return return_;
            } catch (Exception e) {
                return 1;
            }
        }
    }


}