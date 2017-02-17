package character;
import pkmn.Pokemon;

public class Trainer {
	private String name;
	private Pokemon[] pkmn = new Pokemon[6];
	
	public Trainer (String name) {
		this.name = name;
		for (int i = 0; i<6; i++) {
			this.pkmn[i] = new Pokemon();
		}
		 
	}
	
	public void addPokemon (Pokemon pkmn, int place) {
		this.pkmn[place] = pkmn;
	}
	
	public void removePokemon (int place) {
		this.pkmn[place] = new Pokemon();
	}
	
	public Pokemon getPokemon(int place) {
		return this.pkmn[place];
	}
	
	public void arrangePkmn () {
		int emptyStart = -1;
		for (int i=0; i<6; i++) {
			if (pkmn[i].getID() == 0 && emptyStart < 0) {
				emptyStart = i;
			}
			else if (emptyStart >= 0) {
				pkmn[emptyStart] = pkmn[i];
				emptyStart += 1;
			}
		}
	}
	
	public void showTrainer () {
		System.out.println(this.name);
		for (int i = 0; i<6; i++) {
			if (this.pkmn[i].getID() != 0) {
				System.out.println(this.pkmn[i].getName());
			}
			else {
				System.out.println("-");
			}
		}
	}
}
