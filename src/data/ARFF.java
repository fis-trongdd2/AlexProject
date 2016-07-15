package data;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by trong_000 on 7/1/2016.
 * doc file arff .
 * input: link file arff.
 * output : list ten cac thuoc tinh, danh sach gia tri cua tung thuoc tinh.
 */
public class ARFF {
    private ArrayList<ArrayList<Double>> dsgiatri ;
    private ArrayList<String> dsthuoctinh;
    private int sothehienbandau;

    public ARFF(String link) {
        dsgiatri = new ArrayList<ArrayList<Double>>();
        dsthuoctinh = new ArrayList<String>();
        //lay duong dan
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(link));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArffLoader.ArffReader arff = null;
        try {
            arff = new ArffLoader.ArffReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //instance data : bien data lay gia tri.
        Instances data = arff.getData();
        //dat gia tri cho Map dsgiatri
        ArrayList<Double> con = new ArrayList<Double>();
        for (int i = 0; i < data.numAttributes(); i++) {
            double[] values = data.attributeToDoubleArray(i);
             con = new ArrayList<Double>();
            for (int j = 0; j < values.length; j++) {
                con.add(values[j]);
            }
            dsgiatri.add(con);
        }
        sothehienbandau = con.size();
        //dat gia tri cho List thuoc tinh
        for (int i = 0; i < data.numAttributes(); i++) {
            dsthuoctinh.add(data.attribute(i).name());
        }

    }

    public ArrayList<ArrayList<Double>> getDsgiatri() {
        return dsgiatri;
    }

    public void setDsgiatri(ArrayList<ArrayList<Double>> dsgiatri) {
        this.dsgiatri = dsgiatri;
    }

    public ArrayList<String> getDsthuoctinh() {
        return dsthuoctinh;
    }

    public void setDsthuoctinh(ArrayList<String> dsthuoctinh) {
        this.dsthuoctinh = dsthuoctinh;
    }

    public int getSothehienbandau() {
        return sothehienbandau;
    }

    public void setSothehienbandau(int sothehienbandau) {
        this.sothehienbandau = sothehienbandau;
    }

    public static void main(String []args) {
        ARFF test = new ARFF("input/label5.arff");
        System.out.println(test.getDsgiatri());
        System.out.println(test.getSothehienbandau());
    }
}