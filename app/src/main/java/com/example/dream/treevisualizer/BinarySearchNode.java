package com.example.dream.treevisualizer;


import android.util.Log;

/* compiled from: BinarySearchTree */
class BinarySearchNode {
    public int black;
    public int data;
    public double fx;
    public double fy;
    public double height;
    public boolean highlight;
    public boolean ileaf;
    public BinarySearchNode left;
    public double leftWidth;
    public BinarySearchNode parent;
    BinarySearchTree rbt;
    public BinarySearchNode right;
    public double rightWidth;
    public double oldX;
    public double oldY;
    private static final String TAG = "BinarySearchNode";

    public BinarySearchNode() {
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

    public BinarySearchNode(int data, BinarySearchTree rbt) {
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

    public BinarySearchNode findUncle(BinarySearchNode node) {
        if (node.parent == null) {
            return null;
        }
        BinarySearchNode par = node.parent;
        if (par.parent == null) {
            return null;
        }
        BinarySearchNode grandPar = par.parent;
        if (grandPar.left == par) {
            return grandPar.right;
        }
        return grandPar.left;
    }

    public int black(BinarySearchNode node) {
        if (node == null) {
            return 1;
        }
        return node.black;
    }

    public void LeftNullLeaf(BinarySearchNode node) {
        node.left = new BinarySearchNode();
        node.left.ileaf = true;
        node.left.black = 1;
    }

    public void RightNullLeaf(BinarySearchNode node) {
        node.right = new BinarySearchNode();
        node.right.ileaf = true;
        node.right.black = 1;
    }

    public void putNullLeaves(BinarySearchNode node) {
        LeftNullLeaf(node);
        RightNullLeaf(node);
    }

    public BinarySearchNode RotateRight(BinarySearchNode node) {
        BinarySearchNode B = node;
        BinarySearchNode A = node.left;
        BinarySearchNode t2 = A.right;
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

    public BinarySearchNode RotateLeft(BinarySearchNode node) {
        BinarySearchNode A = node;
        BinarySearchNode B = node.right;
        BinarySearchNode t2 = B.left;
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

    public double getHeight(BinarySearchNode node) {
        if (node == null) {
            return 0.0d;
        }
        return node.height;
    }

    public void resetHeight(BinarySearchNode node) {
        if (node != null) {
            if (node.height != Math.max(getHeight(node.left), getHeight(node.right)) + 1.0d) {
                node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1.0d;
            }
        }
    }
//    int maxDepth(BinarySearchNode node)
//    {
//        if (node == null)
//            return 0;
//        else
//        {
//            /* compute the depth of each subtree */
//            node.left.height = maxDepth(node.left);
//            node.right.height = maxDepth(node.right);
//
//            /* use the larger one */
//            if (node.left.height  > node.right.height )
//                return ((int)node.left.height  + 1);
//            else
//                return ((int)node.right.height + 1);
//        }
//    }
int maxDepth(BinarySearchNode node)
{
    if (node == null)
        return 0;
    else
    {
            /* compute the depth of each subtree */
        int lDepth = maxDepth(node.left);
        int rDepth = maxDepth(node.right);

            /* use the larger one */
        if (lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }
}


    public void insert(BinarySearchNode elem, BinarySearchNode node) {
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
//                fixDoubleRed(elem);
                return;
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
//            fixDoubleRed(elem);
        } else {
            insert(elem, node.right);
        }
    }


    public boolean delete(BinarySearchNode root,int id){
        if (root == null || root.ileaf) {
            this.rbt.rbta.print = "Key " + id + " doesn't exist in the tree";
            return false;
        }
        BinarySearchNode parent = root;
        BinarySearchNode current = root;
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
            Log.d(TAG, "delete: IN TWO NODE");

            //now we have found the minimum element in the right sub tree
            BinarySearchNode successor	 = getSuccessor(current);
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

            resizeTree();
        }
        Log.d(TAG, "delete: IN TWO NODE2222");

        return true;
    }

    public BinarySearchNode getSuccessor(BinarySearchNode deleleNode){
        BinarySearchNode successsor =null;
        BinarySearchNode successsorParent =null;
        BinarySearchNode current = deleleNode.right;
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



    /*
    public boolean delete(BinarySearchNode root,int id){
        BinarySearchNode parent = root;
        BinarySearchNode current = root;
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
            if(current.ileaf){
                return false;
            }
        }
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
        if(current.left.ileaf && current.right.ileaf){
            if(current==root){
                root = null;
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
        else if(current.right.ileaf){
            if(current==root){
                root = current.left;
            }else if(isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
            resizeTree();

        }
        else if(current.left.ileaf){
            if(current==root){
                root = current.right;
            }else if(isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
            resizeTree();

        }else if(!current.left.ileaf && !current.right.ileaf){

            //now we have found the minimum element in the right sub tree
            BinarySearchNode successor	 = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;

           resizeTree();
        }
        return true;
    }

    public BinarySearchNode getSuccessor(BinarySearchNode deleleNode){
        BinarySearchNode successsor =null;
        BinarySearchNode successsorParent =null;
        BinarySearchNode current = deleleNode.right;
        while(current.ileaf){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if(successsor!=deleleNode.right){
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }
*/
    /*
    public BinarySearchNode delete(BinarySearchNode root, int k)
    {
        BinarySearchNode p, p2, n;
        root.highlight = true;
        this.rbt.rbta.dt.resume();
        try {
            Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
        } catch (Exception e) {
        }
        this.rbt.rbta.dt.pause();

        root.highlight = false;

        if (root.data == k)
        {
            BinarySearchNode lt, rt;
            lt = root.left;
            rt = root.right;
            if (lt == null && rt == null)
                return null;
            else if (lt == null)
            {
                p = rt;
                resizeTree();

                return p;
            }
            else if (rt == null)
            {
                p = lt;
                resizeTree();

                return p;
            }
            else
            {
                p2 = rt;
                p = rt;
                while (p.left != null)
                    p = p.left;
                p.left=lt;
                resizeTree();
                return p2;
            }
        }
        if (k < root.data)
        {
            n = delete(root.left, k);
            root.left=n;
        }
        else
        {
            n = delete(root.right, k);
            root.right=n;
        }
        resizeTree();

        return root;
    }
*/
/*
    public void delete(BinarySearchNode node, int valueToDelete) {
        boolean leftchild = false;
        BinarySearchNode r3;
        if (node == null || node.ileaf) {
            this.rbt.rbta.print = "Key " + valueToDelete + " doesn't exist in the tree";
            return;
        }
        node.highlight = true;
        this.rbt.rbta.dt.resume();
        try {
            Thread.sleep((long) (1000 - ((int) (this.rbt.rbta.speed * 10.0d))));
        } catch (Exception e) {
        }
        this.rbt.rbta.dt.pause();
        node.highlight = false;
        if (node.parent != null) {
            leftchild = node.parent.left == node;
        }
        if (valueToDelete == node.data) {
            boolean needFix;
            this.rbt.rbta.rbn = node;
            if (node.black > 0) {
                needFix = true;
            } else {
                needFix = false;
            }
            if ((node.left == null || node.left.ileaf) && (node.right == null || node.right.ileaf)) {
                if (leftchild && node.parent != null) {
                    node.parent.left = null;
                    resizeTree();
                    if (needFix) {
                        fixLeftNull(node.parent);
                        return;
                    }
                    LeftNullLeaf(node.parent);
                    resizeTree();
                } else if (node.parent != null) {
                    node.parent.right = null;
                    resizeTree();
                    if (needFix) {
                        fixRightNull(node.parent);
                        return;
                    }
                    RightNullLeaf(node.parent);
                    resizeTree();
                } else {
                    this.rbt.root = null;
                }
            } else if (node.left == null || node.left.ileaf) {
                if (node.left != null) {
                    node.left = null;
                }
                if (node.parent != null) {
                    if (leftchild) {
                        node.parent.left = node.right;
                        if (needFix) {
                            r3 = node.parent.left;
                            r3.black++;
                            fixExtraBlack(node.parent.left);
                        }
                    } else {
                        node.parent.right = node.right;
                        if (needFix) {
                            r3 = node.parent.right;
                            r3.black++;
                            fixExtraBlack(node.parent.right);
                        }
                    }
                    node.right.parent = node.parent;
                } else {
                    this.rbt.root = node.right;
                    this.rbt.root.parent = null;
                    if (this.rbt.root.black == 0) {
                        this.rbt.root.black = 1;
                    }
                }
                resizeTree();
            } else if (node.right == null || node.right.ileaf) {
                if (node.right != null) {
                    node.right = null;
                }
                if (node.parent != null) {
                    if (leftchild) {
                        node.parent.left = node.left;
                        if (needFix) {
                            r3 = node.parent.left;
                            r3.black++;
                            fixExtraBlack(node.parent.left);
                            resizeTree();
                        } else {
                            resizeTree();
                        }
                    } else {
                        node.parent.right = node.left;
                        if (needFix) {
                            r3 = node.parent.right;
                            r3.black++;
                            fixExtraBlack(node.parent.left);
                            resizeTree();
                        } else {
                            resizeTree();
                        }
                    }
                    node.left.parent = node.parent;
                    return;
                }
                this.rbt.root = node.left;
                this.rbt.root.parent = null;
                if (this.rbt.root.black == 0) {
                    this.rbt.root.black = 1;
                }
            } else {
                BinarySearchNode redBlackNode = node;
                redBlackNode = node.left;
                while (redBlackNode.right != null && !redBlackNode.right.ileaf) {
                    redBlackNode = redBlackNode.right;
                }
                if (redBlackNode.right != null) {
                    redBlackNode.right = null;
                }
                node.data = redBlackNode.data;
                if (redBlackNode.black > 0) {
                    needFix = true;
                } else {
                    needFix = false;
                }
                if (redBlackNode.left == null) {
                    if (redBlackNode.parent != node) {
                        redBlackNode.parent.right = null;
                        resizeTree();
                        if (needFix) {
                            fixRightNull(redBlackNode.parent);
                        }
                    } else {
                        node.left = null;
                        resizeTree();
                        if (needFix) {
                            fixLeftNull(redBlackNode.parent);
                        }
                    }
                } else if (redBlackNode.parent != node) {
                    redBlackNode.parent.right = redBlackNode.left;
                    redBlackNode.left.parent = redBlackNode.parent;
                    resizeTree();
                    if (needFix) {
                        r3 = redBlackNode.left;
                        r3.black++;
                        fixExtraBlack(redBlackNode.left);
                    }
                } else {
                    node.left = redBlackNode.left;
                    redBlackNode.left.parent = node;
                    resizeTree();
                    if (needFix) {
                        r3 = redBlackNode.left;
                        r3.black++;
                        fixExtraBlack(redBlackNode.left);
                    }
                }
                r3 = redBlackNode.parent;
            }
        } else if (valueToDelete < node.data) {
            delete(node.left, valueToDelete);
        } else {
            delete(node.right, valueToDelete);
        }
    }
*/
    public void updatePositions(BinarySearchNode node) {
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

    public void setNewPositions(BinarySearchNode node, double xPosition, double yPosition, int side) {
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

    public double resizeWidths(BinarySearchNode node) {
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

    public boolean search(BinarySearchNode node, int k) {
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
        BinarySearchNode node;
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