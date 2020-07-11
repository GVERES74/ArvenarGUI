/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author TE332168
 */
public class ArvenarSettings {
  
    Stage settings_stage = new Stage();
    Pane settings_pane = new Pane();
    Scene settings_scene = new Scene(settings_pane);

    
    public ArvenarSettings(){
      
        settings_stage.setTitle("Game settings");
        settings_stage.setScene(settings_scene);
        
        Button btn_accept_changes = new Button("Accept settings"); btn_accept_changes.setLayoutX(50); btn_accept_changes.setLayoutY(400);
        Button btn_exit_settings = new Button("Back to main menu"); btn_exit_settings.setLayoutX(50); btn_exit_settings.setLayoutY(450);
        
        settings_stage.setMinWidth(800);
        settings_stage.setMinHeight(600);
        
        settings_stage.initModality(Modality.APPLICATION_MODAL);
        settings_pane.getChildren().addAll(btn_accept_changes, btn_exit_settings);
        
            
         btn_accept_changes.setOnAction(action -> {
             
             
         }
         
         );
            
         btn_exit_settings.setOnAction(action -> {
             
            settings_stage.close();
         }); 
        
    }
    
    public void show_Settings(){
        
        settings_stage.show();
        
    }
         
    }
    
    
    

