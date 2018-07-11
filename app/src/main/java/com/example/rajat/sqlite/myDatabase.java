package com.example.rajat.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class myDatabase {

    myDbHelper myhelper;

    public myDatabase(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String pass){
        SQLiteDatabase database = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,name);
        contentValues.put(myDbHelper.password,pass);
        long id = database.insert(myDbHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public String getData(){
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String columns[] =  {myDbHelper.UID,myDbHelper.NAME,myDbHelper.password};
        Cursor cursor = database.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String cname = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String cpass = cursor.getString(cursor.getColumnIndex(myDbHelper.password));
            stringBuffer.append(cid+" "+cname+" "+cpass+"\n");
        }
        return stringBuffer.toString();

    }

    public int delete(String uname){
        SQLiteDatabase database = myhelper.getWritableDatabase();
        String[] whereArgs = {uname};
        return database.delete(myDbHelper.TABLE_NAME , myDbHelper.NAME+" =?",whereArgs);

    }

    public int updatename(String oldname , String newname){
        SQLiteDatabase database = myhelper.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newname);
        String[] whereArgs  = {oldname};
        return database.update(myDbHelper.TABLE_NAME,contentValues,myDbHelper.NAME+" =?",whereArgs);
    }

    static class myDbHelper extends SQLiteOpenHelper{


        private static final String DATABASE_NAME = "mydatabase";
        private static final String  TABLE_NAME= "table1";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "name";
        private static final String password = "password";
        private Context context;

        public myDbHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {


            try {
                db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME + " VARCHAR(224) ," + password +" VARCHAR(224));");

            }catch (Exception e){
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}


