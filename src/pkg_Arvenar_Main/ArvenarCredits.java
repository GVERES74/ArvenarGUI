/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.ESCAPE;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javax.swing.Timer;



/**
 *
 * @author TE332168
 */
public class ArvenarCredits{

Pane creditsPane = new StackPane();
Scene creditsScene;
Image bkgImage = new Image("img/bkg_credits.jpg", 1920 , 1080, true, false, true);
static Text escText;
static Text creditsText; //Itt ne példányosítsd!
static Timer delayer;
String str;
static double g = 0.01;
BufferedReader creditsBR;
ArvenarFXMain arvfxmain = new ArvenarFXMain();
static ArvenarEffects arveffects = new ArvenarEffects();
ArvenarFonts arvfonts = new ArvenarFonts();


    public ArvenarCredits() throws InterruptedException {
        
        creditsText = new Text(); //A konstruktorban példányosítsd, különben mindig új példányt rak a creditsPane-re, ha ki- majd visszalépsz.
                
        creditsPane.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                
        escText = arvfonts.newTextFormat("Press ESC to back", escText, arveffects.setGlowEffect(0.5), arveffects.reflectionEffect, Font.font("Verdana", FontWeight.BOLD, 26), Color.CORAL, -450, 300);
        
        creditsText = arvfonts.newTextFormat("", creditsText, arveffects.setGlowEffect(0.1), arveffects.shadowEffect, Font.font("Verdana", FontWeight.BOLD, 20), Color.AQUAMARINE, -100, 550);
      
      
        creditsPane.getChildren().addAll(escText, showCreditsText());
        creditsScene = new Scene(creditsPane);
        
        //moveText();
        
        new GVTimer().textMover(50);
        new GVTimer().textScaler(100);
                
        creditsScene.setOnKeyPressed(event ->{
            switch  (event.getCode()){
                case ESCAPE: GVTimer.delayer.stop(); ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven); break;

                case ENTER:  
                          
        }            
    });
        
    }
    
    public Text showCreditsText() throws InterruptedException{
        
     try {
                creditsBR = new BufferedReader(new FileReader("c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\credits.txt"));
                    
            try { 
                creditsBR.skip(1);
            } catch (IOException ex) {
                Logger.getLogger(ArvenarCredits.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while((str = creditsBR.readLine()) != null){
                    
                    creditsText.setText(creditsText.getText()+"\n"+str);//Több soros szöveg: előző + új
                                                            
                }
                
                
            } catch (IOException ex) {
                Logger.getLogger(ArvenarCredits.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarCredits.class.getName()).log(Level.SEVERE, null, ex);
        
                
                
        }    
        return creditsText;
               
    }
   
    public static void moveText(){
                //System.out.println("Y-pos: "+creditsText.getTranslateY()); //developer mode
                      creditsText.setTranslateY(creditsText.getTranslateY()-1);
                      
                    /*if (creditsText.getTranslateY() < 300){
                        
                        creditsText.setScaleX(creditsText.getScaleX()-0.001);
                        creditsText.setScaleY(creditsText.getScaleY()-0.001);
                    }*/    
                      
                    if (creditsText.getTranslateY() < -600){
                            creditsText.setTranslateY(560);
                  
                    }
                        
            }
   
        
    public static void vibraText(){
              
            if(escText.getScaleY() < -1.0) {
                g = g + 0.01;
            }
              
            if(escText.getScaleY() > 0.9) {
                g = g +-0.01;
            }
            
            escText.setScaleY(escText.getScaleY()+g);
              System.out.println("Value: "+g);
                                      
          
        
     }  
        
        
        public Scene credits_Scene(){
        return creditsScene;
    }
    
   
  

    
    
}
  
  
    

