package com.example.msi.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = ed1.getText().toString();
                String newEntry2 = ed2.getText().toString();
                if(ed1.length()!=0){
                    if(ed2.length()!=0){
                        AddData(newEntry,newEntry2);
                        ed1.setText("");
                        ed2.setText("");
                    }else{
                        Toast.makeText(Main2Activity.this,"Please enter text!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Main2Activity.this,"Please enter a title!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void AddData(String newEntry,String newEntry2){
        boolean insertData = myDB.addData(newEntry,newEntry2);

        if(insertData==true){
            Toast.makeText(Main2Activity.this,"Note Saved!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(Main2Activity.this,"Error Saving!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
