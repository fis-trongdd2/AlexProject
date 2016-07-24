package main;

import data.WritingFile;
import method.support.MethodSupport;
import java.util.*;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Main {

    public static void RUN (List<Candidate> listCandidatesLabel, List<Candidate> listCandidatesUnlabel, int x) {
        int i;
        int nTrain = x*listCandidatesLabel.size()/10;

        //tao list train: co x phan tu
        System.out.println("--------------Initializing_listCandidateTrains----------------------");
        ArrayList<Candidate> listCandidateTrains = new ArrayList<Candidate>();
        for ( i = 0; i < nTrain; i++) {
            listCandidateTrains.add(listCandidatesLabel.get(i));
        }

        //tao list test: co y phan tu
        System.out.println("--------------Initializing_listCandidateTests-----------------------");
        ArrayList<Candidate> listCandidateTests = new ArrayList<Candidate>();
        for ( i = nTrain; i < listCandidatesLabel.size(); i++) {
            listCandidateTests.add(listCandidatesLabel.get(i));
        }


        System.out.println("--------------CLUSTERING--------------------------------------------");
        //them list train vao khoi tao.
        List<Cluster> listClusterTrains = MethodSupport.setListClusters(listCandidateTrains);
        //phan cum : dua vao list cu tra ve list moi
        listClusterTrains = MethodSupport.clustering(listClusterTrains);
        WritingFile.writeClustersToFile(listClusterTrains);


        //gan nhan cho tap test
        System.out.println("-------------------setLabelForTEST----------------------------------");
        List<Candidate> listTestToLabel = MethodSupport.setLabelForUnlabel(listCandidateTests,listClusterTrains,-1);
        System.out.println("-------------------WRITE_TO_TEST------------------------------------");
        WritingFile.writeCandidatesToFile(listTestToLabel,"test");
        System.out.println("-------------------ASSESSING------------------------------------");
        System.out.println(MethodSupport.assessClustering(listCandidateTests,listTestToLabel));

        //gan nhan cho tap ko nhan, mk dua vao input la so unlabel muon gan : 40 60 80
        System.out.println("-------------------setLabelForUnlabel-------------------------------");
        listCandidatesUnlabel = MethodSupport.setLabelForUnlabel(listCandidatesUnlabel,listClusterTrains,60);
        System.out.println("-------------------WRITE_TO_Unlabel-------------------------------");
        WritingFile.writeCandidatesToFile(listCandidatesUnlabel,"unlabeled");


    }
    public static void main(String[] args) {
        System.out.println("-------------------START---------------------------------------------");


        System.out.println("--------------Initializing_list_candidate_LABEL ---------------------");
        ArrayList<Candidate> listCandidatesLabel = MethodSupport.setCluster("input/label2000_5.arff","input/nhan.xml");

        System.out.println("--------------Initializing_list_candidate_UNLABEL--------------------");
        ArrayList<Candidate> listCandidatesUnlabel = MethodSupport.setCluster("input/unlabeled5.arff","input/nhanexampletrong.xml");;


        System.out.println("-------------------RUNNING-------------------------------------------");
        RUN(listCandidatesLabel,listCandidatesUnlabel,1);


        System.out.println("---------------------END---------------------------------------------");

    }
}