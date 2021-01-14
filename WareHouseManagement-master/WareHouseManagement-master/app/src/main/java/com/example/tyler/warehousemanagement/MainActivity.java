
        package com.example.tyler.warehousemanagement;

        import android.app.DialogFragment;
        import android.content.Context;
        import android.content.Intent;
        import android.support.design.widget.TabLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;

        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.List;

        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import static com.example.tyler.warehousemanagement.R.layout.tab1;

        /**************************************
 *
 *  MAIN ACTIVITY
 *
 **************************************/
public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    List<Item> Inventory = new ArrayList<>();
    String [] data = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read in data
        try {
            data[0] = read(MainActivity.this, "Data.txt");
            //testing only
            String normal = data[0];
            Inventory = JSONConv(normal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the tabs
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), Inventory);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //List view fun
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, tab1, data);
        ListView lv = (ListView) findViewById(R.id.ListViewTab1);
        //lv.setAdapter(adapter);

        //floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = AddEditDialog.newInstance(view.getId());
                newFragment.setShowsDialog(true);
                newFragment.show(getFragmentManager(), "dialog");
            }
        });


    }

    /**************************************
     *
     *  onCreateOptionsMenu
     *
     **************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /**************************************
     *
     *  onOptionsItemSelected
     *
     **************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**************************************
     *
     *  ReadFile
     *
     **************************************/
    public String read(Context context, String File) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(File)));
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine);
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }


    /**************************************
     *
     *  SectionsPagerAdapter
     *
     **************************************/

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Item> Data;
        public SectionsPagerAdapter(FragmentManager fm, List<Item> incData) {
            super(fm); Data = incData;
        }

        @Override
        public Fragment getItem(int position){
            switch(position){
                case 0:
                    HomeTab homeTab = new HomeTab(Data);
                    return homeTab;

                case 1:
                    NameTab nameTab = new NameTab(Data);
                    return nameTab;
                case 2:
                    IDTab IdTab = new IDTab(Data);
                    return IdTab;
                case 3:
                    LocTab locTab = new LocTab(Data);
                    return locTab;
                case 4:
                    CondTab tab5 = new CondTab(Data);
                    return tab5;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Name";
                case 2:
                    return "Id";
                case 3:
                    return "Loc";
                case 4:
                    return "Cond";
            }
            return null;
        }
    }

    /**************************************
     *
     *  JSON converter
     *
     **************************************/
    public List<Item> JSONConv(String item){
        List<Item> Inventory = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(item);
            JSONArray inventory = object.getJSONArray("Warehouse");
            for (int i = 0; i < inventory.length(); i++) {
                Item temp = new Item();
                JSONObject jsonInventory = inventory.getJSONObject(i);
                temp.Name = jsonInventory.getString("Name");
                temp.ID = jsonInventory.getString("ID");
                temp.Condition = jsonInventory.getString("Condition");
                temp.Location = jsonInventory.getString("Location");
                Inventory.add(i,temp);
            }
            return Inventory;
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Failure to load JSON", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e)
        {
            Toast.makeText(MainActivity.this, "Failure to load crap", Toast.LENGTH_SHORT).show();
        }

        return Inventory;
    }
}