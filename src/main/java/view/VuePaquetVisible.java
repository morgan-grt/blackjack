package view;

import model.ImageCarte;
import cartes.Carte;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JFrame;

public class VuePaquetVisible extends VuePaquet{

    public VuePaquetVisible(JFrame frame, String LABEL) {
        super(frame, LABEL);
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int i = 0;
        if (!paquet.getPaquet().isEmpty()){
            for (Carte carte : new LinkedList<Carte>(paquet.getPaquet())){
                ImageCarte imgCarteTMP = imageMap.get(carte.getName());
                try{
                    g.drawImage(imgCarteTMP.getImage(), 20+40*i, 40, null);
                    imgCarteTMP.setRectangle(new Rectangle(20+40*i, 40
                            , imgCarteTMP.getImage().getWidth(frame)
                            , imgCarteTMP.getImage().getHeight(frame)));
                }
                catch(NullPointerException npe){System.out.println(LABEL+" error: "+npe);}i++;
            }
        }
    }

}
