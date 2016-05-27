import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
abstract class Tree {

int par;//détermine quel est le joueur qui joue à cette étape
public  HashMap< Tree,Memo> map;
int mapsize;
static int mapsizemax = 10000;
 long joueur;
 long opposant;



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
		System.out.println("alpha vaut "+alpha);
		System.out.println("beta vaut "+beta);
		return v;
	}
	else {
	
		int v=10;
		for (Tree c:this.children()) {
			v= Math.min(v, c.AlphaBeta(alpha, beta));
			beta=Math.min(beta,v);
			if (beta<=alpha) {System.out.println("breaking");break;}
		}
		System.out.println("alpha vaut "+alpha);
		System.out.println("beta vaut "+beta);
		return v;
	}

}


public int AlphaBetaMemoAux(int alpha,int beta,MyHash map) throws ExceptionEnd{
	final int alphaini=alpha;
	final int betaini=beta;
	
	final int UPPER=-1;
	final int LOWER=1;
	final int ACCURATE=0;
	Memo mv=map.get(this);
	if (mv!=null){//ie valeur mémoizée
	
		if (mv.intervalle==0){return mv.gain;}  //on avait calculé la valeur exacte
		if (mv.intervalle==LOWER){					//on avait un intervalle du type [a,+inf[ 
			if (mv.gain>alpha){return AlphaBetaMemoAux(mv.gain, beta, map);}	//où a était plus grand que alpha
		}
		if (mv.intervalle==UPPER){
			if (mv.gain<beta){return AlphaBetaMemoAux(alpha, mv.gain, map);}
		}
		
	}	
	
	if (this.end()) {return this.gain();}

	if (this.par==1) {
		
	int v=-10;
		for (Tree c:this.children()) {
			v= Math.max(v, c.AlphaBetaMemoAux(alpha, beta,map));
			alpha=Math.max(alpha,v);
			if (beta<=alpha) {break;}
		}
		int bound;
	
		
		if( v <= alphaini ) bound = UPPER;
		else{ if( v >= betaini ) bound = LOWER;
				else bound = ACCURATE;
		}
		map.put(this,new Memo(v,bound,0));
		return v;
	}
	else {
	
		int v=10;
		for (Tree c:this.children()) {
			v= Math.min(v, c.AlphaBetaMemoAux(alpha, beta,map));
			beta=Math.min(beta,v);
			if (beta<=alpha) {break;}
		}
		int bound;

		if( v <= alphaini ) bound = UPPER;
		else{ if( v >= betaini ) bound = LOWER;
				else bound = ACCURATE;
		}
		map.put(this,new Memo(v,bound,0));
		
		return v;
	}
	
}





public int AlphaBetaMemo (int alpha, int beta) throws ExceptionEnd {
	MyHash map=new MyHash(10000);	
	return AlphaBetaMemoAux(alpha, beta, map);

}



}
