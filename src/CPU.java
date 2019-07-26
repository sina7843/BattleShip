
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sina
 */
public class CPU {
    int[][] IDPlace=new int[10][10];
    int[][] EnemyMap=new int[10][10];
    int mode;
      CPU(int mode){
          this.mode=mode;
          int n=(int)(Math.random()*2)+1;
          Scanner input = null;
          try {
              input=new Scanner(new File("File\\map_"+1+".txt"));
              for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    IDPlace[i][j]=input.nextInt();
                }
              }
              
          } catch (FileNotFoundException ex) {
              Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              input=new Scanner(new File("File\\CPUDATA.txt"));
              for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    EnemyMap[i][j]=input.nextInt();
                }
              }
              
          } catch (FileNotFoundException ex) {
              Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
          }
          
      }
      
      public int[][] getIDPlace() {
            return IDPlace;
       }
      int x=0,y=0;
      int WrongAttack=0;
      boolean Find=false;
      public void THINK(BordGame bg){
          if(mode==0){
            if(!Find)
            {
                x=(int)(Math.random()*10);
                y=(int)(Math.random()*10);
                if(!bg.place[x][y].Attaked)Attack(bg.place[x][y],bg);
                else THINK(bg);
            }
            else
            {
              switch(WrongAttack)
              {
                  case 0:
                      y--;
                      break;
                  case 1:
                      y++;x--;
                      break;
                  case 2:
                      x++;x++;
                      break;
                  case 3:
                      x--;
                      y++;
                      break;
                  default:
                      Find=false;
                      WrongAttack=0;
                      break;
              }
               x=Math.abs(x);
               y=Math.abs(y);
               x%=10;
               y%=10;
               if(!bg.place[x][y].Attaked)Attack(bg.place[x][y],bg);
               else {WrongAttack++;THINK(bg);}
            }
          }
          else if(mode==1)
          {
              if(!Find)
            {
                int max=0;
              for (int i = 0; i < 10; i++) {
                  for (int j = 0; j < 10; j++) {
                      if(max<EnemyMap[i][j])
                      {
                          max=EnemyMap[i][j];
                          x=i;
                          y=j;
                      }
                  }
              }
              EnemyMap[x][y]=-1;
              if(max!=0)Attack(bg.place[x][y],bg);
              else mode=0;
            }
            else
            {
              switch(WrongAttack)
              {
                  case 0:
                      y--;
                      break;
                  case 1:
                      y++;x--;
                      break;
                  case 2:
                      x++;x++;
                      break;
                  case 3:
                      x--;
                      y++;
                      break;
                  default:
                      Find=false;
                      WrongAttack=0;
                      break;
              }
               x=Math.abs(x);
               y=Math.abs(y);
               x%=10;
               y%=10;
               if(!bg.place[x][y].Attaked)Attack(bg.place[x][y],bg);
               else {WrongAttack++;THINK(bg);}
            }
          }
          else
          {
              int max=0;
              for (int i = 0; i < 10; i++) {
                  for (int j = 0; j < 10; j++) {
                      if(max<EnemyMap[i][j])
                      {
                          max=EnemyMap[i][j];
                          x=i;
                          y=j;
                      }
                  }
              }
              EnemyMap[x][y]=-1;
              if(max!=0)Attack(bg.place[x][y],bg);
              else mode=0;
          }
      }
      public void Attack(Place place,BordGame bg)
      {
          place.Attaked=true;
          place.setEnabled(false);
          if (place.IDShip == -1) {
              if(Find){
                  this.WrongAttack++;
                }
                place.setBackground(Color.GRAY);
                place.bg.mg.t.time=15;
                
            } else {
                Find=true;
                place.ship[place.IDShip].heal--;
                if(place.ship[place.IDShip].heal==0) {place.setBackground(Color.black);this.Find=false;}
                else place.setBackground(Color.RED);
                boolean end=true;
                for (Ship ship1 : place.ship) {
                    if (ship1.heal > 0) {
                        System.out.println(ship1.heal);
                        end=false;
                        break;
                    }
                }
                if (end) {
                    place.bg.mg.t.stop();
                    JOptionPane.showMessageDialog(place, place.bg.name+" IS LOOOOSER", "LOOOOOSER", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println( place.bg.mg.getParent().getName());
                    place.bg.mg.Parent.setVisible(false);
                    try {
                        EndGame eg=new EndGame(place.bg.name,"CPU",place.bg.mg.p1.placeId);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                THINK(bg);
                place.bg.Change(place.bg.panel, place.enable);
            }
            place.repaint();
      }
}

