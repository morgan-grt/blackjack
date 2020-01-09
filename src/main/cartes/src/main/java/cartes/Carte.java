package cartes;

public class Carte {
    protected final String valeur;
    protected final String couleur;
    protected String NAME;
    
    public Carte(String valeur,String couleur){
        this.valeur = valeur;
        this.couleur = couleur;
        this.NAME = valeur + "_" + couleur;
    }
    
    /**
     * permet de donner des noms aux valeurs
     */
    public enum Valeur {
        As("As"),
        Deux("2"),
        Trois("3"),
        Quatre("4"),
        Cinq("5"),
        Six("6"),
        Sept("7"),
        Huit("8"),
        Neuf("9"),
        Dix("10"),
        Valet("V"),
        Dame("D"),
        Roi("R");
        
        public String val;  // constructeur
        Valeur (String v){val = v;}
        public String getValue(){
            return val;
        }
    }
    
    /**
     * permet de donner des noms aux couleurs
     */
    public enum Couleur {
        Coeur("coeur"),
        Carreau("carreau"),
        Pique("pique"),
        Trefle("trefle");
        
        public String groupe;
        Couleur (String c){groupe = c;}
        public String getGroup(){
            return groupe;
        }
    }

    public String getValeur() {
        return valeur;
    }

    public String getCouleur() {
        return couleur;
    }
    
    public String getName(){
        return NAME;
    }

    @Override
    public String toString(){
        return this.valeur + " de "+ this.couleur+".";
    }
    
    public Carte getCopy(){
        return new Carte(valeur, couleur);
    }
}
