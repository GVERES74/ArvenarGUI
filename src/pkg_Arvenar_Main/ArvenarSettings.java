/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author TE332168
 */
public class ArvenarSettings {
  
    Stage settings_stage = new Stage();
    Pane settings_pane = new Pane();
    Scene settings_scene;
    Pane audio_pane = new Pane();
    Pane video_pane = new Pane();
    CheckBox cbMusic = new CheckBox();
    CheckBox cbFullScreen = new CheckBox();
    ChoiceBox cbResolution = new ChoiceBox();
    Slider slider_music = new Slider(0,100,50);
    Label slider_music_label = new Label("Music volume:");
    Label slider_volume_label = new Label("50%");
    Label label_music = new Label("Music On / Off");
    Label label_is_Fullscreen = new Label("Fullscreen On / Off");
    Label labelResolution = new Label("Select display resolution");
    Tooltip slider_music_tt = new Tooltip();
    Text sceneText = new Text("SETTINGS");
    
    ArvenarEffects arvfx = new ArvenarEffects();
    

    
    public ArvenarSettings(){
        
        settings_stage.setTitle("Game settings");
        settings_stage.setScene(settings_scene);
        
        Button btnAccept = new Button("Accept settings"); btnAccept.setLayoutX(50); btnAccept.setLayoutY(400);
        Button btnExit = new Button("Back to main menu"); btnExit.setLayoutX(50); btnExit.setLayoutY(450);
        
        
        audio_pane.setLayoutX(50); audio_pane.setLayoutY(50);
        audio_pane.setMaxSize(350, 110); audio_pane.setMinSize(350, 110);
        audio_pane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5;");
        cbMusic.setLayoutX(140); cbMusic.setLayoutY(9); cbMusic.setSelected(true);
        label_music.setLayoutX(20); label_music.setLayoutY(10);
        label_music.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        slider_music.setLayoutX(130); slider_music.setLayoutY(62);
        slider_music_tt.setText((int)slider_music.getValue()+"%");
        slider_music.setTooltip(slider_music_tt);
        slider_music_label.setLayoutX(20); slider_music_label.setLayoutY(60);
        slider_music_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        slider_volume_label.setLayoutX(280); slider_volume_label.setLayoutY(60);
        slider_volume_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        video_pane.setLayoutX(50); video_pane.setLayoutY(200);
        video_pane.setMaxSize(350, 110); video_pane.setMinSize(350, 110);
        video_pane.setStyle("-fx-background-color: rgba(0, 50, 50, 0.2); -fx-background-radius: 5;");
        label_is_Fullscreen.setLayoutX(20); label_is_Fullscreen.setLayoutY(20);
        label_is_Fullscreen.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        labelResolution.setLayoutX(20); labelResolution.setLayoutY(50);
        labelResolution.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        cbFullScreen.setLayoutX(170); cbFullScreen.setLayoutY(18); cbFullScreen.setSelected(false);
        cbResolution.setLayoutX(170); cbResolution.setLayoutY(50);
        cbResolution.getItems().add("1366x768"); cbResolution.getItems().add("1920x1080"); cbResolution.setValue("1366x768");
        sceneText = arvfx.setTextEffect(sceneText, arvfx.setGlowEffect(0.5), null, Font.font("Verdana", FontWeight.BOLD, 36), Color.SILVER, 50, 800);
        
        /*settings_stage.setMinWidth(800);
        settings_stage.setMinHeight(600);
        settings_stage.initModality(Modality.APPLICATION_MODAL);*/
        
        audio_pane.getChildren().addAll(cbMusic, label_music, slider_music, slider_music_label, slider_volume_label);
        video_pane.getChildren().addAll(label_is_Fullscreen, cbFullScreen, labelResolution, cbResolution);
        settings_pane.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_settings.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        settings_scene = new Scene(settings_pane);
        settings_pane.getChildren().addAll(btnAccept, btnExit, audio_pane, video_pane, sceneText);
        
        
        arvfx.buttonEffects(btnAccept);
        arvfx.buttonEffects(btnExit);
        
        //---------------------------------------------------    
        
        slider_music.setOnDragDetected(action -> {
        
        slider_music_tt.setText((int)slider_music.getValue()+"%");
        slider_music.setTooltip(slider_music_tt);
        slider_volume_label.setText((int)slider_music.getValue()+"%");
        slider_volume_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        MPlayer.setVolume((int)slider_music.getValue());   
            
        });
        
        
        cbMusic.setOnAction(action -> {
            
            try{
            if (cbMusic.isSelected() == true){
            
                slider_music.setDisable(false);
                MPlayer.setAudio_on(true);
                
            }
            
            else if (cbMusic.isSelected() == false){
                
                slider_music.setDisable(true);
                MPlayer.setAudio_on(false);
                MPlayer.mPlayer_stop();
                slider_volume_label.setText("Music off");
                slider_volume_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                
            } 
            }   
                catch (Exception e){
                    e.printStackTrace();
                }
                
            
        });
        
        
        
        btnAccept.setOnAction(action -> {
             
            JFrame frame = new JFrame();
            Object[] options = {"Yes","No"};
            int opt = JOptionPane.showOptionDialog(frame, "Accept changes?", "Settings", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                   
            if (opt == JOptionPane.YES_OPTION){
                try {
                    //settings_stage.close();
                    switch ((String) cbResolution.getValue()){
                            case "1366x768": ArvenarFXMain.stageElven.setWidth(1366); ArvenarFXMain.stageElven.setHeight(768); break;
                            case "1920x1080": ArvenarFXMain.stageElven.setWidth(1920); ArvenarFXMain.stageElven.setHeight(1080); break;
                    }
                                        
                    ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
                    if (cbFullScreen.isSelected()){
                        ArvenarFXMain.flagFullScreen = true;
                        ArvenarFXMain.stageElven.setFullScreen(true);
                    }
                    else{
                        ArvenarFXMain.flagFullScreen = false;
                    }               
                    
                } catch (Exception ex) {
                    Logger.getLogger(ArvenarSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                return;
            }
         }
         
         );
            
         btnExit.setOnAction(action -> {
             
            JFrame frame = new JFrame();
            Object[] options = {"Yes","No"};
            int opt = JOptionPane.showOptionDialog(frame, "Cancel changes?", "Settings", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                   
            if (opt == JOptionPane.YES_OPTION){
                //settings_stage.close();
                ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
                ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen);
                
            }
            else {
                return;
            }
                     
         }); 
        
    }
    
    
    public Scene settings_Scene(){
        
        return settings_scene;
    }
    
    }
    
    
    

