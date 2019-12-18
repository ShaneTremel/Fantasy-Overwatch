import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class SaveFileWriter{
    public static void writeToFile(String path, User user){
        BufferedWriter writer = null;
        List<PlayerInfo> userTeam = new ArrayList<PlayerInfo>(6);;
        List<String> userTeamNames = new ArrayList<String>(6);;
        File file = new File(path);
        userTeam = user.getTeam();
        for(PlayerInfo n : userTeam){
            userTeamNames.add(n.getName());
        }
        try{ 
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(path, false)); 
            String line = String.join(",",user.getUserName(),userTeamNames.get(0),userTeamNames.get(1),userTeamNames.get(2),userTeamNames.get(3),userTeamNames.get(4),userTeamNames.get(5));
            writer.write(line);
            writer.close();
            System.out.printf("%s saved successfully!%n",user.getUserName());
        }
        catch(IOException e){System.out.printf("Couldn't save %s.%n",user.getUserName());}
        catch(Exception e){System.out.printf("Something went wrong with saving %s.%n",user.getUserName());}
    }
}