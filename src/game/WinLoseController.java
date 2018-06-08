package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinLoseController implements Initializable {

    private final String winner;
    private final String playerX;
    private final String playerO;
    private final boolean single;

    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message.setText(winner);
    }

    WinLoseController(String winner, String playerX, String playerO, boolean single) {
        this.winner = winner;
        this.playerX = playerX;
        this.playerO = playerO;
        this.single = single;
    }

    /* Closes the game */
    public void close() {
        message.getScene().getWindow().hide();
    }

    /* Restart the game (Continue playing) */
    public void replay() throws IOException {
        message.getScene().getWindow().hide();

        Stage newStage = new Stage();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));
        loader.setController(new TicTacToeController(playerX, playerO, single));

        Parent root = loader.load();
        newStage.setTitle("Tic Tac Toe");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    /* Start a new game */
    public void restart() throws IOException {
        message.getScene().getWindow().hide();

        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("playerName.fxml"));
        newStage.setTitle("Enter Players");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

}
