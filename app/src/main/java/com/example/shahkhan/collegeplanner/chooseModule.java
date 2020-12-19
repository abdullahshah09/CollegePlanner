package com.example.shahkhan.collegeplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.shahkhan.collegeplanner.R.id.list;
import static com.example.shahkhan.collegeplanner.R.id.list_item;
import static com.example.shahkhan.collegeplanner.R.id.parent;

public class chooseModule extends AppCompatActivity {


    ArrayList<String> theList = new ArrayList<>();
    ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_module);


        myDatabase db = new myDatabase(this);
        db.open();

        ListView listView = (ListView) findViewById(R.id.list);

        Cursor myCursor = db.getAllRows();

        if(myCursor.getCount() == 0)
        {
            Toast.makeText(chooseModule.this, "No data", Toast.LENGTH_LONG).show();
        } else {
            while(myCursor.moveToNext()) {
                theList.add(myCursor.getString(1));
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId())
        {
            case R.id.delete_id:
                theList.remove(info.position);
                listAdapter.notifyDataSetChanged();


                return true;

            default:
                return super.onContextItemSelected(item);

        }

    }
}

