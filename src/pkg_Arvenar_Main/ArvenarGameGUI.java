/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkg_Characters.Character_DataBase_NPC;
import pkg_Characters.Character_DataBase_PC;
import pkg_Characters.NPC;
import pkg_Characters.Playable_Character;
import pkg_Maps.Arvenar_Maps;
import pkg_Maps.Map_DataBase;

/**
 *
 * @author TE332168
 */
public class ArvenarGameGUI{
    
    Stage gamestage = new Stage();
    Pane gamepane = new Pane();
    Pane gameareapane = new Pane(); 
    Scene gamescene = new Scene(gamepane);
    
    ImageView cmpass = new ImageView();
    TextArea dialog_text = new TextArea();
    Label label_mapname = new Label();
    Image bkgImage = new Image("img/bkg_main.jpg", 1366 , 768, true, false, true);
    
    Character_DataBase_NPC npcs = new Character_DataBase_NPC();
            
    NPC rnpc;   //random NPC
    Playable_Character choosen_pc; //selected PC from ArvenarSetPC - static var
    Arvenar_Maps choosen_map; //selected map from Arvenar_Maps - static var
    Map_DataBase random_map = new Map_DataBase();
    
        
    int hero_x = 300, hero_y = 150;
    int pirate_x = 205, pirate_y = 175;
    int j = 0;
    int rx = 0, ry = 0;
    
    final int MAX_X = 420, MAX_Y = 420;
    final int MIN_X = 10, MIN_Y = 10;
    
