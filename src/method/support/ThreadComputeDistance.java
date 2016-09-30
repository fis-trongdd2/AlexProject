package method.support;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.Cluster;

class ThreadDistance implements Runnable {
	private Thread t;
	private String threadName;
	List<Cluster> listClusters;
	private int begin;
	private int end;
	ThreadDistance( String name,int begin,int end,List<Cluster> listClusters) {
		this.threadName = name;
		this.begin = begin;
		this.end = end;
		this.listClusters = listClusters;
	}
	public void run() {
		int j;
		int i;

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
	            for (i = begin; i < end; i++) {
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
	public void start () {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
}
public class ThreadComputeDistance {
	public static void main(String args[]) {
		List<Cluster> a = new ArrayList<Cluster>();
		
		ThreadDistance R1 = new ThreadDistance( "Thread-1",0,a.size()/4,a);
		R1.start();
		ThreadDistance R2 = new ThreadDistance( "Thread-2",a.size()/4,a.size()/2,a);
		R2.start();
		ThreadDistance R3 = new ThreadDistance( "Thread-3",a.size()/2,a.size()/4*3,a);
		R3.start();
		ThreadDistance R4 = new ThreadDistance( "Thread-4",a.size()/4*3,a.size(),a);
		R4.start();
	}   
}