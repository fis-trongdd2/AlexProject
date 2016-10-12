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
	private String linkFile;
	ThreadDistance( String name,int begin,int end,List<Cluster> listClusters,String link) {
		this.threadName = name;
		this.begin = begin;
		this.end = end;
		this.listClusters = listClusters;
		this.linkFile = link;
	}
	public void run() {
		int j;
		int i;

		File file = new File(linkFile);
    	if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
	                e.printStackTrace();
			}
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile(),false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		double d = 0;
		BufferedWriter bw = new BufferedWriter(fw);
		int count = 0;
		try {
			for (i = begin; i < end; i++) {
				for (j = i + 1; j < listClusters.size(); j++) {
					count++;
					d = listClusters.get(i).computeDistance(listClusters.get(j));
					bw.write(" " + i + " " + j + " " + d);
					if (count > 10) {
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
	public void start () {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
	
}
  public class ThreadComputeDistance {
		public static void main(String args[]) throws CloneNotSupportedException, IOException, InterruptedException {
//			List<Candidate> example = MethodSupport.setCandidate ("input/5/5.valid.arff","input/nhan.xml");
//	        List<Candidate> listCandidateTrains = new ArrayList<Candidate>();
//	        for (int  i = 0; i < 100; i++) {
//	            listCandidateTrains.add(example.get(i).clone());
//	        }
//	        List<Cluster> a = MethodSupport.setListClusters(listCandidateTrains,MethodSupport.LABEL);
//	        MethodSupport.runThreadDistance(a);
			Distance test = new Distance();

			Map<Point,Double> map = null;
//			= test.getMapDistances();
	        for (Map.Entry<Point,Double> entry : map.entrySet()) {
	            System.out.println("[A] : " + entry.getKey().getA()+ "[B]" + entry.getKey().getB()
	                    + " [Value] : " + entry.getValue());
	        }

			System.out.println(map.size());
		}   
	}
