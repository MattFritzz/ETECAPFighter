/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etecapfighter;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author u14287
 */
public class Midlet extends MIDlet {
    
    private Display display;
    protected Game g;
    
    public Midlet() throws Exception {  
       display = Display.getDisplay(this);
       g = new Game();       
    }

    public void startApp() {        
        display.setCurrent(g);
        g.start();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
