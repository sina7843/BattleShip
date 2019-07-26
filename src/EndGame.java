
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sina
 */
public class EndGame {
    String name1;
    String name2;
    ArrayList<Player> players;
    EndGame(String name1,String name2,int[][] ID1) throws FileNotFoundException
    {
        int[][] past=new int[10][10];
        Profile p=new Profile();
        this.players=p.getPlayers();
        boolean find1=false;
        boolean find2=false;
        System.out.println(name2+" "+name1);
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).name.equals(name1)){
                find1=true;
                players.get(i).Game++;
            }else if(players.get(i).name.equals(name2))
            {
                find2=true;
                players.get(i).Game++;
                players.get(i).Win++;
            }
        }
        if(!find1)
        {
            players.add(new Player(name1,0,1));
        }
        if(!find2)
        {
            players.add(new Player(name2,1,1));
        }
         try{    
           FileWriter fw=new FileWriter("File\\Player.txt");    
           for (int i = 0; i < players.size(); i++) {
                fw.write(players.get(i).name+" "+players.get(i).Win+" "+players.get(i).Game+"\n");
            }    
           fw.close();    
          }catch(Exception e){System.out.println(e);} 
         try{
           Scanner input=new Scanner(new File("File\\CPUDATA.txt"));
             for (int i = 0; i < 10; i++) {
                 for (int j = 0; j < 10; j++) {
                     past[i][j]=input.nextInt();
                 }
             }
           FileWriter fw=new FileWriter("File\\CPUDATA.txt");
           
           for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    int temp=past[i][j];
                    if(ID1[i][j]!=-1)
                    {
                        temp++;
                    }
                   fw.write(temp+" ");
               }
                fw.write("\n\r");
            }    
           fw.close();    
          }catch(Exception e){System.out.println(e);}
    }
        
    
}
