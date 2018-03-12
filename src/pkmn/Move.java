package pkmn;
import java.io.BufferedReader;
import java.io.FileReader;

public class Move {
        // Move class handles pkmn attack moves
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
	
	public Move () {
		this.ID = 0;
		this.name = "---";
		this.function = -1;
		this.power = 0;
		this.type = "";
		this.category = "";
		this.accuracy = 0;
		this.pp = 0;
		this.effectChance = 0;
		this.target = 0;
		this.priority = 0;
		this.flags = "";
		this.description = "";
	}
	
	public Move (String name) {
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
	
	public int getID () {
		return this.ID;
	}
	
	public String getName () {
		return this.name;
	}
	
	public int getPower () {
		return this.power;
	}
	
	public String getType () {
		return this.type;
	}
	
	public String getCategory () {
		return this.category;
	}
	
	public int getAccuracy () {
		return this.accuracy;
	}
	
	public String getDescribtion () {
		return this.description;
	}
	
	public int getPP () {
		return this.pp;
	}
	
	void attack () {
		/// use attack, calculate dmg and apply effects
	}
}
