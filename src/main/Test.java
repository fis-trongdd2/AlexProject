package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trong_000 on 6/30/2016.
 */
public class Test {
    public static void main(String[] args) {
//        //tao du lieu
//        TheHien a = new TheHien();
//        TheHien b = new TheHien();
//
//        ArrayList<Double> giatria = new ArrayList<Double>();
//        giatria.add(4.5);
//        giatria.add(3.2);
//
//        ArrayList<Double> giatrib = new ArrayList<Double>();
//        giatrib.add(5.0);
//        giatrib.add(2.0);
//        //tao 2 the hien
//        a.setGiatri(giatria);
//        b.setGiatri(giatrib);
//
//        Cum c = new Cum();
//        Cum e = new Cum();
//        c.themTheHien(a);
//        e.themTheHien(b);
//        double kc = c.getKhoangCach(e);
//        System.out.println("tam c:" + c.getTam());
//        System.out.println("tam d:" + e.getTam());
//        System.out.println("khoang cach:" + c.getKhoangCach(e));
//        System.out.println("----gop-----");
//        c.gopCum(e);
//        System.out.println("tam c :" + c.getTam());
        ArrayList<String> listA =new ArrayList<String>();
        listA.add("a");
        listA.add("b");
        listA.add("c");
        listA.add("d");

        ArrayList<String> listB =new ArrayList<String>();
        listB.add("a");
        listB.add("b");
        listB.add("c");
        listB.add("d");
        if (listA.equals(listB)) System.out.println("1");
    }
}
