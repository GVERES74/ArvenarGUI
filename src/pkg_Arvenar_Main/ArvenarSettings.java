/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.util.Collection;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

/**
 *
 * @author TE332168
 */
public class ArvenarSettings {
  
    Stage settings_stage = new Stage();
    Pane settings_pane = new Pane();
    Scene settings_scene = new Scene(settings_pane);
    CheckBox chkbox_music = new CheckBox();
    Slider slider_music = new Slider(0,100,50);
    Label slider_music_label = new Label("Music volume:");
    Label slider_volume_label = new Label("50%");
    Label label_music = new Label("Music on / off");
    MPlayer play_music;
    Tooltip slider_music_tt = new Tooltip();

    
    public ArvenarSettings(){
        play_music = new MPlayer();
        
        settings_stage.setTitle("Game settings");
        settings_stage.setScene(settings_scene);
        
        Button btn_accept_changes = new Button("Accept settings"); btn_accept_changes.setLayoutX(50); btn_accept_changes.setLayoutY(400);
        Button btn_exit_settings = new Button("Back to main menu"); btn_exit_settings.setLayoutX(50); btn_exit_settings.setLayoutY(450);
        
        
        chkbox_music.setLayoutX(150); chkbox_music.setLayoutY(50); chkbox_music.setSelected(true);
        label_music.setLayoutX(50); label_music.setLayoutY(50);
        slider_music.setLayoutX(140); slider_music.setLayoutY(102);
        slider_music_tt.setText((int)slider_music.getValue()+"%");
        slider_music.setTooltip(slider_music_tt);
        slider_music_label.setLayoutX(50); slider_music_label.setLayoutY(100);
        slider_volume_label.setLayoutX(290); slider_volume_label.setLayoutY(100);
        
        //play_music.mediaPlayer.setVolume(slider_music.getValue());
        
        
        settings_stage.setMinWidth(800);
        settings_stage.setMinHeight(600);
        
        settings_stage.initModality(Modality.APPLICATION_MODAL);
        settings_pane.getChildren().addAll(btn_accept_changes, btn_exit_settings, chkbox_music, label_music, slider_music, slider_music_label, slider_volume_label);
        
        //---------------------------------------------------    
        
        slider_music.setOnDragDetected(action -> {
        
        slider_music_tt.setText((int)slider_music.getValue()+"%");
        slider_music.setTooltip(slider_music_tt);
        slider_volume_label.setText((int)slider_music.getValue()+"%");
        play_music.mediaPlayer.setVolume(slider_music.getValue());   
            
        });
        
        
        chkbox_music.setOnAction(action -> {
            
            try{
            if (chkbox_music.isSelected() == true){
            
                slider_music.setDisable(false);
                play_music.mPlayer_start("journey.mp3", true, 5);
            }
            
            else if (chkbox_music.isSelected() == false){
                
                slider_music.setDisable(true);
                play_music.mPlayer_stop();
                
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
                settings_stage.close();
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
                settings_stage.close();
            }
            else {
                return;
            }
                     
         }); 
        
    }
    
    public void show_Settings(){
        
        settings_stage.show();
        
    }
         
    }
    
    
    

