package data;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by trong_000 on 6/30/2016.
 */
public class DocFile {
    public static void main(String []args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("input/test.arff"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArffLoader.ArffReader arff = null;
        try {
            arff = new ArffLoader.ArffReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Instances data = arff.getData();

        for (int i = 0; i < data.numAttributes(); i++)
        {
            System.out.print(data.attribute(i).name() + ": ");
            double[] values = data.attributeToDoubleArray(i);
            System.out.println(Arrays.toString(values));
        }
        System.out.println("------------------------------");
        for (int i = 0; i < data.attributeToDoubleArray(0).length; i++) {
            System.out.print(data.attributeToDoubleArray(0)[i] + " ");

        }
        System.out.println(data.numAttributes());

    }
}
