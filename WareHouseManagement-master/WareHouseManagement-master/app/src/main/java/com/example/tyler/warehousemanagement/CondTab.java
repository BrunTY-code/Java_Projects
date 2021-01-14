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

public class CondTab extends Fragment {
    List<Item> Data;
    List<Item> CondData;

    public CondTab(List<Item> incCondData){
        Data = incCondData;
        CondData = new ArrayList<>(Data);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        // Sorting
        Collections.sort(CondData, new Comparator<Item>() {
            @Override
            public int compare(Item item2, Item item1)
            {

                return  item1.Condition.compareTo(item2.Condition);
            }
        });
        String[] Practice = new String[CondData.size()];
        for(int i = 0; i < CondData.size();i++) {
            Practice[i] = CondData.get(i).Name + " - " + CondData.get(i).ID + " - " + CondData.get(i).Location + " - " + CondData.get(i).Condition;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Practice);
        ListView list = (ListView) rootView.findViewById(R.id.ListViewTab1);
        list.setAdapter(adapter);

        return rootView;
    }
}