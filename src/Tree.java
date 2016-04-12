
abstract class Tree implements Comparable<Tree> {
	int gain;
	
	public int compareTo(Tree that) {
	    return this.gain - that.gain;
	  }


abstract  int mini();
abstract  int maxi();
abstract int maxi2(int joueur);
abstract public Tree minimax(); //renvoient le coup gagnant mais peut être modifier pour juste renvoyer 
abstract public Tree negamax();// victoire, nul ou défaite
abstract public int AlphaBeta(int joueur);

}
