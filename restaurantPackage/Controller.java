package restaurantPackage;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Controller creates the relationship between models and the views.
 * @author Chereji Iulia and Bucur Alexandra
 */
public class Controller {
    private Main mainReference;
    private View[] views; // references to views
    private ModelCustomer model;  //reference to model
    private ModelFood modelFood;
    private String emailIntroduced, passwordIntroduced, nameIntroduced, dateOfBirthIntroduced, addressIntroduced, phoneIntroduced, passwordRepeatedIntroduced, newPasswordIntroduced; //values given by the user which will be sent to the model
    protected final int[] viewToShow = {-1};  //must be seen in the inner classes
    protected Object source; //must be seen in the inner classes, is the source that produced the action event
    /**
     * Constructor
     * @param views array of View type objects.
     * @param model object of ModelCustomer type
     * @param modelFood object of ModelFood type
     * @param mainReference reference to main frame
     * @author Chereji Iulia
     */
    public Controller(View[] views, ModelCustomer model, ModelFood modelFood, Main mainReference)
    {
        this.views = views;
        this.mainReference = mainReference;
        for(int i=0;i < views.length;i++)
        {
            int nrButtons=views[i].getNrOfButtonsOnView();
            for(int j=0;j<nrButtons;j++)
                views[i].addButtonListener(new ButtonsListener(),j);

            int nrFields=views[i].getNrFields();
            for(int k=0;k<nrFields;k++)
                views[i].addFieldListener(new FieldListener(),k);
        }
        this.model=model;
        this.modelFood=modelFood;
    }

    /**
     * Class ButtonsListener used to work with the buttons in the views.
     * @author Chereji Iulia
     */
    class ButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.this.source=e.getSource();

            Thread thread = new Thread(new MyRunnable()); //to perform the computations needed in another thread
            thread.start();

