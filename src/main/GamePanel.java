/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Player;
import environment.EnvironmentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author Tricko
 */
public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    
    final int originalTileSize = 16;
    final int scale = 4;
    
    public final int tileSize = originalTileSize*scale;
    
    public final int maxScreenCol =16;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    //WORLD SETTING
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 30;
    public final int worldWidth =tileSize * maxWorldCol;
    public final int worldHeight =tileSize * maxWorldRow;
    
    //FPS
    int FPS =60;
    
    
    
    
    //SOUND
    sound sound = new sound();
    
    //Tiles
    TileManager tileM = new TileManager(this);
    
    KeyHandler keyH = new KeyHandler(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    
    Thread gameThread;
    
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    public Player player = new Player(this, keyH);
    
    //SYSTEM
    public UI ui = new UI(this);
    //GAME STATE
    Graphics2D g2;
    public int gameState;
    public final int titleScreen = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    
    
    
    //set player's default position
    int playerX = 50;
    int playerY = 50;
    int playerSpeed = 8;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    
    public void playMusic(int i) {
        
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    
    public void stopMusic() {
        sound.stop();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        eManager.setup();
        
        gameState = 0;
    }
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime()+ drawInterval;
        
        while(gameThread != null){
        
            update();
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; 
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void update(){
       
       if (gameState == playState){
           player.update();
       }
       if (gameState == pauseState){
       }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        
        
        if (gameState == titleScreen){
            ui.draw(g2);
        }
        else{
            tileM.draw(g2);
             player.draw(g2);
        
             eManager.draw(g2);
            ui.draw(g2);
        }
        
        g2.dispose();
        
        
        //title screen
       
    }
    
}
