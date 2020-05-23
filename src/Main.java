import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends JFrame { //Wir haben unser Canvas in einem JFrame
    // Anfang Attribute
    private Canvas canvas1 = new Canvas();

    private int xpos;
    static final int frameRate = 60;




    private Inventory inv = new Inventory(26);


    private final BufferStrategy bs;                      // braucht man einmal für das ganze Canvas
    private BufferedImage e0_left;
    private BufferedImage e0_bottom;// braucht man pro Bild
    private BufferedImage e0_right;
    private BufferedImage e0_top;
    private BufferedImage e1_left;
    private BufferedImage e1_bottom;
    private BufferedImage e1_right;
    private BufferedImage e1_top;
    private BufferedImage e2_left;
    private BufferedImage e2_bottom;
    private BufferedImage e2_right;
    private BufferedImage e2_top;
    private BufferedImage e2_front;
               //Wir machen einen Timer, der 25 Mal/Sekunde tickt, um Bilder zu laden.
    // Ende Attribute


    public Main(String title) {                                       // Konstruktor
        //Das ist alles Standard-JFrame-Zeugs
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int frameWidth = 1280;
        int frameHeight = 720;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        //Jetzt wirds interessant:

        canvas1.setBounds(0, 0, 1280, 720);                         // Canvas auf das Form ziehen erstellt diesen Code
        cp.add(canvas1);





        setVisible(true);


        //OK, ab hier wirds aber wirklich interessant:

        xpos = 0;

        // Die folgenden 2 Zeilen dienen dem Verhindern vom Flimmern, indem man einen DoubleBuffer nutzt
        if (canvas1.getBufferStrategy() == null) canvas1.createBufferStrategy(2);   // Wenn das Canvas noch keine Buffer-Strategie
        bs = canvas1.getBufferStrategy();                                           // hat, dann erstellen wir eine und legen
        // diese auf die Variable bs.

        try {                                                                       // Versuche
            e0_left = ImageIO.read(new File("./images/fertige_bilder/e0_left.png"));
            e0_bottom = ImageIO.read(new File("./images/fertige_bilder/e0_bottom.png"));
            e0_right = ImageIO.read(new File("./images/fertige_bilder/e0_right.png"));
            e0_top = ImageIO.read(new File("./images/fertige_bilder/e0_top.png"));
            e1_left = ImageIO.read(new File("./images/fertige_bilder/e1_left.png"));
            e1_bottom = ImageIO.read(new File("./images/fertige_bilder/e1_bottom.png"));
            e1_right = ImageIO.read(new File("./images/fertige_bilder/e1_right.png"));
            e1_top = ImageIO.read(new File("./images/fertige_bilder/e1_top.png"));
            /*e2_left = ImageIO.read(new File("./images/fertige_bilder/e2_left.png"));
            e2_bottom = ImageIO.read(new File("./images/fertige_bilder/e2_bottom.png"));
            e2_right = ImageIO.read(new File("./images/fertige_bilder/e2_right.png"));
            e2_top = ImageIO.read(new File("./images/fertige_bilder/e2_top.png"));
            e2_front  = ImageIO.read(new File("./images/fertige_bilder/e2_front.png"));*/
        } catch(Exception e) {                                                      // Wenn es dabei einen Fehler gibt
            e.printStackTrace();                                                      // Gebe einen Fehler aus. Das try
        }                                                                           //verhindert dabei, dass unser Java-Programm dabei abstürzt.
        // Nun liegt auf der Variable bild das ganze Bild.
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timer();
            }
        }, 0, 1000/frameRate);

                                                                    // Zum Schluss Timer starten
    } // end of public Main

    // Anfang Methoden

    public static void main(String[] args) {
        new Main("The Game");                                   // Legt ein Objekt von sicher selber an,
        //also ein sichtbares Fenster
        //public itm1 =new Item;



    } // end of main
    /* Das hat gar nichts mit dem Canvas oder dem Buffer zu tun, sondern das ist eine übliche Methode. Die main ist immer static und damit KlassenMethode. Damit man hier nicht groß mit static arbeiten muss, legt man sich ein Objekt von sich selber an. Damit wird der obere Konstruktor aufgerufen, der Timer startet und alles beginnt. Müsst ihr nicht so machen, ihr könnt auch alles mit static machen. */

    public void timer(){
        calculate();
        render();

    }


    public void render() {
        //Macht die Leinwand leer
        bs.getDrawGraphics().clearRect(0,0, canvas1.getWidth(), canvas1.getHeight());

        //Zeichnet Bild

        bs.getDrawGraphics().drawImage(e0_left,0,0,320,720, null);
        bs.getDrawGraphics().drawImage(e0_bottom,0,540,1280,180, null);
        bs.getDrawGraphics().drawImage(e0_right,960,0,320,720, null);
        bs.getDrawGraphics().drawImage(e0_top,0,0,1280,180, null);
        bs.getDrawGraphics().drawImage(e1_left,320,180,180,360,null);
        bs.getDrawGraphics().drawImage(e1_bottom,320,450,640,90,null);
        bs.getDrawGraphics().drawImage(e1_right,780,180,180,360,null);
        bs.getDrawGraphics().drawImage(e1_top,320,180,640,90,null);
        /*bs.getDrawGraphics().drawImage(e2_left,480,270,61,180,null);
        bs.getDrawGraphics().drawImage(e2_bottom,480,415,320,35,null);
        bs.getDrawGraphics().drawImage(e2_right,739,270,61,180,null);
        bs.getDrawGraphics().drawImage(e2_top,480,270,320,35,null);*/
        //bs.getDrawGraphics().drawImage(e2_front,541,305,320,180,null);
        //Zeigt das gezeichnette an. Das ist der Trick vom DoubleBuffer, man zeichnet erst alles auf ein
        //unsichtbares Canvas und macht es dann auf einen Schlag sichtbar.
        bs.show();
    }


    public void calculate(){


    }

} // end of class EinBischenCanvas

