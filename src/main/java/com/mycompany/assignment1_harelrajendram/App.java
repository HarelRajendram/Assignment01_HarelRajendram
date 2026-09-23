package com.mycompany.assignment1_harelrajendram;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox vb = new VBox(8);
        vb.setAlignment(Pos.CENTER);
        
        String[] phrases = {"Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
             "The quick brown fox jumps over the lazy dog.",
              "Five big quacking zephyrs jolt my wax bed.",
                "Sympathizing would fix Quaker objectives.",
                "A large fawn jumped quickly over white zinc boxes."};
        
        Map<String , Button> buttonMap = new HashMap<>();
        
        String[][] letters = {
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
            {"SHIFT","Z", "X", "C", "V", "B", "N", "M","BACKSPACE"},
            {"SPACE"}
        };
        
        for(int i = 0; i < letters.length; i++) {
            HBox hb = new HBox(5);
            hb.setAlignment(Pos.CENTER);
                
            for (int j = 0; j < letters[i].length; j++) {
                String keyWord = letters[i][j];
                
                 Button button = new Button(keyWord);
                 
                 buttonMap.put(keyWord , button);
                 
                 hb.getChildren().add(button);
            }
             vb.getChildren().add(hb);
        }
     
        
        
        var scene = new Scene(vb, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}