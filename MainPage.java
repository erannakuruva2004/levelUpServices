import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class UserDetails{
    private String Name = "default";
    private String EmailId = "xyz@gmail.com";
    private long Phno = 9998886660L;
    private String Password = "abcd@123";
    UserDetails(){}
    UserDetails(String name, String emailid, long phno, String password){
        this.Name = name;
        this.EmailId = emailid;
        this.Phno = phno;
        this.Password = password;
    }
     String getName(){
        return Name;
    }
     String getPassword(){
        return Password;
    }
    long getNumber(){
        return Phno;
    }
}

class SignUp extends UserDetails{
    static Scanner sc = new Scanner(System.in);
    SignUp(String name,String email, long phno, String password){
        super(name,email,phno,password);
    }
    public static void main(String[] args){
       ;
        System.out.print("\u001B[34mEnter your UserName: \u001B[0m");
        String name = sc.nextLine();
        
        String email;
        while(true){
            System.out.print("\u001B[34mEnter your Email id: \u001B[0m");
            email = sc.nextLine();
           
            if(email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")){
                break;
            } else {
                System.out.println("\u001B[31mInvalid Email Format. Please enter a valid email.\u001B[0m");
            }
        }
        
        String password;
        while(true){
            System.out.print("\u001B[34mEnter your Password: \u001B[0m");
            password = sc.nextLine();
            if(password.length() >= 6 && password.matches(".*[^a-zA-Z0-9].*")){
                break;
            } else {
                System.out.println("\u001B[31mInvalid Password.\u001B[0m");
                System.out.println("\u001B[31mPassword must have at least 6 characters and at least one special character.\u001B[0m");
            }
        }
        
        long phno;
        while (true) {
            System.out.print("\u001B[34mEnter Your Phone Number: \u001B[0m");
            try {
                phno = Long.parseLong(sc.nextLine());
                if (checkPhonProcess(phno)) {
                    break;
                } else {
                    System.out.print("\u001B[33mEnter 1 to continue else 0 to exit\n\u001B[0m");
                    int n = Integer.parseInt(sc.nextLine());
                    if (n != 1) {
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                        return;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.print("\u001B[31mInvalid input! Please enter numeric digits only.\u001B[0m\n");
            }
        }

        
            if(OTPProcess()){
                
                System.out.println("Verifing OTP...");
                
                 char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }    
                System.out.print("\r\u001B[32mSuccessfully Signed Up, Thank you!\n\u001B[0m");
                System.out.print("\u001B[36mRedirecting to Login Page\n\u001B[0m");
        System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }
                SignUp user = new SignUp(name,email,phno,password);
                LoginPage.login(user);
            }
            else
            System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
        
    }
    static boolean checkPhonProcess(long phno){
         int error = checkPhono(phno);
         if(error > 0){
             System.out.print("\u001B[31mInvalid Phone Number\n\u001B[0m");
             if(error == 1)
             System.out.print("\u001B[31mNumber must contain 10 digits\n\u001B[0m");
             else if(error == 2)
             System.out.print("\u001B[31mFirst digit of Phone Number must be 6 or greater than 6\n\u001B[0m");
             else if (error == 3){
                 System.out.print("\u001B[31mNumber must contain 10 digits\n\u001B[0m");
                 System.out.print("\u001B[31mAnd first digit of Phone Number must be 6 or greater than 6\n\u001B[0m");
             }
             return false;
         }
         return true;
    }
    static boolean OTPProcess(){
        System.out.print("\u001B[36mPlease Enter the OTP sent to your registered Phone Number\n\u001B[0m");
        int genOTP = OTPgen();
        System.out.print("\u001B[34mEnter OTP: \u001B[0m");
        int enteredOTP = Integer.parseInt(sc.nextLine());
        if(genOTP == enteredOTP){
            return true;
        }
        else{
            System.out.print("\u001B[31mIncorrect OTP\n\u001B[0m");
            System.out.print("\u001B[33mEnter 1 to re-send the OTP else press 0 to exit\n\u001B[0m");
            int n = Integer.parseInt(sc.nextLine());
            if(n==1)
            return OTPProcess();
            else
            return false;
        }
    }
    static int OTPgen(){
        int otp = 1000+(int)(Math.random()*8999);
        System.out.println("\u001B[35mHere is your 4 digit OTP: "+otp+"\u001B[0m");
        return otp;
    }
    static int checkPhono(long phno){
        String num = ""+phno;
        int digits= num.length();
        char fd = num.charAt(0);
        int firstDigit = (int)fd - 48;
        if(digits != 10 && firstDigit < 6)
        return 3;
        else if(digits != 10)
        return 1;
        else if(firstDigit < 6)
        return 2;
        else return 0;
    }
}

