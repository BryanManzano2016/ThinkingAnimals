/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Lesther Carranza
 */
public class BinaryNode<T> {
    
    private T content;
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    //private boolean visited;
    
    public BinaryNode() {
        this(null, null, null);
    }

    public BinaryNode(T content) {
        this(content, null, null);
    }

    public BinaryNode(T content, BinaryTree<T> left, BinaryTree<T> right) {
        this.content = content;
        this.left = left;
        this.right = right;
        //this.visited=false;
    }
    
    /*public void setVisited(){
        this.visited=true;
    }
    
    public boolean getVisited(){
        return this.visited;
    }
    
    public void setVisitedFa(){
        this.visited=false;
    }*/
    
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setRight(BinaryTree<T> right) {
        this.right = right;
    }
}

