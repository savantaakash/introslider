package com.example.aboutpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.MpagerAdapter;
import com.PreferenceManager;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mpager;
    private  int[] layouts = {R.layout.first_slide,
            R.layout.second_slide,R.layout.third_slide,
            R.layout.fourth_slide};

    private MpagerAdapter mpagerAdapter;

    private LinearLayout Dots_Layout;
    private ImageView[] dots;

    private Button BnNext,Bnskip;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      if (new PreferenceManager(this).checkpreferences()){
          loadHome();


      }


        //before setting layout we have to check condition

        if (Build.VERSION.SDK_INT>=19){
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    else{
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        setContentView(R.layout.activity_welcome);

        mpager = findViewById(R.id.viewpager);
        mpagerAdapter=new MpagerAdapter(layouts,this);

        mpager.setAdapter(mpagerAdapter);
        //intialize Linear Layout

        Dots_Layout=findViewById(R.id.dotslayout);
        BnNext = findViewById(R.id.btnnext);
        Bnskip = findViewById(R.id.btnskip);
        BnNext.setOnClickListener(this);
        Bnskip.setOnClickListener(this);



        createdots(0);

        mpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createdots(position);
                if (position==layouts.length-1)
                {
                    BnNext.setText("Start");
                    Bnskip.setVisibility(View.INVISIBLE);


                }
                else{
                    BnNext.setText("Next");
                    Bnskip.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
   private void createdots(int current_position){
        if (Dots_Layout!=null)
            Dots_Layout.removeAllViews();
       dots = new ImageView[layouts.length];

       for (int i=0;i<layouts.length;i++){
           dots[i]=new ImageView(this);
           if (i==current_position){
            dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
           }
           else {
               dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
           }

LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
params.setMargins(4,0,4,0);
Dots_Layout.addView(dots[i],params);

       }




   }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.btnnext:
        loadNextSlide();
        break;
    case R.id.btnskip:
        loadHome();
        new PreferenceManager(this).writePreference();
        break;

}


    }
    private void loadHome(){

startActivity(new Intent(this,MainActivity.class));
finish();

    }
    private void loadNextSlide(){
     int next_slide = mpager.getCurrentItem()+1;

     if (next_slide<layouts.length){
    mpager.setCurrentItem(next_slide);
}
else {
    loadHome();
    new PreferenceManager(this).writePreference();


}

    }

}

