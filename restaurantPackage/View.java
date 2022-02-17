package restaurantPackage;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

/**
 * Class View extends JPanel and it is made abstract to be extended by specific Views in the application.
 * @author Chereji Iulia
 */

public abstract class View extends JPanel implements ViewSpecification{
    protected  JButton [] buttons;
    protected JTextField[] fields;
    protected JLabel[] wrongLabels;
    protected JLabel[] accountLabels;
    protected int nrWrongLabels;
    protected int nrAccountLabels;
    protected int nrButtons;
    protected int nrFields;

    protected String title;
    protected boolean visible; //all these are to be inherited

    /**
     * Constructor
     * @param title String object to be set to the title field of the class
     * @param mainReference the frame of the project
     */
    public View(String title, Main mainReference)
    {
        super();
        int screenWidth = mainReference.screenWidth;
        int screenHeight = mainReference.screenHeight;
        this.setBounds((int)(4*screenWidth/192),0,screenWidth, screenHeight);
        this.title = title;
        setVis(false);
        visible=false;
        nrButtons=0;
        nrFields=0;
        nrWrongLabels=0;
        nrAccountLabels=0;
    }


    public void addButtonListener(ActionListener listener, int nrOfTheButton)
    {
        if(nrOfTheButton<nrButtons)
            buttons[nrOfTheButton].addActionListener(listener);
    }

    public void addFieldListener(DocumentListener listener, int nrOfTheField)
    {
        if(nrOfTheField<nrFields)
            fields[nrOfTheField].getDocument().addDocumentListener(listener);
    }

    public void setVis(boolean b)
    {
        visible=b;
    }


    //Used in Controller, after the user has logged in, to show his name instead of "LOG IN" and vice versa.
    public void changeButtonText(String text, int nrOfTheButton)
    {
        if(nrOfTheButton<nrButtons)
            buttons[nrOfTheButton].setText(text);
    }

    public boolean isVisible()
    {
        return visible;
    }

    public int getNrOfButtonsOnView()
    {
        return nrButtons;
    }

    public int getNrFields()
    {
        return nrFields;
    }

    public int getNrWrongLabels() {
        return nrWrongLabels;
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void setTextField(int nrField, String text)
    {
        if(nrField<nrFields)
            fields[nrField].setText(text);
    }

    public String getTextField(int nrField)
    {
        if(nrField<nrFields)
            return fields[nrField].getText();
        else return null;
    }
}
