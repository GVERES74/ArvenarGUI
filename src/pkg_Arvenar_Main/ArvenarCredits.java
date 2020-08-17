/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
import javafx.util.Duration;

/**
 *
 * @author TE332168
 */
public class ArvenarCredits {


Pane creditsPane = new StackPane();
Scene creditsScene;
Image bkgImage = new Image("img/bkg_credits.jpg", 1920 , 1080, true, false, true);
Text escText = new Text("Press ESC to back");
static Text creditsText = new Text();
TextFlow txtFlow = new TextFlow();
String str;
BufferedReader creditsBR;
Duration delay;

ArvenarFXMain arvfxmain = new ArvenarFXMain();
ArvenarEffects arveffects = new ArvenarEffects();

    public ArvenarCredits() throws InterruptedException{
        creditsPane.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                
        escText = arveffects.setTextEffect(escText, arveffects.glowEffect, arveffects.reflectionEffect, Font.font("Verdana", FontWeight.BOLD, 26), Color.CORAL, -450, 300);
        creditsText = arveffects.setTextEffect(creditsText, arveffects.glowEffect, arveffects.shadowEffect, Font.font("Verdana", FontWeight.BOLD, 20), Color.AQUAMARINE, -450, -200);
        
                
        creditsPane.getChildren().addAll(escText, showCreditsText());
        creditsScene = new Scene(creditsPane);
        
        creditsScene.setOnKeyPressed(event ->{
            switch  (event.getCode()){
                case ESCAPE: ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
              
        }
    });
        
    }
    
    public Text showCreditsText(){
        
     try {
                creditsBR = new BufferedReader(new FileReader("c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\credits.txt"));
                    
            try { 
                creditsBR.skip(1);
            } catch (IOException ex) {
                Logger.getLogger(ArvenarCredits.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while((str = creditsBR.readLine()) != null){
                    
                    creditsText.setText(creditsText.getText()+"\n"+str); //Több soros szöveg: előző + új
                    
                                                            
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
   
    public Scene credits_Scene(){
        return creditsScene;
    }
    
    
}
