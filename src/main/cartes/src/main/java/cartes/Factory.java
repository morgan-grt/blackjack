package cartes;

import cartes.Carte.Couleur;
import cartes.Carte.Valeur;
import java.util.LinkedList;

public class Factory{
    private final LinkedList<Carte> hand ;
    private static final Valeur[] VAL52 = {Valeur.As, Valeur.Deux, Valeur.Trois
            , Valeur.Quatre, Valeur.Cinq, Valeur.Six, Valeur.Sept, Valeur.Huit
            , Valeur.Neuf, Valeur.Dix, Valeur.Valet, Valeur.Dame, Valeur.Roi};
    
    private static final Valeur[] VAL32 = {Valeur.As, Valeur.Sept, Valeur.Huit
            , Valeur.Neuf, Valeur.Dix, Valeur.Valet, Valeur.Dame, Valeur.Roi};
    
    private static final Couleur[] COLORS = {Couleur.Coeur, Couleur.Carreau
            , Couleur.Pique, Couleur.Trefle};
    
    /**
     * creer le paquet pour le nombre donne de carte
     * @param nbcarte nombre de carte dans le paquet
     */
    public Factory(int nbcarte){
        switch (nbcarte) {
            case 32:
                hand = paquet32();
                break;
            case 0:
                hand = new LinkedList();
                break;
            case 52:
                hand = paquet52();
                break;
            default:
                hand = paquet52();
                break;
        }
    }

    /**
     * creer un paquet de 52 cartes
     * @return le paquet de 52 cartes
     */
    public static LinkedList<Carte> paquet52(){
        LinkedList<Carte> paquet52 = new LinkedList();
        for(Couleur col : COLORS){
            for(Valeur val : VAL52){
                paquet52.add(new Carte(val.toString(),col.toString()));
            }
        }
        return paquet52;
    } 
    
    /**
     * creer un paquet de 32 cartes
     * @return le paquet de 32 cartes
     */
    public static LinkedList<Carte> paquet32(){
        LinkedList<Carte> paquet32 = new LinkedList();
        for(Couleur col : COLORS){
            for(Valeur val : VAL32){
                paquet32.add(new Carte(val.toString(),col.toString()));
            }
        }
        return paquet32;
    }    

    public LinkedList<Carte> getHand() {
        return hand;
    }
    
}
