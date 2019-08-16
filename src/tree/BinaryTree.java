/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import Model.Pregunta;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Lesther Carranza
 * @param <T>
 */
public class BinaryTree<T> {

    private BinaryNode<T> root;
    //private Integer hojas;
    public BinaryTree() {
        this.root = new BinaryNode<>();
        //this.hojas=null;
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
        //this.hojas=null;
    }
    
    public BinaryNode<T> getRoot() {
        return root;
    }
    
    /*public void setNum(){
        this.hojas=countLeavesIterative();
    }
    
    public Integer getNum(){
        return this.hojas;
    }*/
    
    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }

    public BinaryNode<T> recursiveSearch(T content) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (this.root.getContent().equals(content)) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content);
                    }
                }
                return tmp;
            }
        }
    }
    //Primer Literal
    public void recursiveInOrden(){
        recursiveInOrden(this);
        System.out.println();
    }
    private void recursiveInOrden(BinaryTree<T> node){
        if(node!=null){
            recursiveInOrden(node.getLeft());
            System.out.print(node.getRoot().getContent());
            recursiveInOrden(node.getRight());
        }
    }
    public void IterativeInOrden(){
        IterativeInOrden(this);
    }
    private void IterativeInOrden(BinaryTree<T> node){
        if(root == null)
            return;
	Stack<BinaryTree<T>> s = new Stack<>();
	BinaryTree<T> currentNode=node;
 	while(!s.empty() || currentNode!=null){
 
            if(currentNode!=null){
		s.push(currentNode);
		currentNode=currentNode.getLeft();
            }else{
		BinaryTree<T> n=s.pop();
		System.out.println(n.getRoot().getContent());
		currentNode=n.getRight();
            }
        }
    }
    
    //Segundo Literal
    public void recursivePreOrden(){
        recursivePreOrden(this);
        System.out.println();
    }
    private void recursivePreOrden(BinaryTree<T> node){
        if(node!=null){
            System.out.print(node.getRoot().getContent());
            recursivePreOrden(node.getLeft());
            recursivePreOrden(node.getRight());
        }
    }
    
    public void IterativePreOrden(){
        IterativePreOrden(this);
        System.out.println();
    }
    
    private void IterativePreOrden(BinaryTree<T> node){
        if(root == null)
            return;
	Stack<BinaryTree<T>> s = new Stack<>();
	BinaryTree<T> currentNode=node;
 	while(!s.empty() || currentNode!=null){
            
            if(currentNode!=null){
                System.out.print(currentNode.getRoot().getContent());
		s.push(currentNode);
		currentNode=currentNode.getLeft();
            }else{
		BinaryTree<T> n=s.pop();
		currentNode=n.getRight();
            }
        }
    }
    
    //Tercer Literal
    public void recursivePostOrden(){
        recursivePostOrden(this);
        System.out.println();
    }
    private void recursivePostOrden(BinaryTree<T> node){
        if(node!=null){
            recursivePostOrden(node.getLeft());
            recursivePostOrden(node.getRight());
            System.out.print(node.getRoot().getContent());
        }
    }
    
    public void iterativePostOrden(){
        String palabra="";
        
            if (null != this.root)
            {
                Stack<BinaryNode<T>> postOrderStack = new Stack<>();
                Stack<BinaryNode<T>> rightLeftPreOrderStack = new Stack<>();
                rightLeftPreOrderStack.push(this.root);
                while(rightLeftPreOrderStack.size() > 0)
                {
                    BinaryNode<T> top = rightLeftPreOrderStack.pop();
                    postOrderStack.push(top);
                    if(top.getLeft() != null)
                    {
                        rightLeftPreOrderStack.push(top.getLeft().getRoot());
                    }
                    if(top.getRight() != null)
                    {
                        rightLeftPreOrderStack.push(top.getRight().getRoot());
                    }
                }
                while(postOrderStack.size() > 0)
                {
                    BinaryNode<T> top = postOrderStack.pop();
                    palabra=palabra+top.getContent();
                }
            }
            System.out.println(palabra);
        }
    
    //Literal 4
    public BinaryNode<T> recursiveMin(){
        if(isEmpty())
            return null;
        return new BinaryNode(recursiveMin(root));
    }
    private int recursiveMin(BinaryNode<T> raiz){
        int tmp=Integer.parseInt(raiz.getContent().toString());
        if(raiz.getLeft()!=null){
           int ltmp=recursiveMin(raiz.getLeft().getRoot());
           if(ltmp<tmp)
               tmp=ltmp;
        }
        if(raiz.getRight()!=null){
           int rtmp=recursiveMin(raiz.getRight().getRoot());
           if(rtmp<tmp)
               tmp=rtmp;
        }
        return tmp;
    }
    
    public BinaryNode<T> IterativeMin(){
        if(isEmpty())
            return null;
        return new BinaryNode(IterativeMin(this));
    }
    private int IterativeMin(BinaryTree<T> tree){
        Stack<BinaryTree<T>> pila=new Stack();
        int valor=Integer.parseInt(tree.getRoot().getContent().toString());
        if(tree.isEmpty())
            return valor;
        else{
            pila.push(tree);
            while(!pila.isEmpty()){
                BinaryTree<T> tmp=pila.pop();
                int tmpval=Integer.parseInt(tmp.getRoot().getContent().toString());
                if(valor>tmpval){
                    valor=tmpval;
                }
                if(tmp.getRight()!=null){
                    pila.push(tmp.getRight());
                }
                if(tmp.getLeft()!=null){
                    pila.push((tmp.getLeft()));
                }
            }
        }
        return valor;
    }
    
    //Quinto Literal
    public BinaryNode<T> IterativeSearch(T content) {
        if(content==null || isEmpty())
            return null;
        Stack<BinaryTree<T>> pila=new Stack();
        pila.push(this);
        while(!pila.isEmpty()){
            BinaryTree<T> tmp=pila.pop();
            if(tmp.getRoot().getContent().equals(content))
                return tmp.getRoot();
            if(tmp.getLeft()!=null){
                pila.push(tmp.getLeft());
            }
            if(tmp.getRight()!=null){
                pila.push(tmp.getRight());
            }
        }
        return null;
    }

    
    //Sexto Literal
    public int recursiveCountLevels(){
        if(root!=null){
            return this.recursiveCountLevel();
        }else{
            return 0;
        }
    }
    private int recursiveCountLevel(){
        int left=0,right=0;
        if(this.getLeft()!=null)
            left=this.getLeft().recursiveCountLevels();
        if(this.getRight()!=null)
            right=this.getRight().recursiveCountLevels();
        return 1+Math.max(left, right);
    }
    public int iterativeCountLevels(){
        if(isEmpty()){
            return 0;
        }
        return iterativeCountLevels(this.root);
    }
    private int iterativeCountLevels(BinaryNode<T> node){ 
        if (node == null) 
            return 0; 
        Queue<BinaryNode<T>> q = new LinkedList(); 
        q.add(node); 
        int height = 0; 
        while (true){ 
            int nodeCount = q.size(); 
            if (nodeCount == 0) 
                return height; 
            height++; 
            while (nodeCount > 0){ 
                BinaryNode<T> newnode = q.peek(); 
                q.remove(); 
                if (newnode.getLeft() != null) 
                    q.add(newnode.getLeft().getRoot()); 
                if (newnode.getRight() != null) 
                    q.add(newnode.getRight().getRoot()); 
                nodeCount--; 
            }
        }
    }
    //Septimo Literal
    public boolean recursiveisLefty(){
        if(isEmpty()||isLeaf())
            return true;
        else
            return recursiveisLefty(this)==1;
    }
    private int recursiveisLefty(BinaryTree<T> tree){
        if(tree!=null){
            int total=recursivecountSons(tree);
            int iz=recursivecountSons(tree.getLeft());
            if(iz>(total/2))
                return 1;
            else
                return 0;
        }
       return 0; 
    }
    private int recursivecountSons(BinaryTree<T> tree){
        if(tree==null)
            return 0;
        return 1+recursivecountSons(tree.getLeft())+recursivecountSons(tree.getRight());
    }
    public boolean iterativeisLefty(){
        if(isEmpty()||isLeaf())
            return true;
        else
            return iterativeisLefty(this)==1;
    }
    private int iterativeisLefty(BinaryTree<T> tree){
        int total=iterativecountSons(tree);
        int left=iterativecountSons(tree.getLeft());
        if(left>(total/2))
            return 1;
        else
            return 0;
    }
    public int iterativecountSons(BinaryTree<T> tree){
        Stack<BinaryTree<T>> pila=new Stack();
        pila.push(tree);
        int cont=0;
        while(!pila.isEmpty()){
            BinaryTree<T> sub=pila.pop();
            cont++;
            if(sub.getLeft()!=null)
                pila.push(sub.getLeft());
            if(sub.getRight()!=null)
                pila.push(sub.getRight());
        }
        return cont;
    }
    //Octavo Literal
    public boolean recursiveisIdentical(BinaryTree<T> another){
        if(isEmpty()||another.isEmpty()||recursiveCountLevels()!=another.recursiveCountLevels()||countLeavesRecursive()!=another.countLeavesRecursive())
            return false;
        return recursiveisIdentical(this,another);
    }
    private boolean recursiveisIdentical(BinaryTree<T> original,BinaryTree<T> another){
        if(original==null&&another==null)
            return true;
        if(original!=null&&another!=null){
            return (original.getRoot().getContent().equals(another.getRoot().getContent()))&&(recursiveisIdentical(original.getLeft(),another.getLeft()))&&(recursiveisIdentical(original.getRight(),another.getRight()));  
        }
        return false;
    }
    
    public boolean iterativeisIdentical(BinaryTree<T> another){
        if(isEmpty()||another.isEmpty()||iterativeCountLevels()!=another.iterativeCountLevels()||countLeavesIterative()!=another.countLeavesIterative())
            return false;
        return iterativeisIdentical(this,another);
    }
    private boolean iterativeisIdentical(BinaryTree<T> original,BinaryTree<T> another){
        Stack<BinaryTree<T>> pila=new Stack();
        pila.push(original);
        pila.push(another);
        while(pila.size()>1){
            BinaryTree<T> tmp1=pila.pop();
            BinaryTree<T> tmp2=pila.pop();
            if((tmp1.getRoot().getContent().equals(tmp2.getRoot().getContent()))){
                if(tmp1.getLeft()!=null&&tmp2.getLeft()!=null){
                    pila.push(tmp1.getLeft());
                    pila.push(tmp2.getLeft());
                }if(tmp1.getRight()!=null&&tmp2.getRight()!=null){
                    pila.push(tmp1.getRight());
                    pila.push(tmp2.getRight());
                }
            }else{
                return false;
            }
        }
        return true;
    }
    
    /*Se hicieron cambios en la clase NodeBinary para poder trabajar en el proyecto
    Tener en cuenta que algunos metodos fueron clonados para pobrar optimizacion en otros casos
    No hacerles caso (eliminarlos o dejarlos como comentario) pueden llegar a servir
    mas adelante o puede que no*/
    
    // Un nuevo arbol se inicia como hijo izquierdo y derecho de las hojas
    public void setChildrens( String s1 ) {
        // Una lista para buscar las hojas
        Stack<BinaryTree<T>> stack = new Stack();

        if (!this.isEmpty()) {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                // Cada nivel tiene hojas por igual, asi una pregunta se ubica en cada nivel
                if (subtree.isLeaf()) {
                    subtree.setLeft(new BinaryTree( new Pregunta(s1) ));
                    subtree.setRight(new BinaryTree( new Pregunta(s1) ));
                }
            }
        }
    }
    // Un arbol es dado y retorna las respuestas no vacias( parametro len = 0 es cadenas vacias)
    public LinkedList<String> getChildrensAnswers(BinaryTree<Pregunta> tree) {
        
        // Una lista para buscar las hojas
        LinkedList<String> respuestas = new LinkedList<>();
        Stack<BinaryTree<Pregunta>> stack = new Stack();

        if (!this.isEmpty()) {
            stack.push(tree);
            while (!stack.empty()) {
                BinaryTree<Pregunta> subtree = stack.pop();
                // Si ese arbol tiene en el root con una respuesta no vacia, añade a la lista de hijos
                if (!subtree.getRoot().getContent().getRespuesta().equals("") && subtree.isLeaf()){
                    respuestas.addLast(subtree.getRoot().getContent().getRespuesta());
                    continue;
                }
                
                if (subtree.getLeft() != null) {
                    stack.push(subtree.getLeft());
                }
                if (subtree.getRight() != null) {
                    stack.push(subtree.getRight());
                }
            }
        }
        return respuestas;
    }
    
    // Elimina las hojas sin respuestas
    public void removeChildrenWithoutAnwers(BinaryTree<Pregunta> tree) {
        // Una lista para buscar las hojas
        Stack<BinaryTree<Pregunta>> stack = new Stack();

        if (!this.isEmpty()) {
            stack.push(tree);
            while (!stack.empty()) {
                BinaryTree<Pregunta> subtree = stack.pop();
               
                if (subtree.getLeft() != null) {
                    // Si la respuesta es nula y es hoja hace null a ese hijo
                    if (subtree.getLeft().getRoot().getContent().getRespuesta().equals("")
                            && subtree.getLeft().isLeaf()) {
                        subtree.setLeft(null);
                    } else
                        stack.push(subtree.getLeft());
                }
                
                if (subtree.getRight() != null) {
                    // Si la respuesta es nula y es hoja hace null a ese hijo
                    if (subtree.getRight().getRoot().getContent().getRespuesta().equals("")
                            && subtree.getRight().isLeaf()) {
                        subtree.setRight(null);
                    } else                    
                        stack.push(subtree.getRight());
                }
            }
        }
    }
    
    public BinaryNode<T> searchNodo(T element){
        return searchNodeRecursivo(element,this.getRoot());
    }
    
    private BinaryNode<T> searchNodeRecursivo(T element, BinaryNode<T> nodo){
        if(nodo == null){
            return null;
        }else if(nodo.getContent().equals(element)){
            return nodo;
        }
        BinaryNode<T> i = null;
        BinaryNode<T> j = null;
        if(nodo.getLeft() == null && nodo.getRight() != null){
            //nodo.getRight().getRoot().setVisited();
            j = searchNodeRecursivo(element,nodo.getRight().getRoot());
        }else if(nodo.getRight() == null && nodo.getLeft() != null){
            //nodo.getLeft().getRoot().setVisited();
            i = searchNodeRecursivo(element,nodo.getLeft().getRoot());
        }else if(nodo.getLeft() != null && nodo.getRight() != null){
            /*nodo.getLeft().getRoot().setVisited();
            nodo.getRight().getRoot().setVisited();*/
            i = searchNodeRecursivo(element,nodo.getLeft().getRoot());
            j = searchNodeRecursivo(element,nodo.getRight().getRoot());
        }
        if(i == null && j == null){
            return null;
        }else{
            if(i == null){
                return j;
            }else{
                return i;
            }
        }
    }
    /*Este metodo regresa el padre de un nodo es util si se quiere añadir la opcion 
    de volver en el juego de adivinar animal*/
    
    public BinaryTree<T> getPadre(T hijo){
        Stack<BinaryTree<T>> pila=new Stack();
        pila.push(this);
        while(!pila.isEmpty()){
            BinaryTree<T> arbol=pila.pop();
            if(arbol.getLeft()!=null){
                if(arbol.getLeft().getRoot().getContent().equals(hijo)){
                    return arbol;
                }else{
                    pila.push(arbol.getLeft());
                }
            }
            else if(arbol.getRight()!=null){
                if(arbol.getRight().getRoot().getContent().equals(hijo)){
                    return arbol;
                }else{
                    pila.push(arbol.getRight());
                }
            }
        }
        return null;
    }
}