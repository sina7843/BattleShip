
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sina
 */
public class Profile {
    ArrayList<Player> players=new ArrayList();
    Profile() throws FileNotFoundException{
        Scanner input=new Scanner(new File("File\\Player.txt"));
        while(input.hasNext())
        {
            String name=input.next();
            int win=input.nextInt();
            int game=input.nextInt();
            players.add(new Player(name,win,game));
        }
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size()-1; j++) {
                if(players.get(j).Win*100/players.get(j).Game<players.get(j+1).Win*100/players.get(j+1).Game){
                    int temp1=players.get(j+1).Game;
                    players.get(j+1).Game=players.get(j).Game;
                    players.get(j).Game=temp1;
                    temp1=players.get(j+1).Win;
                    players.get(j+1).Win=players.get(j).Win;
                    players.get(j).Win=temp1;
                    String temp=players.get(j+1).name;
                    players.get(j+1).name=players.get(j).name;
                    players.get(j).name=temp;
                }
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
}
