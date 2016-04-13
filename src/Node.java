import java.util.Collection;


public class Node extends Tree{

	Collection<Tree> children;


	Node(Collection<Tree> t){
		gain=-2;
		children=t;
	}

	@Override
	int mini() {
		int g=2;
		for (Tree c:this.children){
			c.gain=c.maxi();
			//on retient le gain min d'en dessous
			if (c.gain<g){g=c.gain;}
		}
		this.gain=g;
		return g;
	}

	@Override
	int maxi() {

		int g=-2; //ou -infini idem
		for (Tree c:this.children){
			c.gain=c.mini();
			//on retient le gain min d'en dessous
			if (c.gain>g){g=c.gain;}
		}
		this.gain=g;
		return g;
	}

	@Override
	public Tree minimax() {
		int g=this.maxi();

		for (Tree child:children){
			if (child.gain==g){return child;}
		}

		return null;
	}

	@Override
	//for negamax! On contrôle si on est Joueur ou adversaire par la variable joueur qui vaut 1 si on est le joueur, -1 l'adversaire
	//les valeurs "vues" par l'adversaire sont donc multipliées par -1 et il en cherche le max. 
	int maxi2(int joueur) {
		int g=-2;	
		for (Tree child:children){
			child.gain=child.maxi2(-joueur);
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
		 int res=this.maxi2(1);
		 
		 for (Tree child:children){
				if (child.gain==res){return child;}
			}

			return null;
	}

	
	
	//Contrairement aux algo précédents, ici on ne modifie pas les gain en remontant l'arbre
	// Pour l'instant de fonctionne pas
	
	public int AlphaBetaTest(int alpha, int beta,int joueur) { 
		
 int valcurr=-10000;
		
		if (joueur==-1){
		 valcurr=1000;
			for (Tree child:this.children){
				valcurr=Math.min(valcurr,child.AlphaBetaTest(alpha,beta,-joueur));
				if (alpha>=valcurr) return valcurr; //coupure alpha
				beta=Math.min(beta,valcurr);		
			}		
		}
		if (joueur==1){
		valcurr=-1000;
			for (Tree child:this.children){
				valcurr=Math.max(valcurr,child.AlphaBetaTest(alpha,beta,-joueur));
				if (valcurr>=beta) return valcurr; //coupure beta
				alpha=Math.max(alpha,valcurr);		
			}		
		}
		return valcurr;
	
}
	

	
	
	
	/*algo en dessous pas vraiment alphabeta en réalité mais une amélioration possible qui exploite le fait que les gains 
	 de nos jeux sont juste -1 0 ou 1	 */
	
	
	/* Autre tentative, qui est plus dans la mentalité de ce que j'ai fait avant
	 Si le joueur vaut 1, il doit maximiser, et si un de ses fils a un gain de &, inutile de visiter les autres,
	le sien est forcement 1
 	De meme, si joueur vaut -1, ie le noeud doit minimiser son gain, alors, si un de ses fils a un noeud de gain -1, inutile
	d'aller visiter les autres. */
	

	
	//En fait c'est pas alpha beta mais c'est quand même interessant
	//Je reprends l'algo fait pour negamax en l'améliorant simplement
	@Override
	public int coupureleo(int joueur) {
		
		
		int g=-2;	
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
