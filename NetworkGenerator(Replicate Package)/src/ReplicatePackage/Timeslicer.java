package ReplicatePackage;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
//import java.util.Date;
import java.util.stream.LongStream;

public class Timeslicer {
	private static Timestamp startTime;
	private static Timestamp endTime;
//	public static ArrayList <timeslice> intervalList = new ArrayList();
//	private static timeslice ts = new timeslice(startTime , endTime);


	public static ArrayList<Timeslice> timeslicer(int x ) {
		
	
		ArrayList<Timeslice> list = new ArrayList();
		list.addAll(Timeslice.intervalList(x));
		return list;
		
		
	}

	
}