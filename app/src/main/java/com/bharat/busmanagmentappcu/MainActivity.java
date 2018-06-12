package com.bharat.busmanagmentappcu;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView b1 = findViewById(R.id.b1);
        ImageView b2 = findViewById(R.id.b2);

        ObjectAnimator imageViewObjectAnimator2 = ObjectAnimator.ofFloat(b2 ,
                "rotation", 0f, 360f);
        imageViewObjectAnimator2.setRepeatCount(ObjectAnimator.INFINITE);
        imageViewObjectAnimator2.setRepeatMode(ObjectAnimator.RESTART);
        imageViewObjectAnimator2.setInterpolator(new AccelerateInterpolator());
        imageViewObjectAnimator2.start();

        ObjectAnimator imageViewObjectAnimator1 = ObjectAnimator.ofFloat(b1 ,
                "rotation", 0f, 360f);
        imageViewObjectAnimator1.setRepeatCount(ObjectAnimator.INFINITE);
        imageViewObjectAnimator1.setRepeatMode(ObjectAnimator.RESTART);
        imageViewObjectAnimator1.setInterpolator(new AccelerateInterpolator());
        imageViewObjectAnimator1.start();


        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(MainActivity.this, ChooseActivity.class));
                finish();
            }
        }, 3000);

    }
}
