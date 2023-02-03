package ReplicatePackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;



public class NWFileGenerator {


		public static HashSet<Integer> Linknew1 = new HashSet<Integer>();

		public static HashSet<Integer> Linknewnew1 = new HashSet<Integer>();
		
		public static HashSet<String> Linknew1_uuid = new HashSet<String>();

		public static HashSet<String> Linknewnew1_uuid = new HashSet<String>();

		private static int AUTO_INCREMENT_ID = 1;
		private static int AUTO_INCREMENT_ID1 = 1;
		public static ArrayList<Vertices> uniquevertices = new ArrayList<>(); //for pid
		public static ArrayList<Vertices> uniquevertices1 = new ArrayList<>(); //for pid 
		public static ArrayList<VerticesUUID> uniquevertices_uuid = new ArrayList<>(); //for pid
		public static ArrayList<VerticesUUID> uniquevertices1_uuid = new ArrayList<>(); //for pid 
		

		public static void generateNWFile1(ArrayList<Link> Linklist, int sliceNo, ArrayList<Integer> PID) {
			uniquevertices.clear();
			uniquevertices1.clear();
			Linknew1.clear();
			Linknewnew1.clear();
			AUTO_INCREMENT_ID = 1;
			AUTO_INCREMENT_ID1 = 1;
			int slice = sliceNo +1;
			
			Linknew1.addAll(PID);
			PID.clear();
			PID.addAll(Linknew1);
			

			Collections.sort(PID);
			for (int x = 0; x < PID.size(); x++) {
				Vertices Vertice = new Vertices(x+1,PID.get(x));
				Vertice.setNumberlist(x+1);
				Vertice.setPID(PID.get(x));
				
				uniquevertices.add(Vertice);
			}
			

			for (int x = 0; x < uniquevertices.size(); x++) {
				for (int z = 0; z < Linklist.size(); z++) {

					if ( uniquevertices.get(x).getPID().equals(Linklist.get(z).getStartVertex())) {

						Linklist.get(z).setStartVertex(uniquevertices.get(x).getNumberlist());
					}

				}
			}
			for (int x = 0; x < uniquevertices.size(); x++) {
				for (int z = 0; z < Linklist.size(); z++) {
					if (uniquevertices.get(x).getPID().equals(Linklist.get(z).getEndVertex())) {

						Linklist.get(z).setEndVertex(uniquevertices.get(x).getNumberlist());
					}
				}
			}

			try {

				PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\Comments Network\\Comments_common50_" + slice + ".net", true)); //change file name as needed
				writer.println("*Vertices " + PID.size());

				for (int ctr = 0; ctr < PID.size(); ctr++) {

					writer.printf("%d \"%d\" \n", AUTO_INCREMENT_ID++, PID.get(ctr));

				}

				writer.println("\n*Edges");
				for (int ctr = 0; ctr < Linklist.size(); ctr++) {
					writer.println(Linklist.get(ctr).getStartVertex() + " " + Linklist.get(ctr).getEndVertex() + " "
							+ Linklist.get(ctr).getWeight());

				}

				writer.close();
				System.out.println("done");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				System.out.println(dtf.format(now)); // 2016/11/16 12:08:43

			} catch (IOException E) {

			}
		}
		
