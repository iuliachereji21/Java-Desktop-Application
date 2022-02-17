package restaurantPackage;

import javax.swing.*;
import java.awt.*;

public class ViewCheckOut extends View{
    private JLabel backgroundLabel, logoBackgroundLabel;
    private ImageIcon background,background3,background3Logo, logoBackground;
    private Image background2,backgroundImageLogo;

    /**
     * Constructor.
     * Sets the background, logo image, and the other neccessary things
     * @param title String object to be set to the title field of the class
     */
    public ViewCheckOut(String title, Main mainReference) //, Item[] items, int nrItems)
    {
        super(title, mainReference);
        int screenWidth = mainReference.screenWidth;
        int screenHeight = mainReference.screenHeight;

        background = new ImageIcon(this.getClass().getResource("/wallpapersimple.jpg"));
        background2 = background.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        background3 = new ImageIcon(background2);
        backgroundLabel = new JLabel(background3, JLabel.CENTER);
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        this.add(backgroundLabel);

        //some values needed to give the sizes in this view
        int ButSize1 = (int) (2 * screenWidth / 192); //20
        int ButHeight = (int) (4 * screenWidth / 192); //40
        int butWidth = (int) (12 * screenWidth / 192); //120
        int butYaxis = (int) (screenWidth / 384);//5

        nrButtons = 4;
        buttons = new JButton[nrButtons];
        for (int i = 0; i < 2; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setOpaque(false);
            this.buttons[i].setContentAreaFilled(false);
            this.buttons[i].setBorderPainted(false);

            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setFont(new Font("TimesRoman", 20, ButSize1));
            this.backgroundLabel.add(buttons[i]);
        }
        this.buttons[0].setText("HOME");
        this.buttons[0].setBounds(0, butYaxis, butWidth, ButHeight);
        this.buttons[1].setText("LOGIN");
        this.buttons[1].setBounds((int) (175 * screenWidth / 192), butYaxis, (int) (20 * screenWidth / 192), ButHeight);
        //this.buttons[2].setText("| CART");
        //this.buttons[2].setBounds((int) (180 * screenWidth / 192), butYaxis, butWidth, ButHeight);

        //set the view of buttons "Place Order" and "back to cart"
        for (int i = 2; i < this.nrButtons; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setOpaque(false);
            this.buttons[i].setContentAreaFilled(false);
            this.buttons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setFont(new Font("TimesRoman", 20, 25));
            this.backgroundLabel.add(this.buttons[i]);
        }

        this.buttons[2].setText("Back to cart"); //back to cart button
        this.buttons[2].setBounds((int) (screenWidth/30f), (int) (screenHeight/1.2f), screenWidth/10, screenHeight/30);

        this.buttons[3].setText("Place order"); //place order button
        this.buttons[3].setBounds(screenWidth - (int) (screenWidth/30f) - screenWidth/10, (int) (screenHeight/1.2f), screenWidth/10, screenHeight/30);

        JLabel address=new JLabel();
        address.setText("Address:");
        address.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        address.setForeground(Color.WHITE);
        address.setBounds(screenWidth/30,screenHeight/10,(int)(20*screenWidth/192),screenHeight/30);
        backgroundLabel.add(address);

        JTextField addressField=new JTextField(); //adress field
        addressField.setBounds(screenWidth/9,screenHeight/10,(int)(40*screenWidth/192),screenHeight/30);
        addressField.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        addressField.setForeground(Color.WHITE);
        addressField.setOpaque(false);
        backgroundLabel.add(addressField);

        //set the views for the delivery label
        JLabel deliveryOption=new JLabel(); //label for the delivery option
        deliveryOption.setText("Delivery Option:");
        deliveryOption.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        deliveryOption.setForeground(Color.WHITE);
        deliveryOption.setBounds(screenWidth/30,screenHeight/4,(int)(50*screenWidth/192),screenHeight/30);
        backgroundLabel.add(deliveryOption);

        //set the appearance for the radio buttons
        JRadioButton home_delivery = new JRadioButton("Home Delivery");
        JRadioButton pickup = new JRadioButton("Pickup");
        home_delivery.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        home_delivery.setOpaque(false);
        home_delivery.setContentAreaFilled(false);
        home_delivery.setBorderPainted(false);
        pickup.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        pickup.setOpaque(false);
        pickup.setContentAreaFilled(false);
        pickup.setBorderPainted(false);
        ButtonGroup group = new ButtonGroup();
        group.add(home_delivery);
        group.add(pickup);
        JPanel radiopanel = new JPanel(new GridLayout(0, 1));
        radiopanel.add(home_delivery);
        radiopanel.add(pickup);
        backgroundLabel.add(radiopanel);
        radiopanel.setBounds(screenWidth/6,screenHeight/4,(int)(50*screenWidth/192),screenHeight/10);
        radiopanel.setOpaque(false);
        home_delivery.setForeground(Color.white);
        pickup.setForeground(Color.white);

        //set appearance for payment methos label
        JLabel paymentMethod=new JLabel();
        paymentMethod.setText("Payment Method:");
        paymentMethod.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        paymentMethod.setForeground(Color.WHITE);
        paymentMethod.setBounds(screenWidth/30, (int) (screenHeight/2.6f),(int)(50*screenWidth/192),screenHeight/30);
        backgroundLabel.add(paymentMethod);

        //set the appearance for the radio buttons
        JRadioButton card = new JRadioButton("Cash");
        JRadioButton arrives = new JRadioButton("Card");
        card.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        card.setOpaque(false);
        card.setContentAreaFilled(false);
        card.setBorderPainted(false);
        arrives.setFont(new Font("TimesRoman",20,(int)(3*screenWidth/192)));
        arrives.setOpaque(false);
        arrives.setContentAreaFilled(false);
        arrives.setBorderPainted(false);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(card);
        group2.add(arrives);
        JPanel groupedRadio = new JPanel(new GridLayout(0, 1));
        groupedRadio.add(card);
        groupedRadio.add(arrives);
        backgroundLabel.add(groupedRadio);
        groupedRadio.setBounds(screenWidth/6,(int) (screenHeight/2.6f),(int)(50*screenWidth/192),screenHeight/10);
        groupedRadio.setOpaque(false);
        card.setForeground(Color.white);
        arrives.setForeground(Color.white);
    }
}

