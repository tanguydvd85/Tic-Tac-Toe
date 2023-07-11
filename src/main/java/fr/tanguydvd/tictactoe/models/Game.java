package fr.tanguydvd.tictactoe.models;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void changePlayer() {
        if (currentPlayer.equals(playerOne)) currentPlayer = playerTwo;
        else currentPlayer = playerOne;
    }
}
