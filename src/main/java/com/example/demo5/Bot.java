package com.example.demo5;

import java.util.SortedMap;

public class Bot {
    public int minimax(Board board, int depth,int alfa,int beta,boolean Maxplayer)
    {

        if(depth>0) {
            if (!board.Win(true) && board.HowManyEmpty() == 0) {

                return 4;
            }
            if (Maxplayer) {
                if (board.Win(true)) {
                    return -10 - board.HowManyEmpty();
                }
                int bestvalue = -1000000;
                for (int i = 0; i < board.size; i++) {
                    for (int j = 0; j < board.size; j++) {
                        if (board.board[i][j] == 0) {
                            board.board[i][j] = -1;
                            int value = minimax(board, depth - 1, alfa, beta, false);
                            board.board[i][j] = 0;
                            bestvalue = Math.max(value, bestvalue);
                            if (bestvalue > beta) break;
                            alfa = Math.max(alfa, bestvalue);
                        }
                    }
                }
                return bestvalue;
            } else {
                if (board.Win(true)) {
                    return +10 + board.HowManyEmpty();
                }
                int bestvalue = 1000000;
                for (int i = 0; i < board.size; i++) {
                    for (int j = 0; j < board.size; j++) {
                        if (board.board[i][j] == 0) {
                            board.board[i][j] = 1;
                            int value = minimax(board, depth - 1, alfa, beta, true);
                            board.board[i][j] = 0;
                            bestvalue = Math.min(value, bestvalue);
                            if (bestvalue < alfa) break;
                            beta = Math.min(beta, bestvalue);
                        }
                    }
                }
                return bestvalue;
            }
        }
        else {
            return 0;
        }
    }

    public Move ShowTheBestMove(Board board) {
        int bestval = -10000000;
        int a = 0, b = 0;
        System.out.println(board.generateDepth());
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                if (board.board[i][j] == 0) {
                    board.board[i][j] = -1;
                    int val = minimax(board, board.generateDepth(), -1000000,1000000,false);
                    if (val > bestval) {
                        a = i;
                        b = j;
                        bestval = val;
                        board.board[i][j] = 0;
                    }
                    else {
                        board.board[i][j] = 0;
                    }
                }
            }
        }
        board.board[a][b]=-1;

        System.out.println("____Best_Move____");
        for (int k = 0; k < board.size; k++) {
            for (int o = 0; o < board.size; o++) {
                System.out.print(board.board[k][o] + " ");
            }
            System.out.println();
        }
        Move move=new Move(a,b);
        return move;
    }
};

