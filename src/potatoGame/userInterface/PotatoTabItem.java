package potatoGame.userInterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import potatoGame.Potato;

public class PotatoTabItem extends JLayeredPane{
    private final Potato potato;
    private final JLabel mainLabel;
    
    public PotatoTabItem(Potato paramPotato){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	JLayeredPane mainPane = new JLayeredPane();
	mainPane.setLayout(new GridLayout(1, 2));
	
	JLayeredPane buttonsPane = new JLayeredPane();
	buttonsPane.setLayout(new FlowLayout());
        
	mainLabel = new JLabel();
	JButton buy = new JButton("Comprar");
	JButton sell = new JButton("Vender");
	JButton sellAll = new JButton("Vender Todos");

	potato = paramPotato;
	
	mainPane.add(mainLabel);
	buttonsPane.add(buy);
	buttonsPane.add(sell);
	buttonsPane.add(sellAll);
	mainPane.add(buttonsPane);
	
	updateLabel();
	
	buy.addActionListener(al -> {
	   potato.buyPotato(1);
	});
	
	sell.addActionListener(al -> {
	    potato.sellPotato(1);
	});
	
	sellAll.addActionListener(al -> {
	    potato.sellPotato(potato.getQuantity().intValue());
	});
	
	potato.setPotatoTabItem(this);
    }
    
    public void updateLabel(){
	mainLabel.setText(potato.getName() + ": x"+potato.getQuantity().toString()+" - TD$"+new DecimalFormat("#,###,###,##0.00").format(potato.getCurrentPrice()));
    }
}
