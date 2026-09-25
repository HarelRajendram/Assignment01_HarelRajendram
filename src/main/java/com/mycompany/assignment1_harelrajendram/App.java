package com.mycompany.assignment1_harelrajendram;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private int correctLetter;
    private int wrongLetters;

    @Override
    public void start(Stage stage) {
        VBox vb = new VBox(8);
        vb.setAlignment(Pos.CENTER);
        
        int currentIndex = 0;
        
        TextField userTypedText = new TextField();
    
        String[] phrases = {"Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
             "The quick brown fox jumps over the lazy dog.",
              "Five big quacking zephyrs jolt my wax bed.",
                "Sympathizing would fix Quaker objectives.",
                "A large fawn jumped quickly over white zinc boxes."};
        
         Label accuracyLabel = new Label("Correct: 0 | Incorrect: 0");
        Label statusLabel = new Label("Key pressed: None");
        Label prompText = new Label(phrases[currentIndex]);
        
        
        vb.getChildren().addAll(prompText,userTypedText ,statusLabel,accuracyLabel);
        
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
                 button.setFocusTraversable(false);
                 buttonMap.put(keyWord , button);
                 
                 hb.getChildren().add(button);
                 if (keyWord.equals("SPACE")) {
                     button.setPrefWidth(190);
                 }
                  if (keyWord.equals("SHIFT") || keyWord.equals("BACKSPACE")) {
                      button.setPrefWidth(90);
                }
            }
             vb.getChildren().add(hb);
        }
       
        
        var scene = new Scene(vb, 640, 480);
        
        scene.setOnKeyPressed(event -> {
        String keyPressed = event.getCode().toString();
        Button virtualLetter = buttonMap.get(keyPressed);
        
         int targetIndex = userTypedText.getText().length();
        String currentPhrase = phrases[currentIndex];
        
        boolean isValidChar = false;
        char typed = ' ';
        
            if (keyPressed.length() == 1) {
            typed = keyPressed.charAt(0);
            isValidChar = true;
       
        
        char expected = Character.toUpperCase(currentPhrase.charAt(targetIndex));
       
        } else if (keyPressed.equals("SPACE")) {
            typed = ' ';
            isValidChar = true;
            
            userTypedText.appendText(" ");
        
        }
            if (isValidChar && targetIndex < currentPhrase.length()) {
                char expected = Character.toUpperCase(currentPhrase.charAt(targetIndex));            
           
                if (expected == typed)  {
                    correctLetter += 1;
            } else {
           wrongLetters += 1;
            }
        accuracyLabel.setText("Correct: " + correctLetter + " | Incorrect: " + wrongLetters);

    }
          
        if (virtualLetter != null) {
        virtualLetter.setStyle("-fx-background-color: #0078D7;-fx-text-fill: white;");
        statusLabel.setText("key pressed: " + keyPressed);
        statusLabel.setStyle("-fx-text-fill:black;");
        
        } else {
            
        statusLabel.setText("Not handled");
        statusLabel.setStyle("-fx-text-fill:red;");
        }
        if (userTypedText.getText().length() >= currentPhrase.length()) {
            currentIndex++;
            
            if ()
        }
        
        });
        
        scene.setOnKeyReleased(event -> {
            String keyPressed = event.getCode().toString();
            Button virtualLetter = buttonMap.get(keyPressed);
            
            if (virtualLetter != null) {
                virtualLetter.setStyle("");
            }
        });
        
        stage.setScene(scene);
        stage.show();
        
        userTypedText.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

}