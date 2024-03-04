import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Pieces extends ChessBoard {
    int xp;
    int yp;
    int x;
    int y;

    public static boolean color;

    boolean isWhite;
    LinkedList<Pieces> ps;
    String name;
    JFrame winScreen;
    JButton restart;
    JLabel winText;
    String winColor;
    int pointsWorth;
    public static int blackScore;
    public static int whiteScore;



    public Pieces(int xp, int yp, boolean isWhite, String n, int points, LinkedList<Pieces> ps) {
        this.xp = xp;
        this.yp = yp;
        x=xp*64;
        y=yp*64;
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
        ps.add(this);
        color = true;
        winScreen = new JFrame();
        restart = new JButton();
        winText = new JLabel();
        winColor = new String();
        pointsWorth = points;
        blackScore = 0;
        whiteScore= 0;
    }
    public void move(int xp, int yp){
        if(Objects.equals(ChessBoard.getName(xp * 64, yp * 64), "king")){
            if(color){
                winColor = "White";
            }
            if(!color){
                winColor = "Black";
            }
                winScreen().setVisible(true);
        }
        if (ChessBoard.getPiece(xp * 64, yp * 64) != null) {//is there something in the spot moving to
                if (ChessBoard.getPiece(xp * 64, yp * 64).isWhite != isWhite) {//is the piece moving onto different color piece?
                    ChessBoard.getPiece(xp * 64, yp * 64).kill();//if so kill

                } else {
                    x = this.xp * 64;
                    y = this.yp * 64;

                    return;
                }
            }


        lastColor();

        this.xp=xp;
        this.yp=yp;
        x=xp*64;
        y=yp*64;

    }
        public void kill(){
            if(ChessBoard.getPiece(xp * 64, yp * 64).isWhite == true){
                blackScore += Pieces.getPiece(xp * 64, yp * 64).pointsWorth;
                trackerScreen();
            }else
                whiteScore += Pieces.getPiece(xp * 64, yp * 64).pointsWorth;
                trackerScreen();
            size = size-1;
            ps.remove(this);
        }
        //Find the color of last piece moved
        //White is true, Black is false
        public void lastColor(){
            color = !color;
        }
        public JFrame winScreen() {
            removeList();
            size = 32;
            winScreen.setBounds(440, 65, 700, 700);
            restart.setAlignmentX(0);
            restart.setAlignmentY(0);
            restart.setBounds(220, 275, 250, 100);
            restart.setText("Press to restart");
            restart.setFocusable(false);
            restart.setBorder(BorderFactory.createBevelBorder(5));
            restart.setForeground(Color.cyan);
            restart.setBackground(Color.darkGray);
            winScreen.add(restart);

            if(Objects.equals(winColor, "Black")){
                winText.setText(winColor + " Wins With " + blackScore +" Points!");
            }else winText.setText(winColor + " Wins With " + whiteScore +" Points!");


            winText.setAlignmentX(0);
            winText.setAlignmentY(0);
            winText.setBounds(275,200, 300, 100);
            winScreen.add(winText);

            winScreen.setLayout(null);
            winScreen.getContentPane().setBackground(Color.lightGray);
            winScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            winScreen.setLocationRelativeTo(null);
            winScreen.setVisible(true);
            restart.addActionListener(e -> winScreen.setVisible(false));
            restart.addActionListener(e -> {
                try {
                    run();
                } catch (AWTException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            return winScreen;
        }
}
