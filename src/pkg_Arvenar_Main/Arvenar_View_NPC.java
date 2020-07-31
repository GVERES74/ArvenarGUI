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
    Character_DataBase_NPC cdbase_npc = new Character_DataBase_NPC();
            
    FileInputStream inputimg = new FileInputStream("src/img/npc_animal_bbear1.jpg");
    Image selectedpcimage = new Image(inputimg);
    ImageView npc_imgview = new ImageView(selectedpcimage);
    TextField npc_name_textfield = new TextField(); 
    TextArea npc_bio_textarea = new TextArea();
    TextArea npc_description_textarea = new TextArea();
    TextArea npc_stats_textarea = new TextArea();
    
    Stage stagename = new Stage();
    Pane panename = new Pane();
    Scene scenename = new Scene(panename);
    String npc_name = "";
                    
    public Arvenar_View_NPC() throws FileNotFoundException {
        
        //cdbase_npc.Character_DataBase_NPC(); //Karakter adatbázis inicializálása. Nem kell ha konstruktort hozol létre: "public Character_DataBase_NPC()" és ebben töltöd fel az ArrayList-et. Nem lehet void!!
        
        npc_name_textfield.setLayoutX(305); npc_name_textfield.setLayoutY(50);
       
        npc_name_textfield.setEditable(false); npc_name_textfield.setFocusTraversable(false);
            
        npc_bio_textarea.setLayoutX(100); npc_bio_textarea.setLayoutY(100); npc_bio_textarea.setWrapText(true); npc_bio_textarea.setMaxHeight(100);
       
        npc_bio_textarea.setEditable(false); npc_bio_textarea.setFocusTraversable(false);
        
        npc_stats_textarea.setLayoutX(100); npc_stats_textarea.setLayoutY(220); npc_stats_textarea.setWrapText(true); npc_stats_textarea.setMaxWidth(200.0);
        npc_stats_textarea.setEditable(false); npc_stats_textarea.setFocusTraversable(false);
        
        npc_description_textarea.setLayoutX(310); npc_description_textarea.setLayoutY(220); npc_description_textarea.setWrapText(true); npc_description_textarea.setMaxWidth(250.0);
        npc_description_textarea.setEditable(false); npc_description_textarea.setFocusTraversable(false);
                            
        getNonPlayableCharacter();
        
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
        
        panename.getChildren().addAll(choosebutton, cancelbutton, nextbutton, prevbutton, this.npc_name_textfield, this.npc_bio_textarea, npc_stats_textarea, npc_description_textarea);
                
        useArrowKeys();
    //ActionListener block ----------------------------------------------------------------------------------
        
        
        //Choose selected hero_name-------------------------------------------------------------------------------
        choosebutton.setOnAction(Action -> {
            npc_name_textfield.setText("You are viewing: "+npc_name);
            
            
        });
        
        //Back to previous page (Main menu)------------------------------------------------------------------
        cancelbutton.setOnAction(Action -> stagename.close());
                                  
               
        //Get the next Player---------------------------------------------------------------------------------
        nextbutton.setOnAction(Action -> nextNpc());
                
        //--------------------------------------------------------------------------------------------------------
        
        //Get the previous Player---------------------------------------------------------------------------------
        prevbutton.setOnAction(Action -> previousNpc());
                
    }
        
        public void nextNpc(){
        
            if(i < cdbase_npc.npc_character.size()-1){
                    i++;
            }
                    else {
                        i = cdbase_npc.npc_character.size()-1;
                    }
                    npc_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    getNonPlayableCharacter();
        }
        
        public void previousNpc(){
            
           npc_imgview.setImage(null); //ImageView "törlése", különben egymásra pakolja az image-ket.
                    
                    if(i > 0){
                    i--;
                    }
                    else {
                        i = 0;
                    }            
                    getNonPlayableCharacter();
        }
      
        public String getNpcName(){
            return cdbase_npc.getNPC(i).getFname();
        }
        
        public void useArrowKeys(){
            scenename.setOnKeyPressed(event ->{
                switch  (event.getCode()){
                 case RIGHT: nextNpc();break;
                    case LEFT: previousNpc(); break;
            }
        
            });
    
        }
        
        public void getNonPlayableCharacter(){
            
         try {
             inputimg = new FileInputStream(cdbase_npc.npc_img.get(i));
             npc_name_textfield.setText(cdbase_npc.getNPC(i).getFname());
             npc_bio_textarea.setText(cdbase_npc.getNPC(i).getBiography());
             npc_stats_textarea.clear();
             npc_description_textarea.clear();
             npc_description_textarea.appendText(cdbase_npc.getNPC(i).getNpcDesc());
             
             
             npc_stats_textarea.appendText("Race: "+cdbase_npc.getNPC(i).getRace());
             npc_stats_textarea.appendText("\nCast: "+cdbase_npc.getNPC(i).getCast());
             npc_stats_textarea.appendText("\nHealth: "+cdbase_npc.getNPC(i).getHealth_point());
             npc_stats_textarea.appendText("\nWeapon: "+cdbase_npc.getNPC(i).getFav_weapon());
             npc_stats_textarea.appendText("\nBattle shout: "+cdbase_npc.getNPC(i).getShout());
             this.npc_name = cdbase_npc.getNPC(i).getFname();
             
             
             
             npc_imgview = new ImageView(new Image(inputimg));
             npc_imgview.setLayoutX(600);
             npc_imgview.setLayoutY(100);
             panename.getChildren().add(npc_imgview);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Arvenar_View_NPC.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        }
        
        
        }

