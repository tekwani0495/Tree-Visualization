package com.example.dream.treevisualizer;


import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    public double delHeight;
    public double delWidth;
    public BinarySearchTreeActivity rbta;
    public BinarySearchNode root = null;
    public double startPositionX;
    public double startPositionY;

    public BinarySearchTree(BinarySearchTreeActivity rbta) {
        this.rbta = rbta;
        this.startPositionX = (double) (((int) (((double) ((float) rbta.width)) * 0.9d)) / 2);
        this.startPositionY = 150.0d;
        this.delHeight = 200.0d;
        this.delWidth = 200.0d;
    }

    public void insert(int val) {
        if (this.root == null) {
            this.root = new BinarySearchNode(val, this);
            this.rbta.rbn = this.root;
            this.root.black = 1;
            this.root.putNullLeaves(this.root);
            this.root.resizeTree();
            return;
        }
        BinarySearchNode insertElem = new BinarySearchNode(val, this);
        rbta.current=insertElem;
        insertElem.height = 1.0d;
        this.root.insert(insertElem, this.root);
        root.height=root.maxDepth(root);

        this.root.resizeTree();
    }

    public void delete(int deletedValue) {
        if (this.root == null) {
            this.rbta.print = "Tree doesn't exist";
            return;
        }
        this.root.delete(this.root, deletedValue);
////        node.highlight = true;
//        this.rbta.dt.resume();
//        try {
//            Thread.sleep((long) (1000 - ((int) (this.rbta.speed * 10.0d))));
//        } catch (Exception e) {
//        }
//        this.rbta.dt.pause();
////        node.highlight = false;
//        root.resetHeight(root);
        root.height=root.maxDepth(root);

        root.resizeTree();


    }

    public void inorder(BinarySearchNode root){
        if(root!=null && !root.ileaf){
            inorder(root.left);
            rbta.linkedList.add(root);
            inorder(root.right);
        }
    }
    public void preorder(BinarySearchNode root){
        if(root!=null && !root.ileaf){
            rbta.linkedList.add(root);
            preorder(root.left);
            preorder(root.right);
        }
    }
    public void postorder(BinarySearchNode root){
        if(root!=null && !root.ileaf){
            postorder(root.left);
            postorder(root.right);
            rbta.linkedList.add(root);

        }
    }


    public BinarySearchTree clone() {
        BinarySearchTree rbtn = new BinarySearchTree(this.rbta);
        rbtn.delWidth = this.delWidth;
        rbtn.delHeight = this.delHeight;
        rbtn.startPositionX = this.startPositionX;
        rbtn.startPositionY = this.startPositionY;
        Queue<BinarySearchNode> que = new LinkedList();
        Queue<BinarySearchNode> cque = new LinkedList();
        if (this.root != null) {
            que.add(this.root);
            BinarySearchNode croot = new BinarySearchNode(this.root.data, rbtn);
            cque.add(croot);
            while (!que.isEmpty()) {
                BinarySearchNode n = (BinarySearchNode) que.remove();
                BinarySearchNode cn = (BinarySearchNode) cque.remove();
                cn.ileaf = n.ileaf;
                cn.black = n.black;
                cn.height = n.height;
                cn.oldX = n.oldX;
                cn.oldY = n.oldY;
                cn.leftWidth = n.leftWidth;
                cn.rightWidth = n.rightWidth;
                if (n.left != null) {
                    que.add(n.left);
                    BinarySearchNode cl = new BinarySearchNode(n.left.data, rbtn);
                    cl.parent = cn;
                    cn.left = cl;
                    cque.add(cl);
                }
                if (n.right != null) {
                    que.add(n.right);
                    BinarySearchNode cr = new BinarySearchNode(n.right.data, rbtn);
                    cr.parent = cn;
                    cn.right = cr;
                    cque.add(cr);
                }
            }
            rbtn.root = croot;
        }
        return rbtn;
    }

    public boolean search(int k) {
        if (this.root != null) {
            return this.root.search(this.root, k);
        }
        this.rbta.print = "Tree dosen't exist";
        return false;
    }

    public boolean order() {
        if (this.root != null) {
            this.root.order();
            return true;
        }
        this.rbta.print = "Tree dosen't exist";
        return false;
    }

}
