package main;

import data.XML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Candidate {
    private List<Double> value_;
    private List <Double> listLabel_;
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
            if (this.listLabel_.equals( a.getListCombination().get(i))) {
                this.label_ = i;
                break;
            }
        }
    }
    public int getNumberOfAtribute () {
        return value_.size();
    }


    public List<Double> getValue() {
        return value_;
    }


    public void setListLabel(ArrayList<Double> listLabel) {
        this.listLabel_ = listLabel;
    }
    public  void  addValue (double a) {
        this.value_.add(a);
    }
}
