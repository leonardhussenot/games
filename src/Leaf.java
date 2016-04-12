
 public class Leaf extends Tree{

	public Leaf (int n){
		this.gain=n;
	}

	@Override
	int mini() {
		return gain;
	}

	@Override
	int maxi() {
		return gain;
	}

	@Override
	public Tree minimax() {
		return this;
	}

	@Override
	int maxi2(int joueur) {
		 return this.gain;
	
	}

	@Override
	public Tree negamax() {
		return null;
	}

	@Override
	public int AlphaBeta(int joueur) {
		System.out.println("visited leaf +1");
		return this.gain;
	
	}

	
}
