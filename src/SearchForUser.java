import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForUser extends Login{
    JFrame frame = new JFrame("SEARCH");

    /*
    Creates a frame asking for a firstname or lastname for the logged on user to search for,
    and if system finds that name it displays a found message, otherwise a user not found message.
     */

    SearchForUser()
    {
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel search = new JLabel("Search for User based on First Name ");
        search.setBounds(10,10,300,25);
        panel.add(search);

        JTextField firstName = new JTextField();
        firstName.setBounds(10,40,100,25);
        panel.add(firstName);

        JButton searchFname = new JButton("SEARCH");
        searchFname.setBounds(150,40,100,25);
        searchFname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findFirstName(usingFile, firstName.getText());
            }
        });
        panel.add(searchFname);

        JLabel searchL = new JLabel("Search for User based on Last Name ");
        searchL.setBounds(10,200,300,25);
        panel.add(searchL);

        JTextField lastName = new JTextField();
        lastName.setBounds(10,250,100,25);
        panel.add(lastName);

        JButton searchLname = new JButton("SEARCH");
        searchLname.setBounds(150,250,100,25);
        searchLname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findLastName( usingFile, lastName.getText());
            }
        });
        panel.add(searchLname);
        frame.setVisible(true);
    }
    /*
    Searches for firstname in users.csv, we create a boolean variable for us to know if we found it or not
    and depending on that variable we display our JOptionPane message.
     */
    public void findFirstName(String filepath, String name)
    {
        boolean found = false;
        for ( User b: readUsersFromFile(filepath))
        {
            if (b.getFirstName().equals(name))
            {
                found = true;
                JOptionPane.showMessageDialog(null,"User has been found and has an account.");
            }
        }
        if (!found)
        {
            JOptionPane.showMessageDialog(null,"User doesn't exist.");
        }
    }
    /*
   Searches for lastname in users.csv, we create a boolean variable for us to know if we found it or not
   and depending on that variable we display our JOptionPane message.
    */
    public void findLastName(String filepath, String name)
    {
        boolean found = false;
        for ( User b : readUsersFromFile(filepath))
        {
            if (b.getLastName().equals(name))
            {
                found = true;
                JOptionPane.showMessageDialog(null,"User has been found and has an account");
            }
        }
        if (!found)
        {
            JOptionPane.showMessageDialog(null,"User doesn't exist");
        }
    }
}
