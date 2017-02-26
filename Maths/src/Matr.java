public class Matr
{
	private long[][] coeff = null;
	private int diametre = 0;
	private int distance = 0;
	
	//----------------------------------------------//
	//					   CONSTRUCTOR							//
	//----------------------------------------------//
	/** Constructeur Matr
	  * @param
	  * int i - ligne
	  * int j - colonne 
	  */
	public Matr(int i, int j)
	{
		this.setLength(i,j);
	}
	
	public Matr()
	{
		this(0,0);
	}

	public Matr(long[][] mat)
	{
		this.coeff = mat;
	}

	//----------------------------------------------//
	//					  		 SETTER					   	//
	//----------------------------------------------//	
	// dÃ©finit une Matr de type long[][]
	public void setMatr(long[][] mat)
	{
		this.coeff = mat;
	}
	
	// dÃ©finit une valeur Ã  la position i et j
	// i - ligne
	// j - col
	public void setValue(int i, int j, long value)
	{
		this.coeff[i][j] = value;
	}
	
	
	// on dÃ©finit la taille de la mtrice
	public void setLength(int i, int j)
	{
		this.coeff = new long[i][j];
	}
	
	
	//----------------------------------------------//
	//					  		 GETTER					   	//
	//----------------------------------------------//	
	// retourne la Matr sous forme du type long[][]
	public long[][] getMatr()
	{
		return this.coeff;
	}
	
	// retourne le nombre de ligne
	public int getRows()
	{
		return this.coeff.length;
	}
	
	// retourne le nombre de colonne
	public int getColumns()
	{
		return this.coeff[0].length;
	}
	
	// retourne la valeur Ã  la position i et j
	public long getValue(int i, int j)
	{
		return this.coeff[i][j];
	}
	
	// retourne le dÃ©terminant d'une Matr
	public long getDeterminant()
	{
		Matr a = null;
		long value = 0;
	
		if (this.getRows() < 3 && this.getColumns() < 3)
			return (this.getValue(0,0)*this.getValue(1,1) - this.getValue(1,0)*this.getValue(0,1));
		
		
		for (int j=0; j<this.getColumns(); j++)
		{
				a = this.getNewMatr(0,j);
				value += (int)Math.pow(-1,j)*(this.getValue(0,j)*a.getDeterminant());
		}
		
		return value;
	}
	
	// retourne la Matr inverse de la Matr this
	public Matr getMatrInverse()
	{
		Matr a = new Matr(this.getRows(), this.getColumns());
		Matr tmp = null;
		long det = this.getDeterminant();
		
		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
			{
				tmp = this.getNewMatr(i,j);
				a.setValue(i,j,(int)Math.pow(-1,i+j)*(tmp.getDeterminant()/det));
			}
			
		// on transpose la Matr les coeffcients seront positionnÃ© de faÃ§on incorrect
		return a.getMatrTranspose();
	}
	
	
	/* Retourne une nouvelle Matr mais en supprimant 
	 * la ligne row et la colonne columns
	 */
	private Matr getNewMatr(int row, int columns)
	{
		Matr a = new Matr(this.getRows()-1, this.getColumns()-1);
		int k = -1, m = 0;
		
		for (int i=0; i<this.getRows(); i++)
		{
			
			k++;
			
			if (i == row) 
			{	
				k--;
			 	continue;
			}

			m = -1;
			
			for (int j=0; j<this.getColumns(); j++)
			{
			
				m++;
							
				if (j==columns) 
				{
					m--;
					continue;					
				}
				
				a.setValue(k,m,this.getValue(i,j));
			}		

		}
		
		return a;
	}
	
	/**
	  * Retourne le nombre de combinaison a partir d'une Matr
	  * dÃ©finit a partir d'un graphe.
	  * @param 
	  * 	sA - sommet A
	  *	sB - sommet B
	  *	nb - Nombre de arrete (ou nombre de caractere du combinaison)
	  * @return
	  * 	long - nombre de combinaison possible entre 2 sommets
	  */
	public long getGrapheCombiCount(int sA, int sB, int nb)
	{
		if (sB > this.getRows() || sA > this.getColumns())
			return -1;
	
		Matr a = this.MatrPow(nb);
		
		return a.getValue(sB-1,sA-1);
	}
	
	
	// retourne la Matr I en fonction de la mtrice this
	public Matr getMatrIdentity()
	{
		Matr a = new Matr(this.getRows(),this.getColumns());
		
		for (int i=0; i<this.getRows(); i++)
			a.setValue(i,i,1);
		
		return a;		
	}
	
	// transpose la Matr 
	public Matr getMatrTranspose()
	{
		Matr a = new Matr(this.getColumns(), this.getRows());
		long tmp = 0;
		
		for (int i=0; i<a.getRows(); i++)
			for (int j=0; j<a.getColumns(); j++)
			{
				tmp = this.getValue(j,i);
				a.setValue(i,j,tmp);
			}
		
		
		return a;
	}
	
	// retourne la valeur de la trice de la Matr
	public long getTraceMatr()
	{
		long value = 0;
		
		for (int i=0; i<this.getRows(); i++)
			value += this.getValue(i,i);
		
		return value;
	}
	
	
	/** Retourne la distance (nombre d'arrete) entre 
	  * entre deux sommets sA et sB, tel que sA <= sB
	  * si sA > sB, mÃ©thode renvoi -1 pour erreur
	  */
	public long getDistanceGraphe(int sA, int sB)
	{
		long value = 0;
		
		if (sA > sB) 
			return -1;
		
		if (this.getValue(sB-1,sA-1) != 0)
			return (this.getValue(sB-1,sA-1));
		
		for (int i=sA; i<sB; i++)
			value += this.getValue(i-1, (i+1)-1);

		return value;
	}
	
	// GRAPHE
	// retourne la Matr de distance 
	public Matr getMatrDistanceGraphe()
	{
		Matr a = new Matr(this.getRows(),this.getColumns());
		int n=1;
		
		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
			{
				 if (a.getValue(i,j) == 0)
						a = this.MatrPow(n++);

			}

		// n-1 correspond a la distance 
		this.distance = n-1;
	
		return a;
	}
	
	// GRAPHE
	// retourne la distance de la Matr
	public int getDistance()
	{
		this.getMatrDistanceGraphe();
		return this.distance;
	}
	
	
	// retourne la Matr compagnon en fonction de la Matr this
	public Matr getMatrCompagnon()
	{
		Matr a = new Matr(this.getRows(),this.getColumns());
		
		for (int i=0; i<a.getRows()-1; i++)
			a.setValue(i+1,i,1);
		
			a.setValue(this.getRows()-1,this.getColumns()-1,-1);				
					
		return a;
	}
	
	
	// GRAPHE
	// retourne la Matr diam
	public Matr getMatrDiametre()
	{
		int n=1;
		Matr ai = this.sumMatr(this.getMatrIdentity());

		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
			{
				 if (ai.getValue(i,j) == 0)
						ai = this.MatrPow(n++);

			}
			
		this.diametre = n-1;
		
		return ai;
	}
	
	// GRAPHE
	// retourne la valeur du diametre de la Matr (ou graphe :))
	public int getDiametre()
	{
		this.getMatrDiametre();
		return this.diametre;
	}
	
	// GRAPHE
	// retourne les deux sommets les plus Ã©loignÃ©s
	public int[] getSommetPLusDistant()
	{
		int[] sommets = new int[2];
		Matr m = this.MatrPow(this.getDiametre()-1);
		byte n = 0;
		
		for (int i=0; i<m.getRows(); i++)
			for (int j=0; j<m.getColumns(); j++)
				if (m.getValue(i,j) == 0)
				{
					sommets[n++] = i+1;
				}
		
		return sommets;
	}
	
	//----------------------------------------------//
	//  		  			 OTHERS METHODS				   //
	//----------------------------------------------//	
	// multiplication
	public Matr multiply(final Matr Matr)
	{
		Matr a = new Matr(this.getRows(), this.getColumns());
		int k,i,j,m;
		long value = 0;
				
		for (k=0; k<this.getColumns(); k++)
		{
						
			for (i=0; i<this.getRows(); i++)
			{
			
				for (j=0; j<this.getColumns(); j++)
					value += this.getValue(i,j)*Matr.getValue(j,k);

				a.setValue(i,k,value);
				value = 0;
			}
		}
		
		return a;
	}
	
	// addition
	public Matr sumMatr(final Matr Matr)
	{
		Matr a = new Matr(this.getRows(), this.getColumns());
		
		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				a.setValue(i,j,this.getValue(i,j)+Matr.getValue(i,j));
			
		return a;
	}
	
	// puissance -> M^n
	public Matr MatrPow(int n)
	{
		Matr a = this;
		Matr b = a;
		
		for (int i=0; i<n-1; i++)
			b = a.multiply(b);
			
		return b;
	}
	
	// soustraction
	public Matr soustraction(final Matr Matr)
	{
		Matr a = new Matr(this.getRows(), this.getColumns());
		
		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				a.setValue(i,j,this.getValue(i,j)-Matr.getValue(i,j));
			
		return a;	
	}
	
	// multiplication d'une Matr par une constante k
	public Matr multiplyByK(long k)
	{
		Matr a = this;
		
		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				a.setValue(i,j,this.getValue(i,j)*k);
		
		return a;
	}
	
	
	// division d'une Matr par une constante k
	public Matr divByK(long k)
	{
		Matr a = this;
		
		for (int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				a.setValue(i,j,this.getValue(i,j)/k);
		
		return a;
	}
	
	// le fameux toString() :)
	public String toString()
	{
		String out = "";
	
		for (int i=0; i<this.getRows(); i++)
		{
			for (int j=0; j<this.getColumns(); j++)
				out +=this.coeff[i][j]+"\t ";
				
				out+="\n";
		}
				
		return out;
	}
	
	
	// definit si deux Matrs sont Ã©quivalentes
	public boolean equals(Matr Matr)
	{
		for(int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				if (this.getValue(i,j) != Matr.getValue(i,j))
						return false;
		
		return true;
	} 
	
	//----------------------------------------------//
	//	   		  		 METHODS IS...					   //
	//----------------------------------------------//	
	// dÃ©termine si la Matr est symetrique
	public boolean isSymetric()
	{
		if (this.getRows() == this.getColumns())
			return false;
			
		for(int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				if (this.getValue(i,j) != this.getValue(j,i))
						return false;
						
		return true;				
	}
	
	// dÃ©termine si la Matr est triangulaire
	public boolean isTriangularMatrix()
	{
		for(int i=0; i<this.getRows(); i++)
			for (int j=1; j<this.getColumns(); j++)
				if (this.getValue(i,j) != 0)
						return false;		
		
		return true;
	}
	
	// dÃ©termine si la Matr est une Matr unitÃ©
	public boolean isUnitMatrix()
	{		
		return (this.equals(this.getMatrIdentity()));
	}
	
	// dÃ©termine si la Matr est inversible
	public boolean isInversible()
	{
		return (this.getDeterminant() != 0);
	}
	
	// determine si la mtrice contient au moins une valeur 0
	public boolean isZero()
	{
		for(int i=0; i<this.getRows(); i++)
			for (int j=0; j<this.getColumns(); j++)
				if (this.getValue(i,j) == 0)
						return true;			
						
		return false;
	}
	
	//----------------------------------------------//
	//	   		  	 LAUNCH PROGRAM					   //
	//----------------------------------------------//
	public static void main(String[] args)
	{
			// Matr d'adjacence d'un graphe
			Matr a = new Matr();
			a.setMatr(new long[][] { {0,1,1,0,0,0,0,0,0,0,0}, // 1
												 {1,0,1,1,1,0,0,0,0,0,0}, // 2
												 {1,1,0,0,1,0,0,0,0,0,0}, // 3
												 {0,1,0,0,0,1,0,0,0,0,0}, // 4
												 {0,1,1,0,0,0,1,0,0,0,0}, // 5
												 {0,0,0,1,0,0,1,1,1,0,0}, // 6
												 {0,0,0,0,1,1,0,0,0,1,0}, // 7
												 {0,0,0,0,0,1,0,0,1,0,1}, // 8
												 {0,0,0,0,0,1,0,1,0,1,1}, // 9
												 {0,0,0,0,0,0,1,0,1,0,1}, // 10
												 {0,0,0,0,0,0,0,1,1,1,0}  // 11
												});
																
		//System.out.println("Matr A : \n"+a);				
		//System.out.println("Distance : \n"+a.MatrPow(4));
		
		Matr x = new Matr(new long[][] { {0,1,0,0}, {1,0,0,0}, {0,0,1,1},{0,0,0,1}});
		System.out.println("Det : \n" + x.getDeterminant());
		System.out.println("Matr inverse : \n" + x.getMatrInverse());

	}
}