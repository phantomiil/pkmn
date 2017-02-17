package pkmn;

public class Moves {
	private Move[] moves = {new Move(), new Move(), new Move(), new Move()};
	private int[] maxpp = new int[4];
	private int[] pp = new int[4];
	
	public Moves () {
		for (int i = 0; i<4; i++) {
			this.maxpp[i] = 0;
			this.pp[i] = 0;
		}
	}
	
	public Moves (Move mv1, Move mv2, Move mv3, Move mv4) {
		this.moves[0] = mv1;
		this.moves[1] = mv2;
		this.moves[2] = mv3;
		this.moves[3] = mv4;
		
		for (int i=0; i<4; i++) {
			this.maxpp[i] = this.moves[i].getPP();
			this.pp[i] = this.moves[i].getPP();
		}
	}
	
	public Move getMove (int i) {
		return moves[i];
	}
	
	public void replaceMove (Move mv, int n) {
		this.moves[n] = mv;
		this.maxpp[n] = mv.getPP();
		this.pp[n] = mv.getPP();
	}
}
