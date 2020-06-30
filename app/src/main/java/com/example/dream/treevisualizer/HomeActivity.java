package com.example.dream.treevisualizer;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button bst,rbt,avl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.toolbar_color));
        }

       bst=(Button)findViewById(R.id.bst);


        bst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeActivity.this,BinarySearchTreeActivity.class);
                startActivity(intent);
                finish();

            }
        });
        avl=(Button)findViewById(R.id.avl);

        avl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeActivity.this,AvlTreeActivity.class);
                startActivity(intent);
                finish();

            }
        });

        rbt=(Button)findViewById(R.id.rb);


        rbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HomeActivity.this,RedBlackTreeActivity.class);
                startActivity(intent);
                finish();

            }
        });

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about_us) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
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
            return true;
        }

        if (id == R.id.action_contact_us) {
            PackageManager pm = getPackageManager();
            if (pm.checkPermission(Manifest.permission.CALL_PHONE, getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:7240915320")));
            } else {
                Toast.makeText(HomeActivity.this, "Permission Call Phone denied", Toast.LENGTH_SHORT).show();

            }
            return true;
        }

        if (id == R.id.action_feedback) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