class LoginPage extends UserDetails{
    static Scanner sc = SignUp.sc;
    static void login(UserDetails user){
        System.out.print("\r\u001B[34mEnter Your User Name: \u001B[0m");
        String name = sc.nextLine();
        System.out.print("\u001B[34mEnter Your Password: \u001B[0m");
        String password = sc.nextLine();
        String Name = user.getName();
        String Password = user.getPassword();
        if( name.equals(Name) && password.equals(Password) ){
            System.out.print("\u001B[32mSuccessfully Logged in\nRedirecting to Home Page..\n\u001B[0m");
            char[] spinner = {'|', '/', '-', '\\'};
            System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }
        Home.home(user);
        }
        else if(!name.equals(Name) && !password.equals(Password) ){
            System.out.println("\u001B[31mInvalid Credentials\n\u001B[0m");
            System.out.print("\u001B[33mPress 1 to display Credentials, else 0 to exit\n\u001B[0m");
            int n = Integer.parseInt(sc.nextLine());
            if(n==1){
                System.out.print("\u001B[34mEnter your Phone Number: \u001B[0m");
                long phno = Long.parseLong(sc.nextLine());
                if (SignUp.checkPhonProcess(phno)){
                    if(phno == user.getNumber()){
                        if(SignUp.OTPProcess()){
                            System.out.print("\u001B[32mPhone Number Verified, Thank you!\u001B[0m");
                            System.out.print("\u001B[36mGetting Your Credentials..\n\u001B[0m");
                            String userName = user.getName();
                            String userPassword = user.getPassword();
                            System.out.println("\u001B[35mYour UserName :"+userName+"\u001B[0m");
                            System.out.println("\u001B[35mYour Password :"+userPassword+"\u001B[0m");
                            System.out.println("\u001B[36mRedirecting to LoginPage..\u001B[0m");
                            char[] spinner = {'|', '/', '-', '\\'};
                            System.out.print("... ");

                            for (int i = 0; i < 30; i++) {
                                try {
                                    System.out.print("\r... " + spinner[i % spinner.length]);
                                    Thread.sleep(150); 
                                } catch (InterruptedException e) {
                                    
                                }
                            }
                            login(user);
                        }
                        else
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                    }
                    else{
                        System.out.print("\u001B[31mIncorrect Phone Number\n\u001B[0m");
                        System.out.print("\u001B[33mEnter 1 to login again else 0 to exit\n\u001B[0m");
                        int m = Integer.parseInt(sc.nextLine());
                        if(m==1)
                        login(user);
                        else
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                    }
                }
                else{
                    System.out.print("\u001B[33mEnter 1 to login again else 0 to exit\n\u001B[0m");
                    int m = Integer.parseInt(sc.nextLine());
                    if(m==1)
                    login(user);
                    else
                    System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                }
            }
            else
            System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
        }
        else if(!name.equals(Name)){
            System.out.println("\u001B[31mInvalid User Name\n\u001B[0m");
            System.out.print("\u001B[33mPress 1 to display User Name, else 0 to exit\n\u001B[0m");
            int n = Integer.parseInt(sc.nextLine());
            if(n==1){
                System.out.print("\u001B[34mEnter your Phone Number: \u001B[0m");
                long phno = Long.parseLong(sc.nextLine());
                if (SignUp.checkPhonProcess(phno)){
                    if(phno == user.getNumber()){
                        if(SignUp.OTPProcess()){
                            System.out.print("\u001B[32mPhone Number Verified, Thank you!\u001B[0m");
                            System.out.print("\u001B[36mGetting Your User Name..\n\u001B[0m");
                            String userName = user.getName();
                            System.out.println("\u001B[35mYour UserName :"+userName+"\u001B[0m");
                            System.out.println("\u001B[36mRedirecting to LoginPage..\u001B[0m");
                            
                            char[] spinner = {'|', '/', '-', '\\'};
                            System.out.print("... ");
                    
                            for (int i = 0; i < 30; i++) {
                                try {
                                    System.out.print("\r... " + spinner[i % spinner.length]);
                                    Thread.sleep(150); 
                                } catch (InterruptedException e) {
                                    
                                }
                            }
                            login(user);
                        }
                        else
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                    }
                    else{
                        System.out.print("\u001B[31mIncorrect Phone Number\n\u001B[0m");
                        System.out.print("\u001B[33mEnter 1 to login again else 0 to exit\n\u001B[0m");
                        int m = Integer.parseInt(sc.nextLine());
                        if(m==1)
                        login(user);
                        else
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                    }
                }
                else{
                    System.out.print("\u001B[33mEnter 1 to login again else 0 to exit\n\u001B[0m");
                    int m = Integer.parseInt(sc.nextLine());
                    if(m==1)
                    login(user);
                    else
                    System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                }
            }
        }
        else {
            System.out.println("\u001B[31mInvalid Password\n\u001B[0m");
            System.out.print("\u001B[33mPress 1 to display Password, else 0 to exit\n\u001B[0m");
            int n = Integer.parseInt(sc.nextLine());
            if(n==1){
                System.out.print("\u001B[34mEnter your Phone Number: \u001B[0m");
                long phno = Long.parseLong(sc.nextLine());
                if (SignUp.checkPhonProcess(phno)){
                    if(phno == user.getNumber()){
                        if(SignUp.OTPProcess()){
                            System.out.print("\u001B[32mPhone Number Verified, Thank you!\u001B[0m");
                            System.out.print("\u001B[36mGetting Your Password..\n\u001B[0m");
                            String userPassword = user.getPassword();
                            System.out.println("\u001B[35mYour Password :"+userPassword+"\u001B[0m");
                            System.out.println("\u001B[36mRedirecting to LoginPage..\u001B[0m");
                             char[] spinner = {'|', '/', '-', '\\'};
                                 System.out.print("... ");
                    
                            for (int i = 0; i < 30; i++) {
                                try {
                                    System.out.print("\r... " + spinner[i % spinner.length]);
                                    Thread.sleep(150); 
                                } catch (InterruptedException e) {
                                    
                                }
                            }
                            login(user);
                        }
                        else
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                    }
                    else{
                        System.out.print("\u001B[31mIncorrect Phone Number\n\u001B[0m");
                        System.out.print("\u001B[33mEnter 1 to login again else 0 to exit\n\u001B[0m");
                        int m = Integer.parseInt(sc.nextLine());
                        if(m==1)
                        login(user);
                        else
                        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                    }
                }
                else{
                    System.out.print("\u001B[33mEnter 1 to login again else 0 to exit\n\u001B[0m");
                    int m = Integer.parseInt(sc.nextLine());
                    if(m==1)
                    login(user);
                    else
                    System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
                }
            }
        }
    }
}

