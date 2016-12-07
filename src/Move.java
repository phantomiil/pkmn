import java.io.BufferedReader;
import java.io.FileReader;

public class Move {
	static String PKM_MOVES = "Pokemon/moves.txt";
	
	private int ID;
	private String name;
	private int function;
	private int power;
	private String type;
	private String category;
	private int accuracy;
	private int pp;
	private int effectChance;
	private int target;
	private int priority;
	private String flags;
	private String description;
	
	Move (String name) {
		String line = null;
		
		try {
			FileReader freader = new FileReader(PKM_MOVES);
			BufferedReader breader = new BufferedReader(freader);
			while((line = breader.readLine()) != null) {
				String[] s = line.split(",");
				if (s[1].equals(name)) {
					this.ID = Integer.parseInt(s[0]);
					this.name = s[2];
					this.function = Integer.parseInt(s[3]);
					this.power = Integer.parseInt(s[4]);
					this.type = s[5];
					this.category = s[6];
					this.accuracy = Integer.parseInt(s[7]);
					this.pp = Integer.parseInt(s[8]);
					this.effectChance = Integer.parseInt(s[9]);
					this.target = Integer.parseInt(s[10]);
					this.priority = Integer.parseInt(s[11]);
					this.flags = s[12];
					this.description = s[13];
					break;
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
	
	String getName () {
		return this.name;
	}
	
	int getPower () {
		return this.power;
	}
	
	String getType () {
		return this.type;
	}
	
	String getCategory () {
		return this.category;
	}
	
	int getAccuracy () {
		return this.accuracy;
	}
	
	String getDescribtion () {
		return this.description;
	}
	
	int getPP () {
		return this.pp;
	}
	
	void attack () {
		/// use attack, calculate dmg and apply effects
	}
}
