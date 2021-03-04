package com.layoutdemo.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.layoutdemo.R;
import com.layoutdemo.Utils.Constant;
import com.layoutdemo.Utils.Utils;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    TextInputEditText edtEmail,edtPassword;
    TextInputLayout txt_email,txt_password;
    Button btnLogin,btnGetvalue,btn_add;

    String str_email, str_password;
    boolean isPasswordValid,isemailvalid;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        utils= new Utils(getApplicationContext());
        setviewIds();
    }

    private void setviewIds() {
        //edit text ids
        edtEmail=findViewById(R.id.edt_email);
        edtPassword=findViewById(R.id.edt_password);
        // buttons id
        btnLogin=findViewById(R.id.btn_login);
        btnGetvalue=findViewById(R.id.btn_getvalue);
        btn_add=findViewById(R.id.btn_add);
        //text box ids
        txt_email=findViewById(R.id.txt_email);
        txt_password=findViewById(R.id.txt_password);

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                str_email=editable.toString();

                              if(str_email.length()>0){
                    isemailvalid=utils.isvalidemail(str_email);

                    if(isemailvalid){
                        txt_email.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green)));

                    }
                    else {
                        txt_email.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    }
                }


            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                str_password=editable.toString();


                if(str_password.length()>0){
                     isPasswordValid=utils.isValidPassword(str_password);

                    if(isPasswordValid){
                        txt_password.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.green)));

                    }
                    else {
                        txt_password.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    }
                }

            }
        });


        btnLogin.setOnClickListener(this);
        btnGetvalue.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login: {

                if(isemailvalid){
                    if(isPasswordValid){

                        utils.setStringPref(Constant.email,str_email);
                        utils.setStringPref(Constant.password,str_password);
                        utils.setIntPref(Constant.id,123);


                        utils.ToastMessage(getResources().getString(R.string.login_success),this);
                    }
                }

            }
            case  R.id.btn_getvalue:{
                //get pref value and display in log
                String email,password;
                int id;

                email=utils.getStringPref(Constant.email,this);
                password=utils.getStringPref(Constant.password,this);
                id=utils.getIntPref(Constant.id,this);


                Log.i("loginstore","=================================");
                Log.i("loginstore","email -> "+email);
                Log.i("loginstore","password - >"+password);
                Log.i("loginstore","id ->"+id);
                Log.i("loginstore","=================================");

            }
            case R.id.btn_add:{
//                StudentDB studDB= new StudentDB(getApplicationContext());
//
//
//                Long insertReocrd=studDB.add_data("sarita","agravat","abc@gamil.com","yes","20-feb-1996","femail","000000000");
//                Log.d("Count",""+insertReocrd);
//                utils.ToastMessage("Record sucessfuly Add",getApplicationContext());
//                studDB.close();

                Intent callintent = new Intent(this,FromFillActivity.class);
                callintent.putExtra("btnTitle","Add Record");
                startActivity(callintent);

            }
        }
    }



}