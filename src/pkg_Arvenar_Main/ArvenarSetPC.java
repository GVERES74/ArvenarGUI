/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
import pkg_Characters.Summon_Characters;
import pkg_Characters.Character_DataBase_PC;

/**
 *
 * @author TE332168
 */
public class ArvenarSetPC {
    
    int i = 0;
    
    Summon_Characters clone = new Summon_Characters();
    Character_DataBase_PC cdbase = new Character_DataBase_PC();
    MPlayer play_music; //ne példányosítsd!!
        
    FileInputStream inputimg = new FileInputStream("src/img/pc_human_warrior_arthur.jpg");
    Image selectedpcimage = new Image(inputimg);
    ImageView character_imgview = new ImageView(selectedpcimage);
    TextField character_name_textfield = new TextField(); 
    TextArea character_bio_textarea = new TextArea();
    TextArea character_stats_textarea = new TextArea();
    
    Stage stagename = new Stage();
    Pane panename = new Pane();
    Scene scenename = new Scene(panename);
    String hero_name = "";
                    
    public ArvenarSetPC() throws FileNotFoundException {
        
        cdbase.dataBase(); //Karakter adatbázis inicializálása
        
        character_name_textfield.setLayoutX(305); character_name_textfield.setLayoutY(50);
        character_name_textfield.setText(clone.human_warrior.getFname());
        character_name_textfield.setEditable(false); character_name_textfield.setFocusTraversable(false);
            
        character_bio_textarea.setLayoutX(100); character_bio_textarea.setLayoutY(100); character_bio_textarea.setWrapText(true); character_bio_textarea.setMaxHeight(100);
        character_bio_textarea.setText(clone.human_warrior.getBiography());
        character_bio_textarea.setEditable(false); character_bio_textarea.setFocusTraversable(false);
        
        character_stats_textarea.setLayoutX(100); character_stats_textarea.setLayoutY(220); character_stats_textarea.setWrapText(true); character_stats_textarea.setMaxWidth(200.0);
        character_stats_textarea.setEditable(false); character_stats_textarea.setFocusTraversable(false);
        
        character_stats_textarea.appendText("Gender: "+cdbase.players_stats_gender.get(i)); 
        character_stats_textarea.appendText("\nAge: "+cdbase.players_stats_age.get(i));
        character_stats_textarea.appendText("\nCast: "+cdbase.players_stats_cast.get(i));
        character_stats_textarea.appendText("\nHealth: "+cdbase.players_stats_health.get(i));
        character_stats_textarea.appendText("\nSkill: "+cdbase.players_stats_skill.get(i));
        character_stats_textarea.appendText("\nExperience: "+cdbase.players_stats_xp.get(i));
        character_stats_textarea.appendText("\nDefence: "+cdbase.players_stats_defence.get(i));
                    
        character_imgview.setLayoutX(600);
        character_imgview.setLayoutY(100);
        
        
        stagename.setTitle("Choose playable hero");
        stagename.setResizable(false);
        stagename.setMinHeight(600); stagename.setMinWidth(800);
        stagename.setScene(scenename);
        stagename.setResizable(false);
        stagename.initModality(Modality.APPLICATION_MODAL);
        
        Button choosebutton = new Button("Choose character"); choosebutton.setLayoutX(300); choosebutton.setLayoutY(450);
        Button cancelbutton = new Button("Back"); cancelbutton.setLayoutX(50); cancelbutton.setLayoutY(450);
        Button nextbutton = new Button("Next"); nextbutton.setLayoutX(500); nextbutton.setLayoutY(50);
        Button prevbutton = new Button("Previous"); prevbutton.setLayoutX(200); prevbutton.setLayoutY(50);
        
        panename.getChildren().addAll(choosebutton, cancelbutton, nextbutton, prevbutton, this.character_name_textfield, this.character_bio_textarea, character_imgview, character_stats_textarea);
        
        useArrowKeys();        
        
    //ActionListener block ----------------------------------------------------------------------------------
        //Choose selected hero_name-------------------------------------------------------------------------------
        choosebutton.setOnAction(Action -> {
            character_name_textfield.setText("You have selected: "+hero_name);
            
        });
        
        //Back to previous page (Main menu)------------------------------------------------------------------
        cancelbutton.setOnAction(Action -> {
           
            stagename.close();
        });
        
        //Get the next Player---------------------------------------------------------------------------------
        nextbutton.setOnAction(Action -> {
         
            nextPlayer();
                
        }); 
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        prevbutton.setOnAction(Action -> {
            
            previousPlayer();
                    
        });
                
    }
        
        public void nextPlayer(){
        
           if(i < cdbase.players_img.size()-1){
                    i++;
                    }
                    else {
                        i = cdbase.players_img.size()-1;
                    }
                try {
                    character_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    inputimg = new FileInputStream(cdbase.players_img.get(i));
                    character_name_textfield.setText(cdbase.players_name.get(i));
                    character_bio_textarea.setText(cdbase.players_bio.get(i));
                    character_stats_textarea.clear();
                    character_stats_textarea.appendText("Gender: "+cdbase.players_stats_gender.get(i));
                    character_stats_textarea.appendText("\nAge: "+cdbase.players_stats_age.get(i));
                    character_stats_textarea.appendText("\nCast: "+cdbase.players_stats_cast.get(i));
                    character_stats_textarea.appendText("\nHealth: "+cdbase.players_stats_health.get(i));
                    character_stats_textarea.appendText("\nSkill: "+cdbase.players_stats_skill.get(i));
                    character_stats_textarea.appendText("\nExperience: "+cdbase.players_stats_xp.get(i));
                    character_stats_textarea.appendText("\nDefence: "+cdbase.players_stats_defence.get(i));
                    this.hero_name = cdbase.players_name.get(i);
                    
                                        
                    character_imgview = new ImageView(new Image(inputimg));
                    character_imgview.setLayoutX(600);
                    character_imgview.setLayoutY(100);
                    panename.getChildren().add(character_imgview);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
                
            } 
        }
        
        public void previousPlayer(){
            
           character_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }            
                    try {
                        inputimg = new FileInputStream(cdbase.players_img.get(i));
                        character_name_textfield.setText(cdbase.players_name.get(i));
                        character_bio_textarea.setText(cdbase.players_bio.get(i));
                        character_stats_textarea.clear();
                        character_stats_textarea.appendText("Gender: "+cdbase.players_stats_gender.get(i));
                        character_stats_textarea.appendText("\nAge: "+cdbase.players_stats_age.get(i));
                        character_stats_textarea.appendText("\nCast: "+cdbase.players_stats_cast.get(i));
                        character_stats_textarea.appendText("\nHealth: "+cdbase.players_stats_health.get(i));
                        character_stats_textarea.appendText("\nSkill: "+cdbase.players_stats_skill.get(i));
                        character_stats_textarea.appendText("\nExperience: "+cdbase.players_stats_xp.get(i));
                        character_stats_textarea.appendText("\nDefence: "+cdbase.players_stats_defence.get(i));
                        this.hero_name = cdbase.players_name.get(i);
                        
                                                
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
                    };
                
            character_imgview = new ImageView(new Image(inputimg));
            character_imgview.setLayoutX(600);
            character_imgview.setLayoutY(100);
            panename.getChildren().add(character_imgview); 
        }
      
        public String getHeroName(){
            return hero_name;
        }
        
        public void useArrowKeys(){
        scenename.setOnKeyPressed(event ->{
            switch  (event.getCode()){
                case RIGHT: nextPlayer();break;
                case LEFT: previousPlayer(); break;
            }
        
        });
        
        }
        
        }       //-----------------------------------------------------------------------------------------------------   
