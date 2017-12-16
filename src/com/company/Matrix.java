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

    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%9.4f", data[i][j]);
            }
            System.out.println();
        }
    }
}