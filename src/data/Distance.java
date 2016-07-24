package data;

import main.*;
import main.Point;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by trong_000 on 7/19/2016.
 * Class nay co muc dich luu tat cac cac khoang cach giua cac cum vao 1 file distance.txt
 *
 * Trong do co phuong thuc static updateDistance() rat quan trong : cap nhat lai danh sach khoang cach nay,
 * sau khi gop cum
 */
public class Distance {
    private Map<Point,Double> distances_;
    //chi cho phep lay map khoang cach nay tu file distance ra
    public Map<Point, Double> getMapDistances () {
        Map nDistances_ = new HashMap<Point, Double>();
        Path filePath = Paths.get("input/distance.txt");
        Scanner scanner = null;
        Point p;
        double a,b,c;
        try {
            scanner = new Scanner(filePath);
            int j = 0;
            while (scanner.hasNext()) {
                a = scanner.nextDouble();
                b = scanner.nextDouble();
                c = scanner.nextDouble();
                p = new Point(a,b);
                nDistances_.put(p,c);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        distances_ = new LinkedHashMap<Point, Double>();
        distances_ = sortByComparator(nDistances_);
        System.out.println("size after set" + distances_.size());
        return distances_;
    }
    //them cac gia tri vao map nay. dong thoi ghi ra file input/distance
    public void setMapDistancesSorted (List<Cluster> listClusters){
        int i,j;
        File file = new File("input/distance.txt");
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
        double d = 0;
        BufferedWriter bw = new BufferedWriter(fw);
        int count = 0;
        try {
            for (i = 0; i < listClusters.size(); i++) {
                for (j = i + 1; j < listClusters.size(); j++) {
                    d = listClusters.get(i).computeDistance(listClusters.get(j));
                    bw.write(i + " " + j + " " + d +" ");
                    count++;
                    if (count == 10) {
                        bw.newLine();
                        count = 0;
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map updateDistance (List<Cluster> clusters, Map map, int x, int y) {
        List<Map.Entry<Point,Double>> list =
                new LinkedList<Map.Entry<Point,Double>>(map.entrySet());
        for (Iterator<Map.Entry<Point,Double>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Point,Double> entry = it.next();
            if (entry.getKey().getB() == y || entry.getKey().getA() == y){
                //System.out.println("da xoa " + entry.getKey().getA() +" "+entry.getKey().getB());
                map.remove(entry.getKey());
            }
            else {
                //System.out.println("size sau khi xoa  "+map.size()+"x="+x+"y=" +y);
                if (entry.getKey().getB() > y ){
                    if (entry.getKey().getA() > y ) {
                        //System.out.println("them x,y : "+entry.getKey().getA()+" "+entry.getKey().getB());
                        map.put(new Point(entry.getKey().getA()-1, entry.getKey().getB() - 1), entry.getValue());
                        map.remove(entry.getKey());
                    }
                    else {
                        //System.out.println("them y : "+entry.getKey().getA()+" "+entry.getKey().getB());
                        map.put(new Point(entry.getKey().getA(), entry.getKey().getB() - 1), entry.getValue());
                        map.remove(entry.getKey());
                    }
                }
            }
        }

        list = new LinkedList<Map.Entry<Point,Double>>(map.entrySet());
        //System.out.println("map size" + map.size());
        for (Iterator<Map.Entry<Point,Double>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Point,Double> entry = it.next();
            if (entry.getKey().getA() == x || entry.getKey().getB() == x){
                map.put(entry.getKey(),clusters.get(entry.getKey().getA()).computeDistance(clusters.get(entry.getKey().getB())));
            }
        }
        return sortByComparator(map);
    }
    private static Map<Point,Double> sortByComparator(Map<Point,Double> unsortMap) {

        // Convert Map to List
        List<Map.Entry<Point,Double>> list =
                new LinkedList<Map.Entry<Point,Double>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<Point,Double>>() {
            public int compare(Map.Entry<Point,Double> o1,
                               Map.Entry<Point,Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<Point,Double> sortedMap = new LinkedHashMap<Point,Double>();
        for (Iterator<Map.Entry<Point,Double>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Point,Double> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    public static void main (String []args) {
       Map<Point,Double> map = new HashMap<Point,Double>();
        map.put(new Point(1,2), 10.0);
        map.put(new Point(1,2), 30.0);
        map.put(new Point(1,2), 50.0);
        map.put(new Point(1,2), 40.0);
        map.put(new Point(1,2), 100.0);
        map.put(new Point(1,2), 60.0);
        map.put(new Point(1,2), 110.0);
        map.put(new Point(1,2), 50.0);
        map.put(new Point(1,2), 90.0);
        map.put(new Point(1,2), 70.0);
        map.put(new Point(1,2), 80.0);
        map = sortByComparator(map);
        for (Map.Entry<Point,Double> entry : map.entrySet()) {
            System.out.println("[Key] : " + entry.getKey()
                    + " [Value] : " + entry.getValue());
        }

    }
}
