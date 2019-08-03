/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import tree.BinaryTree;

/**
 *
 * @author Lesther Carranza
 */
public class ControlJuego {
    
    public BinaryTree<String> constructorArbol(){
        HashMap<Integer,String> preguntas = ControlArchivosIO.lecturaArchivo("src/archivos/preguntas.txt");
        HashMap<String,LinkedList<String>> respuestas = ControlArchivosIO.lecturaArchivoRespuestas("src/archivos/respuestas.txt");
        
        Set<Integer> keypreguntas = preguntas.keySet();
        BinaryTree<String> arbol=new BinaryTree<>(preguntas.get(0));
        keypreguntas.stream().filter((i) -> (i!=0)).forEachOrdered((i) -> {
            arbol.setChildrens(new BinaryTree(preguntas.get(i)));
        });
        respuestas.forEach((resp,lista)->arbol.add(resp,lista));
        /*
        for (Integer i: preguntas.keySet()) {
            System.out.println(i + "---" + preguntas.get(i));
        }
        */
        for (String i: respuestas.keySet()) {
            for (String s: respuestas.get(i)) {
                System.out.println(i + "..." + s);
            }
        }
        
        return arbol;
    }
    
    public void setAnswers(String element,LinkedList<String> lista,BinaryTree<String> arbol){
        Stack<BinaryTree<String>> pila = new Stack();
        pila.push(arbol);
        System.out.println(element);
        while(!pila.isEmpty()&&!lista.isEmpty()){
            BinaryTree<String> subarbol=pila.pop();
            if(!arbol.isLeaf()){
                String res=lista.removeFirst().trim();
                //System.out.println(lista.size());
                //System.out.println(arbol.getRoot().getContent()+"  "+res);
                if(res.equals("si")){
                    //System.out.println(arbol.getLeft().getRoot().getContent()+"Izquierdo");
                    pila.push(arbol.getLeft());
                }else{
                    //System.out.println(arbol.getRight().getRoot().getContent()+"Dereceho");
                    pila.push(arbol.getRight());
                }
            }if(pila.size()==1 && lista.size()==1){
                BinaryTree<String> subarbolmi=pila.pop();
                String fina=lista.removeLast().trim();
                //Deberia añadir a solo donde pertenece pero guarda tambien donde no
            }
            
        }
        arbol.IterativeInOrden(); //esto es para visualizar el ingreso en cada iteracion
    }
}