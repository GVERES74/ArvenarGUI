package pkg_Arvenar_Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author VERESG
 */
public class ArvenarFXMain extends Application {
    
        public static int resX = 1366;
        public static int resY = 768;
        static Stage stageElven;
        public static boolean flagFullScreen;
        static Scene sceneElven;
        
        static ArvenarEffects arvfx = new ArvenarEffects();
        ArvenarFonts arvfonts;
        ArvenarCredits credits;
        ArvenarExtras extras;
        static Text mTxtStartGame, mTxtSettings, mTxtExtras, mTxtCredits, mTxtExit, versionText;
        static double g, h;
        static int starposX;
        static int starposY;
        static int starspeed;
        
        Pane paneElven;
        static ImageView starImgView; 
        Image starimg;
        
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ArvenarFx.fxml"));

        Arvenar_App elvenarapp = new Arvenar_App(); //Példányosítani kell, nem lehet direkt hivatkozni, mint pl. "Arvenar_App.fight()"!!
        
        ArvenarGameGUI gamegui = new ArvenarGameGUI();
        ArvenarSettings gsettings = new ArvenarSettings();
                
        arvfonts = new ArvenarFonts();
        
        
        Tooltip tt = new Tooltip();
        paneElven = new StackPane();
        
        VBox mTxtVBox = new VBox();     
        
        Image bkgImage = new Image("img/bkg_main.jpg");
        
        
        stageElven = new Stage();
        stageElven.setTitle("Arvenar - Elven Tales - 2020 - by Gabor Veres");
        paneElven.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        sceneElven = new Scene(paneElven);
        
        stageElven.setResizable(false);
                
        stageElven.setScene(sceneElven);
        
                         
        FileInputStream starFx = new FileInputStream("src/img/starfx.png");
        starimg = new Image(starFx);
        starImgView = new ImageView(starimg);
        //starImgView.setOpacity(0.2);
                
        mTxtStartGame = arvfonts.newTextFormat("Start game", mTxtStartGame, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        
        mTxtSettings = arvfonts.newTextFormat("Game settings", mTxtSettings, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtExtras = arvfonts.newTextFormat("Extras", mTxtExtras, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtCredits = arvfonts.newTextFormat("Credits", mTxtCredits, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
        mTxtExit = arvfonts.newTextFormat("Exit game", mTxtExit, arvfx.setGlowEffect(0.0), null, Font.font("Verdana", FontWeight.BOLD, 24), Color.CORAL, 0, 0);
                
        mTxtVBox.setTranslateX(30); mTxtVBox.setTranslateY(30);
        mTxtVBox.setSpacing(20); 
        mTxtVBox.setStyle("-fx-background-color: rgba(64, 50, 128, 0.2); -fx-background-radius: 5; -fx-padding: 30;"); //transparent and rounded VBox with padding
        
        versionText = arvfonts.newTextFormat("Arvenar GUI version - Build 05.11.20", versionText, null, arvfx.reflectionEffect, Font.font("Verdana", FontWeight.BOLD, 20), Color.CORAL, 400, 300);
        
        mTxtVBox.getChildren().addAll(mTxtStartGame, mTxtSettings, mTxtExtras, mTxtCredits, mTxtExit);
        paneElven.getChildren().addAll(mTxtVBox, versionText, starImgView);
                
        stageElven.setHeight(resY); stageElven.setWidth(resX);
        stageElven.setFullScreen(flagFullScreen);        
        stageElven.show();
        MPlayer.mPlayer_start("journey.mp3", true, 5);    
        
        new GVTimer().mainTimer(100);
        //new GVTimer().starFaller(1);
        
        //--------------------------------------------------------
        arvfx.btnTextEffects(mTxtStartGame);
        arvfx.btnTextEffects(mTxtSettings);
        arvfx.btnTextEffects(mTxtExtras);
        arvfx.btnTextEffects(mTxtCredits);
        arvfx.btnTextEffects(mTxtExit);
        
        //--------------------------------------------------------
        
        mTxtExit.setOnMouseClicked(action -> {
            
            JFrame frame = new JFrame();
            Object[] options = {"Yes","No"};
            int opt = JOptionPane.showOptionDialog(frame, "Really exit the game?", "Notice!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                   
            if (opt == JOptionPane.YES_OPTION){
                System.exit(0);
            }
            else {
                return;
            }
                        
         });
       
        
        mTxtStartGame.setOnMouseClicked(action -> {
            
            stageElven.setScene(gamegui.game_Scene());
            stageElven.setFullScreen(flagFullScreen);
        
                //gamegui.show_GameGUI();
                //elvenarapp.fight();
                MPlayer.mPlayer_stop();
                MPlayer.mPlayer_start("main.mp3", true, 5);
                
        });
        
        mTxtSettings.setOnMouseClicked(action -> {
        
            //gsettings.show_Settings();
            stageElven.setScene(gsettings.settings_Scene());
            stageElven.setFullScreen(flagFullScreen);
        });
                
        mTxtExtras.setOnMouseClicked(action -> {
            
               
            try {
                extras = new ArvenarExtras();
            } catch (Exception ex) {
                Logger.getLogger(ArvenarFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("credits1.mp3", true, 5);
        stageElven.setScene(extras.extras_Scene());
        stageElven.setFullScreen(flagFullScreen);
                            
        });
        
        
        mTxtCredits.setOnMouseClicked(action -> {
            try {
                credits = new ArvenarCredits();
            } catch (InterruptedException ex) {
                Logger.getLogger(ArvenarFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("credits1.mp3", true, 5);
        stageElven.setScene(credits.credits_Scene());
        stageElven.setFullScreen(flagFullScreen);
                            
        });
    
    }    
                   
    public static void buttonTxtFxer() {
    
                           
            if (arvfx.glowEffect.getLevel() < 0.1){
                g = g + 0.1;
                                                }        
            if (arvfx.glowEffect.getLevel() > 0.9) {
                g = g - 0.1;
                        
                }
                    mTxtStartGame.setEffect(arvfx.setGlowEffect(arvfx.glowEffect.getLevel()+g));
                    System.out.println("Glow: "+arvfx.glowEffect.getLevel());
                       
    }   
        
    public static void fallingStar() {
    
        starspeed = (int) (Math.random()*3);
        starImgView.setTranslateX(starImgView.getTranslateX()+starspeed);
        starImgView.setTranslateY(starImgView.getTranslateY()+starspeed);
        System.out.println(starImgView.getTranslateX()+" / "+starImgView.getTranslateY());
            
        if(starImgView.getTranslateY() > 400){
            starposX = (int) (Math.random()*800)-800;
            starposY = (int) (Math.random()*500)-500;
            starImgView.setTranslateX(starposX);
            starImgView.setTranslateY(starposY);
        }
                    
    }   
        
    public static void main(String[] args) throws FileNotFoundException {
      Application.launch(args); //Kell az Application.launch();!!
     
    }
    /**
     * @param args the command line arguments
     */
        
}
