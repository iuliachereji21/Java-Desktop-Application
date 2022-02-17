package restaurantPackage;

import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

/**
 * ViewSpecification specifies the behaviour of a view in the project
 * @author Chereji Iulia
 */

public interface ViewSpecification {

    /**
     * Adds a listener to one of the buttons on this view, which is referenced by the field buttons[nrOfTheButton] if the index is correct.
     * @param listener object of type ButtonsListener which is implemented in Controller.java
     * @param nrOfTheButton the index in the "buttons" array, presumed >=0
     */
    public void addButtonListener(ActionListener listener, int nrOfTheButton);

    /**
     * Adds a listener to one of the text fields of this view, which is referenced by the fields[nrOfTheField] if the index is correct.
     * @param listener object of type FieldListener which is implemented in Controller.java
     * @param nrOfTheField the index in the "fields" array, presumed >=0
     */
    public void addFieldListener(DocumentListener listener, int nrOfTheField);

    /**
     * Sets the visible field to be b.
     * @param b boolean variable, true if we want the view to be visible, false otherwise
     */
    public void setVis(boolean b);

    /**
     * Changes the text of a button.
     * @param text String, new text
     * @param nrOfTheButton index in the "buttons" array, presumed >=0
     */
    public void changeButtonText(String text, int nrOfTheButton);

    /**
     * @return the value of the boolean field visible
     */
    public boolean isVisible();

    /**
     * Sets the text of the text field in the array fields[nrField] to text if the index is correct
     * @param nrField index of the text field in the array fields
     * @param text the text to be set to the field
     */
    public void setTextField(int nrField, String text);

    /**
     * Returns the text from the text field in the array fields[nrField] if the index is correct, otherwise returns null
     * @param nrField index of the text field in the array fields
     * @return the text that was typed in the field
     */
    public String getTextField(int nrField);

    /**
     * @return the nr of the buttons on this view
     */
    public int getNrOfButtonsOnView();

    /**
     * @return the nr of the fields on this view
     */
    public int getNrFields();

    /**
     * @return the nr of the wrong labels on this view
     */
    public int getNrWrongLabels();
}
