import java.util.Scanner;
public class Main{
    static Scanner input = new Scanner(System.in);
    static String userInput = null;
    public static void main(String[] args){
        System.out.println("Welcome to Fantasy OverWatch!!"); 
        userInput = getInput("Login(L) or Create Account(A)");
        if(userInput.equalsIgnoreCase("l")){
            // add login function
        }
        if(userInput.equalsIgnoreCase("s")){
            // add create new account funtion
            // password needs to be hash encrypted (part of stretch goal for mod vs user)
        }
    }
    
    public static String getInput(String message){
        System.out.println(message);
        String returnMessage = input.nextLine();
        return returnMessage;
    }
}