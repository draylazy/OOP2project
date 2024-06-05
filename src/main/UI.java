/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import object.SuperObject;
import object.obj_Heart;

/**
 *
 * @author Dray
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font Pixel;
    ImageIcon gifBackground;
    
     //Heart
    BufferedImage heart_full, heart_half, heart_blank;
    
    public int commandNum = 0;
    
    public UI(GamePanel gp){
       this.gp = gp;
       
       
       InputStream is = getClass().getResourceAsStream("/res/MP16OSF.ttf");
        try {
            Pixel = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            gifBackground = new ImageIcon(getClass().getResource("/titleImage/Lava Animation.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //CREATE HUD OBJECT
        SuperObject heart = new obj_Heart(gp);
        heart_full = heart.image;
         heart_half = heart.image2;
         heart_blank = heart.image3;
    }
    
     public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(Pixel);
        g2.setColor(Color.WHITE);
        
        //Title Screen
        if (gp.gameState == gp.titleScreen){
            drawTitleScreen();
        }
        
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverState();
        }
        
    }
     public void drawPlayerLife(){
         
         gp.player.life = 0;
         
         int x = gp.tileSize/2;
         int y = gp.tileSize/2;
         int i = 0;
         
         //BLANK HEART
         while(i < gp.player.maxLife/2){
             g2.drawImage(heart_blank, x, y, null);
             i++; 
             x += gp.tileSize;
         }
         
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;
         
         while(i < gp.player.life){
              g2.drawImage(heart_half, x, y, null);
              i++;
              if(i < gp.player.life){
              g2.drawImage(heart_full, x, y, null);
              x += gp.tileSize;
              }
         }
         
     }
     public void drawTitleScreen(){
        if (gifBackground != null) {
            // Draw the animated GIF as the background
            Image gifImage = gifBackground.getImage();
            g2.drawImage(gifImage, 0, 0, gp.screenWidth, gp.screenHeight, (ImageObserver) null);
        } else {
            // Fallback to a solid color if the GIF fails to load
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }
         //Title
         g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
         String text = "DUNGEON ESCAPE";
         int x = getLengthXforCenterText(text);
         int y = gp.tileSize*3;
        //SHADOW
        g2.setColor(Color.ORANGE);
        g2.drawString(text, x+5, y+5);

        //COLOR
         g2.setColor(Color.red);
         g2.drawString(text, x, y);
        
         //CHARACTER
         x = gp.screenWidth/2 - (gp.tileSize*2)/2;
         y += gp.tileSize*2;
         g2.drawImage(gp.player.down1, x,y,gp.tileSize*2, gp.tileSize*2, null);
         
         //MENU
         g2.setColor(Color.white);
         g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
         
         text = "PLAY GAME";
         x = gp.screenWidth/4 - (gp.tileSize*6)/2;
         y += gp.tileSize*2-10;
         g2.drawString(text, x, y);
         if(commandNum == 0){
             g2.drawString(">", x-(gp.tileSize*2)/5, y);
         }
         text = "LOAD GAME";
         x = gp.screenWidth/4 - (gp.tileSize*6)/2;
         y += gp.tileSize;
         if(commandNum == 1){
             g2.drawString(">", x-(gp.tileSize*2)/5, y);
         }
         g2.drawString(text, x, y);
         text = "QUIT";
         x = gp.screenWidth/4 - (gp.tileSize*6)/2;
         y += gp.tileSize;
         if(commandNum == 2){
             g2.drawString(">", x-(gp.tileSize*2)/5, y);
         }
         g2.drawString(text, x, y);
         
     }
     
     public void drawPauseScreen(){
         String text = "PAUSE";
         
         g2.setFont(g2.getFont().deriveFont(Font.PLAIN,100F));
        int x = getLengthXforCenterText(text);        
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
     }
     public int getLengthXforCenterText(String text){
         int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
         int x = gp.screenWidth/2 - length/2;
         return x;
     }
     public void drawGameOverState(){
         
         g2.setColor(new Color(0,0,0,150));
         g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
         
         int x;
         int y;
         String text;
         g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
         
         
         text = "Game Over";
         //Shadow
         g2.setColor(Color.BLACK);
         x = getLengthXforCenterText(text);
         y = gp.tileSize*4;
         //Main
         g2.setColor(Color.white);
         g2.drawString(text, x-4, y-4);
         
         //Retry
         g2.setFont(g2.getFont().deriveFont(50F));
         text = "Retry";
         x = getLengthXforCenterText(text);
         y += gp.tileSize*4;
         g2.drawString(text, x, y);
         if(commandNum == 0) {
             g2.drawString(">", x-40, y);
         }
         
         //Exit to Title Screen
         text = "Quit";
         x = getLengthXforCenterText(text);
         y += 55;
         g2.drawString(text, x, y);
         if(commandNum == 1) {
             g2.drawString(">", x-40, y);
         }
     }
     
}
