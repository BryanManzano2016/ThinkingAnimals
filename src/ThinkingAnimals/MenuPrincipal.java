/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThinkingAnimals;



import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Axel
 */
public class MenuPrincipal {
    VBox root;
    Button play,salir,si,no;
    Label lbl,preg;
    HBox menu=new HBox(100);
    public MenuPrincipal(){
        root=new VBox(20);
        lbl=new Label("Adivinalo!");
        lbl.setFont(new Font("Arial",40));
        root.setPadding(new Insets(50,250,50,250));
        play= new Button("Jugar");
        play.setMinSize(200, 50);
        salir= new Button("Salir");
        salir.setMinSize(200, 50);
        si=new Button("Si");
        no=new Button("No");
        preg= new Label("Pregunta 1");
        root.getChildren().addAll(lbl,play,salir);
        play.setOnAction(e-> Jugar());
        salir.setOnAction(e->System.exit(0));
        Efectos();
    }
    public void Efectos(){
        
        salir.setOnMouseEntered(e->salir.setEffect(new DropShadow(10, Color.AQUA)));
        salir.setOnMouseExited(e->salir.setEffect(null));
        play.setOnMouseEntered(e->play.setEffect(new DropShadow(10, Color.AQUA)));
        play.setOnMouseExited(e->play.setEffect(null));
    }
    public  Parent getroot(){
        return root;
    }
    private void Jugar() {
        System.out.println("A jJugar!");
        root.getChildren().removeAll(lbl,play,salir);
        menu.getChildren().addAll(si,no);
        root.getChildren().addAll(preg,menu);
    }
}
