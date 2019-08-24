/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Model.ControlArchivosIO;
import Model.ControlJuego;
import Model.Pregunta;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import tree.BinaryTree;

/**
 *
 * @author Axel
 */
public class FrmPregunta extends javax.swing.JFrame {

    /**
     * Creates new form FrmPregunta
     */
    
    private HashMap<Integer,String> preguntas;
    private ControlJuego control;
    private int pos = 0;
    private String pathrespuestas;
    private BinaryTree<Pregunta> arbol;
    private int numeroPreguntas;
    private boolean validar = true;
    private LinkedList<String> lista = new LinkedList<>();
    
    public FrmPregunta(HashMap<Integer,String> preguntas, ControlJuego control,int numeroPreguntas,String path) {
        
        initComponents();
        this.pathrespuestas = path;
        this.preguntas = preguntas;
        arbol = control.getArbol();
        this.control = control;
        this.numeroPreguntas = numeroPreguntas;
        this.lblPregunta.setText(preguntas.get(pos++));
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPregunta = new javax.swing.JLabel();
        cbSi = new javax.swing.JButton();
        cbNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPregunta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPregunta.setText("PREGUNTA");

        cbSi.setText("SI");
        cbSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSiActionPerformed(evt);
            }
        });

        cbNo.setText("NO");
        cbNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(cbSi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(cbNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSiActionPerformed
        // TODO add your handling code here:
        lista.add("si");
        Ingreso(arbol.getRight());
        arbol = arbol.getRight();
        if(pos<numeroPreguntas)
            if(this.control.changeNode("si")){
                callRespuestas();
                this.lblPregunta.setText(preguntas.get(pos++));
            }        
            else{
                validar = false;
                JOptionPane.showMessageDialog(this, "¡No poseo ese animal!", "Error", JOptionPane.ERROR_MESSAGE);
                reinicioJuego();
            }
        else{
            pos = preguntas.keySet().size();
            callRespuestas();
        }
    }//GEN-LAST:event_cbSiActionPerformed
    
    private void reinicioJuego(){
        this.setVisible(false);
        FrmHome frm = new FrmHome();
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
    
    private String respuestaFormada(String ingreso){
        String result = ingreso;
        for(int i=0; i<=lista.size()-1; i++){
            result = result + " "+ lista.get(i);
        }
        return result;
    }
    
    private void Ingreso(BinaryTree<Pregunta> subarbol){
        if(subarbol == null){
            int salida = JOptionPane.showConfirmDialog(this,"No poseo ese animal, Deseas agregarlo?","Pregunta",JOptionPane.YES_NO_OPTION);
            if(salida == JOptionPane.YES_OPTION){
                String palabra = JOptionPane.showInputDialog("¿Cual es el animal?");
                ControlArchivosIO.ingresoNuevaRespuesta(respuestaFormada(palabra), pathrespuestas);
                JOptionPane.showMessageDialog(this, "El ingreso fue exitoso", "Ingreso Exitoso", JOptionPane.INFORMATION_MESSAGE);
                reinicioJuego();
                
            }else{
                JOptionPane.showMessageDialog(this, "Sigue jugando", "Intentalo", JOptionPane.INFORMATION_MESSAGE);
                reinicioJuego();
            }
        }
    }
    private void cbNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNoActionPerformed
        // TODO add your handling code here:
        lista.add("no");
        Ingreso(arbol.getLeft());
        arbol = arbol.getLeft();
        if(pos<numeroPreguntas)
            if(this.control.changeNode("no")){
                callRespuestas();
                this.lblPregunta.setText(preguntas.get(pos++));
            }
            else{
                validar=false;
                JOptionPane.showMessageDialog(this, "¡No poseo ese animal!", "Error", JOptionPane.ERROR_MESSAGE);
                reinicioJuego();
            }
        else{
            pos=preguntas.keySet().size();
            callRespuestas();
        }
    }//GEN-LAST:event_cbNoActionPerformed

    private void callRespuestas() {
        if(pos == preguntas.keySet().size() && validar){
            LinkedList<String> respuestas = control.posibleAnswer();
            String resp = "";
            if(arbol != null){
                if(respuestas.size() != 1){
                    resp = arbol.getChildrensAnswers(arbol).toString();
                    resp = resp.replace("[","").replace("]","").replace(",", " o ");
                }else{
                    resp = arbol.getRoot().getContent().getRespuesta(); 
                }

                if (!resp.equals("")) {
                    JOptionPane.showMessageDialog(this, "¡Es posible que el animal sea " + resp + "!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(this, "No se encontro ningun animal posible!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                reinicioJuego();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cbNo;
    private javax.swing.JButton cbSi;
    private javax.swing.JLabel lblPregunta;
    // End of variables declaration//GEN-END:variables
}
