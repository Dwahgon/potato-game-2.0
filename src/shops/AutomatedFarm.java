package shops;

import java.math.BigDecimal;
import projeto1.Potatoes;
import projeto1.Building;

public class AutomatedFarm extends  Building {
    //===================\\
    //===[CONSTRUCTOR]===\\
    //===================\\
    
    public AutomatedFarm() {
        //Define new values
        this.buildingName = "Fazenda Automatizada";
        this.potatoType = Potatoes.allPotatoes[0];
        this.bPrice = new BigDecimal("30000");
        this.bAdditionalPrice = new BigDecimal("20000");
        this.bAdditionalPricePerPurchase = new BigDecimal("15000");
        this.potatoProduction = 500;
        
        
        CreateBuildingPanel();
    }
}
