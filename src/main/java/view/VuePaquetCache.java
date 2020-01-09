package view;

import model.ImageCarte;
import cartes.Carte;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class VuePaquetCache extends VuePaquet{

    public VuePaquetCache(JFrame frame, String LABEL) {
        super(frame, LABEL);
        chargeImage();
        
    }
    
    public void chargeImage() {
        try{
            Image imageTMP = ImageIO.read(this.getClass().getResource("/sprites/fff.gif"));
            ImageCarte imageCarte = new ImageCarte(imageTMP, new Carte("pioche","cachee"));
            imageMap.put("pioche_cachee", imageCarte);     
        }
            catch (IOException ex){System.out.println("VuePaquetCache erreur : " + ex);}
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int i = 0;
        if (!paquet.getPaquet().isEmpty()){
            for (Carte carte : new LinkedList<Carte>(paquet.getPaquet())){
                ImageCarte imgCarteTMP = imageMap.get("pioche_cachee");
                try{
                    g.drawImage(imgCarteTMP.getImage(), 20+i, 20+i, null);
                    imgCarteTMP.setRectangle(new Rectangle(20+i, 20+i
                            , imgCarteTMP.getImage().getWidth(frame)
                            , imgCarteTMP.getImage().getHeight(frame)));
                }
                catch(NullPointerException npe){System.out.println(LABEL);}i++;
            }
        }
    }

    

}
