package pkmn;

public class Type {
        // Type class handles pkmn type
	
	private String typeStr;
	private int type;
	
	public Type (String typeStr) {
		this.typeStr = typeStr;
	}
	
	public Type () {
		this.typeStr = "";
	}
	
	public void setType (String type) {
		this.typeStr = type;
	}
}
