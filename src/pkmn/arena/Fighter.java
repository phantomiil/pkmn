package pkmn.arena;

import pkmn.*;

public class Fighter {

	private String name;
	private int level;
	private int exp;
	
	private Type[] type = {new Type(), new Type()};
	private Ability ability = new Ability();
	
	private State state = new State();
	
	private String item = "";		//carried item
	private int gender = 0;			//pokemons gender
	
	private Moves moves = new Moves();
	
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
		this.moves = pkmn.getMoves();
	}
	
	public String getName () {
		return this.name;
	}
	
	public Move getMove(int i) {
		return this.moves.getMove(i);
	}
	
	public State getState() {
		return this.state;
	}
}
