import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Statistics extends Login{

    JFrame frame = new JFrame("STATISTICS");
    /*
    Creating a frame which will display two statistics of the user, based on his balance and his bills,
    in comparison with the rest of the users of the bank.
     */

    Statistics() throws IOException, ClassNotFoundException {
        frame.setSize(350,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FileInputStream fis = new FileInputStream(loggedONuserFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User loggedOn = (User) ois.readObject();

        JLabel percentile = new JLabel("Percentile based on account balance is : " + getPercentile(loggedOn));
        percentile.setBounds(10,20,300,25);

        JLabel bills = new JLabel("Percentile based on bills owed : " + getPercentileOfBills(loggedOn));
        bills.setBounds(20,60,100,25);

        frame.add(percentile);
        frame.add(bills);
        frame.setVisible(true);


    }

    /*
    To get the percentile based on bills, first we add the logged on users bills, then we iterate through the list of users,
    checking if our combined bills are higher than the rest of the users and keeping track of how many are lower.
    Then, once we have our numbers, to find the percentile, we simply divide the amount of users with bills lower than the logged on
    by the total amount of users -1 and multiply by 100.
     */
    public double getPercentileOfBills( User c){
        double underBills = 0;
        double totalUser = 1;
        int thisUserBills = c.getRent() +c.getElectricBill();
        for ( User a : readUsersFromFile(usingFile))
        {
            totalUser++;
            int totalBills = a.getElectricBill() + a.getRent();
            if ( totalBills <= thisUserBills)
            {
                underBills++;
            }
        }
        return (underBills/(totalUser-1))*100;
    }

    /*
    Similar logic with above, this time checking balances and not needing to add both bills to one integer variable.
     */
    public double getPercentile ( User b ){
        double underBalance = 0;
        double totalUsers = 1;
        for (User a : readUsersFromFile(usingFile))
        {
            totalUsers++;
            if (a.getAccountBalance() <= b.getAccountBalance()){
                underBalance++;
            }
        }
        return (underBalance/(totalUsers -1 ))*100;
    }
}
