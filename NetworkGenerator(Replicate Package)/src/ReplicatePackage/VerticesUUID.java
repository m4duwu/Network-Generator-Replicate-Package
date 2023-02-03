package ReplicatePackage;

public class VerticesUUID {

private Integer numberlist;
	
	public VerticesUUID(Integer numberlist, String UUID)
	{
		this.numberlist=numberlist;
		this.UUID = UUID;
	}

	public Integer getNumberlist() {
		return numberlist;
	}

	public void setNumberlist(Integer numberlist) {
		this.numberlist = numberlist;
	}
	
	private String UUID;


	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		UUID = UUID;
	}



}

