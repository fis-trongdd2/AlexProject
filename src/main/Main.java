package main;

import data.ARFF;
import data.ToHopNhom;
import data.XML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Main {
    public static boolean kiemTraDung(ArrayList<Cum> kiemtra) {
        int dem = 0;
        for (int i = 0; i < kiemtra.size(); i++) {
            if (kiemtra.get(i).getSothehien() == 1){
                dem++;
			}
        }
        if (dem < 2) return true;
        return false;
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
        ToHopNhom toHopNhom= new ToHopNhom("input/nhan.txt");

        for (i = 0; i < dsthehienkhoitao.size(); i++) {
            dsthehienkhoitao.get(i).setNhan(toHopNhom);
        }
        for (i = 0; i < dsthehienkhoitao.size(); i++) {
            System.out.print(dsthehienkhoitao.get(i).getGiatri());
            System.out.println(dsthehienkhoitao.get(i).getNhan());
        }
        //-------------------ket thuc khoi tao------------------------
/*        // vong while
        ArrayList<Cum> dscum = new ArrayList<Cum>();
        int i, j;
        double khoangcach;
        Diem luuvitri = new Diem();
        ArrayList<Diem> phanbiet = new ArrayList<Diem>();
        while (!Main.kiemTraDung(dscum)) {
            khoangcach = dscum.get(0).getKhoangCach(dscum.get(1));
            for (i = 0; i < dscum.size(); i++) {
                for (j = i + 1; j < dscum.size(); j++) {
                    if (!phanbiet.contains(new Diem(i,j))) {
                        double d = dscum.get(i).getKhoangCach(dscum.get(j));
                        if (d < khoangcach) {
                            khoangcach = d;
                            luuvitri.setA(i);// them cai setDiem(int a,int b) vao .
                            luuvitri.setB(j);
                        }
                    }
                }
            }
            if (dscum.get(luuvitri.getA()).getNhancum() == dscum.get(luuvitri.getB()).getNhancum()) {
                dscum.get(luuvitri.getA()).gopCum(dscum.get(luuvitri.getB()));
                dscum.remove(luuvitri.getB());
                phanbiet.clear();
            }
            else {
                if (dscum.get(luuvitri.getA()).getNhancum() == -1) {
                    dscum.get(luuvitri.getB()).gopCum(dscum.get(luuvitri.getA()));
                    dscum.remove(luuvitri.getA());
                    phanbiet.clear();
                }
                else
                if (dscum.get(luuvitri.getB()).getNhancum() == -1) {
                    dscum.get(luuvitri.getA()).gopCum(dscum.get(luuvitri.getB()));
                    dscum.remove(luuvitri.getB());
                    phanbiet.clear();
                }
                else
                    phanbiet.add(new Diem(luuvitri.getA(),luuvitri.getB()));

            }
        }*/
    }
}