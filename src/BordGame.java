import java.awt.Color;
import javax.swing.JPanel;
public class BordGame {
    Place[][] place=new Place[10][10];
    int[] shipLeangh={4,3,3,2,2,2,1,1,1,1};
    Ship[] ship=new Ship[10];
    JPanel panel;
    String name;
    MainGame mg;
    int[][] placeId;
    BordGame(JPanel panel,boolean enable,int[][] placeId,String name,MainGame mg){
        this.placeId=placeId;
        this.mg=mg;
        this.name=name;
        for (int i = 0; i < 10; i++) {
            ship[i] = new Ship(this.shipLeangh[i]);
        }
        this.panel=panel;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                place[i][j]=new Place(placeId[i][j],enable,ship,this);
                place[i][j].setSize(35,35);
                place[i][j].setLocation(35*i,35*j);
                place[i][j].setBackground(Color.white);
                panel.add(place[i][j]);
            }
        }
        Change(panel,enable);
    }
    public void Change(JPanel panel,boolean enable){
        this.panel=panel;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                place[i][j].enable=enable;
                if(!enable){
                    place[i][j].setEnabled(enable);
                    if(place[i][j].IDShip!=-1)
                    {
                        if(place[i][j].Attaked)
                            if(ship[place[i][j].IDShip].heal==0)place[i][j].setBackground(Color.black);
                            else place[i][j].setBackground(Color.red);
                        else
                            place[i][j].setBackground(Color.blue);
                    }
                    else
                    {
                        if(place[i][j].Attaked)
                            place[i][j].setBackground(Color.gray);
                        else
                            place[i][j].setBackground(Color.white);
                    }
                }else if(!place[i][j].Attaked){
                    place[i][j].setEnabled(true);
                    if(place[i][j].IDShip!=-1)
                    {
                        if(place[i][j].Attaked)
                            place[i][j].setBackground(Color.gray);
                        else
                            place[i][j].setBackground(Color.white);
                    }
                }
            }
        }
    }
}
