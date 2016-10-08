package com.example.rain.sqlitetest2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button insert;
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        dbHelper = new DBHelper(this);

        textView = (TextView) findViewById(R.id.textview);

        insert = (Button) findViewById(R.id.insertBtn);
        listView = (ListView) findViewById(R.id.myList);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText) findViewById(R.id.title)).getText().toString();
                String content = ((EditText) findViewById(R.id.content)).getText().toString();

                int tLevel = Integer.parseInt(content);
                Role role = new Role();
                role.setLevel(tLevel);
                role.setName(title);
                role.setId(1);

                dbHelper.addRole(role);

                /*List<Role> roleList = dbHelper.getAllRole();
                for(Role tRole : roleList) {
                    String tip = "\nId: " + tRole.getId() + "\nname: " +tRole.getName()
                            + "\nlevel: " + tRole.getLevel();
                    textView.append("\n" + tip);
                }
                */
            }
        });
    }



}
