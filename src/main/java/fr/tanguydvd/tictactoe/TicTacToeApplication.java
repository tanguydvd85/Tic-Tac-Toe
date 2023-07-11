package fr.tanguydvd.tictactoe;

import fr.tanguydvd.tictactoe.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 668, 400);
        stage.setTitle("Tic Tac Toe !");
        stage.setScene(scene);

        FXMLLoader fxmlGameRulesLoader = new FXMLLoader(TicTacToeApplication.class.getResource("gameRules-view.fxml"));
        Scene sceneRules = new Scene(fxmlGameRulesLoader.load());
        Stage stageRules = new Stage();

        stageRules.setScene(sceneRules);
        stageRules.initOwner(stage);
        stageRules.setTitle("Règles du Jeu");

        MainController mainController = fxmlLoader.getController();
        mainController.setStageRules(stageRules);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}