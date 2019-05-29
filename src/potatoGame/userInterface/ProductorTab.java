package potatoGame.userInterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import potatoGame.productor.Productor;

public class ProductorTab extends JScrollPane{
    private final ArrayList<ProductorTabItem> productorTabItems = new ArrayList<>();
    
    public ProductorTab(Productor... productors){
	initComponents(productors);
    }
    
    public ProductorTab(java.util.ArrayList<Productor> productors){
	initComponents(productors.toArray(new Productor[productors.size()]));
    }
    
    private void initComponents(Productor[] productors){
	JPanel pane = new JPanel();
	this.setViewportView(pane);
	
	pane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
	
	for(Productor productor : productors){
	    ProductorTabItem pti = new ProductorTabItem(productor);
	    productorTabItems.add(pti);
	    pane.add(pti);
	}
    }
    
    public void updateProductorProgress(Productor productor){
	for (ProductorTabItem pti : productorTabItems){
	    if (pti.getProductor() == productor){
		long l = Math.round(productor.getProductionProcess().getProductorProgress() * 100.0);
		int i = (int)l;
		pti.setProgressBarProgress(i);
	    }
	}
    }
}

class ProductorTabItem extends JPanel{
    private final JRadioButton activeRadio;
    private final JProgressBar progressBar;
    private final Productor productor;
    
    public ProductorTabItem(Productor paramProductor){
	productor = paramProductor;
	
	setBorder(new javax.swing.border.TitledBorder(productor.getProductorStats().getName()));
	setLayout(new GridLayout(3, 1));
	
	JButton details = new JButton("Detalhes");
	
	activeRadio = new JRadioButton("Ativo", productor.isActive());
	progressBar = new JProgressBar(0, 100);
	
	add(activeRadio);
	add(details);	
	add(progressBar);
	
	details.addActionListener(al -> {
	    new ProductorDetails(productor);
	});
	activeRadio.addActionListener(al ->{
	    productor.setActive(activeRadio.isSelected());
	});
	
	setVisible(true);
    }
    
    public Productor getProductor(){
	return productor;
    }
    
    public void setProgressBarProgress(int progress){
	progressBar.setValue(progress);
    }
}