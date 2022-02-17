package restaurantPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Class View1 extends View and it is the main (first) page of the application
 * @author Chereji Iulia
 */
public class View1 extends View{

    /**
     * Constructor.
     * Sets the background, logo image, and the other neccessary things
     * @param title String object to be set to the title field of the class
     * @param mainReference the frame of the project
     */
    public View1(String title, Main mainReference)
    {
        super(title, mainReference);
        int screenWidth = mainReference.screenWidth;
        int screenHeight = mainReference.screenHeight;
        ImageIcon background = new ImageIcon(this.getClass().getResource("/wallpaper2.jpg"));
        Image background2 = background.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        ImageIcon background3 = new ImageIcon(background2);
        JLabel backgroundLabel = new JLabel(background3, JLabel.CENTER);
        backgroundLabel.setBounds(0,0,screenWidth, screenHeight);
        this.add(backgroundLabel);

        //some values needed to give the sizes in this view
        int ButSize1= (int)(2*screenWidth/192);
        int ButHeight=(int)(4*screenWidth/192); //40
        int butWidth =(int)(12*screenWidth/192); //120
        int butYaxis=(int)(screenWidth/384);//5
        int ButSize2= (int)(3*screenWidth/192); //3
        int buttonHeight=(int)(screenHeight/7.35); //140
        int buttonWidth=(int)(screenWidth/7.68); //250
        int firstDistance = (int)(screenWidth/192); //10
        int widthDistBetweenButtons=(int)(3*screenWidth/192); //30
        int firstButYaxis=(int)(32*screenHeight/103);
        int secondButYaxis=(int)(50*screenHeight/103);

        nrButtons=10;
        buttons=new JButton[10];

        for(int i=0;i<3;i++)
        {
            buttons[i]=new JButton();
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);

            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("TimesRoman",20,ButSize1));
            backgroundLabel.add(buttons[i]);
        }
        buttons[0].setText("HOME");
        buttons[0].setBounds(0,butYaxis,butWidth,ButHeight);
        buttons[1].setText("LOGIN");
        buttons[1].setBounds((int)(160*screenWidth/192),butYaxis,(int)(20*screenWidth/192),ButHeight);
        buttons[2].setText("| CART");
        buttons[2].setBounds((int)(180*screenWidth/192),butYaxis,butWidth,ButHeight);


        for(int i=3;i<10;i++)
        {
            buttons[i]=new JButton();
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,3));

            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("TimesRoman",20,ButSize2));
            backgroundLabel.add(buttons[i]);
        }


        buttons[3].setText("PIZZA");
        buttons[3].setBounds(firstDistance,firstButYaxis,buttonWidth,buttonHeight);
        buttons[4].setText("PASTA");
        buttons[4].setBounds(firstDistance+buttonWidth+widthDistBetweenButtons,firstButYaxis,buttonWidth,buttonHeight);
        buttons[5].setText("SALADS");
        buttons[5].setBounds(firstDistance+ 2*(buttonWidth+widthDistBetweenButtons),firstButYaxis,buttonWidth,buttonHeight);
        buttons[6].setText("BURGERS");
        buttons[6].setBounds(firstDistance+ 3*(buttonWidth+widthDistBetweenButtons),firstButYaxis,buttonWidth,buttonHeight);
        buttons[7].setText("<html>" + "WARM\nDISHES".replaceAll("\\n","<br>") + "</html>");
        buttons[7].setBounds(firstDistance,secondButYaxis,buttonWidth,buttonHeight);
        buttons[8].setText("DESSERTS");
        buttons[8].setBounds(firstDistance+buttonWidth+widthDistBetweenButtons,secondButYaxis,buttonWidth,buttonHeight);
        buttons[9].setText("DRINKS");
        buttons[9].setBounds(firstDistance+ 2*(buttonWidth+widthDistBetweenButtons),secondButYaxis,buttonWidth,buttonHeight);

        ImageIcon logoBackground = new ImageIcon(this.getClass().getResource("/GalaLunch.png"));
        Image backgroundImageLogo = logoBackground.getImage().getScaledInstance((int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030), Image.SCALE_DEFAULT);
        ImageIcon background3Logo = new ImageIcon(backgroundImageLogo);
        JLabel logoBackgroundLabel = new JLabel(background3Logo, JLabel.CENTER);
        logoBackgroundLabel.setBounds((int)(20*screenWidth/192),(int)(15*screenHeight/1030),(int)(30*screenWidth/192), (int)(208*screenHeight/1030));
        backgroundLabel.add(logoBackgroundLabel);

    }


}
