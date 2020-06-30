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

public class CodeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_new_bst);

        TextView code = (TextView) findViewById(R.id.code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Code-BST");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        codes = "/*\n" +
                " *  Java Program to Implement Binary Search Tree\n" +
                " */\n" +
                " \n" +
                " import java.util.Scanner;\n" +
                " \n" +
                " /* Class BSTNode */\n" +
                " class BSTNode\n" +
                " {\n" +
                "     BSTNode left, right;\n" +
                "     int data;\n" +
                " \n" +
                "     /* Constructor */\n" +
                "     public BSTNode()\n" +
                "     {\n" +
                "         left = null;\n" +
                "         right = null;\n" +
                "         data = 0;\n" +
                "     }\n" +
                "     /* Constructor */\n" +
                "     public BSTNode(int n)\n" +
                "     {\n" +
                "         left = null;\n" +
                "         right = null;\n" +
                "         data = n;\n" +
                "     }\n" +
                "     /* Function to set left node */\n" +
                "     public void setLeft(BSTNode n)\n" +
                "     {\n" +
                "         left = n;\n" +
                "     }\n" +
                "     /* Function to set right node */ \n" +
                "     public void setRight(BSTNode n)\n" +
                "     {\n" +
                "         right = n;\n" +
                "     }\n" +
                "     /* Function to get left node */\n" +
                "     public BSTNode getLeft()\n" +
                "     {\n" +
                "         return left;\n" +
                "     }\n" +
                "     /* Function to get right node */\n" +
                "     public BSTNode getRight()\n" +
                "     {\n" +
                "         return right;\n" +
                "     }\n" +
                "     /* Function to set data to node */\n" +
                "     public void setData(int d)\n" +
                "     {\n" +
                "         data = d;\n" +
                "     }\n" +
                "     /* Function to get data from node */\n" +
                "     public int getData()\n" +
                "     {\n" +
                "         return data;\n" +
                "     }     \n" +
                " }\n" +
                " \n" +
                " /* Class BST */\n" +
                " class BST\n" +
                " {\n" +
                "     private BSTNode root;\n" +
                " \n" +
                "     /* Constructor */\n" +
                "     public BST()\n" +
                "     {\n" +
                "         root = null;\n" +
                "     }\n" +
                "     /* Function to check if tree is empty */\n" +
                "     public boolean isEmpty()\n" +
                "     {\n" +
                "         return root == null;\n" +
                "     }\n" +
                "     /* Functions to insert data */\n" +
                "     public void insert(int data)\n" +
                "     {\n" +
                "         root = insert(root, data);\n" +
                "     }\n" +
                "     /* Function to insert data recursively */\n" +
                "     private BSTNode insert(BSTNode node, int data)\n" +
                "     {\n" +
                "         if (node == null)\n" +
                "             node = new BSTNode(data);\n" +
                "         else\n" +
                "         {\n" +
                "             if (data <= node.getData())\n" +
                "                 node.left = insert(node.left, data);\n" +
                "             else\n" +
                "                 node.right = insert(node.right, data);\n" +
                "         }\n" +
                "         return node;\n" +
                "     }\n" +
                "     /* Functions to delete data */\n" +
                "     public void delete(int k)\n" +
                "     {\n" +
                "         if (isEmpty())\n" +
                "             System.out.println(\"Tree Empty\");\n" +
                "         else if (search(k) == false)\n" +
                "             System.out.println(\"Sorry \"+ k +\" is not present\");\n" +
                "         else\n" +
                "         {\n" +
                "             root = delete(root, k);\n" +
                "             System.out.println(k+ \" deleted from the tree\");\n" +
                "         }\n" +
                "     }\n" +
                "     private BSTNode delete(BSTNode root, int k)\n" +
                "     {\n" +
                "         BSTNode p, p2, n;\n" +
                "         if (root.getData() == k)\n" +
                "         {\n" +
                "             BSTNode lt, rt;\n" +
                "             lt = root.getLeft();\n" +
                "             rt = root.getRight();\n" +
                "             if (lt == null && rt == null)\n" +
                "                 return null;\n" +
                "             else if (lt == null)\n" +
                "             {\n" +
                "                 p = rt;\n" +
                "                 return p;\n" +
                "             }\n" +
                "             else if (rt == null)\n" +
                "             {\n" +
                "                 p = lt;\n" +
                "                 return p;\n" +
                "             }\n" +
                "             else\n" +
                "             {\n" +
                "                 p2 = rt;\n" +
                "                 p = rt;\n" +
                "                 while (p.getLeft() != null)\n" +
                "                     p = p.getLeft();\n" +
                "                 p.setLeft(lt);\n" +
                "                 return p2;\n" +
                "             }\n" +
                "         }\n" +
                "         if (k < root.getData())\n" +
                "         {\n" +
                "             n = delete(root.getLeft(), k);\n" +
                "             root.setLeft(n);\n" +
                "         }\n" +
                "         else\n" +
                "         {\n" +
                "             n = delete(root.getRight(), k);\n" +
                "             root.setRight(n);             \n" +
                "         }\n" +
                "         return root;\n" +
                "     }\n" +
                "     /* Functions to count number of nodes */\n" +
                "     public int countNodes()\n" +
                "     {\n" +
                "         return countNodes(root);\n" +
                "     }\n" +
                "     /* Function to count number of nodes recursively */\n" +
                "     private int countNodes(BSTNode r)\n" +
                "     {\n" +
                "         if (r == null)\n" +
                "             return 0;\n" +
                "         else\n" +
                "         {\n" +
                "             int l = 1;\n" +
                "             l += countNodes(r.getLeft());\n" +
                "             l += countNodes(r.getRight());\n" +
                "             return l;\n" +
                "         }\n" +
                "     }\n" +
                "     /* Functions to search for an element */\n" +
                "     public boolean search(int val)\n" +
                "     {\n" +
                "         return search(root, val);\n" +
                "     }\n" +
                "     /* Function to search for an element recursively */\n" +
                "     private boolean search(BSTNode r, int val)\n" +
                "     {\n" +
                "         boolean found = false;\n" +
                "         while ((r != null) && !found)\n" +
                "         {\n" +
                "             int rval = r.getData();\n" +
                "             if (val < rval)\n" +
                "                 r = r.getLeft();\n" +
                "             else if (val > rval)\n" +
                "                 r = r.getRight();\n" +
                "             else\n" +
                "             {\n" +
                "                 found = true;\n" +
                "                 break;\n" +
                "             }\n" +
                "             found = search(r, val);\n" +
                "         }\n" +
                "         return found;\n" +
                "     }\n" +
                "     /* Function for inorder traversal */\n" +
                "     public void inorder()\n" +
                "     {\n" +
                "         inorder(root);\n" +
                "     }\n" +
                "     private void inorder(BSTNode r)\n" +
                "     {\n" +
                "         if (r != null)\n" +
                "         {\n" +
                "             inorder(r.getLeft());\n" +
                "             System.out.print(r.getData() +\" \");\n" +
                "             inorder(r.getRight());\n" +
                "         }\n" +
                "     }\n" +
                "     /* Function for preorder traversal */\n" +
                "     public void preorder()\n" +
                "     {\n" +
                "         preorder(root);\n" +
                "     }\n" +
                "     private void preorder(BSTNode r)\n" +
                "     {\n" +
                "         if (r != null)\n" +
                "         {\n" +
                "             System.out.print(r.getData() +\" \");\n" +
                "             preorder(r.getLeft());             \n" +
                "             preorder(r.getRight());\n" +
                "         }\n" +
                "     }\n" +
                "     /* Function for postorder traversal */\n" +
                "     public void postorder()\n" +
                "     {\n" +
                "         postorder(root);\n" +
                "     }\n" +
                "     private void postorder(BSTNode r)\n" +
                "     {\n" +
                "         if (r != null)\n" +
                "         {\n" +
                "             postorder(r.getLeft());             \n" +
                "             postorder(r.getRight());\n" +
                "             System.out.print(r.getData() +\" \");\n" +
                "         }\n" +
                "     }     \n" +
                " }\n" +
                " \n" +
                " /* Class BinarySearchTree */\n" +
                " public class BinarySearchTree\n" +
                " {\n" +
                "     public static void main(String[] args)\n" +
                "    {                 \n" +
                "        Scanner scan = new Scanner(System.in);\n" +
                "        /* Creating object of BST */\n" +
                "        BST bst = new BST(); \n" +
                "        System.out.println(\"Binary Search Tree Test\\n\");          \n" +
                "        char ch;\n" +
                "        /*  Perform tree operations  */\n" +
                "        do    \n" +
                "        {\n" +
                "            System.out.println(\"\\nBinary Search Tree Operations\\n\");\n" +
                "            System.out.println(\"1. insert \");\n" +
                "            System.out.println(\"2. delete\");\n" +
                "            System.out.println(\"3. search\");\n" +
                "            System.out.println(\"4. count nodes\");\n" +
                "            System.out.println(\"5. check empty\"); \n" +
                " \n" +
                "            int choice = scan.nextInt();            \n" +
                "            switch (choice)\n" +
                "            {\n" +
                "            case 1 : \n" +
                "                System.out.println(\"Enter integer element to insert\");\n" +
                "                bst.insert( scan.nextInt() );                     \n" +
                "                break;                          \n" +
                "            case 2 : \n" +
                "                System.out.println(\"Enter integer element to delete\");\n" +
                "                bst.delete( scan.nextInt() );                     \n" +
                "                break;                         \n" +
                "            case 3 : \n" +
                "                System.out.println(\"Enter integer element to search\");\n" +
                "                System.out.println(\"Search result : \"+ bst.search( scan.nextInt() ));\n" +
                "                break;                                          \n" +
                "            case 4 : \n" +
                "                System.out.println(\"Nodes = \"+ bst.countNodes());\n" +
                "                break;     \n" +
                "            case 5 :  \n" +
                "                System.out.println(\"Empty status = \"+ bst.isEmpty());\n" +
                "                break;            \n" +
                "            default : \n" +
                "                System.out.println(\"Wrong Entry \\n \");\n" +
                "                break;   \n" +
                "            }\n" +
                "            /*  Display tree  */ \n" +
                "            System.out.print(\"\\nPost order : \");\n" +
                "            bst.postorder();\n" +
                "            System.out.print(\"\\nPre order : \");\n" +
                "            bst.preorder();\n" +
                "            System.out.print(\"\\nIn order : \");\n" +
                "            bst.inorder();\n" +
                " \n" +
                "            System.out.println(\"\\nDo you want to continue (Type y or n) \\n\");\n" +
                "            ch = scan.next().charAt(0);                        \n" +
                "        } while (ch == 'Y'|| ch == 'y');               \n" +
                "    }\n" +
                " }";

        code.setText(codes);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CodeActivity.this, BinarySearchTreeActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(CodeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.execution) {
            Intent intent = new Intent(CodeActivity.this, BinarySearchTreeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.details) {
            Intent intent = new Intent(CodeActivity.this, DetailsActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
