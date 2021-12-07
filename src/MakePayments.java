import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MakePayments extends Login{
    JFrame frame = new JFrame("PAYMENTS");
    /*
    Shows electricity bill and rent bill. Provides a textField for the user to input an amount to be payed for each bill
    and then removes the amount from the bill specified and from the user's balance.
    2 extra methods, updateRentBill and updateElectric, each for their respective field.
     */

    MakePayments() throws IOException, ClassNotFoundException {
        frame.setSize(420,420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FileInputStream fis = new FileInputStream(loggedONuserFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User loggedOn = (User) ois.readObject();

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel rentBill = new JLabel("Rent Bill: " + loggedOn.getRent());
        rentBill.setBounds(250, 20, 100, 25);
        panel.add(rentBill);

        JLabel payRent = new JLabel("Pay Rent: ");
        payRent.setBounds(240,60,100,25);
        panel.add(payRent);

        JTextField payForRent = new JTextField("");
        payForRent.setBounds(300,60,100,25);
        panel.add(payForRent);

        JButton payTheRent = new JButton("Pay");
        payTheRent.setBounds(250,100,100,25);
        payTheRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(payForRent.getText());
                if (amount <= loggedOn.getRent())
                {
                    updateRentBill(loggedOn, usingFile, amount);
                    rentBill.setText("Rent Bill: " + (loggedOn.getRent() - amount));
                }
            }
        });
        panel.add(payTheRent);

        JLabel electricBill = new JLabel("Electric Bill: " + loggedOn.getElectricBill());
        electricBill.setBounds(25, 20, 100, 25);
        panel.add(electricBill);

        JLabel payElectricBill = new JLabel("Pay Electric: ");
        payElectricBill.setBounds(10, 60 , 100 , 25);
        panel.add(payElectricBill);


        JTextField amountForElectric = new JTextField("");
        amountForElectric.setBounds(90,60 , 100 , 25);
        panel.add(amountForElectric);

        JButton payForElectric = new JButton("Pay");
        payForElectric.setBounds(40 , 100 ,100 , 25);
        payForElectric.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amountWanted = Integer.parseInt(amountForElectric.getText());
                if (amountWanted <= loggedOn.getElectricBill()) {
                    updateElectric(loggedOn, usingFile, amountWanted);
                    electricBill.setText("Electric Bill: " + (loggedOn.getElectricBill() - amountWanted));
                }
            }
        });
        panel.add(payForElectric);
        frame.setVisible(true);
    }

    /*
    Writing to a tempFile the fixed data, which is removing the amount payed from the bill and the balance,
    and then writing the contents of tempFile to our original file.
     */
   public void updateRentBill( User b , String filepath , int amount){
        String tempFile = "tempRent.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String fName = ""; String lName=""; int balance = 0; int id = 0; String userName=""; String password =""; int electricBill = 0; int rent = 0;
        try {
            FileWriter fw = new FileWriter(tempFile,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner sc = new Scanner(new File(filepath));
            sc.useDelimiter("[,\n]"); // to read csv
            while (sc.hasNext()){
                fName = sc.next();
                lName = sc.next();

                try {
                    balance = Integer.parseInt(sc.next());
                }catch (NumberFormatException e){}

                try{
                    id = Integer.parseInt(sc.next());
                }catch (NumberFormatException x){}

                userName = sc.next();
                password = sc.next();

                try {
                    electricBill = Integer.parseInt(sc.next());
                }catch (NumberFormatException w){}

                try{
                    rent = Integer.parseInt(sc.next());
                }catch (NumberFormatException r){
                    //due to format of csv file, we need to assign rent each time correctly
                    for (User a :readUsersFromFile(filepath)){
                        if ( a.getAccountId() == id){
                            rent = a.getRent();
                        }
                    }
                }

                if ( b.getAccountId() == id){
                    pw.println(b.getFirstName()+","+b.getLastName()+","+(b.getAccountBalance()-amount)+","+b.getAccountId()+","+b.getUserName()+","+b.getPassWord()+","+b.getElectricBill()+","+(b.getRent()-amount));
                }
                else {
                    pw.println(fName+","+lName+","+balance+","+id+","+userName+","+password+","+electricBill+","+rent);
                }
            }
            sc.close();
            pw.flush();
            pw.close();
            Scanner sc1 = new Scanner(new File(tempFile));
            FileWriter fw1 = new FileWriter(filepath,false);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw1 = new PrintWriter(bw1);
            //printing to our main file
            while (sc1.hasNext()){
                pw1.println(sc1.next());
            }
            sc1.close();
            pw1.flush();
            pw1.close();
            JOptionPane.showMessageDialog(null,"Successfully payed "+ amount+" for rent");

        } catch (IOException e) {
            e.printStackTrace();
        }
   }
   /*
   Writing to a tempFile the fixed data, which is removing the amount payed from the bill and the balance,
   and then writing the contents of tempFile to our original file.
    */
   public void updateElectric( User c , String filepath , int amount){
       String tempFile = "tempElectric.csv";
       File oldFile = new File(filepath);
       File newFile = new File(tempFile);
       String firstName=""; String lastName=""; int balance= 0; int id= 0; String userN=""; String pass=""; int electric = 0; int rent =0;

       try {
           FileWriter fw = new FileWriter(tempFile,false);
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter pw = new PrintWriter(bw);
           Scanner x = new Scanner(new File(filepath));
           x.useDelimiter("[,\n]"); // to read csv
           BufferedReader reader = new BufferedReader(new FileReader(filepath));
               while (x.hasNext()) {
                   firstName = x.next();
                   lastName = x.next();
                   try {
                       balance = Integer.parseInt(x.next());
                   } catch (NumberFormatException e) {
                   }
                   try {
                       id = Integer.parseInt(x.next());
                   } catch (NumberFormatException r) {
                   }
                   userN = x.next();
                   pass = x.next();
                   try {
                       electric = Integer.parseInt(x.next());
                   } catch (NumberFormatException ex) {
                   }
                   try {
                       rent = Integer.parseInt(x.next());
                   } catch (NumberFormatException rx) {
                       //due to format of csv file, we need to assign rent each time correctly
                       for ( User a : readUsersFromFile(filepath)){
                           if ( a.getAccountId() == id){
                               rent = a.getRent();
                           }
                       }
                   }
                   //printing to our temp file the values, which we will then transfer to our main file
                   if (c.getAccountId() == id) {
                       pw.println(c.getFirstName() + "," + c.getLastName() + "," + (c.getAccountBalance() - amount) + "," + c.getAccountId() + "," + c.getUserName() + "," + c.getPassWord() + "," + (c.getElectricBill() - amount) + "," + c.getRent());
                   } else {
                       pw.println(firstName + "," + lastName + "," + balance + "," + id + "," + userN + "," + pass + "," + electric + "," + rent);
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
           //printing to our main file
           while (y.hasNext()){
               pw1.println(y.next());
           }
           y.close();
           bw1.close();
           fw1.close();
           pw1.flush();
           pw1.close();
           JOptionPane.showMessageDialog(null,"Successfully payed " + amount + " for electricity");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}

