package hashcode;

public class Object1 {

	private int obID;
	private String obName;
	public Object1(int obID, String obName) {
		super();
		this.obID = obID;
		this.obName = obName;
	}
	
	public int getObID() {
		return obID;
	}
	public void setObID(int obID) {
		this.obID = obID;
	}
	public String getObName() {
		return obName;
	}
	public void setObName(String obName) {
		this.obName = obName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Object1) {
			Object1 object1 = (Object1)obj;
			if(object1.getObID() == this.obID) {
				return true;
			}
			else {
				return false;
			}
		}
		else 
			return false;
	}
}
