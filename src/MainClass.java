import javax.swing.JFrame;

public class MainClass {
    static int cou=0;
    static String name1="sina",name2;
    public static void main(String[] args)
    {
        JFrame app=new JFrame();
        StartMenu sm=new StartMenu();
        app.setSize(1050, 740);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLocation(420, 150); 
        app.setUndecorated(true);
        app.setResizable(false);
        app.add(sm);
        app.setVisible(true);
    }
}
