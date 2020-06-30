package com.example.dream.treevisualizer;

/* compiled from: RedBlackTree */
class RedBlackNode {
    public int black;
    public int data;
    public double fx;
    public double fy;
    public double height;
    public boolean highlight;
    public boolean ileaf;
    public RedBlackNode left;
    public double leftWidth;
    public RedBlackNode parent;
    RedBlackTree rbt;
    public RedBlackNode right;
    public double rightWidth;
    public double oldX;
    public double oldY;

    public RedBlackNode() {
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

    public RedBlackNode(int data, RedBlackTree rbt) {
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

    public RedBlackNode findUncle(RedBlackNode node) {
        if (node.parent == null) {
            return null;
        }
        RedBlackNode par = node.parent;
        if (par.parent == null) {
            return null;
        }
        RedBlackNode grandPar = par.parent;
        if (grandPar.left == par) {
            return grandPar.right;
        }
        return grandPar.left;
    }

    public int black(RedBlackNode node) {
        if (node == null) {
            return 1;
        }
        return node.black;
    }

    public void LeftNullLeaf(RedBlackNode node) {
        node.left = new RedBlackNode();
        node.left.ileaf = true;
        node.left.black = 1;
    }

    public void RightNullLeaf(RedBlackNode node) {
        node.right = new RedBlackNode();
        node.right.ileaf = true;
        node.right.black = 1;
    }

    public void putNullLeaves(RedBlackNode node) {
        LeftNullLeaf(node);
        RightNullLeaf(node);
    }

    public RedBlackNode RotateRight(RedBlackNode node) {
        RedBlackNode B = node;
        RedBlackNode A = node.left;
        RedBlackNode t2 = A.right;
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

    public RedBlackNode RotateLeft(RedBlackNode node) {
        RedBlackNode A = node;
        RedBlackNode B = node.right;
        RedBlackNode t2 = B.left;
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

    public double getHeight(RedBlackNode node) {
        if (node == null) {
            return 0.0d;
        }
        return node.height;
    }

    public void resetHeight(RedBlackNode node) {
        if (node != null) {
            if (node.height != Math.max(getHeight(node.left), getHeight(node.right)) + 1.0d) {
                node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1.0d;
            }
        }
    }

    public void insert(RedBlackNode elem, RedBlackNode node) {
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
                fixDoubleRed(elem);
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
            fixDoubleRed(elem);
        } else {
            insert(elem, node.right);
        }
    }

    public void fixDoubleRed(RedBlackNode node) {
        if (node.parent != null) {
            if (node.parent.black <= 0) {
                if (node.parent.parent == null) {
                    node.parent.black = 1;
                    return;
                }
                RedBlackNode uncle = findUncle(node);
                if (black(uncle) == 0) {
                    uncle.black = 1;
                    node.parent.black = 1;
                    node.parent.parent.black = 0;
                    fixDoubleRed(node.parent.parent);
                    return;
                }
                if (node.isLeftChild() && !node.parent.isLeftChild()) {
                    RotateRight(node.parent);
                    node = node.right;
                } else if (!node.isLeftChild() && node.parent.isLeftChild()) {
                    RotateLeft(node.parent);
                    node = node.left;
                }
                if (node.isLeftChild()) {
                    RotateRight(node.parent.parent);
                    node.parent.black = 1;
                    node.parent.right.black = 0;
                    return;
                }
                RotateLeft(node.parent.parent);
                node.parent.black = 1;
                node.parent.left.black = 0;
            }
        } else if (node.black == 0) {
            node.black = 1;
        }
    }

    public void fixLeftNull(RedBlackNode node) {
        RedBlackNode nullLeaf = new RedBlackNode();
        nullLeaf.black = 2;
        nullLeaf.parent = node;
        nullLeaf.ileaf = true;
        node.left = nullLeaf;
        resizeTree();
        fixExtraBlackChild(node, true);
        nullLeaf.black = 1;
    }

    public void fixRightNull(RedBlackNode node) {
        RedBlackNode nullLeaf = new RedBlackNode();
        nullLeaf.parent = node;
        nullLeaf.ileaf = true;
        nullLeaf.black = 2;
        node.right = nullLeaf;
        resizeTree();
        fixExtraBlackChild(node, false);
        nullLeaf.black = 1;
    }

    public void fixExtraBlackChild(RedBlackNode parNode, boolean isLeftChild) {
        RedBlackNode sibling, newPar, newSib;
        RedBlackNode doubleBlackNode;
        int oldParblack;
        if (isLeftChild) {
            sibling = parNode.right;
            doubleBlackNode = parNode.left;
        } else {
            sibling = parNode.left;
            doubleBlackNode = parNode.right;
        }
        if (black(sibling) > 0 && black(sibling.left) > 0 && black(sibling.right) > 0) {
            sibling.black = 0;
            if (doubleBlackNode != null) {
                doubleBlackNode.black = 1;
            }
            if (parNode.black == 0) {
                parNode.black = 1;
                return;
            }
            parNode.black = 2;
            fixExtraBlack(parNode);
        } else if (black(sibling) == 0) {
            if (isLeftChild) {
                newPar = RotateLeft(parNode);
                newPar.black = 1;
                newPar.left.black = 0;
                fixExtraBlack(newPar.left.left);
                return;
            }
            newPar = RotateRight(parNode);
            newPar.black = 1;
            newPar.right.black = 0;
            fixExtraBlack(newPar.right.right);
        } else if (isLeftChild && black(sibling.right) > 0) {
            newSib = RotateRight(sibling);
            newSib.black = 1;
            newSib.right.black = 0;
            fixExtraBlackChild(parNode, isLeftChild);
        } else if (!isLeftChild && black(sibling.left) > 0) {
            newSib = RotateLeft(sibling);
            newSib.black = 1;
            newSib.left.black = 0;
            fixExtraBlackChild(parNode, isLeftChild);
        } else if (isLeftChild) {
            oldParblack = parNode.black;
            newPar = RotateLeft(parNode);
            if (oldParblack == 0) {
                newPar.black = 0;
                newPar.left.black = 1;
            }
            newPar.right.black = 1;
            if (newPar.left.left != null) {
                newPar.left.left.black = 1;
            }
        } else {
            oldParblack = parNode.black;
            newPar = RotateRight(parNode);
            if (oldParblack == 0) {
                newPar.black = 0;
                newPar.right.black = 1;
            }
            newPar.left.black = 1;
            if (newPar.right.right != null) {
                newPar.right.right.black = 1;
            }
        }
    }

    public void fixExtraBlack(RedBlackNode node) {
        if (node.black <= 1) {
            return;
        }
        if (node.parent == null) {
            node.black = 1;
        } else if (node.parent.left == node) {
            fixExtraBlackChild(node.parent, true);
        } else {
            fixExtraBlackChild(node.parent, false);
        }
    }

    public void delete(RedBlackNode node, int valueToDelete) {
        boolean leftchild = false;
        RedBlackNode r3;
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
                RedBlackNode redBlackNode = node;
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

    public void updatePositions(RedBlackNode node) {
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

    public void setNewPositions(RedBlackNode node, double xPosition, double yPosition, int side) {
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

    public double resizeWidths(RedBlackNode node) {
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

    public boolean search(RedBlackNode node, int k) {
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
        RedBlackNode node;
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