package com.example.medicinealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private static int splash_Time_Out = 5000;

    Animation topAnimation, bottomAnimation, midddleAnimation;
    TextView slogan,greeting;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        midddleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        greeting = findViewById(R.id.greeting);
        slogan = findViewById(R.id.slogan);
        logo   = findViewById(R.id.logo);

        logo.setAnimation(topAnimation);
        slogan.setAnimation(midddleAnimation);
        greeting.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Register.class);
                startActivity(intent);
                finish();
            }
        },splash_Time_Out);
    }

}