
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
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
public class Timer extends Thread{
    double time=15.0000;
    double pasttime=0;
    MainGame mg;

    public Timer(MainGame mg) {
        this.mg = mg;
    }
      private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    public void run(){
        while(true){
        while(time>0)
        {
            try {
                TimeUnit.MILLISECONDS.sleep(16);
                time-=0.015;
                pasttime+=0.015;
                mg.jLabel4.setText((df2.format(time))+"");
                mg.jLabel5.setText(((int)(pasttime/60))+":"+(df2.format(pasttime%60)));
            } catch (InterruptedException ex) {
                Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        time=0;
        mg.jLabel4.setText((df2.format(time))+"");
        mg.Change(true);
        time=15;
        }
    }
    
}
