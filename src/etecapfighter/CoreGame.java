/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etecapfighter;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 *
 * @author User
 */
public class CoreGame extends GameCanvas implements Runnable{
    public boolean isPlay;
    public long delay;
    private int currentX;
    private int currentY;
    private int height;
    private int width;
    
    private Image imgMira;
    private Image imgMenina;
    private Image imgTijoloFundo;
    
    private Sprite spMenina;
    // gera variavel para definir o fundo
    private TiledLayer fundo;
    
    public int florFundo;
    public int florseq001Delay = 200;
    public int[] florseq001 = {9, 10, 11, 12, 13};
 
    private LayerManager lmgr;
       
    public CoreGame() throws IOException {
        super(true);
        delay = 40;
        height = getHeight();
        width =  getWidth();
        currentX = width /2;
        currentY = height /2;
        
        try {
            imgMira = Image.createImage("resources/mira.png");
            imgMenina = Image.createImage("resources/run.png");
            imgTijoloFundo = Image.createImage("resources/topview_tiles.png");
        } catch (java.io.IOException e) {
            System.err.println("Não foi possivel achar arquivo png");
        }
        spMenina = new Sprite(imgMenina,75,76);
        
        // Change the reference pixel to the middle of sprite
        spMenina.defineReferencePixel(32 / 2, 32 / 2);
        // Center the sprite on the canvas
        // (center of sprite is now in center of display)
        spMenina.setRefPixelPosition(width/ 2, height / 2);
      
        getFundo();
        
       // spMenina.setPosition(currentX,currentY );
        lmgr = new LayerManager();
        lmgr.append(spMenina);
        lmgr.append(fundo);
    }

    public void start() {
        isPlay = true;
        Thread t = new Thread(this);
        t.start();
    }
    
    public void stop() {
        isPlay = false;
    }
    
    public void run() {
       Graphics  g = getGraphics();
       
       while (isPlay){
           // le teclado
           leTeclado();
           // desenha a tela
           drawScreen(g);
           try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }
       }
       
    }
    
    public void leTeclado(){
       int keyStates;
       
       
       
       keyStates = getKeyStates(); // le estado das teclas 
       
       if ((keyStates & GameCanvas.LEFT_PRESSED) != 0) {
           spMenina.setTransform(Sprite.TRANS_MIRROR);
           spMenina.nextFrame();
	   currentX = Math.max(0,currentX -10);
           if ((currentX ) == 0)
               fundo.move(1, 0);
       }else
       if ((keyStates & GameCanvas.RIGHT_PRESSED) != 0) {
           spMenina.setTransform(Sprite.TRANS_NONE);
           spMenina.nextFrame();
           if (currentX + 75 < width){
             currentX = Math.min(width, currentX + 10);            
           }  
           if ((currentX +75) == width)
               fundo.move(-1, 0);
       } else
       if ((keyStates & GameCanvas.UP_PRESSED) != 0) {
	  currentY = Math.max(0,currentY -10);
       } else
       if ((keyStates & GameCanvas.DOWN_PRESSED) != 0) {
          if (currentY + 10 < height)
              currentY = Math.min(height, currentY + 10);
       } else {
           ;
       }
       spMenina.setPosition(currentX, currentY);

    }
    
    public void drawScreen(Graphics g){
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(),getHeight());
    
        g.drawImage(imgMira, 10,10,0);
        
        lmgr.paint(g, 0,-50);
        g.drawImage(imgMira, 10,80,0);
        flushGraphics();
    }
   
 private void getFundo() throws java.io.IOException {//GEN-BEGIN:|6-getter|0|6-preInit
        if (fundo == null) {
            
            fundo = new TiledLayer(20, 22, imgTijoloFundo, 16, 16);//GEN-BEGIN:|6-getter|1|6-midInit
            florFundo = fundo.createAnimatedTile(florseq001[0]);
            int[][] tiles = {
                {0, 0, 0, 16, 0, 76, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 0, 0, 0, 0, 0, 0, 0},
                {0, 62, 62, 62, 0, 62, 62, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 62, 0, 62, 0,  62, 0, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 62, 62, 62, 0, 62, 0, 62, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 62, 0, 0, 0,   62, 62,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, florFundo, 16, florFundo, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 0},
                {5, 62, 62, 62, 0, 62, 69, 16, 0, 0, 2, 0, 69, 16, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                
            };
            
            for (int row = 0; row < 22; row++) {
                for (int col = 0; col < 20; col++) {
                    fundo.setCell(col, row, tiles[row][col]);
                }
            }
        }

    }
}
