import java.util.Collection;
import java.util.Stack;


public class Test {

	public static void main(String[] args) throws ExceptionEnd {
		
	
	long bid = 5;
	long bid2 = 2;
	long t=16;
	
	
	long truc = bid | bid2;
	//Tictactoe essai = new Tictactoe( 609,1046 ,3, 3, 3, 1); //match nul
	//Tictactoe essai = new Tictactoe(82,2084,3, 3, 3, 1); //défaite
	//Tictactoe essai = new Tictactoe( 0,0,3, 3, 3, 1); //match nul
	//Tictactoe essai = new Tictactoe( 0,7 ,4, 4, 4, -1); //match nul
	
	//System.out.println(essai.win());
	//System.out.println(essai.end());


	//System.out.println("le résultat par minimax est "+essai.minimax());
	//System.out.println("le résultat par negamax est "+essai.negamax());
	//System.out.println("le résultat par Alpha-Beta est "+essai.AlphaBeta(-100, 100));

	Tictactoe e=new Tictactoe(66625,34850,4,4,4,1);
	Puissance4 p=new Puissance4(430081,68614,4,4,-1);	

	
	
//	System.out.println(p.win());
	//System.out.println(p.end());

	System.out.println("le résultat par Alpha-Beta est "+p.AlphaBeta(-100, 100));
	
	//test case dispo puissance 4
/*	long tout=3;
	boolean caseplusbasse=true;
	for (int i=0;i<4;i++){
		caseplusbasse=true;	
		
		for (int j=(4+1)*i;j<(4+1)*i+4;j++){
			
			if (tout%2==0&&caseplusbasse){						
				
				System.out.println("une case atteignable est "+j);
				//TODO : ajouter fils correspondant
				caseplusbasse=false;
			}
			tout= tout >> 1;
		}
		tout=tout >> 1 ;   // on saute la ligne supérieure (la boucle sur j s'arrete avant à chaque f	
		
	}
	*/
	long full=  1 << (6-1);

	for (int i=2;i<=7;i++){
		full=full+(1 << ( i*(6+1)-2));	
	}
	//System.out.println(full);
	
	
	
	
	// Avec la dernière modif : classe Node abstraite qui possède une méthode children() pour calculer les coups suivants possibles, 
	//construire un arbre test devient différent ! 
	
	//TODO :refléchir pour les tests à comment implémenter ca...
	
	/*
	Node n1=new Node(f1);
	Node n2=new Node(f2);
	Node n4=new Node(f4);
	
	Collection<Tree> f3 = new Stack<Tree>();
	f3.add(n1);
	f3.add(n2);
	Node n3=new Node(f3);
	Collection<Tree> b = new Stack<Tree>();
	b.add(n3);
	b.add(n4);
	
	Node boss=new Node(b);
	
	int res=boss.AlphaBetaTest(-4,4,1);
	System.out.println(res);
	
	
	*/	 	  	
	
			

}
	
}

