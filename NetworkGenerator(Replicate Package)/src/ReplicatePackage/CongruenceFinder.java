package ReplicatePackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CongruenceFinder {

	private static final String COMMA_DELIMITER = ",";

	private static final String NEW_LINE_SEPARATOR = "\n";
	
public static void findSimilarity_uuid_reviews(){
		

		
		// map is changes
				// map1 is comments
				// mapcomment to find number of comments
				Map<String, Set<Integer>> mapcomment = new LinkedHashMap<String, Set<Integer>>();
				mapcomment.clear();
				Set<Integer> s1comment = new LinkedHashSet<Integer>();
				s1comment.clear();

				Map<String, Set<Integer>> mapchanges = new LinkedHashMap<String, Set<Integer>>();
				mapchanges.clear();
				Set<Integer> s1changes = new LinkedHashSet<Integer>();
				s1changes.clear();

				Map<String, Set<Integer>> mapsubmitted = new LinkedHashMap<String, Set<Integer>>();
				mapsubmitted.clear();
				Set<Integer> s1submitted = new LinkedHashSet<Integer>();
				s1submitted.clear();
				
				Map<String, Set<Integer>> uupid = new LinkedHashMap<String, Set<Integer>>();
				
				Map<String, Set<Integer>> mapassigned = new LinkedHashMap<String, Set<Integer>>();
				mapassigned.clear();
				Set<Integer> s1assigned = new LinkedHashSet<Integer>();
				s1assigned.clear();

				Map<String, Set<String>> map = new LinkedHashMap<String, Set<String>>();
				map.clear();
				Map<String, Set<String>> map1 = new LinkedHashMap<String, Set<String>>();
				map1.clear();
				Set<String> s1 = new LinkedHashSet<String>();
				s1.clear();
				Set<String> s2 = new LinkedHashSet<String>();
				s2.clear();
				Set<String> s3 = new LinkedHashSet<String>();
				s3.clear();
				Set<Integer> uPid = new LinkedHashSet<Integer>();
				uPid.clear();
				Set<String> uPidz = new LinkedHashSet<String>();
				uPidz.clear();
				 ArrayList<Integer> PIDCommon = new ArrayList<>();
				 PIDCommon.clear();
				 ArrayList<String> PIDCommonz = new ArrayList<>();
				 PIDCommonz.clear();

				int comments = 0;
				int changes = 0;
				int watched = 0;
				int submitted = 0;
				int assigned = 0;
				
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
				//PIDCommon.addAll(uPid);
				PIDCommonz.addAll(uPidz);

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(

							"SELECT reviews_openstack.issues.id,reviews_openstack.issues.submitted_by,reviews_openstack.piduuid_alldb.uuid FROM reviews_openstack.issues right join reviews_openstack.piduuid_alldb on reviews_openstack.issues.submitted_by=reviews_openstack.piduuid_alldb.people_id;");

					while (rs.next()) {

						if (!mapsubmitted.containsKey(rs.getString("uuid")))
							mapsubmitted.put(rs.getString("uuid"), new HashSet<Integer>());
						mapsubmitted.get(rs.getString("uuid")).add(rs.getInt("id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(

							"SELECT reviews_openstack.issues.id,reviews_openstack.issues.assigned_to,reviews_openstack.piduuid_alldb.uuid FROM reviews_openstack.issues right join reviews_openstack.piduuid_alldb on reviews_openstack.issues.assigned_to=reviews_openstack.piduuid_alldb.people_id;");

					while (rs.next()) {

			
						
						if (!mapassigned.containsKey(rs.getString("uuid")))
							mapassigned.put(rs.getString("uuid"), new HashSet<Integer>());
						mapassigned.get(rs.getString("uuid")).add(rs.getInt("id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}


				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(

							"Select distinct(reviews_openstack.comments.submitted_by) as submitted_by,issue_id, reviews_openstack.piduuid_alldb.uuid FROM reviews_openstack.comments right join reviews_openstack.piduuid_alldb on reviews_openstack.comments.submitted_by=reviews_openstack.piduuid_alldb.people_id where reviews_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '2020-04-14 16:30:35.0' order by submitted_by;");

					while (rs.next()) {

						if (!mapcomment.containsKey(rs.getString("uuid")))
							mapcomment.put(rs.getString("uuid"), new HashSet<Integer>());
						mapcomment.get(rs.getString("uuid")).add(rs.getInt("issue_id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(
				
					"SELECT distinct(reviews_openstack.changes.changed_by) as changed_by,reviews_openstack.changes.issue_id, reviews_openstack.piduuid_alldb.uuid FROM reviews_openstack.changes right join reviews_openstack.piduuid_alldb on reviews_openstack.changes.changed_by= reviews_openstack.piduuid_alldb.people_id where reviews_openstack.changes.changed_on between '2010-04-14 16:30:35.0' and '2020-04-14 16:30:35.0' order by changed_by;");


					while (rs.next()) {

						if (!mapchanges.containsKey(rs.getString("uuid")))
							mapchanges.put(rs.getString("uuid"), new HashSet<Integer>());
						mapchanges.get(rs.getString("uuid")).add(rs.getInt("issue_id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM reviews_openstack.changeslinklist_uuid_reviews Where ts = 50;");

					while (rs.next()) {

						if (!map.containsKey(rs.getString("start")))
							map.put(rs.getString("start"), new HashSet<String>());
						map.get(rs.getString("start")).add(rs.getString("end"));

						if (!map.containsKey(rs.getString("end")))
							map.put(rs.getString("end"), new HashSet<String>());
						map.get(rs.getString("end")).add(rs.getString("start"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviews_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM reviews_openstack.commentslinklist_uuid_reviews Where ts = 50;");

					while (rs.next()) {

						if (!map1.containsKey(rs.getString("start")))
							map1.put(rs.getString("start"), new HashSet<String>());
						map1.get(rs.getString("start")).add(rs.getString("end"));

						if (!map1.containsKey(rs.getString("end")))
							map1.put(rs.getString("end"), new HashSet<String>());
						map1.get(rs.getString("end")).add(rs.getString("start"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				float ss = 0;
				float s1intersect = 0;
				float pchanges = 0;

				try {
					PrintWriter writer = new PrintWriter(
							new FileWriter("D:\\Replicate package\\Similarity Score\\Similarity_Score_uuid_Reviews.csv", true));

					for (int x = 0; x < PIDCommonz.size(); x++) {

						s1.clear();
						s2.clear();
						s3.clear();
						ss = 0;
						s1intersect = 0;
						pchanges = 0;

						s1comment.clear();
						if(mapcomment.get(PIDCommonz.get(x))!= null){
						s1comment.addAll(mapcomment.get(PIDCommonz.get(x)));
						if (map.get(PIDCommonz.get(x)) != null) { // changes
							s1.addAll(map.get(PIDCommonz.get(x)));
							s3.addAll(map.get(PIDCommonz.get(x)));

						}
						}

						s1changes.clear();
						if(mapchanges.get(PIDCommonz.get(x))!= null){
						s1changes.addAll(mapchanges.get(PIDCommonz.get(x)));
						if (map1.get(PIDCommonz.get(x)) != null) { // comments
							s2.addAll(map1.get(PIDCommonz.get(x)));

						}
						}
						
						s1submitted.clear();
						if(mapsubmitted.get(PIDCommonz.get(x))!= null){
						s1submitted.addAll(mapsubmitted.get(PIDCommonz.get(x)));
						}
						
						s1assigned.clear();
						if(mapassigned.get(PIDCommonz.get(x))!= null){
						s1assigned.addAll(mapassigned.get(PIDCommonz.get(x)));
						}

						comments = s1comment.size();
						changes = s1changes.size();
						submitted = s1submitted.size();
						assigned = s1assigned.size();

						System.out.println(map.get(PIDCommonz.get(x)));
						System.out.println(map1.get(PIDCommonz.get(x)));

					
						
					

					

						s1.retainAll(s2);
						s1intersect = s1.size();
						pchanges = s3.size(); // changes

						if (pchanges > 0) {
							ss = s1intersect / pchanges;
						} else if (pchanges <= 0) {
							ss = 0;
						}

						writer.append(String.valueOf(PIDCommonz.get(x)));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(ss));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(s1intersect));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(comments));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(changes));
						writer.append(COMMA_DELIMITER);
						
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(submitted));
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(assigned));
						writer.append(COMMA_DELIMITER);

						writer.append(NEW_LINE_SEPARATOR);

						System.out.println("\nSS : " + ss);

					}

					writer.close();
					System.out.println("done");

				}

				catch (IOException E) {
				}
		
	}
	
	public static void findSimilarity_uuid_tickets(){
		

		
		// map is changes
				// map1 is comments
				// mapcomment to find number of comments
				Map<String, Set<Integer>> mapcomment = new LinkedHashMap<String, Set<Integer>>();
				mapcomment.clear();
				Set<Integer> s1comment = new LinkedHashSet<Integer>();
				s1comment.clear();

				Map<String, Set<Integer>> mapchanges = new LinkedHashMap<String, Set<Integer>>();
				mapchanges.clear();
				Set<Integer> s1changes = new LinkedHashSet<Integer>();
				s1changes.clear();

				Map<String, Set<Integer>> mapsubmitted = new LinkedHashMap<String, Set<Integer>>();
				mapsubmitted.clear();
				Set<Integer> s1submitted = new LinkedHashSet<Integer>();
				s1submitted.clear();
				
				Map<String, Set<Integer>> uupid = new LinkedHashMap<String, Set<Integer>>();
				
				Map<String, Set<Integer>> mapassigned = new LinkedHashMap<String, Set<Integer>>();
				mapassigned.clear();
				Set<Integer> s1assigned = new LinkedHashSet<Integer>();
				s1assigned.clear();

				Map<String, Set<String>> map = new LinkedHashMap<String, Set<String>>();
				map.clear();
				Map<String, Set<String>> map1 = new LinkedHashMap<String, Set<String>>();
				map1.clear();
				Set<String> s1 = new LinkedHashSet<String>();
				s1.clear();
				Set<String> s2 = new LinkedHashSet<String>();
				s2.clear();
				Set<String> s3 = new LinkedHashSet<String>();
				s3.clear();
				Set<Integer> uPid = new LinkedHashSet<Integer>();
				uPid.clear();
				Set<String> uPidz = new LinkedHashSet<String>();
				uPidz.clear();
				 ArrayList<Integer> PIDCommon = new ArrayList<>();
				 PIDCommon.clear();
				 ArrayList<String> PIDCommonz = new ArrayList<>();
				 PIDCommonz.clear();

				int comments = 0;
				int changes = 0;
				int watched = 0;
				int submitted = 0;
				int assigned = 0;
				
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

				
					ResultSet rs = stmt.executeQuery(
							"SELECT distinct(tickets_openstack.piduuid_alldb.uuid) FROM tickets_openstack.piduuid_alldb;");
					
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
				//PIDCommon.addAll(uPid);
				PIDCommonz.addAll(uPidz);

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(

							"SELECT tickets_openstack.issues.id,tickets_openstack.issues.submitted_by,tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.issues right join tickets_openstack.piduuid_alldb on tickets_openstack.issues.submitted_by=tickets_openstack.piduuid_alldb.people_id;");

					while (rs.next()) {

						if (!mapsubmitted.containsKey(rs.getString("uuid")))
							mapsubmitted.put(rs.getString("uuid"), new HashSet<Integer>());
						mapsubmitted.get(rs.getString("uuid")).add(rs.getInt("id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(

							"SELECT tickets_openstack.issues.id,tickets_openstack.issues.assigned_to,tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.issues right join tickets_openstack.piduuid_alldb on tickets_openstack.issues.assigned_to=tickets_openstack.piduuid_alldb.people_id;");

					while (rs.next()) {

			
						
						if (!mapassigned.containsKey(rs.getString("uuid")))
							mapassigned.put(rs.getString("uuid"), new HashSet<Integer>());
						mapassigned.get(rs.getString("uuid")).add(rs.getInt("id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}


				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(

							"Select distinct(tickets_openstack.comments.submitted_by) as submitted_by,issue_id, tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.comments right join tickets_openstack.piduuid_alldb on tickets_openstack.comments.submitted_by=tickets_openstack.piduuid_alldb.people_id where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '2020-04-14 16:30:35.0' order by submitted_by;");

					while (rs.next()) {

						if (!mapcomment.containsKey(rs.getString("uuid")))
							mapcomment.put(rs.getString("uuid"), new HashSet<Integer>());
						mapcomment.get(rs.getString("uuid")).add(rs.getInt("issue_id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery(
				
					"SELECT distinct(tickets_openstack.changes.changed_by) as changed_by,tickets_openstack.changes.issue_id, tickets_openstack.piduuid_alldb.uuid FROM tickets_openstack.changes right join tickets_openstack.piduuid_alldb on tickets_openstack.changes.changed_by= tickets_openstack.piduuid_alldb.people_id where tickets_openstack.changes.changed_on between '2010-04-14 16:30:35.0' and '2020-04-14 16:30:35.0' order by changed_by;");


					while (rs.next()) {

						if (!mapchanges.containsKey(rs.getString("uuid")))
							mapchanges.put(rs.getString("uuid"), new HashSet<Integer>());
						mapchanges.get(rs.getString("uuid")).add(rs.getInt("issue_id"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM tickets_openstack.changeslinklist_uuid Where ts = 50;");

					while (rs.next()) {

						if (!map.containsKey(rs.getString("start")))
							map.put(rs.getString("start"), new HashSet<String>());
						map.get(rs.getString("start")).add(rs.getString("end"));

						if (!map.containsKey(rs.getString("end")))
							map.put(rs.getString("end"), new HashSet<String>());
						map.get(rs.getString("end")).add(rs.getString("start"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
							"password");

					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM tickets_openstack.commentslinklist_uuid Where ts = 50;");

					while (rs.next()) {

						if (!map1.containsKey(rs.getString("start")))
							map1.put(rs.getString("start"), new HashSet<String>());
						map1.get(rs.getString("start")).add(rs.getString("end"));

						if (!map1.containsKey(rs.getString("end")))
							map1.put(rs.getString("end"), new HashSet<String>());
						map1.get(rs.getString("end")).add(rs.getString("start"));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				float ss = 0;
				float s1intersect = 0;
				float pchanges = 0;

				try {
					PrintWriter writer = new PrintWriter(
							new FileWriter("D:\\Replicate package\\Similarity Score\\Similarity_Score_uuid.csv", true));

					for (int x = 0; x < PIDCommonz.size(); x++) {

						s1.clear();
						s2.clear();
						s3.clear();
						ss = 0;
						s1intersect = 0;
						pchanges = 0;

						s1comment.clear();
						s1comment.addAll(mapcomment.get(PIDCommonz.get(x)));

						s1changes.clear();
						s1changes.addAll(mapchanges.get(PIDCommonz.get(x)));
						
						s1submitted.clear();
						if(mapsubmitted.get(PIDCommonz.get(x))!= null){
						s1submitted.addAll(mapsubmitted.get(PIDCommonz.get(x)));
						}
						
						s1assigned.clear();
						if(mapassigned.get(PIDCommonz.get(x))!= null){
						s1assigned.addAll(mapassigned.get(PIDCommonz.get(x)));
						}

						comments = s1comment.size();
						changes = s1changes.size();
						submitted = s1submitted.size();
						assigned = s1assigned.size();

						System.out.println(map.get(PIDCommonz.get(x)));
						System.out.println(map1.get(PIDCommonz.get(x)));

						if (map.get(PIDCommonz.get(x)) != null) { // changes
							s1.addAll(map.get(PIDCommonz.get(x)));
							s3.addAll(map.get(PIDCommonz.get(x)));

						}

						if (map1.get(PIDCommonz.get(x)) != null) { // comments
							s2.addAll(map1.get(PIDCommonz.get(x)));

						}

						s1.retainAll(s2);
						s1intersect = s1.size();
						pchanges = s3.size(); // changes

						if (pchanges > 0) {
							ss = s1intersect / pchanges;
						} else if (pchanges <= 0) {
							ss = 0;
						}

						writer.append(String.valueOf(PIDCommonz.get(x)));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(ss));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(s1intersect));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(comments));
						writer.append(COMMA_DELIMITER);

						writer.append(String.valueOf(changes));
						writer.append(COMMA_DELIMITER);
						
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(submitted));
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(assigned));
						writer.append(COMMA_DELIMITER);

						writer.append(NEW_LINE_SEPARATOR);

						System.out.println("\nSS : " + ss);

					}

					writer.close();
					System.out.println("done");

				}

				catch (IOException E) {
				}
		
	}

	public static void findSimilarity(ArrayList<Integer> PIDcommon) {

		// map is changes
		// map1 is comments
		// mapcomment to find number of comments
		Map<Integer, Set<Integer>> mapcomment = new LinkedHashMap<Integer, Set<Integer>>();
		mapcomment.clear();
		Set<Integer> s1comment = new LinkedHashSet<Integer>();
		s1comment.clear();

		Map<Integer, Set<Integer>> mapchanges = new LinkedHashMap<Integer, Set<Integer>>();
		mapchanges.clear();
		Set<Integer> s1changes = new LinkedHashSet<Integer>();
		s1changes.clear();

		Map<Integer, Set<Integer>> mapsubmitted = new LinkedHashMap<Integer, Set<Integer>>();
		mapsubmitted.clear();
		Set<Integer> s1submitted = new LinkedHashSet<Integer>();
		s1submitted.clear();
		
		Map<Integer, Set<Integer>> mapassigned = new LinkedHashMap<Integer, Set<Integer>>();
		mapassigned.clear();
		Set<Integer> s1assigned = new LinkedHashSet<Integer>();
		s1assigned.clear();

		Map<Integer, Set<Integer>> map = new LinkedHashMap<Integer, Set<Integer>>();
		map.clear();
		Map<Integer, Set<Integer>> map1 = new LinkedHashMap<Integer, Set<Integer>>();
		map1.clear();
		Set<Integer> s1 = new LinkedHashSet<Integer>();
		s1.clear();
		Set<Integer> s2 = new LinkedHashSet<Integer>();
		s2.clear();
		Set<Integer> s3 = new LinkedHashSet<Integer>();
		s3.clear();

		int comments = 0;
		int changes = 0;
		int watched = 0;
		int submitted = 0;
		int assigned = 0;

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

					"SELECT tickets_openstack.issues.id,tickets_openstack.issues.submitted_by FROM tickets_openstack.issues right join tickets_openstack.set_p on tickets_openstack.issues.submitted_by=tickets_openstack.set_p.PID;");

			while (rs.next()) {

				if (!mapsubmitted.containsKey(rs.getInt("submitted_by")))
					mapsubmitted.put(rs.getInt("submitted_by"), new HashSet<Integer>());
				mapsubmitted.get(rs.getInt("submitted_by")).add(rs.getInt("id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

					"SELECT tickets_openstack.issues.id,tickets_openstack.issues.assigned_to FROM tickets_openstack.issues right join tickets_openstack.set_p on tickets_openstack.issues.assigned_to=tickets_openstack.set_p.PID;");

			while (rs.next()) {

	
				
				if (!mapassigned.containsKey(rs.getInt("assigned_to")))
					mapassigned.put(rs.getInt("assigned_to"), new HashSet<Integer>());
				mapassigned.get(rs.getInt("assigned_to")).add(rs.getInt("id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

					"Select distinct(tickets_openstack.comments.submitted_by) as submitted_by,issue_id FROM tickets_openstack.comments right join tickets_openstack.set_p on tickets_openstack.comments.submitted_by=tickets_openstack.set_p.PID where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '2020-04-14 16:30:35.0' order by submitted_by;");

			while (rs.next()) {

				if (!mapcomment.containsKey(rs.getInt("submitted_by")))
					mapcomment.put(rs.getInt("submitted_by"), new HashSet<Integer>());
				mapcomment.get(rs.getInt("submitted_by")).add(rs.getInt("issue_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(

					"SELECT distinct(tickets_openstack.changes.changed_by) as changed_by,tickets_openstack.changes.issue_id FROM tickets_openstack.changes right join tickets_openstack.set_p on tickets_openstack.changes.changed_by= tickets_openstack.set_p.PID where tickets_openstack.changes.changed_on between '2010-04-14 16:30:35.0' and "
							+ "'2020-04-14 16:30:35.0' order by changed_by");

			while (rs.next()) {

				if (!mapchanges.containsKey(rs.getInt("changed_by")))
					mapchanges.put(rs.getInt("changed_by"), new HashSet<Integer>());
				mapchanges.get(rs.getInt("changed_by")).add(rs.getInt("issue_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM tickets_openstack.linklistchanges Where ts = 50;");

			while (rs.next()) {

				if (!map.containsKey(rs.getInt("start")))
					map.put(rs.getInt("start"), new HashSet<Integer>());
				map.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map.containsKey(rs.getInt("end")))
					map.put(rs.getInt("end"), new HashSet<Integer>());
				map.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM tickets_openstack.linklistcomments Where ts = 50;");

			while (rs.next()) {

				if (!map1.containsKey(rs.getInt("start")))
					map1.put(rs.getInt("start"), new HashSet<Integer>());
				map1.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map1.containsKey(rs.getInt("end")))
					map1.put(rs.getInt("end"), new HashSet<Integer>());
				map1.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		float ss = 0;
		float s1intersect = 0;
		float pchanges = 0;

		try {
			PrintWriter writer = new PrintWriter(
					new FileWriter("D:\\Replicate package\\Similarity Score\\Similarity_Score_Test1.csv", true));

			for (int x = 0; x < PIDcommon.size(); x++) {

				s1.clear();
				s2.clear();
				s3.clear();
				ss = 0;
				s1intersect = 0;
				pchanges = 0;

				s1comment.clear();
				s1comment.addAll(mapcomment.get(PIDcommon.get(x)));

				s1changes.clear();
				s1changes.addAll(mapchanges.get(PIDcommon.get(x)));
				
				s1submitted.clear();
				if(mapsubmitted.get(PIDcommon.get(x))!= null){
				s1submitted.addAll(mapsubmitted.get(PIDcommon.get(x)));
				}
				
				s1assigned.clear();
				if(mapassigned.get(PIDcommon.get(x))!= null){
				s1assigned.addAll(mapassigned.get(PIDcommon.get(x)));
				}

				comments = s1comment.size();
				changes = s1changes.size();
				submitted = s1submitted.size();
				assigned = s1assigned.size();

				System.out.println(map.get(PIDcommon.get(x)));
				System.out.println(map1.get(PIDcommon.get(x)));

				if (map.get(PIDcommon.get(x)) != null) { // changes
					s1.addAll(map.get(PIDcommon.get(x)));
					s3.addAll(map.get(PIDcommon.get(x)));

				}

				if (map1.get(PIDcommon.get(x)) != null) { // comments
					s2.addAll(map1.get(PIDcommon.get(x)));

				}

				s1.retainAll(s2);
				s1intersect = s1.size();
				pchanges = s3.size(); // changes

				if (pchanges > 0) {
					ss = s1intersect / pchanges;
				} else if (pchanges <= 0) {
					ss = 0;
				}

				writer.append(String.valueOf(PIDcommon.get(x)));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(ss));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(s1intersect));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(comments));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(changes));
				writer.append(COMMA_DELIMITER);
				
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(submitted));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(assigned));
				writer.append(COMMA_DELIMITER);

				writer.append(NEW_LINE_SEPARATOR);

				System.out.println("\nSS : " + ss);

			}

			writer.close();
			System.out.println("done");

		}

		catch (IOException E) {
		}
	}

	public static void findSimilarity_Average(ArrayList<Integer> PIDcommon, Integer sliceNo) {

		// map is changes
		// map1 is comments

		Map<Integer, Set<Integer>> map = new LinkedHashMap<Integer, Set<Integer>>();
		map.clear();
		Map<Integer, Set<Integer>> map1 = new LinkedHashMap<Integer, Set<Integer>>();
		map1.clear();
		Set<Integer> s1 = new LinkedHashSet<Integer>();
		s1.clear();
		Set<Integer> s2 = new LinkedHashSet<Integer>();
		s2.clear();
		Set<Integer> s3 = new LinkedHashSet<Integer>();
		s3.clear();
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM tickets_openstack.linklistchanges Where ts = " + sliceNo + ";");

			while (rs.next()) {

				if (!map.containsKey(rs.getInt("start")))
					map.put(rs.getInt("start"), new HashSet<Integer>());
				map.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map.containsKey(rs.getInt("end")))
					map.put(rs.getInt("end"), new HashSet<Integer>());
				map.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM tickets_openstack.linklistcomments Where ts = " + sliceNo + ";");

			while (rs.next()) {

				if (!map1.containsKey(rs.getInt("start")))
					map1.put(rs.getInt("start"), new HashSet<Integer>());
				map1.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map1.containsKey(rs.getInt("end")))
					map1.put(rs.getInt("end"), new HashSet<Integer>());
				map1.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int num = 0;
		float ss = 0;
		float sstotal = 0;
		float ssavg = 0;
		float s1intersect = 0;
		float pchanges = 0;
		float totalpeople = 0;

		for (int x = 0; x < PIDcommon.size(); x++) {
			s1.clear();
			s2.clear();
			s3.clear();

			num = PIDcommon.get(x);
			System.out.println(map.get(PIDcommon.get(x)));
			System.out.println(map1.get(PIDcommon.get(x)));

			if (map.get(PIDcommon.get(x)) != null) {
				s1.addAll(map.get(PIDcommon.get(x)));
				s3.addAll(map.get(PIDcommon.get(x)));

			}

			if (map1.get(PIDcommon.get(x)) != null) {
				s2.addAll(map1.get(PIDcommon.get(x)));

			}

			s1.retainAll(s2);
			s1intersect = s1.size();
			pchanges = s3.size();
			if (pchanges > 0) {
				ss = s1intersect / pchanges;
			} else if (pchanges <= 0) {
				ss = 0;
			}

			if (!Double.isNaN(ss)) {
				sstotal = sstotal + ss;
			}

			System.out.println("\nSS : " + ss);
			System.out.println("Total : " + sstotal + "\n\n");
		}

		totalpeople = PIDcommon.size();
		if (!Double.isNaN(sstotal)) {
			ssavg = sstotal / totalpeople;
		}
		// ssavg = sstotal/totalpeople;

		System.out.println("\nTS : " + sliceNo);
		System.out.println("\nNumber of people : " + totalpeople);
		System.out.println("\nSS avg : " + ssavg);

		try {
			PrintWriter writer = new PrintWriter(
					new FileWriter("D:\\Replicate package\\Similarity Score\\Similarity_Score_Average.csv", true));

			if (num > 0) {

				writer.append(String.valueOf(sliceNo));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(PIDcommon.size()));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(ssavg));
				writer.append(COMMA_DELIMITER);

				writer.append(NEW_LINE_SEPARATOR);
			}
			writer.close();
			System.out.println("done");

		}

		catch (IOException E) {
		}
	}

	public static void findSimilarity_Modified(ArrayList<Integer> PIDcommon) {

		// map is changes
		// map1 is comments

		Map<Integer, Set<Integer>> map = new LinkedHashMap<Integer, Set<Integer>>();
		map.clear();
		Map<Integer, Set<Integer>> map1 = new LinkedHashMap<Integer, Set<Integer>>();
		map1.clear();
		Set<Integer> s1 = new LinkedHashSet<Integer>();
		s1.clear();
		Set<Integer> s2 = new LinkedHashSet<Integer>();
		s2.clear();
		Set<Integer> s3 = new LinkedHashSet<Integer>();
		s3.clear();
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM tickets_openstack.linklistchanges Where ts = 50;");

			while (rs.next()) {

				if (!map.containsKey(rs.getInt("start")))
					map.put(rs.getInt("start"), new HashSet<Integer>());
				map.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map.containsKey(rs.getInt("end")))
					map.put(rs.getInt("end"), new HashSet<Integer>());
				map.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM tickets_openstack.linklistcomments Where ts = 50;");

			while (rs.next()) {

				if (!map1.containsKey(rs.getInt("start")))
					map1.put(rs.getInt("start"), new HashSet<Integer>());
				map1.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map1.containsKey(rs.getInt("end")))
					map1.put(rs.getInt("end"), new HashSet<Integer>());
				map1.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		float ss = 0;
		float s1intersect = 0;
		float pcomments = 0;

		try {
			PrintWriter writer = new PrintWriter(
					new FileWriter("D:\\Replicate package\\Similarity Score\\Similarity_Score_modified.csv", true));

			for (int x = 0; x < PIDcommon.size(); x++) {
				s1.clear();
				s2.clear();
				s3.clear();
				ss = 0;
				s1intersect = 0;
				pcomments = 0;

				System.out.println(map.get(PIDcommon.get(x)));
				System.out.println(map1.get(PIDcommon.get(x)));

				if (map.get(PIDcommon.get(x)) != null) { // changes
					s1.addAll(map.get(PIDcommon.get(x)));
					// s3.addAll(map.get(PIDcommon.get(x)));

				}

				if (map1.get(PIDcommon.get(x)) != null) { // comments
					s2.addAll(map1.get(PIDcommon.get(x)));
					s3.addAll(map1.get(PIDcommon.get(x)));
				}

				s1.retainAll(s2);
				s1intersect = s1.size();
				pcomments = s3.size(); // comments

				if (pcomments > 0) {
					ss = s1intersect / pcomments;
				} else if (pcomments <= 0) {
					ss = 0;
				}

				writer.append(String.valueOf(PIDcommon.get(x)));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(ss));
				writer.append(COMMA_DELIMITER);

				writer.append(NEW_LINE_SEPARATOR);

				System.out.println("\nSS : " + ss);

			}

			writer.close();
			System.out.println("done");

		}

		catch (IOException E) {
		}

	}

	public static void findSimilarity_modified_Average(ArrayList<Integer> PIDcommon, Integer sliceNo) {

		// map is changes
		// map1 is comments

		Map<Integer, Set<Integer>> map = new LinkedHashMap<Integer, Set<Integer>>();
		map.clear();
		Map<Integer, Set<Integer>> map1 = new LinkedHashMap<Integer, Set<Integer>>();
		map1.clear();
		Set<Integer> s1 = new LinkedHashSet<Integer>();
		s1.clear();
		Set<Integer> s2 = new LinkedHashSet<Integer>();
		s2.clear();
		Set<Integer> s3 = new LinkedHashSet<Integer>();
		s3.clear();
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM tickets_openstack.linklistchanges Where ts = " + sliceNo + ";");

			while (rs.next()) {

				if (!map.containsKey(rs.getInt("start")))
					map.put(rs.getInt("start"), new HashSet<Integer>());
				map.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map.containsKey(rs.getInt("end")))
					map.put(rs.getInt("end"), new HashSet<Integer>());
				map.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM tickets_openstack.linklistcomments Where ts = " + sliceNo + ";");

			while (rs.next()) {

				if (!map1.containsKey(rs.getInt("start")))
					map1.put(rs.getInt("start"), new HashSet<Integer>());
				map1.get(rs.getInt("start")).add(rs.getInt("end"));

				if (!map1.containsKey(rs.getInt("end")))
					map1.put(rs.getInt("end"), new HashSet<Integer>());
				map1.get(rs.getInt("end")).add(rs.getInt("start"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int num = 0;
		float ss = 0;
		float sstotal = 0;
		float ssavg = 0;
		float s1intersect = 0;
		float pcomments = 0;
		float totalpeople = 0;

		for (int x = 0; x < PIDcommon.size(); x++) {
			s1.clear();
			s2.clear();
			s3.clear();

			num = PIDcommon.get(x);
			System.out.println(map.get(PIDcommon.get(x)));
			System.out.println(map1.get(PIDcommon.get(x)));

			if (map.get(PIDcommon.get(x)) != null) {
				s1.addAll(map.get(PIDcommon.get(x)));

			}

			if (map1.get(PIDcommon.get(x)) != null) {
				s2.addAll(map1.get(PIDcommon.get(x)));
				s3.addAll(map1.get(PIDcommon.get(x)));

			}

			s1.retainAll(s2);
			s1intersect = s1.size();
			pcomments = s3.size();

			if (pcomments > 0) {
				ss = s1intersect / pcomments;
			} else if (pcomments <= 0) {
				ss = 0;
			}

			if (!Double.isNaN(ss)) {
				sstotal = sstotal + ss;
			}

			System.out.println("\nSS : " + ss);
			System.out.println("Total : " + sstotal + "\n\n");
		}
		totalpeople = PIDcommon.size();
		if (!Double.isNaN(sstotal)) {
			ssavg = sstotal / totalpeople;
		}
		// ssavg = sstotal/totalpeople;

		System.out.println("\nTS : " + sliceNo);
		System.out.println("\nNumber of people : " + totalpeople);
		System.out.println("\nSS avg : " + ssavg);

		try {
			PrintWriter writer = new PrintWriter(new FileWriter(
					"D:\\Replicate package\\Similarity Score\\Similarity_Score_Average_modified.csv", true));

			if (num > 0) {

				writer.append(String.valueOf(sliceNo));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(PIDcommon.size()));
				writer.append(COMMA_DELIMITER);

				writer.append(String.valueOf(ssavg));
				writer.append(COMMA_DELIMITER);

				writer.append(NEW_LINE_SEPARATOR);
			}
			writer.close();
			System.out.println("done");

		}

		catch (IOException E) {
		}
	}

}