public class MainPage{
    public static void main(String[] args){
    
        
        Banner.main(null);
        
        System.out.println("\n\n\t\t\t\t\u001B[38;5;200mAt LEVEL UP, we don’t just provide services \u001B[38;5;117m — we elevate possibilities.\n");
        System.out.println("\u001B[0mPlease Enter");
        System.out.println("\u001B[35m1. Log In (Existing User)\u001B[0m");
        System.out.println("\u001B[34m2. Sign up (New User)\u001B[0m");
        System.out.println("\u001B[31m3. Exit\u001B[0m");
        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[33mEnter Your Choice: \u001B[0m");
        int n = Integer.parseInt(sc.nextLine());
        if(n==1){
            UserDetails user = new UserDetails();
            LoginPage.login(user);
        }
        else if(n==2){
            SignUp.main(null);
        }
        else
        System.out.print("\u001B[35m\t\t\t\tThank you! HAVE A NICE DAY\n\u001B[0m");
    }
}

class Plot {
    int id, size, areaCode, featureCode;
    double price;

    Plot(int id, int areaCode, int size, double price, int featureCode) {
        this.id = id;
        this.areaCode = areaCode;
        this.size = size;
        this.price = price;
        this.featureCode = featureCode;
    }

    void display() {
        System.out.println("\u001B[33m-----------------------------");
        System.out.println("Plot ID: " + id);
        System.out.println("Area Code: " + areaCode);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price: ₹" + price);
        System.out.println("-----------------------------\u001B[0m");
    }

    void displayFullDetails(String propertyType) {
        display();
        System.out.println("\u001B[36mFeature Code: " + featureCode);
        System.out.println("Features: " + getFeatures(propertyType));
        System.out.println("\"Every home tells a story — maybe this one is yours.\"\u001B[0m");
    }

    String getFeatures(String type) {
        String features = "Unknown Features";

        switch (type) {
            case "Plot":
                switch (featureCode) {
                    case 1: features = "East Facing, Clear Title"; break;
                    case 2: features = "Corner Plot, Road Access"; break;
                    case 3: features = "Park Facing, Vastu Compliant"; break;
                    case 4: features = "DTCP Approved, Drainage Available"; break;
                    default: features = "Basic Amenities"; break;
                }
                break;
            case "Flat":
                switch (featureCode) {
                    case 5: features = "2BHK, Lift, Security"; break;
                    case 6: features = "3BHK, Parking, Gym"; break;
                    case 7: features = "2BHK, Swimming Pool, Balcony"; break;
                    case 8: features = "3BHK, Gated Community, Power Backup"; break;
                    default: features = "Standard Features"; break;
                }
                break;
            case "House":
                switch (featureCode) {
                    case 9: features = "3BHK, Duplex, Garden Area"; break;
                    case 10: features = "3BHK, Car Garage, Borewell"; break;
                    case 11: features = "3BHK, Balcony, Wood Interiors"; break;
                    case 12: features = "3BHK, Open Terrace, Gated Community"; break;
                    default: features = "Residential Features"; break;
                }
                break;
            default:
                features = "Unknown Features";
                break;
        }
        return features;
    }

    double getPrice() {
        return price;
    }

    int getId() {
        return id;
    }

    int getAreaCode() {
        return areaCode;
    }
}

class Banking2 {
    static Scanner sc = new Scanner(System.in);
    static void processPayment(double amount) {
        double advance = amount / 4;
        System.out.println("\u001B[35m\nBooking Summary:");
        System.out.println("Total Amount: ₹" + amount);
        System.out.println("Advance to Pay Now (25%): ₹" + advance);
        System.out.println("Redirecting to secure bank payment portal... 🔒\u001B[0m");
    
    System.out.println("\u001B[33mDo YOu Want To play Discount Games?\nenter 1 for yes else any number for No\u001B[0m");
    int num = Integer.parseInt(sc.nextLine());
    if(num==1)
    amount = DiscountGames.Main(amount);
    RealEstatePayment.main(amount);
    }
}

class RealEstate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\u001B[35m======================================");
            System.out.println("      Welcome to Real Estate Finder");
            System.out.println("======================================\u001B[0m");
            System.out.println("\u001B[36m\"Creating connections between you and your dream home\"\u001B[0m\n");

            System.out.println("\u001B[33mSelect Property Type:\u001B[0m");
            System.out.println("1. Plot\n2. Flat\n3. Individual House");
            System.out.print("\u001B[34mEnter your choice: \u001B[0m");
            int type = Integer.parseInt(sc.nextLine());

            String propertyType;
            if (type == 1) propertyType = "Plot";
            else if (type == 2) propertyType = "Flat";
            else if (type == 3) propertyType = "House";
            else {
                System.out.println("\u001B[31mInvalid property type. Exiting.\u001B[0m");
                return;
            }

            System.out.println("\n\u001B[33mChoose Area Code:\u001B[0m");
            System.out.println("1. Madhapur\n2. Gachibowli\n3. Kukatpally\n4. LB Nagar");
            System.out.print("\u001B[34mEnter area number: \u001B[0m");
            int areaCode = Integer.parseInt(sc.nextLine());

            if (areaCode < 1 || areaCode > 4) {
                System.out.println("\u001B[31mInvalid area code. Exiting.\u001B[0m");
                return;
            }

            System.out.print("\n\u001B[34mEnter your minimum budget (₹): \u001B[0m");
            double minBudget = Double.parseDouble(sc.nextLine());

            System.out.print("\u001B[34mEnter your maximum budget (₹): \u001B[0m");
            double maxBudget = Double.parseDouble(sc.nextLine());

            if (minBudget > maxBudget) {
                System.out.println("\u001B[31mInvalid budget range. Exiting.\u001B[0m");
                return;
            }

            Plot[] plots = new Plot[4];

            if (type == 1) {
                plots[0] = new Plot(1, areaCode, 1000, 2200000, 1);
                plots[1] = new Plot(2, areaCode, 900, 2000000, 2);
                plots[2] = new Plot(3, areaCode, 800, 1800000, 3);
                plots[3] = new Plot(4, areaCode, 700, 1600000, 4);
            } else if (type == 2) {
                plots[0] = new Plot(1, areaCode, 1200, 3500000, 5);
                plots[1] = new Plot(2, areaCode, 1000, 3200000, 6);
                plots[2] = new Plot(3, areaCode, 950, 3000000, 7);
                plots[3] = new Plot(4, areaCode, 850, 2800000, 8);
            } else {
                plots[0] = new Plot(1, areaCode, 1600, 5500000, 9);
                plots[1] = new Plot(2, areaCode, 1400, 5000000, 10);
                plots[2] = new Plot(3, areaCode, 1300, 4700000, 11);
                plots[3] = new Plot(4, areaCode, 1200, 4400000, 12);
            }

            System.out.println("\n\u001B[34mAvailable properties between ₹" + minBudget + " and ₹" + maxBudget + ":\u001B[0m");
            boolean found = false;

            for (Plot plot : plots) {
                double price = plot.getPrice();
                if (price >= minBudget && price <= maxBudget) {
                    plot.display();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("\u001B[31mNo properties found in your budget.\u001B[0m");
                System.out.println("\u001B[36mAvailable Price Ranges for " + propertyType + "s:\u001B[0m");

                for (Plot plot : plots) {
                    System.out.println("\u001B[33mPlot ID: " + plot.getId() + " - ₹" + plot.getPrice() + "\u001B[0m");
                }

                System.out.print("\n\u001B[34mWould you like to:\n1. Try Again\n2. Exit\nEnter your choice: \u001B[0m");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) continue;
                else break;
            }

            System.out.print("\n\u001B[34mWant to view full details? Enter Plot ID (0 to skip): \u001B[0m");
            int viewId = Integer.parseInt(sc.nextLine());
            boolean valid = false;

            for (Plot plot : plots) {
                if (plot.getId() == viewId && plot.getPrice() >= minBudget && plot.getPrice() <= maxBudget) {
                    System.out.println("\n\u001B[32mFull Property Details:\u001B[0m");
                    plot.displayFullDetails(propertyType);
                    valid = true;

                    System.out.print("\n\u001B[34mWould you like to:\n1. Purchase this property\n2. Return to Main Menu\n3. Exit\nEnter your choice: \u001B[0m");
                    int act = Integer.parseInt(sc.nextLine());
                    if (act == 1) {
                        Banking2.processPayment(plot.getPrice());
                    } else if (act == 2) {
                        break; 
                    } else {
                        System.out.println("\u001B[32mThank you for visiting!\u001B[0m");
                        return;
                    }
                }
            }
            if(valid) continue;


            if (!valid && viewId != 0) {
                System.out.println("\u001B[31mInvalid Plot ID or not in selected range.\u001B[0m");
            }

            System.out.println("\n\u001B[32mThank you for using Real Estate Finder!\u001B[0m");
            break;
        }
    }
}


interface PaymentMethod {
    void pay(double amount);
}

class NetBanking implements PaymentMethod {
    Scanner sc = new Scanner(System.in);

    @Override
    public void pay(double amount) {
        System.out.println("\u001B[34m---- Net Banking Payment ----\u001B[0m");

        System.out.print("Enter your Bank Name: ");
        String bankName = sc.nextLine();

        System.out.printf("You are paying \u20B9%.2f via %s.\n", amount, bankName);

        System.out.print("Confirm payment? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (RealEstatePayment.userBalance >= amount) {
                RealEstatePayment.userBalance -= amount;
                System.out.println("Processing payment...");
                 char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }
                System.out.println("\r\u001B[32mPayment of \u20B9" + amount + " successful via Net Banking!\u001B[0m");
                System.out.println("Transaction ID: TXN" + System.currentTimeMillis());
            } else {
                System.out.println("\u001B[31mInsufficient balance in your account.\u001B[0m");
                new Banking().processPayment(amount);
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }
}

