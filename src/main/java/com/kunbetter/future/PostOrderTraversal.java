package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/10
 * time 16:29
 * desc
 */
public class PostOrderTraversal {

    //通过前序和中序遍历，输出后续遍历结果

    public static void main(String[] argv) {
        String preOrder = "ABDECFG";
        String inOrder = "DBEAFCG";
        System.out.println(getPostOrder(preOrder, inOrder));
    }

    public static String getPostOrder(String preOrder, String inOrder) {
        if (preOrder == null || inOrder == null) {
            return null;
        }

        if (preOrder.length() == 0 || inOrder.length() == 0 || preOrder.length() != inOrder.length()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        process(preOrder, inOrder, sb);

        return sb.toString();
    }

    private static void process(String preOrder, String inOrder, StringBuilder sb) {
        int len = preOrder.length();
        if (len <= 0) {
            return;
        }

        char root = preOrder.charAt(0);
        int rootInInOrder = -1;

        for (int i = 0; i < len; i++) {
            if (root == inOrder.charAt(i)) {
                rootInInOrder = i;
                break;
            }
        }

        if (rootInInOrder < 0) {
            return;
        }

        process(preOrder.substring(1, rootInInOrder + 1), inOrder.substring(0, rootInInOrder), sb);
        process(preOrder.substring(rootInInOrder + 1), inOrder.substring(rootInInOrder + 1), sb);
        sb.append(root);
    }
}
