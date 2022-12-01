package com.example.futanalyzer.splash_screen;

import android.content.Intent;
import android.os.Bundle;

import com.example.futanalyzer.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.example.futanalyzer.R;

public class SplashActivity extends AppCompatActivity {
    ImageView imagemSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        imagemSplash = findViewById(R.id.iv_splash);
        imagemSplash.setAlpha(0f);
        imagemSplash.animate().setDuration(2000).alpha(1f).withEndAction(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(it);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
        }

    @Override
    public void finish() {
        super.finish();
    }
}
