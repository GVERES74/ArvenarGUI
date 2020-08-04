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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        Arvenar_View_Maps view_maps = new Arvenar_View_Maps();
        Arvenar_View_NPC view_npc = new Arvenar_View_NPC();
        ArvenarGameGUI gamegui = new ArvenarGameGUI();
        ArvenarSettings gsettings = new ArvenarSettings();
        
        Tooltip tt = new Tooltip();
        Pane paneElven = new Pane();
        Label flowtext_Label = new Label();
        VBox buttonsVBox = new VBox();      
                
        stageElven.setTitle("Arvenar - Elven Tales - 2020 - by Gabor Veres - Main menu");
        Scene sceneElven = new Scene(paneElven);
        
        stageElven.setResizable(true);
        stageElven.setMinHeight(600); stageElven.setMinWidth(800);

        stageElven.setScene(sceneElven);
        TextArea gameTextArea = new TextArea("Waiting for input....\n");
        flowtext_Label.setText("Let's play Arvenar GUI version..");
        
        
        
        FileInputStream main_Img = new FileInputStream("src/img/arvenar_main.jpg");
        ImageView main_Img_View = new ImageView(new Image(main_Img));
        main_Img_View.setLayoutX(200); main_Img_View.setLayoutY(50);
        
        gameTextArea.setLayoutX(200); gameTextArea.setLayoutY(420);
        gameTextArea.setMaxHeight(100);
                
        buttonsVBox.setLayoutX(50); buttonsVBox.setLayoutY(50); buttonsVBox.setSpacing(20);
        Button startButton = new Button("Start game");    startButton.setTooltip(tt); tt.setText("Play game with selected hero");
        Button choose_Pc_Button = new Button("Select Hero");   choose_Pc_Button.setTooltip(new Tooltip("Select playable character"));
        Button settings_Button = new Button("Game settings");   settings_Button.setTooltip(new Tooltip("Game settings"));
        Button maps_Button = new Button("View maps");        maps_Button.setTooltip(new Tooltip("View maps"));
        //utton tradebutton = new Button("Trade");        tradebutton.setLayoutX(50); tradebutton.setLayoutY(200);
        Button view_Npc_Bio_Button = new Button("Show characters"); view_Npc_Bio_Button.setTooltip(new Tooltip("View npc database"));
        Button exitButton = new Button("Exit game");     exitButton.setTooltip(new Tooltip("Exit game"));
        Button playButton = new Button("Play music"); playButton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_play.png"))); playButton.setTooltip(new Tooltip("Play main theme"));
        Button stopButton = new Button("Stop music"); stopButton.setGraphic(new ImageView(new Image("file:\\c:\\Users\\te332168\\Documents\\NetBeansProjects\\Arvenar\\src\\img\\music_stop.png"))); stopButton.setTooltip(new Tooltip("Stop music"));
        
        buttonsVBox.getChildren().addAll(startButton, choose_Pc_Button, maps_Button, view_Npc_Bio_Button, settings_Button, exitButton, playButton, stopButton);
        paneElven.getChildren().addAll(gameTextArea, main_Img_View, buttonsVBox);
        stageElven.show();
        MPlayer.mPlayer_start("journey.mp3", true, 5);    
        
        
        
        //--------------------------------------------------------
        
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
            
            gamegui.show_GameGUI();
                //elvenarapp.fight();
                MPlayer.mPlayer_stop();
                MPlayer.mPlayer_start("main.mp3", true, 5);
                gameTextArea.appendText("Fight has been started...\n");
                gameTextArea.appendText(arvenarset.getHeroName());
                
        });
        
        
        settings_Button.setOnAction(action -> {gsettings.show_Settings();});
                
        choose_Pc_Button.setOnAction(action -> {
            MPlayer.mPlayer_stop();
            MPlayer.mPlayer_start("menu.mp3", true, 5);
            arvenarset.stagename.show();    
            //elvenarapp.choose_Character();
            gameTextArea.appendText("Character setting...\n");
        
        });
        
        
        maps_Button.setOnAction(action -> {
        
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("outdoor.mp3", true, 5);
        view_maps.stage_view_maps.show();
        //elvenarapp.view_Map_DataBase();
        
        });
                
        
        view_Npc_Bio_Button.setOnAction(action -> {
        MPlayer.mPlayer_stop();
        MPlayer.mPlayer_start("browse.mp3", true, 5);
            try {
                
                 view_npc.stagename.show();   
                //elvenarapp.stats();
                //elvenarapp.view_NpcDataBase();
            } catch (Exception e) {
            }
        gameTextArea.appendText("Character properties: "+arvenarset.getHeroName()+"\n");
        });
                
    }
    
    public static void main(String[] args) throws FileNotFoundException {
      Application.launch(args); //Kell az Application.launch();!!
     
    }
    /**
     * @param args the command line arguments
     */
    
    
}
