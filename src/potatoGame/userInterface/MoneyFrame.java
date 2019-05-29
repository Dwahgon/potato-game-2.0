package potatoGame.userInterface;

import java.awt.GridLayout;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import potatoGame.Money;

public class MoneyFrame extends JPanel {
    private Money money;
    private JLabel priceLabel;
    
    public MoneyFrame(Money paramMoney){
	this.setBorder(BorderFactory.createTitledBorder("Temeres Delatados"));
	this.setLayout(new GridLayout(1,1));
	
        money = paramMoney;
	priceLabel = new JLabel();
	this.add(priceLabel);
	update();
	money.setMoneyFrame(this);
	
	setVisible(true);
    }
    
    public void update(){
	priceLabel.setText(money.getQuantityString());
	priceLabel.setToolTipText(priceLabel.getText());
    }
}
