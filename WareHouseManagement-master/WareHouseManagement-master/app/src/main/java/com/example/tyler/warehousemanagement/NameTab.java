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

public class NameTab extends Fragment {
    List<Item> Data;
    List<Item> NameData;

    public NameTab(List<Item> incNameData){
        Data = incNameData;
        NameData = new ArrayList<>(Data);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        // Sorting
        Collections.sort(NameData, new Comparator<Item>() {
            @Override
            public int compare(Item item2, Item item1)
            {

                return  item2.Name.compareTo(item1.Name);
            }
        });
        String[] Practice = new String[NameData.size()];
        for(int i = 0; i < NameData.size();i++) {
            Practice[i] = NameData.get(i).Name + " - " + NameData.get(i).ID + " - " + NameData.get(i).Location + " - " + NameData.get(i).Condition;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Practice);
        ListView list = (ListView) rootView.findViewById(R.id.ListViewTab1);
        list.setAdapter(adapter);
        return rootView;
    }
    private void populateListView() {
    }
}