		public static void generateNWFile2(ArrayList<Link> Linklist, int sliceNo, ArrayList<Integer> PID) {
			uniquevertices.clear();
			uniquevertices1.clear();
			Linknew1.clear();
			Linknewnew1.clear();
			AUTO_INCREMENT_ID = 1;
			AUTO_INCREMENT_ID1 = 1;
			int slice = sliceNo +1;
			
			Linknew1.addAll(PID);
			PID.clear();
			PID.addAll(Linknew1);
			

			Collections.sort(PID);
			for (int x = 0; x < PID.size(); x++) {
				Vertices Vertice = new Vertices(x+1,PID.get(x));
				Vertice.setNumberlist(x+1);
				Vertice.setPID(PID.get(x));
				
				uniquevertices.add(Vertice);
			}
			

			for (int x = 0; x < uniquevertices.size(); x++) {
				for (int z = 0; z < Linklist.size(); z++) {

					if ( uniquevertices.get(x).getPID().equals(Linklist.get(z).getStartVertex())) {

						Linklist.get(z).setStartVertex(uniquevertices.get(x).getNumberlist());
					}

				}
			}
			for (int x = 0; x < uniquevertices.size(); x++) {
				for (int z = 0; z < Linklist.size(); z++) {
					if (uniquevertices.get(x).getPID().equals(Linklist.get(z).getEndVertex())) {

						Linklist.get(z).setEndVertex(uniquevertices.get(x).getNumberlist());
					}
				}
			}

			try {

				PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\Comments Network\\Comments_common50_" + slice + ".net", true)); //change file name as needed
				writer.println("*Vertices " + PID.size());

				for (int ctr = 0; ctr < PID.size(); ctr++) {

					writer.printf("%d \"%d\" \n", AUTO_INCREMENT_ID++, PID.get(ctr));

				}

				writer.println("\n*Edges");
				for (int ctr = 0; ctr < Linklist.size(); ctr++) {
					writer.println(Linklist.get(ctr).getStartVertex() + " " + Linklist.get(ctr).getEndVertex() + " "
							+ Linklist.get(ctr).getWeight());

				}

				writer.close();
				System.out.println("done");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				System.out.println(dtf.format(now)); // 2016/11/16 12:08:43

			} catch (IOException E) {

			}
		}
		
		public static void generateNWFile1_UUID(ArrayList<LinkUUID> Linklist, int sliceNo, ArrayList<String> PID) {
			uniquevertices_uuid.clear();
			uniquevertices1_uuid.clear();
			Linknew1_uuid.clear();
			Linknewnew1_uuid.clear();
			AUTO_INCREMENT_ID = 1;
			AUTO_INCREMENT_ID1 = 1;
			int slice = sliceNo +1;
			
			Linknew1_uuid.addAll(PID);
			PID.clear();
			PID.addAll(Linknew1_uuid);
			

			Collections.sort(PID);
			for (int x = 0; x < PID.size(); x++) {
				VerticesUUID Vertice = new VerticesUUID(x+1,PID.get(x));
				Vertice.setNumberlist(x+1);
				Vertice.setUUID(PID.get(x));
				
				uniquevertices_uuid.add(Vertice);
			}
			

			for (int x = 0; x < uniquevertices_uuid.size(); x++) {
				for (int z = 0; z < Linklist.size(); z++) {

					if ( uniquevertices_uuid.get(x).getUUID().equals(Linklist.get(z).getStartVertex())) {

						Linklist.get(z).setStartVertex(uniquevertices_uuid.get(x).getNumberlist().toString());
					}

				}
			}
			for (int x = 0; x < uniquevertices_uuid.size(); x++) {
				for (int z = 0; z < Linklist.size(); z++) {
					if (uniquevertices_uuid.get(x).getUUID().equals(Linklist.get(z).getEndVertex())) {

						Linklist.get(z).setEndVertex(uniquevertices_uuid.get(x).getNumberlist().toString());
					}
				}
			}

			try {

				PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\Changes Network UUID\\Changes_uuid50_" + slice + ".net", true)); //change file name as needed
				writer.println("*Vertices " + PID.size());

				for (int ctr = 0; ctr < PID.size(); ctr++) {

					writer.printf("%s \"%s\" \n", AUTO_INCREMENT_ID++, PID.get(ctr));

				}

				writer.println("\n*Edges");
				for (int ctr = 0; ctr < Linklist.size(); ctr++) {
					writer.println(Linklist.get(ctr).getStartVertex() + " " + Linklist.get(ctr).getEndVertex() + " "
							+ Linklist.get(ctr).getWeight());

				}

				writer.close();
				System.out.println("done");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				System.out.println(dtf.format(now)); // 2016/11/16 12:08:43

			} catch (IOException E) {

			}
		}

}
