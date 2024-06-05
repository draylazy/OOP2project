/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Lino
 */
public class obj_Heart extends SuperObject{
    
    GamePanel gp; 
    public obj_Heart(GamePanel gp){
        this.gp = gp;
        
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Health/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Health/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Health/heart_blank.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
