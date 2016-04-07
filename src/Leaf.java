
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
		// TODO Auto-generated method stub
		 return this.gain;
	
	}

	@Override
	public Tree negamax() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
