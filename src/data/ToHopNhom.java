package data;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by trong_000 on 7/1/2016.
 *
 * doc file nhan.txt .dua cac to hop vao trong 1 list.
 * day la co so de xd nhan moi cho cac the hien.
 */
public class ToHopNhom {
    ArrayList<ArrayList<Double>> dstohop;

    public ToHopNhom(String link) {
        dstohop = new ArrayList<ArrayList<Double>>();
        Path filePath = Paths.get(link);
        Scanner scanner = null;
        ArrayList<Double> con = new ArrayList<Double>();
        XML xml = new XML("");
        int a = xml.getSoluongnhan();
        try {
            scanner = new Scanner(filePath);
            int j = 0;
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    double q = scanner.nextDouble();
                    con.add(q);
                    j++;
                    if (j >= a) {
                        dstohop.add(con);
                        con = new ArrayList<Double>();
                        j = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ArrayList<Double>> getDstohop() {
        return dstohop;
    }

    public void setDstohop(ArrayList<ArrayList<Double>> dstohop) {
        this.dstohop = dstohop;
    }
    public static void main(String []args) {
        ToHopNhom test = new ToHopNhom("input/nhan2.txt");
        System.out.print(test.getDstohop());
    }
}
