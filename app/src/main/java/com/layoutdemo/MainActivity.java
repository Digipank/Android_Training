package com.layoutdemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.layoutdemo.Utils.Constant;
import com.layoutdemo.Utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtValue1,edtvalue2;
    Button btnAdd;

    String stredt1="",stredt2="";
    Integer strabc = null ;

    StudentDO studDo,stud1,stud2;

    private Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils=new Utils(getApplicationContext());

        setViewId();

        String name=utils.getStringPref(Constant.email,this);

        studDo= new StudentDO("abc","aaaaaaaaaaaaa","1");
        stud1= new StudentDO("xyz","aaaaaaaaaaaaa","2");
        stud2= new StudentDO("pqr","aaaaaaaaaaaaa","3");


        Log.i("Do_class","value of Pojo class");
        Log.i("Do_class","==================================");
        Log.i("Do_class","Student 1:"+studDo.name + "   ,   "+ studDo.address+ " , "+ studDo.id);
        stud1.setName("Monika");
        Log.i("Do_class","Student 1:"+stud1.getName()+ "   ,   "+ stud1.getAddress()+ " , "+ stud1.getId());
        Log.i("Do_class","Student 1:"+stud2.name + "   ,   "+ stud2.address+ " , "+ stud2.id);
        Log.i("Do_class","==================================");

    }

    private void setViewId() {

        edtValue1=findViewById(R.id.edt_value1);
        edtvalue2=findViewById(R.id.edt_value2);

        btnAdd=findViewById(R.id.btn_add);


        edtValue1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                stredt1=editable.toString();


            }
        });

        edtvalue2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                stredt2=editable.toString();


            }
        });

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_add:{

                if(stredt1.length()>0){
                    if(stredt2.length()>0){

                        int value1=Integer.parseInt(stredt1);
                        int value2= Integer.parseInt(stredt2);

                        int add=value1+value2;

                        Toast.makeText(this,"add :->"+add,Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this,"please enter value 2",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(this,"please enter value 1",Toast.LENGTH_LONG).show();
                }


            }
        }

    }
}