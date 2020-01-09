/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.PlayerInput;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import observer.ModelListener;

public class VuePlayerMise extends JPanel implements ModelListener, ActionListener{
    
    protected JButton ajoutMise1, ajoutMise2, ajoutMise5
            , ajoutMise10, supprimer, confirmerMise;
    protected JLabel mise, balance, point;
    private GridBagConstraints pos;
    private int index;
    private PlayerInput input;

    public VuePlayerMise(int index, PlayerInput input) {
        super();
        this.index = index;
        this.input = input;
        ajoutMise1 = new JButton("+1");
        ajoutMise2 = new JButton("+2");
        ajoutMise5 = new JButton("+5");
        ajoutMise10 = new JButton("+10");
        supprimer = new JButton("Reset");
        confirmerMise = new JButton("Confirm");
        
        ajoutMise1.addActionListener(this);
        ajoutMise2.addActionListener(this);
        ajoutMise5.addActionListener(this);
        ajoutMise10.addActionListener(this);
        supprimer.addActionListener(this);
        confirmerMise.addActionListener(this);
        input.getPlayer().addModelListener(this);
        
        mise = new JLabel("mise: "+input.getPlayer().getBet(0));
        mise.setOpaque(true);
        mise.setBackground(Color.LIGHT_GRAY);
        mise.setPreferredSize(new Dimension(100, 30));
        
        balance = new JLabel("balance: "+input.getPlayer().getBalance());
        balance.setOpaque(true);
        balance.setBackground(Color.LIGHT_GRAY);
        balance.setPreferredSize(new Dimension(100, 30));
        
        point = new JLabel("Point: "+input.getPlayer().getPoint(index));
        point.setOpaque(true);
        point.setBackground(Color.LIGHT_GRAY);
        point.setPreferredSize(new Dimension(100, 30));
        
        setLayout(new GridBagLayout());
        pos = new GridBagConstraints();
        pos.gridx = 0; pos.gridy = 2;
        add(mise, pos);
        pos.gridx = 1; pos.gridy = 2;
        add(balance, pos);
        pos.gridx = 2; pos.gridy = 2;
        add(point, pos);
        setOpaque(false);
        repaint();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajoutMise1){
            input.setBet(1, 0);
        }
        else if (e.getSource() == ajoutMise2){
            input.setBet(2, 0);
        }
        else if (e.getSource() == ajoutMise5){
            input.setBet(5, 0);
        }
        else if (e.getSource() == ajoutMise10){
            input.setBet(10, 0);
        }
        else if (e.getSource() == supprimer){
            input.setBet(0, 0);
        }
        else if (e.getSource() == confirmerMise){
            input.callBackBet();
            removeButtons();
        }
    }
    
    public void addButtons() {
        pos.gridx = 0; pos.gridy = 0;
        add(ajoutMise1, pos);
        pos.gridx = 1; pos.gridy = 0;
        add(ajoutMise2, pos);
        pos.gridx = 2; pos.gridy = 0;
        add(ajoutMise5, pos);
        pos.gridx = 0; pos.gridy = 1;
        add(ajoutMise10, pos);
        pos.gridx = 1; pos.gridy = 1;
        add(supprimer, pos);
        pos.gridx = 2; pos.gridy = 1;
        add(confirmerMise, pos);
    }

    public void removeButtons(){
        remove(ajoutMise1);
        remove(ajoutMise2);
        remove(ajoutMise5);
        remove(ajoutMise10);
        remove(supprimer);
        remove(confirmerMise);
        repaint();
    }

    /**
     * mise a jour de l'affichage des que le joueur
     * notifie ses ecouteurs
     * @param o joueur ecoute
     */
    public void somethingHasChanged(Object o) {
        mise.setText("mise: "+ input.getPlayer().getBet(0));
        balance.setText("balance: "+ input.getPlayer().getBalance());
        point.setText("Point: "+ input.getPlayer().getPoint(index));
    }

    
    
    
    
    
    
    
}
