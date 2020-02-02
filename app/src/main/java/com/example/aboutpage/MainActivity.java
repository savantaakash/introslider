package com.example.aboutpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void loadSlides(View view) {
      new  PreferenceManager(this).clearPreferences();
       startActivity(new Intent(this,WelcomeActivity.class));
       finish();
    }
}
