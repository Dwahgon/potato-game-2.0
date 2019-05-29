package potatoGame.userInterface;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import potatoGame.GameContent;
import potatoGame.Potato;

public class PotatoTab extends JScrollPane {
    public PotatoTab(GameContent gameContent){
	initComponents(gameContent.getPotatoes().toArray(new Potato[gameContent.getPotatoes().size()]));
    }
    
    private void initComponents(Potato[] potatoes){
	JPanel pane = new JPanel();
	this.setViewportView(pane);
	
	for(Potato potato : potatoes){
	    pane.add(new PotatoTabItem(potato));
	}

    }
}
