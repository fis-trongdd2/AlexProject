package method.support;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.Distance;
import main.Candidate;
import main.Cluster;
import main.Point;

class ThreadDistance implements Runnable {
	private Thread t;
	private String threadName;
	List<Cluster> listClusters;
	private int begin;
	private int end;
	private static final String fileDistance = "input/distance.txt";
	ThreadDistance( String name,int begin,int end,List<Cluster> listClusters) {
		this.threadName = name;
		this.begin = begin;
		this.end = end;
		this.listClusters = listClusters;
	}
	public void run() {
		int j;
		int i;
		File file = new File(fileDistance);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		double d = 0;
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			for (i = begin; i < end; i++) {
				for (j = i + 1; j < listClusters.size(); j++) {
					d = listClusters.get(i).computeDistance(listClusters.get(j));
					bw.write(i + " " + j + " " + d +" ");
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start () {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
	
}
  public class ThreadComputeDistance {
		public static void main(String args[]) throws CloneNotSupportedException, IOException, InterruptedException {
			List<Candidate> example = MethodSupport.setCandidate ("input/5/5.valid.arff","input/nhan.xml");
	        List<Candidate> listCandidateTrains = new ArrayList<Candidate>();
	        for (int  i = 0; i < 30; i++) {
	            listCandidateTrains.add(example.get(i).clone());
	        }
	        List<Cluster> a = MethodSupport.setListClusters(listCandidateTrains,MethodSupport.LABEL);
	        MethodSupport.runThreadDistance(a);
			Distance test = new Distance();
	        Map<Point,Double> map = test.getMapDistances();
			System.out.println(map.size());
		}   
	}
