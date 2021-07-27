/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawshape;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author isobapc
 */
public class DrawShape extends JPanel implements KeyListener,ActionListener{
    private static DrawShape ds = null;
    private int ballPosX;
    private int ballPosY;
    private int ballXdir;
    private int ballYdir;
    private int playerX;
    private int delay = 5;
    private Point mousePt;
    
    private boolean play = false;
    private Timer timer;
    private DrawShape(){
        ballPosX = 120;
        ballPosY = 350;
        ballXdir = -1;
        ballYdir = -2;
        playerX = 310;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }
    public static DrawShape getInstance(){
        if(ds == null){
            ds = new DrawShape();
        }
        return ds;
    }
    @Override
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        //border
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 892);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //rect
        g.setColor(Color.red);        
        g.fillRect(playerX,550,100,8);
        
        //ball
        g.setColor(Color.red);
        g.fillOval(ballPosX,ballPosY,20,20);
        
        if(ballPosY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString(" GAME OVER ",275,275);
            g.drawString(" Tekrar oynamak için ' p ' tuşuna basınız ! ",210,325);
        }
        g.dispose();
    }
    
    
       
    

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >=600){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_P){
            if(!play){
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir= -1;
                ballYdir= -2;
                playerX = 310;
                repaint();                
            }
        }
    }
    
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    
    public void moveLeft(){
        play = true;
        playerX -= 20; 
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();        
        if(play){
            if(new Rectangle(ballPosX , ballPosY , 20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;
            }
            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if(ballPosX < 0){
                ballXdir = -ballXdir;
            }
            if(ballPosY < 0){
                ballYdir = -ballYdir;
            }
            if(ballPosX > 670){
                ballXdir = -ballXdir;
            }
            
        }
        repaint();
    }

}
