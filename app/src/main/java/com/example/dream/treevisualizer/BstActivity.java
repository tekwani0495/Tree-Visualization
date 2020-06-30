package com.example.dream.treevisualizer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dream.treevisualizer.BinarySearchTree.root;
import static com.example.dream.treevisualizer.BinarySearchTree.zoomLock;
import static java.lang.Thread.sleep;

public class BstActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    EditText insertEdit,deleteEdit,findEdit;
    Button insertButton,deleteButton,findButton,printButton,heightButton,inorderButton,postorderButton;
    Button zoomOutButton,zoomInButton;
    TextView textView,textView2;
    BinarySearchTree binarySearchTree;
    static BstActivity bstActivity;
    FrameLayout frameLayout;
    PathAnimationActivity pathAnimationActivity;
//    Node heap[];
    int height=0;
    int act=0;
    int arr[];
    LinkedList<MyView> linkedList;
    int number=0;
    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    LinearLayout linearLayout;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,back;
    int xMax=0;
    int yMax=0;
    float zoomOut=1f,zoomIn=1f,zoomValue=1f;
    int pivot;
    EditText editText;
    String input;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bst);
//        bstActivity=this;

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);
        back = (Button) findViewById(R.id.back);

        linearLayout=(LinearLayout)findViewById(R.id.keyboard);

//        editText=(EditText)findViewById(R.id.edit);
//
        linearLayout.setVisibility(View.INVISIBLE);
//
//        editText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//
//                linearLayout.setVisibility(View.INVISIBLE);
//                return true;
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setText(editText.getText().insert(editText.getText().length(),"1"));
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"2"));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"3"));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"4"));
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"5"));
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"6"));
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"7"));
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"8"));
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"9"));
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(editText.getText().insert(editText.getText().length(),"0"));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equals("")){
                    editText.setText(editText.getText().insert(editText.getText().length(),""));

                }
                else {
                    editText.setText(editText.getText().delete(editText.getText().length() - 1, editText.getText().length()));
                }
            }
        });




        linkedList = new LinkedList<MyView>();
        bstActivity=this;
        binarySearchTree = new BinarySearchTree();
//        heap = new Node[100]; //skew tree error
        frameLayout = (FrameLayout) findViewById(R.id.layout1);
        frameLayout.setMinimumHeight(2500);
        frameLayout.setMinimumWidth(2500);
        pathAnimationActivity = new PathAnimationActivity();
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("");
        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("");
        horizontalScrollView=findViewById(R.id.horizontalScroll);
        scrollView= findViewById(R.id.scrollView2);
//        scrollView.setScrollY(500);
//        horizontalScrollView.setScrollX(500);

        insertButton = (Button) findViewById(R.id.insertButtonId);
        insertEdit = (EditText)findViewById(R.id.insertTextId);
        // scroll();
//        new Runnable(){
//            @Override
//            public void run() {
//                        scrollView.setScrollY(500);
//        horizontalScrollView.setScrollX(450);
//
//            }
//        }.run();

        zoomInButton = findViewById(R.id.zoomInButton);
        zoomOutButton = findViewById(R.id.zoomOutButton);

        zoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zoomValue==1f)
                    return;

                zoomValue+=0.15f;
                pivot-=50;

                frameLayout.setPivotY(pivot);
                frameLayout.setScaleX(zoomValue);
                frameLayout.setScaleY(zoomValue);


            }
        });

        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(zoomValue<=0.60f)
                    return;

                zoomValue-=.15f;
                pivot+=50;

                frameLayout.setPivotY(pivot);
                frameLayout.setScaleX(zoomValue);
                frameLayout.setScaleY(zoomValue);

                textView.setText(String.valueOf(zoomValue));
            }
        });



        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                insertButton.setEnabled(true);
//                scrollView.setScrollY(500);
//                horizontalScrollView.setScrollX(500);
                if(root==null){
//                    scrollView.setScrollY(500);
                    horizontalScrollView.setScrollX(450);


                }
                MyView myView=null;
                String insertValue = insertEdit.getText().toString();
                if (!insertValue.equals("")) {
                    pause(1000);
//
//                    insertButton.setEnabled(false);
                        act++;

                        arr = new int[100];

                        int data = Integer.parseInt(insertValue);
                        myView = getMyView(data);
                        binarySearchTree.insert(data, arr, myView);

                        animation(arr, myView);

//                    }
                }
                else{
                     return;
                }
                int x=myView.getViewX();
                int y=myView.getViewY();
                textView.setText("view: "+x+" "+y);

                int x1=horizontalScrollView.getScrollX();
                int y1=scrollView.getScrollY();
                textView2.setText("parent: "+x1+" "+y1);


            }
        });

        deleteButton = (Button) findViewById(R.id.deleteButtonId);
        deleteEdit = (EditText)findViewById(R.id.deleteTextId);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String deleteValue = deleteEdit.getText().toString();

                if (!deleteValue.equals("")) {
//                arr = new int[30];
//
//                for (int i = 0; i < 30; i++)
//                    arr[i] = 0;
//
                    int data = Integer.parseInt(deleteValue);
                   boolean b= binarySearchTree.delete(data);
//                   if(b==true)
//                       pause(1500);

                   //
//                animation(arr, getMyView(data));

                }
            }
        });

        printButton = (Button) findViewById(R.id.printButtonId);

        final String preorder="preorder";

        final Handler handlerPreorder = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                MyView myView =(MyView) bundle.getSerializable(preorder);
                pathAnimationActivity.zoom(myView);
            }
        };
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pause();


                textView.setText("Preorder: ");
                if(root!=null)
                binarySearchTree.preorderN(root);
