package com.company;

import java.util.ArrayList;

public class CSP {
    private ArrayList<ArrayList<Variable>> Variables;
    private Matrix data;

    //Constraints
    //A list of Variables which must not be equal to them
    private ArrayList<Variable>     NotEquals;


    public CSP(Matrix data) {

        //Initializing the Variables for the first step of CSP
        this.Variables = new ArrayList<>();
        this.data = data;
        for (int i = 0; i < this.data.getR(); i++) {
            this.Variables.add(new ArrayList<Variable>());
        }
        for (int i = 0; i < this.Variables.size(); i++) {
            for (int j = 0; j < data.getN(); j++) {
                Variable temp = new Variable();
                this.Variables.get(i).add(temp);
            }
        }

        //Assigning Constant Values for decreasing the Search Space. A lot
        ArrayList<ArrayList<Integer>> Duplicates;
        for (int i = 0; i < data.getR(); i++) {
            Duplicates = this.data.getSameOfTheRowAndColumn(i);
            for (int j = 0; j < data.getR(); j++) {
                //WHITE the indexes which not duplicate (These are always constant) in a row
                if (!Duplicates.get(0).contains(this.data.getValue(i,j))) {
                    this.Variables.get(i).get(j).setValue("WHITE");
                }
                //WHITE the indexes which not duplicate (These are always constant) in a column
                else if (!Duplicates.get(1).contains(this.data.getValue(j,i))) {
                    this.Variables.get(j).get(i).setValue("WHITE");
                }
            }
        }
        this.show();

    }

    public void show() {
        for (int i = 0; i < this.data.getR(); i++) {
            for (int j = 0; j < this.data.getN(); j++) {
                System.out.print(this.Variables.get(i).get(j).getValue());
                System.out.print(" ");
            }
            System.out.println();
        }
        return;
    }
}

class Variable {
    public static boolean WHITE = false;
    public static boolean BLACK = true;

    private String Value;
    private String[] Domain;
    private boolean isVisited;



    //Functions

    public String getValue() {
        return Value;
    }

    public Variable() {
        this.Value = null;
        this.Domain = new String[2];
        Domain[0] = "WHITE"; Domain[1] = "BLACK";

    }

    public boolean is_empty() {
        if (this.Domain[0] == null && this.Domain[1] == null)
            return false;

        return true;
    }

    public boolean ModifyDomain(int key) {
        this.Domain[key] = null;
        return !this.is_empty();
    }

    public boolean reset() {
        this.Domain[0] = "WHITE"; this.Domain[1] = "BLACK";
        return !this.is_empty();
    }

    public boolean setValue(String value) {
        if (value.equals("WHITE")) {
            this.Value = value;
            return ModifyDomain(0);
        } else if (value.equals("BLACK")) {
            this.Value = value;
            return ModifyDomain(1);
        }
        return false;
    }
}
