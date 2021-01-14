package com.example.test2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.lang.reflect.Field;

import static android.content.Context.MODE_PRIVATE;
import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;

/******************************************
 *  Character Tab
 ******************************************/

public class Tab1C extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.character_tab, container, false);
        final EditText chname = (EditText) v.findViewById(R.id.characterName);
        final EditText plname = (EditText) v.findViewById(R.id.playerName);




        final Spinner rspinner = (Spinner) v.findViewById(R.id.raceSpinner);
        final Spinner classspin = (Spinner) v.findViewById(R.id.classSpinner);
        final Spinner factionspin = (Spinner) v.findViewById(R.id.faction_Spinner);
        final Spinner piece1 = (Spinner) v.findViewById(R.id.pieceSpinner);
        final Spinner piece2 = (Spinner) v.findViewById(R.id.pieceSpinner2);
        final Spinner piece3 = (Spinner) v.findViewById(R.id.pieceSpinner3);
        final Spinner sspinner = (Spinner) v.findViewById(R.id.special_Spinner);





// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.race_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        rspinner.setAdapter(adapter);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> cadapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.class_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        classspin.setAdapter(cadapter);




        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> fadapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.faction_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        fadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        factionspin.setAdapter(fadapter);




        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> p1adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.piece_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        p1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        piece1.setAdapter(p1adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> p2adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.piece_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        p2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        piece2.setAdapter(p2adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> p3adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.piece_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        p3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        piece3.setAdapter(p3adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sadapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.specializaton_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sspinner.setAdapter(sadapter);


        Button button = v.findViewById(R.id.button);

        /******************************************
         *  Class Saving
         ******************************************/
        classspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String classvalue = classspin.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myClass", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Class", classvalue);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences myclassPref = getContext().getSharedPreferences("myClass", MODE_PRIVATE);
        String myclassstring = myclassPref.getString("Class","nothing"); // the value you want the

        ArrayAdapter<String> myclassAdap = (ArrayAdapter<String>) classspin.getAdapter();
        int classspinnerPosition = myclassAdap.getPosition(myclassstring);

        // set the default according to value
        classspin.setSelection(classspinnerPosition);


        /******************************************
         *  Race Save
         ******************************************/
        rspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String racevalue = rspinner.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myRace", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Race", racevalue);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences myracePrefs = getContext().getSharedPreferences("myRace", MODE_PRIVATE);
        String myraceString = myracePrefs.getString("Race","nothing"); // the value you want the

        ArrayAdapter<String> myraceAdap = (ArrayAdapter<String>) rspinner.getAdapter();
        int racespinnerPosition = myraceAdap.getPosition(myraceString);

        // set the default according to value
        rspinner.setSelection(racespinnerPosition);

        /******************************************
         *  Faction Save
         ******************************************/
        factionspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fvalue = factionspin.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myF", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Faction", fvalue);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences myfactionPrefs = getContext().getSharedPreferences("myF", MODE_PRIVATE);
        String myfactionString = myfactionPrefs.getString("Faction","nothing"); // the value you want the

        ArrayAdapter<String> myfactionAdap = (ArrayAdapter<String>) factionspin.getAdapter();
        int factionspinnerPosition = myfactionAdap.getPosition(myfactionString);

        // set the default according to value
        factionspin.setSelection(factionspinnerPosition);


        /******************************************
         *  Specialization Save
         ******************************************/
        sspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String svalue = sspinner.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myS", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Specialization", svalue);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences mysPrefs = getContext().getSharedPreferences("myS", MODE_PRIVATE);
        String mysString = mysPrefs.getString("Specialization","nothing"); // the value you want the

        ArrayAdapter<String> mySAdap = (ArrayAdapter<String>) sspinner.getAdapter();
        int sspinnerPosition = mySAdap.getPosition(mysString);

        // set the default according to value
        sspinner.setSelection(sspinnerPosition);

        /******************************************
         *  Heavy Armor Save
         ******************************************/
        piece1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String p1value = piece1.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myP1", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Light_Armor", p1value);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences myP1Prefs = getContext().getSharedPreferences("myP1", MODE_PRIVATE);
        String myP1String = myP1Prefs.getString("Light_Armor","nothing"); // the value you want the

        ArrayAdapter<String> myp1Adap = (ArrayAdapter<String>) piece1.getAdapter();
        int p1spinnerPosition = myp1Adap.getPosition(myP1String);

        // set the default according to value
        piece1.setSelection(p1spinnerPosition);

        /******************************************
         *  Medium Armor Save
         ******************************************/
        piece2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String p2value = piece2.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myP2", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Medium_Armor", p2value);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences myP2Prefs = getContext().getSharedPreferences("myP2", MODE_PRIVATE);
        String myP2String = myP2Prefs.getString("Medium_Armor","nothing"); // the value you want the

        ArrayAdapter<String> myp2Adap = (ArrayAdapter<String>) piece2.getAdapter();
        int p2spinnerPosition = myp2Adap.getPosition(myP2String);

        // set the default according to value
        piece2.setSelection(p2spinnerPosition);

        /******************************************
         *  Light Armor Save
         ******************************************/
        piece3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String p3value = piece3.getSelectedItem().toString();
                SharedPreferences myPrefs = getContext().getSharedPreferences("myP3", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = myPrefs.edit();
                prefsEditor.putString("Light_Armor", p3value);
                prefsEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences myP3Prefs = getContext().getSharedPreferences("myP3", MODE_PRIVATE);
        String myP3String = myP3Prefs.getString("Light_Armor","nothing"); // the value you want the

        ArrayAdapter<String> myp3Adap = (ArrayAdapter<String>) piece3.getAdapter();
        int p3spinnerPosition = myp3Adap.getPosition(myP3String);

        // set the default according to value
        piece3.setSelection(p3spinnerPosition);


        /*******************************************
         * Save Character Name
         *******************************************/
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        chname.setText(prefs.getString("cautoSave",""));


        chname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.edit().putString("cautoSave", editable.toString()).commit();

            }
        });

        /*******************************************
         * Save Player Name
         *******************************************/
        final SharedPreferences plprefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        plname.setText(plprefs.getString("pautoSave",""));

        plname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



            }

            @Override
            public void afterTextChanged(Editable editable) {
                plprefs.edit().putString("pautoSave", editable.toString()).commit();

            }
        });




        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Submitted for approval!" , Toast.LENGTH_SHORT ).show();
                Button button = (Button) v;
                button.setVisibility(View.INVISIBLE);
            }
        });






        return v;
    }


    //public void hideKeyboard(View view){

      //  InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
       // inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

    //}

    //public void showSoftKeyboard(View view) {
    //    if (view.requestFocus()) {
    //        InputMethodManager imm = (InputMethodManager)
     //               view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    //        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
     //   }
    //}
}
