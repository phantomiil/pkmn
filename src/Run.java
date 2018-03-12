import pkmn.Pokemon;
import character.*;
import pkmn.arena.Arena;

public class Run {
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