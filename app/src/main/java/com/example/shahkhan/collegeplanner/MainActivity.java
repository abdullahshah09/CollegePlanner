package com.example.shahkhan.collegeplanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent z = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(z);
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    Intent w = new Intent(MainActivity.this, assignmentOverview.class);
                    startActivity(w);
                    return true;


            }
            return false;
        }


    };

    //ListView myList = (ListView) findViewById(R.id.myCustomList);








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //myDatabase db = new myDatabase(this);
        //db.open();

//        ArrayList<String> stringList = new ArrayList<>();
//        ArrayList<Integer> intList = new ArrayList<>();
//
//        Cursor myCursor = db.getAllRows();
//
//        if(myCursor.getCount() == 0)
//        {
//            Toast.makeText(chooseModule.this, "No data", Toast.LENGTH_LONG).show();
//        } else {
//            while(myCursor.moveToNext()) {
//                stringList.add(myCursor.getString(1));
//                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
//                listView.setAdapter(listAdapter);
//            }
//        }

        ListView listView = (ListView) findViewById(R.id.myCustomList);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }

    String[] titles = {"Modules", "CA", "Have"};
    ArrayList<String> stringList = new ArrayList<>();
    ArrayList<Integer> intList = new ArrayList<>();

    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.row, null);

            TextView view_module_name = (TextView) view.findViewById(R.id.textView1);
            TextView view_module_ca = (TextView) view.findViewById(R.id.textView3);
            TextView view_module_have = (TextView) view.findViewById(R.id.textView5);

            view_module_name.setText(titles[0]);
            view_module_ca.setText(titles[1]);
            view_module_have.setText(titles[2]);

            return view;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            Intent nxt = new Intent(MainActivity.this, addModule.class);
            startActivity(nxt);

        }
        return super.onOptionsItemSelected(item);
    }



}

