package com.layoutdemo.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.layoutdemo.R;
import com.layoutdemo.adpter.DisplaRecordAdpter;
import com.layoutdemo.database.GetData;
import com.layoutdemo.database.StudentDB;

import java.util.ArrayList;

public class DisplayAllRecordsActivtity extends AppCompatActivity {

    RecyclerView recyStudentList;
    DisplaRecordAdpter adpter ;


  ArrayList<GetData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_records_activtity);

        getAllDBRecords();
        setViews();


    }

    private void setAdpterwithList() {
        adpter=new DisplaRecordAdpter(list,getApplicationContext(), this);
        recyStudentList.setAdapter(adpter);
    }

    public void getAllDBRecords() {
        try{
            list=new ArrayList<>();
            StudentDB studentDB=new StudentDB(this);
            Cursor getAllCursor=studentDB.getAllData();

            Log.d("total record", String.valueOf(getAllCursor.getCount()));

            if(getAllCursor != null && getAllCursor.getCount()>0){
                getAllCursor.moveToFirst();

                if (getAllCursor.moveToFirst()) {
                    do  {

                        String fname = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_F_NAME));
                        String lname = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_L_NAME));
                        String email = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_EMAIL));
                        String pass = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_PASS));
                        String bod = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_BOD));
                        String gender = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_gender));
                        String mobile = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_mobile));

                        list.add(new GetData(fname,lname,email,pass,bod,gender,mobile));
                    }
                    while (getAllCursor.moveToNext());
                }

            }

            studentDB.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setViews() {
        recyStudentList=findViewById(R.id.recy_student_list);
        //recy. view set layout ( now i set the vertical layout using linearlayyout manager)
        LinearLayoutManager lymanger=new LinearLayoutManager(this);
        recyStudentList.setLayoutManager(lymanger);
        setAdpterwithList();
    }

    public void removeArrayListIndex(GetData getPosistionDataDOobject){
        list.remove(getPosistionDataDOobject);
        adpter.notifyDataSetChanged();
    }
}