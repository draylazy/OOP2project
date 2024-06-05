/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Tricko
 */
public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
   
    public KeyHandler (GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
       //TITLE SCREEN
       if(gp.gameState == gp.titleScreen){
           if(code == KeyEvent.VK_W){
           gp.ui.commandNum--; 
           if (gp.ui.commandNum < 0){
             gp.ui.commandNum = 2;
            }
          }
        
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2){
             gp.ui.commandNum = 0;
            }
         }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if(gp.ui.commandNum == 2){
               System.exit(0); 
            }
        }
       } 
       //PLAY STATE
       else if (gp.gameState == gp.playState){
         if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
        }    
     }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
           if(code == KeyEvent.VK_P){
               gp.gameState = gp.playState;
           }
       }
        //GAME OVER STATE
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
          if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.gameState = gp.gameOverState;
             }
            }
        }
    }
     public void gameOverState(int code){
        if(code == KeyEvent.VK_W) {
           gp.ui.commandNum--;
           if(gp.ui.commandNum < 0) {
               gp.ui.commandNum = 1;
           }
        }
        if(code == KeyEvent.VK_S) {
           gp.ui.commandNum++;
           if(gp.ui.commandNum < 1) {
               gp.ui.commandNum = 0;
           }
        }
        
       if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 1) {
                gp.gameState = gp.titleScreen;
            }
         }
    } 

    @Override
    public void keyReleased(KeyEvent e) {
       
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        
    }
     
}
