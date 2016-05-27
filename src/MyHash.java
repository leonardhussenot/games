
public class MyHash {
static Tree[] keys;
static Memo[] values;
static int length;
public MyHash(int n){
	keys=new Tree[n];
	values=new Memo[n];
	length=n;
}


public  int hachage(Tree t){
	
	 long h= (t.joueur+239)*(t.joueur+311)+197*(t.opposant+1031)*(t.opposant+1429)*(t.opposant+1777);
	 h=h % length;
	 if (h<0) {h+=length;}
	 return (int) h;
	
}


public void put( Tree t, Memo m){
	int h=hachage(t);
	if (keys[h]==null){
		keys[h]=t;
		values[h]=m;
	}
	else{
		if (m.labour>values[h].labour){
			keys[h]=t;
			values[h]=m;			
		}
	}
}

public Memo get(Tree t){
	int h=hachage(t);
	if (keys[h]==null){return null;}
	if (keys[h].joueur==t.joueur&&keys[h].opposant==t.opposant&&keys[h].par==t.par){
		return values[h];
	}
	if (keys[h].joueur==t.opposant&&keys[h].opposant==t.joueur&&keys[h].par==-t.par){
		return new Memo(-values[h].gain,-values[h].intervalle,values[h].labour);
	}
	
	else return null;

}

}