            while(true)
            {
                if(!thread.isAlive())
                {
                    mainReference.setContentPane(views[viewToShow[0]]);
                    try {
                        Thread.sleep(10);
                    }
                    catch(InterruptedException e1)
                    {

                    }
                    mainReference.revalidate();
                    mainReference.repaint();
                    break;
                }
            }

        }
    }

    /**
     * Inner Class MyRunnable does the computations needed to decide what is the next view that has to be shown, and it calls the model's functions.
     * @author Chereji Iulia
     */
    public class MyRunnable implements Runnable{

        @Override
        public void run() {

            viewToShow[0]=-1;
            for(int i=0; viewToShow[0]==-1 && i < views.length; i++)
            {
                if(source==views[i].buttons[0]) //home
                {
                    views[i].setVis(false);
                    views[0].setVis(true);
                    viewToShow[0] =0;
                }
                else
                if(i!=9 && i!=10 && i!=11 && source==views[i].buttons[1]) //login
                {
                    if(Controller.this.model.getCurrentCustomer().getIdCustomer()==0) //not logged in
                    {
                        views[i].setVis(false);
                        views[9].setVis(true);
                        Controller.this.emailIntroduced=null;
                        Controller.this.passwordIntroduced=null;
                        ((ViewLogIn)views[9]).setWrongLabelVisible(false,0);
                        viewToShow[0] =9;
                    }
                    else
                    {
                        views[i].setVis(false);
                        ((ViewAccount)views[11]).setAccountLabels(Controller.this.model.getCurrentCustomer());

                        for(int a=0;a<views[11].nrAccountLabels;a++)
                            ((ViewAccount)views[11]).setAccountLabelVisible(true,a);

                        for(int f=0;f<views[11].getNrFields();f++)
                            views[11].setTextField(f, null);

                        for(int w=0;w<views[11].nrWrongLabels;w++)
                            ((ViewAccount)views[11]).setWrongLabelVisible(false,w);


                        Controller.this.nameIntroduced = null;
                        Controller.this.dateOfBirthIntroduced = null;
                        Controller.this.addressIntroduced = null;
                        Controller.this.phoneIntroduced = null;
                        Controller.this.emailIntroduced = null;
                        Controller.this.passwordIntroduced = null;
                        Controller.this.newPasswordIntroduced = null;

                        views[11].setVis(true);
                        viewToShow[0] = 11;
                    }
                }
                else
                if(i!=9 && i!=10 && i!=11 && i!=12 && i!=13 && source==views[i].buttons[2]) //cart
                {
                    views[i].setVis(false);
                    views[12].setVis(true);
                    viewToShow[0] =12;
                }
                else
                if((i==9 || i==10 || i==11) && source==views[i].buttons[1]) //cart
                {
                    views[i].setVis(false);
                    views[12].setVis(true);
                    viewToShow[0] =12;
                }
            }

            if(viewToShow[0]==-1 && views[0].isVisible()) //main page
                for(int i=3;i<10;i++) //food
                    if(source==views[0].buttons[i])
                    {
                        views[0].setVis(false);
                        views[i-2].setVis(true);
                        viewToShow[0]=i-2;
                    }

            if(viewToShow[0]==-1 && views[9].isVisible()) //login
            {
                if(source==views[9].buttons[2])//login
                {
                    if (model.checkLogIn(Controller.this.emailIntroduced, Controller.this.passwordIntroduced) == 0) //wrong
                    {
                        ((ViewLogIn)views[9]).setWrongLabelVisible(true, 0); //set visible the wrongLabel
                        viewToShow[0]=9;
                    }
                    else
                    {
                        //right
                        //modify instead of login to show name everywhere
                        String name = model.getCurrentCustomer().getCustomerName();
                        if (name.contains(" "))
                            name = name.substring(0, name.indexOf(" "));
                        name = name.toUpperCase();

                        for (int i = 0; i < views.length; i++) {
                            if (i != 9 && i != 10 && i != 11)
                                views[i].changeButtonText(name, 1);
                        }
                        views[9].setVis(false);
                        views[9].setTextField(0,"");
                        views[9].setTextField(1,"");
                        views[0].setVis(true);
                        viewToShow[0]=0;
                    }
                }
                else //sign up
                {
                    views[9].setVis(false);
                    views[10].setVis(true);
                    for(int f=0;f<views[10].getNrFields();f++)
                        views[10].setTextField(f,null);
                    for(int w=0;w<views[10].getNrWrongLabels()-1;w++)
                        ((ViewSignUp)views[10]).setWrongLabelVisible(false,w);
                    Controller.this.nameIntroduced = null;
                    Controller.this.dateOfBirthIntroduced = null;
                    Controller.this.addressIntroduced = null;
                    Controller.this.phoneIntroduced = null;
                    Controller.this.emailIntroduced = null;
                    Controller.this.passwordIntroduced = null;
                    Controller.this.passwordRepeatedIntroduced= null;
                    viewToShow[0]=10;
                }
            }

            if(viewToShow[0]==-1 && views[10].isVisible()) //sign up page
            {
                if(source==views[10].buttons[2])//crate account
                {
                    int nr = model.checkSignUp(nameIntroduced,emailIntroduced,addressIntroduced,phoneIntroduced,passwordIntroduced,passwordRepeatedIntroduced,dateOfBirthIntroduced);
                    if(nr!=-1) //not ok
                    {
                        for(int i=0;i<views[10].getNrWrongLabels()-1;i++)
                            ((ViewSignUp)views[10]).setWrongLabelVisible(false,i);
                        ((ViewSignUp)views[10]).setWrongLabelVisible(true, nr);
                        viewToShow[0]=10;
                    }
                    else
                    {
                        //ok
                        String name = model.getCurrentCustomer().getCustomerName();
                        if(name.contains(" "))
                            name=name.substring(0,name.indexOf(" "));
                        name = name.toUpperCase();
                        for(int i=0; i< views.length; i++)
                        {
                            if (i != 9 && i != 10 && i != 11) {
                                views[i].changeButtonText(name, 1);
                            }
                        }
                        views[10].setVis(false);
                        for(int i=0;i<views[10].getNrFields();i++)
                        {
                            views[10].setTextField(i,"");
                        }

                        views[0].setVis(true);
                        viewToShow[0]=0;
                    }
                }
            }

            if(viewToShow[0]==-1 && views[11].isVisible()) //account page
            {
                if(source==views[11].buttons[2]) //log out
                {
                    model.setCurrentCustomer(new Customer()); //logged out
                    for(int i=0; i< views.length;i++)
                    {
                        if (i != 9 && i != 10 && i != 11) {
                            views[i].changeButtonText("LOGIN", 1);
                        }
                    }
                    views[11].setVis(false);
                    for(int i=0;i<views[11].getNrFields();i++)
                        views[11].setTextField(i,"");
                    views[0].setVis(true);
                    viewToShow[0]=0;
                }
                else
                if(source==views[11].buttons[3]) //update account
                {
                    int nr=model.checkUpdateAccount(nameIntroduced, emailIntroduced, addressIntroduced, phoneIntroduced, passwordIntroduced, newPasswordIntroduced, dateOfBirthIntroduced);
                    if(nr!=-1) //not ok
                    {
                        for(int i=0;i<views[11].getNrWrongLabels();i++)
                            ((ViewAccount)views[11]).setWrongLabelVisible(false,i);
                        ((ViewAccount)views[11]).setWrongLabelVisible(true, nr);
                        viewToShow[0]=11;
                    }

                    else
                    {
                        //ok
                        String name = model.getCurrentCustomer().getCustomerName();
                        if(name.contains(" "))
                            name=name.substring(0,name.indexOf(" "));
                        name = name.toUpperCase();
                        for(int i=0; i < views.length; i++)
                        {
                            if (i != 9 && i != 10 && i != 11) {
                                views[i].changeButtonText(name, 1);
                            }
                        }
                        views[11].setVis(false);
                        views[0].setVis(true);
                        viewToShow[0]=0;
                    }
                }
            }

            if(viewToShow[0]==-1 && views[12].isVisible()) //cart
            {
                views[12].setVis(false);
                views[13].setVis(true);
                viewToShow[0]=13;
            }

            if(viewToShow[0]==-1 && views[13].isVisible()) //checkOut
            {
                if(source==views[13].buttons[3]) //place order
                {
                    views[13].setVis(false);
                    views[0].setVis(true);
                    Main.cart.clear();
                    ((ViewCart)views[12]).rebuild();
                    viewToShow[0]=0;
                }
                else
                if(source==views[13].buttons[2])//back to cart
                {
                    views[13].setVis(false);
                    views[12].setVis(true);
                    viewToShow[0]=12;
                }
            }

        }
    }


    /**
     * Class FieldListener used to work with the fields in the views.
     * @author Chereji Iulia
     */
    class FieldListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            update(e);
        }

        public void update(DocumentEvent e)
        {
            Object sourceDocument = e.getDocument();
            if(views[9].isVisible()) //log in page
            {
                if(sourceDocument==views[9].fields[0].getDocument()) //email field
                {
                    Controller.this.emailIntroduced=new String(views[9].fields[0].getText());
                    return;
                }

                if(sourceDocument==views[9].fields[1].getDocument()) //password
                {
                    Controller.this.passwordIntroduced=new String(views[9].fields[1].getText());
                    return;
                }
            }

            if(views[10].isVisible()) //sign up page
            {
                if(sourceDocument==views[10].fields[0].getDocument()) //name
                {
                    Controller.this.nameIntroduced = new String(views[10].fields[0].getText());
                    return;
                }

                if(sourceDocument==views[10].fields[1].getDocument()) //date of birth
                {
                    Controller.this.dateOfBirthIntroduced = new String(views[10].getTextField(1));
                    if(Controller.this.dateOfBirthIntroduced==null) //show the label "YYYY-MM-DD"
                        ((ViewSignUp)views[10]).setWrongLabelVisible(true,9);
                    else
                    if(Controller.this.dateOfBirthIntroduced.isEmpty()) //show the label "YYYY-MM-DD"
                        ((ViewSignUp)views[10]).setWrongLabelVisible(true,9);
                    else ((ViewSignUp)views[10]).setWrongLabelVisible(false,9); //don't show the label "YYYY-MM-DD"
                    return;
                }

                if(sourceDocument==views[10].fields[2].getDocument()) //address
                {
                    Controller.this.addressIntroduced = new String(views[10].fields[2].getText());
                    return;
                }

                if(sourceDocument==views[10].fields[3].getDocument()) //phone
                {
                    Controller.this.phoneIntroduced = new String(views[10].fields[3].getText());
                    return;
                }

                if(sourceDocument==views[10].fields[4].getDocument()) //email
                {
                    Controller.this.emailIntroduced = new String(views[10].fields[4].getText());
                    return;
                }

                if(sourceDocument==views[10].fields[5].getDocument()) //password
                {
                    Controller.this.passwordIntroduced = new String(views[10].fields[5].getText());
                    return;
                }

                if(sourceDocument==views[10].fields[6].getDocument()) //password repeated
                {
                    Controller.this.passwordRepeatedIntroduced = new String(views[10].fields[6].getText());
                    return;
                }
            }

            if(views[11].isVisible()) // ViewAccount page
            {
                if(sourceDocument == views[11].fields[0].getDocument()) //name
                {
                    Controller.this.nameIntroduced = new String(views[11].fields[0].getText());

                    if(Controller.this.nameIntroduced==null) //show customer's current name
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,0);
                    else
                    if(Controller.this.nameIntroduced.isEmpty())
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,0); //show customer's current name
                    else ((ViewAccount)views[11]).setAccountLabelVisible(false,0); //don't show customer's current name
                    return;
                }

                if(sourceDocument == views[11].fields[1].getDocument()) //date
                {
                    Controller.this.dateOfBirthIntroduced = new String(views[11].fields[1].getText());

                    if(Controller.this.dateOfBirthIntroduced==null)
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,1); //show customer's current date of birth
                    else
                    if(Controller.this.dateOfBirthIntroduced.isEmpty())
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,1); //show customer's current date of birth
                    else
                        ((ViewAccount)views[11]).setAccountLabelVisible(false,1); //don't show customer's current date of birth
                    return;
                }

                if(sourceDocument == views[11].fields[2].getDocument()) //address
                {
                    Controller.this.addressIntroduced = new String(views[11].fields[2].getText());

                    if(Controller.this.addressIntroduced==null)
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,2); //show customer's current address
                    else
                    if(Controller.this.addressIntroduced.isEmpty())
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,2); //show customer's current address
                    else
                        ((ViewAccount)views[11]).setAccountLabelVisible(false,2); //don't show customer's current address
                    return;
                }

                if(sourceDocument == views[11].fields[3].getDocument()) //phone
                {
                    Controller.this.phoneIntroduced = new String(views[11].fields[3].getText());

                    if(Controller.this.phoneIntroduced==null)
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,3); //show customer's current phone
                    else
                    if(Controller.this.phoneIntroduced.isEmpty())
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,3); //show customer's current phone
                    else
                        ((ViewAccount)views[11]).setAccountLabelVisible(false,3); //don't show customer's current phone
                    return;
                }

                if(sourceDocument == views[11].fields[4].getDocument()) //email
                {
                    Controller.this.emailIntroduced = new String(views[11].fields[4].getText());

                    if(Controller.this.emailIntroduced==null)
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,4); //show customer's current email
                    else
                    if(Controller.this.emailIntroduced.isEmpty())
                        ((ViewAccount)views[11]).setAccountLabelVisible(true,4); //show customer's current email
                    else
                        ((ViewAccount)views[11]).setAccountLabelVisible(false,4); //don't show customer's current email
                    return;
                }

                if(sourceDocument == views[11].fields[5].getDocument()) //password
                {
                    Controller.this.passwordIntroduced = new String(views[11].fields[5].getText());
                    return;
                }

                if(sourceDocument == views[11].fields[6].getDocument()) //new password
                {
                    Controller.this.newPasswordIntroduced = new String(views[11].fields[6].getText());
                    return;
                }
            }
        }
    }


}
