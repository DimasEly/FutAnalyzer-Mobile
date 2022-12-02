package com.example.futanalyzer.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.futanalyzer.MainActivity;
import com.example.futanalyzer.R;

public class SplashActivity extends AppCompatActivity {
    ImageView imagemSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imagemSplash = findViewById(R.id.iv_splash);
        imagemSplash.setAlpha(0f);
        imagemSplash.animate().setDuration(2000).alpha(1f).withEndAction(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(it);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}