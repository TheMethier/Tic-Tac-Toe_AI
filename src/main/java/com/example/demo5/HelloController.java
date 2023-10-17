package com.example.demo5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController  {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField Form;
    @FXML
    private Label Text;
    private Scene scene;
    private Stage stage;


    int n;
    int depth;
    @FXML
    protected void MakeBoard(ActionEvent event) throws IOException {

        try
        {
            n=Integer.parseInt(Form.getText());

        }
        catch (NumberFormatException e)
        {

        }
        catch (Exception e)
        {

        }
        FXMLLoader fxmlLoader = new FXMLLoader(BoardController.class.getResource("com/example/demo5/Board.fxml"));

        fxmlLoader.setLocation(BoardController.class.getResource("Board.fxml"));
        if(n>0) {
            Board board = new Board(n);
            Parent root = fxmlLoader.load();
            stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            BoardController.drawTicTacToe(n, stage, board);
        }

        else{
            Text.setText("Zły numer!");
        }

    }
}