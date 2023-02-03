package ReplicatePackage;

public class LinkUUID {

	public LinkUUID (String StartVertex, String EndVertex, int Weight)
	{
		this.StartVertex = StartVertex;
		this.EndVertex = EndVertex;
		this.Weight = Weight;
	}

	/**
	 * 
	 */
	private String EndVertex;
	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private Integer Weight;
	/**
	 * 
	 */
	private String StartVertex;
	/**
	 * Getter of EndVertex
	 */
	public String getEndVertex() {
	 	 return EndVertex; 
	}
	/**
	 * Setter of EndVertex
	 */
	public void setEndVertex(String EndVertex) { 
		 this.EndVertex = EndVertex; 
	}
	/**
	 * Getter of type
	 */
	public String getType() {
	 	 return type; 
	}
	/**
	 * Setter of type
	 */
	public void setType(String type) { 
		 this.type = type; 
	}
	/**
	 * Getter of Weight
	 */
	public Integer getWeight() {
	 	 return Weight; 
	}
	/**
	 * Setter of Weight
	 */
	public void setWeight(Integer Weight) { 
		 this.Weight = Weight; 
	}
	/**
	 * Getter of StartVertex
	 */
	public String getStartVertex() {
	 	 return StartVertex; 
	}
	/**
	 * Setter of StartVertex
	 */
	public void setStartVertex(String StartVertex) { 
		 this.StartVertex = StartVertex; 
	}
	/**
	 * 
	 */
	public void getTimeSlice() { 
		// TODO Auto-generated method
	 }
	/**
	 * 
	 */
	public void setCommentlink() { 
		// TODO Auto-generated method
	 }
	/**
	 * 
	 */
	public void setChangesLink() { 
		// TODO Auto-generated method
	 } 
	
	 @Override
	   public String toString() {
	        return ("Start Vertex:"+this.getStartVertex()+
	                    " End Vertex: "+ this.getEndVertex() +
	                    " Weight: "+ this.getWeight()
	                    +"\n");
	   }

}
