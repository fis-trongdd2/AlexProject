package main;

import data.ARFF;
import data.XML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Main {
    public static boolean checkFinish(ArrayList<Cluster> listCheck) {
        int a = listCheck.size();
        int count = 0;
        for (Cluster c : listCheck) {
            if (count >= 2) return false;
            if (c.getIdentified() == false) {
                count++;
            }
        }
        return true;
    }
    public static ArrayList<Cluster> setCluster (String linkArff, String linkXML) {
        ArrayList<Cluster> listCluster = new ArrayList<Cluster>();
        ArrayList<Candidate> listCandidateFirst = new ArrayList<Candidate>();
        ARFF filearff = new ARFF(linkArff);
        XML filexml = new XML(linkXML);
        int numberOfLabel = filexml.getNumberOfLabel();
        int i,j;


        // vong for nay dua cac the hien vao 1 list. cac the hien nay da dc chuyen sang tu dang rut gon.
        for ( i = 0; i < filearff.getNumberOfCadidateFirst(); i++) {
            Candidate _candidate = new Candidate();
            ArrayList<Double> _ds = new ArrayList<Double>();
            for ( j = 0; j < filearff.getListAtribute().size() - numberOfLabel; j++ ) {
                _ds.add(filearff.getListValue().get(j).get(i));
            }
            _candidate.setValue(_ds);
            listCandidateFirst.add(_candidate);
        }

        //vong for nay lay cac nhan dua vao ds nhan cua tung the hien.
        for ( i = 0; i < filearff.getNumberOfCadidateFirst(); i++) {
            ArrayList<Double> _ds2 = new ArrayList<Double>();
            for ( j = filearff.getListAtribute().size() - numberOfLabel; j < filearff.getListAtribute().size(); j++ ) {
                _ds2.add(filearff.getListValue().get(j).get(i));
            }
            listCandidateFirst.get(i).setListLabel(_ds2);
        }

        //vong for nay dua cac to hop trong file tohopnhom vao 1 list. tu list nay se xac dinh nhom moi cua cac cum.
        //xac dinh cac nhan moi cua cac cum. tuong ung voi danh sach tohopnhom lay ra o tren.
        for (Candidate c : listCandidateFirst) {
            c.setLabel(filexml);
        }

        listCluster = new ArrayList<Cluster>();
        for (Candidate c : listCandidateFirst) {
            Cluster cluster = new Cluster();
            cluster.addCandidate(c);
            cluster.setLabelOfCluster(c.getLabel());
            listCluster.add(cluster);
        }
        return listCluster;
    }
    public static void main(String[] args) {
        int i,j;
//        ArrayList<Cum> listCluster = Main.ganCum("input/test.arff","input/nhan.xml");
        ArrayList<Cluster> listCluster = Main.setCluster("input/example.arff","input/nhanexample.xml");
        ArrayList<Point> phanbiet = new ArrayList<Point>();
        for (Cluster c : listCluster) {
            c.printCluster();
        }
        int test = 0;

        while (!Main.checkFinish(listCluster)) {
            Point saveIndex = new Point();
            double distance = 100;

            for (i = 0; i < listCluster.size(); i++) {
                for (j = i + 1; j < listCluster.size(); j++) {
                    Point k = new Point();
                    k.setPoint(i,j);
                    if (!k.checkDistinct(phanbiet)) {
                        double d = listCluster.get(i).computeDistance(listCluster.get(j));
                        if (d < distance) {
                            distance = d;
                            saveIndex.setPoint(i, j);// them cai setDiem(int a,int b) vao .
                        }
                    }
                }
            }
            //System.out.println(khoangcach);
            //System.out.println(saveIndex.getA() + " " + saveIndex.getB());
            if (listCluster.get(saveIndex.getA()).getLabelOfCluster() == listCluster.get(saveIndex.getB()).getLabelOfCluster()) {
                //System.out.println("bang nhau");
                listCluster.get(saveIndex.getA()).joinCluster(listCluster.get(saveIndex.getB()));
                listCluster.remove(saveIndex.getB());
                phanbiet.clear();
            } else {
                //System.out.println("khac");
                if (listCluster.get(saveIndex.getA()).getLabelOfCluster() == 0) {
                    listCluster.get(saveIndex.getB()).joinCluster(listCluster.get(saveIndex.getA()));
                    listCluster.remove(saveIndex.getA());
                    phanbiet.clear();
                } else {
                    if (listCluster.get(saveIndex.getB()).getLabelOfCluster() == 0) {
                        listCluster.get(saveIndex.getA()).joinCluster(listCluster.get(saveIndex.getB()));
                        listCluster.remove(saveIndex.getB());
                        phanbiet.clear();
                    } else{
                        listCluster.get(saveIndex.getA()).setIdentified(true);
                        listCluster.get(saveIndex.getB()).setIdentified(true);
                        phanbiet.add(saveIndex);
                    }
                }

            }
            //System.out.println("phan biet :"+phanbiet);
        }
        ArrayList <Cluster> listClusterUnlabel = Main.setCluster("input/exampleunlabel.arff","input/nhanexampletrong.xml");
        double min ;
        int index;
        for ( i = 0; i < listClusterUnlabel.size(); i++) {
            min = listClusterUnlabel.get(i).computeDistance(listCluster.get(0));
            index = 0;
            for ( j = 0; j < listCluster.size(); j++) {
                if (listClusterUnlabel.get(i).computeDistance(listCluster.get(j)) < min) {
                    min = listClusterUnlabel.get(i).computeDistance(listCluster.get(j));
                    index = j;
                }
            }
            listClusterUnlabel.get(i).setLabelOfCluster(index+1);
        }


        File file = new File("input/output.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for ( Cluster c : listClusterUnlabel) {
                bw.write(c.printClusterToString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}