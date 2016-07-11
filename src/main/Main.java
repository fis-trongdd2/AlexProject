package main;

import data.ARFF;
import data.XML;

import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Main {
    public static boolean kiemTraDung(ArrayList<Cum> kiemtra) {
        int a = kiemtra.size();
        for (int i = 0; i < a; i++) {
            for (int j = i + 1; j < a; j++) {
                if (kiemtra.get(i).getNhancum() == kiemtra.get(j).getNhancum()) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        //------------------------ cac buoc khoi tao---------------------------
        ArrayList<TheHien> dsthehienkhoitao = new ArrayList<TheHien>();
        ARFF filearff = new ARFF("input/test.arff");
        XML filexml = new XML("input/nhan.xml");
        int soluongnhan = filexml.getSoluongnhan();
        int i,j;


        // vong for nay dua cac the hien vao 1 list. cac the hien nay da dc chuyen sang tu dang rut gon.
        for ( i = 0; i < filearff.getSothehienbandau(); i++) {
            TheHien _thehien = new TheHien();
            ArrayList<Double> _ds = new ArrayList<Double>();
            for ( j = 0; j < filearff.getDsthuoctinh().size() - soluongnhan; j++ ) {
                _ds.add(filearff.getDsgiatri().get(j).get(i));
            }
            _thehien.setGiatri(_ds);
            dsthehienkhoitao.add(_thehien);
        }

        //vong for nay lay cac nhan dua vao ds nhan cua tung the hien.
        for ( i = 0; i < filearff.getSothehienbandau(); i++) {
            ArrayList<Double> _ds2 = new ArrayList<Double>();
            for ( j = filearff.getDsthuoctinh().size() - soluongnhan; j < filearff.getDsthuoctinh().size(); j++ ) {
                _ds2.add(filearff.getDsgiatri().get(j).get(i));
            }
            dsthehienkhoitao.get(i).setDsnhan(_ds2);
        }

        //vong for nay dua cac to hop trong file tohopnhom vao 1 list. tu list nay se xac dinh nhom moi cua cac cum.
        //xac dinh cac nhan moi cua cac cum. tuong ung voi danh sach tohopnhom lay ra o tren.
        for (i = 0; i < dsthehienkhoitao.size(); i++) {
            dsthehienkhoitao.get(i).setNhan(filexml);
        }

        //        -------------------ket thuc khoi tao------------------------
        // vong while
        ArrayList<Cum> dscum = new ArrayList<Cum>();
        for (i = 0; i < dsthehienkhoitao.size(); i++ ) {
            Cum cum = new Cum();
            cum.themTheHien(dsthehienkhoitao.get(i));
            cum.setNhancum(dsthehienkhoitao.get(i).getNhan());
            dscum.add(cum);
        }
        for ( i = 0; i < dscum.size(); i++) {
            dscum.get(i).inThongTinCum();
        }

        ArrayList<Diem> phanbiet = new ArrayList<Diem>();
        while (!Main.kiemTraDung(dscum)) {
            Diem luuvitri = new Diem();
            double khoangcach = 100;

            for (i = 0; i < dscum.size(); i++) {
                for (j = i + 1; j < dscum.size(); j++) {
                    Diem k = new Diem();
                    k.setDiem(i,j);
                    if (!k.kiemTraPhanBiet(phanbiet)) {
                        double d = dscum.get(i).getKhoangCach(dscum.get(j));
                        if (d < khoangcach) {
                            khoangcach = d;
                            luuvitri.setDiem(i, j);// them cai setDiem(int a,int b) vao .
                        }
                    }
                }
            }
            System.out.println(khoangcach);
            System.out.println(luuvitri.getA() + " " + luuvitri.getB());
            if (dscum.get(luuvitri.getA()).getNhancum() == dscum.get(luuvitri.getB()).getNhancum()) {
                System.out.println("bang nhau");
                dscum.get(luuvitri.getA()).gopCum(dscum.get(luuvitri.getB()));
                dscum.remove(luuvitri.getB());
                phanbiet.clear();
            } else {
                System.out.println("khac");
                if (dscum.get(luuvitri.getA()).getNhancum() == 0) {
                    dscum.get(luuvitri.getB()).gopCum(dscum.get(luuvitri.getA()));
                    dscum.remove(luuvitri.getA());
                    phanbiet.clear();
                } else {
                    if (dscum.get(luuvitri.getB()).getNhancum() == 0) {
                        dscum.get(luuvitri.getA()).gopCum(dscum.get(luuvitri.getB()));
                        dscum.remove(luuvitri.getB());
                        phanbiet.clear();
                    } else
                        phanbiet.add(luuvitri);
                    }

            }
            System.out.println("phan biet :"+phanbiet);
            System.out.println("");
        }
        for ( i = 0; i < dscum.size(); i++) {
            dscum.get(i).inThongTinCum();
        }
//        Cum test = new Cum();
//        ArrayList<Double> d = new ArrayList<Double>();d.add(6.0);d.add(5.0);
//        TheHien test1 = new TheHien();
//        test1.setGiatri(d);
//        test.themTheHien(test1);
//        for (i = 0; i < dscum.size(); i++) {
//            System.out.println(test.getKhoangCach(dscum.get(i)));
//        }
    }
}