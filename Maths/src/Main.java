import java.util.Scanner;


public class Main {

	 public static void main(String[] args) {
		 
		 int l;
		 int c;
		 int nb;
		 int l2,c2,nb2;

		 Scanner scan=new Scanner(System.in);
		 
		 System.out.println("Nombre de lignes :");
		 l=scan.nextInt();
		 System.out.println("Nombre de colonnes :");
		 c=scan.nextInt();
		 
		 nb=c*l;
		 
		 Matrice matrice = new Matrice(l,c);

		 matrice.remplirMatrice(nb);
		 
		 matrice.parcourirMatrice(matrice,l,c,nb);
		 
		 
		 
		 
		 System.out.println("Nombre de lignes :");
		 l2=scan.nextInt();
		 System.out.println("Nombre de colonnes :");
		 c2=scan.nextInt();
		 
		 nb2=c2*l2;
		 
		 Matrice matrice2 = new Matrice(l2,c2);

		 matrice2.remplirMatrice(nb2);
		 
		 matrice2.parcourirMatrice(matrice2,l2,c2,nb2);
		 
		 
		 
		 
		 System.out.println("111111111111111111111111");
		 Matrice matriceProduit=new Matrice(0,0);
		 System.out.println("mmmmmmmmm");
		 
		 matriceProduit=matrice2.multiplier(matrice, nb, matrice2, nb2);
		 System.out.println("eeehh");

		 int mPLigne=matriceProduit.getLigne();
		 int mPColonne=matriceProduit.getColonne();
		 System.out.println("rrrr"+mPLigne);

		 scan.close();

	 }
}