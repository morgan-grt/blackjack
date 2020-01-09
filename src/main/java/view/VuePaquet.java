package view;

import model.ImageCarte;
import cartes.Carte;
import cartes.Paquet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import observer.ModelListener;

public abstract class VuePaquet extends JPanel implements ModelListener{
    protected String LABEL;
    protected JFrame frame;
    protected Paquet paquet;
    protected Map<String, ImageCarte> imageMap;
    protected Map<String, Image> allImage;

    public VuePaquet(JFrame frame, String LABEL) {
        super();
        imageMap = new HashMap();
        allImage = chargeAllImage();
        this.LABEL = LABEL;
        this.frame = frame;
        paquet = new Paquet();
        paquet.addModelListener(this);
        JLabel label = new JLabel(LABEL);
        add(label);
        setOpaque(false);
        label.setSize(160, 20);
        setLayout(new BorderLayout());
        repaint();
    }
    
    @Override
    public void somethingHasChanged(Object source) {
        repaint();
    }
    
    public Map<String, Image> chargeAllImage(){
        Map<String, Image> map = new HashMap();
        Paquet paquet = new Paquet(52);
        for (Carte carte : paquet.getPaquet()){
            String string = carte.getName();
            try{
            Image imageTMP = ImageIO.read(this.getClass().getResource("/sprites/"
                    + string +".gif"));
            map.put(string, imageTMP);
            imageMap.put(string, new ImageCarte(imageTMP, carte));
            }
            catch (IOException ex){System.out.println("VuePaquet erreur : " + ex);}
        }
        return map;
    }
    
    public void drawRect(Rectangle rect){
        Graphics g = this.getGraphics();
        g.setColor(Color.GREEN);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 90, 90);
    }

    public Paquet getPaquet() {
        return paquet;
    }

    public void setPaquet(Paquet paquet) {
        this.paquet.removeModelListener(this);
        this.paquet = paquet;
        this.paquet.addModelListener(this);
        repaint();
    }
    
    public void removeImageMap(String string, ImageCarte imageCarte){
        imageMap.remove(string, imageCarte);
    }

    public Map<String, ImageCarte> getImageMap() {
        return imageMap;
    }       
}
