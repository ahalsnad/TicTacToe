package game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerNameController implements Initializable {

    @FXML
    private TextField playerX;

    @FXML
    private TextField playerO;

    @FXML
    private RadioButton single;

    @FXML
    private RadioButton two;

    @FXML
    private Button submit;

    @FXML
    private Label oName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setDisable(true);
    }

    /* Takes the player names and displays the tic-tac-toe board */
    public void submit() throws IOException {
        if (playerX.getText().isEmpty()) {
            playerX.setText("X");
        }
        if (two.isSelected() && playerO.getText().isEmpty()) {
            playerO.setText("O");
        } else if (single.isSelected()) {
            playerO.setText("Computer");
        }

        playerO.getScene().getWindow().hide();

        Stage newStage = new Stage();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));
        loader.setController(new TicTacToeController(playerX.getText(), playerO.getText(), single.isSelected()));

        Parent root = loader.load();
        newStage.setTitle("Tic Tac Toe");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    /* sets game mode */
    public void multiPlayer() {
        if (single.isSelected()) {
            playerO.setEditable(false);
            playerO.setVisible(false);
            oName.setVisible(false);
        } else if (two.isSelected()) {
            playerO.setEditable(true);
            playerO.setVisible(true);
            oName.setVisible(true);
        }

        if (single.isSelected() || two.isSelected()) {
            submit.setDisable(false);
        }
    }
}
