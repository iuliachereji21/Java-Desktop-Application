package restaurantPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Class ViewSignUp extends View and it is the sign up page of the application.
 * @author Chereji Iulia
 */
public class ViewSignUp extends View{

    /**
     * Constructor.
     * Sets the background, logo image, and the other neccessary things
     * @param title String object to be set to the title field of the class
     * @param mainReference the frame of the project
     */
    public ViewSignUp(String title, Main mainReference) {
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
        int xButtons=(int)(20*screenWidth/192);//200
        int butWidth=(int)(40*screenWidth/192); //400
        int butHeeight=(int)(5*screenHeight/103); //50
        int fieldsWidth=(int)(60*screenWidth/192); //600
        int xFieldsWidth=(int)(60*screenWidth/192); //600

        nrButtons=3;
        buttons= new JButton[nrButtons];

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
        buttons[2].setText("CREATE ACCOUNT");
        buttons[2].setBounds((int)(130*screenWidth/192),(int)(75*screenHeight/103),(int)(40*screenWidth/192),(int)(15*screenHeight/103));


        ImageIcon logoBackground = new ImageIcon(this.getClass().getResource("/GalaLunch.png"));
        Image backgroundImageLogo = logoBackground.getImage().getScaledInstance((int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030), Image.SCALE_DEFAULT);
        ImageIcon background3Logo = new ImageIcon(backgroundImageLogo);
        JLabel logoBackgroundLabel = new JLabel(background3Logo, JLabel.CENTER);
        logoBackgroundLabel.setBounds((int)(20*screenWidth/192),(int)(15*screenHeight/1030),(int)(30*screenWidth/192), (int)(208*screenHeight/1030));
        backgroundLabel.add(logoBackgroundLabel);

        int nrDataLabels = 7;
        JLabel[] dataLabels = new JLabel[nrDataLabels];

        for(int i = 0; i< nrDataLabels; i++)
        {
            dataLabels[i]=new JLabel();
            dataLabels[i].setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
            dataLabels[i].setForeground(Color.WHITE);
            backgroundLabel.add(dataLabels[i]);
        }

        dataLabels[0].setText("*Name and surname:");
        dataLabels[0].setBounds(xButtons,(int)(25*screenHeight/103),butWidth,butHeeight);
        dataLabels[1].setText("Date of birth:");
        dataLabels[1].setBounds(xButtons,(int)(35*screenHeight/103),butWidth,butHeeight);
        dataLabels[2].setText("Address:");
        dataLabels[2].setBounds(xButtons,(int)(45*screenHeight/103),butWidth,butHeeight);
        dataLabels[3].setText("Phone number:");
        dataLabels[3].setBounds(xButtons,(int)(55*screenHeight/103),butWidth,butHeeight);
        dataLabels[4].setText("*Email:");
        dataLabels[4].setBounds(xButtons,(int)(65*screenHeight/103),butWidth,butHeeight);
        dataLabels[5].setText("*Password:");
        dataLabels[5].setBounds(xButtons,(int)(75*screenHeight/103),butWidth,butHeeight);
        dataLabels[6].setText("*Confirm password:");
        dataLabels[6].setBounds(xButtons,(int)(85*screenHeight/103),butWidth,butHeeight);

        nrFields=7;
        fields = new JTextField[nrFields];

        for(int i=0;i<nrFields;i++)
        {
            if(i==nrFields-2 || i==nrFields-1)
                fields[i]=new JPasswordField();
            else
                fields[i]=new JTextField();
            fields[i].setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
            fields[i].setForeground(Color.WHITE);
            fields[i].setOpaque(false);
            backgroundLabel.add(fields[i]);
        }

        fields[0].setBounds(xFieldsWidth,(int)(25*screenHeight/103),fieldsWidth,butHeeight);
        fields[1].setBounds(xFieldsWidth,(int)(35*screenHeight/103),fieldsWidth,butHeeight);
        fields[2].setBounds(xFieldsWidth,(int)(45*screenHeight/103),fieldsWidth,butHeeight);
        fields[3].setBounds(xFieldsWidth,(int)(55*screenHeight/103),fieldsWidth,butHeeight);
        fields[4].setBounds(xFieldsWidth,(int)(65*screenHeight/103),fieldsWidth,butHeeight);
        fields[5].setBounds(xFieldsWidth,(int)(75*screenHeight/103),fieldsWidth,butHeeight);
        fields[6].setBounds(xFieldsWidth,(int)(85*screenHeight/103),fieldsWidth,butHeeight);

        nrWrongLabels=10;
        wrongLabels=new JLabel[nrWrongLabels];

        for(int i=0;i<nrWrongLabels-1;i++)
        {
            wrongLabels[i]=new JLabel();
            wrongLabels[i].setFont(new Font("TimesRoman",20,(int)(2*screenWidth/192)));
            wrongLabels[i].setForeground(Color.RED);
            backgroundLabel.add(wrongLabels[i]);
            wrongLabels[i].setVisible(false);
        }

        wrongLabels[0].setText("*please introduce your name");
        wrongLabels[0].setBounds(xFieldsWidth,(int)(20*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[1].setText("*please introduce a valid date");
        wrongLabels[1].setBounds(xFieldsWidth,(int)(30*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[2].setText("");
        wrongLabels[2].setBounds(xFieldsWidth,(int)(40*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[3].setText("*please introduce a valid phone number");
        wrongLabels[3].setBounds(xFieldsWidth,(int)(50*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[4].setText("*please introduce a valid email");
        wrongLabels[4].setBounds(xFieldsWidth,(int)(60*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[5].setText("*please introduce a password");
        wrongLabels[5].setBounds(xFieldsWidth,(int)(70*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[6].setText("*passwords don't match");
        wrongLabels[6].setBounds(xFieldsWidth,(int)(80*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[7].setText("*please introduce only letters and spaces");
        wrongLabels[7].setBounds(xFieldsWidth,(int)(20*screenHeight/103),fieldsWidth,butHeeight);
        wrongLabels[8].setText("*there exists an account with this email");
        wrongLabels[8].setBounds(xFieldsWidth,(int)(60*screenHeight/103),fieldsWidth,butHeeight); //email

        wrongLabels[9]=new JLabel(); //it is not really a wrong field, it shows the format of date
        wrongLabels[9].setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        wrongLabels[9].setForeground(Color.LIGHT_GRAY);
        backgroundLabel.add(this.wrongLabels[9]);
        wrongLabels[9].setVisible(true);
        wrongLabels[9].setText("YYYY-MM-DD");
        wrongLabels[9].setBounds(xFieldsWidth,(int)(35*screenHeight/103),fieldsWidth,butHeeight);
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
