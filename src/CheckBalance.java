import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CheckBalance extends Login{

    /*
    Simply creates a frame which displays the current balance of the logged on User. 
     */
    JFrame frame = new JFrame("BALANCE");

    CheckBalance() throws IOException, ClassNotFoundException {

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel balance = new JLabel();


        FileInputStream fis = new FileInputStream(loggedONuserFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User loggedOn = (User) ois.readObject();

        for ( User b : readUsersFromFile(usingFile))
        {
            if ( loggedOn.getFirstName().equals(b.getFirstName()) && loggedOn.getLastName().equals(b.getLastName()))
            {
                int userBalance = b.getAccountBalance();
                balance.setText(String.valueOf(userBalance));
                break;

            }
        }

        frame.add(balance);
        frame.setVisible(true);

    }


}
