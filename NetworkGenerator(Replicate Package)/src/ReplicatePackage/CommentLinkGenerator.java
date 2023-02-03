package ReplicatePackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CommentLinkGenerator {
	
	  private static final String COMMA_DELIMITER = ",";
	  
      private static final String NEW_LINE_SEPARATOR = "\n";
      
     
  				



	public static ArrayList<Integer> getPID(int sliceNo) {

		ArrayList<Integer> PID = new ArrayList<>();
		PID.clear();
		int slice = sliceNo;

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM tickets_openstack.common_id_50 where ts = "+slice+";"); //Gets common PID from database
			while (rs.next()) {

				PID.add(rs.getInt("Vertices"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return PID;

	}
	
	public static ArrayList<String> getUUPID_comments(ArrayList list, int sliceNo) { // Return String Used for CSV creation

		ArrayList<String> PID = new ArrayList<>();
		PID.clear();
		

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

			"Select distinct(tickets_openstack.comments.submitted_by) as submitted_by, tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.comments right join tickets_openstack.piduuid_alldb on tickets_openstack.comments.submitted_by=tickets_openstack.piduuid_alldb.people_id where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '"
			+list.get(sliceNo)+"' order by submitted_by;");

			while (rs.next()) {

				PID.add(rs.getString("uuid"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return PID;

	}
	
	public static ArrayList<Integer> getUUPID_comments_2(ArrayList list, int sliceNo) { // Return INTEGER Used for NWFiles creation

		ArrayList<Integer> PID = new ArrayList<>();
		PID.clear();
		

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

			"Select distinct(tickets_openstack.comments.submitted_by) as submitted_by, tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.comments right join tickets_openstack.piduuid_alldb on tickets_openstack.comments.submitted_by=tickets_openstack.piduuid_alldb.people_id where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '"
			+list.get(sliceNo)+"' order by submitted_by;");

			while (rs.next()) {

				PID.add(rs.getInt("submitted_by"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return PID;

	}

	public static ArrayList<Link> GenerateCommentLink(ArrayList list, int sliceNo, ArrayList<Integer> PIDx) {
		Set<Integer> s1 = new LinkedHashSet<Integer>();
		s1.clear();
		Set<Integer> s2 = new LinkedHashSet<Integer>();
		s2.clear();
		Set<Integer> s3 = new LinkedHashSet<Integer>();
		s3.clear();
		Set<Integer> pid = new LinkedHashSet<Integer>();
		pid.clear();
		Map<Integer, Set<Integer>> map = new LinkedHashMap<Integer, Set<Integer>>();
		map.clear();
		Set<Integer> pidset = new HashSet<>();
		pidset.clear();
		ArrayList<Integer> PID = new ArrayList<>();
		PID.clear();

		ArrayList<Link> Linklist = new ArrayList<>();
		Linklist.clear();

		int sv = 0;
		int ev = 0;
		int v = 0;
		int z = 0;

		Link link = null;

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

					"Select distinct(tickets_openstack.comments.submitted_by) as submitted_by,issue_id FROM tickets_openstack.comments right join tickets_openstack.set_p on tickets_openstack.comments.submitted_by=tickets_openstack.set_p.PID where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '"
							+ list.get(sliceNo) + "' order by submitted_by;");

			while (rs.next()) {

				if (!map.containsKey(rs.getInt("submitted_by")))
					map.put(rs.getInt("submitted_by"), new HashSet<Integer>());
				map.get(rs.getInt("submitted_by")).add(rs.getInt("issue_id"));

			}
			
			

			System.out.println("\nPID size:   " + PIDx.size() + "\n");

			int n = 0;
			int x;

			for (x = 0; x < PIDx.size(); x++) {

				sv = PIDx.get(x);

				s1.clear();
				s1.addAll(map.get(sv));

				for (v = 0; v < (PIDx.size() - 1) - z; v++) { // Making pairs

					sv = PIDx.get(x);
					ev = PIDx.get(v + z + 1);

					s3.addAll(s1);

					s2.addAll(map.get(ev));

					n = s1.size();

					s3.retainAll(s2);

					link = new Link(sv, ev, s3.size());

					if (s3.size() > 0) {

						link.setStartVertex(sv);
						link.setEndVertex(ev);
						link.setWeight(s3.size());

						Linklist.add(link);
					}

					s2.clear();
					s3.clear();

				}

				z++;
				System.out.println("loop number : " + z);
			}

			System.out.println(Linklist.toString());
			System.out.println(Linklist.size());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		int slice = sliceNo + 1;
		try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\CommentsLinkList.csv", true));
			 
	    	
			  for(int xxx=0; xxx<Linklist.size() ;xxx++)
			  {
				  writer.append(String.valueOf(slice));
					writer.append(COMMA_DELIMITER);
					
					
				writer.append(String.valueOf(Linklist.get(xxx).getStartVertex()));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(Linklist.get(xxx).getEndVertex()));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(Linklist.get(xxx).getWeight()));
				writer.append(COMMA_DELIMITER);
			
				writer.append(NEW_LINE_SEPARATOR);
			  }
			  
			

	  writer.close();
		System.out.println("done");
	
}
		 catch (IOException E) {
	  		
	  		}
		


		return Linklist;

	}
	
	public static ArrayList<Uuid> SetPidtoUUID(ArrayList list, int sliceNo, ArrayList<String> PIDx) {
		
		ArrayList<Uuid> UuidList = new ArrayList<>();
		
		Uuid uuid = null;
		
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
			
		            "Select distinct(tickets_openstack.comments.submitted_by) as submitted_by,issue_id, tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.comments right join tickets_openstack.piduuid_alldb on tickets_openstack.comments.submitted_by=tickets_openstack.piduuid_alldb.people_id where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '"
		            + list.get(sliceNo) + "' order by submitted_by;");


			while (rs.next()) {
				int pid = 0;
			    String uid = "";

				
				uuid = new Uuid(pid, uid);
				
				uuid.setPID(pid);
				
				
				uuid.setUUID(uid);
				

				UuidList.add(uuid);
				
				

			}
			
			
			
		}
			
			
				catch (SQLException e) {
					e.printStackTrace();
				}
			
			
				
			return UuidList;
			
			
		
		
	}
	
	public static ArrayList<LinkUUID> GenerateCommentLink_uuid(ArrayList list, int sliceNo, ArrayList<String> PIDx) {
		Set<Integer> s1 = new LinkedHashSet<Integer>();
		s1.clear();
		Set<Integer> s2 = new LinkedHashSet<Integer>();
		s2.clear();
		Set<Integer> s3 = new LinkedHashSet<Integer>();
		s3.clear();
		Set<Integer> pid = new LinkedHashSet<Integer>();
		pid.clear();
		Map<String, Set<Integer>> map = new LinkedHashMap<String, Set<Integer>>();
		map.clear();
		Set<Integer> pidset = new HashSet<>();
		pidset.clear();
		ArrayList<Integer> PID = new ArrayList<>();
		PID.clear();

		ArrayList<LinkUUID> Linklist = new ArrayList<>();
		Linklist.clear();

		String sv ;
		String ev;
		int v = 0;
		int z = 0;

		LinkUUID link = null;

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
			
		            "Select distinct(tickets_openstack.comments.submitted_by) as submitted_by,issue_id, tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.comments right join tickets_openstack.piduuid_alldb on tickets_openstack.comments.submitted_by=tickets_openstack.piduuid_alldb.people_id where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '"
		            + list.get(sliceNo) + "' order by submitted_by;");


			while (rs.next()) {

				if (!map.containsKey(rs.getString("uuid")))
					map.put(rs.getString("uuid"), new HashSet<Integer>());
				map.get(rs.getString("uuid")).add(rs.getInt("issue_id"));

			}
			
			

			System.out.println("\nPID size:   " + PIDx.size() + "\n");

			int n = 0;
			int x;

			for (x = 0; x < PIDx.size(); x++) {

				sv = PIDx.get(x);

				s1.clear();
				s1.addAll(map.get(sv));

				for (v = 0; v < (PIDx.size() - 1) - z; v++) { // Making pairs

					sv = PIDx.get(x);
					ev = PIDx.get(v + z + 1);

					s3.addAll(s1);

					s2.addAll(map.get(ev));

					n = s1.size();

					s3.retainAll(s2);

					link = new LinkUUID(sv, ev, s3.size());

					if (s3.size() > 0) {

						link.setStartVertex(sv);
						link.setEndVertex(ev);
						link.setWeight(s3.size());

						Linklist.add(link);
					}

					s2.clear();
					s3.clear();

				}

				z++;
				System.out.println("loop number : " + z);
			}

			System.out.println(Linklist.toString());
			System.out.println(Linklist.size());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		int slice = sliceNo + 1;
		/*try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\CommentsLinkList_uuid_1.csv", true));
			 
	    	
			  for(int xxx=0; xxx<Linklist.size() ;xxx++)
			  {
				  writer.append(String.valueOf(slice));
					writer.append(COMMA_DELIMITER);
					
					
				writer.append(String.valueOf(Linklist.get(xxx).getStartVertex()));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(Linklist.get(xxx).getEndVertex()));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(Linklist.get(xxx).getWeight()));
				writer.append(COMMA_DELIMITER);
			
				writer.append(NEW_LINE_SEPARATOR);
			  }
			  
			

	  writer.close();
		System.out.println("done");
	
}
		 catch (IOException E) {
	  		
	  		}*/
		


		return Linklist;

	}



public static ArrayList<LinkUUID> GenerateCommentLink_uuid_reviews() {
	Set<Integer> s1 = new LinkedHashSet<Integer>();
	s1.clear();
	Set<Integer> s2 = new LinkedHashSet<Integer>();
	s2.clear();
	Set<Integer> s3 = new LinkedHashSet<Integer>();
	s3.clear();
	Set<Integer> pid = new LinkedHashSet<Integer>();
	pid.clear();
	Map<String, Set<Integer>> map = new LinkedHashMap<String, Set<Integer>>();
	map.clear();
	Set<Integer> pidset = new HashSet<>();
	pidset.clear();
	ArrayList<Integer> PID = new ArrayList<>();
	PID.clear();

	ArrayList<LinkUUID> Linklist = new ArrayList<>();
	Linklist.clear();
	
	Set<Integer> uPid = new LinkedHashSet<Integer>();
	uPid.clear();
	Set<String> uPidz = new LinkedHashSet<String>();
	uPidz.clear();
	 ArrayList<Integer> PIDCommon = new ArrayList<>();
	 PIDCommon.clear();
	 ArrayList<String> PIDx = new ArrayList<>();
	 PIDx.clear();

	String sv ;
	String ev;
	int v = 0;
	int z = 0;

	LinkUUID link = null;
	try {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
				"password");

		Statement stmt = con.createStatement();

	
		ResultSet rs = stmt.executeQuery(
				"SELECT distinct(reviews_openstack.piduuid_alldb.uuid) FROM reviews_openstack.piduuid_alldb;");
		
		while (rs.next()) {
			
			/*if (!uupid.containsKey(rs.getString("uuid")))
				uupid.put(rs.getString("uuid"), new HashSet<Integer>());
			uupid.get(rs.getInt("uuid")).add(rs.getInt("people_id"));*/
		
			//uPid.add(rs.getInt("people_id"));
			
			uPidz.add(rs.getString("uuid"));
			

}

	}
	
		
	
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	PIDx.addAll(uPidz);

	try {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
				"password");

		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(
		
	            "Select distinct(reviews_openstack.comments.submitted_by) as submitted_by,issue_id, reviews_openstack.piduuid_alldb.uuid FROM reviews_openstack.comments right join reviews_openstack.piduuid_alldb on reviews_openstack.comments.submitted_by=reviews_openstack.piduuid_alldb.people_id order by submitted_by;");


		while (rs.next()) {

			if (!map.containsKey(rs.getString("uuid")))
				map.put(rs.getString("uuid"), new HashSet<Integer>());
			map.get(rs.getString("uuid")).add(rs.getInt("issue_id"));

		}
		
		

		System.out.println("\nPID size:   " + PIDx.size() + "\n");

		int n = 0;
		int x;

		for (x = 0; x < PIDx.size(); x++) {

			sv = PIDx.get(x);

			s1.clear();
			s1.addAll(map.get(sv));

			for (v = 0; v < (PIDx.size() - 1) - z; v++) { // Making pairs

				sv = PIDx.get(x);
				ev = PIDx.get(v + z + 1);

				s3.addAll(s1);

				s2.addAll(map.get(ev));

				n = s1.size();

				s3.retainAll(s2);

				link = new LinkUUID(sv, ev, s3.size());

				if (s3.size() > 0) {

					link.setStartVertex(sv);
					link.setEndVertex(ev);
					link.setWeight(s3.size());

					Linklist.add(link);
				}

				s2.clear();
				s3.clear();

			}

			z++;
			System.out.println("loop number : " + z);
		}

		System.out.println(Linklist.toString());
		System.out.println(Linklist.size());
	}

	catch (SQLException e) {
		e.printStackTrace();
	}
	
	/*try {
		  PrintWriter writer = new PrintWriter(new FileWriter(
					"D:\\Replicate package\\CommentsLinkList_uuid_Reviews.csv", true));
		 
    	
		  for(int xxx=0; xxx<Linklist.size() ;xxx++)
		  {
			  writer.append(String.valueOf(50));
				writer.append(COMMA_DELIMITER);
				
				
			writer.append(String.valueOf(Linklist.get(xxx).getStartVertex()));
			writer.append(COMMA_DELIMITER);
			
			writer.append(String.valueOf(Linklist.get(xxx).getEndVertex()));
			writer.append(COMMA_DELIMITER);
			
			writer.append(String.valueOf(Linklist.get(xxx).getWeight()));
			writer.append(COMMA_DELIMITER);
		
			writer.append(NEW_LINE_SEPARATOR);
		  }
		  
		

  writer.close();
	

}
	 catch (IOException E) {
  		
  		}*/
	
	 System.out.println("done");

	return Linklist;

}

}
