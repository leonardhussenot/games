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

		int g=-2;
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


}
