
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class MakeBord extends javax.swing.JPanel {
    Image img=null;
    int[] Ships={4,3,3,2,2,2,1,1,1,1};
    int[][] IDPlace=new int[10][10];
    JButton[][] btn;
    int n=11;
    MakeBord mb;
    int mode;
    JFrame parent;
    public MakeBord(String name,int mode,MakeBord mb,JFrame parent) {
        try {
            img=ImageIO.read(new File(System.getProperty("user.dir")+"\\img\\back1.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.parent=parent;
        this.mb=mb;
        this.mode=mode;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                IDPlace[i][j]=-1;
            }
        }
        initComponents();
        btn=new JButton[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                btn[i][j]=new JButton();
            }
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                btn[i][j].setSize(35,35);
                btn[i][j].setLocation(35*(i-1),35*(j-1));
                jPanel1.add(btn[i][j]);
                btn[i][j].addActionListener((ActionEvent e) -> {
                    n--;
                    if(n>0)
                    {
                        Command.setText("Plz choose Aim First then Choose Start Place For Ship with Lengh"+Ships[n-1]);
                        JButton temp= (JButton) e.getSource();
                        int x=(temp.getLocation().x/35);
                        int y=(temp.getLocation().y/35);
                        switch(jComboBox1.getSelectedIndex()){
                            case 0:
                                if(!this.CanPut(x, y, Ships[n-1], 0))
                                {
                                    JOptionPane.showMessageDialog(null, "You Must Choose righPlace","EROR",JOptionPane.ERROR_MESSAGE);
                                    n++;
                                }
                                else
                                {
                                    for (int k = 0; k < Ships[n-1]; k++) {
                                        IDPlace[x][y-k]=n-1;
                                    }
                                }
                                break;
                            case 2:
                                if(!this.CanPut(x, y, Ships[n-1], 2))
                                {
                                    JOptionPane.showMessageDialog(null, "You Must Choose righPlace","EROR",JOptionPane.ERROR_MESSAGE);
                                    n++;
                                }
                                else
                                {
                                    for (int k = 0; k < Ships[n-1]; k++) {
                                        IDPlace[x+k][y]=n-1;
                                    }
                                }
                                break;
                            case 1:
                                if(!this.CanPut(x, y, Ships[n-1], 1))
                                {
                                    JOptionPane.showMessageDialog(null, "You Must Choose righPlace","EROR",JOptionPane.ERROR_MESSAGE);
                                    n++;
                                }
                                else
                                {
                                    for (int k = 0; k < Ships[n-1]; k++) {
                                        IDPlace[x-k][y]=n-1;
                                    }
                                }
                                break;
                             case 3:
                                if(!this.CanPut(x, y, Ships[n-1], 3))
                                {
                                    JOptionPane.showMessageDialog(null, "You Must Choose righPlace","EROR",JOptionPane.ERROR_MESSAGE);
                                    n++;
                                }
                                else
                                {
                                    for (int k = 0; k < Ships[n-1]; k++) {
                                        IDPlace[x][y+k]=n-1;
                                    }
                                }
                                break;
                        }
                        Command.setText("Plz choose Aim First then Choose Start Place For Ship with Lengh"+Ships[n-1]);
                    }
                    if(n==1)
                    {
                        Command.setText("Plz Close Frame");
                        jButton2.setEnabled(true);
                    }
                    this.reMake();
                });
            }
        }
        jLabel2.setText(name);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0,730,600, null);
    }
    public void reMake()
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(IDPlace[i][j]!=-1&&IDPlace[i][j]!=-2)
                {
                    btn[i][j].setEnabled(false);
                    btn[i][j+1].setEnabled(false);
                    btn[i][j+2].setEnabled(false);
                    btn[i+1][j].setEnabled(false);
                    btn[i+1][j+1].setEnabled(false);
                     btn[i+1][j+1].setBackground(Color.blue);
                    btn[i+1][j+2].setEnabled(false);
                    btn[i+2][j].setEnabled(false);
                    btn[i+2][j+1].setEnabled(false);
                    btn[i+2][j+2].setEnabled(false);
                    this.Change(i, j);
                }
            }
        }
    }
    
    public void Change(int i,int j){
        try{
            if(IDPlace[i-1][j-1]==-1)
            {
                IDPlace[i-1][j-1]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i-1][j]==-1)
            {
                IDPlace[i-1][j]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i-1][j+1]==-1)
            {
                IDPlace[i-1][j+1]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i][j-1]==-1)
            {
                IDPlace[i][j-1]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i][j+1]==-1)
            {
                IDPlace[i][j+1]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i+1][j-1]==-1)
            {
                IDPlace[i+1][j-1]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i+1][j]==-1)
            {
                IDPlace[i+1][j]=-2;
            }
        }
        catch(Exception e){}
        try{
            if(IDPlace[i+1][j+1]==-1)
            {
                IDPlace[i+1][j+1]=-2;
            }
        }
        catch(Exception e){}
    }
    
    public boolean CanPut(int x,int y,int leangh,int mode){
        switch(mode){
            case 0:
                if(y-leangh<-1){System.out.println(y);return false;}
                for (int i = 0; i < leangh; i++) {
                    try{
                    if(IDPlace[x][y-i]!=-1)
                    {
                        return false;
                    }
                    }
                    catch(Exception e){}
                }
                break;
            case 1:
                if(x-leangh<-1)return false;
                for (int i = 0; i < leangh; i++) {
                    try{
                    if(IDPlace[x-i][y]!=-1)
                    {
                        return false;
                    }
                    }
                    catch(Exception e){}
                }
                break;
            case 2:
                if(x+leangh>10)return false;
                for (int i = 0; i < leangh; i++) {
                    try{
                    if(IDPlace[x+i][y]!=-1)
                    {
                        return false;
                    }
                    }
                    catch(Exception e){}
                }
                break;
            case 3:
                if(y+leangh>10)return false;
                for (int i = 0; i < leangh; i++) {
                    try{
                        if(IDPlace[x][y+i]!=-1)
                        {
                            return false;
                        }
                    }
                    catch(Exception e){}
                }
                break;
        }
        return true;
    }
    public int[][] getIDPlace() {
        for (int i = 0; i < IDPlace.length; i++) {
            for (int j = 0; j < IDPlace[i].length; j++) {
                if(IDPlace[i][j]==-2)
                {
                    IDPlace[i][j]=-1;
                }
            }
        }
        return IDPlace;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        Command = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jLabel1.setText("jLabel1");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Top", "Left", "Right", "Botton" }));

        Command.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bleeding Cowboys", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("jLabel2");

        jButton2.setText("Confirm");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 351, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(Command, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton2))
                            .addComponent(jLabel2))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Command, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.parent.setVisible(false);
         JFrame app=new JFrame();
        if(mode==1)
        {
            MainClass.name2=JOptionPane.showInputDialog("Plz Enter Name of Player 2");
            MakeBord mb1=new MakeBord(MainClass.name2,2,this,app);
            app.setSize(730,600);
            app.setUndecorated(true);
            app.setResizable(false);
            app.setLocation(520, 160); 
            app.add(mb1);
            app.setVisible(true);
        }
        else if(mode==2)
        {
            MainGame mg=new MainGame(this.mb,this,app);
            app.setAlwaysOnTop(true);
            app.setSize(1050, 740);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setLocation(420, 150); 
            app.setUndecorated(true);
            app.setResizable(false);
            app.add(mg);
            app.setVisible(true);
        }
        else
        {
            MainClass.name2="CPU";
            int mode=Integer.parseInt(JOptionPane.showInputDialog("PLZ CHOOSE ONE : EASY=1,MEDIUM=2,HARD=3"))%3;
            CPU cpu=new CPU(1);
            MainGame mg=new MainGame(this,cpu,app);
            app.setAlwaysOnTop(true);
            app.setSize(1050, 740);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setLocation(420, 150); 
            app.setUndecorated(true);
            app.setResizable(false);
            app.add(mg);
            app.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Command;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
