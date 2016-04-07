import java.util.Collection;
import java.util.Stack;


public class Test {

	public static void main(String[] args) {
		
	Leaf ps1= new Leaf(0);
	Leaf ps2= new Leaf(0);
	Leaf ps3= new Leaf(1);
	Leaf ps4= new Leaf(-1);
	Leaf ps5= new Leaf(1);
	Leaf ps6= new Leaf(-1);
		
	Collection<Tree> f1 = new Stack<Tree>(); 
	Collection<Tree> f2 = new Stack<Tree>(); 
	Collection<Tree> f4 = new Stack<Tree>(); 
	f1.add(ps1);
	f1.add(ps2);
	f2.add(ps3);
	f2.add(ps4);
	f4.add(ps5);
	f4.add(ps6);
	
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
	boss.negamax();
	System.out.println(boss.gain);
	
	
			
	}

}
