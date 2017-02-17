package pkmn;
import java.io.*;
import java.util.Random;

public class Pokemon {
	static String PKM_SPECIES = "Pokemon/species.txt";
	Random rg = new Random();
	
	private int ID;
	private String species; //species name
	private String name;	//nickname
	private int level;		//current level
	private int exp;		//current exp
	private int growth;		//pokemons experience growth rate
	
	private Type[] type = {new Type(), new Type()};		//pokemons type
	private Ability ability = new Ability();						//pokemons ability
	private int abilityflag = -1;					//used to set hidden ability
	
	private int[] iv = {0,0,0,0,0,0};			//individual values, 0-31 random
	private int[] ev = {0,0,0,0,0,0};		//effort values 0 on creation
	private int[] basestats = {0,0,0,0,0,0};	//pokemons base stats
	private int nature = -1;			//pokemons nature
	private int natureflag = -1;		//natureflag (for items)
	
	private State state = new State();
	
	private int baseExp = 0;					//experience gained on defeating that species
	private int[] effort = {0,0,0,0,0,0};		//effort values gained on defeating that species
	
	private Appearence app = new Appearence();
	
	private Moves moves = new Moves();		//pokemons known moves
	
	private int eggsteps = 0;		//egg steps if 0 it is a pokemon
	private int ballused = 0;		//used ball to catch
	private int rareness = 0;		//catch probability
	private String item = "";		//carried item
	private int gender = 0;			//pokemons gender
	private int genderflag = 0;		//genderflag (for items)
	private int isShiny = 0;		//1 if pokemon is shiny, 0 else NOT IMPLEMENTED
	private int shinyflag = 0;		//shinyflagg (for items)
	
	private int form = 0;		//pokemons form
	private double weight = 0;	//pokemons weight
	private double height = 0;	//pokemons height
	private String[] evolution = {"", "", ""};		//pokemons next evolution step
	
	private String[] compatibility = {"", ""};		//pokemons egg groups

	
// CONSTRUCTER SECTION //
	public Pokemon() {
		this.ID = 0;
		this.species = "---";
		this.name = "---";
		this.level = 0;
		this.exp = 0;
		this.growth = 0;
	}
	
	public Pokemon(int ID, int level, boolean egg, boolean hidden) {
		String line = null;
		boolean section = false;
		String start = "["+ID+"]";
		String stop = "["+(ID+1)+"]";
		
		this.ID = ID;
		this.level = level;
		try {
			FileReader freader = new FileReader(PKM_SPECIES);
			BufferedReader breader = new BufferedReader(freader);
			while((line = breader.readLine()) != null) {
				if (line.equals(start)) {
					section = true;
				}
				if (line.equals(stop)) {
					section = false;
				}
				if (section) {
					valuesFromFile (line, egg, hidden);
				}
			}
			breader.close();
		}
		
		
		catch (Exception e) {
			
		}
		
		this.name = this.species;
		this.exp = calcExpForLevel (level);
		for (int i = 0; i<6; i++) {
			this.iv[i] = rg.nextInt(32);
		}
		this.nature = rg.nextInt(4)*4+rg.nextInt(4);
		this.SetStats();
	}
	
	public Pokemon (int id, int level) {
		this(id, level, false, false);
	}
////////////////////////
	
// PUBLIC FUNCTIONS //
	public void showPokemon () {
		int p1 = (this.level < 100) ? 1 : 0;
		System.out.printf("%s\tlvl. %d\n", this.name, this.level);
		System.out.println("------------------------------");
		//System.out.printf("%d/%d\t\t%d\n",this.hp, this.totalhp, this.status);
		System.out.printf("Exp. %d/%d\t%s\n", this.exp, this.calcExpForLevel(this.level+p1), this.item);
		System.out.println("");
		//System.out.printf("%s\t\t%s\n", this.moves[0].getName(), this.moves[1].getName());
		//System.out.printf("%s\t\t%s\n", this.moves[2].getName(), this.moves[3].getName());
	}
	
	public Pokemon catchIt(int ball) {
		this.ballused = ball;
		return this;
	}
	
	//public int getStatus() {
	//	return this.status;
	//}
	
	public int getID () {
		return this.ID;
	}
	
	//public void setStatus(int st) {
	//	this.status = st;
	//}

	public String getName () {
		return this.name;
	}
	
	public int getLevel () {
		return this.level;
	}
	
	public int getExp () {
		return this.exp;
	}
	
	public Type[] getType() {
		return this.type;
	}
	
	public Ability getAbility () {
		return this.ability;
	}
	
	public int calcStats (int i) {
		if (i == 0) { 
			int hp = (int)((2*this.basestats[0]+this.iv[0]+this.ev[0]*0.25+100)*this.level/100.0+10);
			if (this.ID == 292) { hp = 1;}
			return hp;
		}else {
			double nature = 1;
			if (Math.abs(this.nature-10*i)<10) {nature = nature - 0.1;}
			if ((this.nature-i) % 10 == 0) {nature = nature + 0.1;}
			int stat = (int)(((2*this.basestats[i]+this.iv[i]+this.ev[i]*0.25)*this.level/100.0+5)*nature);
			return stat;
		}
	}
	
	public State getState () {
		return this.state;
	}
	
	public String getItem () {
		return this.item;
	}
	
	public int getGender () {
		return this.gender;
	}
	
	public Moves getMoves () {
		return this.moves;
	}
	
// PRIVATE FUNCTIONS //	

	private void SetStats () {
		this.state = new State(this);
	}
	
