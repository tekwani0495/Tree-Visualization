package com.example.dream.treevisualizer;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class AVLDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String codes;
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_new_avl);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Details-AVL");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        code = (TextView) findViewById(R.id.code);

        codes = "                                   INTRODUCTION"+
                "\n"+
                "\n"+
                "The AVL trees, also called Height Balanced Trees \n" +
                " were first introduced by two Russians named Adelson-Velskii \n" +
                " and Landis. AVL trees are maintained in such a way that the \n" +
                " trees always remain within one level of being perfectly balanced. \n" +
                " As a result of the balance, retrievals from a tree with n\n" +
                " nodes may always be performed in O(log n) time. Insertions\n" +
                " and deletions on the AVL trees are also done in O (log n) time.\n"+

        "Although the AVL Tree is considered logarithmic balanced, it is not quite\n" +
                 "balanced! AVL can be implemented only for unique keys!\n"+

                "An AVL Tree is a binary tree with the additional balance property\n" +
                " that, for any node in the tree, the height of the left\n" +
                " and right subtrees can differ by at most one. \n"+
                "\n"+
                "\n"+
                "\n"+
                "\n"+
        "                                 Rules For AVL Trees \n"+
                "\n"+
                "\n"+
                "1. An empty tree is height balanced.\n"+
                "\n"+
                "2. For a tree T, T-L shall denote the left subtree +\n"+
                "\n"+
                " and T-R shall denote the right subtree. +\n"+
                "3. For a tree T, h-L shall denote the height of the left +\n" +
                "\n"+
                " subtree (T-L) and h-R shall denote the height of the right subtree (T-R). \n"+
                "4. A non empty binary tree is HEIGHT BALANCED if and only if \n"+
                "\n"+
        "T-R and T-L are height balanced \n"+
                "\n"+
                "-1 <= (h-L) - (h-R)| <= +1\n"+
        "5. The BALANCE FACTOR of a node T in a binary tree bf(T) = h-L - h-R.\n"+
                "6. For any node T in an AVL tree, bf(T) = -1, 0, or +1\n"+
                "\n"+
                "\n"+
                "\n"+
                "\n"+
                "\n"+
        "                                  4 Types of Rotations \n"+
                "\n"+
                "\n"+
                "Authors vary with respect to the exact titles and descriptions\n" +
                " for the four rotations, but all have essentially the same \n" +
                "four rotations. In order to maintain the balance within the tree,\n" +
                " nodes will have to be rotated into a balanced location.\n" +
                " The nodes will be rotated with respect to the nearest ancestor\n" +
                " that contains a +2 or -2 balance factor; the following diagrams\n" +
                " will designate this ancestor with respect to the subtree A.\n" +
                " An AVL tree will need only four rotations for insertion.\n" +
                " AVL trees must be completely constructed with these \n" +
                "four rotations in order to work! \n" +
                "\n"+
                "ROTATE RIGHT rotation - the new node N is inserted on the\n" +
                " left subtree of the left subtree of A.\n" +
                "\n"+
                "ROTATE LEFT rotation - the new node N is inserted on the \n" +
                "right subtree of the right subtree of A.\n" +
                "\n"+
                "DOUBLE -ROTATE RIGHT rotation - the new node N is inserted \n" +
                "on the right subtree of the left subtree of A. \n" +
                "\n"+
                "DOUBLE -ROTATE LEFT rotation - the new node N is inserted on\n" +
                " the left subtree of the right subtree of A. \n" +
                "\n"+
                "It is important to emphasize that if trees are completely\n" +
                " constructed using AVL rotations and insertions, there will\n" +
                " only be four possible situations for a rotations. \n"
        ;

        code.setText(codes);
    }
    @Override
    public void onBackPressed () {

        Intent intent = new Intent(AVLDetails.this, AvlTreeActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(AVLDetails.this, HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            Intent intent = new Intent(AVLDetails.this, AVLCode.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.execution) {
            Intent intent = new Intent(AVLDetails.this, AvlTreeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.details) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}