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

public class DetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String codes;
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_new_bst);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Details-BST");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        code = (TextView) findViewById(R.id.code);

        codes = "Introduction\n" +
                "\n" +
                "We extend the concept of linked data structures to structure \n" +
                "containing nodes with more than one self-referenced field. \n" +
                "A binary tree is made of nodes, where each node contains a \n" +
                "\"left\" reference, a \"right\" reference, and a data element. \n" +
                "The topmost node in the tree is called the root.\n" +
                "Every node (excluding a root) in a tree is connected by a \n" +
                "directed edge from exactly one other node. This node is\n" +
                " called a parent. On the other hand, each node can be\n" +
                " connected to arbitrary number of nodes, called children.\n" +
                " Nodes with no children are called leaves, or external nodes. \n" +
                "Nodes which are not leaves are called internal nodes.\n" +
                " Nodes with the same parent are called siblings.\n" +
                "\n" +
                "=====================================================\n" +
                "\n" +
                "More tree terminology:\n" +
                "\n" +
                "The depth of a node is the number of edges from the root to the node.\n" +
                "The height of a node is the number of edges from the node to the deepest leaf.\n" +
                "The height of a tree is a height of the root.\n" +
                "A full binary tree.is a binary tree in which each node \n" +
                "has exactly zero or two children.\n" +
                "A complete binary tree is a binary tree, which is\n" +
                " completely filled, with the possible exception of\n" +
                " the bottom level, which is filled from left to right.\n" +
                "\n" +
                "=====================================================\n" +
                "\n" +
                "\n" +
                "Advantages of trees\n" +
                "\n" +
                "Trees are so useful and frequently used, because they\n" +
                " have some very serious advantages:\n" +
                "\n" +
                "Trees reflect structural relationships in the data\n" +
                "Trees are used to represent hierarchies\n" +
                "Trees provide an efficient insertion and searching\n" +
                "Trees are very flexible data, allowing to move subtrees \n" +
                "around with minumum effort\n" +
                "\n" +
                "=====================================================\n" +
                "\n" +
                "\n" +
                "Insertion\n" +
                "\n" +
                "The insertion procedure is quite similar to searching. \n" +
                "We start at the root and recursively go down the tree \n" +
                "searching for a location in a BST to insert a new node.\n" +
                " If the element to be inserted is already in the tree,\n" +
                " we are done (we do not insert duplicates).\n" +
                " The new node will always replace a NULL reference.\n" +
                "\n" +
                "=====================================================\n" +
                "\n" +
                "Searching\n" +
                "\n" +
                "Searching in a BST always starts at the root. We compare\n" +
                " a data stored at the root with the key we are searching \n" +
                "for (let us call it as toSearch). If the node does not \n" +
                "contain the key we proceed either to the left or right \n" +
                "child depending upon comparison. If the result of comparison\n" +
                " is negative we go to the left child, otherwise - to the \n" +
                "right child. The recursive structure of a BST yields a recursive algorithm.\n" +
                "Searching in a BST has O(h) worst-case runtime complexity,\n" +
                " where h is the height of the tree. Since s binary search\n" +
                " tree with n nodes has a minimum of O(log n) levels, \n" +
                "it takes at least O(log n) comparisons to find a particular\n" +
                " node. Unfortunately, a binary serch tree can degenerate\n" +
                " to a linked list, reducing the search time to O(n).\n" +
                "\n" +
                "=====================================================\n" +
                "\n" +
                "\n" +
                "Deletion\n" +
                "\n" +
                "Deletion is somewhat more tricky than insertion. \n" +
                "There are several cases to consider. A node to\n" +
                " be deleted (let us call it as toDelete)\n" +
                "\n" +
                "is not in a tree;\n" +
                "is a leaf;\n" +
                "has only one child;\n" +
                "has two children.\n" +
                "If toDelete is not in the tree, there is nothing \n" +
                "to delete. If toDelete node has only one child the\n" +
                " procedure of deletion is identical to deleting a\n" +
                " node from a linked list - we just bypass that node being deleted\n" +
                "\n" +
                "=====================================================";

        code.setText(codes);
    }
        @Override
        public void onBackPressed () {

            Intent intent = new Intent(DetailsActivity.this, BinarySearchTreeActivity.class);
            startActivity(intent);
            finish();

            super.onBackPressed();
        }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            Intent intent = new Intent(DetailsActivity.this, CodeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.execution) {
            Intent intent = new Intent(DetailsActivity.this, BinarySearchTreeActivity.class);
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