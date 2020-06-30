package com.example.dream.treevisualizer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//import com.google.android.gms.ads.AdRequest.Builder;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.example.shubh.redblacktree1.R;


public class RedBlackTreeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    int ScreenWidth;
    boolean animate = false;
    Paint black;
    Paint blue;
    Paint printColor;
    public DrawTree dt;
    EditText editText1;
    FontMetrics fm;
    boolean found = false;
    public String print = "";
    Paint random;
    Paint line;
    RedBlackNode rbn;
    RedBlackTree rbt;
    Paint red;
    RelativeLayout relativeLayout1;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout2;
    Bundle saveIS;
    HorizontalScrollView scroll;
    Button insertButton ;
    Button deleteButton ,inorder,preorder,postorder;
    ImageView searchButton;
    ImageView undoButton;
    ImageView reloadButton;
    RedBlackNode current;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,back;
    int sleeptime = 1000;
    int slow = 10;
    double speed = 0.0d;
    SeekBar ss;
    Activity thisAct;
    boolean undo = false;
    Stack<RedBlackTree> undoStack;
    Paint white;
    int width;
    int height;
    Paint yellow;
    int  sizeYellow=20;
    int sizewhite=17;
    int circleSize=80;
    float textY=20;
    int textSize = 75;
    boolean running;
    boolean inRun;
    LinkedList<RedBlackNode> linkedList;
    TextView check;

    class SeekBarClass implements OnSeekBarChangeListener {
        SeekBarClass() {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            try {
                RedBlackTreeActivity.this.dt.resume();
            } catch (Exception e) {
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            try {
                RedBlackTreeActivity.this.dt.pause();
            } catch (Exception e) {
            }
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            RedBlackTreeActivity.this.speed = (((double) progress) / ((double) RedBlackTreeActivity.this.ss.getMax())) * 100.0d;
        }
    }

    class InsertClass implements OnClickListener {
        InsertClass() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();

//                if(running){
//                    linearLayout.setEnabled(false);
//                }else{
//                    linearLayout.setEnabled(true);
//                }


            try {

                RedBlackTreeActivity.this.undoStack.push(RedBlackTreeActivity.this.rbt.clone());
                RedBlackTreeActivity.this.print = "";
                RedBlackTreeActivity.this.rbn = null;
                int k = Integer.parseInt(RedBlackTreeActivity.this.editText1.getText().toString());
                editText1.setText("");
                RedBlackTreeActivity.this.rbt.insert(k);
                RedBlackTreeActivity.this.animate = false;
                RedBlackTreeActivity redBlackTreeActivity = RedBlackTreeActivity.this;
                redBlackTreeActivity.print += "Inserted " + k;
                check.setText("Inserted " + k);
                RedBlackTreeActivity.this.setWidth();
                RedBlackTreeActivity.this.setDimension();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            RedBlackTreeActivity.this.dt.resume();
        }
    }

    class DeleteClass implements OnClickListener {
        DeleteClass() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();
            try {
                RedBlackTreeActivity.this.undoStack.push(RedBlackTreeActivity.this.rbt.clone());
                RedBlackTreeActivity.this.print = "";
                RedBlackTreeActivity.this.rbn = null;
                int k = Integer.parseInt(RedBlackTreeActivity.this.editText1.getText().toString());
                editText1.setText("");
                RedBlackTreeActivity.this.rbt.delete(k);
                if (RedBlackTreeActivity.this.print == "") {
                    RedBlackTreeActivity.this.print = "Key " + k + " deleted from tree.";
                    check.setText("Key " + k + " deleted from tree.");

                } else {
                    RedBlackTreeActivity.this.undoStack.pop();
                }
                RedBlackTreeActivity.this.rbn = null;
                RedBlackTreeActivity.this.setWidth();
                RedBlackTreeActivity.this.setDimension();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            RedBlackTreeActivity.this.dt.resume();
            RedBlackTreeActivity.this.dt.resume();
        }
    }

    class FindClass implements OnClickListener {
        FindClass() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();
            try {
                RedBlackTreeActivity.this.print = "";
                RedBlackTreeActivity.this.rbn = null;
                RedBlackTreeActivity.this.found = false;
                int k = Integer.parseInt(RedBlackTreeActivity.this.editText1.getText().toString());
                editText1.setText("");
                RedBlackTreeActivity.this.found = RedBlackTreeActivity.this.rbt.search(k);
                if (RedBlackTreeActivity.this.print == "" && RedBlackTreeActivity.this.found) {
                    RedBlackTreeActivity.this.print = "Key " + k + " found.";
                    check.setText("Key " + k + " found.");
                } else {
                    RedBlackTreeActivity.this.print = "Key " + k + " not found";

                }
                RedBlackTreeActivity.this.setWidth();
                RedBlackTreeActivity.this.setDimension();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            RedBlackTreeActivity.this.dt.resume();
        }
    }

    class UndoClass implements OnClickListener {
        UndoClass() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();
            try {
                RedBlackTreeActivity.this.print = "";
                RedBlackTreeActivity.this.rbn = null;
                RedBlackTreeActivity.this.undo();
                RedBlackTreeActivity.this.undo = true;
                RedBlackTreeActivity.this.rbt.startPositionX = (double) (((int) (((double) ((float) RedBlackTreeActivity.this.width)) * 0.9d)) / 2);
                if (RedBlackTreeActivity.this.rbt.root != null) {
                    RedBlackTreeActivity.this.rbt.root.resizeTree();
                    RedBlackTreeActivity.this.setWidth();
                    RedBlackTreeActivity.this.setDimension();
                }
            } catch (Exception e) {
            }
            RedBlackTreeActivity.this.dt.resume();
        }
    }

    class RefreshClass implements OnClickListener {
        RefreshClass() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();
            try {
                RedBlackTreeActivity.this.Refresh();
            } catch (Exception e) {
            }
            RedBlackTreeActivity.this.dt.resume();
        }
    }
    class InorderClass implements OnClickListener {
        InorderClass() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();

            try {
                RedBlackTreeActivity.this.rbn = null;

                RedBlackTreeActivity.this.rbt.inorder(rbt.root);
                check.setText("Inorder: ");

                for(RedBlackNode node:linkedList){
                    check.append(node.data+" , ");
                }

                RedBlackTreeActivity.this.rbt.order();
                dt.resume();
                RedBlackTreeActivity.this.animate = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//            RedBlackTreeActivity.this.dt.resume();
        }
    }
    class PreOrder implements OnClickListener {
        PreOrder() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();

            try {
                RedBlackTreeActivity.this.rbn = null;

                RedBlackTreeActivity.this.rbt.preorder(rbt.root);
                check.setText("Preorder: ");

                for(RedBlackNode node:linkedList){
                    check.append(node.data+" , ");
                }

                RedBlackTreeActivity.this.rbt.order();
                dt.resume();
                RedBlackTreeActivity.this.animate = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//            RedBlackTreeActivity.this.dt.resume();
        }
    }
    class PostOrder implements OnClickListener {
        PostOrder() {
        }

        public void onClick(View arg0) {
            RedBlackTreeActivity.this.dt.pause();

            try {
                RedBlackTreeActivity.this.rbn = null;

                RedBlackTreeActivity.this.rbt.postorder(rbt.root);
                check.setText("Postorder: ");

                for(RedBlackNode node:linkedList){
                    check.append(node.data+" , ");
                }

                RedBlackTreeActivity.this.rbt.order();
                dt.resume();
                RedBlackTreeActivity.this.animate = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//            RedBlackTreeActivity.this.dt.resume();
        }
    }

    class AboutClass implements OnClickListener {
        AboutClass() {
        }

        public void onClick(View arg0) {
//            Intent optionActivity = new Intent("com.structures.algorithms.redblack.ABOUT");
//            optionActivity.addFlags(1073741824);
//            RedBlackTreeActivity.this.startActivity(optionActivity);
        }
    }

    class C15308 implements OnFocusChangeListener {
        C15308() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                RedBlackTreeActivity.this.hideKeyboard(v);
            }
        }
    }

    class C15319 implements OnTouchListener {
        C15319() {
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View v, MotionEvent event) {
            RedBlackTreeActivity.this.hideKeyboard(v);
            return false;
        }
    }

    public class DrawTree extends SurfaceView implements Runnable {
        boolean isRunning = false;
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        String f2781s;

        class C15321 implements Runnable {
            C15321() {
            }

            public void run() {
                RedBlackTreeActivity.this.scroll.fullScroll(66);
            }
        }

        class C15332 implements Runnable {
            C15332() {
            }

            public void run() {
                RedBlackTreeActivity.this.scroll.fullScroll(66);
            }
        }

        public DrawTree(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            setZOrderOnTop(false);
            this.ourHolder = getHolder();
            this.ourHolder.setFormat(-2);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(RedBlackTreeActivity.this.width, RedBlackTreeActivity.this.height);
        }

        public void pause() {
            this.isRunning = false;
            running=isRunning;
            try {
                this.ourThread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.ourThread = null;
        }

        public void resume() {
            this.isRunning = true;
            running=isRunning;
            this.ourThread = new Thread(this);
            this.ourThread.start();
        }


        public void run() {

            int i = 0;
            while (this.isRunning) {
                if (this.ourHolder.getSurface().isValid()) {
                    Canvas canvas = this.ourHolder.lockCanvas();
                    try {
                        canvas.drawColor(Color.argb(255,190,189,189));
                    } catch (Exception e) {
                    }
                    try {

                        Queue<RedBlackNode> que;
                        RedBlackNode n;
                        double tmp;
                        double nx;
                        double ny;
                        double m;
                        if (RedBlackTreeActivity.this.rbt.root != null) {
                            que = new LinkedList();
                            que.add(RedBlackTreeActivity.this.rbt.root);
                            while (!que.isEmpty()) {
                                n = (RedBlackNode) que.peek();
                                if (!(n.left == null || n.left.ileaf)) {
                                    que.add(n.left);
                                }
                                if (!(n.right == null || n.right.ileaf)) {
                                    que.add(n.right);
                                }
                                if (n.parent != null) {
                                    RedBlackTreeActivity.this.random.setARGB(MotionEventCompat.ACTION_MASK, 200, 120, 40);
                                    RedBlackTreeActivity.this.random.setStyle(Style.STROKE);
                                    if (i > RedBlackTreeActivity.this.slow) {
                                        canvas.drawLine((float) ((int) n.fx), (float) ((int) n.fy), (float) ((int) n.parent.fx), (float) ((int) n.parent.fy), RedBlackTreeActivity.this.line);
                                    } else {
                                        double pnx;
                                        double pny;
                                        tmp = (n.fx - n.oldX) / ((double) RedBlackTreeActivity.this.slow);
                                        if (n.fx == n.oldX && n.fy == n.oldY) {
                                            nx = n.oldX;
                                            ny = n.oldY;
                                        } else if (tmp == 0.0d || n.fx - n.oldX ==0) {
                                            nx = n.oldX;
                                            ny = n.oldY + (((double) i) * ((n.fy - n.oldY) / ((double) RedBlackTreeActivity.this.slow)));
                                        } else {
                                            m = (n.fy - n.oldY) / (n.fx - n.oldX);
                                            nx = n.oldX + (((double) i) * tmp);
                                            ny = ((n.oldX + ((double) ((int) (((double) i) * tmp)))) * m) + (n.oldY - (n.oldX * m));
                                        }
                                        double ptmp = (n.parent.fx - n.parent.oldX) / ((double) RedBlackTreeActivity.this.slow);
                                        if (n.parent.fx == n.parent.oldX && n.parent.fy == n.parent.oldY) {
                                            pnx = n.parent.oldX;
                                            pny = n.parent.oldY;
                                        } else if (ptmp == 0.0d || n.parent.fx - n.parent.oldX ==0) {
                                            pnx = n.parent.oldX;
                                            pny = n.parent.oldY + (((double) i) * ((n.parent.fy - n.parent.oldY) / ((double) RedBlackTreeActivity.this.slow)));
                                        } else {
                                            m = (n.parent.fy - n.parent.oldY) / (n.parent.fx - n.parent.oldX);
                                            pnx = n.parent.oldX + (((double) i) * ptmp);
                                            pny = ((n.parent.oldX + ((double) ((int) (((double) i) * ptmp)))) * m) + (n.parent.oldY - (n.parent.oldX * m));
                                        }
                                        canvas.drawLine((float) ((int) nx), (float) ((int) ny), (float) ((int) pnx), (float) ((int) pny), RedBlackTreeActivity.this.line);


                                    }
                                }
                                que.remove();
                            }
                        }
                        if (RedBlackTreeActivity.this.rbt.root != null) {
                            String s;
                            int ts;
                            int r;
                            if (RedBlackTreeActivity.this.animate) {
                                que = new LinkedList();
                                que.add(RedBlackTreeActivity.this.rbt.root);
                                while (!que.isEmpty()) {
                                    n = (RedBlackNode) que.peek();
                                    if (!(n.left == null || n.left.ileaf)) {
                                        que.add(n.left);
                                    }
                                    if (!(n.right == null || n.right.ileaf)) {
                                        que.add(n.right);
                                    }
                                    s = "" + n.data;
                                    ts = (int) RedBlackTreeActivity.this.white.measureText(s);
                                    r = circleSize;
//                                    if (35 < ts) {
//                                        r = (ts + 10) / 2;
//                                    }
                                    if (n == RedBlackTreeActivity.this.rbn && ((double) RedBlackTreeActivity.this.width) - RedBlackTreeActivity.this.rbn.oldX > ((double) (RedBlackTreeActivity.this.ScreenWidth / 2))) {
                                        RedBlackTreeActivity.this.scroll.smoothScrollTo(((int) RedBlackTreeActivity.this.rbn.oldX) - (RedBlackTreeActivity.this.ScreenWidth / 2), 0);
                                    } else if (n == RedBlackTreeActivity.this.rbn) {
                                        RedBlackTreeActivity.this.scroll.postDelayed(new C15321(), 100);
                                    }
                                    if (i > RedBlackTreeActivity.this.slow) {
                                        n.oldX = n.fx;
                                        n.oldY = n.fy;
                                        if (n.highlight) {
                                            n.highlight = false;
                                            canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) (r + sizeYellow), RedBlackTreeActivity.this.yellow);
                                        } else {
                                            canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) (r + sizewhite), RedBlackTreeActivity.this.white);
                                        }
                                        if (n.black == 1) {
                                            canvas.drawCircle((float) ((int) n.fx), (float) ((int) n.fy), (float) r, RedBlackTreeActivity.this.black);
                                        } else {
                                            canvas.drawCircle((float) ((int) n.fx), (float) ((int) n.fy), (float) r, RedBlackTreeActivity.this.red);
                                        }
                                        canvas.drawText(s, (float) ((int) n.fx), (float) ((int) n.fy+textY), RedBlackTreeActivity.this.white);
                                        RedBlackTreeActivity.this.animate = false;
                                        if (RedBlackTreeActivity.this.rbn != null && ((double) RedBlackTreeActivity.this.width) - RedBlackTreeActivity.this.rbn.oldX > ((double) (RedBlackTreeActivity.this.ScreenWidth / 2))) {
                                            RedBlackTreeActivity.this.scroll.smoothScrollTo(((int) RedBlackTreeActivity.this.rbn.oldX) - (RedBlackTreeActivity.this.ScreenWidth / 2), 0);
                                        } else if (RedBlackTreeActivity.this.rbn != null) {
                                            RedBlackTreeActivity.this.scroll.postDelayed(new C15332(), 100);
                                        }
                                    } else if (n.fx == n.oldX && n.fy == n.oldY) {
                                        if (n.highlight) {
                                            n.highlight = false;
                                            canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) (r + sizeYellow), RedBlackTreeActivity.this.yellow);
                                        } else {
                                            canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) (r + sizewhite), RedBlackTreeActivity.this.white);
                                        }
                                        if (n.black == 1) {
                                            canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) r, RedBlackTreeActivity.this.black);
                                        } else {
                                            canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) r, RedBlackTreeActivity.this.red);
                                        }
                                        canvas.drawText(s, (float) ((int) n.oldX), (float) ((int) n.oldY+textY), RedBlackTreeActivity.this.white);
                                    } else {
                                        tmp = (n.fx - n.oldX) / ((double) RedBlackTreeActivity.this.slow);
                                        if (tmp == 0.0d || n.fx - n.oldX ==0) {
                                            nx = n.oldX;
                                            ny = n.oldY + (((double) i) * ((n.fy - n.oldY) / ((double) RedBlackTreeActivity.this.slow)));
                                        } else {
                                            m = (n.fy - n.oldY) / (n.fx - n.oldX);
                                            nx = n.oldX + (((double) i) * tmp);
                                            ny = ((n.oldX + ((double) ((int) (((double) i) * tmp)))) * m) + (n.oldY - (n.oldX * m));
                                        }
                                        if (n.highlight) {
                                            n.highlight = false;
                                            canvas.drawCircle((float) ((int) nx), (float) ((int) ny), (float) (r + sizeYellow), RedBlackTreeActivity.this.yellow);
                                        } else {
                                            canvas.drawCircle((float) ((int) nx), (float) ((int) ny), (float) (r + sizewhite), RedBlackTreeActivity.this.white);
                                        }
                                        if (n.black == 1) {
                                            canvas.drawCircle((float) ((int) nx), (float) ((int) ny), (float) r, RedBlackTreeActivity.this.black);
                                        } else {
                                            canvas.drawCircle((float) ((int) nx), (float) ((int) ny), (float) r, RedBlackTreeActivity.this.red);
                                        }
                                        canvas.drawText(s, (float) ((int) nx), (float) ((int) ny+textY), RedBlackTreeActivity.this.white);
                                    }
                                    que.remove();
                                }
                                i++;
                                Thread.sleep((long) (100 - ((int) RedBlackTreeActivity.this.speed)));
                            } else {
                                RedBlackTreeActivity.this.random.setColor(-1);
//                                canvas.drawText(RedBlackTreeActivity.this.print, (float) (RedBlackTreeActivity.this.scroll.getScrollX() + 40), BitmapDescriptorFactory.HUE_ORANGE, RedBlackTreeActivity.this.random);
//                                canvas.drawText(RedBlackTreeActivity.this.print, (float) (RedBlackTreeActivity.this.scroll.getScrollX() + 40), 80.0f, RedBlackTreeActivity.this.printColor);

                                que = new LinkedList();
                                que.add(RedBlackTreeActivity.this.rbt.root);
                                i = 0;
                                while (!que.isEmpty()) {
                                    n = (RedBlackNode) que.peek();
                                    if (!(n.left == null || n.left.ileaf)) {
                                        que.add(n.left);
                                    }
                                    if (!(n.right == null || n.right.ileaf)) {
                                        que.add(n.right);
                                    }
                                    s = "" + n.data;
                                    ts = (int) RedBlackTreeActivity.this.white.measureText(s);
                                    r = circleSize;
//                                    if (35 < ts) {
//                                        r = (ts + 10) / 2;
//                                    }
                                    if (n == RedBlackTreeActivity.this.rbn || n.highlight) {
                                        canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) (r + sizeYellow), RedBlackTreeActivity.this.yellow);
                                    } else {
                                        canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) (r + sizewhite), RedBlackTreeActivity.this.white);
                                    }
                                    if (n.black == 1) {
                                        canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) r, RedBlackTreeActivity.this.black);
                                    } else {
                                        canvas.drawCircle((float) ((int) n.oldX), (float) ((int) n.oldY), (float) r, RedBlackTreeActivity.this.red);
                                    }
                                    canvas.drawText(s, (float) ((int) n.oldX), (float) ((int) n.oldY+textY), RedBlackTreeActivity.this.white);
                                    que.remove();
                                }
                            }
                        }
