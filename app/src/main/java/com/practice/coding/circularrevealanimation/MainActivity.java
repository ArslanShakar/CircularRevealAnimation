package com.practice.coding.circularrevealanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgView;
    private ConstraintLayout constraintLayout, constraintLayoutNormalViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.img_view_circular_reveal);
        constraintLayout = findViewById(R.id.layout_circular_reveal);
        constraintLayoutNormalViews = findViewById(R.id.layout_normal_views);
    }

    public void circularRevealAnimation(View view) {
        makeCircularRevealAnimation(imgView);
    }

    private void makeCircularRevealAnimation(View view) {
        // get the center for the clipping circle
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        float radius = (float) Math.hypot(cx, cy);

        if (imgView.getVisibility() == View.INVISIBLE || imgView.getVisibility() == View.GONE) {

            constraintLayoutNormalViews.setAlpha(0f); //it makes all other views transparent..means does visible only reveal image visible
            imgView.setVisibility(View.VISIBLE);

            ViewAnimationUtils.createCircularReveal(imgView, cx, cy, 0, radius).start();
        } else {
            Animator anim = ViewAnimationUtils.createCircularReveal(imgView, cx, cy, radius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    imgView.setVisibility(View.INVISIBLE);

                    /*when image invisible make all all views visible*/
                    constraintLayoutNormalViews.setAlpha(1f);
                }
            });

            anim.start();
        }
    }
}
