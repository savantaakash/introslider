package com;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.aboutpage.R;

public class PreferenceManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context){
        this.context=context;
        getSharedPreferences();

    }

    private void getSharedPreferences(){
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.my_preferences),Context.MODE_PRIVATE);

    }
    public void writePreference(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.my_preferences_key),"INIT OK");
        editor.commit();
    }
    public boolean checkpreferences() {
        boolean status = false;
        if (sharedPreferences.getString(context.getString(R.string.my_preferences_key), "null").equals("null")) {

        status=false;
        }
        else {
            status=true;

        }
        return status;


    }

public void clearPreferences(){
        sharedPreferences.edit().clear().commit();
}

}
