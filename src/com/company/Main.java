package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class Main {
    public static Matrix read(String input) {
        // the name of the file to open.
        String fileName = input;
        String backupfileName = input;

        // this will reference one line at a time
        String line = null;
        String[] parts;
        Matrix problem;
        short counter = 0;
        boolean flag = false;
        int timeout = 0;

        try {
            //FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            FileReader backupfileReader = new FileReader(backupfileName);


            // Always warp FileReader in bufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader backup = new BufferedReader(backupfileReader); //backup the Address

            while ((line = bufferedReader.readLine()) != null) {
                counter++;
            }
            problem = new Matrix(counter,counter);

            //restore the backup to set the line reader to first
            //Now The Matrix values will be assigned
            counter = 0;
            while ((line = backup.readLine()) != null) {
                parts = line.split(" ");
                for(int n = 0; n < parts.length; n++) {
                    flag = problem.setValue(counter, n, Integer.parseInt(parts[n]));
                    problem.setValue(counter, n, Integer.parseInt(parts[n]));

                    //Verify that the value has been assigned. We used timeout
                    if(!flag) {
                        if(timeout == 3) {
                            System.out.println("Failed to set the value! Check the Source Code!");
                            System.exit(0);
                        }
                        System.out.println("Failed to set [" + counter + "][" + n + "]'s value. Trying Again...");
                        timeout++;
                        n--;
                    } else {
                        flag = false;
                        timeout = 0;
                    }
                }
                counter++;
            }

            //always close files.
            bufferedReader.close();
            backup.close();

            return problem;

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");

        } catch (IOException ex) {
            System.out.println("Error reading file ' " + fileName + "'");
            // or we could just do this:
            // ex.printStackTrance();
        }
        return null;
    }

    public static void main(String[] args) {
        Matrix problem = read("inputs/sample1.txt");
        problem.show();
        //System.out.println(problem.setWholeDuplicate());
/*        int[][] dup = new int[2][problem.getM()];
          dup = problem.getRow_Column(0);
*/
        SimulatedAnnealing SA = new SimulatedAnnealing(problem);
        HillClimbing HC = new HillClimbing(problem);

        System.out.println("Hill Climbing: ");

        HC.do_HillClimbing();
        HC.showStatus();

        System.out.println("Simulated Annealing: ");

        SA.do_SimlulatedAnnealing();
        SA.showStatus();
    }


}
