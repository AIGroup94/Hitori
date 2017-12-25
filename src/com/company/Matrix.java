package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Matrix {
    //Two Global Variables to assign into our

    private int     R; //number of rows.
    private int     N; // number of columns.
    private int[][] data;// M By N array.
    private int     WholeDuplicates;

    // create M by N matrix of 0's.
    public Matrix(int M, int N) {
        this.R = M;
        this.N = N;
        data = new int[M][N];
    }

    public int getR() {
        return R;
    }

    public int getN() {
        return N;
    }

    public int[][] getData() {
        return data;
    }

    public int getValue(int i, int j) {
        return data[i][j];
    }


    //create matrix based on 2d array
    public Matrix(int[][] data) {
        R = data.length;
        N = data[0].length;
        this.data = new int[R][N];
        for (int i = 0; i < R; i++) {
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
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d", data[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        return;
    }

    public int[][] getRow_Column(int x) {
        int a[][] = new int[2][data.length];
        int i;
        //gets the column numbers at the same row
        for (i = 0; i < data.length; i++)
            a[0][i] = this.data[x][i];

        //gets the row numbers at the same column
        for (i = 0; i < data.length; i++)
            a[1][i] = this.data[i][x];

        return a;
    }

    public ArrayList<ArrayList<Integer>> findDuplicate(int[][] data) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        result.add(new ArrayList<Integer>());

        HashSet<Integer> set = new HashSet<Integer>();

        for (int arrayElement : data[0]) {
            if (!set.add(arrayElement)) {
                //System.out.println("Duplicate Elements in row are : " + arrayElement);
                result.get(0).add(arrayElement);

            }
        }
        System.out.println();
        set.clear();
        for (int arrayElement : data[1]) {
            if (!set.add(arrayElement)) {
                //System.out.println("Duplicate Elements in column are : " + arrayElement);
                result.get(1).add(arrayElement);

            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> getSameOfTheRowAndColumn(int x) {
        return findDuplicate(getRow_Column(x));
    }

}

