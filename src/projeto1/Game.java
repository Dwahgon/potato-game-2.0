package projeto1;
import java.math.BigDecimal;

public class Game {
    //====================\\
    //===[DECLARATIONS]===\\
    //====================\\
    
    //==[GUI]==\\
    public static Janela janela;
    
    //==[CLASSES]==\\
    static Government government;
    
    //==[PRODUCTS]==\\
    public static BigDecimal money = new BigDecimal("0");
    public static int potatoProduction = 0;
    
    //===================\\
    //===[MAIN METHOD]===\\
    //===================\\
    
    public static void main(String[] args) throws InterruptedException {
        //Define the L&F of the menu
        DefinirLookAndFeel("Windows");
        
        //Create game menu
        janela = new Janela();
        
        //Create loading frame
        LoadingFrame load = new LoadingFrame();
        load.setVisible(true);
        
        //Initialize classes
        Potatoes.Initialize();
        load.SetProgressBarValue(10);
        Item.Initialize();
        load.SetProgressBarValue(20);
        Building.Initialize();
        load.SetProgressBarValue(30);
        Announcements.Initialize();
        load.SetProgressBarValue(40);
        
        //Configure menus
        janela.FillBuildingPotatoStatus();
        load.SetProgressBarValue(50);
        janela.FillInventory();
        load.SetProgressBarValue(60);
        janela.FillShop();
        load.SetProgressBarValue(70);
        
        //Create government
        government = new Government();
        load.SetProgressBarValue(80);
        
        //Load game
        try{
            PlayerStats loadedData = (PlayerStats) SaveLoad.Load("nomeSave.save");
            loadedData.LoadData();
            
            Announcements.DisplayAnnouncement("Carregado seu save com sucesso!", Announcements.TextStyles.GoodAlert);
        }catch(Exception ex){
            System.out.println("Não foi encontrado save, começando jogo do começo");
        }
        load.SetProgressBarValue(90);
        
        //Update the UI
        janela.money.setText(Utils.stringifyBD(money)+"");
        load.SetProgressBarValue(100);
        
        load.setVisible(false);
        
        //Show game window
        janela.setVisible(true);
    }
    
    //===============\\
    //===[METHODS]===\\
    //===============\\
    
    //Definir visual do menu
    private static void DefinirLookAndFeel(String estilo){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (estilo.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    //Give money
    public static void GiveMoney(BigDecimal qnt){
        money = money.add(qnt);
        
        janela.money.setText(Utils.stringifyBD(money)+"");
    }
    
    //Saves the game
    public static void SaveGame(){
        try{
            //Create the object holding the stats
            PlayerStats ps = new PlayerStats();
            ps.SaveData();
        }catch(Exception ex){
            System.out.println("Unable to save");
        }
    }
}
