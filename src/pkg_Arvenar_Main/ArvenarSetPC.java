/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    
    FileOutputStream data_out = new FileOutputStream("src/data.df");
    FileInputStream data_back = new FileInputStream("src/data.df");
    
    Image selectedpcimage = new Image(inputimg);
    ImageView character_imgview = new ImageView(selectedpcimage);
    TextField character_name_textfield = new TextField(); 
    TextArea character_bio_textarea = new TextArea();
    TextArea character_stats_textarea = new TextArea();
    
    Stage stagename = new Stage();
    Pane panename = new Pane();
    Scene scenename = new Scene(panename);
    String hero_name = "Player 1";
    
    
                    
    public ArvenarSetPC() throws FileNotFoundException {
        
        
        cdbase.dataBase(); //Karakter adatbázis inicializálása
        
        character_name_textfield.setLayoutX(305); character_name_textfield.setLayoutY(50);
        character_name_textfield.setText(cdbase.getPcCharacter(0).getFname());
        character_name_textfield.setEditable(false); character_name_textfield.setFocusTraversable(false);
            
        character_bio_textarea.setLayoutX(100); character_bio_textarea.setLayoutY(100); character_bio_textarea.setWrapText(true); character_bio_textarea.setMaxHeight(100);
        character_bio_textarea.setText(cdbase.getPcCharacter(0).getBiography());
        character_bio_textarea.setEditable(false); character_bio_textarea.setFocusTraversable(false);
        
        character_stats_textarea.setLayoutX(100); character_stats_textarea.setLayoutY(220); character_stats_textarea.setWrapText(true); character_stats_textarea.setMaxWidth(200.0);
        character_stats_textarea.setEditable(false); character_stats_textarea.setFocusTraversable(false);
        
        character_stats_textarea.appendText("Gender: "+cdbase.getPcCharacter(i).getGender()); 
        character_stats_textarea.appendText("\nAge: "+cdbase.getPcCharacter(i).getAge());
        character_stats_textarea.appendText("\nCast: "+cdbase.getPcCharacter(i).getCast());
        character_stats_textarea.appendText("\nHealth: "+cdbase.getPcCharacter(i).getHealth_point());
        character_stats_textarea.appendText("\nSkill: "+cdbase.getPcCharacter(i).getSkill_point());
        character_stats_textarea.appendText("\nExperience: "+cdbase.getPcCharacter(i).getExperience_point());
        character_stats_textarea.appendText("\nDefence: "+cdbase.getPcCharacter(i).getDefend_point());
                    
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
            setHero_name(hero_name);
                        
        });
        
        //Back to previous page (Main menu)------------------------------------------------------------------
        cancelbutton.setOnAction(Action -> stagename.close());
        
        //Get the next Player---------------------------------------------------------------------------------
        nextbutton.setOnAction(Action -> nextPlayer()); 
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        prevbutton.setOnAction(Action -> previousPlayer());
                
    }
        
        public void nextPlayer(){
        
            if(i < cdbase.player_character.size()-1){
                    i++;
            }
                    else {
                        i = cdbase.player_character.size()-1;
                    }
                    character_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.     
                    getPlayableCharacter();
        } 
        
        
        public void previousPlayer(){
            
           character_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }  
                                        
                   getPlayableCharacter();
        }
      
               
        public void useArrowKeys(){
            scenename.setOnKeyPressed(event ->{
                switch  (event.getCode()){
                    case RIGHT: nextPlayer();                
                    case LEFT:  previousPlayer();
                }
            });
        }
        
        
        public void getPlayableCharacter(){
            
        try {
            inputimg = new FileInputStream(cdbase.players_img.get(i));
            character_name_textfield.setText(cdbase.getPcCharacter(i).getFname());
            character_bio_textarea.setText(cdbase.getPcCharacter(i).getBiography());
            character_stats_textarea.clear();
            
            character_stats_textarea.appendText("Gender: "+cdbase.getPcCharacter(i).getGender()); 
            character_stats_textarea.appendText("\nAge: "+cdbase.getPcCharacter(i).getAge());
            character_stats_textarea.appendText("\nCast: "+cdbase.getPcCharacter(i).getCast());
            character_stats_textarea.appendText("\nHealth: "+cdbase.getPcCharacter(i).getHealth_point());
            character_stats_textarea.appendText("\nSkill: "+cdbase.getPcCharacter(i).getSkill_point());
            character_stats_textarea.appendText("\nExperience: "+cdbase.getPcCharacter(i).getExperience_point());
            character_stats_textarea.appendText("\nDefence: "+cdbase.player_character.get(i).getDefend_point());
            hero_name = cdbase.getPcCharacter(i).getFname();
                    
            
            character_imgview = new ImageView(new Image(inputimg));
            character_imgview.setLayoutX(600);
            character_imgview.setLayoutY(100);
            panename.getChildren().add(character_imgview);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArvenarSetPC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        public String getHeroName(){
                           
                return hero_name;
            }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }
        
        
        
        public FileInputStream getHeroImg() throws FileNotFoundException{
                           
               return new FileInputStream(cdbase.players_img.get(i));
            }
                        
        }       //-----------------------------------------------------------------------------------------------------   
