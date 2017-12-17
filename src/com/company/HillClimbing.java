package com.company;

public class HillClimbing {
    private Matrix data;
    private int[][] status;
    int h;


    public HillClimbing(Matrix problem) {
        this.data = problem;
        this.data.setWholeDuplicate();

    }

    public void init_status(int [][]status) {
        this.status = new int[data.getM()][data.getN()];
        for( int i =0 ; i < data.getM();i++){
            for(int j =0; j < data.getN();j++){
                this.status[i][j]= 0;
            }
        }

    }

    public void setOne(int x,int y) { this.status[x][y] = 1; }

    public void showStatus(){
        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status.length; j++) {
                    System.out.printf("%d", status[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

}
