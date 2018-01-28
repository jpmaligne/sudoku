import entities.Matrice;
import entities.Cellule;
import graffities.*;
public class jeu {


    public static void main(String[] args) {
        System.out.println("test");
        Matrice matrice = new Matrice();
        Cellule[][] elMatrice = matrice.initialize(0);
        MatriceG.showMatrice(elMatrice);
    }
}