package com.mirea.lavrenov.mireaproject.ui.room;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirea.lavrenov.mireaproject.R;
import com.mirea.lavrenov.mireaproject.ui.room.placeholder.PlaceholderItem;
import com.mirea.lavrenov.mireaproject.ui.room.placeholder.PlaceholderContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemRoomFragment extends Fragment {
    // TODO: Customize parameters
    private int mColumnCount = 1;
    EnterCaseDao enterCaseDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_room_list, container, false);

        addCurrentLaunch();
//        firsInitialize();

        // Set the adapter
        boolean test = view instanceof RecyclerView;

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        PlaceholderContent.ITEMS = convert(enterCaseDao.getAll());
        recyclerView.setAdapter(new MyItemRoomRecyclerViewAdapter(PlaceholderContent.ITEMS));





        return view;
    }

    private void addCurrentLaunch() {
        App app = new App();
        AppDataBase db = app.getDatabase();
        enterCaseDao = db.employeeDao();
        EnterCase enterCase = new EnterCase();
        enterCase.id = enterCaseDao.getLength();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        enterCase.date = formatter.format(calendar.getTime());

        enterCaseDao.insert(enterCase);
    }

    public ArrayList<PlaceholderItem> convert(List<EnterCase> arrayListCases) {
        ArrayList<PlaceholderItem> arrayListPlaceholder = new ArrayList<>();
        for(EnterCase item: arrayListCases){
            arrayListPlaceholder.add(new PlaceholderItem(String.valueOf(item.id), item.date, "test"));
        }
        return arrayListPlaceholder;
    }
}