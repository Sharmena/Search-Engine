package tests;

import jsjf.LinkedBinarySearchTree;
import jsjf.exceptions.ElementNotFoundException;

/**
 * @author Sonya Cates <scates@rwu.edu>
 * @date Mar 24, 2016
 *
 */
public class TestSearchTree {
    public static void main(String[] args) {
        LinkedBinarySearchTree<Integer> searchTree = new LinkedBinarySearchTree<Integer>();
        searchTree.addElement(5);
        searchTree.addElement(9);
        searchTree.addElement(1);
        searchTree.addElement(3);
        searchTree.addElement(7);
        searchTree.addElement(6);
        
        System.out.println("Tree contains 3? " + searchTree.contains(3));
        System.out.println("Tree contains 4? " + searchTree.contains(4));
        System.out.println("Find 6 = " + searchTree.find(6));
        System.out.println((searchTree.getLeft()).toString());
        System.out.println((searchTree.getRight()).toString());
        System.out.println(searchTree.size());
        try{
            searchTree.find(4);
        } catch(ElementNotFoundException e){
            System.out.println("Find 4 throws an exception.");
        }
        
    }
    
}
