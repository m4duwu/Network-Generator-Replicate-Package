package ReplicatePackage;

public class Link {
	
	public Link (int StartVertex, int EndVertex, int Weight)
	{
		this.StartVertex = StartVertex;
		this.EndVertex = EndVertex;
		this.Weight = Weight;
	}

	/**
	 * 
	 */
	private Integer EndVertex;
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
	private Integer StartVertex;
	/**
	 * Getter of EndVertex
	 */
	public Integer getEndVertex() {
	 	 return EndVertex; 
	}
	/**
	 * Setter of EndVertex
	 */
	public void setEndVertex(Integer EndVertex) { 
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
	public Integer getStartVertex() {
	 	 return StartVertex; 
	}
	/**
	 * Setter of StartVertex
	 */
	public void setStartVertex(Integer StartVertex) { 
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
