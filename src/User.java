import java.io.Serializable;
/*
Our class object implements serializable in order to track which user is online when we login.
 */
class User implements Serializable {
        private String firstName;
        private String lastName;
        private int accountBalance;
        private int accountId;
        private String userName;
        private String passWord;
        private int electricBill;
        private int rent;
        //constructor
        public User(String firstName, String lastName, int accountBalance, int accountId, String userName, String passWord, int electricBill, int rent){
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountBalance = accountBalance;
            this.accountId = accountId;
            this.userName = userName;
            this.passWord = passWord;
            this.electricBill = electricBill;
            this.rent = rent;
        }
        //getters
        public String getLastName()
        {
            return lastName;
        }
        public String getFirstName()
        {
            return firstName;
        }
        public int getAccountId() {
            return accountId;
        }
        public int getAccountBalance() {
            return accountBalance;
        }
        public String getUserName() {return userName;}
        public String getPassWord() {return passWord;}
        public int getElectricBill() {return electricBill;}
        public int getRent() {return rent;}

        //setters
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public void setAccountBalance(int accountBalance) {
            this.accountBalance = accountBalance;
        }
        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public void setPassWord(String passWord) {this.passWord = passWord;}
        public void setElectricBill(int electricBill) {this.electricBill = electricBill;}
        public void setRent(int rent) {this.rent = rent;}


    @Override
        public String toString()
        {
            return ("Firstname: " + firstName + " Lastname : " + lastName + " Account Balance: " + accountBalance + " Account Id: " + accountId + " Username: " + userName + " Password: " + passWord +" electric bill : " + electricBill + " rent : " + rent);
        }
    }
