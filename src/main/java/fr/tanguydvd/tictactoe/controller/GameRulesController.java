package fr.tanguydvd.tictactoe.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameRulesController {
    @FXML
    void understood(ActionEvent event) {
        Button source = (Button)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

}
