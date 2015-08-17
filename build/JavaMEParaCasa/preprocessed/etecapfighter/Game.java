/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etecapfighter;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;

/**
 *,
 * @author u14287
 */
public class Game extends GameCanvas implements Runnable {
    
    private boolean isPlaying;
    private Images imgs;
    private Sprite player1;
    private Sprite player2;
    private Sprite fireBackground;
    private LayerManager lmgr;
    private long delay = 120;
    private int width = getWidth(), height = getHeight(), xMove, yMove, yJump;
    private boolean jumping = false, falling = false;
            
    public Game() {
        super(true);
        
        try {
            imgs = new Images();
        } catch(Exception e) {
            
        }
        //Image i = Image.createImage("/resources/rogers_sprite.png");
        player1 = new Sprite(imgs.getImgRogerio(), 126, 128);
        player1.defineReferencePixel(126/2, 128/2);
        xMove = width - 250;        
        yMove = height - 140;
        yJump = yMove - 80;
        
        //Fire background
        fireBackground = new Sprite(imgs.getFireBackground(), 239, 321);
        fireBackground.defineReferencePixel(0, 0);
        fireBackground.setRefPixelPosition(0, 0);
        
        lmgr = new LayerManager();
        lmgr.append(player1);
        lmgr.append(fireBackground);
    }
    
    public void start() {
        isPlaying = true;
        
        Thread t = new Thread(this);
        t.start();
    }
    
    public void stop() {
        isPlaying = false;
    }
    
    public void run() {
        Graphics  g = getGraphics();
        while (isPlaying) {
            if (jumping) {
                if (yMove > yJump && falling == false) {
                    yMove -= 25;
                } 
                
                if (yMove <= yJump || falling) {
                    falling = true;
                    yMove += 25;
                } 
                                
                if (yMove == height - 140) {
                    jumping = false;
                    falling = false;
                }
                
                player1.setPosition(xMove, yMove);
                
            } else {                
                leTeclado();
                player1.nextFrame();
            }
            
            drawScreen(g);            
            fireBackground.nextFrame();
            
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }
        }
    }
    
    private void leTeclado() {
        int keyStates = getKeyStates();
        
        
        if ((keyStates & GameCanvas.RIGHT_PRESSED) != 0) {
            player1.setTransform(Sprite.TRANS_NONE);
            if (xMove + 100 < width) {                
                xMove += 5;
                
            }
        } else if ((keyStates & GameCanvas.LEFT_PRESSED) != 0) {
            player1.setTransform(Sprite.TRANS_MIRROR);
            if (xMove - 5 > -30) {
                xMove -= 5;
                
            }
        } else if ((keyStates & GameCanvas.UP_PRESSED) != 0) {            
            jumping = true;
        }
        
        player1.setPosition(xMove, yMove);
    }
    
    private void drawScreen(Graphics g) {        
        lmgr.paint(g, 0, 0);
        flushGraphics();
    }
    
}
