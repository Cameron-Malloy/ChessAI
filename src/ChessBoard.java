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

    public static void main(String[] args) throws IOException {
        BufferedImage all = ImageIO.read(new File("C:\\Users\\camma\\Downloads\\chess.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        Pieces bRook = new Pieces(0, 0, false, "rook", ps);
        Pieces bKnight = new Pieces(1, 0, false, "knight", ps);
        Pieces bBishop = new Pieces(2, 0, false, "bishop", ps);
        Pieces bQueen = new Pieces(3, 0, false, "queen", ps);
        Pieces bKing = new Pieces(4, 0, false, "king", ps);
        Pieces bBishop2 = new Pieces(5, 0, false, "bishop", ps);
        Pieces bKnight2 = new Pieces(6, 0, false, "knight", ps);
        Pieces bRook2 = new Pieces(7, 0, false, "rook", ps);
        Pieces bPawn1 = new Pieces(1, 1, false, "pawn", ps);
        Pieces bPawn2 = new Pieces(2, 1, false, "pawn", ps);
        Pieces bPawn3 = new Pieces(3, 1, false, "pawn", ps);
        Pieces bPawn4 = new Pieces(4, 1, false, "pawn", ps);
        Pieces bPawn5 = new Pieces(5, 1, false, "pawn", ps);
        Pieces bPawn6 = new Pieces(6, 1, false, "pawn", ps);
        Pieces bPawn7 = new Pieces(7, 1, false, "pawn", ps);
        Pieces bPawn8 = new Pieces(0, 1, false, "pawn", ps);

        Pieces wRook = new Pieces(0, 7, true, "rook", ps);
        Pieces wKnight = new Pieces(1, 7, true, "knight", ps);
        Pieces wBishop = new Pieces(2, 7, true, "bishop", ps);
        Pieces wQueen = new Pieces(3, 7, true, "queen", ps);
        Pieces wKing = new Pieces(4, 7, true, "king", ps);
        Pieces wBishop2 = new Pieces(5, 7, true, "bishop", ps);
        Pieces wKnight2 = new Pieces(6, 7, true, "knight", ps);
        Pieces wRook2 = new Pieces(7, 7, true, "rook", ps);
        Pieces wPawn1 = new Pieces(1, 6, true, "pawn", ps);
        Pieces wPawn2 = new Pieces(2, 6, true, "pawn", ps);
        Pieces wPawn3 = new Pieces(3, 6, true, "pawn", ps);
        Pieces wPawn4 = new Pieces(4, 6, true, "pawn", ps);
        Pieces wPawn5 = new Pieces(5, 6, true, "pawn", ps);
        Pieces wPawn6 = new Pieces(6, 6, true, "pawn", ps);
        Pieces wPawn7 = new Pieces(7, 6, true, "pawn", ps);
        Pieces wPawn8 = new Pieces(0, 6, true, "pawn", ps);


        JFrame jf = new JFrame();
        jf.setBounds(10, 10, 512, 512);
        jf.setUndecorated(true);
        jf.setTitle("The Best Chess Board");
        jf.getContentPane().add(new ChessBoard());
        jf.setLocationRelativeTo(null);
        jf.setBackground(Color.GRAY);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(new Color(235, 235, 208));
                        } else {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Pieces p : ps) {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("king")) {
                        ind = 0;
                    }
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
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX()/64, e.getY()/64);
                jf.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jf.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null){
                    selectedPiece.x=e.getX()-32;
                    selectedPiece.y=e.getY()-32;
                    jf.repaint();


                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        jf.setVisible(true);

    }
    public static Pieces getPiece(int x, int y){
        int xp = x/64;
        int yp = y/64;
        for(Pieces p : ps){
            if(p.xp == xp&&p.yp==yp){
                return p;
            }
        }
        return null;
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


