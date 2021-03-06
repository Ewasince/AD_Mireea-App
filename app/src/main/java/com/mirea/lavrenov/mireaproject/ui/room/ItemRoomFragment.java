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

import com.mirea.lavrenov.mireaproject.App;
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
    EntryCaseDao entryCaseDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_room_list, container, false);

        AppDatabase db = App.getInstance().getDatabase();
        entryCaseDao = db.employeeDao();
        List<EntryCase> listItems = entryCaseDao.getAll();

        addCurrentLaunch();
        listItems = entryCaseDao.getAll();

        // Set the adapter
//        boolean test = view instanceof RecyclerView; //true

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        PlaceholderContent.ITEMS = convert(listItems);
        recyclerView.setAdapter(new MyItemRoomRecyclerViewAdapter(PlaceholderContent.ITEMS));





        return view;
    }


    private void addCurrentLaunch() {
        EntryCase entryCase = new EntryCase();
//        entryCase.id = entryCaseDao.getLength();
        entryCase.id = 0;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        entryCase.date = formatter.format(calendar.getTime());

        entryCaseDao.insert(entryCase);
    }

    public ArrayList<PlaceholderItem> convert(List<EntryCase> arrayListCases) {
        ArrayList<PlaceholderItem> arrayListPlaceholder = new ArrayList<>();
        for(EntryCase item: arrayListCases){
            arrayListPlaceholder.add(new PlaceholderItem(String.valueOf(item.id), item.date, "test"));
        }
        return arrayListPlaceholder;
    }
}