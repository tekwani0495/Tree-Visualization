package com.example.dream.treevisualizer;


import android.util.Log;

/* compiled from: AvlTree */
class AvlNode {
    public int black;
    public int data;
    public double fx;
    public double fy;
    public double height;
    public boolean highlight;
    public boolean ileaf;
    public AvlNode left;
    public double leftWidth;
    public AvlNode parent;
    AvlTree rbt;
    public AvlNode right;
    public double rightWidth;
    public double oldX;
    public double oldY;
    private static final String TAG = "AvlNode";

    public AvlNode() {
        this.leftWidth = 0.0d;
        this.rightWidth = 0.0d;
        this.oldX = 0.0d;
        this.oldY = 0.0d;
        this.fx = 0.0d;
        this.fy = 0.0d;
        this.data = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.black = 0;
        this.ileaf = false;
    }

    public AvlNode(int data, AvlTree rbt) {
        this.leftWidth = 0.0d;
        this.rightWidth = 0.0d;
        this.data = data;
        this.rbt = rbt;
        this.oldX = 0.0d;
        this.oldY = 0.0d;
        this.fx = 0.0d;
        this.fy = 0.0d;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.black = 0;
        this.ileaf = false;
    }

    public AvlNode findUncle(AvlNode node) {
        if (node.parent == null) {
            return null;
        }
        AvlNode par = node.parent;
        if (par.parent == null) {
            return null;
        }
        AvlNode grandPar = par.parent;
        if (grandPar.left == par) {
            return grandPar.right;
        }
        return grandPar.left;
    }

    public int black(AvlNode node) {
        if (node == null) {
            return 1;
        }
        return node.black;
    }

    public void LeftNullLeaf(AvlNode node) {
        node.left = new AvlNode();
        node.left.ileaf = true;
        node.left.black = 1;
    }

    public void RightNullLeaf(AvlNode node) {
        node.right = new AvlNode();
        node.right.ileaf = true;
        node.right.black = 1;
    }

    public void putNullLeaves(AvlNode node) {
        LeftNullLeaf(node);
        RightNullLeaf(node);
    }

    public AvlNode RotateRight(AvlNode node) {
        AvlNode B = node;
        AvlNode A = node.left;
        AvlNode t2 = A.right;
        if (t2 != null) {
            t2.parent = B;
        }
        A.parent = B.parent;
        if (this.rbt.root == B) {
            this.rbt.root = A;
        } else if (B.isLeftChild()) {
            B.parent.left = A;
        } else {
            B.parent.right = A;
        }
        A.right = B;
        B.parent = A;
        B.left = t2;
        resetHeight(B);
        resetHeight(A);
        resizeTree();
        return A;
    }

    public AvlNode RotateLeft(AvlNode node) {
        AvlNode A = node;
        AvlNode B = node.right;
        AvlNode t2 = B.left;
        if (t2 != null) {
            t2.parent = A;
        }
        B.parent = A.parent;
        if (this.rbt.root == A) {
            this.rbt.root = B;
        } else if (A.isLeftChild()) {
            A.parent.left = B;
        } else {
            A.parent.right = B;
        }
        B.left = A;
        A.parent = B;
        A.right = t2;
        resetHeight(A);
        resetHeight(B);
        resizeTree();
        return B;
    }

    public double getHeight(AvlNode node) {
        if (node.ileaf) {
            return 0.0d;
        }
        return node.height;
    }

    public void resetHeight(AvlNode node) {
        if (!node.ileaf) {
            if (node.height != Math.max(getHeight(node.left), getHeight(node.right)) + 1.0d) {
                node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1.0d;
            }
        }
    }



    double max(double a, double b)
    {
        return (a > b) ? a : b;
    }
    // Get Balance factor of node N
    double getBalance(AvlNode N)
    {
        if (N.ileaf)
            return 0;
        return getHeight(N.left) - getHeight(N.right);
    }






