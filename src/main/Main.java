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
    public static boolean kiemTraDung(ArrayList<Cluster> listcheck) {
        int a = listcheck.size();
        int count = 0;
        for (int i = 0; i < a; i++) {
            if (count >= 2) return false;
            if (listcheck.get(i).getIdentified() == false) {
                count++;
            }
        }
        return true;
    }
    public static ArrayList<Cluster> ganCum (String linkarff, String linkxml) {
        ArrayList<Cluster> dscum = new ArrayList<Cluster>();
        ArrayList<Candidate> dsthehienkhoitao = new ArrayList<Candidate>();
        ARFF filearff = new ARFF(linkarff);
        XML filexml = new XML(linkxml);
//        ARFF filearff = new ARFF("input/test.arff");
//        XML filexml = new XML("input/nhan.xml");
        int soluongnhan = filexml.getSoluongnhan();
        int i,j;


        // vong for nay dua cac the hien vao 1 list. cac the hien nay da dc chuyen sang tu dang rut gon.
        for ( i = 0; i < filearff.getSothehienbandau(); i++) {
            Candidate _thehien = new Candidate();
            ArrayList<Double> _ds = new ArrayList<Double>();
            for ( j = 0; j < filearff.getDsthuoctinh().size() - soluongnhan; j++ ) {
                _ds.add(filearff.getDsgiatri().get(j).get(i));
            }
            _thehien.setValue(_ds);
            dsthehienkhoitao.add(_thehien);
        }

        //vong for nay lay cac nhan dua vao ds nhan cua tung the hien.
        for ( i = 0; i < filearff.getSothehienbandau(); i++) {
            ArrayList<Double> _ds2 = new ArrayList<Double>();
            for ( j = filearff.getDsthuoctinh().size() - soluongnhan; j < filearff.getDsthuoctinh().size(); j++ ) {
                _ds2.add(filearff.getDsgiatri().get(j).get(i));
            }
            dsthehienkhoitao.get(i).setListlabel(_ds2);
        }

        //vong for nay dua cac to hop trong file tohopnhom vao 1 list. tu list nay se xac dinh nhom moi cua cac cum.
        //xac dinh cac nhan moi cua cac cum. tuong ung voi danh sach tohopnhom lay ra o tren.
        for (i = 0; i < dsthehienkhoitao.size(); i++) {
            dsthehienkhoitao.get(i).setLabel(filexml);
        }

        dscum = new ArrayList<Cluster>();
        for (i = 0; i < dsthehienkhoitao.size(); i++ ) {
            Cluster cum = new Cluster();
            cum.addCandidate(dsthehienkhoitao.get(i));
            cum.setLabelofcluster(dsthehienkhoitao.get(i).getLabel());
            dscum.add(cum);
        }
        return dscum;
    }
    public static void main(String[] args) {
        int i,j;
//        ArrayList<Cum> dscum = Main.ganCum("input/test.arff","input/nhan.xml");
        System.out.println("first");
        ArrayList<Cluster> dscum = Main.ganCum("input/example.arff","input/nhanexample.xml");
        System.out.println("contructor");
        ArrayList<Point> phanbiet = new ArrayList<Point>();
        int test = 0;

        while (!Main.kiemTraDung(dscum)) {
            System.out.print("w");
            Point luuvitri = new Point();
            double khoangcach = 100;

            for (i = 0; i < dscum.size(); i++) {
                for (j = i + 1; j < dscum.size(); j++) {
                    Point k = new Point();
                    k.setDiem(i,j);
                    if (!k.kiemTraPhanBiet(phanbiet)) {
                        double d = dscum.get(i).getDistance(dscum.get(j));
                        if (d < khoangcach) {
                            khoangcach = d;
                            luuvitri.setDiem(i, j);// them cai setDiem(int a,int b) vao .
                        }
                    }
                }
            }
            //System.out.println(khoangcach);
            //System.out.println(luuvitri.getA() + " " + luuvitri.getB());
            if (dscum.get(luuvitri.getA()).getLabelofcluster() == dscum.get(luuvitri.getB()).getLabelofcluster()) {
                //System.out.println("bang nhau");
                dscum.get(luuvitri.getA()).joinCluster(dscum.get(luuvitri.getB()));
                dscum.remove(luuvitri.getB());
                phanbiet.clear();
            } else {
                //System.out.println("khac");
                if (dscum.get(luuvitri.getA()).getLabelofcluster() == 0) {
                    dscum.get(luuvitri.getB()).joinCluster(dscum.get(luuvitri.getA()));
                    dscum.remove(luuvitri.getA());
                    phanbiet.clear();
                } else {
                    if (dscum.get(luuvitri.getB()).getLabelofcluster() == 0) {
                        dscum.get(luuvitri.getA()).joinCluster(dscum.get(luuvitri.getB()));
                        dscum.remove(luuvitri.getB());
                        phanbiet.clear();
                    } else{
                        dscum.get(luuvitri.getA()).setIdentified(true);
                        dscum.get(luuvitri.getB()).setIdentified(true);
                        phanbiet.add(luuvitri);
                    }
                }

            }
            //System.out.println("phan biet :"+phanbiet);
        }
        ArrayList <Cluster> dscumkonhan = Main.ganCum("input/exampleunlabel.arff","input/nhanexampletrong.xml");
        double min ;
        int index;
        for ( i = 0; i < dscumkonhan.size(); i++) {
            min = dscumkonhan.get(i).getDistance(dscum.get(0));
            index = 0;
            for ( j = 0; j < dscum.size(); j++) {
                if (dscumkonhan.get(i).getDistance(dscum.get(j)) < min) {
                    min = dscumkonhan.get(i).getDistance(dscum.get(j));
                    index = j;
                }
            }
            dscumkonhan.get(i).setLabelofcluster(index+1);
        }


        //tu so nhan ghi vao file nhan.txt cac to hop nhom
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
            for ( i = 0; i < dscumkonhan.size(); i++) {
                bw.write(dscumkonhan.get(i).printClusterToString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}