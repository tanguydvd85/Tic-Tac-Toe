module fr.tanguydvd.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.tanguydvd.tictactoe to javafx.fxml;
    exports fr.tanguydvd.tictactoe;
    exports fr.tanguydvd.tictactoe.controller;
    opens fr.tanguydvd.tictactoe.controller to javafx.fxml;
}