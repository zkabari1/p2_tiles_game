package com.example.a2048;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int time_out = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent game = new Intent(MainActivity.this, GameScreen.class);
                startActivity(game);
                finish();
            }
        }, time_out);
    }
}
