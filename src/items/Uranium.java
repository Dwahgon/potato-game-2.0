package items;

import projeto1.Announcements;
import projeto1.Item;

public class Uranium extends Item{
    public Uranium(){
        name = "Urânio";
    }
    
    public void UseItem(){
        Announcements.DisplayAnnouncement("Você usou "+name+": Você provavelmente pegou câncer!", Announcements.TextStyles.Normal);
    }
}
