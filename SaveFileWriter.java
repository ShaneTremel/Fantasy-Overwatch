import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveFileWriter{
    public static void writeToFile(String path, User user){
        BufferedWriter writer = null;
        System.out.println("Saving...");
        try{ 
            writer = new BufferedWriter(new FileWriter(path, false)); 
            //save userTeam as just the names of the team members
            String line = String.join(",",user.getUserName());
            writer.write(line);
            writer.close();
            System.out.println("Save Complete.");
        }
        catch(IOException e){System.out.println("Couldn't save file.");}
        catch(Exception e){System.out.println("Something went wrong.");}
    }
}