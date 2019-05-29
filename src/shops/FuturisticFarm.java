package shops;


import java.math.BigDecimal;
import projeto1.Game;
import projeto1.Potatoes;
import projeto1.Building;

public class FuturisticFarm extends Building{
    //===================\\
    //===[CONSTRUCTOR]===\\
    //===================\\
    
    public FuturisticFarm() {
        //Define new values
        this.buildingName = "Fazenda Futurística";
        this.potatoType = Potatoes.allPotatoes[0];
        this.bPrice = new BigDecimal("5000000");
        this.bAdditionalPrice = new BigDecimal("175890565");
        this.bAdditionalPricePerPurchase = new BigDecimal("234245");
        this.potatoProduction = 2000;
        
        
        CreateBuildingPanel();
    }
}
