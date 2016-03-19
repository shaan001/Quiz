package com.hybrid.tech.miniquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admins on 3/18/2016.
 */
public class TheQuizHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Trivia";
    private static final int DB_VERSION = 1;

    TheQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QUIZ (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "QUESTION TEXT, "
                + "ANSWER TEXT, "
                + "QUIZ_TOPIC TEXT, "
                + "REMARKS TEXT);");

        db.execSQL("CREATE TABLE CATEGORY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "THEGAME TEXT);");

        insertGame(db, "RANDOM");
        insertGame(db,"MUSIC");
        insertGame(db,"GK");
        insertGame(db,"MATHS");

        insertDrink(db, "What is the meaning of Life???a)idk b)LOL", "b", "Random", "damn son");
        insertDrink(db, "In which state Ganga Flows???a)Kathmandu b)LIQUID", "b", "hi mom", "WOaahhhhhhhh");
        insertDrink(db, "What is 4th state of Matter???a)Grey Matter b)PLASMA", "b", "yay", "Nerd");
        insertDrink(db, "5SOS or THE VAMPS??? a)Screw YOU,its LINKIN PARKS", "a", "LP", "In the end it doesnt even maaattterrrr..");

        //  db.execSQL("DROP TABLE IF EXISTS QUIZ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QUIZ");
        onCreate(db);
    }

    private static void insertDrink(SQLiteDatabase db, String question,
                                    String answer, String topic, String remarks) {
        ContentValues values = new ContentValues();
        values.put("QUESTION", question);
        values.put("ANSWER", answer);
        values.put("QUIZ_TOPIC", topic);
        values.put("REMARKS", remarks);
        db.insert("QUIZ", null, values);
    }

    private static void insertGame(SQLiteDatabase db,String n)
    {
        ContentValues values = new ContentValues();
        values.put("THEGAME",n);
        db.insert("CATEGORY",null,values);
    }
}
