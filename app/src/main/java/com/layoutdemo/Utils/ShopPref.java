package com.layoutdemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ShopPref {
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    String shareprefName="shoppref";

    private Context context;

    public ShopPref(Context context){

        this.context=context;

        preferences =context.getSharedPreferences(shareprefName, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }



}
