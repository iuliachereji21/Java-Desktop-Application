//pls let login to be buttons[1]
package restaurantPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewFood extends View{
    private JLabel backgroundLabel, logoBackgroundLabel;
    private ImageIcon background,background3,background3Logo, logoBackground;
    private Image background2,backgroundImageLogo;

    /**
     * Constructor.
     * Sets the background, logo image, and the other neccessary things
     * @param title String object to be set to the title field of the class
     */
    public ViewFood(String title, Main mainReference) //, Item[] items, int nrItems)
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

        int ButSize1 = (int) (2 * screenWidth / 192); //20
        int ButHeight = (int) (4 * screenWidth / 192); //40
        int butWidth = (int) (12 * screenWidth / 192); //120
        int butYaxis = (int) (screenWidth / 384);//5

        nrButtons = 3;
        buttons = new JButton[nrButtons];
        for (int i = 0; i < 3; i++) {
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
        this.buttons[1].setBounds((int) (160 * screenWidth / 192), butYaxis, (int) (20 * screenWidth / 192), ButHeight);
        this.buttons[2].setText("| CART");
        this.buttons[2].setBounds((int) (180 * screenWidth / 192), butYaxis, butWidth, ButHeight);

        /*for (int i = 3; i < this.nrButtons; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setOpaque(false);
            this.buttons[i].setContentAreaFilled(false);
            this.buttons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setFont(new Font("TimesRoman", 20, 30));
            this.backgroundLabel.add(this.buttons[i]);
        }*/

        logoBackground = new ImageIcon(this.getClass().getResource("/GalaLunch.png"));
        backgroundImageLogo = logoBackground.getImage().getScaledInstance((int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030), Image.SCALE_DEFAULT);
        background3Logo = new ImageIcon(backgroundImageLogo);
        logoBackgroundLabel = new JLabel(background3Logo, JLabel.CENTER);
        logoBackgroundLabel.setBounds((int) (20 * screenWidth / 192), (int) (15 * screenHeight / 1030), (int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030));
        backgroundLabel.add(logoBackgroundLabel);

        if(title.equals("menu of the day"))
            return;

        //get the food from database(title == what type of food)
        ResultSet set = Main.getDatabase().getFood(title.toLowerCase());

        JPanel panel = new JPanel();
        //arrange buttons of food from left to right
        panel.setLayout(new FlowLayout());

        JScrollPane scroll = new JScrollPane();
        scroll.getVerticalScrollBar().setBackground(new Color(32, 32, 32));
        scroll.getHorizontalScrollBar().setBackground(new Color(32, 32, 32));
        scroll.getHorizontalScrollBar().setForeground(new Color(32, 32, 32));
        scroll.setBackground(new Color(32, 32, 32));
        scroll.getVerticalScrollBar().setForeground(new Color(32, 32, 32));
        JPanel panel2 = new JPanel(new GridLayout(0, 3, 0, 15));
        panel2.setBackground(new Color(32, 32, 32));

        try {
            while(set.next()) {

                JPanel combined = new JPanel(new GridLayout(0,1));
                combined.setBounds((int) (20 * screenWidth / 192), (int) (15 * screenHeight / 1030), (int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030));

                //get pictures for the menu and put on screen with respect to id of the food item
                int id = set.getInt("id_item");
                ImageIcon foodPicture = new ImageIcon(this.getClass().getResource("/foods/" + id + ".png")); //get picture
                Image foodImage = foodPicture.getImage().getScaledInstance((int) (1.4f * 30 * screenWidth / 192), (int) 1.4f * (208 * screenHeight / 1030), Image.SCALE_DEFAULT);
                ImageIcon scaled = new ImageIcon(foodImage);
                JLabel foodLabel = new JLabel(scaled, SwingConstants.CENTER);

                String stringName = set.getString("name");
                String stringDescription =set.getString("description");
                Double pr = set.getDouble("price");
                String pri = String.valueOf(pr);

                foodLabel.setBounds((int) (combined.getBounds().x/1.5f), 0, (int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030));
                foodLabel.setText(stringName); //set name
                foodLabel.setForeground(Color.WHITE); //color of text
                foodLabel.setHorizontalTextPosition(SwingConstants.CENTER); //position
                foodLabel.setVerticalTextPosition(JLabel.TOP);
                foodLabel.setIconTextGap(4); //distance between name of food and food picture

                //description of 1 item and the price
                JLabel descriptionLabel = new JLabel(stringDescription,SwingConstants.CENTER);
                descriptionLabel.setText("<html>" + stringDescription + "<br>" + pri + " RON" + "</html>");
                descriptionLabel.setVerticalTextPosition(SwingConstants.TOP);
                descriptionLabel.setForeground(Color.WHITE);
                descriptionLabel.setBounds(foodLabel.getX(),foodLabel.getY() + foodLabel.getHeight(), 10,10);// (int) (30 * screenWidth / 192), (int) (208 * screenHeight / 1030));

                //add all info of one product in the panel
                combined.add(foodLabel);
                combined.add(descriptionLabel);
                combined.setBackground(new Color(32, 32, 32));
                combined.setBorder(BorderFactory.createLineBorder(new Color(32, 32, 32),1));

                Main.idName.put(String.valueOf(id), foodLabel.getText());
                Main.idPrice.put(String.valueOf(id), set.getFloat("price"));

                foodLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if(!isVisible())
                            return;
                        Main.cart.put(String.valueOf(id), Main.cart.getOrDefault(String.valueOf(id), 0) + 1);
                        ((ViewCart)Main.views[12]).rebuild();
                    }
                });

                panel2.add(combined);
                //panel2.add(foodLabel);
                //panel2.add(descriptionLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scroll.setViewportView(panel2);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(screenWidth/30, (int) (screenHeight/4f), (int) (screenWidth/1.09f), (int) (screenHeight/1.5f));
        scroll.setBackground(Color.BLACK);
        this.backgroundLabel.add(scroll);
    }
}