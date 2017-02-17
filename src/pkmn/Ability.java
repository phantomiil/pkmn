package pkmn;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ability {
	static String PKM_ABILITY = "Pokemon/abilities.txt";
	
	private int ID;
	private String name;
	private String describtion;
	
	public Ability () {
		this.ID = 0;
		this.name = "---";
		this.describtion = "";
	}
	
	public Ability (String name) {
		String line = null;
		
		try {
			FileReader freader = new FileReader(PKM_ABILITY);
			BufferedReader breader = new BufferedReader(freader);
			while((line = breader.readLine()) != null) {
				String[] s = line.split(",");
				if (s[2].equals(name)) {
					
				}
			}
			breader.close();
		}
		
		
		catch (Exception e) {
			
		}
	}
	
	int getID () {
		return this.ID;
	}
}
