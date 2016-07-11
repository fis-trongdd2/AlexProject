package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by trong_000 on 7/11/2016.
 */
public class GhiToHop {
    public static void ghiToHop (int sonhan) {
        int x = sonhan;
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
            for(int i = 0; i <= Math.pow(2, x)- 1; i++){
                int tmp = Integer.parseInt(Integer.toBinaryString(i));
                for(int j = 0; j < x; j++ ){
                        bw.write(tmp%10+" ");
                        tmp = tmp /10;
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        GhiToHop.ghiToHop(5);
     }
}
