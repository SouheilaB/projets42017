import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


public class Matrice {
	
	private int ligne;
	private int colonne;
	private HashMap<Integer, Integer> hm1;
	private int i=0;
	private int j;
	private int res=0;
	private Scanner scan=new Scanner(System.in); 
	 
	
	public Matrice(int ligne, int colonne) 
	{
		this.ligne = ligne;
		this.colonne = colonne;
		this.hm1 = new HashMap<>();
	}
	
	
	

	public void remplirMatrice(int nb)
	{
		  i=0;
	      while(i<nb){
	    	  System.out.println("Veuillez saisir un nombre à insérer à la suite dans la matrice ("+(nb-i)+" restants) :");
	    	  res=scan.nextInt();
	    	  if (res!=0)
	    		  hm1.put(i,res);
	    	  else
	    		  hm1.put(i, null);
	    	  i++;
	      }
      
     }
	Object x; 
	  public void parcourirMatrice(Matrice mat,int l,int c, int nb){
		  System.out.println("Matrice : ");  
			Set<Entry<Integer, Integer>> setHm = mat.hm1.entrySet();
		      Iterator<Entry<Integer, Integer>> it = setHm.iterator();
		      i=1;
		      j=1;
		      String a="";
		      while(it.hasNext()){
		         Entry<Integer, Integer> e = it.next();  
		        
		         if(e.getValue()==null)
		        	 a+=0;
		         else
		        	 a+=e.getValue();
	
		         while(j<c) { 
		        	x=it.next().getValue();
		        	if(x==null)
		        		a+="  "+ 0;
		        	else
		        		a+="  "+ x;

		        		
		       	 	j++;
		         }
		         System.out.println(a);
		         j=1;
		         a="";	       	 
		         if(i==l){
		        	 System.out.println("\n");
		        	 i=1;
		         }
		         i++;
		      }
		  
	  }
	public Matrice multiplier(Matrice m1, int taille1, Matrice m2, int taille2){
		
		Set<Entry<Integer, Integer>> setHm1 = m1.hm1.entrySet();
	    Iterator<Entry<Integer, Integer>> it1 = setHm1.iterator();
	    
		Set<Entry<Integer, Integer>> setHm= m2.hm1.entrySet();
	    Iterator<Entry<Integer, Integer>> it2 =setHm.iterator();
	   
	   
	    
	    int value=0;
	    
	   /* if(taille1>taille2){
	    	m2=gonflerMatrice(m2,taille1);		
	    }
	    if( taille2>taille1){
	    	m1=gonflerMatrice(m1,taille2);		
	    }*/
		 Matrice matriceProduit = new Matrice(m1.getLigne(),m1.getColonne());
			int j;
			
			int c=0;
			j=1;
			int x=0;
			int y=0;
			int k=0;
			y =it1.next().getValue(); 	  
			x =it2.next().getValue();
			value = y*x;
			
			System.out.println("valueeee    "+value);
			System.out.println(" y "+y);
			System.out.println(" x "+x);
			while(it1.hasNext() && it2.hasNext()){
				for (i=0; i < m1.getColonne(); i++)
				{	for(int u=0; u<m1.getLigne();u++){		
					//System.out.println("it1 === "+it1.next().getValue());
					//System.out.println("it2 === "+it2.next().getValue());
					if(it1.hasNext())
						y =it1.next().getValue();
					while(j <= m2.getColonne()&&it2.hasNext())
					{	
						//System.out.println("it1 === "+it1.next().getValue());
						//System.out.println("it2 === "+it2.next().getValue());
						System.out.println(" y "+y);
						System.out.println(" x "+x);				
						x =it2.next().getValue();
						j++;
					}
						
						//if(it2.hasNext())
							//x =it2.next().getValue();
						System.out.println("pk x =,   "+x+"et y ==  "+y);
						value += y*x;
						System.out.println("valueeee    "+value);
						matriceProduit.hm1.put(c,value);
						c++;  		
						j=0;
				}
				}
			}
			
			System.out.println("nombre de ligne produit  "+matriceProduit.getLigne()+"   nombre colonne produit    "+matriceProduit.getColonne());
			
			parcourirMatrice(matriceProduit,matriceProduit.getLigne(),matriceProduit.getColonne(),matriceProduit.getLigne()*matriceProduit.getColonne());
			
			return matriceProduit;		
	}
	 
	public Matrice gonflerMatrice(Matrice h, int taille){
		
		int nbCol=this.getColonne();
		
		
		
		//je veux des quon arrive au nbCol on rajoute des 0 jusqu'a taille
		//et on recommence jusqua la fin de la matrice
		//ensuite on remplace le dernier chiffre de la matrice par 1
		
		return h;
	}
	
	
    public int getLigne() {
  		return ligne;
  	}
  	public void setLigne(int ligne) {
  		this.ligne = ligne;
  	}
  	public int getColonne() {
  		return colonne;
  	}
  	public void setColonne(int colonne) {
  		this.colonne = colonne;
  	}
  	
  	public static int [] euclideBezout(int a, int b) {
		int x = Math.abs(a);
		System.out.println("a="+x);
		// x=a si a>=0, -a sinon
		
		int y = Math.abs(b);
		System.out.println("b="+y+"\n");
		// y=b si b>=0, -b sinon
		
		int [] triplet = new int[3];
		
		// Dans l'itération précédente, x%y = 0
		if (y == 0) {
			triplet[0] = x;
			triplet[1] = 1;
			triplet[2] = 0;
			
			// triplet[PGCD(a,b),1,0]
			System.out.println("F) "+Arrays.toString(triplet));
			
		} else {
			triplet = euclideBezout(y,x%y);
			
			// triplet[PGCD(a,b),Un,Vn]
			System.out.println("A) "+Arrays.toString(triplet));
			
			int u = triplet[2];
			int v = triplet[1] - (x/y) * triplet[2];
			triplet[1] = u;
			triplet[2] = v;
			
			// triplet[PGCD(a,b),Un+1,Vn+1], soit les u et v des lignes supérieures
			System.out.println("B) "+Arrays.toString(triplet)); 
			
		}
		
		// On ommet les signes tout au long de l'algorithme,
		// et on les replace à la fin.
		// Cette opération ne sera effectuée au plus qu'une seule fois.
		if(a<0) triplet[1] = -triplet[1];
		if(b<0) triplet[2] = -triplet[2];
		
		// Resultat final, sous la forme [d,u,v]
		// Avec	d = PGCD(a,b)
		//		u = +/- Un+1, selon le signe de a
		//		v = +/- Un+1, selon le signe de b
		// Note: le PGCD peut être récupéré à n'importe quelle itération
		System.out.println("C) "+Arrays.toString(triplet)+"\n");

		return triplet;
		
	}

}