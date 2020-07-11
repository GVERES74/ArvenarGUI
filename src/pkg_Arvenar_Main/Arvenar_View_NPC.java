/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkg_Characters.Character_DataBase_NPC;
import pkg_Characters.Character_DataBase_PC;
import pkg_Characters.Summon_Characters;

/**
 *
 * @author TE332168
 */
public class Arvenar_View_NPC {
    
     int i = 0;
    
    Summon_Characters clone = new Summon_Characters();
    Character_DataBase_NPC npcdbase = new Character_DataBase_NPC();
    MPlayer play_music; //ne példányosítsd!!
        
    FileInputStream inputimg = new FileInputStream("src/img/npc_animal_bbear1.jpg");
    Image selectedpcimage = new Image(inputimg);
    ImageView npc_imgview = new ImageView(selectedpcimage);
    TextField npc_name_textfield = new TextField(); 
    TextArea npc_bio_textarea = new TextArea();
    TextArea npc_stats_textarea = new TextArea();
    
    Stage stagename = new Stage();
    Pane panename = new Pane();
    Scene scenename = new Scene(panename);
    String npc_name = "";
                    
    public Arvenar_View_NPC() throws FileNotFoundException {
        
        npcdbase.dataBase(); //Karakter adatbázis inicializálása
        
        npc_name_textfield.setLayoutX(305); npc_name_textfield.setLayoutY(50);
        npc_name_textfield.setText(clone.bbear1.getFname());
            
        npc_bio_textarea.setLayoutX(100); npc_bio_textarea.setLayoutY(100); npc_bio_textarea.setWrapText(true); npc_bio_textarea.setMaxHeight(100);
        npc_bio_textarea.setText(clone.bbear1.getBiography());
        
        npc_stats_textarea.setLayoutX(100); npc_stats_textarea.setLayoutY(220); npc_stats_textarea.setWrapText(true); npc_stats_textarea.setMaxWidth(200.0);
        npc_stats_textarea.appendText("Race: "+npcdbase.npc_stats_race.get(i)); 
        npc_stats_textarea.appendText("\nCast: "+npcdbase.npc_stats_cast.get(i)); 
        npc_stats_textarea.appendText("\nHealth: "+npcdbase.npc_stats_maxhealth.get(i));
        npc_stats_textarea.appendText("\nWeapon: "+npcdbase.npc_stats_weapon.get(i)); 
        npc_stats_textarea.appendText("\nBattle shout: "+npcdbase.npc_stats_shout.get(i)); 
        
                    
        npc_imgview.setLayoutX(600);
        npc_imgview.setLayoutY(100);
        
        
        stagename.setTitle("View non-playable characters");
        stagename.setResizable(false);
        stagename.setMinHeight(600); stagename.setMinWidth(800);
        stagename.setScene(scenename);
        stagename.setResizable(false);
        stagename.initModality(Modality.APPLICATION_MODAL);
        
        Button choosebutton = new Button("Choose character"); choosebutton.setLayoutX(300); choosebutton.setLayoutY(450);
        Button cancelbutton = new Button("Back"); cancelbutton.setLayoutX(50); cancelbutton.setLayoutY(450);
        Button nextbutton = new Button("Next"); nextbutton.setLayoutX(500); nextbutton.setLayoutY(50);
        Button prevbutton = new Button("Previous"); prevbutton.setLayoutX(200); prevbutton.setLayoutY(50);
        
        panename.getChildren().addAll(choosebutton, cancelbutton, nextbutton, prevbutton, this.npc_name_textfield, this.npc_bio_textarea, npc_imgview, npc_stats_textarea);
                
        
    //ActionListener block ----------------------------------------------------------------------------------
        
        
        //Choose selected hero_name-------------------------------------------------------------------------------
        choosebutton.setOnAction(Action -> {
            npc_name_textfield.setText("You are viewing: "+npc_name);
            
            
        });
        
        //Back to previous page (Main menu)------------------------------------------------------------------
        cancelbutton.setOnAction(Action -> {
           
            stagename.close();
                                  
        });
        
        
        //Get the next Player---------------------------------------------------------------------------------
        nextbutton.setOnAction(Action -> {
         
            nextNpc();
                
        }); 
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        prevbutton.setOnAction(Action -> {
            
            previousNpc();
                    
        });
                
    }
        
        public void nextNpc(){
        
           if(i < npcdbase.npc_img.size()-1){
                    i++;
                    }
                    else {
                        i = npcdbase.npc_img.size()-1;
                    }
                try {
                    npc_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    inputimg = new FileInputStream(npcdbase.npc_img.get(i));
                    npc_name_textfield.setText(npcdbase.npc_name.get(i));
                    npc_bio_textarea.setText(npcdbase.npc_bio.get(i));
                    npc_stats_textarea.clear();
                    npc_stats_textarea.appendText("Race: "+npcdbase.npc_stats_race.get(i)); 
                    npc_stats_textarea.appendText("\nCast: "+npcdbase.npc_stats_cast.get(i)); 
                    npc_stats_textarea.appendText("\nHealth: "+npcdbase.npc_stats_maxhealth.get(i));
                    npc_stats_textarea.appendText("\nWeapon: "+npcdbase.npc_stats_weapon.get(i)); 
                    npc_stats_textarea.appendText("\nBattle shout: "+npcdbase.npc_stats_shout.get(i)); 
                    this.npc_name = npcdbase.npc_name.get(i);
                    
                                        
                    npc_imgview = new ImageView(new Image(inputimg));
                    npc_imgview.setLayoutX(600);
                    npc_imgview.setLayoutY(100);
                    panename.getChildren().add(npc_imgview);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
                
            } 
        }
        
        public void previousNpc(){
            
           npc_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }            
                    try {
                    inputimg = new FileInputStream(npcdbase.npc_img.get(i));
                    npc_name_textfield.setText(npcdbase.npc_name.get(i));
                    npc_bio_textarea.setText(npcdbase.npc_bio.get(i));
                    npc_stats_textarea.clear();
                    npc_stats_textarea.appendText("Race: "+npcdbase.npc_stats_race.get(i)); 
                    npc_stats_textarea.appendText("\nCast: "+npcdbase.npc_stats_cast.get(i)); 
                    npc_stats_textarea.appendText("\nHealth: "+npcdbase.npc_stats_maxhealth.get(i));
                    npc_stats_textarea.appendText("\nWeapon: "+npcdbase.npc_stats_weapon.get(i)); 
                    npc_stats_textarea.appendText("\nBattle shout: "+npcdbase.npc_stats_shout.get(i)); 
                    this.npc_name = npcdbase.npc_name.get(i);
                        
                                                
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
                    };
                
            npc_imgview = new ImageView(new Image(inputimg));
            npc_imgview.setLayoutX(600);
            npc_imgview.setLayoutY(100);
            panename.getChildren().add(npc_imgview); 
        }
      
        public String getNpcName(){
            return npc_name;
        }
        
        
    
    
}
