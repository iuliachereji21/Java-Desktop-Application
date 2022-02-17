package restaurantPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Class ViewLogIn extends View and it is the log in page of the application.
 * @author Chereji Iulia
 */
public class ViewLogIn extends View{

    /**
     * Constructor.
     * Sets the background, logo image, and the other neccessary things
     * @param title String object to be set to the title field of the class
     * @param mainReference the frame of the project
     */
    public ViewLogIn(String title, Main mainReference) {
        super(title, mainReference);
        int screenWidth = mainReference.screenWidth;
        int screenHeight = mainReference.screenHeight;
        ImageIcon background = new ImageIcon(this.getClass().getResource("/wallpapersimple.jpg"));
        Image background2 = background.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        ImageIcon background3 = new ImageIcon(background2);
        JLabel backgroundLabel = new JLabel(background3, JLabel.CENTER);
        backgroundLabel.setBounds(0,0,screenWidth, screenHeight);
        this.add(backgroundLabel);

        //some values needed to give the sizes in this view
        int textHeight = (int)(5*screenHeight/103); //50
        int xLabels = (int)(50*screenWidth/192); //500
        int xButtons = (int)(70*screenWidth/192); //700

        nrButtons=4;
        buttons= new JButton[this.nrButtons];

        buttons[0]=new JButton();
        buttons[0].setOpaque(false);
        buttons[0].setContentAreaFilled(false);
        buttons[0].setBorderPainted(false);
        buttons[0].setForeground(Color.WHITE);
        buttons[0].setFont(new Font("TimesRoman",20,(int)(2*screenWidth/192)));
        backgroundLabel.add(buttons[0]);
        buttons[0].setText("HOME");
        buttons[0].setBounds(0,(int)(screenWidth/384),(int)(12*screenWidth/192),(int)(4*screenWidth/192));

        buttons[1]=new JButton();
        buttons[1].setOpaque(false);
        buttons[1].setContentAreaFilled(false);
        buttons[1].setBorderPainted(false);
        buttons[1].setForeground(Color.WHITE);
        buttons[1].setFont(new Font("TimesRoman",20,(int)(2*screenWidth/192)));
        backgroundLabel.add(buttons[1]);
        buttons[1].setText("CART");
        buttons[1].setBounds((int)(180*screenWidth/192),(int)(screenWidth/384),(int)(12*screenWidth/192),(int)(4*screenWidth/192));

        buttons[2]=new JButton();
        buttons[2].setOpaque(false);
        buttons[2].setContentAreaFilled(false);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        buttons[2].setForeground(Color.WHITE);
        buttons[2].setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        backgroundLabel.add(buttons[2]);
        buttons[2].setText("LOG IN");
        buttons[2].setBounds((int)(105*screenWidth/192),(int)(50*screenHeight/103),(int)(screenWidth/7.68),(int)(screenHeight/10.3));

        buttons[3]=new JButton();
        buttons[3].setOpaque(false);
        buttons[3].setContentAreaFilled(false);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        buttons[3].setForeground(Color.WHITE);
        buttons[3].setFont(new Font("TimesRoman",20,(int)(2.5*screenWidth/192)));
        backgroundLabel.add(buttons[3]);
        buttons[3].setText("SIGN UP");
        buttons[3].setBounds(xButtons,(int)(55*screenHeight/103),(int)(19*screenWidth/192),textHeight);

        JLabel emailLabel = new JLabel();
        emailLabel.setText("EMAIL:");
        emailLabel.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(xLabels,(int)(30*screenHeight/103),(int)(20*screenWidth/192),textHeight);
        backgroundLabel.add(emailLabel);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("PASSWORD:");
        passwordLabel.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(xLabels,(int)(40*screenHeight/103),(int)(20*screenWidth/192),textHeight);
        backgroundLabel.add(passwordLabel);

        JLabel noAccountLabel = new JLabel();
        noAccountLabel.setText("NO ACCOUNT?");
        noAccountLabel.setFont(new Font("TimesRoman",20,(int)(2.5*screenWidth/192)));
        noAccountLabel.setForeground(Color.WHITE);
        noAccountLabel.setBounds(xButtons,(int)(50*screenHeight/103),(int)(19*screenWidth/192),textHeight);
        backgroundLabel.add(noAccountLabel);



        nrFields=2;
        fields = new JTextField[nrFields];

        fields[0]=new JTextField(); //email field
        fields[0].setBounds(xButtons,(int)(30*screenHeight/103),(int)(60*screenWidth/192),textHeight);
        fields[0].setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        fields[0].setForeground(Color.WHITE);
        fields[0].setOpaque(false);
        backgroundLabel.add(this.fields[0]);

        fields[1]=new JPasswordField(); //password field
        fields[1].setBounds(xButtons,(int)(40*screenHeight/103),(int)(60*screenWidth/192),textHeight);
        fields[1].setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        fields[1].setForeground(Color.WHITE);
        fields[1].setOpaque(false);
        backgroundLabel.add(this.fields[1]);

        ImageIcon logoBackground = new ImageIcon(this.getClass().getResource("/GalaLunch.png"));
        Image backgroundImageLogo = logoBackground.getImage().getScaledInstance((int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030), Image.SCALE_DEFAULT);
        ImageIcon background3Logo = new ImageIcon(backgroundImageLogo);
        JLabel logoBackgroundLabel = new JLabel(background3Logo, JLabel.CENTER);
        logoBackgroundLabel.setBounds((int)(20*screenWidth/192),(int)(15*screenHeight/1030),(int)(30*screenWidth/192), (int)(208*screenHeight/1030));
        backgroundLabel.add(logoBackgroundLabel);

        nrWrongLabels=1;
        wrongLabels=new JLabel[this.nrWrongLabels];

        wrongLabels[0]=new JLabel();
        wrongLabels[0].setText("wrong email or password");
        wrongLabels[0].setFont(new Font("TimesRoman",20,(int)(2*screenWidth/192)));
        wrongLabels[0].setForeground(Color.RED);
        wrongLabels[0].setBounds(xButtons,(int)(25*screenHeight/103),(int)(30*screenWidth/192),textHeight);
        backgroundLabel.add(this.wrongLabels[0]);
        wrongLabels[0].setVisible(false);
    }

    /**
     * Sets the label in the "wrongLabels" array at the index "nrOfTheLabel" to be visible or not, if index is correct.
     * Used when working with customers, in ViewLogIn, ViewSignUp and ViewAccount to tell the user why their input is wrong.
     * @param b true if we want it to be visible, false otherwise
     * @param nrOfTheLabel index of the label in the "wrongLabels" array, presumed >=0
     */
    public void setWrongLabelVisible(boolean b, int nrOfTheLabel)
    {
        if(nrOfTheLabel<nrWrongLabels)
            wrongLabels[nrOfTheLabel].setVisible(b);
    }
}
