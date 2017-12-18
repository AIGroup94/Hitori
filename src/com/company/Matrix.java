package com.company;

import java.util.ArrayList;
import java.util.HashSet;

int BLACK = 1;

public class Matrix {
    private int M; //number of rows.
    private int N; // number of columns.
    private int[][] data;// M By N array.
    private int WholeDuplicates;

    // create M by N matrix of 0's.
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new int[M][N];
    }

    public int getM() {
        return M;
    }

    public void setWholeDuplicates(int wholeDuplicates) {
        WholeDuplicates = wholeDuplicates;
    }

    public int getN() {
        return N;
    }

    public int[][] getData() {
        return data;
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

    public int[][] getRow_Column(int x)
    {
        int a[][]= new int [2][data.length] ;
        int i;
        //gets the column numbers at the same row
        for (i= 0 ; i<data.length; i++)
            a[0][i] = this.data[x][i];

        //gets the row numbers at the same column
        for(i=0; i<data.length; i++)
            a[1][i]=this.data[i][x];

        return a;
    }

    public ArrayList<ArrayList<Integer>> findDuplicate (int[][] data) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>()); result.add(new ArrayList<Integer>());

        HashSet<Integer> set = new HashSet<Integer>();

        for (int arrayElement : data[0])
        {
            if(!set.add(arrayElement))
            {
                System.out.println("Duplicate Elements in row are : "+arrayElement);
                result.get(0).add(arrayElement);

            }
        }
        System.out.println();
        set.clear();
        for (int arrayElement : data[1])
        {
            if(!set.add(arrayElement))
            {
                System.out.println("Duplicate Elements in column are : "+arrayElement);
                result.get(1).add(arrayElement);

            }
        }
        return result;
    }

    public int NumberOfDuplicates (int[][] data) {
        int h = 0;
        HashSet<Integer> set = new HashSet<Integer>();

        for (int arrayElement : data[0])
        {
            if(!set.add(arrayElement))
            {
                System.out.println("Duplicate Elements in row are : "+arrayElement);
                h++;

            }
        }
        System.out.println();
        set.clear();
        for (int arrayElement : data[1])
        {
            if(!set.add(arrayElement))
            {
                System.out.println("Duplicate Elements in column are : "+arrayElement);
                h++;
            }
        }
        return h;
    }

    public int setWholeDuplicate() {
         WholeDuplicates = 0;
        for (int i = 0; i < M; i++) {
            WholeDuplicates += NumberOfDuplicates(getRow_Column(i));
        }
        return WholeDuplicates;
    }

    public int cost(){
        int errorCounter=0,counter;
        int i,j,k;

//this func has been defined
        for(i=0;i<M;i++){
            for(j=0;j<M;j++){
                if(color[i][j]==WHITE){
                    for(k=j+1;k<M;k++){
                        if(color[i][k]==WHITE && hitori[i][k]==hitori[i][j]){
                            errorCounter++;
                        }
                    }

                }
            }
        }

        for(j=0;j<M;j++){
            for(i=0;i<M;i++){
                if(color[i][j]==WHITE){
                    for(k=i+1;k<M;k++){
                        if(color[k][j]==WHITE && hitori[k][j]==hitori[i][j]){
                            errorCounter++;
                        }
                    }

                }
            }
        }
//========================================================================================


        for(i=0;i<M;i++){
            for(j=0;j<M;j++)    {
                if(j+1<M && color[i][j]==BLACK && color[i][j+1]==BLACK){
                    errorCounter++;
                }
                if(i+1<M && color[i][j]==BLACK && color[i+1][j]==BLACK){
                    errorCounter++;
                }
            }
        }

//    checks a white index not surrounded by black indexes
        for(i=0;i<M;i++){
            for(j=0;j<M;j++){
                counter=0;
                // only one of the indexes is black
                if(color[i][j]==WHITE){

                    if(j+1<M && color[i][j+1]==BLACK){
                        counter++;
                    }
                    if(i+1<M && color[i+1][j]==BLACK){
                        counter++;
                    }
                    if(j-1>=0 && color[i][j-1]==BLACK){
                        counter++;
                    }
                    if(i-1>=0 && color[i-1][j]==BLACK){
                        counter++;
                    }


                    if(i==0 && j==0 && counter==2)
                        errorCounter++;
                    else if(i==0 && j==M-1 && counter==2)
                        errorCounter++;
                    else if(i==M-1 && j==M-1 && counter==2)
                        errorCounter++;
                    else if(i==M-1 && j==0 && counter==2)
                        errorCounter++;
                    else if(i==0 && counter==3)
                        errorCounter++;
                    else if(i==M-1 && counter==3)
                        errorCounter++;
                    else if(j==0 && counter==3)
                        errorCounter++;
                    else if(j==M-1 && counter==3)
                        errorCounter++;
                    else if(counter==4)
                        errorCounter++;
                }

            }
        }


        return errorCounter;
    }



}

