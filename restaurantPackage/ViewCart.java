package restaurantPackage;

import restaurantPackage.Main;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;

public class ViewCart extends View{
    public JLabel backgroundLabel, logoBackgroundLabel;
    private ImageIcon background,background3,background3Logo, logoBackground;
    private Image background2,backgroundImageLogo;
    String title;
    int screenWidth;
    int screenHeight;

    /**
     * Constructor.
     * Sets the background, logo image, and the other neccessary things
     * @param title String object to be set to the title field of the class
     */
    public ViewCart(String title, Main mainReference) //, Item[] items, int nrItems)
    {
        super(title, mainReference);
        this.title = title;
        this.screenHeight = mainReference.screenHeight;
        this.screenWidth = mainReference.screenWidth;

        background = new ImageIcon(this.getClass().getResource("/wallpapersimple.jpg"));
        background2 = background.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        background3 = new ImageIcon(background2);
        backgroundLabel = new JLabel(background3, JLabel.CENTER);
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        this.add(backgroundLabel);

        int ButSize1 = (int) (2 * screenWidth / 192); //20
        int ButHeight = (int) (4 * screenWidth / 192); //40
        int butWidth = (int) (12 * screenWidth / 192); //120
        int butYaxis = (int) (screenWidth / 384);//5

        nrButtons = 3;
        buttons = new JButton[nrButtons];
        //buttons on top of the page
        for (int i = 0; i < 2; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setOpaque(false);
            this.buttons[i].setContentAreaFilled(false);
            this.buttons[i].setBorderPainted(false);

            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setFont(new Font("TimesRoman", 20, ButSize1));
            backgroundLabel.add(buttons[i]);
        }
        this.buttons[0].setText("HOME");
        this.buttons[0].setBounds(0, butYaxis, butWidth, ButHeight);
        this.buttons[1].setText("LOGIN");
        this.buttons[1].setBounds((int) (160 * screenWidth / 192), butYaxis, (int) (20 * screenWidth / 192), ButHeight);

        for (int i = 2; i < this.nrButtons; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setOpaque(false);
            this.buttons[i].setContentAreaFilled(false);
            this.buttons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setFont(new Font("TimesRoman", 20, 30));
            backgroundLabel.add(this.buttons[i]);
        }
        //checkout button
        buttons[2].setText("Checkout");
        buttons[2].setBounds((int) (screenWidth/1.3f), (int) (screenHeight/1.2f), screenWidth/7, screenHeight/20);

        buttons[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < Main.views.length; i++)
                    Main.views[i].setVisible(false);
            }
        });

        /*addComponentListener(new ComponentAdapter() {
            public void componentHidden ( ComponentEvent e )
            {
                System.out.println ( "Component hidden" );
            }
            @Override
            public void componentShown(ComponentEvent e) {



            }
        });*/

        rebuild();


    }
    /**
     *here show cart items
     */
    public void rebuild() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //scroll to see all items
        JScrollPane scroll = new JScrollPane();
        scroll.getVerticalScrollBar().setBackground(new Color(32, 32, 32));//color to be dark
        JPanel panel2 = new JPanel(new GridLayout(0, 1, 0, 15));
        // panel scroll bar to be dark
        panel2.setBackground(new Color(32, 32, 32));
        scroll.setBounds(screenWidth/30, (int) (screenHeight/5.5f), (int) (screenWidth/1.07f), (int) (screenHeight/2.2f));

        double fullness = 1;

        if(Main.cart.keySet().size() == 1)
            fullness = 1;
        if(Main.cart.keySet().size() == 2)
            fullness /= 2f;
        if(Main.cart.keySet().size() >= 3)
            fullness /= 3f;
        float totalPrice = 0;

        for(String key : Main.cart.keySet()) {

            int id = Integer.parseInt(key);

            //food photo for each type of food
            ImageIcon foodPicture = new ImageIcon(this.getClass().getResource("/foods/" + id + ".png"));
            Image foodImage = foodPicture.getImage().getScaledInstance((int) (0.60f * 30 * screenWidth / 192), (int) (0.60f * 30 * screenWidth / 192), Image.SCALE_DEFAULT);
            ImageIcon scaled = new ImageIcon(foodImage);
            JLabel foodLabel = new JLabel(scaled, JLabel.LEFT);
            foodLabel.setBounds(0, 0, (int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030));

            foodLabel.setHorizontalTextPosition(JLabel.CENTER);
            foodLabel.setVerticalTextPosition(JLabel.TOP);
            foodLabel.setIconTextGap(4);

            foodLabel.setBounds(0, 0, scroll.getWidth(), (int) (0.45f * 30 * screenWidth / 192));
            foodLabel.setSize(scroll.getWidth(), (int) (0.45f * 30 * screenWidth / 192));

            foodLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

            //name of the food
            JTextPane textPaneee = new JTextPane();
            textPaneee.setText(Main.idName.get(String.valueOf(id)));
            textPaneee.setForeground(Color.WHITE);
            textPaneee.setBackground(new Color(32, 32, 32));
            StyledDocument doc = textPaneee.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            textPaneee.setBounds((int) (0.61f * 30 * screenWidth / 192), (int) (fullness*(foodLabel.getHeight()*1.75f - foodLabel.getHeight()/2)), 200, (int) (fullness * foodLabel.getHeight()));
            foodLabel.add(textPaneee);

            //quantity
            JTextPane quantity = new JTextPane();
            quantity.setText("Quantity: " + Main.cart.get(String.valueOf(id)));
            quantity.setForeground(Color.WHITE);
            quantity.setBackground(new Color(32, 32, 32));
            doc = quantity.getStyledDocument();
            center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            quantity.setBounds((int) (2.4f * 30 * screenWidth / 192), (int) (fullness*(foodLabel.getHeight()*1.75f - foodLabel.getHeight()/2)), 150, (int) (fullness*foodLabel.getHeight()));
            foodLabel.add(quantity);

            //plus button
            JButton plus = new JButton();
            plus.setOpaque(false);
            plus.setContentAreaFilled(false);
            plus.setText("+");
            plus.setForeground(Color.WHITE);
            plus.setFont(new Font("TimesRoman", 20, 20));
            plus.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            plus.setBounds((int) (3.2f * 30 * screenWidth / 192), (int) (fullness*(foodLabel.getHeight()*1.75f - foodLabel.getHeight()/2)), 60, (int) (fullness*foodLabel.getHeight()));
            foodLabel.add(plus);

            //minus button
            JButton minus = new JButton();
            minus.setOpaque(false);
            minus.setContentAreaFilled(false);
            minus.setText("-");
            minus.setForeground(Color.WHITE);
            minus.setFont(new Font("TimesRoman", 20, 20));
            minus.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            minus.setBounds((int) (3.5f * 30 * screenWidth / 192), (int) (fullness*(foodLabel.getHeight()*1.75f - foodLabel.getHeight()/2)), 60, (int) (fullness*foodLabel.getHeight()));
            foodLabel.add(minus);

            //price label
            JTextPane price = new JTextPane();
            float priceAmount = Main.cart.get(String.valueOf(id)) * Main.idPrice.get(String.valueOf(id));
            price.setText("Price: " + String.format("%.02f", (priceAmount)) + " RON");
            price.setBackground(new Color(32, 32, 32));
            price.setForeground(Color.WHITE);
            totalPrice += priceAmount;
            doc = price.getStyledDocument();
            center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            price.setBounds((int) (4f * 30 * screenWidth / 192), (int) (fullness*(foodLabel.getHeight()*1.75f - foodLabel.getHeight()/2)), 150, (int) (fullness*foodLabel.getHeight()));
            foodLabel.add(price);

            plus.addActionListener(actionEvent -> {
                Main.cart.put(String.valueOf(id), Main.cart.getOrDefault(String.valueOf(id), 0) + 1);
                rebuild();
            });

            minus.addActionListener(actionEvent -> {
                Main.cart.put(String.valueOf(id), Main.cart.getOrDefault(String.valueOf(id), 0) - 1);
                if(Main.cart.get(String.valueOf(id)) == 0)
                    Main.cart.remove(String.valueOf(id));
                rebuild();
            });


            //backgroundLabel.add(logoBackgroundLabel);
            panel2.add(foodLabel);
            //panel2.add(pic);
            //System.out.println(id);
        }

        //total text
        JTextPane totalPriceText = new JTextPane();
        totalPriceText.setText("Total: " + String.format("%.02f", totalPrice));
        totalPriceText.setBackground(new Color(32, 32, 32));
        totalPriceText.setForeground(Color.WHITE);
        StyledDocument doc = totalPriceText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        totalPriceText.setBounds((int) (screenWidth/1.5f), (int) (screenHeight/1.5f), screenWidth/7, screenHeight/20);

        //backgroundLabel.add(totalPriceText);

        scroll.getViewport().setView(panel2);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBackground(Color.BLACK);

        if(backgroundLabel.getComponentCount() > 4) {
            backgroundLabel.remove(backgroundLabel.getComponentCount() - 1);
            backgroundLabel.remove(backgroundLabel.getComponentCount() - 1);
        }

        backgroundLabel.add(scroll);
        backgroundLabel.add(totalPriceText);
    }
}