    public boolean deleteNode(AvlNode root,int id){
        if (root == null || root.ileaf) {
            this.rbt.rbta.print = "Key " + id + " doesn't exist in the tree";
            return false;
        }
        AvlNode parent = root;
        AvlNode current = root;
        AvlNode ancestor ;
        boolean isLeftChild = false;
        while(current.data!=id){

            current.highlight = true;
            this.rbt.rbta.dt.resume();
            try {
                Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
            } catch (Exception e) {
            }
            this.rbt.rbta.dt.pause();
            current.highlight = false;


            parent = current;
            if(current.data>id){
                isLeftChild = true;
                current = current.left;
            }else{
                isLeftChild = false;
                current = current.right;
            }
            if(current.ileaf || current==null){
                return false;
            }
        }
        ancestor=current.parent;
//        rbt.rbta.rbn=current;


        current.highlight = true;
        this.rbt.rbta.dt.resume();
        try {
            Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
        } catch (Exception e) {
        }
        this.rbt.rbta.dt.pause();
        current.highlight = false;

        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if((current.left == null || current.left.ileaf) && (current.right == null || current.right.ileaf)){
            if(current==root){
                rbt.root = null;
                return true;
            }
            if(isLeftChild ==true){
                parent.left = null;
                LeftNullLeaf(parent);
            }else{
                parent.right = null;
                RightNullLeaf(parent);
            }
            resizeTree();

        }
        //Case 2 : if node to be deleted has only one child
        else if(current.right.ileaf || current.right==null){
            if(current==root){
                root = current.left;
                rbt.root = current.left;
                rbt.root.parent=null;

            }else if(isLeftChild){

                parent.left = current.left;
                current.left.parent=parent;
            }else{

                parent.right = current.left;
                current.left.parent=parent;

            }
            resizeTree();

        }
        else if(current.left.ileaf || current.left==null){
            if(current==root){
                root = current.right;
                rbt.root = current.right;
                rbt.root.parent=null;
            }else if(isLeftChild){

                parent.left = current.right;
                current.right.parent=parent;

            }else{
                parent.right = current.right;
                current.right.parent=parent;

            }
            resizeTree();

        }else if(!current.left.ileaf && !current.right.ileaf || current.left!=null && current.right!=null ){
//            Log.d(TAG, "delete: IN TWO NODE");

            //now we have found the minimum element in the right sub tree
            AvlNode successor	 = getSuccessor(current);
            if(current==root){
//                root.parent=successor;
                root = successor;
//                successor.parent=root;
                rbt.root = successor;
                successor.parent=null;

            }else if(isLeftChild){
//                successor.parent=parent.left;
                parent.left = successor;
                successor.parent=parent;
            }else{
//                successor.parent=parent.right;
                parent.right = successor;
                successor.parent=parent;
            }
//            current.left.parent=successor.left;

            successor.left = current.left;
            current.left.parent=successor;
            ancestor=successor;

            resizeTree();
        }
//        Log.d(TAG, "delete: IN TWO NODE2222");




        while(ancestor!=null){
            // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
            ancestor.height = max(getHeight(ancestor.left), getHeight(ancestor.right)) + 1;

            // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
            //  this node became unbalanced)
            Double balance = getBalance(ancestor);
            Log.d(TAG, "delete: "+ancestor.data+" "+balance);
            Log.d(TAG, "delete: in ancestor");

            // If this node becomes unbalanced, then there are 4 cases
            // Left Left Case
            if(balance >1 || balance <-1) {

                if (balance > 1 && getBalance(ancestor.left) >= 0)
                    RotateRight(ancestor);

                // Left Right Case
                if (balance > 1 && getBalance(ancestor.left) < 0) {
                    ancestor.left = RotateLeft(ancestor.left);
                    RotateRight(ancestor);
                }

                // Right Right Case
                if (balance < -1 && getBalance(ancestor.right) <= 0)
                    RotateLeft(ancestor);

                // Right Left Case
                if (balance < -1 && getBalance(ancestor.right) > 0) {
                    ancestor.right = RotateRight(ancestor.right);
                    RotateLeft(ancestor);
                }
                resizeTree();
            }

            ancestor=ancestor.parent;
        }

        return true;
    }

    public AvlNode getSuccessor(AvlNode deleleNode){
        AvlNode successsor =null;
        AvlNode successsorParent =null;
        AvlNode current = deleleNode.right;
//        try {
        while (!current.ileaf && current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
            Log.d(TAG, "delete: IN TWO NODE333");

        }
//        }
//        catch(Exception o1){}

        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if(successsor!=deleleNode.right){

//            successsor.right.arrow.setVisibility(View.INVISIBLE);
//            arrow(successsor.right,0);
//            preorder(successsor.right,0);
//            successsor.right.arrow=successsor.arrow;


            successsorParent.left = successsor.right;
            successsor.right.parent=successsorParent;
            successsor.right = deleleNode.right;
            deleleNode.right.parent=successsor;
        }
        else{
//            successsor.arrow.setVisibility(View.INVISIBLE);
//            arrow(successsor.right,0);
//            preorder(successsor.right,0);
//            successsor.arrow=deleleNode.arrow;

        }
        return successsor;
    }



//
//    AvlNode insert(AvlNode node, int key)
//    {
//        /* 1.  Perform the normal BST rotation */
//        if (node == null)
//            return (new AvlNode(key));
//
//        if (key < node.data)
//            node.left = insert(node.left, key);
//        else if (key > node.data)
//            node.right = insert(node.right, key);
//        else // Equal keys not allowed
//            return node;
//
//        /* 2. Update height of this ancestor node */
//        node.height = 1 + max(getHeight(node.left),
//                getHeight(node.right));
//
//        /* 3. Get the balance factor of this ancestor
//           node to check whether this node became
//           Wunbalanced */
//        double balance = getBalance(node);
//
//        // If this node becomes unbalanced, then
//        // there are 4 cases Left Left Case
//        if (balance > 1 && key < node.left.data)
//            return RotateRight(node);
//
//        // Right Right Case
//        if (balance < -1 && key > node.right.data)
//            return RotateLeft(node);
//
//        // Left Right Case
//        if (balance > 1 && key > node.left.data)
//        {
//            node.left = RotateLeft(node.left);
//            return RotateRight(node);
//        }
//
//        // Right Left Case
//        if (balance < -1 && key < node.right.data)
//        {
//            node.right = RotateRight(node.right);
//            return RotateLeft(node);
//        }
//
//        /* return the (unchanged) node pointer */
//        return node;
//    }

