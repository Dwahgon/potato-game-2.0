package projeto1;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Announcements {
    //====================\\
    //===[DECLARATIONS]===\\
    //====================\\
    
    //==[ENUMS]==\\
    public static enum TextStyles{
        Normal, Alert, CriticalAlert, GoodAlert
    }
    
    //==[GUI]==\\
    static JLabel announcementUI = Game.janela.announcementsLabel;
    
    //==[PRIVATE VARIABLES]==\\
    private static int lastAnnouncement = 10;
    
    //======================\\
    //===[INITIALIZATION]===\\
    //======================\\
    
    static void Initialize(){
        //Countdown to display another announcement
        Thread countdown = new Thread(){
            public void run(){
                while(true){
                    //Wait
                    Utils.waitSeconds(1);
                    
                    //Remove a second on the countdown
                    if(lastAnnouncement > 0)
                        lastAnnouncement--;
                    //Show a random announcement
                    else{
                        lastAnnouncement = 10;
                        RandomAnnouncement();
                    }
                }
            }
        };
        
        //Start thread
        countdown.start();
    }
    
    //===============\\
    //===[METHODS]===\\
    //===============\\
    
    /**
     * Changes the text of on the announcements label
     * @param text The text that will be displayed in the announcements label
     * @param style The style that the text will have
     */
    public static void DisplayAnnouncement(String text, TextStyles style){
        switch (style){
            case Normal:
                Announce(text, Color.BLACK, UIManager.getDefaults().getFont("TabbedPane.font"));
                break;
            case Alert:
                Announce(text, Color.YELLOW, UIManager.getDefaults().getFont("TabbedPane.font"));
                break;
            case CriticalAlert:
                Announce(text, Color.RED, UIManager.getDefaults().getFont("TabbedPane.font"));
                break;
            case GoodAlert:
                Announce(text, new Color(0, 150, 0), UIManager.getDefaults().getFont("TabbedPane.font"));
                break;   
        }
        
        lastAnnouncement = 10;
    }
    
    //=======================\\
    //===[PRIVATE METHODS]===\\
    //=======================\\
    
    //Show the announcement
    private static void Announce(String text, Color textColor, Font font){
        announcementUI.setText(text);
        announcementUI.setToolTipText(text);
        announcementUI.setForeground(textColor);
        announcementUI.setFont(font);
    }
    
    //Display a random announcement
    private static void RandomAnnouncement(){
        //Generate a random number
        int ran = (int)(Math.random()*7);
        
        switch(ran){
            case 0:
                DisplayAnnouncement("Notícias - 'Um crescimento em interesse a batatas na sociedade espanta sociólogos'", TextStyles.Normal);
                break;
            case 1:
                DisplayAnnouncement("Notícias - 'Alguns estudiosos apontam que uma das causas da tuberculose pode estar na ingestão de tubérculos'", TextStyles.Normal);
                break;
            case 2:
                DisplayAnnouncement("'Batatas doces são basicamente batatas com tumor' - Um retardado qualquer", TextStyles.Normal);
                break;
            case 3:
                DisplayAnnouncement("Curiosidades - Batatas nascem no chão", TextStyles.Normal);
                break;
            case 4:
                DisplayAnnouncement("'Eu gosto de batatas, mas não gosto de empresas privadas' - Um esquerdista qualquer", TextStyles.Normal);
                break;
            case 5:
                DisplayAnnouncement("'Aécio, uma batata quente' - Estadão", TextStyles.Normal);
                break;
            case 6:
                DisplayAnnouncement("'Ta então tuberculose é açucar de tubérculo?' - Um retardado qualquer", TextStyles.Normal);
                break;
            case 7:
                DisplayAnnouncement("Notícias - 'Cientístistas dizem ter criado dióxido de batata e dizem 'Não sabemos o que é isso, mas criamos dióxido de batata''", TextStyles.Normal);
                break;
        }
    }
}
