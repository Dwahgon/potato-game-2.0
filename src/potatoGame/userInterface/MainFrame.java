package potatoGame.userInterface;

import potatoGame.PlayerStats;

public class MainFrame extends javax.swing.JFrame {
    public MainFrame(potatoGame.GameContent gameContent, MoneyFrame paramMoneyFrame, PotatoTab paramPotatoTab, TaxFrame paramTaxFrame, ProductorTab paramProductorTab) {
	initComponents(gameContent, paramMoneyFrame, paramPotatoTab, paramTaxFrame, paramProductorTab);
    }
    
    public MainFrame(potatoGame.GameContent gameContent, PlayerStats plrStats){
	initComponents(gameContent, new MoneyFrame(plrStats.getMoney()), new PotatoTab(gameContent), new TaxFrame(), new ProductorTab(gameContent.getProductors()));
    }
    
    private void initComponents(potatoGame.GameContent gameContent, MoneyFrame paramMoneyFrame, PotatoTab paramPotatoTab, TaxFrame paramTaxFrame, ProductorTab paramProductorTab){
	initComponents();
	
	moneyFrame = paramMoneyFrame;
	taxFrame = paramTaxFrame;
	productorTab = paramProductorTab;
	potatoTab = paramPotatoTab;
	
	topBar.add(taxFrame);
	topBar.add(moneyFrame);
	mainPane.addTab("Batatas", paramPotatoTab);
	mainPane.addTab("Produtores", productorTab);
	
	setVisible(true);
    }
	    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        topBar = new javax.swing.JPanel();
        mainPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Potato Game");
        setMinimumSize(new java.awt.Dimension(350, 350));
        setPreferredSize(new java.awt.Dimension(600, 500));

        title.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("The Potato Game");
        title.setPreferredSize(new java.awt.Dimension(400, 51));

        topBar.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
            .addComponent(mainPane)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane mainPane;
    private javax.swing.JLabel title;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
    private MoneyFrame moneyFrame;
    private TaxFrame taxFrame;
    private ProductorTab productorTab;
    private PotatoTab potatoTab;
    
    public MoneyFrame getMoneyFrame() {
	return moneyFrame;
    }

    public TaxFrame getTaxFrame() {
	return taxFrame;
    }

    public ProductorTab getProductorTab() {
	return productorTab;
    }
}
