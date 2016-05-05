import java.util.Collection;
import java.util.HashSet;

public class Tictactoe extends Tree{
	long joueur;
	long opposant;
	final int k, L, H;

	public Tictactoe(long j, long o,int k, int L, int H,int p) {
		this.opposant=o;
		this.joueur=j;
		this.k=k;
		this.L=L;
		this.H=H;
		this.par=p;
	}

	@Override
	public Collection<Tree> children() {
		HashSet<Tree> result = new HashSet<Tree>();
		if (this.end()) {return result ; 
		}
		else {
			long tout= joueur | opposant;
			for (int i=0;i<this.L*(this.H+1);i++) {
				if (i%H==0) {} //on ne fait rien sur la ligne qu'on a ajoutée pour le calcul de fin
				else {
					if (tout%2==0) {
						if (this.par==1) {
							result.add(new Tictactoe(this.joueur & ((long) 1<<i),this.opposant,k, L, H, -this.par));

						} 
						else {
							result.add(new Tictactoe(this.joueur ,this.opposant& ((long) 1<<i),k, L, H, -this.par));
						}
					}
				}
				tout=tout >>1;	
			}
			return result;
		}
	}

	@Override
	public boolean end() {
		// TODO Auto-generated method stub
		return false;
	}
}