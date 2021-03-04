package com.layoutdemo.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.layoutdemo.R;
import com.layoutdemo.Utils.Utils;
import com.layoutdemo.database.GetData;
import com.layoutdemo.database.StudentDB;

import java.util.ArrayList;
import java.util.Calendar;

public class FromFillActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_addrecord,btn_getAll;
    private EditText txt_f, txt_l, txt_g, txt_m, txt_bod, txt_p, txt_e;

    ArrayList<GetData> list;

    String gen="";

    private String strf, strl, strg, strm, strb, strp, stre;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String strSelectedDate ="";

    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_fill);
        setviews();
        getIntendtData();
    }

    private void getIntendtData(){
        Intent intent = getIntent();
        if( intent != null){
            String getButn=intent.getStringExtra("btnTitle");
            if(getButn.equals("Add Record")){
                clearText();
            }else if(getButn.equals("Edit Reocrd")){

                String strf=intent.getStringExtra ("namef");
                String strl=intent.getStringExtra("namel");
                String strg=intent.getStringExtra("gender");
                String strp=intent.getStringExtra("pass");
                String strm=intent.getStringExtra("mobile");
                String strb=intent.getStringExtra("bod");
                String stre=intent.getStringExtra("email");
                setText(strf,strl,strg,strp,strm,strb,stre);
            }
        }
    }

    private void setText(String strf, String strl, String strg, String strp, String strm, String strb,String stre) {
        txt_g.setText(strg);
        txt_bod.setText(strb);
        txt_e.setText(stre);
        txt_p.setText(strp);
        txt_l.setText(strl);
        txt_f.setText(strf);
        txt_m.setText(strm);
        btn_addrecord.setText("Edit record");

    }

    private void clearText(){
        txt_g.setText("");
        txt_bod.setText("");
        txt_e.setText("");
        txt_p.setText("");
        txt_l.setText("");
        txt_f.setText("");
        txt_m.setText("");
        btn_addrecord.setText("Add record");
    }

    private void setviews() {
        btn_addrecord = (Button) findViewById(R.id.btn_record);
        btn_getAll=(Button) findViewById(R.id.btn_getAllRecord);

        txt_f = (EditText) findViewById(R.id.txt_fname);
        txt_l = (EditText) findViewById(R.id.txt_lname);
        txt_e = (EditText) findViewById(R.id.txt_email_file_form);
        txt_p = (EditText) findViewById(R.id.txt_pass);
        txt_bod = (EditText) findViewById(R.id.txt_bod);
        txt_g = (EditText) findViewById(R.id.txt_gender);
        txt_m = (EditText) findViewById(R.id.txt_mobile);

        utils = new Utils(this);
        list = new ArrayList<>();

        btn_addrecord.setOnClickListener(this);
        btn_getAll.setOnClickListener(this);
        txt_bod.setOnClickListener(this);
        txt_g.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_record: {

                strf = txt_f.getText().toString();
                strl = txt_l.getText().toString();
                strp = txt_p.getText().toString();
                strb = txt_bod.getText().toString();
                stre = txt_e.getText().toString();
                strg = txt_g.getText().toString();
                strm = txt_m.getText().toString();


                if (strf != null && !strf.isEmpty()) {
                    if (strl != null && !strl.isEmpty()) {
                        if (strp != null && !strp.isEmpty()) {

                            if (strb != null && !strb.isEmpty()) {

                                if (stre != null && !stre.isEmpty()) {

                                    if (strg != null && !strg.isEmpty()) {

                                        if (strm != null && !strm.isEmpty()) {
                                                    addDBRecord(strf,strl,stre,strm,strg,strb,strp);

                                        } else {
                                            utils.ToastMessage(getResources().getString(R.string.fill_form_filed_required), this);

                                        }
                                    } else {
                                        utils.ToastMessage(getResources().getString(R.string.fill_form_filed_required), this);

                                    }
                                } else {
                                    utils.ToastMessage(getResources().getString(R.string.fill_form_filed_required), this);

                                }

                            } else {
                                utils.ToastMessage(getResources().getString(R.string.fill_form_filed_required), this);

                            }
                        } else {
                            utils.ToastMessage(getResources().getString(R.string.fill_form_filed_required), this);

                        }

                    } else {
                        utils.ToastMessage(getResources().getString(R.string.fill_form_filed_required), this);
                    }


                }
            }
            break;
            case R.id.btn_getAllRecord:{

             //   GetDBReocrd();

                Intent callrecords = new Intent(this,DisplayAllRecordsActivtity.class);
                startActivity(callrecords);
                //withMultiChoiceItems();
            }
            break;
            case R.id.txt_bod:{
                DialogDatePicket();
            }
            break;
            case  R.id.txt_gender :{
                withMultiChoiceItems();
            }
            break;
            default:{
                Log.i("switch","Default value get ");
            }
        }

    }

    private void GetDBReocrd() {
        try{
            list.clear();
            StudentDB studDB= new StudentDB(getApplicationContext());

            Cursor getAllCursor=studDB.getAllData();

            Log.d("total record", String.valueOf(getAllCursor.getCount()));

            if(getAllCursor != null && getAllCursor.getCount()>0){
                getAllCursor.moveToFirst();

                if (getAllCursor.moveToFirst()) {
                    do  {


                        String fname = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_F_NAME));
                        String lname = getAllCursor.getString(getAllCursor.getColumnIndex(StudentDB.KEY_F_NAME));
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

            studDB.close();

            Log.i("Array record  ","list size :"+list.size());

            Log.i("Array record  ","===================================");

            for(int i=0;i<list.size();i++){

                Log.i("Array record  ","First Name ->"+list.get(i).getFname() + "Last Name ->"+list.get(i).getLname());
            }
            Log.i("Array record  ","===================================");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addDBRecord(String strf, String strl, String stre, String strm, String strg, String strb, String strp) {

        try{
            StudentDB studDB= new StudentDB(getApplicationContext());
            Long insertReocrd=studDB.add_data(strf,strl,stre,strp,strb,strg,strm);
            Log.d("Count",""+insertReocrd);
            utils.ToastMessage("Record sucessfuly Add",getApplicationContext());

            //android own alert dialog use
            DialogSucessfull("Record sucessfuly Add");

            studDB.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void DialogSucessfull(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                utils.ToastMessage("dialog cancel press",getApplicationContext());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setTitle(message);
        dialog.show();
    }

    private void DialogDatePicket() {

        // Get Current Date
        final Calendar c = Calendar.getInstance(); //cureent date time
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //dialog initn and show and retrun selected value
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                       strSelectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                       txt_bod.setText(strSelectedDate);
                        Log.i("date","selected value :"+strSelectedDate.toString());

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void DialogGenderSelection(){
    }

    public void withMultiChoiceItems() {
//        final String[] items = {"Male", "Female", "Other"};
//        final ArrayList<Integer> selectedList = new ArrayList<>();
//        final AlertDialog.Builder[] builder = {new AlertDialog.Builder(this)};
//        final int[] strPrevSelection = new int[1];
//
//        builder[0].setTitle("Select your gender");
//        builder[0].setMultiChoiceItems(items, null,
//                new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        if (isChecked) {
//                            if(selectedList.size()>1){
//                                selectedList.clear();
//
//                            }else {
//
//                            }
//
//                            selectedList.add(which);
//
//
//                        } else if (selectedList.contains(which)) {
//                            selectedList.remove(which);
//                        }
//                    }
//                });
//
//        builder[0].setPositiveButton("DONE", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                ArrayList<String> selectedStrings = new ArrayList<>();
//
//                for (int j = 0; j < selectedList.size(); j++) {
//                    selectedStrings.add(items[selectedList.get(j)]);
//                }
//
//                utils.ToastMessage( "Items selected are: " + Arrays.toString(selectedStrings.toArray()),getApplicationContext());
//
//            }
//        });
//
//        builder[0].show();

        final CharSequence[] gender = {"Male","Female"};
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Select Gender");
        alert.setSingleChoiceItems(gender,-1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(gender[which]=="Male")
                {
                    gen="1";
                    txt_g.setText(gender[0].toString());
                }
                else if (gender[which]=="Female")
                {
                    gen="2";
                    txt_g.setText(gender[1].toString());

                }
            }
        });
        alert.show();

    }

}