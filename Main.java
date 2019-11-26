import java.util.Scanner;
public class Main{
    static Scanner input = new Scanner(System.in);
    static String userInput = null;
    static boolean RUN = true;
    public static void main(String[] args){
        System.out.println("Welcome to Fantasy OverWatch!!"); 
        userInput = getInput("Login(L) or Create Account(A)");
        if(userInput.equalsIgnoreCase("l")){
            // add login function
        }
        if(userInput.equalsIgnoreCase("a")){
            // add create new account funtion
            // password needs to be hash encrypted (part of stretch goal for mod vs user)
        }

        while(RUN)
            menu(userInput);
        
    }

    public static void menu(String userInput){
        int choice;
        System.out.println("Please select an option:");
        System.out.println("(1) Start a League"); // should ask for number of users, then draft
        System.out.println("(2) View User Team"); // print user team
        System.out.println("(3) View Player Stats"); // search any player on the spreadsheet 
        System.out.println("(4) View Overwatch Teams"); // ask user to input a overwatch team - print that team
        System.out.println("(5) View Top Players"); // let user see top players for stat of their choice
        System.out.println("(6) Quit"); // prompt user to save
        choice = input.nextInt();
        switch(choice){
            case(1):
                draft();
                break;
            case(2):
                viewTeam();
                break;
            case(3):
                viewStats();
                break;
            case(4):
                viewOWTeam();
                break;
            case(5):
                viewTopPlayers();
                break;
            case(6):
                userInput = getInput("Would you like to save?('yes' or 'no')"); 
                if(userInput.equalsIgnoreCase("y")){
                    //write method to save
                    System.out.println("Yikes"); // this print is just a test (delete later)
                }
                else if(userInput.equalsIgnoreCase("n")){
                    System.out.println("Exiting...");
                    RUN = false;
                    //System.exit();
                }
                else{
                    System.out.println("Please enter a valid input.");
                    menu(userInput);
                }
                break;
            default:
                System.out.println("Please enter a valid input.");
                menu(userInput);
        }
        // if(choice == 1)
            // System.out.println("1");
        // if(choice == 2)
            // System.out.println("2");
        // if(choice == 3)
            // System.out.println("3");
        // if(choice == 4)
            // System.out.println("4");
        // if(choice == 5)
            // System.out.println("5");
        // if(choice == 6)
            // exit();

    }
    
    public static void draft(){
        
    }
    
    public static void viewTeam(){
        
    }
    
    public static void viewStats(){
        
    }
    
    public static void viewOWTeam(){
        
    }
    
    public static void viewTopPlayers(){
        
    }

    public static void exit(){
        userInput = getInput("Would you like to save?('yes' or 'no')"); 
        if(userInput.equalsIgnoreCase("y")){
            //write method to save
            System.out.println("Yikes"); // this print is just a test (delete later)
        }
        if(userInput.equalsIgnoreCase("n")){
            System.out.println("Exiting...");
            //System.exit();
        }
    }

    public static String getInput(String message){
        System.out.println(message);
        String returnMessage = input.nextLine();
        return returnMessage;
    }
}