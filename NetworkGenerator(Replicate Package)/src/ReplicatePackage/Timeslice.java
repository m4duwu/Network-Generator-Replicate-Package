package ReplicatePackage;

import java.awt.*;
import java.io.*;
import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Date;
import java.util.stream.LongStream;

public class Timeslice {

	public static Timestamp startTime;
	public static Timestamp endTime;
	public static ArrayList intervalList = new ArrayList();

	
	public static ArrayList<Timeslice> intervalList(int x) // arraylist of endtimes
	{
		long start, end, duration, interval, temp2;
		float numoftimeslices = x;
		startTime = Timeslice.getStartTime();
		endTime = Timeslice.getEndTime();
		setEndTime(endTime);
		setStartTime(startTime);
		start = startTime.getTime();
		end = endTime.getTime();
		duration = end - start;
		interval = (long) (duration / numoftimeslices);

		for (int i = 1; i < numoftimeslices; i++) {
			temp2 = start + (i * interval);
			Timestamp result = new Timestamp(temp2);

			result.setTime(temp2);
			intervalList.add((Timestamp)result);
		}

		intervalList.add((Timestamp)endTime);
/*
		for (int j = 0; j < intervalList.size(); j++) {
			System.out.println(intervalList.get(j));
		}
*/
		return intervalList; //return arraylist of interval timing(end time)
	}
	
	public Timeslice(Timestamp startTime, Timestamp endTime) {

		
		this.startTime = startTime;
		this.endTime = endTime;	
		
		
	}
	
	public static Timestamp getStartTime() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select min(changed_on) "
					+ "from tickets_openstack.changes inner join tickets_openstack.comments on tickets_openstack.comments.issue_id = tickets_openstack.changes.issue_id");

			while (rs.next()) {
				startTime = rs.getTimestamp("min(changed_on)");

			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return startTime;

	}

	public static Timestamp getEndTime() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickets_openstack", "root",
					"password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select max(changed_on) "
					+ "from tickets_openstack.changes inner join tickets_openstack.comments on tickets_openstack.comments.issue_id = tickets_openstack.changes.issue_id");

			while (rs.next()) {
				endTime = rs.getTimestamp("max(changed_on)");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endTime;
	}
	
	public Timestamp getstartTime(){
		return this.startTime;
	}
	
	
	


	public static void setStartTime(Timestamp startTime) {

		startTime = startTime;

	}


	public static void setEndTime(Timestamp endTime) {

		endTime = endTime;
	}


	

} // end class


