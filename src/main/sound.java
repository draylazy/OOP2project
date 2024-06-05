/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Dray
 */
public class sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];
    
    public sound() {
        soundURL[0] =  getClass().getResource("/sounds/TerrariaMusicUnderworld.wav");
    }
    
    public void setFile(int i) {
        
        try{
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioinput);
        }catch(Exception e){
            
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
    
}
