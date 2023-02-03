package ReplicatePackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;



public class Controller {
	
	 private static final String NEW_LINE_SEPARATOR = "\n";
	 private static final String FILE_HEADER="TS,Comments,Changes,Common,CommonVertices";
	 private static final String FILE_HEADER1="TS, Start, End, Weight";
     private static final String FILE_HEADER2="PID, SS, Related, Comments, Changes, Iss_Watched, Iss_Submitted, Iss_Assigned";
     private static final String FILE_HEADER3="TS,Vertices,Avg SS";
     

	public static void main(String[] args) {
		
	   int slices=50;// insert no of slices
		
		ArrayList list= new ArrayList<>(Timeslice.intervalList(slices));
		Timestamp StartTime=Timeslice.getStartTime(); //set time to timeslice and get the startime
		Timestamp EndTime=null;
		
		
		for(int x=0;x<slices;x++){
		EndTime=(Timestamp) list.get(x); // get the endtime from list
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" Start Time : " +StartTime);// insert into sql statement 
		System.out.println(" End Time   : "+ EndTime);  //	insert into sql statement
	
			
		}
			
		
		//Set header for csv
		
		//1. CommonPID csv 
		/*try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\CommonVertices_ForEachTimeslice.csv", true)); // used for commonPID csv
				writer.append(FILE_HEADER.toString());
				writer.append(NEW_LINE_SEPARATOR);
				 writer.close();
					System.out.println("done");
				
			  }
			
		 
		catch (IOException E) {
		}*/
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		//2. CommentsLinkList csv 
			/*	try {
					  PrintWriter writer = new PrintWriter(new FileWriter(
								"D:\\Replicate package\\ChangesLinkList_uuid_Reviews.csv", true)); // used for CommentsLinkList csv
						writer.append(FILE_HEADER1.toString());
						writer.append(NEW_LINE_SEPARATOR);
						 writer.close();
							System.out.println("done");
						
					  }
					
				 
				catch (IOException E) {
				}*/
				/////////////////////////////////////////////////////////////////////////////////////
		
		
		//3. ChangesLinkList csv 
		/*try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\ChangesLinkList.csv", true)); // used for ChangesLinkList csv
				writer.append(FILE_HEADER1.toString());
				writer.append(NEW_LINE_SEPARATOR);
				 writer.close();
					System.out.println("done");
				
			  }
			
		 
		catch (IOException E) {
		}*/
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		//4. SimilarityScore csv 
		/*try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\Similarity Score\\Similarity_Score_Test1.csv", true)); // used for Similarity Score :  SS(p) = Number of elements in the set [p(comments) interesction p(changes)] divided by the number of elements in the set [p(changes)]
				writer.append(FILE_HEADER2.toString());
				writer.append(NEW_LINE_SEPARATOR);
				 writer.close();
					System.out.println("done");
				
			  }
			
		 
		catch (IOException E) {
		}*/
		/////////////////////////////////////////////////////////////////////////////
		
		
		//5. SS-Modified csv 
		/*try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\Similarity Score\\Similarity_Score_uuid_Reviews.csv", true)); // used for SS-modified : SS-modified(p) = Number of elements in the set [p(comments) interesction p(changes)] divided by the number of elements in the set [p(comments)]
				writer.append(FILE_HEADER2.toString());
				writer.append(NEW_LINE_SEPARATOR);
				 writer.close();
					System.out.println("done");
				
			  }
			
		 
		catch (IOException E) {
		}*/
		//////////////////////////////////////////////////////////////////////////////
		
		
		//6. SS-Average csv 
	   /*try {
					  PrintWriter writer = new PrintWriter(new FileWriter(
								"D:\\Replicate package\\Similarity Score\\Similarity_Score_Average.csv", true)); // used for SSAverage
						writer.append(FILE_HEADER3.toString());
						writer.append(NEW_LINE_SEPARATOR);
						 writer.close();
							System.out.println("done");
						
					  }
					
				 
				catch (IOException E) {
				}
				//////////////////////////////////////////////////////////////////////////////
		
		
		//7. SS-Average-modified csv 
		try {
			  PrintWriter writer = new PrintWriter(new FileWriter(
						"D:\\Replicate package\\Similarity Score\\Similarity_Score_Average_modified.csv", true)); // used for SSAverage-modified
				writer.append(FILE_HEADER3.toString());
				writer.append(NEW_LINE_SEPARATOR);
				 writer.close();
					System.out.println("done");
				
			  }
			
		 
		catch (IOException E) {
		}*/
		//////////////////////////////////////////////////////////////////////////////
		
		
		
		for (int x =0;x<slices;x++) 
		{
		//functions to run
			
		//CommonPIDFinder.getCommonPID(list,x);	// Run First	
		
		
		//run any of the two next
		
		//NWFileGenerator.generateNWFile1(ChangeLinkGenerator.GenerateChangeLink(list, x, ChangeLinkGenerator.getPID(x+1)), x,  ChangeLinkGenerator.getPID(x+1));
		
				
		//NWFileGenerator.generateNWFile1(CommentLinkGenerator.GenerateCommentLink(list, x, CommentLinkGenerator.getPID(x+1)), x,  CommentLinkGenerator.getPID(x+1));
			
		//CommentLinkGenerator.GenerateCommentLink(list, x, CommentLinkGenerator.getPID(x+1));
		//ChangeLinkGenerator.GenerateChangeLink(list, x, ChangeLinkGenerator.getPID(x+1));
			
			//CongruenceFinder.findSimilarity_Average(CommonPIDFinder.getPID(x+1),x+1);
			//CongruenceFinder.findSimilarity_modified_Average(CommonPIDFinder.getPID(x+1),x+1);
			
			//CommentLinkGenerator.GenerateCommentLink_uuid(list, x, CommentLinkGenerator.getUUPID_comments(list,x));
			
			//NWFileGenerator.generateNWFile1_UUID(CommentLinkGenerator.GenerateCommentLink_uuid(list, x, CommentLinkGenerator.getUUPID_comments(list,x)), x, CommentLinkGenerator.getUUPID_comments(list,x));
			NWFileGenerator.generateNWFile1_UUID(ChangeLinkGenerator.GenerateChangeLink_uuid(list, x, ChangeLinkGenerator.getUUPID_changes(list, x)), x, ChangeLinkGenerator.getUUPID_changes(list, x));
			//ChangeLinkGenerator.GenerateChangeLink_uuid(list, x, ChangeLinkGenerator.getUUPID_changes(list,x));
		}
		
		
		//To find Similarity Score of each vertice
		//CongruenceFinder.findSimilarity(CommonPIDFinder.getPID(slices));
		
		
		//CongruenceFinder.findSimilarity_Modified(CommonPIDFinder.getPID(slices));
		//CongruenceFinder.findSimilarity_uuid_tickets();
				//CongruenceFinder.findSimilarity_uuid_reviews();
				//ChangeLinkGenerator.GenerateChangeLink_uuid_reviews();
		
			}
		 
      
	}



