import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Main extends JFrame implements KeyListener{ //Wir haben unser Canvas in einem JFrame
    // Anfang Attribute
    private  int delay=0;
    private  int standardDelay=10;
    private boolean isPlayerLocked = false;
    private Canvas canvas1 = new Canvas();
    private int degree=0;
    private int xpos;
    static final int frameRate = 60;

    static boolean[] keyPressedBool = new boolean[223];


    private Inventory inv = new Inventory(26);
    private Map map = new Map(4, 4);
    private Player player = new Player();

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
    private BufferedImage e0_tunnel_left;
    private BufferedImage e0_tunnel_right;
    private BufferedImage e1_tunnel_left;
    private BufferedImage e1_tunnel_right;


               //Wir machen einen Timer, der 25 Mal/Sekunde tickt, um Bilder zu laden.
    // Ende Attribute


    public Main(String title) {

        // Konstruktor
        //Das ist alles Standard-JFrame-Zeugs
        super(title);
        int frameWidth = 1280;
        int frameHeight = 720;
        setUpMap();
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        
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
            e0_tunnel_left = ImageIO.read(new File("./images/fertige_bilder/e0_front_tunel_left.png"));
            e0_tunnel_right = ImageIO.read(new File("./images/fertige_bilder/e0_front_tunel_right.png"));
            e1_tunnel_left = ImageIO.read(new File("./images/fertige_bilder/e1_front_tunel_left.png"));
            e1_tunnel_right = ImageIO.read(new File("./images/fertige_bilder/e1_front_tunel_right.png"));
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
        canvas1.addKeyListener(this);
                                                                    // Zum Schluss Timer starten
    } // end of public Main

    // Anfang Methoden

    public static void main(String[] args) {
        new Main("The Game");                                   // Legt ein Objekt von sicher selber an,
        //also ein sichtbares Fenster




    } // end of main
    /* Das hat gar nichts mit dem Canvas oder dem Buffer zu tun, sondern das ist eine übliche Methode. Die main ist immer static und damit KlassenMethode. Damit man hier nicht groß mit static arbeiten muss, legt man sich ein Objekt von sich selber an. Damit wird der obere Konstruktor aufgerufen, der Timer startet und alles beginnt. Müsst ihr nicht so machen, ihr könnt auch alles mit static machen. */

    public void timer(){
        calculate();
        render();

    }


    public void render() {
        Graphics g = bs.getDrawGraphics();
        
        //Macht die Leinwand leer
        g.clearRect(0,0, canvas1.getWidth(), canvas1.getHeight());

        //Zeichnet Bild
        if(degree==0) {
            //e0----------------------------
            g.drawImage(e0_bottom, 0, 540, 1280, 180, null);
            g.drawImage(e0_top, 0, 0, 1280, 180, null);
            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isWallowed == false) {
                g.drawImage(e0_left, 0, 0, 320, 720, null);
            } else {
                //tunnel
            }


            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isEallowed == false) {
                g.drawImage(e0_right, 960, 0, 320, 720, null);
            } else {
                //tunnel
            }
            //------------------------------
            //e1----------------------------
            g.drawImage(e1_top, 320, 180, 640, 90, null);
            g.drawImage(e1_bottom, 320, 450, 640, 90, null);
            if(player.getCurrentRoomY() + 1<map.getMapHeight()) {
                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() + 1].isWallowed == false) {
                    g.drawImage(e1_left, 320, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_left, 500, 270, 320, 181, null);
                }
                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() + 1].isEallowed == false) {
                    g.drawImage(e1_right, 780, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e1_left, 320, 180, 180, 360, null);
                g.drawImage(e1_right, 780, 180, 180, 360, null);
            }
            //------------------------------
            //e2----------------------------
            g.drawImage(e2_top,480,270,320,35,null);
            g.drawImage(e2_bottom,480,415,320,35,null);
            if(player.getCurrentRoomY() + 2<map.getMapHeight()) {
                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() + 2].isWallowed == false) {
                    g.drawImage(e2_left, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_left, 500, 270, 320, 181, null);
                }

                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() + 2].isEallowed == false) {
                    g.drawImage(e2_right, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e2_left, 780, 180, 180, 360, null);
                g.drawImage(e2_right, 780, 180, 180, 360, null);
            }
            g.drawImage(e2_front,541,305,320,180,null);
            //------------------------------
        }else if(degree==90) {
            //e0----------------------------
            g.drawImage(e0_bottom, 0, 540, 1280, 180, null);
            g.drawImage(e0_top, 0, 0, 1280, 180, null);
            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isNallowed == false) {
                g.drawImage(e0_left, 0, 0, 320, 720, null);
            } else {
                //tunnel
            }


            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isSallowed == false) {
                g.drawImage(e0_right, 960, 0, 320, 720, null);
            } else {
                //tunnel
            }
            //------------------------------
            //e1----------------------------
            g.drawImage(e1_top, 320, 180, 640, 90, null);
            g.drawImage(e1_bottom, 320, 450, 640, 90, null);
            if(player.getCurrentRoomX() + 1<map.getMapHeight()) {
                if (map.getRooms()[player.getCurrentRoomX() + 1][player.getCurrentRoomY()].isNallowed == false) {
                    g.drawImage(e1_left, 320, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_left, 500, 270, 320, 181, null);
                }
                if (map.getRooms()[player.getCurrentRoomX() + 1][player.getCurrentRoomY()].isSallowed == false) {
                    g.drawImage(e1_right, 780, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e1_left, 320, 180, 180, 360, null);
                g.drawImage(e1_right, 780, 180, 180, 360, null);
            }
            //------------------------------
            //e2----------------------------
            g.drawImage(e2_top,480,270,320,35,null);
            g.drawImage(e2_bottom,480,415,320,35,null);
            if(player.getCurrentRoomX() + 2<map.getMapHeight()) {
                if (map.getRooms()[player.getCurrentRoomX() + 2][player.getCurrentRoomY()].isNallowed == false) {
                    g.drawImage(e2_left, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_left, 500, 270, 320, 181, null);
                }

                if (map.getRooms()[player.getCurrentRoomX() + 2][player.getCurrentRoomY()].isSallowed == false) {
                    g.drawImage(e2_right, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e2_left, 780, 180, 180, 360, null);
                g.drawImage(e2_right, 780, 180, 180, 360, null);
            }
            g.drawImage(e2_front,541,305,320,180,null);
            //------------------------------
        }if(degree==180) {
            //e0----------------------------
            g.drawImage(e0_bottom, 0, 540, 1280, 180, null);
            g.drawImage(e0_top, 0, 0, 1280, 180, null);
            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isEallowed == false) {
                g.drawImage(e0_left, 0, 0, 320, 720, null);
            } else {
                //tunnel
            }


            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isWallowed == false) {
                g.drawImage(e0_right, 960, 0, 320, 720, null);
            } else {
                //tunnel
            }
            //------------------------------
            //e1----------------------------
            g.drawImage(e1_top, 320, 180, 640, 90, null);
            g.drawImage(e1_bottom, 320, 450, 640, 90, null);
            if(player.getCurrentRoomY()-1>=0) {
                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() - 1].isEallowed == false) {
                    g.drawImage(e1_left, 320, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_left, 500, 270, 320, 181, null);
                }
                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() - 1].isWallowed == false) {
                    g.drawImage(e1_right, 780, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e1_left, 320, 180, 180, 360, null);
                g.drawImage(e1_right, 780, 180, 180, 360, null);
            }
            //------------------------------
            //e2----------------------------
            g.drawImage(e2_top,480,270,320,35,null);
            g.drawImage(e2_bottom,480,415,320,35,null);
            if(player.getCurrentRoomY()-2>=0) {
                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() - 2].isEallowed == false) {
                    g.drawImage(e2_left, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_right, 500, 270, 320, 181, null);
                }

                if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY() - 2].isWallowed == false) {
                    g.drawImage(e2_right, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e2_left, 780, 180, 180, 360, null);
                g.drawImage(e2_right, 780, 180, 180, 360, null);
            }

            g.drawImage(e2_front,541,305,320,180,null);
            //------------------------------
        }else if(degree==270) {
            //e0----------------------------
            g.drawImage(e0_bottom, 0, 540, 1280, 180, null);
            g.drawImage(e0_top, 0, 0, 1280, 180, null);
            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isSallowed == false) {
                g.drawImage(e0_left, 0, 0, 320, 720, null);
            } else {
                //tunnel
            }


            if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isNallowed == false) {
                g.drawImage(e0_right, 960, 0, 320, 720, null);
            } else {
                //tunnel
            }
            //------------------------------
            //e1----------------------------
            g.drawImage(e1_top, 320, 180, 640, 90, null);
            g.drawImage(e1_bottom, 320, 450, 640, 90, null);
            if(player.getCurrentRoomX()-1>=0) {
                if (map.getRooms()[player.getCurrentRoomX()-1][player.getCurrentRoomY()].isSallowed == false) {
                    g.drawImage(e1_left, 320, 180, 180, 360, null);
                } else {
                    g.drawImage(e1_tunnel_left, 500, 270, 320, 181, null);
                }
                if (map.getRooms()[player.getCurrentRoomX()-1][player.getCurrentRoomY()].isNallowed == false) {
                    g.drawImage(e1_right, 780, 180, 180, 360, null);
                }else{
                    g.drawImage(e1_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e1_right, 780, 180, 180, 360, null);
                g.drawImage(e1_left, 780, 180, 180, 360, null);
            }
            //------------------------------
            //e2----------------------------
            g.drawImage(e2_top,480,270,320,35,null);
            g.drawImage(e2_bottom,480,415,320,35,null);
            if(player.getCurrentRoomX()-2>=0) {
                if (map.getRooms()[player.getCurrentRoomX()-2][player.getCurrentRoomY()].isSallowed == false) {
                    g.drawImage(e2_left, 780, 180, 180, 360, null);
                }else{
                    //g.drawImage(e2_tunnel_left, 500, 270, 320, 181, null);
                }


                if (map.getRooms()[player.getCurrentRoomX() - 2][player.getCurrentRoomY()].isNallowed == false) {
                    g.drawImage(e2_right, 780, 180, 180, 360, null);
                } else {
                    //g.drawImage(e2_tunnel_right, 500, 270, 320, 181, null);
                }
            }else{
                g.drawImage(e2_right, 780, 180, 180, 360, null);
                g.drawImage(e2_left, 780, 180, 180, 360, null);
            }

            g.drawImage(e2_front,541,305,320,180,null);
            //------------------------------
        }

        //Zeigt das gezeichnette an. Das ist der Trick vom DoubleBuffer, man zeichnet erst alles auf ein
        //unsichtbares Canvas und macht es dann auf einen Schlag sichtbar.
        bs.show();
    }


    public void calculate(){
        if(delay>0){
            delay--;
        }
        if(delay==0&&isPlayerLocked == false) {
            if (keyPressedBool[87] == true) { //W -> vorne, Spieler -> Y
                if(player.getCurrentRoomY()+1<map.getMapHeight()){
                    if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isNallowed == true) {
                        player.setCurrentRoomY(player.getCurrentRoomY() + 1);
                        delay = standardDelay;
                        System.out.println(player.getCurrentRoomX()+","+player.getCurrentRoomY());
                    }
                }
            }
            if (keyPressedBool[83] == true) { //S -> unten, Spieler -> Y
                if(player.getCurrentRoomY()-1>=0d) {
                    if (map.getRooms()[player.getCurrentRoomX()][player.getCurrentRoomY()].isSallowed == true) {
                        player.setCurrentRoomY(player.getCurrentRoomY() - 1);
                        delay = standardDelay;
                        System.out.println(player.getCurrentRoomX()+","+player.getCurrentRoomY());
                    }
                }
            }
            if (keyPressedBool[68] == true) { //D -> nach rechts drehen
                if(degree==0){
                    degree=90;
                }else if(degree==90){
                    degree=180;
                }else if(degree==180){
                    degree=270;
                }else if(degree==270){
                    degree=0;
                }
                delay=standardDelay;
                System.out.println("rechts "+degree);
            }
            if (keyPressedBool[65] == true) { //A -> nach links drehen
                if(degree==0){
                    degree=270;
                }else if(degree==270){
                    degree=180;
                }else if(degree==180){
                    degree=90;
                }else if(degree==90){
                    degree=0;
                }
                delay=standardDelay;
                System.out.println("links "+degree);
            }


        }

    }
    public void keyPressed (KeyEvent e){
        keyPressedBool[e.getKeyCode()] = true;
    }

    public void keyReleased (KeyEvent e){
        keyPressedBool[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e){

    }
    public void setUpMap(){
        map.getRooms()[0][0] =new Room(true,false,false,false,false);
        map.getRooms()[0][1] =new Room(true,true,false,true,false);
        map.getRooms()[0][2] =new Room(true,true,false,false,false);
        map.getRooms()[0][3] =new Room(false,true,false,true,false);
        map.getRooms()[1][0] =new Room(false,false,false,true,false);
        map.getRooms()[1][1] =new Room(false,false,true,false,false);
        map.getRooms()[1][2] =new Room(false,false,false,true,false);
        map.getRooms()[1][3] =new Room(false,false,true,true,false);
        map.getRooms()[2][0] =new Room(true,false,true,true,false);
        map.getRooms()[2][1] =new Room(true,true,false,true,false);
        map.getRooms()[2][2] =new Room(true,true,true,false,false);
        map.getRooms()[2][3] =new Room(false,true,true,false,false);
        map.getRooms()[3][0] =new Room(false,false,true,false,false);
        map.getRooms()[3][1] =new Room(true,false,true,false,false);
        map.getRooms()[3][2] =new Room(true,true,false,false,false);
        map.getRooms()[3][3] =new Room(false,false,false,false,false);

    }

} // end of class Main

