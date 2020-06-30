package com.example.dream.treevisualizer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shubh on 09-04-2018.
 */

public class Cordinates extends View {
    int viewX=0, viewY=0;

    public Cordinates(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public Cordinates(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public int getViewX(){
        return viewX;
    }
    public int getViewY(){
        return  viewY;
    }
    public void setXY(int x,int y){
        viewX=x;
        viewY=y;
    }

}
