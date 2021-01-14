package com.example.tyler.warehousemanagement;

/**
 * Created by Tyler on 6/5/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocTab extends Fragment {
    List<Item> Data;
    List<Item> LocData;

    public LocTab(List<Item> incLocData){
        Data = incLocData;
        LocData = new ArrayList<>(Data);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        // Sorting
        Collections.sort(LocData, new Comparator<Item>() {
            @Override
            public int compare(Item item2, Item item1)
            {

                return  item2.Location.compareTo(item1.Location);
            }
        });
        String[] Practice = new String[LocData.size()];
        for(int i = 0; i < LocData.size();i++) {
            Practice[i] = LocData.get(i).Name + " - " + LocData.get(i).ID + " - " + LocData.get(i).Location + " - " + LocData.get(i).Condition;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Practice);
        ListView list = (ListView) rootView.findViewById(R.id.ListViewTab1);
        list.setAdapter(adapter);
        return rootView;
    }
    private void populateListView() {
    }
}