
package Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import tree.BinaryTree;

public class ControlJuego{
    
    private HashMap<Integer,String> preguntas;
    private HashMap<String,LinkedList<String>> respuestas;
    private BinaryTree<Pregunta> arbol;
    
    private BinaryTree<Pregunta> treeAnswer;
    
    public ControlJuego() {}
    
    public void constructorArbol(String pathPreguntas,String pathRespuestas){
        // Maps con preguntas y respuestas
        preguntas = (HashMap<Integer, String>) ControlArchivosIO.lecturaArchivo(pathPreguntas);
        respuestas = (HashMap<String, LinkedList<String>>) ControlArchivosIO.lecturaArchivoRespuestas(pathRespuestas);
        // Crea un arbol con la 1ra pregunta
        this.arbol = new BinaryTree<>( new Pregunta(preguntas.get(0), "") );
     
        // Las otras preguntas se crean como subarboles que cada nivel tiene una pregunta igual
        Set<Integer> keypreguntas = preguntas.keySet();
        keypreguntas.forEach((i) -> {
            if (i != 0) {
                this.arbol.setChildrens( preguntas.get(i) );
            }
        });
        // Por cada "camino de desicion" que tiene un animal
        respuestas.forEach((animal, camino)->setAnswer(animal, camino,preguntas));
        // Remueve todos las hojas que no tienen respuesta

        //this.arbol.removeChildrenWithoutAnwers(this.arbol);
        
        this.treeAnswer = this.arbol;
    }
    // Usar para asignar el animal a un node segun la ruta
    public void setAnswers(String animal, LinkedList<String> lista){
        if(!lista.isEmpty() && arbol != null){
            Stack<BinaryTree<Pregunta>> pila = new Stack<>();
            pila.push(this.arbol);
            
            while (!pila.isEmpty()) {
                BinaryTree<Pregunta> nodo = pila.pop();
                String decision = "";
                
                // Se vaciara la lista de decisiones
                if (!lista.isEmpty())
                    decision = lista.removeFirst();
                // Solo añado si esta en el camino
                if (nodo.getLeft() != null && decision.equals("no"))
                    pila.push(nodo.getLeft());
                else if (nodo.getRight() != null && decision.equals("si"))
                    pila.push(nodo.getRight());
                    
                if (lista.isEmpty()){
                    // Crea un nodo(que es el arbol) o lo actualiza
                    if (nodo.getRight() != null && decision.equals("si"))
                            nodo.getRight().getRoot().getContent().setRespuesta(animal);
                    else if (nodo.getRight() == null && decision.equals("si"))
                        nodo.setRight(new BinaryTree<>(new Pregunta("", animal)));
                    
                    if (nodo.getLeft() != null && decision.equals("no"))
                        nodo.getLeft().getRoot().getContent().setRespuesta(animal);
                    else if (nodo.getLeft() == null && decision.equals("no"))
                        nodo.setLeft(new BinaryTree<>(new Pregunta("", animal)));
                }
            }
        }
    }
    
    public void setAnswer(String animal, LinkedList<String> lista,HashMap<Integer,String> preguntas){
        if(!lista.isEmpty() && arbol != null){
            Stack<BinaryTree<Pregunta>> pila = new Stack<>();
            pila.push(this.arbol);
            int contador=0;
            while (!pila.isEmpty()) {
                contador++;
                BinaryTree<Pregunta> nodo = pila.pop();
                String decision = "";
                
                // Se vaciara la lista de decisiones
                if (!lista.isEmpty())
                    decision = lista.removeFirst();
                // Solo añado si esta en el camino
                if (decision.equals("no")){
                    if (nodo.getLeft() == null)
                        nodo.setLeft(new BinaryTree<>(new Pregunta(preguntas.get(contador-1),"")));
                    pila.push(nodo.getLeft());
                }
                else if (decision.equals("si")){
                    if (nodo.getRight()== null)
                        nodo.setRight(new BinaryTree<>(new Pregunta(preguntas.get(contador-1),"")));
                    pila.push(nodo.getRight());
                }
                    
                if (lista.isEmpty()){
                    // Crea un nodo(que es el arbol) o lo actualiza
                    if (nodo.getRight() != null && decision.equals("si"))
                            nodo.getRight().getRoot().getContent().setRespuesta(animal);
                    else if (nodo.getRight() == null && decision.equals("si"))
                        nodo.setRight(new BinaryTree<>(new Pregunta("", animal)));
                    
                    if (nodo.getLeft() != null && decision.equals("no"))
                        nodo.getLeft().getRoot().getContent().setRespuesta(animal);
                    else if (nodo.getLeft() == null && decision.equals("no"))
                        nodo.setLeft(new BinaryTree<>(new Pregunta("", animal)));
                }
            }
        }
    }
    
    // Si hay una decision y ese nodo existe el arbol biajero cambia
    public boolean changeNode(String st){
        if (st.equals("si") && treeAnswer.getRight() != null) {
            treeAnswer = treeAnswer.getRight();
            return true;
        }
        if( st.equals("no") && treeAnswer.getLeft() != null){
            treeAnswer = treeAnswer.getLeft();
            return true;
        }
        return false;
    }
    // Guarda todos los arboles hijos de la decision del usuario
    public LinkedList<String> posibleAnswer(){
        LinkedList<String> array = treeAnswer.getChildrensAnswers(treeAnswer);
        return array;
    }

    public BinaryTree<Pregunta> getTreeAnswer() {
        return treeAnswer;
    }
    // Reinicia el arbol viajero con las decisiones tomadas, es decir la raiz toma lugar aqui
    public void restartTreeAnswer(){
        this.treeAnswer = this.arbol;
    }
    
    public HashMap<Integer, String> getPreguntas() {
        return preguntas;
    }

    public HashMap<String, LinkedList<String>> getRespuestas() {
        return respuestas;
    }

    public BinaryTree<Pregunta> getArbol() {
        return arbol;
    }
}
