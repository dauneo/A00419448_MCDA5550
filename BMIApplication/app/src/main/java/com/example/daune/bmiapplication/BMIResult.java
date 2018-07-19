package com.example.daune.bmiapplication;
//import java.util.Date;

public class BMIResult {
    private double height = 1;
    private double weight = 1;
    //private Date dob = new Date().;

    public BMIResult(double height, double weight){ //, Date dob
        this.height = height;
        this.weight = weight;
        //this.dob = dob;

    }

    public double getHeight(){return height;}
    public void setHeight(double height){this.height = height;}
    public double getWeight(){return weight;}
    public void setWeight(double weight){this.weight = weight;}
    //public Date getDob(){return dob;}
    //public void setDob(Date dob){this.dob = dob;}

    public double getResult(){return weight/height*height;}

    public String toString(){
        return String.valueOf(getResult());
    }


}
