package com.company;

import java.util.Random;

public class SimulatedAnnealing {
    private static boolean WHITE = false;
    private static boolean BLACK = true;

    private Matrix data;
    private boolean[][] status;
    int cost;


    public SimulatedAnnealing(Matrix problem) {
        this.data = problem;
        //this.cost = this.data.setWholeDuplicate();
        this.init_status();

    }

    private void init_status() {
        this.status = new boolean[data.getR()][data.getN()];
        for( int i = 0 ; i < data.getR();i++){
            for(int j =0; j < data.getN();j++){
                this.status[i][j]= WHITE;
            }
        }

    }

    public void setOne(int x,int y) { this.status[x][y] = true; }

    public void showStatus(){
        for (int i = 0; i < this.status.length; i++) {
            for (int j = 0; j < this.status.length; j++) {
                if (this.status[i][j] == WHITE) {
                    System.out.print(this.data.getValue(i,j) + " ");
                } else
                    System.out.print("B ");


            }
            System.out.println();
        }
    }

    /*
     *
     * The Public Rule for the game (BLACK INDEXES)
     * And for the duplicate Numbers we have defined another function in Matrix.java called "setWholeDuplicate()"
     */

    public void Rule() {

        int cost = 0;
        int BLACK_COUNT;
        int i,j,k;
        int M = this.data.getR();

        for(i=0;i<M;i++){
            for(j=0;j<M;j++){
                if(this.status[i][j]==WHITE){
                    for(k=j+1;k<M;k++){
                        if(this.status[i][k]==WHITE && this.data.getValue(i,k)==this.data.getValue(i,j)){
                            cost++;
                        }
                    }

                }
            }
        }

        for(j=0;j<M;j++){
            for(i=0;i<M;i++){
                if(this.status[i][j]==WHITE){
                    for(k=i+1;k<M;k++){
                        if(this.status[k][j]==WHITE && this.data.getValue(k,j)==this.data.getValue(i,j)){
                            cost++;
                        }
                    }

                }
            }
        }

        for(i = 0;i < M;i++){
            for(j = 0;j < M;j++)    {
                if(j + 1 < M && this.status[i][j]==BLACK && this.status[i][j+1]==BLACK){
                    cost++;
                }
                if(i+1 < M && this.status[i][j]==BLACK && this.status[i+1][j]==BLACK){
                    cost++;
                }
            }
        }

//    checks a white index not surrounded by black indexes
        for(i = 0;i < M;i++){
            for(j = 0;j < M;j++){
                BLACK_COUNT = 0;
                // counts only one of the indexes is black
                if(this.status[i][j]==WHITE){

                    if(j+1 < M && this.status[i][j+1]==BLACK){
                        BLACK_COUNT++;
                    }
                    if(i+1 < M && this.status[i+1][j]==BLACK){
                        BLACK_COUNT++;
                    }
                    if(j-1 >= 0 && this.status[i][j-1]==BLACK){
                        BLACK_COUNT++;
                    }
                    if(i-1 >= 0 && this.status[i-1][j]==BLACK){
                        BLACK_COUNT++;
                    }


                    if(i==0 && j==0 && BLACK_COUNT==2)
                        cost++;
                    else if(i == 0 && j == M-1 && BLACK_COUNT==2)
                        cost++;
                    else if(i == M-1 && j == M-1 && BLACK_COUNT==2)
                        cost++;
                    /*else if(i==M-1 && j==M-1 && counter==2)
                        cost++;*/
                    else if(i == 0 && BLACK_COUNT==3)
                        cost++;
                    else if(i == M-1 && BLACK_COUNT==3)
                        cost++;
                    else if(j == 0 && BLACK_COUNT==3)
                        cost++;
                    else if(j == M-1 && BLACK_COUNT==3)
                        cost++;
                    else if(BLACK_COUNT==4)
                        cost++;
                }

            }
        }

        this.cost = cost;
        return;
    }

    /*
     * Hill Climbing Algorithm At Last!
     *
     */

    public void do_SimlulatedAnnealing() {
        float T = 10;
        int size = this.data.getR();
        int N = size * size;
        int randI, randJ;
        int cost1, cost2;
        int failure = 0;
        float p;
        Random rand = new Random();

        while (true) {
            for (int i = 0; i < 2 * N; i++) {
                randI = rand.nextInt(1000) % size;
                randJ = rand.nextInt(1000) % size;


                this.Rule();
                cost1 = this.cost;

                if (cost1 == 0) {
                    System.out.println("Success!");
                    return;
                }


                if (this.status[randI][randJ] == WHITE) {
                    this.status[randI][randJ] = BLACK;
                } else {
                    this.status[randI][randJ] = WHITE;
                }
                //this.showStatus();

                this.Rule();
                cost2 = this.cost;

                if (cost2 <= cost1) {
                    failure = 0;
                } else{

                    p = rand.nextInt(1000)/1000f;
                    if( p < Math.exp(-(cost2-cost1)/T)){

                        failure=0;
                    }
                    else{
                        failure++;

                        if(this.status[randI][randJ]==WHITE){
                            this.status[randI][randJ]=BLACK;
                        }
                        else{
                            this.status[randI][randJ]=WHITE;
                        }
                        if(failure==6*N)
                            return;
                    }
                }

            }
            T *= 0.999f;
        }


    }

}
