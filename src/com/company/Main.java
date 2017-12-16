package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // the name of the file to open.
        String fileName = "inputs/sample1.txt";

        // this will reference one line at a time
        String line = null;

        try {
            //FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always warp FileReader in bufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            //always close files.
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");

        } catch (IOException ex) {
            System.out.println("Error reading file ' " + fileName + "'");
            // or we could just do this:
            // ex.printStackTrance();
        }


    }


}
