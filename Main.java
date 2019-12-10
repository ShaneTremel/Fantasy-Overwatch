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
        System.out.println("(3) View Overwatch League Players"); // search any player on the spreadsheet 
        System.out.println("(4) Quit"); // prompt user to save
        choice = input.nextInt();
        switch(choice){
            case(1):
            draft();
            break;
            case(2):
            viewTeam();
            break;
            case(3):
            sortPlayers();
            break;
            case(4):
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

    public static void sortPlayers(){
        int choice;
        System.out.println("How would you like to sort the players?");
        System.out.println("(1)  Alphabetical"); // should ask for number of users, then draft
        System.out.println("(2)  Most Elims"); // sort by elims
        System.out.println("(3)  Least Deaths"); // sort by deaths
        System.out.println("(4)  By Role"); // sort by role
        System.out.println("(5)  Most Healing"); // sort by healing
        System.out.println("(6)  Most Blocked Damage"); // sort by blocked
        System.out.println("(7)  Hero Played"); // sort by hero
        System.out.println("(8)  Overwatch Team"); // sort by ow team
        System.out.println("(9)  Specific Player"); // ask user for player, print that players stats
        System.out.println("(10) Back to Menu"); // back to other menu

        choice = input.nextInt();
        var database = loadFromFile("fantasyOverwatch.csv");
        switch(choice){
            case(1): // alphabetical
            Collections.sort(database,new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        return p1.getName().compareTo(p2.getName());
                    }
                });
            break;
            case(2): // elims
            database.sort(new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        if(p1.getEliminations() > p2.getEliminations())
                            return -1;
                        if(p1.getEliminations() < p2.getEliminations())
                            return 1;
                        return 0;
                    }
                });
            break;
            case(3): // deaths
            database.sort(new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        if(p1.getDeaths() > p2.getDeaths())
                            return 1;
                        if(p1.getDeaths() < p2.getDeaths())
                            return -1;
                        return 0;
                    }
                });
            break;
            case(4): // role
            //userInput = getInput("Which role? (Damage,Healer,Tank)");
            Collections.sort(database,new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        return p1.getRole().compareTo(p2.getRole());
                    }
                });
            break;
            case(5): // healing
            Collections.sort(database,new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        return p2.getHealing() - p1.getHealing();
                    }
                });
            break;
            case(6):  // blocked
            Collections.sort(database,new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        return p2.getBlocked() - p1.getBlocked();
                    }
                });
            break;
            case(7): // hero
            Collections.sort(database,new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        return p1.getHero().compareTo(p2.getHero());
                    }
                });
            break;
            case(8): // sort by team
            Collections.sort(database,new Comparator<PlayerInfo>(){
                    public int compare(PlayerInfo p1, PlayerInfo p2){
                        return p1.getOWTeam().compareTo(p2.getOWTeam());
                    }
                });
            break;
            case(9): // specific player
            
            break;
            case(10): // exit to menu
            menu(userInput);
            default: // reprint sortPlayers()
            System.out.println("Please enter a valid input.");
            sortPlayers();
        }


        print(database);
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
        System.out.printf("%n%-15s%-15s%-9s%-8s%-10s%-10s%-17s%s%n%n","Player","Eliminations","Deaths","Role","Healing","Blocked","Preferred Hero","Team");
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