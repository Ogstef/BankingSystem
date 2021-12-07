import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Login extends FileToUser{

    /*
    where our program begins; Our login section which requires username and password to enter our app.
    We extend FileToUser in order to use its methods.
     */
    // making our user files static final variables to be used throughout our program.
    //These should be changed in order for the program to work on another computer, specifying the right directory.
    static final String usingFile = "C:\\Users\\sdavg\\OneDrive\\Desktop\\OOP_project\\Users.csv";
    static final String loggedONuserFile = "D:\\Deree\\OOP\\LoggedOn.txt";

    public static void main(String[] args)
    {


        JFrame frame = new JFrame();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userTextField = new JTextField(20);
        userTextField.setBounds(100,20, 165, 25);
        panel.add(userTextField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JLabel correct = new JLabel("");
        correct.setBounds(10, 110, 300, 25);
        panel.add(correct);

        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBounds(10, 80, 80, 25);

        // creating our logged on user with serialization, for our future code to use.
        File loggedONuser = new File ( loggedONuserFile);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userTextField.getText();
                String password = passwordText.getText();
               for ( User b : readUsersFromFile(usingFile))
                {
                    if ( b.getUserName().equals(user) && b.getPassWord().equals(password))
                    {
                        correct.setForeground(Color.CYAN);
                        correct.setText("Login Successful.");
                        try {
                            BankingMenu bankingMenu = new BankingMenu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        /*
                        Using serialization, we create a file that stores the currently online user, to use from our future classes.
                         */
                        try {
                            FileOutputStream fos = new FileOutputStream(loggedONuser);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(b);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        // we dispose of our login frame, once login is successful.
                        frame.dispose();
                        break;
                    }
                    else
                    {
                        correct.setForeground(Color.RED);
                        correct.setText("Wrong username or password. Please try again");
                    }
                }
            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.setBounds(110, 80, 80, 25);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userTextField.setText("");
                passwordText.setText("");
            }
        });
        panel.add(loginButton);
        panel.add(resetButton);
        frame.setVisible(true);
    }
}
