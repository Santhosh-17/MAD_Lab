package com.madlab.exno4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.madlab.exno4.MainActivity;
import com.madlab.exno4.Model.Contacts;
import com.madlab.exno4.R;
import com.madlab.exno4.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {



    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // to create a table
        // CREATE TABLE CONTACTS (ID INT,NAME TEXT,PHONENO TEXT);

        // ; HERE

        String CREATE_TABLE = "CREATE TABLE "+Util.TABLE_NAME+" ( "+Util.KEY_NAME+" TEXT PRIMARY KEY, "+Util.KEY_PHONENO +" TEXT ); " ;
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //droping table

        db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);

        onCreate(db);

    }


    //CRUD - CREATE, READ ,UPDATE, DELETE

    //ADD CONTACTS
    public String addContact(Contacts contacts){

        String msg = "";

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, contacts.getName());
        contentValues.put(Util.KEY_PHONENO,contacts.getPhoneNumber());


        try{
            db.insertOrThrow(Util.TABLE_NAME,null,contentValues);
            Log.d("db", "addContact: "+"itemAdded");
        }
        catch (android.database.sqlite.SQLiteConstraintException  e) {
            msg = e.getMessage().toString();
            Log.d("db", "msg: "+msg);
        }

//        //INSERT A ROW
//        db.insert(Util.TABLE_NAME,null,contentValues);
//        Log.d("db", "addContact: "+"itemAdded");

        db.close();

        return msg;

    }

    //TO GET CONTACTS FROM DATABASE


    public Contacts getContact(String name){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_NAME,Util.KEY_PHONENO}, // LIST OF COLUMNS
                Util.KEY_NAME +"=?",new String[]{name}, // SAME AS CONDITIONS
                null,null,null);

        if(cursor != null){
            cursor.moveToFirst();  //IF CURSOR IS IN MIDDLE  OF TABLE
        }

        Contacts contacts = new Contacts();
        contacts.setName(cursor.getString(0));
        contacts.setPhoneNumber(cursor.getString(1));

        return contacts;
    }

    public int update(Contacts contacts,String temp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, contacts.getName());
        contentValues.put(Util.KEY_PHONENO,contacts.getPhoneNumber());

        //UPDATE THE ROW
        //UPDATE(TABLE_NAME,VALUES, WHERE KEY_ID = ID)
        return db.update(Util.TABLE_NAME,contentValues, Util.KEY_NAME+"=?",
                new String[]{temp});

//        String strSQL = "UPDATE myTable SET Column1 = someValue WHERE columnId = "+ someValue;
//
//        db.execSQL(strSQL);

    }


    public void deleteContact(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME,Util.KEY_NAME+"=?",new String[]{name});
        db.close();

    }

    public int getCount(){
        String cQuery = "SELECT * FROM "+Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(cQuery,null);
        return cursor.getCount();
    }

    //TO GET ALL CONTACTS
    public List<Contacts> getAllContacta(){

        List<Contacts> contactsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //SELECT ALL CONTACTS
        String selectAll = "SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        //LOOP THROUGH OUR DATA
        if (cursor.moveToFirst()){

            do {

                Contacts contacts = new Contacts();

                contacts.setName(cursor.getString(0));
                contacts.setPhoneNumber(cursor.getString(1));

                //ADD CONTACT OBJECT TO CONTACT_LIST
                contactsList.add(contacts);



            }while (cursor.moveToNext());

        }

        return contactsList;
    }

}