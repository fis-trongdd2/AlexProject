package main;

import data.XML;

import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Candidate {
    private ArrayList <Double> value;
    private ArrayList <Double> listlabel;
    private int label;


    public void setValue(ArrayList<Double> value) {
        this.value = value;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;

    }
    public void setLabel(XML a) {
        this.label = 0;
        for (int i = 0; i < a.getDstohop().size(); i++) {
            if (this.listlabel.equals( a.getDstohop().get(i))) {
                this.label = i;
                break;
            }
        }
    }
    public int getNumberOfAtribute () {
        return value.size();
    }

    public ArrayList<Double> getListlabel() {
        return listlabel;
    }

    public ArrayList<Double> getValue() {
        return value;
    }


    public void setListlabel(ArrayList<Double> listlabel) {
        this.listlabel = listlabel;
    }
    public  void  addValue (double a) {
        this.value.add(a);
    }
}
