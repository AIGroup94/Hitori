package com.company;

public class Matrix {
    private int M; //number of rows.
    private int N; // number of columns.
    private int[][] data;// M By N array.

    // create M by N matrix of 0's.
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new int[M][N];
    }


    //create matrix based on 2d array
    public Matrix(int[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public boolean setValue(int i, int j, int value) {
        this.data[i][j] = value;
        return true;
    }

    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d", data[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void show_row_col(int x,int y)
    {
        int a [][]= new int [2][data.length] ;
        for ( int i= 0 ; i<data.length;i++) {
            a[0][i] = this.data[x][i];
        }
        for(int j=0; j<data.length;j++)
            a [1][j]=this.data[j][y];
    }


}
/*String[] strArray = {"abc", "def", "mno", "xyz", "pqr", "xyz", "def"};

        HashSet<String> set = new HashSet<String>();

        for (String arrayElement : strArray)
        {
            if(!set.add(arrayElement))
            {
                System.out.println("Duplicate Element is : "+arrayElement);
            }
        }*/