import java.util.Scanner;
public class Menu{
    static Scanner input = new Scanner(System.in);
    public Menu(){
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
                System.out.println("option 1"); // this is just to test that it works (change later)
            break;
            case(2):
                System.out.println("option 2"); // this is just to test that it works (change later)
            break;
            case(3):
                System.out.println("option 3"); // this is just to test that it works (change later)
            break;
            case(4):
                System.out.println("option 4"); // this is just to test that it works (change later)
            break;
            case(5):
                System.out.println("option 5"); // this is just to test that it works (change later)
            break;
            case(6):
                System.out.println("option 6"); // this is just to test that it works (change later)
            break;
            default:
        }
    }
}