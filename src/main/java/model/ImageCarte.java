/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cartes.Carte;
import java.awt.Image;
import java.awt.Rectangle;

public class ImageCarte{
    protected Image image;
    protected Carte carte;
    protected Rectangle rectangle;

    public ImageCarte(Image image, Carte carte) {
        this.image = image;
        this.carte = carte;
        this.rectangle = null;
    }

    public Image getImage() {
        return image;
    }

    public Carte getCarte() {
        return carte;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    
    
}
