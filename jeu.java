import entities.Matrice;
import entities.Cellule;
import graffities.*;
public class jeu {


    public static void main(String[] args) {
        Matrice matrice = new Matrice();
        Cellule[][] elMatrice = matrice.initialize(0);
        MatriceG.showMatrice(elMatrice);
        MatriceG.drawGridLayout(elMatrice);
    }
}