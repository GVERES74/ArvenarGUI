package pkg_Arvenar_Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.FileNotFoundException;
import pkg_Characters.Summon_Characters;



/**
 *
 * @author VERESG
 */
public class Arvenar_App{
    
    ArvenarSetPC choose_player = new ArvenarSetPC(); 
    Arvenar_View_NPC view_npc = new Arvenar_View_NPC(); 
    Summon_Characters born = new Summon_Characters();
    ArvenarSettings setgame = new ArvenarSettings();
    ArvenarGameGUI gamegui = new ArvenarGameGUI();
    
    
    public Arvenar_App() throws FileNotFoundException{
     //Ez a konstruktor kell ahhoz, hogy lekezelje a fenti példányosítást.
    }
    
    //public static void main(String[] args){ !!Itt nem lehet main függvény, ha máshol már van!! --> ElvenFXMain.java
      
    public void fight() throws FileNotFoundException{
        
            gamegui.show_GameGUI();
            born.dwarf_smith.attacksOpponent(born.wolf2);
            born.human_warrior.attacksOpponent(born.wolf1);
            born.elf_rogue.attacksOpponent(born.outlaw);
            born.outlaw.attacksOpponent(born.dwarf_smith);
            born.wolf2.attacksOpponent(born.human_warrior);
            born.bbear1.attacksOpponent(born.elf_rogue);
            born.merlin.attacksOpponent(born.human_warrior);
            born.bbear2.attacksOpponent(born.dwarf_smith);
            born.human_mage.attacksOpponent(born.bbear2);
     }       
             
        //PC és NPC adatok          //PC és NPC adatok  

     public void trade(){
                
        //born.trader.trade_with_Character(born.dwarf_smith, born.axe);
        born.human_trader.trade_with_Character(born.elf_rogue, born.bow);
     }
        
     public void stats(){
       
        born.load_inventory(); //inventory -k feltöltése
        System.out.println("Current hero: "+choose_player.getHeroName()+"\n");
        
        String hero = choose_player.getHeroName();
        
        switch (hero){
            case "Arthur": born.human_warrior.Who_Am_I(); 
            break;
            case "Morgana": born.human_mage.Who_Am_I();
            break;
            case "Arven": born.elf_rogue.Who_Am_I(); 
            break;
            case "Otli": born.dwarf_smith.Who_Am_I(); 
            break;
        }
        
                
        /*born.trader.Who_Am_I();

        born.outlaw.Who_Am_I();
        
        born.merlin.Who_Am_I();
        
        born.wolf1.Who_Am_I();
        born.wolf2.Who_Am_I();
        
        born.bbear1.Who_Am_I();
        born.bbear2.Who_Am_I();
        
        born.ogre1.Who_Am_I();
        
        born.dragon1.Who_Am_I();*/
   }
     
     public void choose_Character() throws FileNotFoundException{
     
        choose_player.stagename.show();
     }    
     
     public void view_NpcDataBase(){
       view_npc.stagename.show();
     }
     
    public void set_Game(){
        
       setgame.show_Settings();
    }
} 

      
      
      
      



