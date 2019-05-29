package potatoGame;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import potatoGame.productList.*;
import potatoGame.productor.*;

public class GameContent {
    private final ArrayList<Potato> potatoes = new ArrayList<>();
    private final ArrayList<Productor> productors = new ArrayList<>();
    
    public GameContent(PlayerStats plrStats){
	initPotatoes();
	initProductors(plrStats);
    }
    
    public final void initPotatoes(){
	potatoes.addAll(
	    Arrays.asList(
		new Potato("Batata Normal", 5, 2)
	    )
	);
    }
    
    public final void initProductors(PlayerStats plrStats){
	ProductorStats farm = new ProductorStats("Fazenda De Batatas", 1, 0);
	productors.addAll(
	    Arrays.asList(
		new Productor(
		    farm,
		    new ProductorLevel(
			10,
			farm,
			new ProductList(
			    new ProductListItem<>(
				plrStats.getMoney(),
				new BigDecimal("235755"),
				BigDecimalQuantity.INSTANCE
			    )
			),
			new ProductList(
			    new ProductListItem<>(
				plrStats.getMoney(),
				new BigDecimal("254750"),
				BigDecimalQuantity.INSTANCE
			    )
			)   
		    ),
		    new ProductorFactory(
			farm,
			new ProductList(
			    new ProductListItem<>(
				plrStats.getMoney(),
				new BigDecimal("1000"),
				BigDecimalQuantity.INSTANCE
			    )
			),
			new ProductList(
			    new ProductListItem<>(
				plrStats.getMoney(),
				new BigDecimal("2500"),
				BigDecimalQuantity.INSTANCE
			    )
			)
		    ),
		    new ProductionProcess(
			farm,
			new ProductorOutput(
			    farm,
			    new ProductList(
				new ProductListItem<>(
				    potatoes.get(0),
				    10,
				    IntegerQuantity.INSTANCE
				)
			    ),
			    new ProductList(
				new ProductListItem<>(
				    potatoes.get(0),
				    5,
				    IntegerQuantity.INSTANCE
				)
			    )
			),
			new ProductorInput(farm),
			10,
			.25
		    )
		)
	    )
	);
    }
    
    public ArrayList<Productor> getProductors(){return productors;}
    public ArrayList<Potato> getPotatoes(){return potatoes;}
    
    public Productor getSpecificProductor(String name){
	for(Productor productor : productors){
	    if(productor.getProductorStats().getName().equals(name)){
		return productor;
	    }
	}
	
	return null;
    }
}
