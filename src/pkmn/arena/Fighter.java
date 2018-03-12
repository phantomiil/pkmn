package pkmn.arena;

import pkmn.*;

public class Fighter {
        // Fighter class smaller class clone of Pokemon, handles pkmn status in fight

	private String name;
	private int level;
	private int exp;
	
	private Type[] type = {new Type(), new Type()};
	private Ability ability = new Ability();
	
	private State state = new State();
	
	private String item = "";		//carried item
	private int gender = 0;			//pokemons gender
	
	private Moveset moveset = new Moveset();
	
	public Fighter () {
		this.name = "---";
		this.level = 0;
		this.exp = 0;
	}
	
	public Fighter(Pokemon pkmn) {
		this.name = pkmn.getName();
		this.level = pkmn.getLevel();
		this.exp = pkmn.getExp();
		this.type = pkmn.getType();
		this.ability = pkmn.getAbility();
		this.state = pkmn.getState();
		this.item = pkmn.getItem();
		this.gender = pkmn.getGender();
		this.moveset = pkmn.getMoveset();
	}
	
	public String getName () {
		return this.name;
	}
	
	public Move getMove(int i) {
		return this.moveset.getMove(i);
	}
	
	public State getState() {
		return this.state;
	}
}
