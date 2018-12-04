package com.example.msi.notes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private ListView listview;
    DatabaseHelper myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listview = (ListView) findViewById(R.id.mobile_list);
        myDB = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0){
            Toast.makeText(MainActivity.this,"Add some notes?",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
            listview.setAdapter(listAdapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String title = parent.getItemAtPosition(position).toString();
                    String text;
                    Cursor data = myDB.getNoteID(title);

                    int noteID = -1;
                    while(data.moveToNext()){
                        noteID = data.getInt(0);
                    }

                    if(noteID > -1){
                        Cursor datab = myDB.getNoteText(noteID);
                        datab.move(1);
                        text = datab.getString(0);

                        Intent editNotes = new Intent(MainActivity.this,Main3Activity.class);
                        editNotes.putExtra("id",noteID);
                        editNotes.putExtra("title",title);
                        editNotes.putExtra("text",text);
                        startActivity(editNotes);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,"No ID",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        add = (Button) findViewById(R.id.normal_plus);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnote();
            }
        });
    }

    public void addnote(){
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

}
