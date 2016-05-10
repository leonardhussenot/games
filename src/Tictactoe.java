

import java.util.Collection;
import java.util.HashSet;

public class Tictactoe extends Tree{
	long joueur;
	long opposant;
	final int k, L, H;
	final long full;

	public Tictactoe(long j, long o,int k, int L, int H,int p) {
		this.opposant=o;
		this.joueur=j;
		this.k=k;
		this.L=L;
		this.H=H;
		this.par=p;
		long g= (1<<(L*(H+1)-1))-1;
		for (int i=1;i<L;i++){
			g=g-(1 <<( i*(H+1)-1));
		}
		this.full=g;
		
	}

	@Override
	public Collection<? extends Tree> children() {
		HashSet<Tictactoe> result = new HashSet<Tictactoe>();
		if (this.end()) {return result ; 
		}
		else {
			long tout= joueur | opposant;
			for (int i=0;i<this.L*(this.H+1);i++) {
				if (i%(H+1)==H) {} //on ne fait rien sur la ligne qu'on a ajoutée pour le calcul de fin
				else {
					//System.out.println(i);
					if (tout%2==0) {
						if (this.par==1) {
						Tictactoe e=new Tictactoe(this.joueur + ((long) 1<<i),this.opposant,k, L, H, -this.par);
							result.add(e);

						} 
						else {
							Tictactoe e=new Tictactoe(this.joueur ,this.opposant + ((long) 1<<i),k, L, H, -this.par);
							result.add(e);
							
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
		return (this.win()!=0||(joueur|opposant)==full);
	}

	@Override
	public int gain() throws ExceptionEnd {
		if (end()) {return win();}
		else {throw new ExceptionEnd();}
	}


	int win_hauteur() {
		long resultj=this.joueur;//on regarde si le joueur gagne
		long resulto=this.opposant;//on regarde si l'opposant gagne
		for (int i=1;i<k;i++) {
			resultj=resultj&(this.joueur >> i);
			resulto=resulto& (this.opposant >> i);
		}
		if (resultj>0) {
			return 1;
		}
		else if (resulto>0) {
			return -1;
		}
		else {return 0;}
	}
	int win_lateral() {
		long resultj=this.joueur;//on regarde si le joueur gagne
		long resulto=this.opposant;//on regarde si l'opposant gagne
		for (int i=1;i<k;i++) {
			resultj=resultj&(this.joueur >> (H+1)*i);
			resulto=resulto& (this.opposant >> (H+1)*i);
		}
		if (resultj>0) {
			return 1;
		}
		else if (resulto>0) {
			return -1;
		}
		else {return 0;}
	}
	int win_diag1() {
		long resultj=this.joueur;//on regarde si le joueur gagne
		long resulto=this.opposant;//on regarde si l'opposant gagne
		for (int i=1;i<k;i++) {
			resultj=resultj&(this.joueur >> (H+2)*i);
			resulto=resulto& (this.opposant >> (H+2)*i);
		}
		if (resultj>0) {
			return 1;
		}
		else if (resulto>0) {
			return -1;
		}
		else {return 0;}
	}
	int win_diag2() {
		long resultj=this.joueur;//on regarde si le joueur gagne
		long resulto=this.opposant;//on regarde si l'opposant gagne
		for (int i=1;i<k;i++) {
			resultj=resultj&(this.joueur >> (H)*i);
			resulto=resulto& (this.opposant >> (H)*i);
		}
		if (resultj>0) {
			return 1;
		}
		else if (resulto>0) {
			return -1;
		}
		else {return 0;}
	}

	@Override
	int win() {
		if (win_hauteur()==1 || win_lateral()==1 || win_diag1()==1 || win_diag2()==1) {return 1;}
		if (win_hauteur()==-1 || win_lateral()==-1 || win_diag1()==-1 || win_diag2()==-1) {return -1;}
		else {return 0;}
	}
	
	
}