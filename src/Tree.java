import java.util.Collection;
abstract class Tree implements Comparable<Tree> {
	int gain;
	int par;//détermine quel est le joueur qui joue à cette étape
	
	
	public int compareTo(Tree that) {
	    return this.gain - that.gain;
	  }


abstract Collection<Tree> children();
abstract public boolean end();

 
int mini() {
	if (this.end()){return this.gain;
	}
	else{
	
	this.gain=2;
	Collection<Tree> children=this.children();
	for (Tree c:children){
		c.gain=c.maxi();
		//on retient le gain min d'en dessous
		if (c.gain<this.gain){this.gain=c.gain;}
	}
	return this.gain;
	}
}

 
int maxi() {
	if (this.end()){return this.gain;}
	else {
	this.gain=-2;
	//int g=-2; //ou -infini idem
	Collection<Tree> children=this.children();
	for (Tree c:children){
		c.gain=c.mini();
		//on retient le gain min d'en dessous
		if (c.gain>this.gain){this.gain=c.gain;}
	}
	//this.gain=g;
	//return g;
	return this.gain;
	}
}

 
public int minimax() {
	return this.maxi();
}

 
//for negamax! On contrôle si on est Joueur ou adversaire par la variable joueur qui vaut 1 si on est le joueur, -1 l'adversaire
//les valeurs "vues" par l'adversaire sont donc multipliées par -1 et il en cherche le max. 
int maxi_for_negamax() {
	if (this.end()){return this.gain;}
	
	else{
	int g=-2;	
	Collection<Tree> children=this.children();
	for (Tree child:children){
		child.gain=child.maxi_for_negamax();
		if (this.par*child.gain>g) g=this.par*child.gain;
	}
	g=this.par*g;
	this.gain=g;
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
	if (this.end()){return this.gain;}
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












}
