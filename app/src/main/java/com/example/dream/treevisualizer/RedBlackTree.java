package com.example.dream.treevisualizer;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree {
    public double delHeight;
    public double delWidth;
    public RedBlackTreeActivity rbta;
    public RedBlackNode root = null;
    public double startPositionX;
    public double startPositionY;

    public RedBlackTree(RedBlackTreeActivity rbta) {
        this.rbta = rbta;
        this.startPositionX = (double) (((int) (((double) ((float) rbta.width)) * 0.9d)) / 2);
        this.startPositionY = 150.0d;
        this.delHeight = 200.0d;
        this.delWidth = 200.0d;
    }

    public void insert(int val) {
        if (this.root == null) {
            this.root = new RedBlackNode(val, this);
            this.rbta.rbn = this.root;
            this.root.black = 1;
            this.root.putNullLeaves(this.root);
            this.root.resizeTree();
            return;
        }
        RedBlackNode insertElem = new RedBlackNode(val, this);
        rbta.current=insertElem;
        insertElem.height = 1.0d;
        this.root.insert(insertElem, this.root);
        this.root.resizeTree();
    }

    public void delete(int deletedValue) {
        if (this.root == null) {
            this.rbta.print = "Tree doesn't exist";
            return;
        }
        this.root.delete(this.root, deletedValue);
    }

    public void inorder(RedBlackNode root){
    if(root!=null && !root.ileaf){
        inorder(root.left);
        rbta.linkedList.add(root);
        inorder(root.right);
    }
    }
    public void preorder(RedBlackNode root){
        if(root!=null && !root.ileaf){
            rbta.linkedList.add(root);
            preorder(root.left);
            preorder(root.right);
        }
    }
    public void postorder(RedBlackNode root){
        if(root!=null && !root.ileaf){
            postorder(root.left);
            postorder(root.right);
            rbta.linkedList.add(root);

        }
    }


    public RedBlackTree clone() {
        RedBlackTree rbtn = new RedBlackTree(this.rbta);
        rbtn.delWidth = this.delWidth;
        rbtn.delHeight = this.delHeight;
        rbtn.startPositionX = this.startPositionX;
        rbtn.startPositionY = this.startPositionY;
        Queue<RedBlackNode> que = new LinkedList();
        Queue<RedBlackNode> cque = new LinkedList();
        if (this.root != null) {
            que.add(this.root);
            RedBlackNode croot = new RedBlackNode(this.root.data, rbtn);
            cque.add(croot);
            while (!que.isEmpty()) {
                RedBlackNode n = (RedBlackNode) que.remove();
                RedBlackNode cn = (RedBlackNode) cque.remove();
                cn.ileaf = n.ileaf;
                cn.black = n.black;
                cn.height = n.height;
                cn.oldX = n.oldX;
                cn.oldY = n.oldY;
                cn.leftWidth = n.leftWidth;
                cn.rightWidth = n.rightWidth;
                if (n.left != null) {
                    que.add(n.left);
                    RedBlackNode cl = new RedBlackNode(n.left.data, rbtn);
                    cl.parent = cn;
                    cn.left = cl;
                    cque.add(cl);
                }
                if (n.right != null) {
                    que.add(n.right);
                    RedBlackNode cr = new RedBlackNode(n.right.data, rbtn);
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
