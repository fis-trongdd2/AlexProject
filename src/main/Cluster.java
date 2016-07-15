package main;

import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Cluster {
    private int numberofcandidate;
    private int labelofcluster;
    private ArrayList <Candidate> listcandidate = new ArrayList<Candidate>();
    private boolean identified = false;

    public boolean getIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

    public int getListcandidateSize() {
        return listcandidate.size();
    }


    public int getLabelofcluster() {
        return labelofcluster;
    }

    public void setLabelofcluster(int labelofcluster) {
        this.labelofcluster = labelofcluster;
    }

    public ArrayList<Candidate> getListcandidate() {
        return listcandidate;
    }

    public void joinCluster (Cluster a)  {
        for (int i = 0; i < a.getListcandidateSize(); i++) {
            listcandidate.add(a.getListcandidate().get(i));
        }
        a = null;
    }
    public void addCandidate (Candidate a) {
        this.listcandidate.add(a);
    }

    public ArrayList<Double> getCentroid () {
        ArrayList<Double> a = new ArrayList<Double>(this.getListcandidate().get(0).getValue());
        int numberatribute = this.getListcandidate().get(0).getNumberOfAtribute();
        for (int i = 1; i < this.getListcandidateSize(); i++) {
            for (int j = 0; j < numberatribute; j++) {
                a.set(  j,  a.get(j) + this.getListcandidate().get(i).getValue().get(j));
            }
        }
        for (int i = 0; i < numberatribute; i++) {
            a.set(i,a.get(i)/this.getListcandidateSize());
        }
        return a;
    }
    public double getDistance (Cluster a) {
        double distance = 0;
        for (int i = 0; i < this.getCentroid().size(); i++) {
            distance += (this.getCentroid().get(i) - a.getCentroid().get(i))*(this.getCentroid().get(i) - a.getCentroid().get(i));
        }
        return Math.sqrt(distance);
    }
    public void printCluster () {
        System.out.print("List candidate : ");
        for (int i = 0; i < this.getListcandidate().size(); i++) {
            System.out.print(this.getListcandidate().get(i).getValue());
        }
        System.out.println(" Label cluster : " + this.getLabelofcluster());
    }
    public String printClusterToString () {
        String a = "list candidate : ";
        for (int i = 0; i < this.getListcandidate().size(); i++) {
            a+= this.getListcandidate().get(i).getValue();
        }
        a = a + " ;Label of cluster : " + this.getLabelofcluster();
        return a;
    }
    public static void main(String []args) {

    }
}
