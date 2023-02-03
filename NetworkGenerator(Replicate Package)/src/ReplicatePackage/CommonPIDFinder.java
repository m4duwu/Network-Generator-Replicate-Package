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




public class CommonPIDFinder {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
    
    
    
    public static ArrayList<Integer> getCommonPID(ArrayList list, Integer sliceNo){
		 ArrayList<Integer> PIDCommon = new ArrayList<>();
		 PIDCommon.clear();
		 Set<Integer> s3 = new LinkedHashSet<Integer>();
			s3.clear();
		 Set<Integer> s1 = new LinkedHashSet<Integer>();
			s1.clear();
			Set<Integer> s2 = new LinkedHashSet<Integer>();
			s2.clear();
			int slice = sliceNo + 1;
			
			//Get Developers who commented from specified time
			try {

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
						"password");

				Statement stmt = con.createStatement();

			
				ResultSet rs = stmt.executeQuery(

						"Select distinct(tickets_openstack.comments.submitted_by) as submitted_by,issue_id FROM tickets_openstack.comments right join tickets_openstack.set_p on tickets_openstack.comments.submitted_by=tickets_openstack.set_p.PID where tickets_openstack.comments.submitted_on between '2010-04-14 16:30:35.0' and '"
								+ list.get(sliceNo) + "' order by submitted_by;");

				while (rs.next()) {
					
				
					s1.add(rs.getInt("submitted_by"));
					
		
			
	}

			}
				
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Get Developers who made changes from specified time
			try {

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
						"password");

				Statement stmt = con.createStatement();


				ResultSet rs = stmt.executeQuery(

						"SELECT distinct(tickets_openstack.changes.changed_by) as changed_by,tickets_openstack.changes.issue_id FROM tickets_openstack.changes right join tickets_openstack.set_p on tickets_openstack.changes.changed_by= tickets_openstack.set_p.PID where tickets_openstack.changes.changed_on between '2010-04-14 16:30:35.0' and '"
								+ list.get(sliceNo) + "' order by changed_by");
			
					while (rs.next()) {
						
				       s2.add(rs.getInt("changed_by"));
				       s3.add(rs.getInt("changed_by"));

					}

				
					s2.retainAll(s1); // get common developers
				
				
				PIDCommon.clear();
				PIDCommon.addAll(s2);
				Collections.sort(PIDCommon);

				
				try {
					PrintWriter writer = new PrintWriter(
							new FileWriter("D:\\Replicate package\\Changes Network\\CommonVertices_ForEachTimeslice.csv", true));
					
					for (int xxx = 0; xxx < PIDCommon.size(); xxx++) {
						
						writer.append(String.valueOf(slice));
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(s1.size()));
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(s3.size()));
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(s2.size()));
						writer.append(COMMA_DELIMITER);
						
						writer.append(String.valueOf(PIDCommon.get(xxx)));
						writer.append(COMMA_DELIMITER);
						
						writer.append(NEW_LINE_SEPARATOR);
					}

					writer.close();
					System.out.println("done");

				} catch (IOException E) {

				}

			}
				catch (SQLException e) {
					e.printStackTrace();
				}		
		    return PIDCommon;
	}
    
	public static ArrayList<Integer> getPID(Integer sliceNo) {

		ArrayList<Integer> PID = new ArrayList<>();
		PID.clear();
		

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM tickets_openstack.common_id_50 where ts = "+sliceNo+""); //Gets common PID from database
			while (rs.next()) {

				PID.add(rs.getInt("Vertices"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return PID;

	}
	
	
	
	


}
