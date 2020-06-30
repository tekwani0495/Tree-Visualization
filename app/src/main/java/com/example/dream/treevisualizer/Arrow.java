package com.example.dream.treevisualizer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shubh on 09-04-2018.
 * <p>
 * Created by shubh on 07-04-2018.
 */
/**
 * Created by shubh on 07-04-2018.
 */

class Arrow extends View {
    int id=R.drawable.left40;
    Bitmap mBitmap;
    Paint paint = new Paint();
    int viewX=0, viewY=0;
    int data;

    public Arrow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        setupShape();
    }

    public Arrow(Context context, AttributeSet attrs) {
        super(context, attrs);
  //      setupShape();
    }

    public Arrow(Context context, int ans) {
        super(context);
//        data=data1;
        setupShape();
        if(ans==0){
            id=R.drawable.left40;
        }else{
            id=R.drawable.right40;
        }
        setupShape();

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
    private void setupShape() {
        mBitmap = BitmapFactory.decodeResource(getResources(),id).copy(Bitmap.Config.ARGB_8888, true);
        //   Canvas c = new Canvas(mBitmap);
        //   paint = new Paint();

        //     paint.setTextSize(80);
        //        paint.setTextScaleX(1.f);
        //       paint.setAlpha(0);
        //      paint.setAntiAlias(true);
        //       c.drawText(String.valueOf(data), 40, 100, paint);
//            paint.setColor(Color.RED);

    }

    public void setButtonLoc(PathPoint newLoc) {
        this.setTranslationX(newLoc.mX);
        this.setTranslationY(newLoc.mY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, paint);
    }

}




