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
            dsthehien.add(a.getDsthehien().get(i));
        }
        a = null;
    }
    public void themTheHien (TheHien a) {
        this.dsthehien.add(a);
    }

    public ArrayList<Double> getTam () {
        ArrayList<Double> a = new ArrayList<Double>(this.getDsthehien().get(0).getGiatri());
        int sothuoctinh = this.getDsthehien().get(0).getSoThuocTinh();
        for (int i = 1; i < this.getSothehien(); i++) {
            for (int j = 0; j < sothuoctinh; j++) {
                a.set(  j,  a.get(j) + this.getDsthehien().get(i).getGiatri().get(j));
            }
        }
        for (int i = 0; i < sothuoctinh; i++) {
            a.set(i,a.get(i)/this.getSothehien());
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
    public void inThongTinCum () {
        System.out.print("Danh sach the hien : ");
        for (int i = 0; i < this.getDsthehien().size(); i++) {
            System.out.print(this.getDsthehien().get(i).getGiatri());
        }
        System.out.println(" Nhan cum : " + this.nhancum);
    }
    public static void main(String []args) {
        ArrayList<Double> giatri = new ArrayList<Double>();
        giatri.add(7.6);
        giatri.add(3.6);

        ArrayList<Double> giatri2 = new ArrayList<Double>();
        giatri2.add(7.4);
        giatri2.add(3.2);

        ArrayList<Double> giatri3 = new ArrayList<Double>();
        giatri3.add(8.1);
        giatri3.add(3.2);

        TheHien th1 = new TheHien();
        th1.setGiatri(giatri);

        TheHien th2 = new TheHien();
        th2.setGiatri(giatri2);

        TheHien th3 = new TheHien();
        th3.setGiatri(giatri3);

        Cum cum1 = new Cum();
        cum1.themTheHien(th1);
        cum1.themTheHien(th2);
        cum1.themTheHien(th3);

        System.out.println(cum1.getDsthehien().get(1).getGiatri());
        System.out.println(cum1.getTam());

    }
}