//                else
//                    return;

                pause(linkedList.size()*1100);

                Runnable runnable = new Runnable() {
                    public void run() {

                        for(;linkedList.size()!=0;) {

                            Message msg = handlerPreorder.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(preorder,linkedList.removeFirst());
                            msg.setData(bundle);
                            handlerPreorder.sendMessage(msg);

                                try {
                                    sleep(1000);
                                } catch (Exception e) {
                                }

                        }
                    }
                };

                Thread mythread = new Thread(runnable);
                mythread.start();
            }
        });

         inorderButton= (Button) findViewById(R.id.inorderButtonId);

        final String inorder="inorder";

        final Handler handlerInorder = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                MyView myView =(MyView) bundle.getSerializable(inorder);
                pathAnimationActivity.zoom(myView);
            }
        };
        inorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pause();


                textView.setText("Inorder: ");

                if(root!=null)
                    binarySearchTree.inorder(root);

                pause(linkedList.size()*1100);

                Runnable runnable = new Runnable() {
                    public void run() {

                        for(;linkedList.size()!=0;) {

                            Message msg = handlerInorder.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(inorder,linkedList.removeFirst());
                            msg.setData(bundle);
                            handlerInorder.sendMessage(msg);

                            try {
                                sleep(1000);
                            } catch (Exception e) {
                            }

                        }
                    }
                };

                Thread mythread = new Thread(runnable);
                mythread.start();
            }
        });


        postorderButton= (Button) findViewById(R.id.postorderButtonId);

        final String postorder="postorder";

        final Handler handlerPostorder = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                MyView myView =(MyView) bundle.getSerializable(postorder);
                pathAnimationActivity.zoom(myView);
            }
        };
        postorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                pause();


                textView.setText("Postorder: ");

                if(root!=null)
                    binarySearchTree.postorder(root);

                pause(linkedList.size()*1100);

                Runnable runnable = new Runnable() {
                    public void run() {

                        for(;linkedList.size()!=0;) {

                            Message msg = handlerPostorder.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(postorder,linkedList.removeFirst());
                            msg.setData(bundle);
                            handlerPostorder.sendMessage(msg);

                            try {
                                sleep(1000);
                            } catch (Exception e) {
                            }

                        }
                    }
                };

                Thread mythread = new Thread(runnable);
                mythread.start();
            }
        });

        findButton = (Button) findViewById(R.id.findButtonId);
        findEdit = (EditText)findViewById(R.id.findTextId);


        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String findValue = findEdit.getText().toString();
                if (!findValue.equals("")) {

                    int data = Integer.parseInt(findValue);
//                    pause(1800);
                    Node node = binarySearchTree.find(data);
                    if(node ==null)
                        return;
                    else {
                        pause(1800);
                        pathAnimationActivity.zoom(node.myView, 3, 500);
//                    pathAnimationActivity.zoom(node.myView);

                    }
                }

            }
        });


        heightButton = (Button) findViewById(R.id.heightButtonId);


        heightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a= binarySearchTree.findHeight(root);
                String s= String.valueOf(a);
                textView.setText("HEIGHT: "+a);
            }
        });

    }
