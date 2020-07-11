/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Characters;

/**
 *
 * @author VERESG
 */
public abstract class NPC_Elf extends NPC {
    
  public NPC_Elf(){
  
 }
   
    public String getFname() {
        return fname;
    }

   
   public String getCast() {
        return cast;
    }

  
   public int getHealth_point() {
        return health_point;
    }

 
    @Override
    public void attacksOpponent(Character opponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  @Override //Felülírtuk az Ős (Npc) metódusát, mivel csak a "human" -nak lehet pénze (Money) és Iventory -ja, a többi Npc -nél (állatoknál és szörnyeknél) jobb, ha hanem jelenik meg. :)
    public void Who_Am_I(){
       
        System.out.println(fname == null ? "Nameless": "Name: "+fname);
        System.out.println(race == null ? "Raceless": "Type: "+race);
        System.out.println(cast == null ? "Outlaw": "Cast: "+cast);
        System.out.println(hasmoney == 0 ? "Beggar": "Money: "+hasmoney);
        System.out.println(fav_weapon.weapon_description == null ? "Fist": cast+" is "+fav_weapon.weapon_say+" with its "+fav_weapon.getDescription()+".");
        System.out.println(health_point == 0 ? "Dead": "HP: "+health_point);
        System.out.println(defend_point == 0 ? "Defenseless": "Defend: "+defend_point);
        System.out.println(fav_weapon.getMin_damage() == 0 ? "Can't hit": "Damage: "+fav_weapon.getMin_damage()+" - "+fav_weapon.getMax_damage());
        System.out.println(fav_weapon.getMin_damage() == 0 ? "Can't hit": "Hits by "+current_weapon_damage+" hit points.");
        System.out.println();
        System.out.println("Current inventory:");
        
        for (int i=0; i < inventory.size(); i++){
        System.out.println("\t"+inventory.get(i));
                
        }
    
}
}