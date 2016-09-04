package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import method.support.LTSupport;
import method.support.MethodSupport;


public class LiftTescMain {
	public static List<Cluster> listCluster = new ArrayList<>();
	public static void main (String []args) {
		
//		List<Candidate> example = MethodSupport.setCandidate ("input/ltexamplearff.arff","input/ltnhanexample.xml");
		List<Candidate> example = MethodSupport.setCandidate ("input/5/train5.arff","input/nhan.xml");
        List<Candidate> listCandidateTrains = new ArrayList<Candidate>();
        for (int  i = 0; i < 80; i++) {
            listCandidateTrains.add(example.get(i));
        }
		Set<Integer> l1 = new HashSet<>();
		Set<Integer> l2 = new HashSet<>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);
		List <LTObject> listObject= new ArrayList<>();
		listObject.add(new LTObject(listCandidateTrains,l1,l2,new ArrayList<>()));
		List<Candidate> dl = new ArrayList<>();
		List<Candidate> du = new ArrayList<>();
		List<Candidate> d0 = new ArrayList<>();
		List<Candidate> d1 = new ArrayList<>();
		List<Candidate> d2 = new ArrayList<>();
		int i;
			
		for (i = 0; i < listObject.size(); i++) {	
			dl.clear();
			du.clear();
			for (Candidate c : listObject.get(i).get_listCandidate()) {
				if (c.getListSeqLabel().size() == 0) {
					du.add(c);
				}
				else {
					dl.add(c);
				}
			}
			int t = LTSupport.findLamda(listObject.get(i).get_listCandidate(), listObject.get(i).getL2());
			listObject.get(i).getLamda().add(t);
			for (Candidate c :  dl) {
				c.divideToSet(listObject.get(i).getLamda());
			}
			List<Cluster> clusteringD = MethodSupport.setListClusters(listObject.get(i).get_listCandidate(), MethodSupport.ADDLABEL);
			clusteringD = MethodSupport.clustering(clusteringD);
			d0.clear();
			d1.clear();
			d2.clear();
			for (Cluster c : clusteringD) {
				if (c.getLabelOfCluster() == 1) {
					d0.addAll(c.getListCandidate());
				} 
				else {
					if (c.getLabelOfCluster() == 2) {
						d1.addAll(c.getListCandidate());
					}
					else
						d2.addAll(c.getListCandidate());
					}
				}
			if (d0.size() != 0) {
				List<Cluster> clusterD0 = MethodSupport.setListClusters(d0, MethodSupport.ADDLABEL);
				clusterD0 = MethodSupport.clustering(clusterD0);
				for (Cluster c : clusterD0) {
					listCluster.add(c);			
				}
			}
			Set<Integer> nList2 = listObject.get(i).getL2().stream().map(Integer::new).collect(Collectors.toSet());
			nList2.remove(t);
			Set<Integer> nList1 = listObject.get(i).getL1().stream().map(Integer::new).collect(Collectors.toSet());
			Set<Integer> nListClone = listObject.get(i).getL1().stream().map(Integer::new).collect(Collectors.toSet());
			if (d1.size() != 0) {
				if (LTSupport.checkLabel(d1)) {
					List<Cluster> clusterD1 = MethodSupport.setListClusters(d1, MethodSupport.ADDLABEL);
					clusterD1 = MethodSupport.clustering(clusterD1);
					for (Cluster c : clusterD1) {
						listCluster.add(c);	
					}
				}
				else {
					nList1.add(t);
					listObject.add(new LTObject(d1, nList1,nList2,listObject.get(i).getLamda()));
				}
			}
			if (d2.size() != 0) {
				if (LTSupport.checkLabel(d2)) {
					List<Cluster> clusterD2 = MethodSupport.setListClusters(d2, MethodSupport.ADDLABEL);
					clusterD2 = MethodSupport.clustering(clusterD2);
					for (Cluster c : clusterD2) {
						listCluster.add(c);			
					}
				}		
				else {
					listObject.add(new LTObject(d2 , nListClone,nList2,new ArrayList<>()));
				}
			}
			listObject.remove(i);
			i--;
		}
        List<Candidate> listCandidateTests = new ArrayList<Candidate>();
        for (i = 80; i < 120; i++) {
            try {
                listCandidateTests.add(example.get(i).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        List<Candidate> listCandidateTestsToLabel = new ArrayList<Candidate>();
        for (Candidate c :listCandidateTests) {
            try {
                listCandidateTestsToLabel.add(c.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        listCandidateTestsToLabel = MethodSupport.setLabelForUnlabel(listCandidateTestsToLabel,listCluster,-1);
        System.out.println(MethodSupport.assessClustering(listCandidateTestsToLabel,listCandidateTests));
	}
}