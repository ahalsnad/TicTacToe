package game;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {

    @FXML
    Label id00;
    @FXML
    Label id01;
    @FXML
    Label id02;
    @FXML
    Label id10;
    @FXML
    Label id11;
    @FXML
    Label id12;
    @FXML
    Label id20;
    @FXML
    Label id21;
    @FXML
    Label id22;
    @FXML
    Label label;

    /* Names of the players */
    private String playerX;
    private String playerO;

    /* Game mode : Single player or Two player */
    private boolean single;
    /* Turn */
    private String whoseTurn;
    private String winner;

    /* Parameterized constructor */
    TicTacToeController(String playerX, String playerO, boolean single) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.whoseTurn = playerX;
        this.single = single;
        this.winner = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(whoseTurn + "'s turn to play");
    }

    /*
     * Prints the character in the respective grids and checks if the game ends.
     * Game mode = Single : The computer makes its move.
     */
    public void printTurn(MouseEvent event) {

        if(winner != null) {
            showWindow(winner);
        } else {
            Label textId = (Label) event.getSource();
            if(single && textId.getText().isEmpty())
                disableLabel();

            if (textId.getText().isEmpty()) {
                if (playerX.equals(whoseTurn)) {
                    textId.setText("X");
                    whoseTurn = playerO;
                } else {
                    textId.setText("O");
                    whoseTurn = playerX;
                }
            }
            computeWinLose();
            label.setText(whoseTurn + "'s turn to play");

            if (single && whoseTurn.equals(playerO)) {
                printO();
                computeWinLose();
            }
        }
    }

    /*
     * Computes the computers move.
     * First, checks if it can win the game.
     * If not, checks if it can sabotage the opponents game.
     * Else, selects one of the available grid entry.
     */
    private void printO() {
        String[] players = {"O", "X"};
        boolean flag = false;

        for (int player = 0; player < 2; player++) {
            if (!flag && (id00.getText().isEmpty() ^ id01.getText().isEmpty() ^ id02.getText().isEmpty())
                    && !(id00.getText().isEmpty() && id01.getText().isEmpty() && id02.getText().isEmpty())) {
                if (id00.getText().isEmpty() && id01.getText().equals(id02.getText()) && players[player].equals(id01.getText())) {
                    pauseAndPlay(id00);
                    flag = true;
                } else if (id01.getText().isEmpty() && id00.getText().equals(id02.getText()) && players[player].equals(id00.getText())) {
                    pauseAndPlay(id01);
                    flag = true;
                } else if (id02.getText().isEmpty() && id00.getText().equals(id01.getText()) && players[player].equals(id00.getText())) {
                    pauseAndPlay(id02);
                    flag = true;
                }

            }

            if (!flag && (id00.getText().isEmpty() ^ id10.getText().isEmpty() ^ id20.getText().isEmpty())
                    && !(id00.getText().isEmpty() && id10.getText().isEmpty() && id20.getText().isEmpty())) {
                if (id00.getText().isEmpty() && id10.getText().equals(id20.getText()) && players[player].equals(id10.getText())) {
                    pauseAndPlay(id00);
                    flag = true;
                } else if (id10.getText().isEmpty() && id00.getText().equals(id20.getText()) && players[player].equals(id00.getText())) {
                    pauseAndPlay(id10);
                    flag = true;
                } else if (id20.getText().isEmpty() && id00.getText().equals(id10.getText()) && players[player].equals(id00.getText())) {
                    pauseAndPlay(id20);
                    flag = true;
                }

            }

            if (!flag && (id00.getText().isEmpty() ^ id11.getText().isEmpty() ^ id22.getText().isEmpty())
                    && !(id00.getText().isEmpty() && id11.getText().isEmpty() && id22.getText().isEmpty())) {
                if (id00.getText().isEmpty() && id11.getText().equals(id22.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id00);
                    flag = true;
                } else if (id11.getText().isEmpty() && id00.getText().equals(id22.getText()) && players[player].equals(id00.getText())) {
                    pauseAndPlay(id11);
                    flag = true;
                } else if (id22.getText().isEmpty() && id00.getText().equals(id11.getText()) && players[player].equals(id00.getText())) {
                    pauseAndPlay(id22);
                    flag = true;
                }
            }

            if (!flag && (id11.getText().isEmpty() ^ id10.getText().isEmpty() ^ id12.getText().isEmpty())
                    && !(id11.getText().isEmpty() && id10.getText().isEmpty() && id12.getText().isEmpty())) {
                if (id10.getText().isEmpty() && id11.getText().equals(id12.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id10);
                    flag = true;
                } else if (id11.getText().isEmpty() && id10.getText().equals(id12.getText()) && players[player].equals(id12.getText())) {
                    pauseAndPlay(id11);
                    flag = true;
                } else if (id12.getText().isEmpty() && id10.getText().equals(id11.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id12);
                    flag = true;
                }
            }

            if (!flag && (id11.getText().isEmpty() ^ id01.getText().isEmpty() ^ id21.getText().isEmpty())
                    && !(id11.getText().isEmpty() && id01.getText().isEmpty() && id21.getText().isEmpty())) {
                if (id01.getText().isEmpty() && id11.getText().equals(id21.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id01);
                    flag = true;
                } else if (id11.getText().isEmpty() && id01.getText().equals(id21.getText()) && players[player].equals(id21.getText())) {
                    pauseAndPlay(id11);
                    flag = true;
                } else if (id21.getText().isEmpty() && id01.getText().equals(id11.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id21);
                    flag = true;
                }
            }

            if (!flag && (id11.getText().isEmpty() ^ id02.getText().isEmpty() ^ id20.getText().isEmpty())
                    && !(id11.getText().isEmpty() && id02.getText().isEmpty() && id20.getText().isEmpty())) {
                if (id02.getText().isEmpty() && id11.getText().equals(id20.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id02);
                    flag = true;
                } else if (id11.getText().isEmpty() && id02.getText().equals(id20.getText()) && players[player].equals(id02.getText())) {
                    pauseAndPlay(id11);
                    flag = true;
                } else if (id20.getText().isEmpty() && id02.getText().equals(id11.getText()) && players[player].equals(id11.getText())) {
                    pauseAndPlay(id20);
                    flag = true;
                }
            }

            if (!flag && (id22.getText().isEmpty() ^ id02.getText().isEmpty() ^ id12.getText().isEmpty())
                    && !(id22.getText().isEmpty() && id02.getText().isEmpty() && id12.getText().isEmpty())) {
                if (id02.getText().isEmpty() && id22.getText().equals(id12.getText()) && players[player].equals(id22.getText())) {
                    pauseAndPlay(id02);
                    flag = true;
                } else if (id22.getText().isEmpty() && id02.getText().equals(id12.getText()) && players[player].equals(id02.getText())) {
                    pauseAndPlay(id22);
                    flag = true;
                } else if (id12.getText().isEmpty() && id02.getText().equals(id22.getText()) && players[player].equals(id22.getText())) {
                    pauseAndPlay(id12);
                    flag = true;
                }
            }

            if (!flag && (id22.getText().isEmpty() ^ id20.getText().isEmpty() ^ id21.getText().isEmpty())
                    && !(id22.getText().isEmpty() && id20.getText().isEmpty() && id21.getText().isEmpty())) {
                if (id20.getText().isEmpty() && id22.getText().equals(id21.getText()) && players[player].equals(id22.getText())) {
                    pauseAndPlay(id20);
                    flag = true;
                } else if (id22.getText().isEmpty() && id20.getText().equals(id21.getText()) && players[player].equals(id20.getText())) {
                    pauseAndPlay(id22);
                    flag = true;
                } else if (id21.getText().isEmpty() && id20.getText().equals(id22.getText()) && players[player].equals(id22.getText())) {
                    pauseAndPlay(id21);
                    flag = true;
                }
            }
        }

        if (!flag) {
            if (id22.getText().isEmpty()) {
                pauseAndPlay(id22);
            } else if (id12.getText().isEmpty()) {
                pauseAndPlay(id12);
            } else if (id11.getText().isEmpty()) {
                pauseAndPlay(id11);
            } else if (id10.getText().isEmpty()) {
                pauseAndPlay(id10);
            } else if (id01.getText().isEmpty()) {
                pauseAndPlay(id01);
            } else if (id21.getText().isEmpty()) {
                pauseAndPlay(id21);
            } else if (id00.getText().isEmpty()) {
                pauseAndPlay(id00);
            } else if (id02.getText().isEmpty()) {
                pauseAndPlay(id02);
            } else if (id20.getText().isEmpty()) {
                pauseAndPlay(id20);
            }
        }
    }

    /*
     * Disable clicks while processing previous clicks
     */
    private void disableLabel() {
        Label[] labelArray = {id00, id01, id02, id10, id11, id12, id20, id21, id22};
        for(Label lbl : labelArray) {
            lbl.setOnMouseClicked(null);
        }
    }

    /*
     * Enable clicks on the grid
     */
    private void enableLabels() {
        Label[] labelArray = {id00, id01, id02, id10, id11, id12, id20, id21, id22};
        for(Label lbl : labelArray) {
            lbl.setOnMouseClicked((event) -> printTurn(event));
        }
    }

    /*
     * Introduce pause transition before the computer plays its turn
     */
    private void pauseAndPlay(Label textId) {
        textId.setVisible(false);
        textId.setText("O");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> enableAll(textId));
        pause.play();

    }

    /*
     * On finishing the pause transition, enable the labels and make the turn visible
     */
    private void enableAll(Label id){
        id.setVisible(true);
        enableLabels();
        whoseTurn = playerX;
        label.setText(whoseTurn + "'s turn to play");
    }

    /*
     * Computes if the game is won or a draw, by checking all the 8 possible lines.
     */
    private void computeWinLose() {

        if (!id11.getText().isEmpty()) {
            if ((id11.getText().equals(id00.getText()) && id11.getText().equals(id22.getText())) ||
                    (id11.getText().equals(id02.getText()) && id11.getText().equals(id20.getText())) ||
                    (id11.getText().equals(id10.getText()) && id11.getText().equals(id12.getText())) ||
                    (id11.getText().equals(id01.getText()) && id11.getText().equals(id21.getText()))) {
                winner = id11.getText();
            }
        }

        if (!id00.getText().isEmpty()) {
            if ((id00.getText().equals(id01.getText()) && id00.getText().equals(id02.getText())) ||
                    id00.getText().equals(id10.getText()) && id00.getText().equals(id20.getText())) {
                winner = id00.getText();
            }
        }

        if (!id22.getText().isEmpty()) {
            if ((id22.getText().equals(id21.getText()) && id22.getText().equals(id20.getText())) ||
                    id22.getText().equals(id12.getText()) && id22.getText().equals(id02.getText())) {
                winner = id22.getText();
            }
        }

        // If all the grid entries are filled, it is a draw
        if (!id00.getText().isEmpty() && !id01.getText().isEmpty() && !id02.getText().isEmpty() && !id10.getText().isEmpty() && !id11.getText().isEmpty() && !id12.getText().isEmpty() && !id20.getText().isEmpty() && !id21.getText().isEmpty() && !id22.getText().isEmpty()) {
            winner = "Draw";
        }

        if (winner != null) {
            if ("X".equals(winner)) {
                winner = playerX;
            } else if ("O".equals(winner)) {
                winner = playerO;
            } else {
                winner = "Draw";
            }
            showWindow(winner);
        }
    }

    /* Display the winner in another screen */
    private void showWindow(String winner) {
        String message;
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("winlose.fxml"));
        if ("Draw".equals(winner)) {
            message = "It's a draw! Play again.";
        } else {
            if (single) {
                if (winner.equals(playerO)) {
                    message = "LOSER!!";
                } else {
                    message = "YOU WON!!";
                }
            } else {
                message = winner + " won!";
            }
        }

        loader.setController(new WinLoseController(message, playerX, playerO, single));
        resetValues();

        try {
            final Parent root = loader.load();
            final Scene scene = new Scene(root, 250, 150);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Game Over!");
            stage.initOwner(label.getScene().getWindow());
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Close present window
        label.getScene().getWindow().hide();

    }

    /* reset values */
    private void resetValues() {
        winner = "";
        whoseTurn = "";
    }

    /* Start a new game */
    public void newGame() throws IOException {
        label.getScene().getWindow().hide();

        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("playerName.fxml"));
        newStage.setTitle("Enter Players");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    /* Refresh the game */
    public void replay() throws IOException {
        label.getScene().getWindow().hide();

        resetValues();

        Stage newStage = new Stage();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));
        loader.setController(new TicTacToeController(playerX, playerO, single));

        Parent root = loader.load();
        newStage.setTitle("Tic Tac Toe");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    /* Close game */
    public void close() {
        label.getScene().getWindow().hide();
    }
}
