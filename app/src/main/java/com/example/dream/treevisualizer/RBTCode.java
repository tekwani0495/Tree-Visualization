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

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class RBTCode extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_new_rbt);

        TextView code = (TextView) findViewById(R.id.code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Code-RBT");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        codes = "                  -----RED-BLACK TREE INSERTION-----"+
                "\n"+
                "\n"+
                "\n"+
                "static void RBinsert(Node [] rbTree, int key)\n" +
                "    {\n" +
                "        Node k = new Node();\n" +
                "        k.data = key;\n" +
                "        k.color = 0; // color new key RED\n" +
                "        treeInsert(rbTree, k);\n" +
                "        while (k != rbTree[0] & k.parent.color == 0)\n" +
                "        {\n" +
                "            // case k's parent is left child\n" +
                "            if (k.parent == k.parent.parent.lchild)\n" +
                "            {\n" +
                "                Node uncle = k.parent.parent.rchild;\n" +
                "                System.out.println(uncle.data);\n" +
                "                // case 1: k's uncle is RED and k is left child\n" +
                "                if (uncle != null & uncle.color == 0)\n" +
                "                {\n" +
                "                    k.parent.color = 1; // new key's parent becomes BLACK\n" +
                "                    uncle.color = 1;\n" +
                "                    k.parent.parent.color = 0;\n" +
                "                    k = k.parent.parent;\n" +
                "                }\n" +
                "                else\n" +
                "                {\n" +
                "                    // case 2: k's uncle is BLACK and k is right child\n" +
                "                    if (k == k.parent.rchild)\n" +
                "                    {\n" +
                "                        k = k.parent;\n" +
                "                        leftRotate(rbTree, k);\n" +
                "                    }\n" +
                "                    // case 3: k's uncle is BLACK and k is left child\n" +
                "                    k.parent.color = 1;\n" +
                "                    k.parent.parent.color = 0;\n" +
                "                    rightRotate(rbTree, k.parent.parent);\n" +
                "                }\n" +
                "            }\n" +
                "            // case k's parent is right child\n" +
                "            else\n" +
                "            {\n" +
                "                Node uncle = k.parent.parent.lchild;\n" +
                "                System.out.println(uncle);\n" +
                "                // case 1: k's uncle is RED and k is left child\n" +
                "                if (uncle != null & uncle.color == 0)        -----> crash here\n" +
                "                {\n" +
                "                    k.parent.color = 1;\n" +
                "                    uncle.color = 1;\n" +
                "                    k.parent.parent.color = 0;\n" +
                "                    k = k.parent.parent;\n" +
                "                }\n" +
                "                else\n" +
                "                {\n" +
                "                    // case 2: k's uncle is BLACK and k is left child\n" +
                "                    if (k == k.parent.lchild)\n" +
                "                    {\n" +
                "                        k = k.parent;\n" +
                "                        rightRotate(rbTree, k);\n" +
                "                    }\n" +
                "                    // case 3: k's uncle is BLACK and k is right child\n" +
                "                    k.parent.color = 1;\n" +
                "                    k.parent.parent.color = 0;\n" +
                "                    leftRotate(rbTree, k.parent.parent);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        rbTree[0].color = 1;\n" +
                "    }" +
                "\n"+
                "\n"+
                "\n"+
                "                    ----RED-BLACK TREE DELETION----"+
                "\n"+
                "\n"+
                "\n"+
                "RB-DELETE(T, z)\n" +
                "    1 if left[z] = nil[T] or right[z] = nil[T]\n" +
                "    2    then y ← z\n" +
                "    3    else y ← TREE-SUCCESSOR(z)\n" +
                "    4 if left[y] ≠ nil[T]\n" +
                "    5    then x ← left[y]\n" +
                "    6    else x ← right[y]\n" +
                "    7 p[x] ← p[y]\n" +
                "    8 if p[y] = nil[T]\n" +
                "    9    then root[T] ← x\n" +
                "    10    else if y = left[p[y]]\n" +
                "    11            then left[p[y]] ← x\n" +
                "    12            else right[p[y]] ← x\n" +
                "    13 if y != z\n" +
                "    14    then key[z] ← key[y]\n" +
                "    15         copy y's satellite data into z\n" +
                "    16 if color[y] = BLACK\n" +
                "    17    then RB-DELETE-FIXUP(T, x)\n" +
                "    18 return y\n";

        code.setText(codes);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(RBTCode.this, RedBlackTreeActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(RBTCode.this, HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.execution) {
            Intent intent = new Intent(RBTCode.this, RedBlackTreeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.details) {
            Intent intent = new Intent(RBTCode.this, RBTDetails1.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
