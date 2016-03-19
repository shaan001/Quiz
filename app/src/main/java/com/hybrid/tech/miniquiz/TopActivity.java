package com.hybrid.tech.miniquiz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TopActivity extends Activity {
    private SQLiteDatabase db;
    private Cursor quizCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ListView listView = (ListView) findViewById(R.id.category);


        try
        {
            SQLiteOpenHelper help=new TheQuizHelper(this);
            db=help.getReadableDatabase();
           // Toast.makeText(this,"HEllo",Toast.LENGTH_LONG).show();
            quizCursor=db.query("CATEGORY", new String[]{"_id", "THEGAME"}, null, null, null, null, null);
            CursorAdapter quizAdapter=new SimpleCursorAdapter(TopActivity.this,android.R.layout.simple_list_item_1,
                    quizCursor,new String[]{"THEGAME"},new int[]{android.R.id.text1},0);
           // Toast.makeText(this,"HEllo"+Boolean.toString(quizCursor.isBeforeFirst()),Toast.LENGTH_LONG).show();
listView.setAdapter(quizAdapter);
        }
        catch(SQLiteException e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(TopActivity.this, TheQuiz.class);
                intent.putExtra(TheQuiz.EXTRA_TOPIC, (int) id);
               if((int)id==1) {
                   startActivity(intent);
               }
                else
               {
                   Toast.makeText(TopActivity.this,"We are NOT working on it",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    }

