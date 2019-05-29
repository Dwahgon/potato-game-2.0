package shops;

import java.math.BigDecimal;
import projeto1.Game;
import projeto1.Potatoes;
import projeto1.Building;

public class MSTLands extends Building{
    //===================\\
    //===[CONSTRUCTOR]===\\
    //===================\\
    
    public MSTLands() {
        //Define new values
        this.buildingName = "Terras do MST";
        this.potatoType = Potatoes.allPotatoes[0];
        this.bPrice = new BigDecimal("1000000000");
        this.bAdditionalPrice = new BigDecimal("500000000");
        this.bAdditionalPricePerPurchase = new BigDecimal("275000000");
        this.potatoProduction = 11987;
        
        
        CreateBuildingPanel();
    }
}
