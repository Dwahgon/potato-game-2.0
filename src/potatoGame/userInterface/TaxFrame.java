package potatoGame.userInterface;
import java.awt.GridLayout;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TaxFrame extends JPanel{
    private JLabel taxPriceLabel, payTimeLabel;
    
    public TaxFrame(){
	initComponents(BigDecimal.ZERO, 0);
    }
    
    public TaxFrame(BigDecimal taxPrice, int payTime){
	initComponents(taxPrice, payTime);
    }
    
    private void initComponents(BigDecimal taxPrice, int payTime){
	this.setBorder(BorderFactory.createTitledBorder("Imposto de Renda"));
	this.setLayout(new GridLayout(2, 1));
	
	JLayeredPane jLayeredPane = new JLayeredPane();
	jLayeredPane.setLayout(new GridLayout(1, 2));
	payTimeLabel = new JLabel();
	taxPriceLabel = new JLabel();
	jLayeredPane.add(payTimeLabel);
	jLayeredPane.add(taxPriceLabel);
	this.add(jLayeredPane);
	
	JButton jButton = new JButton("Pagar Antecipado");
	this.add(jButton);
	
	setTaxPrice(taxPrice);
	setPayTime(payTime);
	
	setVisible(true);
    }
    
    public void setTaxPrice(BigDecimal price){
	taxPriceLabel.setText("TD$ "+price.toPlainString());
	taxPriceLabel.setToolTipText(taxPriceLabel.getText());
    }
    
    public void setPayTime(int time){
	int sec, min;
	min = time/60;
	sec = time - min*60;
	
	payTimeLabel.setText("Tempo: " + min + ":"+sec);
	payTimeLabel.setToolTipText(payTimeLabel.getText());
    }
}
