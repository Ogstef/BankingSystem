import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static java.awt.Color.*;
/*
Our banking menu , which will display once login is successful and provides the user with options to choose from.
we extend login to keep track of our logged on user, as well as some methods from FileToUser.
 */

public class BankingMenu  extends Login {

    JFrame frame = new JFrame("INTERNATIONAL BANK OF RANDOM PEOPLE");

    BankingMenu() throws IOException, ClassNotFoundException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(7,1));
        JMenuBar bankMenu = new JMenuBar();
        frame.setJMenuBar(bankMenu);
        /*
        extractToCSV, extracts all the information that exists about the currently logged on user, to a csv File in a specified path.
        We use the method extractInfoToCSV to complete this task.
         */
        JMenuItem extractToCsv = new JMenuItem("Extract my information to csv");
        bankMenu.add(extractToCsv);
        extractToCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fis = new FileInputStream(loggedONuserFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    User loggedOn = (User) ois.readObject();
                    extractInfoToCsv(loggedOn);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        /*
        exit option simply exits the program.
         */
        JMenuItem exit = new JMenuItem("Exit");
        bankMenu.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        /*
        We call our CheckBalance class for it, which opens a frame displaying user's current balance.
         */
        JButton checkBalanceButton = new JButton("CHECK BALANCE ");
        checkBalanceButton.setBackground(WHITE);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CheckBalance thisUsersBalance = new CheckBalance();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        frame.add(checkBalanceButton);
        /*
        We call our MakePayments class, to open a frame with options to make payments on the user's rent or electricity bill
         */
        JButton makePayments = new JButton("PAYMENTS ");
        makePayments.setBackground(orange);
        makePayments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MakePayments thisUsersBills = new MakePayments();

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(makePayments);
        /*
        We call our Statistics class, which will open a frame displaying statistics based on the current user's balance in comparison to the rest,
        along with the users bills in comparison with the rest.
         */
        JButton statistics = new JButton("STATISTICS");
        statistics.setBackground(cyan);
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statistics statistics1 = new Statistics();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(statistics);
        /*
        We call our SearchForUser class, to open a frame asking for firstname or lastname of a user, that the current user
        wants to search for.
         */
        JButton search = new JButton("SEARCH FOR OTHER USERS");
        search.setBackground(MAGENTA);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchForUser searchForUser = new SearchForUser();
            }
        });
        frame.add(search);
        /*
        We call our AddFunds class, to add funds up to 20% of user's current balance
         */
        JButton addFunds = new JButton("ADD FUNDS");
        addFunds.setBackground(green);
        addFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AddFunds addFunds1 = new AddFunds();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(addFunds);
        /*
        We call our WithdrawFunds class, to allow the user to withdraw funds of up to 50% of user's current balance.
         */
        JButton withdrawFunds = new JButton("WITHDRAW FUNDS");
        withdrawFunds.setBackground(GRAY);
        withdrawFunds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WithDrawFunds funds = new WithDrawFunds();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(withdrawFunds);

        /*
        We open a word File that contains Terms of service along with privacy policy for the app.
         */
        JButton tosPrivacy = new JButton("TERMS OF SERVICE \n PRIVACY POLICY");
        tosPrivacy.setBackground(RED);
        tosPrivacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //This works only for the specific directory, for it to work it needs to be adjusted to the right directory for someone else to use it.
                    Desktop.getDesktop().open(new File("C:\\Users\\sdavg\\OneDrive\\Desktop\\OOP_project\\TERMS OF SERVICEandPP.docx"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(tosPrivacy);





        frame.setVisible(true);
    }


    /*
    When user presses extract info to csv option in jmenu, we create a new file in the destination specified containing all their info.
     */
    public void extractInfoToCsv(User b) throws IOException {
        String prefix = "currentUser"+ b.getAccountId()+ "-";

        String suffix = ".csv";

        // filepath must be adjusted to right directory for someone else to use it.
        File directoryPath = new File ("C:\\Users\\sdavg\\OneDrive\\Desktop\\OOP_project\\");

        File tempFile = File.createTempFile(prefix,suffix,directoryPath);

        FileWriter fw = new FileWriter(tempFile,true);

        fw.write(b.getFirstName()+","+b.getLastName()+","+b.getAccountBalance()+","+b.getAccountId()+","+b.getUserName()+","+b.getPassWord()+","+b.getElectricBill()+","+b.getRent());
        fw.close();

        JOptionPane.showMessageDialog(null,"CSV CREATED");
    }

}


