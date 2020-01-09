package cartes;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import observer.AbstractListenableModel;

public class Paquet extends AbstractListenableModel{
    protected LinkedList<Carte> paquet;
    Random random = new Random();
    
    
    public Paquet(int nb){
        paquet = new Factory(nb).getHand();
        shuffle();
    }
    
    public Paquet(){
        paquet = new Factory(0).getHand();
    }
    
    public Paquet(LinkedList<Carte> paquet){
        this.paquet = paquet;
    }
    
    public Paquet(Carte carte){
        super();
        paquet.add(carte);
    }
    
    /**
     * melange les cartes du paquet
     */
    public void shuffle(){
        Collections.shuffle(paquet);
    }
    
    /**
     * coupe le paquet en deux
     */
    public void cut(){
        int cutter = random.nextInt(paquet.size()-2)+1;
        LinkedList<Carte> tmp = new LinkedList();
        for(int i = 0;i<cutter;i++){
            tmp.add(paquet.pop());
        }
        for(int i = 0;i<cutter;i++){
            paquet.add(tmp.pop());
        }
    }
    
    /**
     * retourne la premiere carte et la retire du paquet
     * @return premiere carte du paquet
     */
    public Carte getfirst(){
        if (paquet.isEmpty()){
            return null;
        }
        Carte firstCarte = paquet.pop();
        removeCarte(paquet.getFirst());
        return firstCarte;
    }
    
    /**
     * retourne la premiere carte du paquet
     * @return premiere carte du paquet
     */
    public Carte showFirst(){
        if (paquet.isEmpty()){
            return null;
        }
        return paquet.getFirst();
    }
    
    /**
     * retourne la derniere carte du paquet
     * @return la derniere carte du paquet
     */
    public Carte showLast(){
        if (paquet.isEmpty()){
            return null;
        }
        int i = 0;
        while(paquet.get(i + 1) != null){
            i++;
        }
        return paquet.get(i);
    }
    
    /**
     * retourne et enleve la carte d'indice i
     * @param i indice dans la liste
     * @return la carte d'indice i
     */
    public Carte getThis(int i){
        Carte thisCarte = paquet.get(i);
        removeCarte(paquet.get(i));
        return thisCarte;
    }
    
    public void addCarte(Carte toAdd){
        paquet.add(toAdd);
        fireChange();
    }
    
    public void removeCarte(Carte toAdd){
        paquet.remove(toAdd);
        fireChange();
    }

    public LinkedList<Carte> getPaquet() {
        return paquet;
    }    
    
    @Override
    public String toString(){
        String string = "";
        for(Carte c : paquet){
            string +=  "\n" + c.toString();
        }
        return string;
    }
    
    public Paquet getCopy(){
        LinkedList<Carte> copy = new LinkedList();
        for (Carte carte : paquet){
            copy.add(carte.getCopy());
        }
        return new Paquet(copy);
    }
}
