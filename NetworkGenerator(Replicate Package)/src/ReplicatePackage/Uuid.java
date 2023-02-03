package ReplicatePackage;

public class Uuid {
	
	public Uuid (Integer PID, String UUID)
	{
		this.PID = PID;
		this.UUID = UUID;
		
	}
	
	private Integer PID;
	/**
	 * 
	 */
	private String UUID;

	public Integer getPID() {
	 	 return PID; 
	}
	/**
	 * Setter of EndVertex
	 */
	public void setPID(Integer PID) { 
		 this.PID = PID; 
	}
	/**
	 * Getter of type
	 */
	public String getUUID() {
	 	 return UUID; 
	}
	/**
	 * Setter of type
	 */
	public void setUUID(String UUID) { 
		 this.UUID = UUID; 
	}
	

	@Override
	   public String toString() {
	        return ("PID:"+this.getPID()+
	        		"UUID:"+this.getUUID()+
	                    
	                    "\n");
	   }

}
