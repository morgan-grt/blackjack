/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.PlayerInput;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VuePlayerAction extends JPanel implements ActionListener{
    
    protected JButton tirer, doubler, split, assurance, passe;
    private GridBagConstraints pos;
    protected PlayerInput input;
    
    public VuePlayerAction(PlayerInput input) {
        super();
        this.input = input;
        
        tirer = new JButton("Tirer");
        doubler = new JButton("Doubler");
        split = new JButton("Split");
        assurance = new JButton("Assurance");
        passe = new JButton("Passer");
        
        tirer.addActionListener(this);
        doubler.addActionListener(this);
        split.addActionListener(this);
        assurance.addActionListener(this);
        passe.addActionListener(this);
        
        setLayout(new GridBagLayout());
        pos = new GridBagConstraints();
        setOpaque(false);
        repaint();
    }
    
    /**
     * appel l'input du joueur en lui donnant
     * l'action recuperee
     * @param e source (boutons)
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tirer){
            input.callBackAction("tirer");
        }
        else if (e.getSource() == doubler){
            input.callBackAction("doubler");
        }
        else if (e.getSource() == split){
            input.callBackAction("split");
        }
        else if (e.getSource() == assurance){
            input.callBackAction("assurance");
        }
        else if (e.getSource() == passe){
            input.callBackAction("passe");
        }
        
    }
    
    public void addActions() {
        pos.gridx = 0; pos.gridy = 0;
        add(tirer,pos);
        pos.gridx = 1; pos.gridy = 0;
        add(doubler,pos);
        pos.gridx = 2; pos.gridy = 0;
        //add(split,pos);
        pos.gridx = 0; pos.gridy = 1;
        add(assurance,pos);
        pos.gridx = 1; pos.gridy = 1;
        add(passe,pos);
    }

    public void removeActions() {
        remove(tirer);
        remove(doubler);
        remove(split);
        remove(assurance);
        remove(passe);
    }
    
    
}
