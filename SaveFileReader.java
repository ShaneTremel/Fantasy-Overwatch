import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SaveFileReader{
    public static User readFromFile(String path){
        BufferedReader reader = null;
        User user = null;
        String userName = null;
        List<PlayerInfo> userTeam = null;
        try{ 
            reader = new BufferedReader(new FileReader(path)); String line = reader.readLine();
            String[] data = line.split(",");
            userName = data[0];
            //get userTeam as Strings of names
            user = new User(userName,userTeam);
            reader.close();
        }
        catch(FileNotFoundException e){System.out.println("Could not read from save file.");}
        catch(IOException e){System.out.println("Error while reading from save file.");}
        catch(NumberFormatException e){System.out.println("Save file not valid.");}
        catch(Exception e){System.out.println("Something went wrong");}
        finally{return user;}
    }
}