	private int calcExpForLevel(int level) {
		if (level == 1) {
			return 0;
		}
		switch (this.growth) {
		case 1:
			return (int)(4.0*level*level*level/5);
		case 2:
			return (int)(level*level*level);
		case 3:
			return (int)(5.0*level*level*level/4);
		case 4:
			return (int)(6./5*level*level*level-15*Math.pow(level,2)+100*level-140);
		case 5:
			if (level <= 50) { return (int)(level*level*level*((100-level)/50.0)); }
			else if (level <= 68) { return (int)(level*level*level*((100-level)/100.)); }
			else if (level <= 98) { return (int)(level*level*level*(((1911-10*level)/3.)/500.)); }
			else { return (int)(level*level*level*(160-level)/100.); }
		case 6:
			if (level <= 15) { return (int)(level*level*level*(24+((level+1)/3.))/50.); }
			else if (level <= 36) { return (int)(level*level*level*(14+level)/50.); }
			else { return (int)(level*level*level*(32+(level/2.))/50.); }
		default:
			return 0;
		}
	}
	
	private void valuesFromFile(String line, boolean egg, boolean hidden) {
		String[] var = line.split("=");
		switch (var[0]) {
			case "Name":
				this.species = var[1];
				break;
			case "Type1":
				this.type[0].setType(var[1]);
				break;
			case "Type2":
				this.type[1].setType(var[1]);
				break;
			case "BaseStats": {
				String[] s = var[1].split(",");
				for (int i = 0; i < 6; i++) {
					this.basestats[i] = Integer.parseInt(s[i]);
				}
				break;
			}
			case "GenderRate":
				switch (var[1]) {
					case "AlwaysMale":
						this.gender = 0;
					case "FemaleOneEighth":
						if(rg.nextDouble()<0.125){this.gender=1;}
						break;
					case "Female25Percent":
						if (rg.nextDouble()<0.25){this.gender=1;}
						break;
					case "Female50Percent":
						if (rg.nextDouble()<0.50){this.gender=1;}
						break;
					case "Female75Percent":
						if (rg.nextDouble()<0.75){this.gender=1;}
						break;
					case "FemaleSevenEighths":
						if (rg.nextDouble()<0.875){this.gender=1;}
						break;
					case "AlwaysFemale":
						this.gender=1;
						break;
					case "Genderless":
						this.gender=2;
						break;
				}
				break;
			case "GrowthRate":
				switch (var[1]) {
					case "Fast":
						this.growth = 1;
						break;
					case "Medium":
						this.growth = 2;
						break;
					case "MediumFast":
						this.growth = 2;
						break;
					case "Slow":
						this.growth = 3;
						break;
					case "Parabolic":
						this.growth = 4;
						break;
					case "MediumSlow":
						this.growth = 4;
						break;
					case "Erratic":
						this.growth = 5;
						break;
					case "Fluctuating":
						this.growth = 6;
						break;
				}
				break;
			case "BaseEXP":
				this.baseExp = Integer.parseInt(var[1]);
				break;
			case "EffortPoints": {
				String[] s = var[1].split(",");
				for (int i = 0; i<6; i++) {
					this.effort[i] = Integer.parseInt(s[i]);
				}
				break;
			}
			case "Rareness":
				this.rareness = Integer.parseInt(var[1]);
				break;
			case "Happiness":
				this.state.setHappines(Integer.parseInt(var[1]));
				break;
			case "Moves": {
				String[] s = var[1].split(",");
				int l = s.length;
				int[] lvl = new int[l/2];
				String[] name = new String[l/2];
				int counter = 0;
				String[] selected = new String[4];
				for (int i = 0; i<l; i++) {
					if (i%2 == 0) {
						lvl[i/2] = Integer.parseInt(s[i]);
					}
					else {
						name[i/2] = s[i];
					}
				}
				for (int i = 0; i<l/2; i++) {
					if (this.level >= lvl[i]) {
						selected[counter] = name[i];
						counter = (counter+1)%4;
					}
				}
				this.moves = new Moves(new Move(selected[0]), new Move(selected[1]), new Move(selected[2]), new Move(selected[3]));
				break;
			}
			case "Compatibility": {
				String[] s = var[1].split(",");
				for (int i = 0; i<s.length; i++) {
					this.compatibility[i] = s[i];
				}
				break;
			}
			case "StepsToHatch":
				this.eggsteps = Integer.parseInt(var[1]);
				break;
			case "Height":
				double h = Double.parseDouble(var[1]);
				this.height = Math.round(h*(1+0.2*h*rg.nextDouble()-0.1*h)*10)/10.0;
				break;
			case "Weight":
				double w = Double.parseDouble(var[1]);
				this.weight = Math.round(w*(1+0.3*w*rg.nextDouble()-0.15*w)*10)/10.0;
				break;
			case "Abilities": {
				String[] s = var[1].split(",");
				if (rg.nextDouble()<0.5 && s.length==2) {
					this.ability = new Ability(s[1]);
				}
				else {
					this.ability = new Ability(s[0]);
				}
				break;
			}
			case "HiddenAbility":
				if (hidden == true) {
					this.ability = new Ability(var[1]);
				}
				break;
			case "EggMoves":
				if (egg == true) {
					String[] s = var[1].split(",");
					Move mv = new Move(s[rg.nextInt(s.length)]);
					int r = rg.nextInt(3);
					this.moves.replaceMove(mv, r);
				}
				break;
			case "Evolutions":
				this.evolution = var[1].split(",");
				break;
		}
	}
}
