package it.zcsf.zcsfnativeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(SignUp.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#F8F4F3"))
                .withLogo(R.drawable.zcsf)
                .withFooterText("copyright to ZCSF 2017");

        //Set text color
        config.getFooterTextView().setTextColor(Color.DKGRAY);

        //Set to view
        View view = config.create();

        //set vie to content view
        setContentView(view);
    }
}
