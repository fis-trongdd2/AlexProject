package data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by trong_000 on 7/1/2016.
 * doc file xml de xd cac nhan.
 * cac nhan thuong la cac thuoc tinh cuoi cung, nen o day chi tim so nhan,sau do
 * doc nguoc file arff va lay dung so nhan nay, ta co tap nhan.
 */
public class XML {
    int soluongnhan;
    ArrayList<ArrayList<Double>> dstohop;

    public ArrayList<ArrayList<Double>> getDstohop() {
        return dstohop;
    }

    public void setDstohop(ArrayList<ArrayList<Double>> dstohop) {
        this.dstohop = dstohop;
    }

    public XML(String linkxml) {
        int dem = 0;
        try {
            File inputFile = new File(linkxml);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("label");
            dem = nList.getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.soluongnhan = dem;

        File file = new File("input/nhan.txt");
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
            for(int i = 0; i <= Math.pow(2, dem)- 1; i++){
                int tmp = Integer.parseInt(Integer.toBinaryString(i));
                for(int j = 0; j < dem; j++ ){
                    bw.write(tmp%10+" ");
                    tmp = tmp /10;
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        dstohop = new ArrayList<ArrayList<Double>>();
        Path filePath = Paths.get("input/nhan.txt");
        Scanner scanner = null;
        ArrayList<Double> con = new ArrayList<Double>();
        int a = this.getSoluongnhan();
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

    public int getSoluongnhan() {
        return soluongnhan;
    }

    public void setSoluongnhan(int soluongnhan) {
        this.soluongnhan = soluongnhan;
    }
    public static  void  main(String []args) {
        XML test = new XML("input/nhanexample.xml");
        System.out.print(test.getSoluongnhan());
        System.out.print(test.getDstohop().size());

    }
}
