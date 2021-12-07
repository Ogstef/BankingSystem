import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToUser {
        /*
        reads from Users file and creates a list of type user.
         */
        public static List<User> readUsersFromFile(String filename){
            List<User> users = new ArrayList<User>();
            try {
                Scanner sc = new Scanner(new File(filename));
                String line = sc.nextLine();

                while (sc.hasNextLine())
                {
                    String[] info = line.split(",");

                    User user = createUser(info);

                    users.add(user);

                    line = sc.nextLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return users;
        }

        /*
        is called from readUsersFromFile, and creates one user that will be in passed into a list of users.
         */
        public static User createUser(String[] data)
        {
            String firstname = data[0];
            String lastname = data[1];
            int balance = 0 ;
            int id = 0;
            int electricBill = 0;
            int rent = 0;
            try {
                balance = Integer.parseInt(data[2]);
            }catch (NumberFormatException ex){
            }
            try {
                id = Integer.parseInt(data[3]);
            }catch (NumberFormatException ex){
            }
            String userName = data[4];
            String passWord = data[5];
            try {
                electricBill = Integer.parseInt(data[6]);
            } catch (NumberFormatException ex){
            }
            try {
                rent = Integer.parseInt(data[7]);
            } catch (NumberFormatException ex) {
            }

            return new User(firstname, lastname, balance, id, userName, passWord, electricBill, rent);
        }
    }





