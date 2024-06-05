/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author Tricko
 */
public final class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize*7;
        worldY = gp.tileSize*15;
        speed = 6;
        direction = "down";
        
        //LIFE
        maxLife = 3;
        life = maxLife;
    }
    
    public void getPlayerImage(){
       try{
           up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
           up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
           down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
           down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
           left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
           left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
           right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
           right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
           
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    public void update(){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed == true){
             direction = "up";
           
        }
        else if(keyH.downPressed == true){
            direction = "down";
        
        }
        else if(keyH.leftPressed == true){
            direction = "left";
            
        } 
        else if(keyH.rightPressed == true){
            direction = "right";
           
        }
            
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            if(collisionOn == false){
                switch(direction){
                    case "up":
                         worldY -= speed;
                        break;
                    case "down":
                         worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                         worldX += speed;
                        break;
                }
            }
            
         spriteCounter++;
         if(spriteCounter > 12){
             if(spriteNum == 1){
                 spriteNum = 2;
             }
             else if(spriteNum == 2){
                 spriteNum =1;
             }
             spriteCounter = 0;
         }
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
        }
         
    }
    public void draw(Graphics2D g2){
      
        BufferedImage image = null;
        
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
               
                break;
            case "down":
                 if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                 if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                 if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        
        
    }
}