    Button btn_playgame = new Button("Play game");
    Button btn_exitgame = new Button("Stop game");
    Button btn_playrandommap = new Button("Play on random map");
    Button up = new Button("N");
    Button down = new Button("S");
    Button left = new Button("W");
    Button right = new Button("E");
    Button hero = new Button("H");
    Button pirate = new Button("P");
        
                
    public ArvenarGameGUI() throws FileNotFoundException{ //ez nem lehet void, különben üres stage-t kapsz vissza!!
      
        FileInputStream cmp_img = new FileInputStream("src/img/compass.jpg");
                
        gamestage.setTitle("Arvenar main run");
        gamestage.setScene(gamescene);
       
              
         btn_playgame.setLayoutX(50); btn_playgame.setLayoutY(400);
         btn_exitgame.setLayoutX(50); btn_exitgame.setLayoutY(450);
         btn_playrandommap.setLayoutX(50); btn_playrandommap.setLayoutY(100);
         up.setLayoutX(130); up.setLayoutY(175);
         down.setLayoutX(130); down.setLayoutY(320);
         left.setLayoutX(55); left.setLayoutY(250);
         right.setLayoutX(200); right.setLayoutY(250);
         hero.setLayoutX(hero_x); hero.setLayoutY(hero_y);
         
        label_mapname.setLayoutX(250); label_mapname.setLayoutY(30);
        gameareapane.setLayoutX(250); gameareapane.setLayoutY(50);
        gameareapane.setMaxSize(450, 450); gameareapane.setMinSize(450, 450);
        gameareapane.setStyle("-fx-background-color: lightblue;");
        
        gamestage.setMinWidth(800);
        gamestage.setMinHeight(600);
        
        cmpass.setImage(new Image(cmp_img));
        cmpass.setLayoutX(90); cmpass.setLayoutY(210);
        gamestage.initModality(Modality.APPLICATION_MODAL);
               
        
        dialog_text.setLayoutX(250); dialog_text.setLayoutY(500); dialog_text.setMaxHeight(30); dialog_text.setFocusTraversable(false);
        
        gamepane.setBackground(new Background(new BackgroundImage(bkgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        gamepane.getChildren().addAll(cmpass, btn_playgame, btn_exitgame, btn_playrandommap, gameareapane, up, down, left, right, dialog_text, label_mapname);
        
        
        gamescene.setOnKeyPressed(event ->{
            switch  (event.getCode()){
                case UP: if (hero_y!= MIN_Y){hero.setLayoutY(hero_y -=5);} else hero_y = MIN_Y + 5; check_HeroPos();break;
                case DOWN: if (hero_y!= MAX_Y){hero.setLayoutY(hero_y +=5);} else hero_y = MAX_Y - 5; check_HeroPos();break;
                case LEFT: if (hero_x!= MIN_X){hero.setLayoutX(hero_x -=5);} else hero_x = MIN_X + 5; check_HeroPos();break;
                case RIGHT: if (hero_x!= MAX_X){hero.setLayoutX(hero_x +=5);} else hero_x = MAX_X - 5; check_HeroPos();break;
            }
        });
        
        
        up.setOnAction(action -> {
            
            if (hero_y!= MIN_Y){ 
                hero.setLayoutY(hero_y -=5);
            }
            else hero_y = MIN_Y + 5;
            
            check_HeroPos();
                
        });
        
        down.setOnAction(action -> {
            
            if (hero_y!= MAX_Y){
                
                hero.setLayoutY(hero_y +=5);
            }
            else hero_y = MAX_Y - 5;
            
            check_HeroPos();
            
            
        });
        
        left.setOnAction(action -> {
            
            if (hero_x!= MIN_X){ 
                hero.setLayoutX(hero_x -=5);
            }
            else hero_x = MIN_X + 5;
            
            check_HeroPos();
            
            
        });
        
        right.setOnAction(action -> {
            
            if (hero_x!= MAX_X){ 
                hero.setLayoutX(hero_x +=5);
            }
            else hero_x = MAX_X - 5;
            
            
            check_HeroPos();
            
        });
        
       //----------------------------------------------- 
        
             
        btn_playgame.setOnAction(action -> {
            try {
                /*int rx = 0, ry = 0;
                
                rx = (int)(Math.random()*(MAX_X-MIN_X)+1);
                ry = (int)(Math.random()*(MAX_Y-MIN_Y)+1);
                pirate.setLayoutX(rx); pirate.setLayoutY(ry);*/
                gameareapane.getChildren().clear();
                
                addHero();
                addPirates();
                label_mapname.setText("Hero "+choosen_pc.getFname()+" is playing on current map: "+choosen_map.getMap_name()); //for selected map
                                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarGameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });
              
        btn_playrandommap.setOnAction(action ->{
                     
           try {
                /*int rx = 0, ry = 0;
                
                rx = (int)(Math.random()*(MAX_X-MIN_X)+1);
                ry = (int)(Math.random()*(MAX_Y-MIN_Y)+1);
                pirate.setLayoutX(rx); pirate.setLayoutY(ry);*/
                gameareapane.getChildren().clear();
                
                addHero();
                addPirates();
                label_mapname.setText("Hero "+choosen_pc.getFname()+" is playing on current map: "+random_map.get_Random_Map().getMap_name()); //for random map
                                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArvenarGameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
       
       
        btn_exitgame.setOnAction(action -> {
            
            //gamestage.close();
            ArvenarFXMain.stageElven.setScene(ArvenarFXMain.sceneElven);
            ArvenarFXMain.stageElven.setFullScreen(ArvenarFXMain.flagFullScreen);
            
        });
    
    }
        
        
    public void check_HeroPos(){
        
        pirate.setLayoutX(rx); pirate.setLayoutY(ry); //Csak a P4 koordinátái lesznek
        if (((hero.getLayoutY() < pirate.getLayoutY()+20) && (hero.getLayoutX() < pirate.getLayoutX()+20)) 
                   && ((hero.getLayoutY() > pirate.getLayoutY()-20) && (hero.getLayoutX() > pirate.getLayoutX()-20))){   
        
            hero.setText("Fucked");
            
                 dialog_text.appendText(choosen_pc.getFname()+" was fucked up by "+rnpc.getFname()+"\n");
    }
            else hero.setText("H");
    }
    
    
    public void show_GameGUI(){
        
         gamestage.show();
    }

   public void addPirates() throws FileNotFoundException{
              
       for (j = 0; j < 5; j++){
        
        Button pirate = new Button("P"+j);
        rx = (int)(Math.random()*(MAX_X-MIN_X)+1);
        ry = (int)(Math.random()*(MAX_Y-MIN_Y)+1);
               
        Tooltip ptt = new Tooltip(); //Itt muszáj minden ciklusban új példányt létrehozni (5x)
        NPC random_npc = npcs.getRandomNPC();
        rnpc = random_npc; //átadjuk az osztályszintű változónak a random npc-nket, hogy használhassák más metódusok is.
            ptt.setText(random_npc.getFname());
            ImageView pirate_imgv = new ImageView(new File(random_npc.getAvatarimg()).toURI().toString());
            pirate_imgv.setFitHeight(50); pirate_imgv.setFitWidth(50); //thumbnail-eket akarunk
            ptt.setGraphic(pirate_imgv); //Lásd: hints.txt
            pirate.setTooltip(ptt);
            pirate.setLayoutX(rx); pirate.setLayoutY(ry);
            System.out.println(rx+" / "+ry);
            
        gameareapane.getChildren().add(pirate);
        
       }
   }   
       public void addHero(){
         
                              
                choosen_pc = ArvenarSetPC.selected_pc;
                choosen_map = Arvenar_View_Maps.choosen_map;
                
                ImageView hero_imgv = new ImageView(new File(choosen_pc.getAvatarimg()).toURI().toString());              
                                
                hero_imgv.setFitHeight(50); hero_imgv.setFitWidth(50); //thumbnail-eket akarunk
                Tooltip hero_tooltip = new Tooltip();
                
                hero_tooltip.setGraphic(hero_imgv); 
                hero_tooltip.setText(choosen_pc.getFname());
                hero.setTooltip(hero_tooltip);
                gameareapane.getChildren().add(hero);
                
       }
   
       public Scene game_Scene(){
           return gamescene;
       }
 }

