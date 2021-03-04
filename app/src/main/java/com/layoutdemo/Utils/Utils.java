package com.layoutdemo.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private  Context context;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String shareprefName="studentpref";
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;

    String passwordPattern="(/^(?=.*\\d)(?=.*[A-Z])([@$%&#])[0-9a-zA-Z]{4,}$/)\n";

    @SuppressLint("CommitPrefEdits")
    public Utils(Context context){

        this.context=context;

        preferences =context.getSharedPreferences(shareprefName, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    /**
     * set string Preference
     */
    public void setStringPref(String key, String value) {

        if(value != null){
            editor.putString(key, value);
            editor.commit();
        }

    }

    /**
     * set string Preference
     */
    public void setIntPref(String key, int value) {
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * set string Preference
     */
    public void setLongPref(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }


    public  String getStringPref(String key, Context context){
        return preferences.getString(key, "abc");
    }

    public  Long getLongPref(String key, Context context){
        return preferences.getLong(key, 0);
    }

    public  int getIntPref(String key, Context context){
        return preferences.getInt(key, 0);
    }


    public boolean isvalidemail(String  email){
//        boolean isEmailValid= false;
//
//
//                if(email.length()>0){
//                        if(email.matches(emailPattern)){
//                            isEmailValid=true;
//
//                        }
//                        else {
//                            isEmailValid=false;
//                        }
//                }


                return   Pattern.compile(emailPattern).matcher(email).matches();
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


    public void ToastMessage(String message,Context context){
        if(message != null){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
    }
}
