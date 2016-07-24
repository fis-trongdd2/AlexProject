package main;

import data.XML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Candidate {
    private List<Double> value_;
    private List <Double> listLabels_;
    private int label_;


    public void setValue(ArrayList<Double> value) {
        this.value_ = value;
    }

    public int getLabel() {
        return label_;
    }

    public void setLabel(XML a) {
        this.label_ = 0;
        for (int i = 0; i < a.getListCombination().size(); i++) {
            if (this.listLabels_.equals( a.getListCombination().get(i))) {
                this.label_ = i;
                break;
            }
        }
    }

    public void setLabel (int l) {
        this.label_= l;
    }
    public int getNumberOfAtribute () {
        return value_.size();
    }


    public List<Double> getValue() {
        return value_;
    }


    public void setListLabel(ArrayList<Double> listLabels) {
        this.listLabels_ = listLabels;
    }

    public double computeDistance (Cluster a) {
        double distance = 0;
        for (int i = 0; i < this.value_.size(); i++) {
            distance += (this.value_.get(i) - a.getCentroid().get(i))*(this.value_.get(i) - a.getCentroid().get(i));
        }
        return Math.sqrt(distance);
    }

    public void printCandidate () {
        System.out.print("List data : "+ this.getValue());
        System.out.println(" Label candidate : " + this.getLabel());
    }

    public String valueToString () {
        return  "List data : "+ this.getValue() + " Label candidate : " + this.getLabel();
    }
}