class EMI implements PaymentMethod {
    Scanner sc = new Scanner(System.in);

    @Override
    public void pay(double amount) {
        System.out.println("\u001B[34m---- EMI Payment ----\u001B[0m");

        System.out.println("Choose Bank:");
        System.out.println("1. SBI (8% interest)");
        System.out.println("2. HDFC (9% interest)");
        System.out.println("3. ICICI (10% interest)");
        System.out.println("4. Axis Bank (11% interest)");

        int bankChoice = sc.nextInt();
        sc.nextLine();

        String bankName = "";
        double interestRate = 0;

        switch (bankChoice) {
            case 1: bankName = "SBI"; interestRate = 8; break;
            case 2: bankName = "HDFC"; interestRate = 9; break;
            case 3: bankName = "ICICI"; interestRate = 10; break;
            case 4: bankName = "Axis Bank"; interestRate = 11; break;
            default:
                System.out.println("Invalid bank selection.");
                return;
        }

        System.out.print("Enter number of months for EMI: ");
        int months = sc.nextInt();
        sc.nextLine();

        double totalInterest = (amount * interestRate * months) / (12 * 100);
        double totalPayable = amount + totalInterest;
        double monthlyInstallment = totalPayable / months;

        System.out.println("\n\u001B[36m---- EMI Details ----\u001B[0m");
        System.out.println("Bank Selected: " + bankName);
        System.out.printf("Property Price: \u20B9%.2f\n", amount);
        System.out.printf("Interest Rate: %.2f%%\n", interestRate);
        System.out.printf("Total Interest: \u20B9%.2f\n", totalInterest);
        System.out.printf("Total Payable Amount: \u20B9%.2f\n", totalPayable);
        System.out.printf("Monthly Installment for %d months: \u20B9%.2f\n", months, monthlyInstallment);

        System.out.print("Confirm EMI plan? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("Processing payment...");
                 char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }
            System.out.println("\rEMI plan confirmed with " + bankName + ".");
            System.out.println("EMI Plan ID: EMI" + System.currentTimeMillis());
        } else {
            System.out.println("EMI payment cancelled.");
        }
    }
}

class CardPayment implements PaymentMethod {
    Scanner sc = new Scanner(System.in);

    @Override
    public void pay(double amount) {
        System.out.println("\u001B[34m---- Card Payment ----\u001B[0m");

        System.out.print("Enter Card Type (Credit/Debit): ");
        String cardType = sc.nextLine();

        System.out.printf("You are paying \u20B9%.2f using %s card.\n", amount, cardType);

        System.out.print("Confirm payment? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (RealEstatePayment.userBalance >= amount) {
                RealEstatePayment.userBalance -= amount;
                System.out.println("Processing card payment...");
                 char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }
                System.out.println("\r\u001B[32mPayment of \u20B9" + amount + " successful via " + cardType + " card!\u001B[0m");
                System.out.println("Transaction ID: CARD" + System.currentTimeMillis());
            } else {
                System.out.println("\u001B[31mInsufficient balance in your account.\u001B[0m");
                new Banking().processPayment(amount);
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }
}

class Loan implements PaymentMethod {
    Scanner sc = new Scanner(System.in);

    @Override
    public void pay(double amount) {
        System.out.println("\u001B[34m---- Loan Application ----\u001B[0m");

        System.out.println("Choose Loan Bank:");
        System.out.println("1. SBI (8.5%)");
        System.out.println("2. HDFC (9.0%)");
        System.out.println("3. ICICI (9.5%)");

        int choice = sc.nextInt();
        sc.nextLine();

        String bank = "";
        double interestRate = 0;

        switch (choice) {
            case 1: bank = "SBI"; interestRate = 8.5; break;
            case 2: bank = "HDFC"; interestRate = 9.0; break;
            case 3: bank = "ICICI"; interestRate = 9.5; break;
            default:
                System.out.println("Invalid bank selection.");
                return;
        }

        System.out.print("Enter your CIBIL score (300 to 900): ");
        int cibil = sc.nextInt();
        sc.nextLine();

        if (cibil < 700) {
            System.out.println("\u001B[31m\u274C Loan rejected due to low CIBIL score.\u001B[0m");
            new Banking().processPayment(amount);
            return;
        }

        System.out.print("Enter loan duration in years: ");
        int years = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter your monthly income: \u20B9");
        double income = sc.nextDouble();
        sc.nextLine();

        if (income < 25000) {
            System.out.println("\u001B[31m\u274C Loan rejected due to low income.\u001B[0m");
            return;
        }

        double totalInterest = (amount * interestRate * years) / 100;
        double totalPayable = amount + totalInterest;
        double monthlyInstallment = totalPayable / (years * 12);

        System.out.println("\n\u001B[32m\u2705 Loan Approved from " + bank + "\u001B[0m");
        System.out.printf("Loan Amount: \u20B9%.2f\n", amount);
        System.out.printf("Interest Rate: %.2f%%\n", interestRate);
        System.out.printf("Total Payable (with interest): \u20B9%.2f\n", totalPayable);
        System.out.printf("Monthly EMI for %d years: \u20B9%.2f\n", years, monthlyInstallment);

        System.out.print("Do you accept the loan terms? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("Processing payment...");
                 char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("... ");

        for (int i = 0; i < 30; i++) {
            try {
                System.out.print("\r... " + spinner[i % spinner.length]);
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                
            }
        }
            System.out.println("\r\u001B[32m\u2705 Loan disbursed. Payment completed via loan.\u001B[0m");
            System.out.println("Loan Transaction ID: LOAN" + System.currentTimeMillis());
        } else {
            System.out.println("Loan declined by user.");
        }
    }
}

class Banking {
    public void processPayment(double amount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose payment method:");
        System.out.println("1. Net Banking");
        System.out.println("2. EMI");
        System.out.println("3. Card Payment");
        System.out.println("4. Apply for Loan");
        int choice = sc.nextInt();
        sc.nextLine();
        PaymentMethod method;

        switch (choice) {
            case 1: method = new NetBanking(); break;
            case 2: method = new EMI(); break;
            case 3: method = new CardPayment(); break;
            case 4: method = new Loan(); break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        method.pay(amount);
    }
}

class RealEstatePayment {
    public static double userBalance;

    public static void main(double amount) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\u001B[35m===== PAYMENT SYSTEM =====\u001B[0m");

        System.out.print("Enter your current account balance: \u20B9");
        userBalance = sc.nextDouble();

        double price = amount;

        System.out.println("You chose a property worth \u20B9" + price);

        new Banking().processPayment(price);

    }
}





class ElectronicGadgets
{
    static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";

