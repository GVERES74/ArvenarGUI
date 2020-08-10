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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    CheckBox chkbox_music = new CheckBox();
    CheckBox chkbox_fullScreen = new CheckBox();
    Slider slider_music = new Slider(0,100,50);
    Label slider_music_label = new Label("Music volume:");
    Label slider_volume_label = new Label("50%");
    Label label_music = new Label("Music On / Off");
    Label label_is_Fullscreen = new Label("Fullscreen On / Off");
    Tooltip slider_music_tt = new Tooltip();
    

    
    public ArvenarSettings(){
        
        settings_stage.setTitle("Game settings");
        settings_stage.setScene(settings_scene);
        
        Button btn_accept_changes = new Button("Accept settings"); btn_accept_changes.setLayoutX(50); btn_accept_changes.setLayoutY(400);
        Button btn_exit_settings = new Button("Back to main menu"); btn_exit_settings.setLayoutX(50); btn_exit_settings.setLayoutY(450);
        
        
        audio_pane.setLayoutX(50); audio_pane.setLayoutY(50);
        audio_pane.setMaxSize(350, 110); audio_pane.setMinSize(350, 110);
        audio_pane.setStyle("-fx-border-color: gray");
        chkbox_music.setLayoutX(140); chkbox_music.setLayoutY(9); chkbox_music.setSelected(true);
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
        video_pane.setStyle("-fx-border-color: gray");
        label_is_Fullscreen.setLayoutX(20); label_is_Fullscreen.setLayoutY(20);
        label_is_Fullscreen.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        chkbox_fullScreen.setLayoutX(170); chkbox_fullScreen.setLayoutY(18); chkbox_fullScreen.setSelected(false);
        
        /*settings_stage.setMinWidth(800);
        settings_stage.setMinHeight(600);
        settings_stage.initModality(Modality.APPLICATION_MODAL);*/
        
        audio_pane.getChildren().addAll(chkbox_music, label_music, slider_music, slider_music_label, slider_volume_label);
        video_pane.getChildren().addAll(label_is_Fullscreen, chkbox_fullScreen);
        settings_pane.setBackground(new Background(new BackgroundImage(new Image("/img/bkg_settings.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        settings_scene = new Scene(settings_pane);
        settings_pane.getChildren().addAll(btn_accept_changes, btn_exit_settings, audio_pane, video_pane);
        
        //---------------------------------------------------    
        
        slider_music.setOnDragDetected(action -> {
        
        slider_music_tt.setText((int)slider_music.getValue()+"%");
        slider_music.setTooltip(slider_music_tt);
        slider_volume_label.setText((int)slider_music.getValue()+"%");
        slider_volume_label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        MPlayer.setVolume((int)slider_music.getValue());   
            
        });
        
        
        chkbox_music.setOnAction(action -> {
            
            try{
            if (chkbox_music.isSelected() == true){
            
                slider_music.setDisable(false);
                MPlayer.setAudio_on(true);
                
            }
            
            else if (chkbox_music.isSelected() == false){
                
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
        
        
        btn_accept_changes.setOnAction(action -> {
             
            JFrame frame = new JFrame();
            Object[] options = {"Yes","No"};
            int opt = JOptionPane.showOptionDialog(frame, "Accept changes?", "Settings", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                   
            if (opt == JOptionPane.YES_OPTION){
                try {
                    //settings_stage.close();
                    
                    ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
                    if (chkbox_fullScreen.isSelected()){
                        ArvenarFXMain.flagFullScreen = true;
                        ArvenarFXMain.stageElven.setFullScreen(true);
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
            
         btn_exit_settings.setOnAction(action -> {
             
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
    
    public void show_Settings(){
        
        settings_stage.show();
        
    }

    public Scene settings_Scene(){
        
        return settings_scene;
    }
    
    }
    
    
    

