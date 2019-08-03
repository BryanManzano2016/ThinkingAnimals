/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lesther Carranza
 */
public class ControlArchivosIO {
    
    private static File existFile(String nombre){
        File archivo = new File(nombre);
        if(!archivo.exists()){
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ControlArchivosIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return archivo;
    }
    
    public static HashMap<Integer,String> lecturaArchivo(String nombre){
        int contador=0;
        HashMap<Integer,String> mapa = new HashMap<>();
        String cadena;
        try {
            FileReader fr = new FileReader(existFile(nombre));
            try (BufferedReader br = new BufferedReader(fr)) {
                while((cadena=br.readLine())!=null){
                    mapa.put(contador, cadena);
                    contador++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ControlArchivosIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;
    }
    
    public static HashMap<String,LinkedList<String>> lecturaArchivoRespuestas(String nombre){
        HashMap<String,LinkedList<String>> mapa= new HashMap<>();
        String cadena;
        try {
            FileReader fr = new FileReader(existFile(nombre));
            BufferedReader br = new BufferedReader(fr);
            while((cadena=br.readLine())!=null){
                String[] respuesta=cadena.split(" ");
                LinkedList<String> answers=new LinkedList<>();
                for(int i=1;i<=respuesta.length-1;i++){
                    answers.addLast(respuesta[i]);
                }
                mapa.put(respuesta[0],answers);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlArchivosIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControlArchivosIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;
    }
}
