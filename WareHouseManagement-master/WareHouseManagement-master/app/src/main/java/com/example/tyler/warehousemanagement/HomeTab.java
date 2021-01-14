package com.example.tyler.warehousemanagement;

/**
 * Created by Tyler on 6/5/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class HomeTab extends Fragment {
    List<Item> Data;
    public HomeTab(List<Item> incData){
        Data = incData;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1, container, false);

        String[] Practice = new String[Data.size()];
        for (int i = 0; i < Data.size(); i++) {
            Practice[i] = Data.get(i).Name + " - " + Data.get(i).ID + " - " + Data.get(i).Location + " - " + Data.get(i).Condition;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, Practice);
        ListView list = (ListView) rootView.findViewById(R.id.ListViewTab1);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View rootView, int position, long id) {
                //Intent appInfo = new Intent(AddEditDialog.this);
                //startActivity(appInfo);
            }
        });
        return rootView;
    }
    private void populateListView() {
    }
}