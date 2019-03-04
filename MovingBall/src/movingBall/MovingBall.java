/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movingBall;

import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author L
 */
public class MovingBall extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        Random ran=new Random();
        
        Pane root=new Pane();
        Scene scene = new Scene(root, 800, 600);
        scene.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.SPACE)) {
                Circle c=new Circle(0,0,40);
                c.setFill(Color.color(ran.nextDouble(),ran.nextDouble(),ran.nextDouble(),ran.nextDouble()));
                c.setSmooth(true);
                move(c);
                root.getChildren().add(c);
                System.out.println("new Ball");
            }
            if(e.getCode().equals(KeyCode.ESCAPE)){
                root.getChildren().removeAll(root.getChildren());
                System.out.println("remove all");
            }
        });
        
        
        primaryStage.setTitle("Moving a ball");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    Point2D randomPoint(){
        Random ran=new Random();
        int n=ran.nextInt(4);
        
        switch(n){
            case 0: 
                n++;
                return new Point2D(800,ran.nextInt(600));
            case 1: 
                n++;
                return new Point2D(ran.nextInt(800),600);
            case 2: 
                n++;
                return new Point2D(0,ran.nextInt(600));
            case 3: 
                n=0;
                return new Point2D(ran.nextInt(800),0);
            
        }
        return new Point2D(800,ran.nextInt(600));
    }
    void move(Circle ball){
        TranslateTransition tran=new TranslateTransition(Duration.seconds(1), ball);
        Point2D p=randomPoint();
        tran.setToX(p.getX());
        tran.setToY(p.getY());
        tran.setOnFinished(e->{
            move(ball);
        });
        tran.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
