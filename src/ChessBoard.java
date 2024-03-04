import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ChessBoard extends JPanel {
    public static LinkedList<Pieces> ps = new LinkedList<>();
    public static Pieces selectedPiece=null;
    public static int size = 32;

    public static void main(String[] args) throws IOException, AWTException {
        run();
        }
    public static void run() throws AWTException, IOException {
        JFrame jf = new JFrame();
            Robot bot = new Robot();
        int ind = 0;
        BufferedImage all = ImageIO.read(new File("C:\\Users\\camma\\Downloads\\chess.png"));
        Image[] imgs = new Image[12];

        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        Pieces bRook = new Pieces(0, 0, false, "rook",5, ps);
        Pieces bKnight = new Pieces(1, 0, false, "knight",3, ps);
        Pieces bBishop = new Pieces(2, 0, false, "bishop",3, ps);
        Pieces bQueen = new Pieces(3, 0, false, "queen",9, ps);
        Pieces bKing = new Pieces(4, 0, false, "king",0,  ps);
        Pieces bBishop2 = new Pieces(5, 0, false, "bishop", 3, ps);
        Pieces bKnight2 = new Pieces(6, 0, false, "knight", 3, ps);
        Pieces bRook2 = new Pieces(7, 0, false, "rook",5, ps);
        Pieces bPawn1 = new Pieces(1, 1, false, "pawn",1, ps);
        Pieces bPawn2 = new Pieces(2, 1, false, "pawn",1, ps);
        Pieces bPawn3 = new Pieces(3, 1, false, "pawn", 1, ps);
        Pieces bPawn4 = new Pieces(4, 1, false, "pawn",1, ps);
        Pieces bPawn5 = new Pieces(5, 1, false, "pawn",1, ps);
        Pieces bPawn6 = new Pieces(6, 1, false, "pawn",1, ps);
        Pieces bPawn7 = new Pieces(7, 1, false, "pawn",1, ps);
        Pieces bPawn8 = new Pieces(0, 1, false, "pawn",1, ps);

        Pieces wRook = new Pieces(0, 7, true, "rook",5, ps);
        Pieces wKnight = new Pieces(1, 7, true, "knight",3, ps);
        Pieces wBishop = new Pieces(2, 7, true, "bishop",3, ps);
        Pieces wQueen = new Pieces(3, 7, true, "queen",9, ps);
        Pieces wKing = new Pieces(4, 7, true, "king",0, ps);
        Pieces wBishop2 = new Pieces(5, 7, true, "bishop",3, ps);
        Pieces wKnight2 = new Pieces(6, 7, true, "knight",3, ps);
        Pieces wRook2 = new Pieces(7, 7, true, "rook",5, ps);
        Pieces wPawn1 = new Pieces(1, 6, true, "pawn",1, ps);
        Pieces wPawn2 = new Pieces(2, 6, true, "pawn",1, ps);
        Pieces wPawn3 = new Pieces(3, 6, true, "pawn",1, ps);
        Pieces wPawn4 = new Pieces(4, 6, true, "pawn",1, ps);
        Pieces wPawn5 = new Pieces(5, 6, true, "pawn",1, ps);
        Pieces wPawn6 = new Pieces(6, 6, true, "pawn",1, ps);
        Pieces wPawn7 = new Pieces(7, 6, true, "pawn",1, ps);
        Pieces wPawn8 = new Pieces(0, 6, true, "pawn",1, ps);


        jf.setBounds(10, 10, 512, 512);
        jf.setUndecorated(true);
        jf.setTitle("The Best Chess Board");
        jf.getContentPane().add(new ChessBoard());
        jf.setLocationRelativeTo(null);
        jf.setBackground(Color.GRAY);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JFrame start = new JFrame();


        JLabel label = new JLabel("Start Game Here  (Press alt + F4 to Quit Game)", JLabel.CENTER);
        label.setAlignmentX(0);
        label.setAlignmentY(0);
        label.setBounds(100, 100, 300, 100);

        JButton button = new JButton();
        button.setAlignmentX(0);
        button.setAlignmentY(0);
        button.setBounds(125, 200, 250, 100);
        button.setText("Start");
        button.setFocusable(false);
        button.setBorder(BorderFactory.createBevelBorder(5));
        button.setForeground(Color.cyan);
        button.setBackground(Color.darkGray);


        start.setBounds(100, 100, 512, 512);
        start.add(label);
        start.add(button);
        start.setLayout(null);
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setLocationRelativeTo(null);
        start.setVisible(true);
        button.addActionListener(e -> jf.setVisible(true));
        button.addActionListener(e -> trackerScreen());


        JPanel pn = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(new Color(111, 111, 111));
                        } else {
                            g.setColor(new Color(222, 222, 222));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Pieces p : ps) {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("queen")) {
                        ind = 1;
                    }
                    if (p.name.equalsIgnoreCase("bishop")) {
                        ind = 2;
                    }
                    if (p.name.equalsIgnoreCase("knight")) {
                        ind = 3;
                    }
                    if (p.name.equalsIgnoreCase("rook")) {
                        ind = 4;
                    }
                    if (p.name.equalsIgnoreCase("pawn")) {
                        ind = 5;
                    }
                    if (!p.isWhite) {
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);

                }
            }

        };
        jf.add(pn);

        jf.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println((getPiece(e.getX(), e.getY()).isWhite?"white":"black")+getPiece(e.getX(), e.getY()).name);
                selectedPiece = getPiece(e.getX(), e.getY());
                if (selectedPiece != null && Pieces.color != selectedPiece.isWhite) {
                    bot.mouseMove(770, 400);
                }
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null && Pieces.color == selectedPiece.isWhite) {
                    selectedPiece.move(e.getX() / 64, e.getY() / 64);
                    jf.repaint();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bot.mouseMove(770, 400);
            }

        });
        jf.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null && Pieces.color == selectedPiece.isWhite) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    jf.repaint();

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

        });
    }

    public static void trackerScreen(){
        JLabel score1 = new JLabel("Black's Score: " + Pieces.blackScore, JLabel.CENTER);
        score1.setAlignmentX(0);
        score1.setAlignmentY(0);
        score1.setForeground(Color.white);
        score1.setBackground(Color.white);

        JFrame tracker1 = new JFrame();
        tracker1.setBounds(512,106,513,40);
        tracker1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tracker1.setUndecorated(true);
        tracker1.getContentPane().setBackground(Color.black);
        tracker1.add(score1);
        tracker1.setVisible(true);
        tracker1.setLayout(null);

        JLabel score2 = new JLabel("White's Score: " + Pieces.whiteScore, JLabel.CENTER);
        score2.setAlignmentX(0);
        score2.setAlignmentY(0);
        score2.setForeground(Color.white);
        score2.setBackground(Color.white);

        JFrame tracker2 = new JFrame();
        tracker2.setBounds(511,658,514,40);
        tracker2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tracker2.setUndecorated(true);
        tracker2.getContentPane().setBackground(Color.black);
        tracker2.setVisible(true);
        tracker2.add(score2);
    }
    public static Pieces getPiece(int x, int y){//this returns the piece at a given x,y
        int xp = x/64;
        int yp = y/64;
        for(Pieces p : ps){
            if(p.xp == xp&&p.yp==yp){
                return p;
            }
        }
        return null;
    }
    public static String getName(int x, int y){//this returns the piece name
        int xp = x/64;
        int yp = y/64;
        for(Pieces p : ps){
            if(p.xp == xp&&p.yp==yp){
                return p.name;
            }
        }
        return null;
    }
    public static void removeList(){//this returns the piece name
        for(int i = 0; i < size; i++){
            ps.remove();
            }
        }
}


/* another old thing that I changed
    public void paint(Graphics gp) {
        boolean white = true;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (white) {
                    gp.setColor(Color.white.darker());
                } else {
                    gp.setColor(Color.black);
                }
                gp.fillRect(x * 64, y * 64, 64, 64);
                white = !white;
            }
            white = !white;
        }
    }
}

        /* This is the code I had before if I even want to go back
        gp.fillRect(50, 50, 600, 600);
        for(int x =50; x<=600; x+=150){
            for(int y = 50; y<=600; y+=150){
                gp.clearRect(x, y, 75, 75);
            }
        }
        for(int x =125; x<=600; x+=150){
            for(int y = 125; y<=600; y+=150){
                gp.clearRect(x, y, 75, 75);
            }
        }

         */


