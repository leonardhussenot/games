import java.util.Collection;
import java.util.HashSet;

public class Puissance4 extends Tree{

	final int L, H;
	final long full;  //ici il suffit de voir si toutes les cases du haut sont remplies
	
	
	public Puissance4(long j, long o, int L, int H,int p) {
		this.opposant=o;
		this.joueur=j;
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
	public boolean end() {
		return (this.win()!=0||(joueur|opposant)==full);
	}
	
	
	@Override
	public int gain() throws ExceptionEnd {
		if (end()) {return win();}
		else {throw new ExceptionEnd();}
	}

	public Collection<? extends Tree> children() {//les cases dispo ont été testé, c'est OK, reste ajout dans children
		HashSet<Puissance4> result = new HashSet<Puissance4>();
		if (this.end()) {return result ; 
		}
		else {
			long tout= joueur | opposant;
			boolean caseplusbasse=true;
			for (int i=0;i<this.L;i++){
				caseplusbasse=true;	
				
				for (int j=(this.H+1)*i;j<(this.H+1)*i+this.H;j++){
					
					if (tout%2==0&&caseplusbasse){						
						// puis ajout fils correspondant
						if (this.par==1) {
							Puissance4 e=new Puissance4(this.joueur + ((long) 1<<j),this.opposant, L, H, -this.par);
								result.add(e);
								

							} 
							else {
								Puissance4 e=new Puissance4(this.joueur ,this.opposant + ((long) 1<<j), L, H, -this.par);
								result.add(e);
								
							}
			
						
						caseplusbasse=false;
					}
					tout= tout >> 1;
				}
				tout=tout >> 1 ;   // on saute la ligne supérieure (la boucle sur j s'arrete avant à chaque f	
				
			}
	
		
			return result;
		}
		}	
	


	int win_hauteur() {
		long resultj=this.joueur;//on regarde si le joueur gagne
		long resulto=this.opposant;//on regarde si l'opposant gagne
		for (int i=1;i<4;i++) {
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
		for (int i=1;i<4;i++) {
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
		for (int i=1;i<4;i++) {
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
		for (int i=1;i<4;i++) {
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
