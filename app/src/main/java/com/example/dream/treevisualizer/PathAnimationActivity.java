/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.dream.treevisualizer;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.FrameLayout;

public class PathAnimationActivity {
    PathEvaluator mEvaluator = new PathEvaluator();
//    MyView myView1;

    public void layout(View myView, FrameLayout frameLayout){


//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT );
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                175, 175);
        myView.setLayoutParams(params);
        frameLayout.addView(myView);

    }

    public void startAnimation(View myView, int moveX, int moveY, int lineX, int lineY){

        AnimatorPath path = new AnimatorPath();

        path.moveTo(moveX, moveY);
        path.lineTo(lineX, lineY);
//        myView.setXY(lineX,lineY);


        final ObjectAnimator anim = ObjectAnimator.ofObject(myView, "buttonLoc",
                new PathEvaluator(), path.getPoints().toArray());

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                BstActivity.bstActivity.insertButton.setEnabled(true);
            }
        });

        anim.setDuration(1000);
//        anim.setStartDelay(1000);
        anim.start();



    }

    public void zoom(View myView){

//        myView.setPivotX(0);
//        myView.setPivotY(0);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);

        ObjectAnimator scaleAnimation =
                ObjectAnimator.ofPropertyValuesHolder(myView, pvhX,pvhY);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setDuration(750);
          scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);

          scaleAnimation.addListener(new AnimatorListenerAdapter() {
              @Override
              public void onAnimationStart(Animator animation) {
                  super.onAnimationStart(animation);
//                  BinarySearchTree.zoomLock=false;
//                  BstActivity.bstActivity.textView.append("after false ");

              }


              @Override
              public void onAnimationEnd(Animator animation) {
                  super.onAnimationEnd(animation);
//                  BinarySearchTree.zoomLock=true;
//                  BstActivity.bstActivity.textView.append("\nafter true ");
//
              }
          });
        scaleAnimation.start();

    }
    public void zoom(View myView,int no,int speed){

//        myView.setPivotX(0);
//        myView.setPivotY(0);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);

        ObjectAnimator scaleAnimation =
                ObjectAnimator.ofPropertyValuesHolder(myView, pvhX,pvhY);
        scaleAnimation.setRepeatCount(no);
        scaleAnimation.setDuration(speed);
        scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);


        scaleAnimation.start();

    }

}