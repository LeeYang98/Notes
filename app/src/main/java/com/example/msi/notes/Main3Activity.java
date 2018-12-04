package com.example.msi.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Button btnSave2, btnDelete;
    private EditText ed3, ed4;

    DatabaseHelper myDB;

    private String stitle,stext;
    private int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);

        btnSave2 = (Button) findViewById(R.id.btnSave2);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        myDB = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        sid = receivedIntent.getIntExtra("id",-1);
        stitle = receivedIntent.getStringExtra("title");
        stext = receivedIntent.getStringExtra("text");
        ed3.setText(stitle);
        ed4.setText(stext);

        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = ed3.getText().toString();
                String item2 = ed4.getText().toString();
                if(!item.equals("")){
                    if(!item2.equals("")){
                        myDB.updateData(item,item2,sid);
                        Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Main3Activity.this,"Please enter text!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Main3Activity.this,"Please enter title!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteData(sid);
                ed3.setText("");
                ed4.setText("");
                Toast.makeText(Main3Activity.this,"Note Deleted!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main3Activity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
