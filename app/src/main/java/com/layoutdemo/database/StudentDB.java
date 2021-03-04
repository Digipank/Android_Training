package com.layoutdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDB  extends SQLiteOpenHelper {

    public  static  String DB_NAME="register1.db";
    public static  int DB_VERSION=1;

    public static String TB_NAME="Detail";
    SQLiteDatabase  db;

    //filed name
    public static final String KEY_Id="id";
    public static final String KEY_F_NAME="f_name";
    public static final String KEY_L_NAME="l_name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASS="pass";
    public static final String KEY_BOD="bod";
    public static final String KEY_gender="sex";
    public static final String KEY_mobile="mobile";

    String CREATE_DATA_TABLE = "CREATE TABLE " + TB_NAME + "("
            + KEY_Id + " INTEGER PRIMARY KEY," + KEY_F_NAME + " TEXT,"
            + KEY_L_NAME + " TEXT," + KEY_EMAIL
            + " TEXT NOT NULL UNIQUE,"
            + KEY_PASS + " TEXT,"
            + KEY_BOD + " TEXT,"
            + KEY_gender + " TEXT,"
            + KEY_mobile + " TEXT"
            + ")";

   // String CREATE_DATA_TABLE1 = "CREATE TABLE " + TB_NAME + "("+ KEY_Id + " INTEGER PRIMARY KEY," + KEY_F_NAME + " TEXT," + KEY_L_NAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PASS + " TEXT," + KEY_BOD + " TEXT," + KEY_gender + " TEXT," + KEY_mobile + " TEXT" + ")";

    public  StudentDB(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
       // db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        this.db = sqLiteDatabase;

        db.execSQL(CREATE_DATA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS"+ DB_NAME);
        onCreate(db);
    }


    public  long add_data(String fname,String lname,String email,String pass,String bod,String gender,String mobile)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_F_NAME,fname);
        values.put(KEY_L_NAME,lname);
        values.put(KEY_EMAIL,email);
        values.put(KEY_PASS,pass);
        values.put(KEY_BOD,bod);
        values.put(KEY_gender,gender);
        values.put(KEY_mobile,mobile);

        return db.insert(TB_NAME,null,values);
    }

    public Cursor getAllData () {
        db = this.getWritableDatabase();

        String query = "Select * from " + TB_NAME;

        return db.rawQuery(query, null);
    }



    public Cursor Exist(String name)
    {
        String fritst_nm="";
        db=this.getWritableDatabase();

        Cursor c=db.query(TB_NAME,null,KEY_F_NAME + "=?",new String[]{String.valueOf(name)},null,null,null);
        return  c;
    }

    public  int DelteReocrd(String id){
        int a=0;

        try{
            db=this.getWritableDatabase();
            String where = KEY_EMAIL + " = ?";
            String[] whereArgs = { String.valueOf(id) };
          a=   db.delete(TB_NAME, where, whereArgs);

        }catch (Exception e){
            e.printStackTrace();
        }

        return  a;
    }
}
