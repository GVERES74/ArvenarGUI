/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;


import java.io.File;

import java.net.URISyntaxException;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

/**
 *
 * @author TE332168
 */
public class MPlayer {
 
    AudioClip mediaPlayer; 

 public String mPlayer_start(String title, Boolean playing, int repeat) {

     String theme = "src/music/"+title;

     Media music = new Media(Paths.get(theme).toUri().toString());    

     this.mediaPlayer = new AudioClip(music.getSource());


      //MediaPlayer stops playing music afer 5-10 seconds --> use AudioClip instead

             mediaPlayer.setCycleCount(repeat);
             this.mediaPlayer.play();


     return title;     

     }         

        public void mPlayer_stop(){
            
            if (mediaPlayer.isPlaying()){
                this.mediaPlayer.stop();
            }
            else System.out.println("Music is no playing");
        } 
}    
    
    
