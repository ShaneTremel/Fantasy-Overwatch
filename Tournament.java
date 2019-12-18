import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Tournament{
    private static Scanner input = new Scanner(System.in);
    public Tournament(boolean RUN){
        ArrayList<FantasyTeam> roundOne = new ArrayList<FantasyTeam>();
        loadTeams(roundOne);
        System.out.println("\nRound #1");
        System.out.println("--------");

        ArrayList<FantasyTeam> roundTwo = processBracket(roundOne);
        System.out.println("\nRound #2");
        System.out.println("--------");

        ArrayList<FantasyTeam> roundThree = processBracket(roundTwo);
        System.out.println("\nRound #3");
        System.out.println("--------");

        ArrayList<FantasyTeam> winner = processBracket(roundThree);
        System.out.println("\nCongrats!!! Winner of tournament is "+ winner.get(0).toString());
    }

    private static void loadTeams(ArrayList<FantasyTeam> teamList){
        System.out.println("Welcome to the Fantasy Overwatch Tournament Simulator\nA tournament can only be played with 8 teams\n**Note that team 1 will be the 1 seed, team 2 the 2 seed, and so on...**");

        FantasyTeam t1 = new FantasyTeam(getInput("Please enter team 1's name:"),1);
        teamList.add(t1);
        FantasyTeam t2 = new FantasyTeam(getInput("Please enter team 2's name:"),2);
        teamList.add(t2);
        FantasyTeam t3 = new FantasyTeam(getInput("Please enter team 3's name:"),3);
        teamList.add(t3);
        FantasyTeam t4 = new FantasyTeam(getInput("Please enter team 4's name:"),4);
        teamList.add(t4);
        FantasyTeam t5 = new FantasyTeam(getInput("Please enter team 5's name:"),5);
        teamList.add(t5);
        FantasyTeam t6 = new FantasyTeam(getInput("Please enter team 6's name:"),6);
        teamList.add(t6);
        FantasyTeam t7 = new FantasyTeam(getInput("Please enter team 7's name:"),7);
        teamList.add(t7);
        FantasyTeam t8 = new FantasyTeam(getInput("Please enter team 8's name:"),8);
        teamList.add(t8);

    }
    private static ArrayList<FantasyTeam> processBracket(ArrayList<FantasyTeam> thisRound){
        ArrayList<FantasyTeam> returnBracket = new ArrayList<FantasyTeam>();
        int start = 0;
        int end = (thisRound.size()-1);
        while(start < end){
            FantasyTeam winner = pickWinner(thisRound.get(start),thisRound.get(end));
            returnBracket.add(winner);
            start++;
            end--;
        }

        return returnBracket;
    }

    private static FantasyTeam pickWinner(FantasyTeam t1,FantasyTeam t2) {
        Random r = new Random();
        int ranWinner = r.nextInt(2)+1;
        FantasyTeam winner = null;
        if(ranWinner == 1){
            winner = t1;
        }
        else{
            winner = t2;
        }
        System.out.println("Winner of " + t1.toString() + " vs " + t2.toString() + " is " + winner);
        return winner;
    }

    public static String getInput(String message){
        System.out.println(message);
        String returnMessage = input.nextLine();
        return returnMessage;
    }
}
