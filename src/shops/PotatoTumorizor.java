package shops;
import projeto1.Building;
import projeto1.Potatoes;

public class PotatoTumorizor extends Building {
    public PotatoTumorizor() {
        //Define new values
        this.buildingName = "Tumorizador de Batatas";
        this.purchasable = false;
        this.potatoType = Potatoes.RequestPotato("Doce");
        this.potatoProduction = 2;
        
        
        CreateBuildingPanel();
    }
}
