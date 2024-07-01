package TopQuestions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode1382 {

    class Solution {
        public TreeNode balanceBST(TreeNode root) {

            List<Integer> inorder = new ArrayList<>();
            inorder_traverse(root,inorder);
            //System.out.println(inorder);
            return construct_bst(inorder,0,inorder.size()-1);
        }

        public TreeNode construct_bst(List<Integer> inorder, int start, int end){

            if(end < start){
                return null;
            }

            //Current Node of BST is the middle node of inorder
            int mid = end + (start-end)/2;

            TreeNode curr = new TreeNode(inorder.get(mid));
            curr.left = construct_bst(inorder,start,mid-1);
            curr.right = construct_bst(inorder,mid+1,end);

            return curr;
        }

        public void inorder_traverse(TreeNode root, List<Integer> inorder){

            if(root == null){
                return;
            }

            //Left
            inorder_traverse(root.left,inorder);
            //Node
            inorder.add(root.val);
            //Right
            inorder_traverse(root.right,inorder);

        }
    }
}
