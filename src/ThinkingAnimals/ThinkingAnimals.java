/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThinkingAnimals;

import Juego.ControlArchivosIO;
import Juego.ControlJuego;
import java.util.HashMap;
import java.util.LinkedList;
import tree.BinaryTree;

/**
 *
 * @author Lesther Carranza
 */
public class ThinkingAnimals {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ControlJuego control=new ControlJuego();
        BinaryTree<String> arbol = control.constructorArbol();
        arbol.IterativeInOrden();
        System.out.println(arbol.iterativecountSons(arbol));
        HashMap<String,LinkedList<String>> respuestas = ControlArchivosIO.lecturaArchivoRespuestas("respuestas.txt");
        respuestas.forEach((texto,list)->System.out.println(texto+"=>"+list));
       
    }
    
}
