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
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TheQuiz extends Activity {

    private SQLiteDatabase db;
    private Cursor quizCursor;
   private static  int i=0;
    static final String EXTRA_TOPIC = "topic no";
    static String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_quiz);
        setTitle("Random");
        try {

            SQLiteOpenHelper help = new TheQuizHelper(this);
            db = help.getReadableDatabase();
            // Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
          //  Toast.makeText(this, Integer.toString(i), Toast.LENGTH_LONG).show();
            //  quizCursor=db.rawQuery("SELECT QUESTION FROM QUIZ WHERE _id = ?", new String[] {Integer.toString(i)});
            quizCursor = db.rawQuery("SELECT * FROM QUIZ", null);
        }
            catch(SQLiteException e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }

    }


    public void onNext(View view)
    {
        Button button=(Button)view ;
        TextView question = (TextView) findViewById(R.id.question);
        button.setText("Next");


              // quizCursor = db.query("QUIZ", new String[]{"_id", "QUESTION"}, "_id=?", new String[]{Integer.toString(0)}, null, null, null);
              if(quizCursor.getCount()==0)
              {
                  Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
              }
              if (  quizCursor.moveToPosition(i)) {

                  //Get the drink details from the cursor
                  question.setText(quizCursor.getString(1));
              }
              i++;
        if( quizCursor.isAfterLast())
        {
          //  Toast.makeText(this,"AFTER LAST",Toast.LENGTH_LONG).show();
            question.setText("BRAVO!!!!!!! THANK YOU FOR WASTING YOUR TIME IN THIS POINTLESS APP");
          //  Toast.makeText(this,button.getText().toString(),Toast.LENGTH_LONG).show();
            if(str=="hello")
            {
                clearall();
                Intent intent=new Intent(TheQuiz.this,TopActivity.class);
                startActivity(intent);
            }
            str="hello";
            button.setText("Main Menu");

        }
              //else {
              //  Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
              //}
          }


    public void onSubmit(View view)
    {
        EditText tv=(EditText)findViewById(R.id.ans);
        Button submit=(Button)findViewById(R.id.submit);

        if(tv.getText().toString().equals(quizCursor.getString(2)))
        {
            Toast.makeText(this,quizCursor.getString(4),Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(this,"WRONG ANSWER MORON",Toast.LENGTH_LONG).show();
        }

        if( quizCursor.isAfterLast()) {
            //  Toast.makeText(this,"AFTER LAST",Toast.LENGTH_LONG).show();
            submit.setClickable(false);
        }
    }


    public void clearall()
    {
        quizCursor.moveToPosition(0);
        i=0;
        str="";
    }
    }

