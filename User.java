import java.util.List;
import java.util.ArrayList;
public class User implements AbstractUser{
    private static String userName;
    private static List<PlayerInfo> userTeam = new ArrayList<PlayerInfo>(6);
    public User(String userName){
        this.userName = userName;
    }
    public User(String userName,List<PlayerInfo> userTeam){
        this.userName = userName;
        this.userTeam = userTeam;
    }
    public List<PlayerInfo> getTeam(){
        return userTeam;
    }
    public String getUserName(){
        return userName;
    }
}