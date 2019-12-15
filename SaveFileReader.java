import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class SaveFileReader{
    public static User readFromFile(String path,List<PlayerInfo> database){
        BufferedReader reader = null;
        User user = null;
        String userName = "";
        List<PlayerInfo> userTeam = new ArrayList<PlayerInfo>(6);;
        try{ 
            reader = new BufferedReader(new FileReader(path)); String line = reader.readLine();
            String[] data = line.split(",");
            userName = data[0];
            for(int i =1; i<7; i++){
                for(PlayerInfo p : database){
                    if (data[i].equalsIgnoreCase(p.getName())){
                        userTeam.add(p);
                    }
                }
            }
            user = new User(userName,userTeam);
            reader.close();
        }
        catch(FileNotFoundException e){System.out.println("Can't find save file.");}
        catch(IOException e){System.out.println("Error while reading from save file.");}
        catch(NumberFormatException e){System.out.println("Save file not valid.");}
        catch(Exception e){System.out.println("Something went wrong");}
        finally{return user;}
    }
}