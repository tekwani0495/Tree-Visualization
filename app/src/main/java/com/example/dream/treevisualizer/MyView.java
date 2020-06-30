package com.example.dream.treevisualizer;
       // import rapid.decoder.BitmapDecoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.io.Serializable;

class MyView extends View implements Serializable {

        Bitmap mBitmap;
        Paint paint = new Paint();
        int viewX, viewY;
        int data;
        Node node;

        public MyView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setupShape();
        }

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setupShape();
        }

        public MyView(Context context, int data1) {
            super(context);
            data=data1;
            setupShape();
        }
        public Node getNode(){
            return node;
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
             mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.circlegrey50).copy(Bitmap.Config.ARGB_8888, true);
            Canvas c = new Canvas(mBitmap);

//            int a=mBitmap.getHeight();
//            int b=mBitmap.getWidth();
//            BstActivity.bstActivity.textView.setText("a:"+a+" b: "+b);
//            Canvas c = new Canvas();
            paint = new Paint();

            paint.setTextSize(80);
    //        paint.setTextScaleX(1.f);
     //       paint.setAlpha(0);
      //      paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            c.drawText(String.valueOf(data), 50, 120, paint);
            paint.setColor(Color.WHITE);
//            c.drawColor(Color.RED);
//            c.drawBitmap(mBitmap,0,0,paint);


        }

        public void setButtonLoc(PathPoint newLoc) {
            this.setTranslationX(newLoc.mX);
            this.setTranslationY(newLoc.mY);
//            viewX=(int)newLoc.mX;
//            viewY=(int)newLoc.mY;
        }


        @Override
        protected void onDraw(Canvas canvas) {
//            canvas.drawColor(Color.RED);
//
            canvas.drawBitmap(mBitmap,0 ,0, paint);


        }

}



