import java.util.Collection;


public abstract class Node extends Tree{

	abstract Collection<Tree> children();
	
	


	Node(Collection<Tree> t){
		gain=-2;
		
	}

	@Override
	int mini() {
		this.gain=2;
		Collection<Tree> children=this.children();
		for (Tree c:children){
			c.gain=c.maxi();
			//on retient le gain min d'en dessous
			if (c.gain<this.gain){this.gain=c.gain;}
		}
		return this.gain;
	}

	@Override
	int maxi() {
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

	@Override
	public Tree minimax() {
		int g=this.maxi();

		Collection<Tree> children=this.children();
		for (Tree child:children){
			if (child.gain==g){return child;}
		}

		return null;
	}

	@Override
	//for negamax! On contrôle si on est Joueur ou adversaire par la variable joueur qui vaut 1 si on est le joueur, -1 l'adversaire
	//les valeurs "vues" par l'adversaire sont donc multipliées par -1 et il en cherche le max. 
	int maxi_for_negamax(int joueur) {
		int g=-2;	
		Collection<Tree> children=this.children();
		for (Tree child:children){
			child.gain=child.maxi_for_negamax(-joueur);
			if (joueur*child.gain>g) g=joueur*child.gain;
		}
		g=joueur*g;
		this.gain=g;
		//System.out.println("child:"+g);
		return g;
	}

	@Override
	public Tree negamax() {
		// TODO Auto-generated method stub
		 int res=this.maxi_for_negamax(1);
		 
		 Collection<Tree> children=this.children();
			for (Tree child:children){
				if (child.gain==res){return child;}
			}

			return null;
	}

	
	
	//Contrairement aux algo précédents, ici on ne modifie pas les gain en remontant l'arbre
	// Pour l'instant de fonctionne pas
	@Override
	public int AlphaBeta(int alpha, int beta) {
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
	/*
	public int AlphaBetaTest(int alpha, int beta,int joueur) { 
		
 int valcurr=-10000;
		
		if (joueur==-1){
		 valcurr=1000;
		 Collection<Tree> children=this.children();
			for (Tree child:children){
				valcurr=Math.min(valcurr,child.AlphaBetaTest(alpha,beta,-joueur));
				if (alpha>=valcurr) return valcurr; //coupure alpha
				beta=Math.min(beta,valcurr);		
			}		
		}
		if (joueur==1){
		valcurr=-1000;
		 Collection<Tree> children=this.children();
			for (Tree child:children){
				valcurr=Math.max(valcurr,child.AlphaBetaTest(alpha,beta,-joueur));
				if (valcurr>=beta) return valcurr; //coupure beta
				alpha=Math.max(alpha,valcurr);		
			}		
		}
		return valcurr;
	
}
	
*/
	
	
	
	/*algo en dessous pas vraiment alphabeta en réalité mais une amélioration possible qui exploite le fait que les gains 
	 de nos jeux sont juste -1 0 ou 1	 */
	
	
	/* Autre tentative, qui est plus dans la mentalité de ce que j'ai fait avant
	 Si le joueur vaut 1, il doit maximiser, et si un de ses fils a un gain de &, inutile de visiter les autres,
	le sien est forcement 1
 	De meme, si joueur vaut -1, ie le noeud doit minimiser son gain, alors, si un de ses fils a un noeud de gain -1, inutile
	d'aller visiter les autres. */
	

	
	//En fait c'est pas alpha beta mais c'est quand même interessant
	//Je reprends l'algo fait pour negamax en l'améliorant simplement

	
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
