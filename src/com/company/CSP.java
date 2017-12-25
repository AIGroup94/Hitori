package com.company;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

public class CSP {
    private final ArrayList<ArrayList<Variable>> Variables;
    private Matrix data;

    //Constraints
    //A list of Variables which must not be equal to them
    private final ArrayList<Object>     Candidates;


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
        this.Candidates = new ArrayList<>();
        for (int i = 0; i < data.getR(); i++) {
            Duplicates = this.data.getSameOfTheRowAndColumn(i);
            for (int j = 0; j < data.getR(); j++) {
                //WHITE the indexes which not duplicate (These are always constant) in a row
                if (!Duplicates.get(0).contains(this.data.getValue(i,j))) {
                    this.Variables.get(i).get(j).setValue(0);
                }
                //WHITE the indexes which not duplicate (These are always constant) in a column
                else if (!Duplicates.get(1).contains(this.data.getValue(j,i))) {
                    this.Variables.get(j).get(i).setValue(0);
                }
            }
        }

        Object temp;
        for (int i = 0; i < data.getR(); i++) {
            for (int j = 0; j < data.getN(); j++) {
                if (this.Variables.get(i).get(j).getValue() == -1) {
                    temp = new Object(this.Variables.get(i).get(j), i, j);
                    this.Candidates.add(temp);
                }
            }
        }
        if (!this.BackTracking(0)) {
            System.out.println("Problem Didn't Solve!");
        }


    }

    public void show() {
        for (int i = 0; i < this.Variables.size(); i++) {
            for (int j = 0; j < this.Variables.size(); j++) {
                if (this.Variables.get(i).get(j).getValue() == 0)
                    System.out.print(this.data.getValue(i,j) + " ");
                else {
                    System.out.print("B ");
                }
            }
            System.out.println();
        }
        return;
    }

    public boolean BackTracking(int count) {
        if (isGoal()) {
            return true;
        }
        if (this.Variables.get(this.Candidates.get(count).i()).get(this.Candidates.get(count).j()).setValue(1)) {
            this.show();
            System.out.println("\n\n" + count + "============================");
            if (forwardChecking(this.Candidates.get(count).i(), this.Candidates.get(count).j(), 1)) {
                if (!BackTracking(count + 1)) {
                    this.Variables.get(this.Candidates.get(count).i()).get(this.Candidates.get(count).j()).reset();
                    this.Variables.get(this.Candidates.get(count).i()).get(this.Candidates.get(count).j()).setValue(0);
                    BackTracking(count + 1);
                }
            } else {
                this.Variables.get(this.Candidates.get(count).i()).get(this.Candidates.get(count).j()).setValue(0);
                BackTracking(count+1);
            }
        }
        return false;
    }

    public boolean forwardChecking(int i, int j, int key) {
        int size = this.Variables.size();
        if (j + 1 < size && !this.Variables.get(i).get(j + 1).ModifyDomain(key) && this.Variables.get(i).get(j + 1).getValue() == -1)
            return false;
        if (j - 1 >= 0 && !this.Variables.get(i).get(j - 1).ModifyDomain(key) && this.Variables.get(i).get(j - 1).getValue() == -1)
            return false;
        if (i + 1 < size && !this.Variables.get(i + 1).get(j).ModifyDomain(key) && this.Variables.get(i + 1).get(j).getValue() == -1)
            return false;
        if (i - 1 >= 0 && !this.Variables.get(i - 1).get(j).ModifyDomain(key) && this.Variables.get(i - 1).get(j).getValue() == -1)
            return false;


        if (j + 1 < size && this.Variables.get(i).get(j).getValue() == 1 && this.Variables.get(i).get(j + 1).getValue() == 1) {
            return false;
        }
        if (i + 1 < size && this.Variables.get(i).get(j).getValue() == 1 && this.Variables.get(i + 1).get(j).getValue() == 1) {
            return false;
        }
        if (j - 1 >= 0 && this.Variables.get(i).get(j).getValue() == 1 && this.Variables.get(i).get(j - 1).getValue() == 1) {
            return false;
        }
        if (i - 1 >= 0 && this.Variables.get(i).get(j).getValue() == 1 && this.Variables.get(i - 1).get(j).getValue() == 1) {
            return false;
        }

        int BLACK_COUNT = 0;

        if (j + 1 < size && this.Variables.get(i).get(j + 1).getValue() == 1) {
            BLACK_COUNT++;
        }
        if (i + 1 < size && this.Variables.get(i + 1).get(j).getValue() == 1) {
            BLACK_COUNT++;
        }
        if (j - 1 >= 0 && this.Variables.get(i).get(j - 1).getValue() == 1) {
            BLACK_COUNT++;
        }
        if (i - 1 >= 0 && this.Variables.get(i - 1).get(j).getValue() == 1) {
            BLACK_COUNT++;
        }

        if (BLACK_COUNT >= 2)
            return false;


        return true;
    }


    public boolean isGoal() {
        for (int i = 0; i < this.Variables.size(); i++) {
            for (int j = 0; j < this.Variables.size(); j++) {
                if (this.Variables.get(i).get(j).getValue() == -1) {
                    return false;
                }

            }
        }
        return true;
    }
}


//Our Variable
class Variable {
    public static boolean WHITE = false;
    public static boolean BLACK = true;

    private int Value;
    private int[] Domain;
    private boolean isVisited;

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }


//Functions

    public int getValue() {
        return Value;
    }

    public Variable() {
        this.Value = -1;
        this.isVisited = false;
        this.Domain = new int[2];
        Domain[0] = 0; Domain[1] = 1;

    }

    public boolean is_empty() {
        if (this.Domain[0] == -1 && this.Domain[1] == -1)
            return true;

        return false;
    }

    public boolean ModifyDomain(int key) {
        this.Domain[key] = -1;
        return !this.is_empty();
    }

    public boolean reset() {
        this.Domain[0] = 0; this.Domain[1] = 1;
        return !this.is_empty();
    }

    public boolean setValue(int value) {
        if (value == 1) {
            this.Value = 1;
            return ModifyDomain(1);
        } else if (value == 0) {
            this.Value = 0;
            return ModifyDomain(0);
        }
        return false;
    }
}

class Object {
    private Variable variable;
    private int i;
    private int j;

    public int i() {
        return i;
    }

    public int j() {
        return j;
    }

    public Variable getVariable() {
        return variable;
    }

    public Object(Variable variable, int i, int j) {
        this.variable = variable;
        this.i = i;
        this.j = j;
    }
}