import java.util.Scanner;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.lang.Enum;
public class Main{
    static Scanner input = new Scanner(System.in);
    static String userInput;
    static boolean RUN = true;
    static int numberOfTeams = 8; // default value for number of teams
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
            exit();
            break;
            default:
            System.out.println("Please enter a valid input.");
            menu(userInput);
        }

    }

    public static void draft(){
        do{
            numberOfTeams = getInt("How many teams in your league? (Min '2', Max '8')");
        }while(!(numberOfTeams >= 2 && numberOfTeams <= 8));
        Draft draft = new Draft(numberOfTeams);
    }

    public static void viewTeam(){

    }

    public static void viewStats(){
        var database = loadFromFile("fantasyOverwatch.csv");
    }

    public static void viewOWTeam(){
        var database = loadFromFile("fantasyOverwatch.csv");
        // sort by team
        Collections.sort(database,new Comparator<PlayerInfo>(){
                @Override
                public int compare(PlayerInfo p1, PlayerInfo p2){
                    return p1.getOWTeam().compareTo(p2.getOWTeam());
                }
            });

        print(database);
    }

    public static void viewTopPlayers(){

    }

    public static void exit(){ 
        userInput = getInput("Would you like to save?('yes' or 'no')"); 
        if(userInput.equalsIgnoreCase("y")){
            //write method to save
            System.out.println("Saving..."); // this print is just a test (delete later)
            RUN = false;
        }
        if(userInput.equalsIgnoreCase("n")){
            RUN = false;
            System.out.println("Exiting...");
        }
    }

    public static void print(Collection<PlayerInfo> players){
        System.out.printf("%-15s%-15s%-9s%-8s%-10s%-10s%-17s%s%n%n","Player","Eliminations","Deaths","Role","Healing","Blocked","Preferred Hero","Team");
        for(PlayerInfo player: players){
            System.out.printf("%-15s%-15.2f%-9.2f%-8s%-10d%-10d%-17s%s%n",player.getName(),player.getEliminations(),player.getDeaths(),player.getRole(),player.getHealing(),player.getBlocked(),player.getHero(),player.getOWTeam()); 
        }
    }

    public static ArrayList<PlayerInfo> loadFromFile(String path){
        ArrayList<PlayerInfo> owTeams = new ArrayList();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while((line = reader.readLine()) != null){
                String[] columns = line.split(",");

                String player = columns[0];
                double elims = Double.parseDouble(columns[1]);
                double deaths = Double.parseDouble(columns[2]);
                Role role = Role.valueOf(columns[3]);
                int healing = Integer.parseInt(columns[4]);
                int blocked = Integer.parseInt(columns[5]);
                Hero hero = Hero.valueOf(columns[6]);
                OWTeam owTeam = OWTeam.valueOf(columns[7]);

                PlayerInfo playerInfo = new PlayerInfo(player,elims,deaths,role,healing,blocked,hero,owTeam);
                owTeams.add(playerInfo);
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println("Could not read from file");
            System.out.println(e);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid numerical parse");
            System.out.println(e);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid enum parse");
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println("Unknown exception");
            System.out.println(e);
        }
        return owTeams;
    }

    public static String getInput(String message){
        System.out.println(message);
        String returnMessage = input.nextLine();
        return returnMessage;
    }

    public static int getInt(String message){
        System.out.println(message);
        int returnInt = input.nextInt();
        return returnInt;
    }
}