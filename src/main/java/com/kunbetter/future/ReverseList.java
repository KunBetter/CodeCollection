package com.kunbetter.future;

/**
 * User: xinxi.li
 * Date: 2020/9/4
 * Time: 23:14
 */
public class ReverseList {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            next = this;
        }
    }

    public Node reverseList(Node head) {
        Node pre = null;
        Node next;
        Node cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void main(String[] argv) {
        ReverseList rl = new ReverseList();

        int[] nums = new int[]{2, 4, 3};
        Node head = rl.GenLinkedList(nums);
        head = rl.reverseList(head);
        System.out.println(head);
    }

    public Node GenLinkedList(int[] nums) {
        Node res = null;
        Node cur = null;
        for (int num : nums) {
            Node n = new Node(num);
            if (res == null) {
                res = n;
            } else {
                cur.next = n;
            }
            cur = n;
        }

        return res;
    }
}
