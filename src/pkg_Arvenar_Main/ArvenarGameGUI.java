/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author TE332168
 */
public class ArvenarGameGUI {
    
    Stage gamestage = new Stage();
    Pane gamepane = new Pane();
    Pane gameareapane = new Pane();
    Scene gamescene = new Scene(gamepane);
    MPlayer play_music;
    ImageView cmpass = new ImageView();
        
    int hero_x = 300, hero_y = 150;
    int pirate_x = 205, pirate_y = 175;
    
    final int max_x = 400, max_y = 400;
    final int min_x = 30, min_y = 30;
    
    Button btn_playgame = new Button("Play game");
    Button btn_exitgame = new Button("Stop game");
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
         up.setLayoutX(130); up.setLayoutY(175);
         down.setLayoutX(130); down.setLayoutY(320);
         left.setLayoutX(55); left.setLayoutY(250);
         right.setLayoutX(200); right.setLayoutY(250);
         hero.setLayoutX(hero_x); hero.setLayoutY(hero_y);
         pirate.setLayoutX(pirate_x); pirate.setLayoutY(pirate_y);
        
        gameareapane.setLayoutX(250); gameareapane.setLayoutY(50);
        gameareapane.setMaxSize(450, 450); gameareapane.setMinSize(450, 450);
        gameareapane.setStyle("-fx-background-color: lightblue;");
        
        gamestage.setMinWidth(800);
        gamestage.setMinHeight(600);
        
        cmpass.setImage(new Image(cmp_img));
        cmpass.setLayoutX(90); cmpass.setLayoutY(210);
        gamestage.initModality(Modality.APPLICATION_MODAL);
        gameareapane.getChildren().addAll(hero, pirate);
        
        gamepane.getChildren().addAll(cmpass, btn_playgame, btn_exitgame, gameareapane, up, down, left, right);
        
         
                      
        up.setOnAction(action -> {
            
            if (hero_y!= min_y){ 
                hero.setLayoutY(hero_y -=5);
            }
            else hero_y = min_y + 5;
            
            check_HeroPos();
                
        });
        
        down.setOnAction(action -> {
            
            if (hero_y!= max_y){
                
                hero.setLayoutY(hero_y +=5);
            }
            else hero_y = max_y - 5;
            
            check_HeroPos();
            
            
        });
        
        left.setOnAction(action -> {
            
            if (hero_x!= min_x){ 
                hero.setLayoutX(hero_x -=5);
            }
            else hero_x = min_x + 5;
            
            check_HeroPos();
            
            
        });
        
        right.setOnAction(action -> {
            
            if (hero_x!= max_x){ 
                hero.setLayoutX(hero_x +=5);
            }
            else hero_x = max_x - 5;
            
            
            check_HeroPos();
            
        });
        
       //----------------------------------------------- 
        btn_exitgame.setOnAction(action -> {
            
            gamestage.close();
        });
        
        
        
    
    }
        
    public void check_HeroPos(){
            
           if (((hero.getLayoutY() < pirate.getLayoutY()+30) && (hero.getLayoutX() < pirate.getLayoutX()+30)) 
                   && ((hero.getLayoutY() > pirate.getLayoutY()-30) && (hero.getLayoutX() > pirate.getLayoutX()-30))) {
                
                hero.setText("Fucked");
            
        }
            else hero.setText("H");
    }        
    
    public void show_GameGUI(){
        
         gamestage.show();
    }
    
    
    }

