package BankProject;

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.text.*;

public class BankController {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<User> table=new ArrayList<>();
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj=new Date();//System date-time
        try{
            //dummy data for testing
            table.add(new User(14523698,  "shiv", 1289, 500000000));
            table.add(new User(16712129,  "Yuvraj Singh", 3449, 758000000));
            table.add(new User(99463834,  "Kuldeep Singh", 1389, 840000000));
            table.add(new User(18723698,  "Rohit Sharma", 1788, 83224000));
            table.add(new User(14521235,  "Jasprit Bumrah", 3388, 3900000));
            table.add(new User(15445311,  "Suryakumar Sharma", 9889, 133430000));
            table.add(new User(14523232,  "Hardik Pandya", 1156, 0));
            table.add(new User(12122213,  "Rishabh Pant", 1678, 501212000));
            table.add(new User(75091245,  "Ravindra Jadeja", 1009, 589900000));
            table.add(new User(75818205,  "Yashasvi Jaiswal", 1811, 50780));
            
            System.out.println("\n      ********************");
            System.out.println(  "  WELCOME TO STATE BANK OF INDIA\n");
            while(true)
            {
                System.out.println("   __________________________");
                System.out.println("For New User>>  Press 1 Open New Account");
                System.out.println("Existing User>> Press 2 go to Login Page\n");
                System.out.println("   ___________________________");
                int c=sc.nextInt();

                int acc,found=0,pin;
                double amt;
                User active=null;
                c=sc.nextInt();
                switch(c)
                {
                    case 1:System.out.println(">>SIGN UP<<");
                           System.out.println("(Note: Don't use Space in Name)");
                           System.out.print("Enter First Name");
                           String new_name_f=sc.next();
                           System.out.print("Enter Last Name:");
                           String new_name_l=sc.next();
                    
                        int random_acc=(int)(Math.random()*(99999999-1000000+1)+100);
                        int random_pin=(int)(Math.random()*(9999-1000+1)+1000);
                        table.add(new User(random_acc,(new_name_f+" "+new_name_l),random_pin,0));

                        System.out.println("\n>>New Account Created Successfully<<");
                        System.out.println("New Acc. no="+random_pin);
                        System.out.println("New Pin="+random_pin);
                        System.out.println("Login to access your new Account & Change your Pin");
                        break;

                    case 2:System.out.println(">>LOGIN<<");    
                           System.out.println("Enter Account Number:");
                           acc=sc.nextInt();
                           //Iterator through the database to find the user
                           Iterator<User> it=table.iterator();
                           while (it.hasNext()) {
                            User ur=(User)it.next();
                            if(ur.getAccount_id()==acc)
                            {
                                found=1;
                                System.out.print("Enter Pin:");
                                pin=sc.nextInt();
                                if(ur.getPin()==pin)
                                {
                                    active=ur;
                                    System.out.println("\n<Successfully Logged in at"+df.format(dateobj)+">\n");
                                    System.out.println("\n ** Welcome" +active.getName()+"**");
                                    found=2;
                                    break;
                                }
                            }
                           }
                           if(found==0)
                           {
                            System.out.println("\n!! INVALID ACCOUNT NUMBER!!");
                            System.out.println("!! PLEASE TRY AGAIN LATER!!");
                            System.out.println("\n<Session ended at "+df.format(dateobj)+">\n");
                            System.exit(0);
                           }
                           if (found==1)
                           {
                            System.out.println("\n!! INVALID PIN!!");
                            System.out.println("!!PLEASE TRY AGAIN LATER!!");
                            System.out.println("\n<Session ended at "+df.format(dateobj)+">\n");
                            System.exit(0);
                           }
                           while(true)
                           {
                            System.out.println("\n______________________");
                            System.out.println("SELECT OPERATION:");
                            System.out.println("1-Update Pin");
                            System.out.println("2-Balance Enquiry");
                            System.out.println("3-Withdraw Money");
                            System.out.println("4-Deposite Money");
                            System.out.println("5-Logout");
                            System.out.println("\n_______________________");
                            int ch=sc.nextInt();
                            int op;
                            switch(ch)
                            {
                                case 1:System.out.println("Enter Old Pin");
                                       op=sc.nextInt();
                                      if(op==active.getPin())
                                       {
                                        System.out.print("Enter New Pin:");
                                        active.setPin(sc.nextInt());
                                        System.out.println(">> PIN UPDATED SUCCESSFULLY <<");
                                       }
                                       else{
                                        System.out.println("\n     !!INVALID PIN!! ");
                                       }
                                       break;

                                case 2:System.out.println("AVAILABLE BALANCE = Rs."+active.getBalance());
                                       break; 
                                case 3:System.out.println("\nEnter Amount to be Withdraw:Rs.");  
                                       amt=sc.nextDouble();
                                       if(amt>active.getBalance())
                                       System.out.println("\n  !! INSUFFICIENT BALANCE !!");
                                       else
                                       {
                                        active.setBalance(active.getBalance()-amt);
                                        System.out.println(">> WITHDRAWL SUCCESSFULL <<");
                                        System.out.println("AVAILABLE BALANCE = Rs. "+active.getBalance());
                                       }
                                       break;
                                case 4:  System.out.println("\n Enter Amount to be Deposited: Rs");     
                                         amt=sc.nextDouble();
                                         if (amt>1000000)
                                         {
                                            System.out.println("\n Your amout exceed limit.");
                                            System.out.println("Maximum depositing limit is = Rs.1000000");
                                         }
                                         else{
                                           active.setBalance(active.getBalance()+amt);
                                           System.out.println(">> DEPOSIT  SUCCESSFULL <<");
                                           System.out.println("AVAILABLE BALANCE=Rs."+active.getBalance());
                                         }
                                         break;  
                                case 5:  System.out.println("\n Thanks for using SBI, Visit Again  :):) ");  
                                         System.out.println("\n<Session ended at "+df.format(dateobj)+">\n"); 
                                         System.exit(0);   
                                default:System.out.println("\n!! WRONG CHOICE!!\nPress between 1 to 5");                   
                            }
                           }
                 default:System.out.println("\n!! WRONG CHOICE!!\n PRESS BETWEEN 1 TO 2");          
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("\n     !!WARNING!!\n Please use correct input format");
            System.out.println("\n<Session Expired at "+df.format(dateobj)+">\n");
        }
    }
}
