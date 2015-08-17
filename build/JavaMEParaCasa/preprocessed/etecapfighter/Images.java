/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etecapfighter;

import javax.microedition.lcdui.Image;

/**
 *
 * @author u14287
 */
public class Images {
    private Image imgSpriteRogerio, imgSpriteEmilene, imgFireBackground;
    
    public Images() throws Exception {
        try {
            imgSpriteRogerio = Image.createImage("/resources/rogers_sprite.png");
            imgFireBackground = Image.createImage("/resources/fire_background.png");
        } catch (Exception e) {
            throw new Exception("Sem imagem!");
        }
    }
    
    public Image getImgRogerio() {
        return imgSpriteRogerio;
    }
    
    public Image getFireBackground() {
        return imgFireBackground;
    }

}
