import java.util.Scanner;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
public class Main{
    static User user;
    private static Scanner input = new Scanner(System.in);
    private static List<PlayerInfo> database = new ArrayList<PlayerInfo>(loadFromFile("fantasyOverwatch.csv"));
    private static List<PlayerInfo> userTeam = new ArrayList<PlayerInfo>(6);
    private static List<PlayerInfo> draftingDatabase = new ArrayList<PlayerInfo>(loadFromFile("fantasyOverwatch.csv"));
    private static List<PlayerInfo> playerSearch = new ArrayList<PlayerInfo>();
    private static PlayerInfo chosenPlayer;
    private static List<User> users = new ArrayList<User>();
    private static String message = "";
    private static int numberOfUsers = 0;
    private static String userInput;
    private static boolean RUN = true;
    public static boolean DRAFTING = false;
    private static int numberOfTeams = 0; //setting this to 8 will not let you choose team size
    private static int win = 0;
    private static int lose = 0; // win/lose/tie used to create a record for your team
    private static int tie = 0;
    public static void main(String[] args){
        System.out.println("Welcome to Fantasy OverWatch!!"); 
        userInput = getInput("Do you already have a team?(Y or N)");
        if(userInput.equalsIgnoreCase("y")){
            while(numberOfUsers < 1 || numberOfUsers > 22){
                userInput = getInput("How many people were in your league?");
                try{
                    numberOfUsers = Integer.parseInt(userInput);
                }
                catch(Exception e){
                    System.out.println("Not a valid number.");
                }
            }
            for(int i = 0; i < numberOfUsers; i++){
                boolean loop = true;
                while(loop){
                    String userName = "";
                    System.out.printf("What was the name of Team %d?%n",i+1);
                    userInput = input.nextLine();
                    user = SaveFileReader.readFromFile(userInput+".csv",database);
                    if(user!=null){
                        users.add(user);
                        loop = false;
                    }
                }
            }

        }
        while(RUN){
            try{
                if (!DRAFTING){
                    System.out.println("Please select an option:");
                    System.out.println("(1) Start a League"); // should ask for number of users, then draft
                    System.out.println("(2) View User Team"); // print user team
                    System.out.println("(3) View Overwatch League Players"); // search any player on the spreadsheet 
                    System.out.println("(4) Play Game");
                    System.out.println("(5) Quit"); // prompt user to save 
                    menu(input.nextLine());
                    continue;
                }
                for(int i = 0; i<6; i++){
                    for(User u : users){
                        List<PlayerInfo> tempDatabase = new ArrayList<PlayerInfo>();
                        boolean loop = true;
                        while(loop){
                            System.out.printf("%s, which player would you like to draft? (%d more tank, %d more healer, %d more damage)%n",u.getUserName(),u.getTankCount(), u.getHealerCount(), u.getDamageCount());            
                            tempDatabase = u.draftNewPlayer(input.nextLine(),draftingDatabase);
                            if(tempDatabase != null){
                                draftingDatabase = tempDatabase;
                                loop = false;
                                print(draftingDatabase);
                            }
                        }
                    }
                }
                DRAFTING = false;
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid NUMBER");
                input.next();
            }
            catch(Exception e){
                System.out.println("Please enter a valid input..."+e);
            }        
        }
    }

    public static void menu(String choice){
        switch(choice){
            case("1"):
            if(users.size() == 0){
                DRAFTING = true;
                while(numberOfUsers < 1 || numberOfUsers > 22){
                    System.out.println("Up to 22 players are allowed.");
                    userInput = getInput("How many people are drafting?");
                    try{
                        numberOfUsers = Integer.parseInt(userInput);
                    }
                    catch(Exception e){
                        System.out.println("Not a valid number.");
                    }
                }
                for(int i = 0; i < numberOfUsers; i++){
                    String userName = "";
                    System.out.printf("Player %d, What is your team name?%n",i+1);
                    userInput = input.nextLine();
                    user = new User(userInput);
                    users.add(user);
                }
                print(database);
            }else{
                System.out.println("You already drafted!");
            }
            break;
            case("2"):
            viewTeam();
            break;
            case("3"):
            sortPlayers();
            break;
            case("4"):
            if(user != null){
                simulateGame();
            }else{System.out.println("You must draft a team before you can play a game!");}
            break;            
            case("5"):
            exit();
            break;
            default:
            System.out.println("Please enter a valid input. (1-4)");
        }
    }

    public static void viewTeam(){
        if(users.size() != 0){
            System.out.printf("What is your team name?%n");
            userInput = input.nextLine();
            user = null;
            for(User u : users){
                if(u.getUserName().equalsIgnoreCase(userInput))
                    user = u;
            }
            if(user != null){
                System.out.printf("%n%s%n",user.getUserName());
                print(user.getTeam());
            }else{System.out.println("Team not found.");}
        }else{System.out.println("You must draft a team before you can view your team!");}
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

        //I don't think this should be an option for this method
        //System.out.println("(9)  Back to Menu"); // back to other menu

        boolean printDatabase = true;
        choice = input.nextInt();
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
            printDatabase = false;
            System.out.println("Enter a player: ");
            String playerName = input.next();
            for(PlayerInfo p : database){
                if (playerName.equalsIgnoreCase(p.getName())){
                    playerSearch.add(p);
                }
            }
            print(playerSearch);
            System.out.println();
            break;

            //Dont think this option is neccesary
            //case(9): // exit to menu
            //menu(userInput);

            default: // reprint sortPlayers()
            System.out.println("Please enter a valid input.");
            sortPlayers();
        }

        if(printDatabase)
            print(database);
    }

    public static void simulateGame(){
        Random r = new Random();
        // scores for userTeam players
        int p1 = r.nextInt(40)+1; // assume p1 and p2 are damage
        int p2 = r.nextInt(40)+1;
        int p3 = r.nextInt(40)+1; // assume p3 and p4 are healers
        int p4 = r.nextInt(40)+1;
        int p5 = r.nextInt(40)+1; // assume p5 and p6 are tanks
        int p6 = r.nextInt(40)+1;
        // scores for opponents players
        int c1 = r.nextInt(40)+1;
        int c2 = r.nextInt(40)+1;
        int c3 = r.nextInt(40)+1;
        int c4 = r.nextInt(40)+1;
        int c5 = r.nextInt(40)+1;
        int c6 = r.nextInt(40)+1;

        //int[] playerScores = new int[]{p1,p2,p3,p4,p5,p6};
        int userPointsScored = p1 + p2 + p3 + p4 + p5 + p6;
        int oppPointsScored = c1 + c2 + c3 + c4 + c5 + c6;
        System.out.println("Your team scored: "+userPointsScored+"\nOpponent team scored: "+oppPointsScored);
        if(userPointsScored > oppPointsScored){
            System.out.println("You win!!");
            win++;
        }
        else if(userPointsScored < oppPointsScored){
            System.out.println("You lost!!");
            lose++;
        }
        else{
            System.out.println("You tied!!");
            tie++;
        }
        System.out.printf("User Record:\t%d-%d-%d%n",win,lose,tie);
    }    

    public static void exit(){ 
        RUN = false;
        if(users.size() != 0)
            for(User u : users){
                SaveFileWriter.writeToFile(u.getUserName()+".csv",u);
            }
        System.out.println("...Exiting.");
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