package com.softbard.easysms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText nameUser;
    Button saveNameBT, senderPage, UpdateBtn, filebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(getApplicationContext());

        nameUser = findViewById(R.id.userName);
        saveNameBT = findViewById(R.id.saveName);
        UpdateBtn = findViewById(R.id.udpadbtn);
        senderPage = findViewById(R.id.goSenderpage);
        filebtn = findViewById(R.id.filebtn);
        filebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlldataView.class);
                startActivity(intent);
            }
        });
        senderPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SMSBox_1.class);
                startActivity(intent);
            }
        });
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean setupdate = dbHelper.upDateData("1",nameUser.getText().toString().trim());
                if (setupdate == true){
                    Toast.makeText(MainActivity.this, "Name is Update", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Not Update", Toast.LENGTH_LONG).show();
                }
            }
        });

        saveNameBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean dataisinsart = dbHelper.insartData(nameUser.getText().toString());

               if (dataisinsart = true) {
                   Toast.makeText(MainActivity.this, "Name Save", Toast.LENGTH_LONG).show();
                    saveNameBT.setVisibility(View.INVISIBLE);
                    saveNameBT.setEnabled(false);
               }else {
                   Toast.makeText(MainActivity.this, "Name not save", Toast.LENGTH_LONG).show();
               }

               /*
               String name =  nameUser.getText().toString().trim();
               if (nameUser.getText().toString().equals("")){
                   dbHelper.inputData(new ModelClass(name));
               }

               */
            }
        });


    }
}