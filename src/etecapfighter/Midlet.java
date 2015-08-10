/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etecapfighter;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author User
 */
public class Midlet extends MIDlet implements CommandListener {

    private Display display;
    private Command cmdExit;
    private CoreGame cg;
    
    Midlet() throws Exception{
        display = Display.getDisplay(this);
    
       
        if (( cg = new CoreGame())!= null){
            cmdExit = new Command("sair",Command.EXIT,0);
            cg.addCommand(cmdExit);
            cg.setCommandListener(this);
        }
       
        
    }
    
    public void startApp() {
       
        if (cg != null){
            display.setCurrent(cg);
            cg.start();
        }
       
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
         if (c==cmdExit) {
            System.gc();  // chama o grabage collector
            destroyApp(false);
            notifyDestroyed();
        }
    }
}
