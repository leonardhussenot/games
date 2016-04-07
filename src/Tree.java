
abstract class Tree implements Comparable<Tree> {
	int gain;
	
	public int compareTo(Tree that) {
	    return this.gain - that.gain;
	  }


abstract  int mini();
abstract  int maxi();
abstract int maxi2(int joueur);
abstract public Tree minimax();
abstract public Tree negamax();


}
