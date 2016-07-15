package main;

import java.util.ArrayList;

/**
 * Created by trong_000 on 6/30/2016.
 */
public class Point {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    public void setDiem (int x, int y) {
        this.a = x;
        this.b = y;
    }
    public boolean kiemTraPhanBiet (ArrayList<Point> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if  (this.a == arr.get(i).getA() && this.b == arr.get(i).getB())
                return true;
        }

        return false;
    }
    public static void main(String []args) {
        Point a = new Point();
        System.out.println(a.getA()+" "+a.getB());
    }
}
