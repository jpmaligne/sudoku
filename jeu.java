import entities.Matrice;
public class jeu extends Matrice {


    public static void main(String[] args) {
        System.out.println("test");
        Matrice matrice = new Matrice();
        // System.out.println(matrice[0][1]);
        matrice.initialize(0);
        matrice.showMatrice();

    }
}