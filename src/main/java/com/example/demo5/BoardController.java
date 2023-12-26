package com.example.demo5;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class BoardController {
    @FXML
    private static GridPane pane;
    private static Text text;
    private static boolean played;
    private static Stage stage;
    private static ArrayList<Rectangle> boxes;
    private static Bot Bob;
    private static Scene scene;

    public static void drawTicTacToe(int n, Stage stage, Board board) {
        pane = new GridPane();
        boxes = new ArrayList<>();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(25, 25, 25, 25));
        FXMLLoader fxmlLoader = new FXMLLoader(BoardController.class.getResource("../../../../../../target/classes/com/example/demo5/Board.fxml"));
        fxmlLoader.setLocation(BoardController.class.getResource("Board.fxml"));
        Group Root = new Group();
        text = new Text();
        text.setText("2137");
        text.setX(320);
        text.setY(320);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setScaleX(30);
        text.setScaleY(30);
        text.setFill(Color.DARKBLUE);
        text.setVisible(false);
        EventHandler<MouseEvent> Restart = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                text.setVisible(false);
                for (Rectangle p :
                        boxes) {
                    p.setFill(Color.WHITE);
                }
                board.Clear();
            }
        };
        text.addEventFilter(MouseEvent.MOUSE_CLICKED, Restart);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Rectangle circle = new Rectangle();
                circle.setWidth(600 / n);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                circle.setHeight(600 / n);
                circle.setId(i + "," + j);
                boxes.add(circle);
                pane.add(circle, i, j);
            }
        }
        Root.getChildren().add(pane);
        Root.getChildren().add(text);
        scene = new Scene(Root, 640, 640);
        stage.setScene(scene);
        stage.show();
        try {
            start(board);
        } catch (FileNotFoundException e) {

        }
    }

    public static void start(Board board) throws FileNotFoundException {
        Bob = new Bot();
        Image O = new Image("data:image/png;base64,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", 500, 500, true, true);
        Image X = new Image("https://www.kindpng.com/picc/m/263-2638237_tic-tac-toe-x-imagen-png-transparent-png.png", 500, 500, true, true);
        for (Rectangle box :
                boxes) {
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    String[] Id = box.getId().split(",");
                    System.out.println(box.getId());
                    int i = Integer.parseInt(Id[1]);
                    int j = Integer.parseInt(Id[0]);
                    if (board.board[i][j] == 0 && board.board[i][j] != -1) {
                        box.setFill(Color.BLACK);
                        board.board[i][j] = 1;
                        if (board.Win(false)) {

                            text.setVisible(true);
                            text.setText("WIN");
                            return;
                        }
                        if (board.Tie()) {
                            for (Rectangle p :
                                    boxes) {
                                p.setFill(Color.WHITE);
                            }
                            board.Clear();
                            text.setVisible(true);
                            text.setText("TIE");
                            return;
                        }
                        Move m = Bob.ShowTheBestMove(board);
                        String o = String.format("%d,%d", m.b, m.a);
                        boxes.forEach(b -> {
                            if (b.getId().equals(o)) {
                                b.setFill(Color.RED);
                            }
                        });
                        if (board.Win(true)) {

                            text.setVisible(true);
                            text.setText("WIN");
                            return;
                        }
                        if (board.Tie()) {
                            for (Rectangle p :
                                    boxes) {
                                p.setFill(Color.WHITE);
                            }
                            board.Clear();
                            text.setVisible(true);
                            text.setText("TIE");
                        }
                    }
                }
            };
            box.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        }


    }
}