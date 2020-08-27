package pkg_Arvenar_Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
        Arvenar_View_NPC view_npc;
        ArvenarEffects arvfx = new ArvenarEffects();
    
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ArvenarFx.fxml"));

        Arvenar_App elvenarapp = new Arvenar_App(); //Példányosítani kell, nem lehet direkt hivatkozni, mint pl. "Arvenar_App.fight()"!!
        ArvenarSetPC arvenarset = new ArvenarSetPC();
        Arvenar_View_Maps view_maps = new Arvenar_View_Maps();
                          view_npc = new Arvenar_View_NPC();
        ArvenarGameGUI gamegui = new ArvenarGameGUI();
        ArvenarSettings gsettings = new ArvenarSettings();
        ArvenarCredits credits = new ArvenarCredits();
        
        Tooltip tt = new Tooltip();
        Pane paneElven = new StackPane();
        Text versionText = new Text("Arvenar GUI version - Build 18.08.20");
        VBox buttonsVBox = new VBox();     
        
        Image bkgImage = new Image("img/bkg_main.jpg");
        
        
        stageElven = new Stage();
        stageElven.setTitle("Arvenar - Elven Tales - 2020 - by Gabor Veres");
        paneElven.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        sceneElven = new Scene(paneElven);
        
        stageElven.setResizable(false);
        
        
        stageElven.setScene(sceneElven);
        TextArea gameTextArea = new TextArea("Waiting for input....\n");
         
                
        /*FileInputStream main_Img = new FileInputStream("src/img/arvenar_main.jpg");
        ImageView main_Img_View = new ImageView(new Image(main_Img));
        main_Img_View.setLayoutX(200); main_Img_View.setLayoutY(50);*/
                
        Button startButton = new Button("Start game");    startButton.setPrefWidth(150); startButton.setTooltip(tt); tt.setText("Play game with selected hero");
        Button pcButton = new Button("Select Hero");   pcButton.setPrefWidth(150); pcButton.setTooltip(new Tooltip("Select playable character"));
        Button settingsButton = new Button("Game settings");  settingsButton.setPrefWidth(150); settingsButton.setTooltip(new Tooltip("Game settings"));
        Button mapsButton = new Button("View maps");       mapsButton.setPrefWidth(150); mapsButton.setTooltip(new Tooltip("View maps"));
        Button npcButton = new Button("Show characters"); npcButton.setPrefWidth(150); npcButton.setTooltip(new Tooltip("View npc database"));
        Button creditsButton = new Button("Credits"); creditsButton.setPrefWidth(150); creditsButton.setTooltip(new Tooltip("Game credits"));
        Button exitButton = new Button("Exit game");   exitButton.setPrefWidth(150);  exitButton.setTooltip(new Tooltip("Exit game"));
        Button playButton = new Button("Play music"); playButton.setPrefWidth(150); playButton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_play.png"))); playButton.setTooltip(new Tooltip("Play main theme"));
        Button stopButton = new Button("Stop music"); stopButton.setPrefWidth(150); stopButton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_stop.png"))); stopButton.setTooltip(new Tooltip("Stop music"));
        
        buttonsVBox.setTranslateX(30); buttonsVBox.setTranslateY(30);
        buttonsVBox.setSpacing(20); 
        
        versionText = arvfx.setTextEffect(versionText, arvfx.glowEffect, arvfx.reflectionEffect, Font.font("Verdana", FontWeight.BOLD, 20), Color.CORAL, 400, 300);
        buttonsVBox.getChildren().addAll(startButton, pcButton, mapsButton, npcButton, settingsButton, creditsButton, exitButton, playButton, stopButton);
        paneElven.getChildren().addAll(buttonsVBox, versionText);
        
        stageElven.setHeight(resY); stageElven.setWidth(resX);
        stageElven.setFullScreen(flagFullScreen);        
        stageElven.show();
        MPlayer.mPlayer_start("journey.mp3", true, 5);    
        
        
        
        //--------------------------------------------------------
        arvfx.buttonEffects(playButton);
        arvfx.buttonEffects(stopButton);
        arvfx.buttonEffects(exitButton);
        arvfx.buttonEffects(creditsButton);
        arvfx.buttonEffects(startButton);
        arvfx.buttonEffects(pcButton);
        arvfx.buttonEffects(npcButton);
        arvfx.buttonEffects(mapsButton);
        arvfx.buttonEffects(settingsButton);
        
        playButton.setOnAction(action ->{MPlayer.mPlayer_start("journey.mp3", true, 5);});
        
        stopButton.setOnAction(action ->{MPlayer.mediaPlayer.stop();});
                
        exitButton.setOnAction(action -> {
            
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
       
        startButton.setOnAction(action -> {
            
            stageElven.setScene(gamegui.game_Scene());
            stageElven.setFullScreen(flagFullScreen);
        
                //gamegui.show_GameGUI();
                //elvenarapp.fight();
                MPlayer.mPlayer_stop();
                MPlayer.mPlayer_start("main.mp3", true, 5);
                gameTextArea.appendText("Fight has been started...\n");
                gameTextArea.appendText(arvenarset.getHeroName());
                
        });
        
        
        settingsButton.setOnAction(action -> {
        
            //gsettings.show_Settings();
            stageElven.setScene(gsettings.settings_Scene());
            stageElven.setFullScreen(flagFullScreen);
        });
                
        pcButton.setOnAction(action -> {
            MPlayer.mPlayer_stop();
            MPlayer.mPlayer_start("menu.mp3", true, 5);
            stageElven.setScene(arvenarset.pc_Scene());
            stageElven.setFullScreen(flagFullScreen);  
            //arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
            gameTextArea.appendText("Character setting...\n");
        
        });
        
        
        mapsButton.setOnAction(action -> {
        
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("outdoor.mp3", true, 5);
        stageElven.setScene(view_maps.maps_Scene());
        stageElven.setFullScreen(flagFullScreen);
        //view_maps.stage_view_maps.show();
        //elvenarapp.view_Map_DataBase();
        
        });
                
        
        npcButton.setOnAction(action -> {
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("browse.mp3", true, 5);
            try {
                
                stageElven.setScene(view_npc.npc_Scene());
                stageElven.setFullScreen(flagFullScreen);
                //view_npc.stagename.show();   
                //elvenarapp.stats();
                //elvenarapp.view_NpcDataBase();
            } catch (Exception e) {
            }
        gameTextArea.appendText("Character properties: "+arvenarset.getHeroName()+"\n");
        });
    
        creditsButton.setOnAction(action -> {
        
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("credits1.mp3", true, 5);
        stageElven.setScene(credits.credits_Scene());
        stageElven.setFullScreen(flagFullScreen);
                
        });
    
    }    
                   
       
    
    public static void main(String[] args) throws FileNotFoundException {
      Application.launch(args); //Kell az Application.launch();!!
     
    }
    /**
     * @param args the command line arguments
     */
    
    
}
