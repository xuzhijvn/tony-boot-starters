/*
 *       Copyright© (2020).
 */
package com.tony.component.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author tony
 * @create 2021-11-30
 * @description:
 */
public class Test {


    static Queue<TreeNode> queue = new LinkedList<>();

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;

        node3.left = node4;
        node3.right = node5;

//        System.out.println(btf2(root));

        btf2(root).forEach(System.out::println);

        System.out.println(reverse(1234));

        System.out.println(getDistance(1, 3, 1.1));
        String str = "hello";
        test(str);
        System.out.println(str);


    }


    public static void test(String str){
        str = "111";


    }

    public static int reverse(int n) {

        int m = 0;
        while (n > 0) {
            m = 10 * m + n % 10;
            n = n / 10;
        }
        return m;

    }

    public static double getDistance(int begin, int end, double d) {
        return ((end - begin) * d) / 1000;
    }


    public static List<List<Integer>> btf2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
