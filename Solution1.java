/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

/**
 *
 * @author Lu Hong
 */
public class Solution1 {
    /*
    You are to find the closest common ancestor of two vertices in a binary tree. For example, 
    the common ancestors of vertices 8 and 13 in the figure below are vertices 3 and 1. 
    Among them, vertex 3 is the closest to the vertex 8 and 13. 
    And the size of sub-tree (the number of vertices in the sub-tree) rooted by vertex 3 is 8
    */
    
    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root.val == node1.val || root.val == node2.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        } else if (left == null && right == null) {
            return null;
        } else {
            return (left == null) ? right : left;
        }
    }
    
    public int countTreeNodes(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftTreeNodes = countTreeNodes(node.left);
            int rightTreeNodes = countTreeNodes(node.right);
            return leftTreeNodes + rightTreeNodes + 1;
        }
    } 
    

    
    TreeNode tree;
    
    public void buildTree(TreeNode node, int data, int child) {
        if (tree == null) {
            tree = new TreeNode(data);
            tree.left = new TreeNode(child);
        } else {
            if ((data == node.val)) {
                if (node.left == null) {
                    node.left = new TreeNode(child);
                }else{
                    node.right = new TreeNode(child);
                }
            } else{
                if (node.left != null){
                    buildTree(node.left, data, child);
                }
                if (node.right != null){
                    buildTree(node.right, data, child);
                }
            }
        }
    }

    public void createBinaryTree(int verticesNum, int edgesNum, int node1, int node2, int[] edges) {
        tree = null;
        if (verticesNum < 3 || edgesNum < 2 || edges == null) {
            return;
        }
        for (int i1 = 0; i1 < edgesNum; i1++) {
            buildTree(tree, edges[2 * i1], edges[2 * i1 + 1]);
        }
        TreeNode node = lowestCommonAncestor(tree, new TreeNode(node1), new TreeNode(node2));
        int depth = countTreeNodes(node);
        System.out.println(node.val + " " + depth);
    }

    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        int[] edges = {1, 2, 1, 3, 2, 4, 3, 5, 3, 6, 4, 7, 7, 12, 5, 9, 5, 8, 6, 11, 6, 10, 11, 13};
        sol.createBinaryTree(13, 12, 8, 13, edges);

        edges = new int[]{1, 2, 1, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10};
        sol.createBinaryTree(10, 9, 2, 10, edges);
    }

}