//                    insertButton.setEnabled(true);
//                    deleteButton.setEnabled(true) ;
//                    searchButton.setEnabled(true);
//                    undoButton.setEnabled(true);
//                    reloadButton.setEnabled(true);

                    } catch (Exception e2) {
                    }



                    this.ourHolder.unlockCanvasAndPost(canvas);

                }
            }

        }
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        ProgressBar progressBar;

        private LongOperation() {
        }

        protected String doInBackground(String... params) {
//            if (RedBlackTreeActivity.this.saveIS != null) {
//                SaveState sr = (SaveState) RedBlackTreeActivity.this.saveIS.getParcelable("key");
//                RedBlackTreeActivity.this.rbt = sr.rbt;
//                RedBlackTreeActivity.this.rbn = sr.rbn;
//                RedBlackTreeActivity.this.print = sr.print;
//                RedBlackTreeActivity.this.undoStack = sr.undoStack;
//                int max = RedBlackTreeActivity.this.ss.getMax();
//                RedBlackTreeActivity.this.speed = (((double) RedBlackTreeActivity.this.ss.getProgress()) / ((double) max)) * 100.0d;
//                RedBlackTreeActivity.this.dt.resume();
//            }
            return null;
        }

        protected void onPostExecute(String result) {
//            RedBlackTreeActivity.this.relativeLayout1.removeView(this.progressBar);
            RedBlackTreeActivity.this.constraintLayout.removeView(this.progressBar);

        }

        protected void onPreExecute() {
            this.progressBar = new ProgressBar(RedBlackTreeActivity.this.thisAct, null, 16842874);
            this.progressBar.setIndeterminate(true);
            this.progressBar.setVisibility(View.VISIBLE);
            LayoutParams param = new LayoutParams(100, 100);
            param.addRule(13);
//            RedBlackTreeActivity.this.relativeLayout1.addView(this.progressBar, param);
//            RedBlackTreeActivity.this.setContentView(RedBlackTreeActivity.this.relativeLayout1);
            RedBlackTreeActivity.this.constraintLayout.addView(this.progressBar, param);
            RedBlackTreeActivity.this.setContentView(RedBlackTreeActivity.this.constraintLayout);

        }

        protected void onProgressUpdate(Void... values) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(1024, 1024);
