import java.util.Collection;
import java.lang.Math;
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


/*
01 function alphabeta(node, depth, α, β, maximizingPlayer)
02      if depth = 0 or node is a terminal node
03          return the heuristic value of node
04      if maximizingPlayer
05          v := -∞
06          for each child of node
07              v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
08              α := max(α, v)
09              if β ≤ α
10                  break (* β cut-off *)
11          return v
12      else
13          v := ∞
14          for each child of node
15              v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
16              β := min(β, v)
17              if β ≤ α
18                  break (* α cut-off *)
19          return v
*/

public int AlphaBeta (int alpha, int beta) throws ExceptionEnd {

	if (this.end()) {return this.gain();}
	if (this.par==1) {
	int v=-10;
		for (Tree c:this.children()) {
			v= Math.max(v, c.AlphaBeta(alpha, beta));
			alpha=Math.max(alpha,v);
			if (beta<=alpha) {break;}
		}

		return v;
	}
	else {
	
		int v=10;
		for (Tree c:this.children()) {
			v= Math.min(v, c.AlphaBeta(alpha, beta));
			alpha=Math.min(beta,v);
			if (beta<=alpha) {break;}
		}

		return v;
	}

}

    
    // Deux tentatives de alpha-beta qui ne fonctionnent pas après des tests simples

/*public int AlphaBeta(int alpha, int beta) throws ExceptionEnd {
	if (this.end()){
		return this.gain();
	}
	else{
	int best = -10;
	int v;
	for (Tree c:this.children()) {
		v=-c.AlphaBeta(-beta,-alpha);
		if (v>best) {
			best=v;
		}
		if (best>alpha) {
				alpha=best;
		}
		if (alpha>=beta) {
					break;
		}
	}
	return(best);
	}
}*/
    /*

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

*/

}
