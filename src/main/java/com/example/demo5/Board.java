package com.example.demo5;

public class Board {
    public  int size;
    public  int board[][];

    public Board(int size) {
        this.size = size;
        this.board=new int [size][size];
        for (int i=0;i<size;i++)
            for(int j=0;j<size; j++)
        {
            this.board[i][j]=0;
        }
    }
    public  void Clear() {
        for (int a = 0; a < this.size; a++) {
            for (int b = 0; b < this.size; b++) {
                this.board[a][b] = 0;
            }
        }
    }
    public int generateDepth()
    {
        if(size<3)
        return 9;
        else if (size==4) {
            return 6;
        } else if (size==5) {
            return 5;
        } else if (size>=6&&size<=7) {
            return 4;
        } else if (size>=8&&size<=10) {
            return 3;
        }
        return 2;
    }
    public Boolean Win(boolean Who)
    {

        int i=0;
        int j=0;
        int w1=0,w2=0,h1=0,h2=0,s1=0,s2=0,s3=0,s4=0;
        while(i< this.size ) {
            if(board[i][i]==1)s1++;
            if(board[i][i]==-1)s2++;
            if(board[i][size-1-i]==-1)s3++;
            if(board[i][size-1-i]==1)s4++;
            while(j< size)
            {
                if(board[i][j]==1) w1++;
                if(board[i][j]==-1) w2++;
                if(board[j][i]==1) h1++;
                if(board[j][i]==-1) h2++;

                j++;
            }
            if(h1== size||w1== size||h2==size||h1==size)
            {
                return true;
            }
            j=0;
            w1=0;
            w2=0;
            h1=0;
            h2=0;
            i++;
        }
        if(s1== size||s2== size||s3== size||s4== size) return true;
        return false;
    }
    public int HowManyEmpty() {
    int r=0;
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                if(board[a][b]==0) r++;
            }
        }
        return r;
    }

    public  Boolean Tie() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
