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

        public boolean Rule(int [][]status) {
            for (int i = 0; i < status.length; i++) {
                for (int j = 0; j < status.length; j++) {
                    if (status[i][j] == 1) {
                        if ((status[i][j] == status[i][j + 1]) && ((j + 1) < status.length))
                            return false;
                        if ((status[i][j] == status[i + 1][j]) && ((i + 1) < status.length))
                            return false;
                        if ((status[i][j] == status[i - 1][j]) && ((i - 1) > 0))
                            return false;
                        if ((status[i][j] == status[i][j - 1]) && ((j - 1) > 0))
                            return false;
                        if ((status[0][1] == 1) && (status[1][0] == 1))
                            return false;
                        if ((status[0][(status.length) - 1] == 1) && (status[1][status.length] == 1))
                            return false;
                        if ((status[(status.length) - 1][0] == 1) && (status[status.length][1] == 1))
                            return false;
                        if ((status[(status.length) - 1][status.length] == 1) && (status[status.length][(status.length) - 1] == 1))
                            return false;
                        if ((status[i][j] == 1) && (status[i + 1][j + 1] == 1) && (status[i + 1][j - 1] == 1) && (status[i + 2][j] == 1)&&
                                ((i+1)<status.length)&&((i+2)<status.length)&&((j+1)<status.length)&& ((j-1)>0))
                            return false;
                    }

                }
            }
            return true;
        }



}
