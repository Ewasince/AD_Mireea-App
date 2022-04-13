package com.mirea.lavrenov.mireaproject.ui.history;

import static com.mirea.lavrenov.mireaproject.ui.camera.CameraFragment.REQUEST_CODE_PERMISSION_CAMERA;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mirea.lavrenov.mireaproject.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyFileManager {
    private Context context = MainActivity.generalActivity;
    public final String TAG = this.getClass().getName();
    private SharedPreferences preferences;
    public final String listNamesFilename = "list_of_files";
    public boolean isWork = false;

    public ArrayList<String> getArrayNames(){
        String str = loadFile(listNamesFilename);
        if (str != null){
            ArrayList<String> arrayList = openJson(str);

            return arrayList;
        } else {
            int storagePermissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (storagePermissionStatus != PackageManager.PERMISSION_GRANTED)
                return null;

            String firstFileSampleName = "first_sample";
            saveFile(firstFileSampleName, "this is first file sample");

            JSONObject jsonObject = new JSONObject();
            int lentArr = jsonObject.length();
            try {
                jsonObject.put(Integer.toString(lentArr), firstFileSampleName);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
                return null;
            }
            String serializedJSON = jsonObject.toString();
            saveFile(listNamesFilename, serializedJSON);
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(firstFileSampleName);

            return arrayList;
        }
    }

    public String loadFile(String filename) {

        FileInputStream fin = null;
        try {
            fin = context.openFileInput(filename + ".txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(TAG, text);
            return text;
        } catch (IOException ex) {
            if (ex.getClass().getName().equals("class java.io.FileNotFoundException"))
                return null;
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public void saveFile(String filename, String text) {
        if (!isWork)
            return;
        FileOutputStream outputStream;
        try {
            outputStream = MainActivity.generalActivity.openFileOutput(filename + ".txt", Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> openJson(String jsonString){
        ArrayList<String> arrayList = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        int lenJson = jsonObject.length();
        for (int i = 0; i < lenJson; i++) {
            try {
                arrayList.add(jsonObject.getString(Integer.toString(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public String packJson(ArrayList<String> arrayList){
        JSONObject jsonObject = new JSONObject();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            try {
                jsonObject.put(Integer.toString(i), arrayList.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String serializedJSON = jsonObject.toString();

        return serializedJSON;
    }
}