//        requestWindowFeature(7);
//        setContentView(R.layout.activity_redblacktree);
        setContentView(R.layout.activity_bst1);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        getWindow().setFeatureInt(7, R.layout.maintitlebar);

//        b1 = (Button) findViewById(R.id.b1);
//        b2 = (Button) findViewById(R.id.b2);
//        b3 = (Button) findViewById(R.id.b3);
//        b4 = (Button) findViewById(R.id.b4);
//        b5 = (Button) findViewById(R.id.b5);
//        b6 = (Button) findViewById(R.id.b6);
//        b7 = (Button) findViewById(R.id.b7);
//        b8 = (Button) findViewById(R.id.b8);
//        b9 = (Button) findViewById(R.id.b9);
//        b0 = (Button) findViewById(R.id.b0);
//        back = (Button) findViewById(R.id.back);
//
//        linearLayout2=(LinearLayout)findViewById(R.id.keyboard);
//        linearLayout2.setVisibility(View.INVISIBLE);
////
        editText1 = (EditText) findViewById(R.id.number);



//
//        editText1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//
//                linearLayout2.setVisibility(View.VISIBLE);
//                return true;
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.toolbar_color));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"1"));
//            }
//        });
//
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"2"));
//            }
//        });
//
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"3"));
//            }
//        });
//
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"4"));
//            }
//        });
//
//        b5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"5"));
//            }
//        });
//
//        b6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"6"));
//            }
//        });
//
//        b7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"7"));
//            }
//        });
//
//        b8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"8"));
//            }
//        });
//
//        b9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"9"));
//            }
//        });
//
//        b0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editText1.setText(editText1.getText().insert(editText1.getText().length(),"0"));
//            }
//        });
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(editText1.getText().toString().equals("")){
//                    editText1.setText(editText1.getText().insert(editText1.getText().length(),""));
//
//                }
//                else {
//                    editText1.setText(editText1.getText().delete(editText1.getText().length() - 1, editText1.getText().length()));
//                }
//            }
//        });

        setContent();
        setScreenDimensions();
        this.ScreenWidth = this.width;
        setDimension();
        setPaintSpecifications();
        this.thisAct = this;
        insertButton = (Button) findViewById(R.id.insert);
        deleteButton = (Button) findViewById(R.id.delete);
        searchButton = (ImageView) findViewById(R.id.search);
        undoButton = (ImageView) findViewById(R.id.undo);
        reloadButton = (ImageView) findViewById(R.id.reload);
        inorder = findViewById(R.id.inorder);
        preorder = findViewById(R.id.preorder);
        postorder = findViewById(R.id.postorder);

        linkedList = new LinkedList<>();
        check = findViewById(R.id.check);
        check.setText("");
//        ImageView quesButton = (ImageView) findViewById(R.id.question);
        this.ss = (SeekBar) findViewById(R.id.seekbar);
        ss.setProgress(50);
        RedBlackTreeActivity.this.speed = (((double) 50) / ((double) RedBlackTreeActivity.this.ss.getMax())) * 100.0d;


        this.editText1 = (EditText) findViewById(R.id.number);
        this.rbt = new RedBlackTree(this);
        this.undoStack = new Stack();
        this.saveIS = savedInstanceState;
//        this.relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout);
        constraintLayout=findViewById(R.id.contraintLayout);
//        linearLayout = findViewById(R.id.linearlayout);

//        relativeLayout1.setScaleX(2f);
//        relativeLayout1.setScaleY(2f);
//        AdView adView = new AdView(this.thisAct);
//        adView.setAdUnitId("ca-app-pub-7838254985501188/5871827158");
//        adView.setAdSize(AdSize.BANNER);
//        this.relativeLayout1.addView(adView);
//        LayoutParams params = (LayoutParams) adView.getLayoutParams();
//        params.addRule(8, R.id.verticalScrollView);
//        adView.setLayoutParams(params);
//        adView.loadAd(new Builder().build());
//        adView.loadAd(new Builder().build());
//        if (savedInstanceState != null) {
//            SaveState sr = (SaveState) savedInstanceState.getParcelable("key");
//            this.rbt = sr.rbt;
//            this.rbn = sr.rbn;
//            this.print = sr.print;
//            this.undoStack = sr.undoStack;
//            this.speed = (((double) this.ss.getProgress()) / ((double) this.ss.getMax())) * 100.0d;
//            this.dt.resume();
//        }
        if (this.dt != null) {
            try {
                this.dt.pause();
            } catch (Exception e) {
            }
        }
        this.rbt.rbta = this;
        this.rbt.startPositionX = (double) (((int) (((double) ((float) this.width)) * 0.9d)) / 2);
        if (this.rbt.root != null) {
            this.undo = true;
            double tmp = this.speed;
            this.speed = 100.0d;
            this.rbt.root.resizeTree();
            setWidth();
            setDimension();
            this.speed = tmp;
            this.undo = false;
        }
        this.dt.resume();
        try {
            this.ss.setOnSeekBarChangeListener(new SeekBarClass());
            insertButton.setOnClickListener(new InsertClass());
            deleteButton.setOnClickListener(new DeleteClass());
            searchButton.setOnClickListener(new FindClass());
            undoButton.setOnClickListener(new UndoClass());
            reloadButton.setOnClickListener(new RefreshClass());
            inorder.setOnClickListener(new InorderClass());
            preorder.setOnClickListener(new PreOrder());
            postorder.setOnClickListener(new PostOrder());

//            quesButton.setOnClickListener(new AboutClass());
            this.editText1.setOnFocusChangeListener(new C15308());
            this.scroll.setOnTouchListener(new C15319());
        } catch (Exception e2) {
        }
    }

    protected void onPause() {
        super.onPause();
        this.dt.pause();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
//        SaveState ss = new SaveState();
//        ss.save(this.rbt, this.rbn, this.print, this.undoStack);
//        savedInstanceState.putParcelable("key", ss);
        super.onSaveInstanceState(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
        try {
            if (VERSION.SDK_INT < 16) {
                getWindow().setFlags(1024, 1024);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(4);
            }
            this.dt.resume();
        } catch (Exception e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View v, MotionEvent event) {
        if (!(v == null || v == this.editText1)) {
            hideKeyboard(v);
        }
        return false;
    }

    public void hideKeyboard(View view) {
//        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void undo() {
        this.print = "";
        this.rbn = null;
        if (this.undoStack.isEmpty()) {
            setScreenDimensions();
            this.rbt = new RedBlackTree(this);
            return;
        }
        this.rbt = (RedBlackTree) this.undoStack.pop();
        this.rbt.rbta = this;
    }

    private void setWidth() {
        if (this.rbt.root.leftWidth + this.rbt.root.rightWidth > ((double) this.ScreenWidth)) {
            this.width = (int) (this.rbt.root.leftWidth + this.rbt.root.rightWidth);
        } else {
            setScreenDimensions();
        }
    }

    public void Refresh() {
        try {
            setScreenDimensions();
            this.rbt = new RedBlackTree(this);
            this.rbn = null;
            this.print = "";
            this.undo = false;
            this.undoStack.clear();
            setDimension();
        } catch (Exception e) {
        }
    }

    public void setScreenDimensions() {
        try {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
//            this.width = 1500;
//            this.height = 2500;
//            this.width = scroll.getWidth();
//            this.height = scroll.getHeight();
            this.width = size.x;
            this.height =1650;

        } catch (Exception e) {
        }
    }

    public void setContent() {
        this.dt = new DrawTree(this, null);
        this.dt.setId((int) System.currentTimeMillis());
        this.scroll = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        this.scroll.removeAllViews();
        this.dt.setLayoutParams(new FrameLayout.LayoutParams(this.width,this.height));

        this.scroll.addView(this.dt);
//        scroll.setScaleX(1.5f);
//        scroll.setScaleY(1.5f);
    }

    public void setDimension() {
        this.dt.setLayoutParams(new FrameLayout.LayoutParams(this.width,this.height));
    }

    public void setPaintSpecifications() {
        this.red = new Paint();
        this.black = new Paint();
        this.white = new Paint();
        this.blue = new Paint();
        this.yellow = new Paint();
        this.random = new Paint();
        this.line = new Paint();
        this.printColor = new Paint();
        this.yellow.setColor(Color.rgb(102, 255, 204));
        this.yellow.setStyle(Style.FILL);
        this.yellow.setAntiAlias(true);
        this.red.setColor(SupportMenu.CATEGORY_MASK);
        this.red.setStyle(Style.FILL);
        this.red.setAntiAlias(true);
        this.black.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.black.setStyle(Style.FILL);
        this.black.setAntiAlias(true);
        this.white.setColor(-1);
        this.blue.setColor(-16776961);
        this.white.setTextSize((float) this.textSize);
        this.white.setTextAlign(Align.CENTER);
        this.white.getFontMetrics(this.fm);
        this.white.setTextSize((float) this.textSize);
        this.white.setAntiAlias(true);
        this.blue.setTextSize((float) (this.textSize + 5));
        this.blue.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        this.blue.getFontMetrics(this.fm);
        this.random.setStyle(Style.FILL);
        this.random.setTextSize((float) (this.textSize + 5));
        this.random.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.random.getFontMetrics(this.fm);
        this.line.setColor(-256);
        this.line.setStrokeWidth(15);
        this.line.setStyle(Style.FILL);
        this.line.setAntiAlias(true);

        this.printColor.setColor(-1);
//        this.printColor.setStrokeWidth(15);
        this.printColor.setTextSize((float) (this.textSize + 5));
        this.printColor.setStyle(Style.FILL);
        this.printColor.setAntiAlias(true);

    }

    @Override
    public void onBackPressed () {

        Intent intent = new Intent(RedBlackTreeActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(RedBlackTreeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            Intent intent = new Intent(RedBlackTreeActivity.this, RBTCode.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.execution) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.details) {
            Intent intent = new Intent(RedBlackTreeActivity.this, RBTDetails1.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
