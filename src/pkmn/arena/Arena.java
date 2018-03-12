package pkmn.arena;
import java.util.Scanner;
import character.*;
import pkmn.Pokemon;

public class Arena {
        // Arena class handles pkmn fight environment
	private int numberOfFighters = 1;		//number of fighters on one side at the same time
	private Fighter[] blue = new Fighter[6];
	private Fighter[] red = new Fighter[6];
	private int status = 0;		// 0: init; 1: summone; 2; select; 3; process;
	int activeBlue = 0;
	int activeRed = 0;
	
	public Arena (Fighter pkmn, Player trainer) {
		for (int i = 0; i<6; i++) {
			if (trainer.getPokemon(i) != null || trainer.getPokemon(i).getID() != 0) {
				blue[i] = new Fighter (trainer.getPokemon(i));
			}
		}
		this.red[0] = pkmn;
	}
	
	public Arena (Player tBlue, Nplayer tRed) {
		for (int i = 0; i<6; i++) {
			if (tBlue.getPokemon(i) != null || tBlue.getPokemon(i).getID() != 0) {
				blue[i] = new Fighter (tBlue.getPokemon(i));
			}
			if (tRed.getPokemon(i) != null || tRed.getPokemon(i).getID() != 0) {
				red[i] = new Fighter (tRed.getPokemon(i));
			}
		}
	}
	
	private void checkStatus () {
		
	}
	
	private void fightloop () {
		while (this.status != 16) {
			onRoundStart();
			onSummon();
			while (this.status < 11) {
				display();
				checkInput();
			}
			onMove();
			onMoveAfter();
			onRoundEnd();
		}
	}
	
	private void onRoundStart () {
		
	}
	
	private void onSummon () {
		checkAbility();
		checkItem();
	}
	
	private void checkInput () {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		if (s.equals("Fight") && this.status == 0) {
			this.status = 1;
		}
		else if (s.equals("reset") && this.status == -2) {
			this.status = 0;
		}
		else {
			this.status = -2; //wrong input
		}
	}
	
	private void onMove () {
		
	}
	
	private void onMoveAfter () {
		
	}
	
	private void onRoundEnd () {
		
	}
	
	private void checkArenaStatus () {
		
	}
	
	private void checkItem () {
		
	}
	
	private void checkAbility() {
		
	}
	
	private void display () {
		System.out.print("\r" + displayPkm() + "\n\n" + displayMenu() + "\n");		
	}
	
	private String displayPkm () {
		Fighter blueNow = blue[this.activeBlue];
		Fighter redNow = red[this.activeRed];
		String outblue = "\t" + redNow.getName() + "\n\t" + redNow.getState().getHp() + "/" + redNow.getState().getTotalHp();
		String outred = blueNow.getName() + "\n" + blueNow.getState().getHp() + "/" + blueNow.getState().getTotalHp();
		return outblue + "\n\n" + outred;
	}
	
	private String displayMenu () {
		Fighter playerpkm = blue[this.activeBlue];
		String menu = new String(new char[40]).replace("\0", "-");
		if (this.status == 0) {
			menu += "\nFight\t\t" + "Bag\n" + "Pokemon\t\t" + "Escape";
			return menu;
		}
		else if (this.status == 1) {
			menu += "\n" + playerpkm.getMove(0).getName() +"\t\t" + 
					playerpkm.getMove(1).getName() + "\n" + 
					playerpkm.getMove(2).getName() + "\t\t" +
					playerpkm.getMove(3).getName();
			return menu;
		}
		else {
			this.status = -2;
			return "Unkown input! Type \"reset\" to continue";
		}
	}
	
    public static void main(String[] args) {
    	Pokemon pkmwild = new Pokemon(300, 36);
    	Pokemon pkmtrainer = new Pokemon (1, 40);
    	Fighter wildfighter = new Fighter(pkmwild);
    	Player me = new Player("phantomiil");
    	me.addPokemon(pkmtrainer, 0);
    	//me.showTrainer();
    	Arena test = new Arena(wildfighter, me);
    	test.fightloop();
    }
}