   public static void main(String[] args) 
   {
     String billDetails = "";
     double totalPrice = 0L;
     boolean continueShopping = true;
     while (continueShopping) 
     {
         System.out.println();
         System.out.println(YELLOW + "     ===============================================" + RESET);
         System.out.println(WHITE + "                 ELECTRONIC GADGETS MENU        ");
         System.out.println(YELLOW + "     ===============================================" + RESET);
         System.out.println(" Enter your choice ");
         System.out.println(RED + "1. Home Applications " + RESET);
         System.out.println(GREEN + "2. Computing Devices " + RESET);
         System.out.println(BLUE + "3. Communications " + RESET);
         System.out.println(MAGENTA + "4. Exit" + RESET);

         int n = Integer.parseInt(sc.nextLine());
        

         if (n == 1) 
         {
             System.out.println(BLUE + "\n---------Home Appliances---------" + RESET);
             System.out.println(MAGENTA + "1. Air Conditioner");
             System.out.println(MAGENTA + "2. Dishwasher");
             System.out.println(MAGENTA + "3. Washing Machine");
             System.out.println(MAGENTA + "4. Refrigerator");
             System.out.print(WHITE + "\nChoose a Home Appliance [1-4]: " + RESET);
             int applianceChoice = Integer.parseInt(sc.nextLine());

             if (applianceChoice == 1) 
             {
                 System.out.println(YELLOW + "\n-- Brands for Air Conditioner --");
                 System.out.println(GREEN + "1. LG - Rs.55000");
                 System.out.println(GREEN + "2. Samsung - Rs.48000");
                 System.out.println(GREEN + "3. Daikin - Rs.60000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1) 
                 {
                     billDetails += "Item : LG Air Conditioner - Rs.55000/-\n";
                     totalPrice += 55000;
                 } 
                 else if (brand == 2) 
                 {
                     billDetails += "Item : Samsung Air Conditioner - Rs.48000/-\n";
                     totalPrice += 48000;
                 }
                 else if (brand == 3) 
                 {
                     billDetails += "Item : Daikin Air Conditioner - Rs.60000/-\n";
                     totalPrice += 60000;
                 }
             }

             else if (applianceChoice == 2) 
             {
                 System.out.println(YELLOW + "\n-- Brands for Dishwasher --");
                 System.out.println(GREEN + "1. Bosch - Rs.45000");
                 System.out.println(GREEN + "2. IFB - Rs.52000");
                 System.out.println(GREEN + "3. LG - Rs.48000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1) 
                 {
                     billDetails += "Item : Bosch Dishwasher - Rs.45000/-\n";
                     totalPrice += 45000;
                 }
                 else if (brand == 2) 
                 {
                     billDetails += "Item : IFB Dishwasher - Rs.52000/-\n";
                     totalPrice += 52000;
                 } 
                 else if (brand == 3) 
                 {
                     billDetails += "Item : LG Dishwasher - Rs.48000/-\n";
                     totalPrice += 48000;
                 }
             }

             else if (applianceChoice == 3) 
             {
                 System.out.println(YELLOW + "\n-- Brands for Washing Machine --");
                 System.out.println(GREEN + "1. Whirlpool - Rs.28000");
                 System.out.println(GREEN + "2. LG - Rs.30000");
                 System.out.println(GREEN + "3. IFB - Rs.35000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1) 
                 {
                     billDetails += "Item : Whirlpool Washing Machine - Rs.28000/-\n";
                     totalPrice += 28000;
                 } 
                 else if (brand == 2) 
                 {
                     billDetails += "Item : LG Washing Machine - Rs.30000/-\n";
                     totalPrice += 30000;
                 } 
                 else if (brand == 3) 
                 {
                     billDetails += "Item : IFB Washing Machine - Rs.35000/-\n";
                     totalPrice += 35000;
                 }
             }

             else if (applianceChoice == 4) 
             {
                 System.out.println(YELLOW + "\n-- Brands for Refrigerator --");
                 System.out.println(GREEN + "1. Haier - Rs.20000");
                 System.out.println(GREEN + "2. Godrej - Rs.25000");
                 System.out.println(GREEN + "3. LG - Rs.22000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1)
                 {
                     billDetails += "Item : Haier Refrigerator - Rs.20000/-\n";
                     totalPrice += 20000;
                 } 
                 else if (brand == 2) 
                 {
                     billDetails += "Item : Godrej Refrigerator - Rs.25000/-\n";
                     totalPrice += 25000;
                 } 
                 else if (brand == 3)
                 {
                     billDetails += "Item : LG Refrigerator - Rs.22000/-\n";
                     totalPrice += 22000;
                 }
             }

             else {
                 System.out.println(RED + "Invalid appliance option." + RESET);
                 System.out.println(RED + "Please select valid one" + RESET);

             }
         }

         else if (n == 2) 
         {
             System.out.println(MAGENTA + "\n---------Computing Devices---------" + RESET);
             System.out.println(BLUE + "1. Laptop");
             System.out.println(BLUE + "2. Desktop");
             System.out.println(BLUE + "3. Tablet");
             System.out.println(BLUE + "4. Printer");
             System.out.print(WHITE + "\nChoose a Computing Device [1-4]: " + RESET);
             int choice = Integer.parseInt(sc.nextLine());

             if (choice == 1) 
             {
                 System.out.println(GREEN + "\n-- Brands for Laptop --");
                 System.out.println(YELLOW + "1. HP - Rs.55000");
                 System.out.println(YELLOW + "2. Dell - Rs.60000");
                 System.out.println(YELLOW + "3. Lenovo - Rs.52000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1)
                 {
                     billDetails += "Item : HP Laptop - Rs.55000/-\n";
                     totalPrice += 55000;
                 } 
                 else if (brand == 2) 
                 {
                     billDetails += "Item : Dell Laptop - Rs.60000/-\n";
                     totalPrice += 60000;
                 } 
                 else if (brand == 3) 
                 {
                     billDetails += "Item : Lenovo Laptop - Rs.52000/-\n";
                     totalPrice += 52000;
                 }
             }

             else if (choice == 2) 
             {
                 System.out.println(GREEN + "\n-- Brands for Desktop --");
                 System.out.println(YELLOW + "1. Lenovo - Rs.45000");
                 System.out.println(YELLOW + "2. HP - Rs.50000");
                 System.out.println(YELLOW + "3. Acer - Rs.48000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1) 
                 {
                     billDetails += "Item : Lenovo Desktop - Rs.45000/-\n";
                     totalPrice += 45000;
                 } 
                 else if (brand == 2)
                 {
                     billDetails += "Item : HP Desktop - Rs.50000/-\n";
                     totalPrice += 50000;
                 } 
                 else if (brand == 3)
                 {
                     billDetails += "Item : Acer Desktop - Rs.48000/-\n";
                     totalPrice += 48000;
                 }
             }

             else if (choice == 3)
             {
                 System.out.println(GREEN + "\n-- Brands for Tablet --");
                 System.out.println(YELLOW + "1. Apple iPad - Rs.50000");
                 System.out.println(YELLOW + "2. Samsung Tab - Rs.35000");
                 System.out.println(YELLOW + "3. Lenovo Tab - Rs.30000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1) 
                 {
                     billDetails += "Item : Apple iPad - Rs.50000/-\n";
                     totalPrice += 50000;
                 } 
                 else if (brand == 2) 
                 {
                     billDetails += "Item : Samsung Tab - Rs.35000/-\n";
                     totalPrice += 35000;
                 } 
                 else if (brand == 3) 
                 {
                     billDetails += "Item : Lenovo Tab - Rs.30000/-\n";
                     totalPrice += 30000;
                 }
             }

             else if (choice == 4) 
             {
                 System.out.println(GREEN + "\n-- Brands for Printer --");
                 System.out.println(YELLOW + "1. Canon - Rs.8000");
                 System.out.println(YELLOW + "2. HP - Rs.7500");
                 System.out.println(YELLOW + "3. Epson - Rs.9000");
                 System.out.print(WHITE + "Choose a brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());

                 if (brand == 1) 
                 {
                     billDetails += "Item : Canon Printer - Rs.8000/-\n";
                     totalPrice += 8000;
                 }
                 else if (brand == 2) 
                 {
                     billDetails += "Item : HP Printer - Rs.7500/-\n";
                     totalPrice += 7500;
                 } 
                 else if (brand == 3) 
                 {
                     billDetails += "Item : Epson Printer - Rs.9000/-\n";
                     totalPrice += 9000;
                 }
             }

             else {
                 System.out.println(RED + "Invalid computing device option." + RESET);
                 System.out.println(RED + "Please select valid one" + RESET);

             }
         }

         else if(n==3)
         {

             System.out.println(BLUE + "\n---------Communications Devices---------");
             System.out.println(MAGENTA + "1. Smartphones");
             System.out.println(MAGENTA + "2. Smart Watches");
             System.out.println(MAGENTA + "3. Bluetooth Headsets");
             System.out.print(WHITE + "\nChoose a Communication Device [1-3]: " + RESET);
             int commChoice = Integer.parseInt(sc.nextLine());

             if(commChoice == 1)
             {
                 System.out.println(GREEN + "\n-- Brands for Smartphones --");
                 System.out.println(YELLOW + "1. Apple iPhone 14 - Rs.95,000/-");
                 System.out.println(YELLOW + "2. Samsung Galaxy S23 - Rs.70,000/-");
                 System.out.println(YELLOW + "3. OnePlus 12 - Rs.80,000/-");
                 System.out.print(BLUE + "Choose brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());
                
                 if( brand == 1)
                 { 
                     billDetails += "Item : Apple iPhone 14 - Rs.95000/-\n";
                     totalPrice += 95000;
                 }
                 else if(brand == 2) 
                 {
                     billDetails += "Item : Samsung Galaxy S23 - Rs.70000/-\n";
                     totalPrice += 70000; 
                 }
                 else if( brand == 3) 
                 {
                     billDetails += "Item : OnePlus 12 - Rs.80000/-\n";
                     totalPrice += 80000;
                     
                 }
             }
             else if(commChoice == 2)
             {
                 System.out.println(GREEN + "\n-- Brands for Smart Watches --");
                 System.out.println(YELLOW + "1. Apple Watch Series 9 - Rs.45,000/-");
                 System.out.println(YELLOW + "2. Samsung Galaxy Watch 6 - Rs.30,000/-");
                 System.out.println(YELLOW + "3. Noise ColorFit Ultra 3 - Rs.6,000/-");
                 System.out.print(BLUE + "Choose brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());
                
                 if(brand == 1) 
                 {
                     billDetails += "Item : Apple Watch Series 9 - Rs.45000/-\n";
                     totalPrice += 45000; 
                 }
                 else if(brand == 2) 
                 {
                     billDetails += "Item : Samsung Galaxy Watch 6 - Rs.30000/-\n";
                     totalPrice += 30000;
                 }
                 else if(brand == 3) 
                 {
                     billDetails += "Item : Noise ColorFit Ultra 3 - Rs.6000/-\n";
                     totalPrice += 6000; 
                 }
             }
             else if(commChoice == 3)
             {
                 System.out.println(GREEN + "\n-- Brands for Bluetooth Headsets --");
                 System.out.println(YELLOW + "1. Sony WH-1000XM5 - Rs.29,000/-");
                 System.out.println(YELLOW + "2. boAt Rockerz 450 - Rs.1,500/-");
                 System.out.println(YELLOW + "3. JBL Tune 760NC - Rs.6,500/-");
                 System.out.print(BLUE + "Choose brand: " + RESET);
                 int brand = Integer.parseInt(sc.nextLine());
                
                 if(brand == 1) 
                 {
                     billDetails += "Item : Sony WH-1000XM5 - Rs.29000/-\n";
                     totalPrice += 29000;
                 }
                 else if(brand == 2) 
                 { 
                     billDetails += "Item : boAt Rockerz 450 - Rs.1500/-\n";
                     totalPrice += 1500;
                 }
                 else if(brand == 3) 
                 {
                     billDetails += "Item : JBL Tune 760NC - Rs.6500/-\n";
                     totalPrice += 6500;
                 }
             }
             
             else 
             {
                 System.out.println(RED + "Invalid communications device option." + RESET);
                 System.out.println(RED + "Please select valid one" + RESET);

             }
         }
         else if (n == 4) 
         {
             continueShopping = false;
         }
         else 
         {
             System.out.println(RED + "Invalid option. Please choose between 1-4." + RESET);
         }
         if (continueShopping) 
         {
             System.out.print(WHITE + "\nDo you want to select another item? (y/n): " + RESET);
             String again = sc.nextLine();
             if (!again.equalsIgnoreCase("y")) 
             {
                 continueShopping = false;
             }
         }
     } 
    
     System.out.println(BLUE+"Do YOu Want To play Discount Games?"+YELLOW+ "\nenter 1 for yes else any number for No"+RESET);
     int num = Integer.parseInt(sc.nextLine());
     if(num==1)
     totalPrice = DiscountGames.Main(totalPrice);

     if (!billDetails.equals("")) 
 {
     System.out.println(YELLOW + "\n======== BILLING DETAILS ========" + RESET);
     System.out.print(BLUE + billDetails + RESET);
     System.out.println(YELLOW + "----------------------------------" + RESET);
     System.out.println(GREEN + "      Total Amount: Rs." + totalPrice + "/-" + RESET);
     System.out.println(YELLOW + "=================================" + RESET);

     System.out.println(WHITE + "\nChoose your payment method:" + RESET);
     System.out.println(GREEN + "1. EMI");
     System.out.println(GREEN + "2. Credit");
     System.out.println(GREEN + "3. NetBanking");
     System.out.print(WHITE + "Enter your choice [1-3]: " + RESET);
     String paymentOption = sc.nextLine();

     String paymentMethod = "";
     if(paymentOption.equals("1")) {
         
     System.out.print(WHITE + "Enter number of EMI months: " + RESET);
     int months = Integer.parseInt(sc.nextLine());

     if (months <= 0) {
         System.out.println(RED + "Invalid number of months." + RESET);
     } else {
         double monthlyPayment = (double) totalPrice / months;
         System.out.printf(GREEN + "Your monthly EMI for %d months is: Rs.%.2f/-\n" + RESET, months, monthlyPayment);
     }

     }
     else{
           if(paymentOption.equals("2")) paymentMethod = "Credit";
     else if(paymentOption.equals("3")) paymentMethod = "NetBanking";
     else paymentMethod = "Unknown";

     System.out.print(WHITE + "Enter your bank name: " + RESET);
     String bank = sc.nextLine();

     System.out.println(GREEN + "\nProcessing " + paymentMethod + " payment via " + bank + "..." + RESET);
     System.out.println(GREEN + "Payment successful!" + RESET);
     }
     System.out.println(GREEN + "Thank you for your purchase!" + RESET);
 }
 else 
 {
     System.out.println(RED + "No item selected for billing." + RESET);
 }
   } 
}

class DiscountGames{
    static Scanner sc = new Scanner(System.in);

    static String reset = "\u001B[0m";
    static String bold = "\u001B[1m";
    static String red = "\u001B[91m";
    static String green = "\u001B[92m";
    static String yellow = "\u001B[93m";
    static String blue = "\u001B[94m";
    static String cyan = "\u001B[36m";
    static String magenta = "\u001B[95m";


    private static String repeatString(String str, int count) {
        if (count <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    static double wheel(double totalPrice) {
        Random random = new Random();
        int[] possibleDiscounts = {0, 5, 10, 15};
        int maxDiscount = 0;

        System.out.println(cyan + repeatString("==", 38));
        System.out.println(bold + yellow + "          🎡 SPIN THE WHEEL - Win Your Discount (3 Chances) 🎯         " + reset);
        System.out.println(cyan + repeatString("==", 38) + reset);
        System.out.println(blue + "You have 3 chances to spin. The highest discount will be applied." + reset);
        sc.nextLine();

        for (int i = 1; i <= 3; i++) {
            System.out.print(yellow+"     🎡 Spinning..."+reset);
            System.out.println("\n➡ Press Enter to STOP the wheel (Spin " + i + " of 3)...");
          
            int resultIndex = random.nextInt(possibleDiscounts.length);
            int discount = SimpleProgress.main(null);
            System.out.println("🎰 Spin Result " + i + ": " + yellow + discount + "%" + reset + " discount!\n");

            if (discount > maxDiscount) {
                maxDiscount = discount;
            }
        }

        System.out.println("\n🎯 Highest discount from your spins: " + green + bold + maxDiscount + "%" + reset);
        System.out.print(blue + "💲 Your product price: ₹"+totalPrice + reset);
        double price = totalPrice;
        double discountAmount = (maxDiscount / 100.0) * price;
        double finalPrice = price - discountAmount;

        System.out.println(cyan + "\n" + repeatString("==", 25));
        System.out.println(magenta + bold + "🧾 BILLING SUMMARY");
        System.out.println(repeatString("==", 25) + reset);
        System.out.printf("💸 %sOriginal Price      : ₹%.2f%s%n", yellow, price, reset);
        System.out.printf("🎁 %sDiscount Applied    : %d%%%s%n", yellow, maxDiscount, reset);
        System.out.printf("💰 %sDiscount Amount     : ₹%.2f%s%n", yellow, discountAmount, reset);
        System.out.printf("✅ %sFinal Price         : ₹%.2f%s%n", yellow, finalPrice, reset);
        System.out.println(cyan + repeatString("==", 25) + reset);
        
        return finalPrice;
    }

    static double guessTheGadget(double totalPrice) {
        System.out.println(cyan + repeatString("==", 38));
        System.out.println(bold + yellow + "          🤖 GUESS THE GADGET - Win Up To 15% Discount! 🧠          ");
        System.out.println(cyan + repeatString("==", 38) + reset);
        System.out.println(blue + "Answer 3 gadget-related questions correctly to win a discount!" + reset);
        sc.nextLine();

        String[][] allQuestions = {
            {"I can make calls, take pictures, and fit in your pocket. What am I?", "smartphone"},
            {"I'm used to type, have a screen, and can run software. What am I?", "laptop"},
            {"You wear me on your wrist, I can track fitness and show time. What am I?", "smartwatch"},
            {"I help you hear audio privately. I go in or over your ears. What am I?", "headphones"},
            {"I fly in the sky with a camera. What am I?", "drone"},
            {"I keep food cold and fresh. What am I?", "refrigerator"},
            {"I show visuals and let you change channels. What am I?", "television"},
            {"I print on paper and connect to your PC. What am I?", "printer"},
            {"I control your smart devices using your voice. What am I?", "smart speaker"},
            {"I capture video and can be used for meetings. What am I?", "webcam"}
        };

        boolean[] used = new boolean[allQuestions.length];
        Random rand = new Random();
        int count = 0, score = 0;

        while (count < 3) {
            int index = rand.nextInt(allQuestions.length);
            if (!used[index]) {
                used[index] = true;

                String question = allQuestions[index][0];
                String answer = allQuestions[index][1];

                System.out.println("\nQ" + (count + 1) + ": " + question);
                System.out.print(blue + "Your answer: " + reset);
                String userAnswer = sc.nextLine().toLowerCase().trim();

                if (userAnswer.equals(answer)) {
                    System.out.println(green + "✅ Correct!" + reset);
                    score++;
                } else {
                    System.out.println(red + "❌ Wrong! Correct answer: " + answer + reset);
                }
                count++;
            }
        }

        int discountpercent;
        switch (score) {
            case 1: discountpercent = 5; break;
            case 2: discountpercent = 10; break;
            case 3: discountpercent = 15; break;
            default: discountpercent = 0; break;
        }

        System.out.println("\n🎉 You got " + yellow + score + "/3" + reset + " correct.");
        System.out.println("🎁 You earned a " + green + bold + discountpercent + "%" + reset + " discount!");

        System.out.print(blue + "\n💲 Your product price: ₹ "+totalPrice + reset);
        double price = totalPrice;
        double discount = (discountpercent / 100.0) * price;
        double finalPrice = price - discount;

        System.out.println(cyan + "\n" + repeatString("==", 25) + reset);
        System.out.println(magenta + bold + "🧾 BILLING SUMMARY" + reset);
        System.out.println(reset + repeatString("==", 25) + reset);
        System.out.printf("💸 %sOriginal Price      : ₹%.2f%s%n", yellow, price, reset);
        System.out.printf("🎁 %sDiscount Applied    : %d%%%s%n", yellow, discountpercent, reset);
        System.out.printf("💰 %sDiscount Amount     : ₹%.2f%s%n", yellow, discount, reset);
        System.out.printf("✅ %sFinal Price         : ₹%.2f%s%n", yellow, finalPrice, reset);
        System.out.println(cyan + repeatString("==", 25) + reset);
        System.out.println("Re-directing to Payment System..");
        return finalPrice;
    }

      static double Main(double totalPrice) {
          sc = new Scanner(System.in); 
          double amount=0;
        while (true) {
            
            System.out.println(cyan + repeatString("==", 30) + reset); 
            System.out.println(bold + yellow + "      🎮 WELCOME TO THE DISCOUNT GAME ZONE        " + reset);
            System.out.println(cyan + repeatString("==", 30) + "\n"); 

            System.out.println(cyan + bold + "📍 Choose a Game to Play:\n" + reset);
            System.out.println(yellow + "    1️⃣  Spin the Wheel 🎡");
            System.out.println("    2️⃣  Guess the Gadget 🤖\n");

            System.out.print(blue + "👉  Enter your choice (1 or 2): " + reset);
            
    
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine(); 
                if (choice == 1) {
                   amount= wheel(totalPrice);
                    break;
                } else if (choice == 2) {
                   amount =  guessTheGadget(totalPrice);
                    break;
                } else {
                    System.out.println(red + "\n❌ Invalid option! Please enter 1 or 2.\n" + reset);
                }
            } else {
                System.out.println(red + "\n❌ Invalid input! Please enter a number (1 or 2).\n" + reset);
                sc.nextLine(); 
            }
        }
        
        return amount;
    }
    


class SimpleProgress {
    public static int main(String[] args) {
        int[] percentages = {10, 15, 0, 5,10, 15, 0, 5};
        boolean[] stopFlag = {false};

        Thread inputThread = new Thread(() -> {
            try {
                
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                br.readLine(); 
                stopFlag[0] = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        inputThread.start();

        int lastShown = 0;

        while (!stopFlag[0]) {
            for (int percent : percentages) {
                if (stopFlag[0]) break;

                lastShown = percent;
                System.out.print("\r" + percent + "%");

                try {
                    Thread.sleep(90);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return lastShown;
    }
}

}



class Banner {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String[] COLORS = {
        "\u001B[38;5;187m", // pink color
        "\u001B[38;5;159m"  // Blue color
    };

    private static final String[] WELCOME_TO_ART = {
        "\t\t\t\t █████   ███   ███████████████ █████       █████████     ███████   ██████  ████████████████ ",
        "\t\t\t\t ░░███   ░███  ░░███░░███░░░░░█░░███      ███░░░░░███  ███░░░░░███░░██████ ██████░░███░░░░░█ ",
        "\t\t\t\t  ░███   ░███   ░███ ░███  █ ░  ░███      ███      ░░░ ███      ░░███░███░█████░███ ░███  █ ░ ",
        "\t\t\t\t  ░███   ░███   ░███ ░██████    ░███     ░███         ░███       ░███░███░░███ ░███ ░██████   ",
        "\t\t\t\t  ░░███ █████  ███  ░███░░█    ░███     ░███         ░███       ░███░███ ░░░  ░███ ░███░░█   ",
        "\t\t\t\t    ░░░█████░█████░  ░███ ░  █ ░███    █░░███      ███░░███      ███ ░███     ░███ ░███ ░  █ ",
        "\t\t\t\t      ░░███ ░░███    ██████████ ███████████ ░░█████████  ░░░███████░  █████     ███████████████ ",
        "\t\t\t\t      ░░░   ░░░    ░░░░░░░░░░ ░░░░░░░░░░░   ░░░░░░░░░    ░░░░░░░   ░░░░░     ░░░░░░░░░░░░░░░ "
    };

    private static final String[] LEVELUP_SERVICES_ART = {
        "\t\t\t\t\t\t\t\t    ███████████  ███████               ",
        "\t\t\t\t\t\t\t\t   ░█░░░███░░░████░░░░░███            ",
        "\t\t\t\t\t\t\t\t   ░  ░███ ░███      ░░███            ",
        "\t\t\t\t\t\t\t\t      ░███ ░███       ░███            ",
        "\t\t\t\t\t\t\t\t      ░███ ░███       ░███            ",
        "\t\t\t\t\t\t\t\t      ░███ ░░███       ███            ",
        "\t\t\t\t\t\t\t\t      █████  ░░░███████░              ",
        "\t\t\t\t\t\t\t\t     ░░░░░    ░░░░░░░                       ",
        "\t\t\t\t                                                                            ",
        "\t\t\t\t █████      ██████████ █████   ███████████████ █████                █████  ████████████████     ",
        "\t\t\t\t░░███      ░░███░░░░░█░░███   ░░███░░███░░░░░█░░███                ░░███  ░░███░░███░░░░░███     ",
        "\t\t\t\t ░███       ░███  █ ░  ░███    ░███ ░███  █ ░  ░███                 ░███   ░███ ░███     ░███     ",
        "\t\t\t\t ░███       ░██████    ░███    ░███ ░██████    ░███                 ░███   ░███ ░██████████     ",
        "\t\t\t\t ░███       ░███░░█    ░░███   ███  ░███░░█    ░███                 ░███   ░███ ░███░░░░░░       ",
        "\t\t\t\t ░███     █░███ ░  █  ░░░█████░  ░███ ░  █ ░███      █          ░███   ░███ ░███             ",
        "\t\t\t\t █████████████████████   ░░███    ██████████ ████████████         ░░████████  █████             ",
        "\t\t\t\t░░░░░░░░░░░░░░░░░░░░░    ░░░    ░░░░░░░░░░ ░░░░░░░░░░░░░░         ░░░░░░░░  ░░░░░             "
    };

    public static void main(String[] args) {
        System.out.println();
        printRainbowAscii(WELCOME_TO_ART);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\u001B[38;5;200m         - W E L C O M  T O");
        System.out.println();
        printRainbowAscii(LEVELUP_SERVICES_ART);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\u001B[38;5;200m       - L E V E L U P  S E R V I C E S");
    }
    
    public static void printRainbowAscii(String[] asciiArtLines) {
        int colorIndex = 0;
        for (String line : asciiArtLines) {
            String color = COLORS[colorIndex % COLORS.length];
            System.out.println(color + line + ANSI_RESET);
            colorIndex++;
        }
    }
}


class Home
{   static Scanner sc = new Scanner(System.in);
	static void home(UserDetails user){
        System.out.println("\r\u001B[32m**********************************************************\u001B[0m");
        System.out.println("\u001B[1;35m             Welcome to HOME PAGE              \u001B[0m");
        System.out.println("\u001B[32m**********************************************************\u001B[0m");
        
        System.out.print("\u001B[36mChoose one of Our Services.\n");
        System.out.println("\u001B[35m1.Real Estate\n2.Electronic Gadgets\n3.Exit");
        System.out.print("\u001B[33mEnter Your Choice:\u001B[0m");
        int n = Integer.parseInt(sc.nextLine());
        if(n==1)
            RealEstate.main(null);
        else if(n==2)
            ElectronicGadgets.main(null);
        else if(n==3)
            System.out.print("\u001B[35mThank you!");
        else{
            System.out.print("\u001B[36mInvalid Input\nEnter 1 to continue else 0 to exit");
            int m = Integer.parseInt(sc.nextLine());
            if(m==1)
            Home.home(user);
            else
            System.out.print("\u001B[35mThank you!");
        }
	}
}