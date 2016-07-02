package main;

import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Cum {
    private int sothehien;
    private int nhancum;
    private ArrayList <TheHien> dsthehien = new ArrayList<TheHien>();

    public int getSothehien() {
        return dsthehien.size();
    }

    public void setSothehien(int sothehien) {
        this.sothehien = sothehien;
    }

    public int getNhancum() {
        return nhancum;
    }

    public void setNhancum(int nhancum) {
        this.nhancum = nhancum;
    }

    public ArrayList<TheHien> getDsthehien() {
        return dsthehien;
    }

    public void setDsthehien(ArrayList<TheHien> dsthehien) {
        this.dsthehien = dsthehien;
    }
    public void gopCum (Cum a)  {
        for (int i = 0; i < a.getSothehien(); i++) {
            this.dsthehien.add(a.getDsthehien().get(i));
        }
    }
    public void themTheHien (TheHien a) {
        this.dsthehien.add(a);
    }
    public ArrayList<Double> getTam () {
        ArrayList<Double> a = new ArrayList<Double>(dsthehien.get(0).getGiatri());
        int sothuoctinh = dsthehien.get(0).getSoThuocTinh();
        for (int i = 1; i < dsthehien.size(); i++) {
            for (int j = 0; j < sothuoctinh; j++) {
                a.set(  j,  a.get(j) + dsthehien.get(i).getGiatri().get(j));
            }
        }
        for (int i = 0; i < dsthehien.size(); i++) {
            a.set(i,a.get(i)/dsthehien.size());
        }
        return a;
    }
    public double getKhoangCach (Cum a) {
        double khoangcach = 0;
        for (int i = 0; i < this.getTam().size(); i++) {
            khoangcach += (this.getTam().get(i) - a.getTam().get(i))*(this.getTam().get(i) - a.getTam().get(i));
        }
        return Math.sqrt(khoangcach);
    }
}
