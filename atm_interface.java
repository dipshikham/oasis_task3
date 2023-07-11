import java.util.Scanner;
class BankAccount
{
    String name;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";
    public void register()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Name: ");
        this.name = sc.nextLine();
        System.out.print("Enter Your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.print("Enter Your Password (password should be atleast of length 6) : ");
        this.password = sc.nextLine();
        while(password.length()< 6)
        {
            System.out.println("Enter a strong password");
            this.password=sc.nextLine();
        }
        System.out.println("Registration completed. Kindly login!");
    }

    public boolean login()
    {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while ( !isLogin )
        {
            System.out.print("Enter Your Account No: ");
            String AccountNo = sc.nextLine();
            if ( AccountNo.equals(accountNo) )
            {
                while ( !isLogin )
                {
                    System.out.print("Enter Your Password: ");
                    String Password = sc.nextLine();
                    if ( Password.equals(password) )
                    {
                        System.out.print("Login successful!");
                        isLogin = true;
                    }
                    else
                    {
                        System.out.println("Incorrect Password!");
                    }
                }
            }
            else
            {
                System.out.println("Account No. not found.");
            }
        }
        return true;
    }

    public void withdraw()
    {
        System.out.print("Enter amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try
        {
            if ( balance >= amount )
            {
                transactions++;
                balance -= amount;
                System.out.println("Withdrawn successfully.");
                String str = amount + " Rs Withdrawn.\n";
                transactionHistory = transactionHistory.concat(str);

            }
            else
            {
                System.out.println("Insufficient Balance.");
            }

        }
        catch ( Exception ignored)
        {
        }
    }

    public void deposit()
    {
        System.out.print("Enter amount to deposit (Maximum limit is 100000) : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try
        {
            if ( amount <= 100000f )
            {
                transactions++;
                balance += amount;
                System.out.println("Deposited successfully.");
                String str = amount + " Rs deposited.\n";
                transactionHistory = transactionHistory.concat(str);
            }
            else
            {
                System.out.println("Sorry! Maximum limit exceeded.");
            }

        }
        catch ( Exception ignored) {
        }
    }

    public void transfer() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.println("Enter Account No of " + recipient);
        String recipientAccNo = sc.nextLine();
        System.out.print("Enter amount to transfer (Maximum limit is 50000) : ");
        float amount = sc.nextFloat();
        try
        {
            if ( balance >= amount )
            {
                if ( amount <= 50000f )
                {
                    transactions++;
                    balance -= amount;
                    System.out.println("Successfully transferred to " + recipient+ " Account No "+recipientAccNo);
                    String str = amount + " Rs transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                }
                else
                {
                    System.out.println("Sorry! Maximum limit exceeded.");
                }
            }
            else
            {
                System.out.println("Insufficient Balance.");
            }
        }
        catch ( Exception ignored) {
        }
    }

    public void checkBalance()
    {
        System.out.println("Balance is: " + balance + " Rs.");
    }

    public void transHistory()
    {
        if ( transactions == 0 )
        {
            System.out.println("No transaction history.");
        }
        else
        {
            System.out.println("Transaction history:\n" + transactionHistory);
        }
    }
}


public class atm_interface
{
    public static int takeIntegerInput(int limit)
    {
        int input = 0;
        boolean flag = false;
        while ( !flag )
        {
            try
            {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (input > limit || input < 1)
                {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            }
            catch ( Exception e )
            {
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }
    public static void main(String[] args)
    {
        System.out.println("WELCOME TO ATM INTERFACE");
        System.out.println("1.Register \n2.Exit");
        System.out.print("Enter Your Choice: ");
        int choice = takeIntegerInput(2);

        if ( choice == 1 )
        {
            BankAccount b = new BankAccount();
            b.register();
            while(true)
            {
                System.out.println("1.Login \n2.Exit");
                System.out.print("Enter Your Choice: ");
                int ch = takeIntegerInput(2);
                if ( ch == 1 )
                {
                    if (b.login())
                    {
                        System.out.println("\nWELCOME BACK " + b.name);
                        boolean isFinished = false;
                        while (!isFinished)
                        {
                            System.out.println("1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit\n");
                            System.out.print("Enter Your Choice: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1 -> b.withdraw();
                                case 2 -> b.deposit();
                                case 3 -> b.transfer();
                                case 4 -> b.checkBalance();
                                case 5 -> b.transHistory();
                                case 6 -> isFinished = true;
                            }
                        }
                    }
                }
                else
                {
                    System.exit(0);
                }
            }
        }
        else
        {
            System.exit(0);
        }
    }
}