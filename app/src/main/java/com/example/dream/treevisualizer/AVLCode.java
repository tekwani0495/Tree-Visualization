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

public class AVLCode extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_new_avl);

        TextView code = (TextView) findViewById(R.id.code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Code-AVL");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        codes = " Java program for INSERTION in AVL Tree\n" +
                "class Node {\n" +
                        "    int key, height;\n" +
                        "    Node left, right;\n" +
                        " \n" +
                        "    Node(int d) {\n" +
                        "        key = d;\n" +
                        "        height = 1;\n" +
                        "    }\n" +
                        "}\n" +
                        " \n" +
                        "class AVLTree {\n" +
                        " \n" +
                        "    Node root;\n" +
                        " \n" +
                        "    // A utility function to get height of the tree\n" +
                        "    int height(Node N) {\n" +
                        "        if (N == null)\n" +
                        "            return 0;\n" +
                        " \n" +
                        "        return N.height;\n" +
                        "    }\n" +
                        " \n" +
                        "    // A utility function to get maximum of two integers\n" +
                        "    int max(int a, int b) {\n" +
                        "        return (a > b) ? a : b;\n" +
                        "    }\n" +
                        " \n" +
                        "    // A utility function to right rotate subtree rooted with y\n" +
                        "    // See the diagram given above.\n" +
                        "    Node rightRotate(Node y) {\n" +
                        "        Node x = y.left;\n" +
                        "        Node T2 = x.right;\n" +
                        " \n" +
                        "        // Perform rotation\n" +
                        "        x.right = y;\n" +
                        "        y.left = T2;\n" +
                        " \n" +
                        "        // Update heights\n" +
                        "        y.height = max(height(y.left), height(y.right)) + 1;\n" +
                        "        x.height = max(height(x.left), height(x.right)) + 1;\n" +
                        " \n" +
                        "        // Return new root\n" +
                        "        return x;\n" +
                        "    }\n" +
                        " \n" +
                        "    // A utility function to left rotate subtree rooted with x\n" +
                        "    // See the diagram given above.\n" +
                        "    Node leftRotate(Node x) {\n" +
                        "        Node y = x.right;\n" +
                        "        Node T2 = y.left;\n" +
                        " \n" +
                        "        // Perform rotation\n" +
                        "        y.left = x;\n" +
                        "        x.right = T2;\n" +
                        " \n" +
                        "        //  Update heights\n" +
                        "        x.height = max(height(x.left), height(x.right)) + 1;\n" +
                        "        y.height = max(height(y.left), height(y.right)) + 1;\n" +
                        " \n" +
                        "        // Return new root\n" +
                        "        return y;\n" +
                        "    }\n" +
                        " \n" +
                        "    // Get Balance factor of node N\n" +
                        "    int getBalance(Node N) {\n" +
                        "        if (N == null)\n" +
                        "            return 0;\n" +
                        " \n" +
                        "        return height(N.left) - height(N.right);\n" +
                        "    }\n" +
                        " \n" +
                        "    Node insert(Node node, int key) {\n" +
                        " \n" +
                        "        /* 1.  Perform the normal BST insertion */\n" +
                        "        if (node == null)\n" +
                        "            return (new Node(key));\n" +
                        " \n" +
                        "        if (key < node.key)\n" +
                        "            node.left = insert(node.left, key);\n" +
                        "        else if (key > node.key)\n" +
                        "            node.right = insert(node.right, key);\n" +
                        "        else // Duplicate keys not allowed\n" +
                        "            return node;\n" +
                        " \n" +
                        "        /* 2. Update height of this ancestor node */\n" +
                        "        node.height = 1 + max(height(node.left),\n" +
                        "                              height(node.right));\n" +
                        " \n" +
                        "        /* 3. Get the balance factor of this ancestor\n" +
                        "              node to check whether this node became\n" +
                        "              unbalanced */\n" +
                        "        int balance = getBalance(node);\n" +
                        " \n" +
                        "        // If this node becomes unbalanced, then there\n" +
                        "        // are 4 cases Left Left Case\n" +
                        "        if (balance > 1 && key < node.left.key)\n" +
                        "            return rightRotate(node);\n" +
                        " \n" +
                        "        // Right Right Case\n" +
                        "        if (balance < -1 && key > node.right.key)\n" +
                        "            return leftRotate(node);\n" +
                        " \n" +
                        "        // Left Right Case\n" +
                        "        if (balance > 1 && key > node.left.key) {\n" +
                        "            node.left = leftRotate(node.left);\n" +
                        "            return rightRotate(node);\n" +
                        "        }\n" +
                        " \n" +
                        "        // Right Left Case\n" +
                        "        if (balance < -1 && key < node.right.key) {\n" +
                        "            node.right = rightRotate(node.right);\n" +
                        "            return leftRotate(node);\n" +
                        "        }\n" +
                        " \n" +
                        "        /* return the (unchanged) node pointer */\n" +
                        "        return node;\n" +
                        "    }\n" +
                        " \n" +
                        "    // A utility function to print preorder traversal\n" +
                        "    // of the tree.\n" +
                        "    // The function also prints height of every node\n" +
                        "    void preOrder(Node node) {\n" +
                        "        if (node != null) {\n" +
                        "            System.out.print(node.key + \" \");\n" +
                        "            preOrder(node.left);\n" +
                        "            preOrder(node.right);\n" +
                        "        }\n" +
                        "    }\n" +
                        " \n" +
                        "    public static void main(String[] args) {\n" +
                        "        AVLTree tree = new AVLTree();\n" +
                        " \n" +
                        "        /* Constructing tree given in the above figure */\n" +
                        "        tree.root = tree.insert(tree.root, 10);\n" +
                        "        tree.root = tree.insert(tree.root, 20);\n" +
                        "        tree.root = tree.insert(tree.root, 30);\n" +
                        "        tree.root = tree.insert(tree.root, 40);\n" +
                        "        tree.root = tree.insert(tree.root, 50);\n" +
                        "        tree.root = tree.insert(tree.root, 25);\n" +
                        " \n" +
                        "        /* The constructed AVL Tree would be\n" +
                        "             30\n" +
                        "            /  \\\n" +
                        "          20   40\n" +
                        "         /  \\     \\\n" +
                        "        10  25    50\n" +
                        "        */\n" +
                        "        System.out.println(\"Preorder traversal\" +\n" +
                        "                        \" of constructed tree is : \");\n" +
                        "        tree.preOrder(tree.root);\n" +
                        "    }\n" +
                        "} }" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "// Java program for DELETION in AVL Tree\n" +
                " \n" +
                "class Node\n" +
                "{\n" +
                "    int key, height;\n" +
                "    Node left, right;\n" +
                " \n" +
                "    Node(int d)\n" +
                "    {\n" +
                "        key = d;\n" +
                "        height = 1;\n" +
                "    }\n" +
                "}\n" +
                " \n" +
                "class AVLTree\n" +
                "{\n" +
                "    Node root;\n" +
                " \n" +
                "    // A utility function to get height of the tree\n" +
                "    int height(Node N)\n" +
                "    {\n" +
                "        if (N == null)\n" +
                "             return 0;\n" +
                "         return N.height;\n" +
                "    }\n" +
                " \n" +
                "    // A utility function to get maximum of two integers\n" +
                "    int max(int a, int b)\n" +
                "    {\n" +
                "        return (a > b) ? a : b;\n" +
                "    }\n" +
                " \n" +
                "    // A utility function to right rotate subtree rooted with y\n" +
                "    // See the diagram given above.\n" +
                "    Node rightRotate(Node y)\n" +
                "    {\n" +
                "        Node x = y.left;\n" +
                "        Node T2 = x.right;\n" +
                " \n" +
                "        // Perform rotation\n" +
                "        x.right = y;\n" +
                "        y.left = T2;\n" +
                " \n" +
                "        // Update heights\n" +
                "        y.height = max(height(y.left), height(y.right)) + 1;\n" +
                "        x.height = max(height(x.left), height(x.right)) + 1;\n" +
                " \n" +
                "        // Return new root\n" +
                "        return x;\n" +
                "    }\n" +
                " \n" +
                "    // A utility function to left rotate subtree rooted with x\n" +
                "    // See the diagram given above.\n" +
                "    Node leftRotate(Node x)\n" +
                "    {\n" +
                "        Node y = x.right;\n" +
                "        Node T2 = y.left;\n" +
                " \n" +
                "        // Perform rotation\n" +
                "        y.left = x;\n" +
                "        x.right = T2;\n" +
                " \n" +
                "        //  Update heights\n" +
                "        x.height = max(height(x.left), height(x.right)) + 1;\n" +
                "        y.height = max(height(y.left), height(y.right)) + 1;\n" +
                " \n" +
                "        // Return new root\n" +
                "        return y;\n" +
                "    }\n" +
                " \n" +
                "    // Get Balance factor of node N\n" +
                "    int getBalance(Node N)\n" +
                "    {\n" +
                "        if (N == null)\n" +
                "            return 0;\n" +
                "        return height(N.left) - height(N.right);\n" +
                "    }\n" +
                " \n" +
                "    Node insert(Node node, int key)\n" +
                "    {\n" +
                "        /* 1.  Perform the normal BST rotation */\n" +
                "        if (node == null)\n" +
                "            return (new Node(key));\n" +
                " \n" +
                "        if (key < node.key)\n" +
                "            node.left = insert(node.left, key);\n" +
                "        else if (key > node.key)\n" +
                "            node.right = insert(node.right, key);\n" +
                "        else // Equal keys not allowed\n" +
                "            return node;\n" +
                " \n" +
                "        /* 2. Update height of this ancestor node */\n" +
                "        node.height = 1 + max(height(node.left),\n" +
                "                              height(node.right));\n" +
                " \n" +
                "        /* 3. Get the balance factor of this ancestor\n" +
                "           node to check whether this node became\n" +
                "           Wunbalanced */\n" +
                "        int balance = getBalance(node);\n" +
                " \n" +
                "        // If this node becomes unbalanced, then\n" +
                "        // there are 4 cases Left Left Case\n" +
                "        if (balance > 1 && key < node.left.key)\n" +
                "            return rightRotate(node);\n" +
                " \n" +
                "        // Right Right Case\n" +
                "        if (balance < -1 && key > node.right.key)\n" +
                "            return leftRotate(node);\n" +
                " \n" +
                "        // Left Right Case\n" +
                "        if (balance > 1 && key > node.left.key)\n" +
                "        {\n" +
                "            node.left = leftRotate(node.left);\n" +
                "            return rightRotate(node);\n" +
                "        }\n" +
                " \n" +
                "        // Right Left Case\n" +
                "        if (balance < -1 && key < node.right.key)\n" +
                "        {\n" +
                "            node.right = rightRotate(node.right);\n" +
                "            return leftRotate(node);\n" +
                "        }\n" +
                " \n" +
                "        /* return the (unchanged) node pointer */\n" +
                "        return node;\n" +
                "    }\n" +
                " \n" +
                "    /* Given a non-empty binary search tree, return the\n" +
                "       node with minimum key value found in that tree.\n" +
                "       Note that the entire tree does not need to be\n" +
                "       searched. */\n" +
                "    Node minValueNode(Node node)\n" +
                "    {\n" +
                "        Node current = node;\n" +
                " \n" +
                "        /* loop down to find the leftmost leaf */\n" +
                "        while (current.left != null)\n" +
                "           current = current.left;\n" +
                " \n" +
                "        return current;\n" +
                "    }\n" +
                " \n" +
                "    Node deleteNode(Node root, int key)\n" +
                "    {\n" +
                "        // STEP 1: PERFORM STANDARD BST DELETE\n" +
                "        if (root == null)\n" +
                "            return root;\n" +
                " \n" +
                "        // If the key to be deleted is smaller than\n" +
                "        // the root's key, then it lies in left subtree\n" +
                "        if (key < root.key)\n" +
                "            root.left = deleteNode(root.left, key);\n" +
                " \n" +
                "        // If the key to be deleted is greater than the\n" +
                "        // root's key, then it lies in right subtree\n" +
                "        else if (key > root.key)\n" +
                "            root.right = deleteNode(root.right, key);\n" +
                " \n" +
                "        // if key is same as root's key, then this is the node\n" +
                "        // to be deleted\n" +
                "        else\n" +
                "        {\n" +
                " \n" +
                "            // node with only one child or no child\n" +
                "            if ((root.left == null) || (root.right == null))\n" +
                "            {\n" +
                "                Node temp = null;\n" +
                "                if (temp == root.left)\n" +
                "                    temp = root.right;\n" +
                "                else\n" +
                "                    temp = root.left;\n" +
                " \n" +
                "                // No child case\n" +
                "                if (temp == null)\n" +
                "                {\n" +
                "                    temp = root;\n" +
                "                    root = null;\n" +
                "                }\n" +
                "                else   // One child case\n" +
                "                    root = temp; // Copy the contents of\n" +
                "                                 // the non-empty child\n" +
                "            }\n" +
                "            else\n" +
                "            {\n" +
                " \n" +
                "                // node with two children: Get the inorder\n" +
                "                // successor (smallest in the right subtree)\n" +
                "                Node temp = minValueNode(root.right);\n" +
                " \n" +
                "                // Copy the inorder successor's data to this node\n" +
                "                root.key = temp.key;\n" +
                " \n" +
                "                // Delete the inorder successor\n" +
                "                root.right = deleteNode(root.right, temp.key);\n" +
                "            }\n" +
                "        }\n" +
                " \n" +
                "        // If the tree had only one node then return\n" +
                "        if (root == null)\n" +
                "            return root;\n" +
                " \n" +
                "        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE\n" +
                "        root.height = max(height(root.left), height(root.right)) + 1;\n" +
                " \n" +
                "        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether\n" +
                "        //  this node became unbalanced)\n" +
                "        int balance = getBalance(root);\n" +
                " \n" +
                "        // If this node becomes unbalanced, then there are 4 cases\n" +
                "        // Left Left Case\n" +
                "        if (balance > 1 && getBalance(root.left) >= 0)\n" +
                "            return rightRotate(root);\n" +
                " \n" +
                "        // Left Right Case\n" +
                "        if (balance > 1 && getBalance(root.left) < 0)\n" +
                "        {\n" +
                "            root.left = leftRotate(root.left);\n" +
                "            return rightRotate(root);\n" +
                "        }\n" +
                " \n" +
                "        // Right Right Case\n" +
                "        if (balance < -1 && getBalance(root.right) <= 0)\n" +
                "            return leftRotate(root);\n" +
                " \n" +
                "        // Right Left Case\n" +
                "        if (balance < -1 && getBalance(root.right) > 0)\n" +
                "        {\n" +
                "            root.right = rightRotate(root.right);\n" +
                "            return leftRotate(root);\n" +
                "        }\n" +
                " \n" +
                "        return root;\n" +
                "    }\n" +
                " \n" +
                "    // A utility function to print preorder traversal of\n" +
                "    // the tree. The function also prints height of every\n" +
                "    // node\n" +
                "    void preOrder(Node node)\n" +
                "    {\n" +
                "        if (node != null)\n" +
                "        {\n" +
                "            System.out.print(node.key + \" \");\n" +
                "            preOrder(node.left);\n" +
                "            preOrder(node.right);\n" +
                "        }\n" +
                "    }\n" +
                " \n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        AVLTree tree = new AVLTree();\n" +
                " \n" +
                "        /* Constructing tree given in the above figure */\n" +
                "        tree.root = tree.insert(tree.root, 9);\n" +
                "        tree.root = tree.insert(tree.root, 5);\n" +
                "        tree.root = tree.insert(tree.root, 10);\n" +
                "        tree.root = tree.insert(tree.root, 0);\n" +
                "        tree.root = tree.insert(tree.root, 6);\n" +
                "        tree.root = tree.insert(tree.root, 11);\n" +
                "        tree.root = tree.insert(tree.root, -1);\n" +
                "        tree.root = tree.insert(tree.root, 1);\n" +
                "        tree.root = tree.insert(tree.root, 2);\n" +
                " \n" +
                "        /* The constructed AVL Tree would be\n" +
                "           9\n" +
                "          /  \\\n" +
                "         1    10\n" +
                "        /  \\    \\\n" +
                "        0    5    11\n" +
                "        /    /  \\\n" +
                "        -1   2    6\n" +
                "         */\n" +
                "        System.out.println(\"Preorder traversal of \"+\n" +
                "                            \"constructed tree is : \");\n" +
                "        tree.preOrder(tree.root);\n" +
                " \n" +
                "        tree.root = tree.deleteNode(tree.root, 10);\n" +
                " \n" +
                "        /* The AVL Tree after deletion of 10\n" +
                "           1\n" +
                "          /  \\\n" +
                "         0    9\n" +
                "        /     / \\\n" +
                "        -1    5   11\n" +
                "        /  \\\n" +
                "        2    6\n" +
                "         */\n" +
                "        System.out.println(\"\");\n" +
                "        System.out.println(\"Preorder traversal after \"+\n" +
                "                           \"deletion of 10 :\");\n" +
                "        tree.preOrder(tree.root);\n" +
                "    }\n" +
                "}";

        code.setText(codes);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AVLCode.this, AvlTreeActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(AVLCode.this, HomeActivity.class);
            startActivity(intent);
            finish();

            // Handle the camera action
        } else if (id == R.id.code) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.execution) {
            Intent intent = new Intent(AVLCode.this, AvlTreeActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.details) {
            Intent intent = new Intent(AVLCode.this, AVLDetails.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
