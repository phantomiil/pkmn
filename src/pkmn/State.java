package pkmn;

public class State {
	private int hp;							//pokemons current hp
	private int totalhp;					//pokemons total hp
	private int attack;						//pokemons attack
	private int defense;					//pokemons defense
	private int speed;						//pokemons speed
	private int spatk;						//pokemons special attack
	private int spdef;						//pokemons special defense
	private int happiness;					//pokemons happines
	private int status;						//pokemons status 0:neutral, 1:sleep, 2:paralyze, 3:fire, 4:ice, 5:poisen, 6:dead
	private int statusCount;				//count for status change
	
	public State () {
		this.totalhp = 0;
		this.hp = 0;
		this.attack = 0;
		this.defense = 0;
		this.speed = 0;
		this.spatk = 0;
		this.spdef = 0;
		this.happiness = 0;
		this.status = 0;
		this.statusCount = 0;
	}
	
	public State(Pokemon pkmn, int hp, int status) {
		this.totalhp = pkmn.calcStats(0);
		this.hp = (hp > this.totalhp) ? this.totalhp : (hp < 0) ? 0 : hp;
		this.attack = pkmn.calcStats(1);
		this.defense = pkmn.calcStats(2);
		this.speed = pkmn.calcStats(3);
		this.spatk = pkmn.calcStats(4);
		this.spdef = pkmn.calcStats(5);
		this.status = status;
		this.statusCount = 0;
	}
	
	public State (Pokemon pkmn) {
		this.totalhp = pkmn.calcStats(0);
		this.hp = this.totalhp;
		this.attack = pkmn.calcStats(1);
		this.defense = pkmn.calcStats(2);
		this.speed = pkmn.calcStats(3);
		this.spatk = pkmn.calcStats(4);
		this.spdef = pkmn.calcStats(5);
		this.status = 0;
		this.statusCount = 0;
	}
	
	//PUBLIC FUNCTIONS
	public void setHappines (int happiness) {
		this.happiness = happiness;
	}
	
	public void resetStatusCounter () {
		this.statusCount = 0;
	}
	
	public int getHp () {
		return this.hp;
	}
	
	public int getTotalHp () {
		return this.totalhp;
	}
}
