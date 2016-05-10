
public class Brouillons {
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
