package com.mycompany.assignment1_harelrajendram;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        String[] phrases = {"Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
             "The quick brown fox jumps over the lazy dog.",
              "Five big quacking zephyrs jolt my wax bed.",
                "Sympathizing would fix Quaker objectives.",
                "A large fawn jumped quickly over white zinc boxes."};
        
        GridPane grid = new GridPane();
        Button button = new Button();
        Map<String , Button> buttonMap = new HashMap<>();
        
        String[][] letters = {
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
            {"SHIFT","Z", "X", "C", "V", "B", "N", "M","BACKSPACE"},
            {"SPACE"}
        };
        
        for(int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[i].length; j++) {
                String keyWord = letters[i][j];
            }
        }
        Button button = new Button(keyWord);
      
        
        
        var scene = new Scene(grid, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}