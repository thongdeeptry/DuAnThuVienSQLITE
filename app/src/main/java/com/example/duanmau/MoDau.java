package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MoDau extends AppCompatActivity {
    Animation animation;
    ImageView anh;
    TextView tvtv;
    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_dau);
        anh=findViewById(R.id.gd);
        tvtv = findViewById(R.id.tvtv);
        progressBar=findViewById(R.id.progressBar);
        animation= AnimationUtils.loadAnimation(this,R.anim.animm);
        anh.startAnimation(animation);
        tvtv.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100){
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            if(progressStatus==100){
                                Intent i = new Intent(MoDau.this,LoginActivity.class);
                                startActivity(i);
                            }
                        }
                    });

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}