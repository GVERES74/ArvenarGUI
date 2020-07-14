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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author VERESG
 */
public class ArvenarFXMain extends Application {
    
    
    @Override
    public void start(Stage stageElven) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ArvenarFx.fxml"));

        Arvenar_App elvenarapp = new Arvenar_App(); //Példányosítani kell, nem lehet direkt hivatkozni, mint pl. "Arvenar_App.fight()"!!
        ArvenarSetPC arvenarset = new ArvenarSetPC();
        MPlayer play_music = new MPlayer();
        Tooltip tt = new Tooltip();
        Pane paneElven = new Pane();
        ArvenarGameGUI gamegui = new ArvenarGameGUI();
        ArvenarSettings gsettings = new ArvenarSettings();
        
        stageElven.setTitle("Arvenar - Elven Tales - 2020 - by Gabor Veres - Main menu");
        Scene sceneElven = new Scene(paneElven);
        
        stageElven.setResizable(true);
        stageElven.setMinHeight(600); stageElven.setMinWidth(800);

        stageElven.setScene(sceneElven);
        TextArea gametextarea = new TextArea("Waiting for input....\n");
        
        FileInputStream main_img = new FileInputStream("src/img/arvenar_main.jpg");
        ImageView main_img_view = new ImageView(new Image(main_img));
        main_img_view.setLayoutX(200); main_img_view.setLayoutY(50);
        
        gametextarea.setLayoutX(200); gametextarea.setLayoutY(420);
        gametextarea.setMaxHeight(100);
                
        Button startbutton = new Button("Start game");   startbutton.setLayoutX(50); startbutton.setLayoutY(50); startbutton.setTooltip(tt); tt.setText("Play game with selected hero");
        Button choose_pc_button = new Button("Select Hero");   choose_pc_button.setLayoutX(50); choose_pc_button.setLayoutY(100); choose_pc_button.setTooltip(new Tooltip("Select playable character"));
        Button settings_button = new Button("Game settings");   settings_button.setLayoutX(50); settings_button.setLayoutY(150); settings_button.setTooltip(new Tooltip("Game settings"));
        Button tradebutton = new Button("Trade");        tradebutton.setLayoutX(50); tradebutton.setLayoutY(200);
        Button view_npc_bio_button = new Button("Show characters"); view_npc_bio_button.setLayoutX(50); view_npc_bio_button.setLayoutY(250); view_npc_bio_button.setTooltip(new Tooltip("View npc database"));
        Button exitbutton = new Button("Exit game");     exitbutton.setLayoutX(50); exitbutton.setLayoutY(300); exitbutton.setTooltip(new Tooltip("Exit game"));
        Button playbutton = new Button("Play music"); playbutton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_play.png"))); playbutton.setLayoutX(50); playbutton.setLayoutY(400); playbutton.setTooltip(new Tooltip("Play main theme"));
        Button stopbutton = new Button("Stop music"); stopbutton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_stop.png")));stopbutton.setLayoutX(50); stopbutton.setLayoutY(450); stopbutton.setTooltip(new Tooltip("Stop music"));
        
        paneElven.getChildren().addAll(gametextarea, startbutton, tradebutton, view_npc_bio_button, exitbutton, choose_pc_button, settings_button, main_img_view, playbutton, stopbutton);
        stageElven.show();
        play_music.mPlayer_start("journey.mp3", true, 5);        
        
        playbutton.setOnAction(action ->{
            play_music.mPlayer_start("journey.mp3", true, 5);
        }
        );
        
        stopbutton.setOnAction(action ->{
            play_music.mPlayer_stop();
        }
        );
        
        
        exitbutton.setOnAction(action -> {
            
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
       
        startbutton.setOnAction(action -> {
            
            try {
                elvenarapp.fight();
                play_music.mPlayer_stop();
                play_music.mPlayer_start("main.mp3", true, 5);
                gametextarea.appendText("Fight has been started...\n");
                gametextarea.appendText(arvenarset.getHeroName());
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        });
        
        settings_button.setOnAction(action -> {
           
        gsettings.show_Settings();
        }
        
        );
        
        
        choose_pc_button.setOnAction(action -> {
            play_music.mPlayer_stop();
            play_music.mPlayer_start("menu.mp3", true, 5);
            try {
                elvenarapp.choose_Character();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        gametextarea.appendText("Character setting...\n");
        
        
        });
        
        tradebutton.setOnAction(action -> {
        elvenarapp.trade();
        gametextarea.appendText("You are trading with "+arvenarset.getHeroName()+"\n");
        });
        
        view_npc_bio_button.setOnAction(action -> {
        play_music.mPlayer_stop();
        play_music.mPlayer_start("browse.mp3", true, 5);
            try {
                elvenarapp.stats();
                elvenarapp.view_NpcDataBase();
            } catch (Exception e) {
            }
        gametextarea.appendText("Character properties: "+elvenarapp.choose_player.getHeroName()+"\n");
        });
                
    }
    
    public static void main(String[] args) throws FileNotFoundException {
      Application.launch(args); //Kell az Application.launch();!!
     
     
    }
    /**
     * @param args the command line arguments
     */
    
    
}
