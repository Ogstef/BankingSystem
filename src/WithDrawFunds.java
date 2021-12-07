import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class WithDrawFunds extends Login{
    JFrame frame = new JFrame("WITHDRAW");
    /*
    We create a frame which allows the user to input any amount he wants, but will only work if the user decides to withdraw
    up to 50% of his current balance.
     */

    WithDrawFunds() throws IOException, ClassNotFoundException {
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        FileInputStream fis = new FileInputStream(loggedONuserFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User loggedOn = (User) ois.readObject();


        JLabel label = new JLabel("(NOTE: YOU MAY ONLY WITHDRAW UP TO 50% OF YOUR CURRENT BALANCE");
        label.setBounds(10,10,500,35);
        panel.add(label);

        JLabel withdrawFund = new JLabel("WITHDRAW FUNDS :");
        withdrawFund.setBounds(0,50,200,35);
        panel.add(withdrawFund);

        JTextField amountToWithdraw  = new JTextField();
        amountToWithdraw.setBounds(120,50,200,35);
        panel.add(amountToWithdraw);

        JButton withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(100,90,100,35);
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amountToBeWithdrawn = Integer.parseInt(amountToWithdraw.getText());
                if ( amountToBeWithdrawn <= (loggedOn.getAccountBalance()*0.5));
                {
                    try {
                        withDrawFunds(loggedOn, amountToBeWithdrawn, usingFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(withdraw);
        frame.setVisible(true);
    }
    /*
    We write to a temp File called tempFundsWithdraw.csv the changed data, with the newly added funds to the logged on user,
    then we write to our original file which is Users.csv the contents of the temp File which contains the new data.
     */
    public void withDrawFunds( User b , int amount , String filepath) throws IOException {
        String tempFile = "tempFundsWithdraw.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String firstName=""; String lastName=""; int balance= 0; int id= 0; String userN=""; String pass=""; int electric = 0; int rent =0;
        try
        {
            FileWriter fw = new FileWriter(tempFile,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()){
                firstName = x.next();
                lastName = x.next();

                try{
                    balance = Integer.parseInt(x.next());
                }catch (NumberFormatException e){}

                try{
                    id = Integer.parseInt(x.next());
                }catch (NumberFormatException e){}

                userN = x.next();
                pass = x.next();

                try{
                    electric = Integer.parseInt(x.next());
                }catch (NumberFormatException e){}

                try{
                    rent = Integer.parseInt(x.next());
                }catch (NumberFormatException e){
                    for ( User a : readUsersFromFile( filepath)){
                        if ( a.getAccountId() == id){
                            rent = a.getRent();
                        }
                    }
                }

                if ( b.getAccountId() == id)
                {
                    pw.println(b.getFirstName()+","+b.getLastName()+","+(b.getAccountBalance()-amount)+","+b.getAccountId()+","+b.getUserName()+","+b.getPassWord()+","+b.getElectricBill()+","+b.getRent());
                }
                else{
                    pw.println(firstName+","+lastName+","+balance+","+id+","+userN+","+pass+","+electric+","+rent);
                }
            }
            x.close();
            bw.close();
            fw.close();
            pw.flush();
            pw.close();
            Scanner y = new Scanner(new File(tempFile));
            FileWriter fw1 = new FileWriter(filepath,false);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw1 = new PrintWriter(bw1);
            while ( y.hasNext())
            {
                pw1.println(y.next());
            }
            y.close();
            bw1.close();
            fw1.close();
            pw1.flush();
            pw1.close();
            JOptionPane.showMessageDialog(null,"Successfully withdrew funds");
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
