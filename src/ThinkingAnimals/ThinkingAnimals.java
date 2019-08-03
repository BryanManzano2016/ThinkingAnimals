 
package ThinkingAnimals;

import Juego.ControlJuego;
import java.util.LinkedList;
import java.util.Scanner;
 
public class ThinkingAnimals {
 
    public static void main(String[] args){
        // Objeto que contiene el arbol y la carga de archivos
        ControlJuego control = new ControlJuego();
        control.constructorArbol();
        
        Scanner sc = new Scanner(System.in);

        int numPreguntas = -1;
        // Bucle infinito hasta que el nro de preguntas sea 0
        while (numPreguntas != 0) {

            System.out.println("\n***************************");
            System.out.println("Nro de preguntas: ");
            try{
                numPreguntas = Integer.parseInt(sc.nextLine());
                // Si hay mas preguntas que el arbol pueda responder continue
                if (numPreguntas > control.getPreguntas().size()) {
                    System.out.println("Excede el nro de preguntas");
                    continue;
                }
            }catch(NumberFormatException e){
                System.out.println("No es numero posible");
                continue;
            }
            
            boolean validar = true;
            for (int i: control.getPreguntas().keySet()) {
                if (i == numPreguntas)
                    break;  
                System.out.println(control.getPreguntas().get(i));

                String respuesta = sc.nextLine();
                // Si NO hay movimiento valido
                if (!((respuesta.equals("si") || respuesta.equals("no")) && control.changeNode(respuesta))){
                    System.out.println("No tengo repuesta para ese animal");
                    validar = false;
                    break;
                }
            }
            
            if (validar) {
                // Llama a las posibles respuestas
                LinkedList<String> respuestas = control.posibleAnswer();
                    System.out.println("Es posible que el animal sea: ");
                    for(int i=0;i<=respuestas.size()-1;i++){
                        if(i!=respuestas.size()-1){
                            System.out.print(respuestas.get(i)+" o ");
                        }else{
                            System.out.print(respuestas.get(i));
                        }
                    }         
            }
            // Reinicia el arbol viajero
            control.restartTreeAnswer();     
            
        }
        

    }
}