    /* Given a non-empty binary search tree, return the
       node with minimum key value found in that tree.
       Note that the entire tree does not need to be
       searched. */
    AvlNode minValueNode(AvlNode node)
    {
        AvlNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

//
//    AvlNode deleteNode(AvlNode root, int key) {
//        resizeTree();
//
//        root.highlight = true;
//        this.rbt.rbta.dt.resume();
//        try {
//            Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
//        } catch (Exception e) {
//        }
//        this.rbt.rbta.dt.pause();
//        root.highlight = false;
//
//
//
//
//        // STEP 1: PERFORM STANDARD BST DELETE
//        if (root == null)
//            return root;
//
//        // If the key to be deleted is smaller than
//        // the root's key, then it lies in left subtree
//        if (key < root.data)
//            root.left = deleteNode(root.left, key);
//
//            // If the key to be deleted is greater than the
//            // root's key, then it lies in right subtree
//        else if (key > root.data)
//            root.right = deleteNode(root.right, key);
//
//            // if key is same as root's key, then this is the node
//            // to be deleted
//        else {
//
//            root.highlight = true;
//            this.rbt.rbta.dt.resume();
//            try {
//                Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
//            } catch (Exception e) {
//            }
//            this.rbt.rbta.dt.pause();
//            root.highlight = false;
//
//
//            // node with only one child or no child
//            if ((root.left.ileaf) || (root.right.ileaf)) {
//                AvlNode temp = null;
//                if (temp == root.left)
//                    temp = root.right;
//                else
//                    temp = root.left;
//
//                // No child case
//                if (temp.ileaf) {
//                    temp = root;
//                    root = null;
//                } else   // One child case
//                    root = temp; // Copy the contents of
//                // the non-empty child
//            } else {
//
//                // node with two children: Get the inorder
//                // successor (smallest in the right subtree)
//                AvlNode temp = minValueNode(root.right);
//
//                // Copy the inorder successor's data to this node
//                root.data = temp.data;
//
//                // Delete the inorder successor
//                root.right = deleteNode(root.right, temp.data);
//            }
//        }
//
//        // If the tree had only one node then return
//        if (root.ileaf)
//            return root;
//
//        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
//        root.height = max(getHeight(root.left), getHeight(root.right)) + 1;
//
//        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
//        //  this node became unbalanced)
//        Double balance = getBalance(root);
//
//        // If this node becomes unbalanced, then there are 4 cases
//        // Left Left Case
//        if (balance > 1 && getBalance(root.left) >= 0)
//            return RotateRight(root);
//
//        // Left Right Case
//        if (balance > 1 && getBalance(root.left) < 0) {
//            root.left = RotateLeft(root.left);
//            return RotateRight(root);
//        }
//
//        // Right Right Case
//        if (balance < -1 && getBalance(root.right) <= 0)
//            return RotateLeft(root);
//
//        // Right Left Case
//        if (balance < -1 && getBalance(root.right) > 0) {
//            root.right = RotateRight(root.right);
//            return RotateLeft(root);
//        }
//        resizeTree();
//
//        return root;
//    }






    public AvlNode insert(AvlNode elem, AvlNode node) {
        Log.d(TAG, "insert: 000");

        node.highlight = true;
        this.rbt.rbta.dt.resume();
        try {
            Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
        } catch (Exception e) {
        }
        this.rbt.rbta.dt.pause();
        node.highlight = false;
        if (elem.data < node.data) {
            if (node.left == null || node.left.ileaf) {
                this.rbt.rbta.rbn = elem;

                node.left = elem;
                elem.parent = node;
                putNullLeaves(elem);
                resizeTree();

                return null;
            }
            insert(elem, node.left);
        } else if (node.right == null || node.right.ileaf) {
            this.rbt.rbta.rbn = elem;

            node.right = elem;
            elem.parent = node;
            elem.oldX = node.oldX + (this.rbt.delWidth / 2.0d);
            elem.oldY = node.oldY + this.rbt.delHeight;
            putNullLeaves(elem);
            resizeTree();

        } else {
            insert(elem, node.right);
        }
        Log.d(TAG, "insert: 111");

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(getHeight(node.left),
                getHeight(node.right));

        /* 3. Get the balance factor of this ancestor
           node to check whether this node became
           Wunbalanced */
        double balance = getBalance(node);

        if(balance >1 || balance < -1)

        // If this node becomes unbalanced, then
        // there are 4 cases Left Left Case
        if (balance > 1 && elem.data <= node.left.data)
            return RotateRight(node);

        // Right Right Case
        if (balance < -1 && elem.data >= node.right.data)
            return RotateLeft(node);

        // Left Right Case
        if (balance > 1 && elem.data >= node.left.data)
        {
            node.left = RotateLeft(node.left);
            return RotateRight(node);
        }

        // Right Left Case
        if (balance < -1 && elem.data <= node.right.data)
        {
            node.right = RotateRight(node.right);
            return RotateLeft(node);
        }

        /* return the (unchanged) node pointer */
        Log.d(TAG, "insert: 222");
        if(balance >1 || balance < -1)
            resizeTree();

        return node;


    }


    public void updatePositions(AvlNode node) {
        if (node != null) {
            node.oldX = node.fx;
            node.oldY = node.fy;
            updatePositions(node.left);
            updatePositions(node.right);
        }
    }

    public void resizeTree() {
        double startingPoint = this.rbt.startPositionX;
        resizeWidths(this.rbt.root);
        if (this.rbt.root != null) {
            if (this.rbt.root.leftWidth > startingPoint) {
                startingPoint = this.rbt.root.leftWidth;
            } else if (this.rbt.root.rightWidth > startingPoint) {
                startingPoint = Math.max(this.rbt.root.leftWidth, (2.0d * startingPoint) - this.rbt.root.rightWidth);
            }
            setNewPositions(this.rbt.root, startingPoint, this.rbt.startPositionY, 0);
            this.rbt.rbta.animate = true;
            this.rbt.rbta.dt.resume();
            int i = 0;
            while (this.rbt.rbta.animate && i < 1000 && !this.rbt.rbta.undo) {
                try {
                    Thread.sleep(10);
                    i++;
                } catch (Exception e) {
                }
            }
            this.rbt.rbta.undo = false;
            this.rbt.rbta.dt.pause();
            this.rbt.rbta.animate = false;
            updatePositions(this.rbt.root);
        }
    }

    public void setNewPositions(AvlNode node, double xPosition, double yPosition, int side) {
        if (node != null) {
            node.fy = yPosition;
            if (side == -1) {
                xPosition -= node.rightWidth;
            } else if (side == 1) {
                xPosition += node.leftWidth;
            }
            node.fx = xPosition;
            setNewPositions(node.left, xPosition, yPosition + this.rbt.delHeight, -1);
            setNewPositions(node.right, xPosition, yPosition + this.rbt.delHeight, 1);
        }
    }

    public double resizeWidths(AvlNode node) {
        if (node == null) {
            return 0.0d;
        }
        node.leftWidth = Math.max(resizeWidths(node.left), this.rbt.delWidth / 2.0d);
        node.rightWidth = Math.max(resizeWidths(node.right), this.rbt.delWidth / 2.0d);
        return node.leftWidth + node.rightWidth;
    }

    public boolean isLeftChild() {
        if (this.parent == null || this.parent.left == this) {
            return true;
        }
        return false;
    }

    public boolean search(AvlNode node, int k) {
        if (node == null) {
            return false;
        }
        node.highlight = true;
        this.rbt.rbta.dt.resume();
        try {
            Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
        } catch (Exception e) {
        }
        this.rbt.rbta.dt.pause();
        node.highlight = false;
        if (k == node.data) {
            this.rbt.rbta.rbn = node;
            return true;
        } else if (k > node.data && search(node.right, k)) {
            return true;
        } else {
            if (k >= node.data || !search(node.left, k)) {
                return false;
            }
            return true;
        }
    }

    public void order() {
        AvlNode node;
        while(rbt.rbta.linkedList.size()!=0) {
            node=rbt.rbta.linkedList.removeFirst();
            node.highlight = true;
            this.rbt.rbta.dt.resume();
            try {
                Thread.sleep((long) (1200 - ((int) (this.rbt.rbta.speed * 10.0d))));
            } catch (Exception e) {
            }
            this.rbt.rbta.dt.pause();
            node.highlight = false;
        }

    }
}