//    void scroll() {
//        scrollView.setScrollY(500);
//        horizontalScrollView.setScrollX(500);
//    }
    private static final String TAG = "BstActivity";
    public void animation(int arr[],MyView myView){
//        ArrowLeft arrowLeft = new ArrowLeft(BstActivity.bstActivity,0);
//        ArrowRight arrowRight = new ArrowRight(BstActivity.bstActivity,0);

        int x,y;
        x=1100;
        y=100;

        if(arr[0]==0){
            pathAnimationActivity.layout(myView,frameLayout);
            pathAnimationActivity.startAnimation(myView,0,0,x,y);
            myView.setXY(x,y);

            return;
        }

        int i=0;
        for(i=0;;i++){
            if(arr[i]==0)
                break;
            if(arr[i]==3){
                x=x-300;
                y=y+250;
//       pathAnimationActivity.startAnimation(ring,ring.getViewX(),ring.getViewY(),ring.getViewX()-150,ring.getViewY()+150);
            }
            else if(arr[i]==6){
                x=x+300;
                y=y+250;
//                pathAnimationActivity.startAnimation(ring,ring.getViewX(),ring.getViewY(),ring.getViewX()+150,ring.getViewY()+150);

            }
        }

        if(arr[i-1]==3){
            Arrow arrow = new Arrow(BstActivity.bstActivity,0);

            pathAnimationActivity.layout(arrow,frameLayout);

            pathAnimationActivity.startAnimation(arrow,x+150,y-115,x+150,y-115);
//            textView.append("\ncor: "+x+" "+y+" "+" \n");
            arrow.setXY(x+150,y-115);
            myView.getNode().arrow=arrow;
        }
        else if (arr[i-1]==6){
            Arrow arrow = new Arrow(BstActivity.bstActivity,1);
            pathAnimationActivity.layout(arrow,frameLayout);
            pathAnimationActivity.startAnimation(arrow,x-140,y-105,x-140,y-105);
            arrow.setXY(x-140,y-105);
            myView.getNode().arrow=arrow;
        }
        pathAnimationActivity.layout(myView,frameLayout);
        pathAnimationActivity.startAnimation(myView,0,0,x,y);
        myView.setXY(x,y);


        //         mainActivity.insertButton.setEnabled(true);

        //    frameLayout.removeView(ring);
    }

    public MyView getMyView(int data){
        return new MyView(BstActivity.this,data);
    }
    
    
    
    
    
    
    //@@@@@@@@@@@@@@@
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        linearLayout.setVisibility(View.INVISIBLE);
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent=new Intent(BstActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about_us) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(BstActivity.this);
            dialog.setCancelable(false);
            dialog.setTitle("About us");
            dialog.setMessage("Binary Tree Class is an Android Application based on the idea  of graphically implementing the " +
                    "binary tree data structures \n \n" + "Made as a part of  Minor project by \n \n" + "Shubham Misra(151404)\n" + "Shubham Verma(151406)\n" + "Kapil Tekwani(151458)");
            dialog.setPositiveButton("Ok Got it!!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            })
                    .setNegativeButton(" ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

            final AlertDialog alert = dialog.create();
            alert.show();
            return true;
        }

        if (id == R.id.action_rate_us) {
          /* Dialog rankDialog;
            RatingBar ratingBar;
            rankDialog = new Dialog(BstActivity.this, R.style.FullHeightDialog);
            rankDialog.setContentView(R.layout.);
            rankDialog.setCancelable(true);
            ratingBar = (RatingBar)rankDialog.findViewById(R.id.dialog_ratingbar);
            TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
            text.setText("Rate us");
            Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            rankDialog.show();*/

            return true;
        }
        if (id == R.id.action_contact_us) {
            PackageManager pm = getPackageManager();
            if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:7240915320")));
            } else {
                Toast.makeText(BstActivity.this, "Permission Call Phone denied", Toast.LENGTH_SHORT).show();

            }
            return true;
        }
        if (id == R.id.action_feedback) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent=new Intent(BstActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            Intent intent=new Intent(BstActivity.this,CodeActivity.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.execution) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.details) {
            Intent intent=new Intent(BstActivity.this,DetailsActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void before(){

        insertButton.setEnabled(false);
        deleteButton.setEnabled(false);
        findButton.setEnabled(false);
        heightButton.setEnabled(false);
        printButton.setEnabled(false);
        inorderButton.setEnabled(false);
        postorderButton.setEnabled(false);

    }
    void after(){
        insertButton.setEnabled(true);
        deleteButton.setEnabled(true);
        findButton.setEnabled(true);
        heightButton.setEnabled(true);
        printButton.setEnabled(true);
        inorderButton.setEnabled(true);
        postorderButton.setEnabled(true);

    }

    final String hide="hide";
    int time =0;

    final Handler handlerPause = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
//            MyView myView =(MyView) bundle.getSerializable(postorder);
            int a = bundle.getInt(hide);

            if(a==0)
                before();
            else
                after();

      }
    };

        public void pause(int a) {
            time=a;
            before();
            Runnable runnable = new Runnable() {
                public void run() {

//                    Message msg = handlerPause.obtainMessage();
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(hide,0);
//                    msg.setData(bundle);
//                    handlerPause.sendMessage(msg);


                    try {
                            sleep(time);
                        } catch (Exception e) {
                        }
                    Message msg = handlerPause.obtainMessage();
                    Bundle bundle = new Bundle();
//                    bundle.clear();
                    bundle.putInt(hide,1);

                    msg.setData(bundle);
                    handlerPause.sendMessage(msg);

                }

            };

            Thread mythread = new Thread(runnable);
            mythread.start();
        }


}
