package main;

import data.XML;

import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class TheHien {
    private ArrayList <Double> giatri;
    private ArrayList <Double> dsnhan;

    private int nhan;

    public ArrayList<Double> getGiatri() {
        return giatri;
    }

    public void setGiatri(ArrayList<Double> giatri) {
        this.giatri = giatri;
    }

    public int getNhan() {
        return nhan;
    }

    public void setNhan(int nhan) {
        this.nhan = nhan;

    }
    public void setNhan(XML a) {
        this.nhan = -1;
        for (int i = 0; i < a.getDstohop().size(); i++) {
            if (this.dsnhan.equals( a.getDstohop().get(i))) {
                this.nhan = i;
                break;
            }
        }
    }
    public int getSoThuocTinh () {
        return giatri.size();
    }

    public ArrayList<Double> getDsnhan() {
        return dsnhan;
    }

    public void setDsnhan(ArrayList<Double> dsnhan) {
        this.dsnhan = dsnhan;
    }
    public  void  themGiaTri (double a) {
        this.giatri.add(a);
    }
}
