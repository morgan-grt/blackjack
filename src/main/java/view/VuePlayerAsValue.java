/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.PlayerInput;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class VuePlayerAsValue extends JPanel implements ActionListener{
    
    protected JButton confirmerAs;
    protected JComboBox selectAsValue;
    protected final String[] asValues = {"1","11"};
    protected PlayerInput input;
    
    public VuePlayerAsValue(PlayerInput input) {
        super();
        this.input = input;
        confirmerAs = new JButton("Valider As");
        selectAsValue = new JComboBox(asValues);
        confirmerAs.addActionListener(this);
        setOpaque(false);
        repaint();
    }
    
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmerAs){
            input.callBackAsValue(""+selectAsValue.getSelectedItem());
        }
    }

    public void addAsValue() {
        add(selectAsValue);
        add(confirmerAs);
    }

    public void removeAsValue() {
        remove(selectAsValue);
        remove(confirmerAs);
    }
}
