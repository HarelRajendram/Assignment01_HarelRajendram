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
    private int currentIndex;

    @Override
    public void start(Stage stage) {
        VBox vb = new VBox(8);
        vb.setAlignment(Pos.CENTER);
        
        TextField userTypedText = new TextField();
    
        String[] phrases = {"Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
             "The quick brown fox jumps over the lazy dog.",
              "Five big quacking zephyrs jolt my wax bed.",
                "Sympathizing would fix Quaker objectives.",
                "A large fawn jumped quickly over white zinc boxes."};
        
        Label counterLabel = new Label((currentIndex + 1) +" of " + phrases.length);
         Label accuracyLabel = new Label("Correct: 0 | Incorrect: 0");
        Label statusLabel = new Label("Key pressed: None");
        Label prompText = new Label(phrases[currentIndex]);
        
        Button nextButton = new Button("Next");
        Button resetButton = new Button("Reset");
        
        HBox controlBx = new HBox(10 ,nextButton, resetButton, counterLabel );
        controlBx.setAlignment(Pos.CENTER);
        
         vb.getChildren().addAll(controlBx, prompText,userTypedText ,statusLabel,accuracyLabel);
         
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
        Runnable updatePhrase = () -> {
        prompText.setText(phrases[currentIndex]);
        userTypedText.clear();
        counterLabel.setText((currentIndex + 1) + " of " + phrases.length);
        
        };
        
        nextButton.setOnAction(e -> {
            if (currentIndex < phrases.length - 1) {
                currentIndex++;
                
                updatePhrase.run();
            }
        });
       
        var scene = new Scene(vb, 640, 480);
        
        scene.setOnKeyPressed(event -> {
        String keyPressed = event.getCode().toString();
        
        if (keyPressed.equals("BACK_SPACE")) {
        keyPressed = "BACKSPACE";
    }
        
        Button virtualLetter = buttonMap.get(keyPressed);
           
        if (virtualLetter != null) {
        virtualLetter.setStyle("-fx-background-color: #0078D7;-fx-text-fill: white;");
        statusLabel.setText("key pressed: " + keyPressed);
        statusLabel.setStyle("-fx-text-fill:black;");
        
        } else {
            
        statusLabel.setText("Not handled");
        statusLabel.setStyle("-fx-text-fill:red;");
        
        }
        });
       userTypedText.textProperty().addListener((observable, oldValue, newValue) -> {
    String currentPhrase = phrases[currentIndex];

    if (newValue.length() > oldValue.length()) {
        int typedIndex = newValue.length() - 1;

        if (typedIndex < currentPhrase.length()) {
            char expected = Character.toUpperCase(currentPhrase.charAt(typedIndex));
            char typed = Character.toUpperCase(newValue.charAt(typedIndex));

            if (expected == typed) {
                correctLetter++;
            } else {
                wrongLetters++;
            }
            accuracyLabel.setText("Correct: " + correctLetter + " | Incorrect: " + wrongLetters);
        }
    }
     if (newValue.length() >= currentPhrase.length()) {
            if (currentIndex < phrases.length - 1) {
                currentIndex++;
                updatePhrase.run();
            } else {
            prompText.setText("Congratulations you have completed all the phrases! ");
            userTypedText.setDisable(true);
            }
        }
     });
        
        scene.setOnKeyReleased(event -> {
            String keyPressed = event.getCode().toString();
            if (keyPressed.equals("BACK_SPACE")) {
        keyPressed = "BACKSPACE";
    }
            Button virtualLetter = buttonMap.get(keyPressed);
            
            if (virtualLetter != null) {
                virtualLetter.setStyle("");
            }
        });
        resetButton.setOnAction(e -> {
            correctLetter = 0;
            wrongLetters = 0;
            currentIndex = 0;
            userTypedText.setDisable(false);
            accuracyLabel.setText("Correct: 0 | Incorrect: 0");
            statusLabel.setText("Key pressed: None");
            statusLabel.setStyle("-fx-text-fill: black;");
            updatePhrase.run();
            
        });
        
        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
        
        userTypedText.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

}