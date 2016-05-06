import java.util.Collection;
abstract class Tree {

	int par;//détermine quel est le joueur qui joue à cette étape

abstract public int  gain() throws ExceptionEnd;
abstract int win();
abstract Collection<? extends Tree> children();
abstract public boolean end();

 
int mini() throws ExceptionEnd {
	if (this.end()){return this.gain();}
	
	else{
	int g=2;
	Collection<? extends Tree> children=this.children();
	for (Tree c:children){
		//on retient le gain min d'en dessous
		int u=c.maxi();
		if (u<g){g=u;}
	}
	return g;
	}
}

 
int maxi() throws ExceptionEnd {
	if (this.end()){return this.gain();}
	else {
	int g=-2;
	//int g=-2; //ou -infini idem
	Collection<? extends Tree> children=this.children();
	//System.out.println("+1");
	for (Tree c:children){
		//System.out.println("+1");
		//on retient le gain min d'en dessous
		int u=c.mini();
		if (u>g){g=u;}
	}

	return g;

	}
}

 
public int minimax() throws ExceptionEnd {
	return this.maxi();
}

 
//for negamax! On contrôle si on est Joueur ou adversaire par la variable joueur qui vaut 1 si on est le joueur, -1 l'adversaire
//les valeurs "vues" par l'adversaire sont donc multipliées par -1 et il en cherche le max. 
int maxi_for_negamax() {
	if (this.end()){try {
		return this.gain();
	} catch (ExceptionEnd e) {
		// TODO Auto-generated catch block
		return 0;
	}}
	
	else{
	int g=-2;	
	Collection<? extends Tree> children=this.children();
	for (Tree child:children){
		
		int u=child.maxi_for_negamax();
		if (this.par*u>g) g=this.par*u;
	}
	g=this.par*g;
	//System.out.println("child:"+g);
	return g;
	}
}

 
public int negamax() {
	// TODO Auto-generated method stub
return this.maxi_for_negamax();
}


//Contrairement aux algo précédents, ici on ne modifie pas les gain en remontant l'arbre
// Pour l'instant de fonctionne pas

public int AlphaBeta(int alpha, int beta) {
	if (this.end()){try {
		return this.gain();    //normalement, ne va JAMAIS lancer l'exception puisqu'elle est lancé si non-end()
	} catch (ExceptionEnd e) {
		// TODO Auto-generated catch block
		return 0;
	}}
	else{
	int best = -2;
	int v;
	for (Tree c:this.children()) {
		v=-c.AlphaBeta(-beta,-alpha);
		if (v>best) {
			best=v;
			if (best>alpha) {
				alpha=best;
				if (alpha>=beta) {
					return(best);
				}
			}
		
		}
		
	}
	return(best);
	}
}

public int AlphaBetaTest(int alpha, int beta,int joueur) { 
	
	 int valcurr=-10000;
			
			if (joueur==-1){
			 valcurr=1000;
			 Collection<? extends Tree> children=this.children();
				for (Tree child:children){
					valcurr=Math.min(valcurr,child.AlphaBetaTest(alpha,beta,-joueur));
					if (alpha>=valcurr) return valcurr; //coupure alpha
					beta=Math.min(beta,valcurr);		
				}		
			}
			if (joueur==1){
			valcurr=-1000;
			 Collection<? extends Tree> children=this.children();
				for (Tree child:children){
					valcurr=Math.max(valcurr,child.AlphaBetaTest(alpha,beta,-joueur));
					if (valcurr>=beta) return valcurr; //coupure beta
					alpha=Math.max(alpha,valcurr);		
				}		
			}
			return valcurr;
		
	}


/*	public int coupureleo(int joueur) {
	
	
	int g=-2;	
	 Collection<Tree> children=this.children();
		for (Tree child:children){
		child.gain=child.coupureleo(-joueur);
		if (joueur*child.gain>g) g=joueur*child.gain;
		
		//lignes supps pour amélioration alpha-beta
		if (g==1){
			g=joueur*g;
			this.gain=g;
			return g;
		}
		//fin amélioration
		//grossièrement, on a court-circuité l'alglo,
		//si on atteint la meilleure valeur possible(ie 1 pour un node max, -1 pour un min, ie joueur*1, inutile de visiter le reste des fils
		
	}
	g=joueur*g;
	this.gain=g;
	
	return g;
	
	
	
	

}


*/

}
