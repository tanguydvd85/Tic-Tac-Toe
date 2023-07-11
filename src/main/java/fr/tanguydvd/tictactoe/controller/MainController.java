package fr.tanguydvd.tictactoe.controller;

import fr.tanguydvd.tictactoe.models.Game;
import fr.tanguydvd.tictactoe.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private RadioButton gameModeFriends;

    @FXML
    private RadioButton gameModeIA;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField playerOne;

    @FXML
    private Label playerOneLabel;

    @FXML
    private Label playerOneScore;

    @FXML
    private TextField playerOneTextField;

    @FXML
    private Label playerTwoLabel;

    @FXML
    private Label playerTwoScore;

    @FXML
    private TextField playerTwoTextField;

    @FXML
    private CheckBox randomSelectionChecked;
    @FXML
    private Button replayBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private Button newGameBtn;
    @FXML
    private Label informationLabel;
    private Game currentGame;
    boolean isFinish;
    private Stage stageRules;

    public void setStageRules(Stage stageRules) {
        this.stageRules = stageRules;
    }
    @FXML
    void initialize() {
        for (Node node: gridPane.getChildren()) {
            Button button = (Button) node;
            button.setBackground(Background.EMPTY);
            button.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        }
        gridPane.setDisable(true);
        gameModeFriends.setSelected(true);
        selectGameModeFriends();
        replayBtn.setVisible(false);
        resetBtn.setVisible(false);
        newGameBtn.setVisible(false);
        informationLabel.setText("Bonne chance !");
        isFinish = false;
    }

    @FXML
    void createNewGame() {
        for (Node node: gridPane.getChildren()) {
            Button button = (Button) node;
            button.setDisable(false);
            button.setBackground(Background.EMPTY);
            button.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            button.setText("");
        }
        gridPane.setDisable(true);
        playerOneLabel.setText("Joueur 1");
        playerTwoLabel.setText("Joueur 2");
        playerOneScore.setText("0");
        playerTwoScore.setText("0");
        playerOneTextField.setText("");
        playerTwoTextField.setText("");
        playerOne.setText("");
        replayBtn.setVisible(false);
        resetBtn.setVisible(false);
        newGameBtn.setVisible(false);
    }

    @FXML
    void openGamesRules(ActionEvent event) {
        stageRules.show();
    }

    @FXML
    void quitLauncher(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de sortie");
        alert.setHeaderText("Êtes-vous sûr de vouloir quitter ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    @FXML
    void replay() {
        for (Node node: gridPane.getChildren()) {
            Button button = (Button) node;
            button.setDisable(false);
            button.setBackground(Background.EMPTY);
            button.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            button.setText("");
        }
        replayBtn.setVisible(false);
        resetBtn.setVisible(false);
        newGameBtn.setVisible(false);
        isFinish = false;
        informationLabel.setText("Bonne chance !");
        gridPane.setDisable(false);
    }

    @FXML
    void reset(ActionEvent event) {
        gridPane.setDisable(false);
        for (Node node: gridPane.getChildren()) {
            Button button = (Button) node;
            button.setDisable(false);
            button.setBackground(Background.EMPTY);
            button.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            button.setText("");
        }
        replayBtn.setVisible(false);
        resetBtn.setVisible(false);
        newGameBtn.setVisible(false);
        isFinish = false;
        informationLabel.setText("Bonne chance !");
        currentGame.getPlayerOne().setScore(0);
        currentGame.getPlayerTwo().setScore(0);
        playerOneScore.setText(String.valueOf(currentGame.getPlayerOne().getScore()));
        playerTwoScore.setText(String.valueOf(currentGame.getPlayerTwo().getScore()));
    }

    @FXML
    void selectGameModeFriends() {
        if (gameModeFriends.isSelected()) {
            gameModeIA.setSelected(false);
            playerOne.setDisable(true);
            playerOneTextField.setDisable(false);
            playerTwoTextField.setDisable(false);
            randomSelectionChecked.setDisable(false);
        }
    }

    @FXML
    void selectGameModeIA() {
        if (gameModeIA.isSelected()) {
            gameModeFriends.setSelected(false);
            playerOneTextField.setDisable(true);
            playerTwoTextField.setDisable(true);
            randomSelectionChecked.setDisable(true);
            playerOne.setDisable(false);
        }
    }

    @FXML
    void setUpButton(ActionEvent event) {
        if (gameModeFriends.isSelected()) {
            Button button = (Button) event.getSource();
            button.setText(currentGame.getCurrentPlayer().getSymbol());
            currentGame.changePlayer();
            button.setDisable(true);
            finish();
        } else if (gameModeIA.isSelected()) {
            Button button = (Button) event.getSource();
            button.setText(currentGame.getPlayerOne().getSymbol());
            currentGame.changePlayer();
            button.setDisable(true);
            finish();

            ArrayList<Button> buttons = new ArrayList<>();
            for (Node node : gridPane.getChildren()) {
                Button button2 = (Button) node;
                if (button2.getText().isEmpty()) {
                    buttons.add(button2);
                }
            }

            Collections.shuffle(buttons);
            buttons.get(0).setText(currentGame.getPlayerTwo().getSymbol());

            currentGame.changePlayer();
            finish();
        }

    }
    void finish() {
        if (boardIsFull(gridPane)) {
            informationLabel.setText("Égalité !");
            gridPane.setDisable(true);
            replayBtn.setVisible(true);
            resetBtn.setVisible(true);
            newGameBtn.setVisible(true);
            isFinish = true;
        }
        if (checkGridPaneConsistency()) {
            currentGame.changePlayer();
            informationLabel.setText(currentGame.getCurrentPlayer().getName() + " a gagné !");
            gridPane.setDisable(true);
            replayBtn.setVisible(true);
            resetBtn.setVisible(true);
            newGameBtn.setVisible(true);
            isFinish = true;
            currentGame.getCurrentPlayer().incrementScore();
            playerOneScore.setText(String.valueOf(currentGame.getPlayerOne().getScore()));
            playerTwoScore.setText(String.valueOf(currentGame.getPlayerTwo().getScore()));
        }
    }

    @FXML
    void startGame(ActionEvent event) {
        if (gameModeFriends.isSelected()) {
            if (!playerOneTextField.getText().isEmpty() && !playerTwoTextField.getText().isEmpty()) {
                if (!randomSelectionChecked.isSelected()) {
                    currentGame = new Game(new Player(playerOneTextField.getText(), "X"), new Player(playerTwoTextField.getText(), "O"));
                    gridPane.setDisable(false);
                    playerOneLabel.setText(playerOneTextField.getText());
                    playerTwoLabel.setText(playerTwoTextField.getText());
                    informationLabel.setText("Bonne chance !");
                } else if (randomSelectionChecked.isSelected()){
                    ArrayList<String> players = randomPlayers(playerOneTextField.getText(),playerTwoTextField.getText());
                    currentGame = new Game(new Player(players.get(0), "X"), new Player(players.get(1), "O"));
                    gridPane.setDisable(false);
                    playerOneLabel.setText(players.get(0));
                    playerTwoLabel.setText(players.get(1));
                    informationLabel.setText("Bonne chance !");
                }
            } else {
                informationLabel.setText("Sélectionnez le(s) nom(s) du ou des joueur(s)");
            }
        } else if (gameModeIA.isSelected()) {
            if (playerOne.getText().isEmpty()) {
                informationLabel.setText("Sélectionnez le(s) nom(s) du ou des joueur(s)");
            } else {
                currentGame = new Game(new Player(playerOne.getText(), "X"), new Player("Ordinateur", "O"));
                gridPane.setDisable(false);
                playerOneLabel.setText(playerOne.getText());
                playerTwoLabel.setText(currentGame.getPlayerTwo().getName());
                informationLabel.setText("Bonne chance !");
            }
        }
    }

    private ArrayList<String> randomPlayers(String playerOne, String playerTwo) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList(playerOne,playerTwo));
        Collections.shuffle(names);
        return names;
    }

    boolean boardIsFull(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            Button button = (Button) node;
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkGridPaneConsistency() {
        //Check diagonals
        if (!button1.getText().isEmpty()) {
            if (button1.getText().equals(button5.getText()) && button5.getText().equals(button9.getText())) {
                button1.setBackground(Background.fill(Color.GREEN));
                button5.setBackground(Background.fill(Color.GREEN));
                button9.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }
        if (!button3.getText().isEmpty()) {
            if (button3.getText().equals(button5.getText()) && button5.getText().equals(button7.getText())) {
                button3.setBackground(Background.fill(Color.GREEN));
                button5.setBackground(Background.fill(Color.GREEN));
                button7.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }

        //Check Columns
        if (!button1.getText().isEmpty()) {
            if (button1.getText().equals(button4.getText()) && button4.getText().equals(button7.getText())) {
                button1.setBackground(Background.fill(Color.GREEN));
                button4.setBackground(Background.fill(Color.GREEN));
                button7.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }
        if (!button2.getText().isEmpty()) {
            if (button2.getText().equals(button5.getText()) && button5.getText().equals(button8.getText())) {
                button2.setBackground(Background.fill(Color.GREEN));
                button5.setBackground(Background.fill(Color.GREEN));
                button8.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }
        if (!button3.getText().isEmpty()) {
            if (button3.getText().equals(button6.getText()) && button6.getText().equals(button9.getText())) {
                button3.setBackground(Background.fill(Color.GREEN));
                button6.setBackground(Background.fill(Color.GREEN));
                button9.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }

        //Check rows
        if (!button1.getText().isEmpty()) {
            if (button1.getText().equals(button2.getText()) && button2.getText().equals(button3.getText())) {
                button1.setBackground(Background.fill(Color.GREEN));
                button2.setBackground(Background.fill(Color.GREEN));
                button3.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }
        if (!button4.getText().isEmpty()) {
            if (button4.getText().equals(button5.getText()) && button5.getText().equals(button6.getText())) {
                button4.setBackground(Background.fill(Color.GREEN));
                button5.setBackground(Background.fill(Color.GREEN));
                button6.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }
        if (!button7.getText().isEmpty()) {
            if (button7.getText().equals(button8.getText()) && button8.getText().equals(button9.getText())) {
                button7.setBackground(Background.fill(Color.GREEN));
                button8.setBackground(Background.fill(Color.GREEN));
                button9.setBackground(Background.fill(Color.GREEN));
                return true;
            }
        }
        return false;
    }
}
