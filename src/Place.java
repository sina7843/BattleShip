/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author sina
 */
public class Place extends JButton {
    int IDShip;
    boolean Attaked;
    boolean enable;
    Ship[] ship;
    BordGame bg;
    Place(int IDShip,boolean enable,Ship[] ship,BordGame bg){
        this.ship=ship;
        this.bg=bg;
        this.Attaked=false;
        this.IDShip=IDShip;
        this.enable=enable;
        this.addActionListener((ActionEvent e) -> {
            Place.this.Attaked = true;
            Place.this.setEnabled(false);
            
            bg.mg.t.time=15;
            if (Place.this.IDShip == -1) {
                Place.this.setBackground(Color.GRAY);
                bg.mg.Change(false);
                
            } else {
                ship[Place.this.IDShip].heal--;
                if(ship[Place.this.IDShip].heal==0) Place.this.setBackground(Color.black);
                else Place.this.setBackground(Color.RED);
                boolean end=true;
                for (Ship ship1 : ship) {
                    if (ship1.heal > 0) {
                        end=false;
                        break;
                    }
                }
                if (end) {
                    bg.mg.t.stop();
                    JOptionPane.showMessageDialog(Place.this, bg.name+" IS LOOOOSER", "LOOOOOSER", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println( bg.mg.getParent().getName());
                    bg.mg.Parent.setVisible(false);
                    try {
                        
                        EndGame eg=new EndGame(bg.name,(bg.name.equals(MainClass.name1)? MainClass.name2:MainClass.name1),bg.mg.p1.placeId);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                bg.Change(bg.panel, Place.this.enable);
            }
            repaint();
        });
    }
}
