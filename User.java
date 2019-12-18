import java.util.List;
import java.util.ArrayList;
public class User implements AbstractUser{
    private String userName;
    private List<PlayerInfo> userTeam = new ArrayList<PlayerInfo>(6);
    private int draftCount;
    private int draftCountTank;
    private int draftCountDamage;
    private int draftCountHealer;
    public User(String userName){
        this(userName,new ArrayList<PlayerInfo>(6));
    }

    public User(String userName,List<PlayerInfo> userTeam){
        this.userName = userName;
        this.userTeam = userTeam;
        draftCount = 6;
        draftCountTank = 2;
        draftCountDamage = 2;
        draftCountHealer = 2;
    }

    public List<PlayerInfo> getTeam(){
        return userTeam;
    }

    public String getUserName(){
        return userName;
    }

    public int getTankCount(){
        return draftCountTank;
    }

    public int getHealerCount(){
        return draftCountHealer;
    }

    public int getDamageCount(){
        return draftCountDamage;
    }
    
    @Override
    public String toString(){
        return userName;
    }

    public List<PlayerInfo> draftNewPlayer(String playerName,List<PlayerInfo> draftingDatabase){
        boolean validPlayer = false;
        PlayerInfo chosenPlayer = null;
        for(PlayerInfo p : userTeam){
            if (playerName.equalsIgnoreCase(p.getName())){
                System.out.println(p.getName()+" is already on your team!");
                return null;
            }
        }
        for(PlayerInfo p : draftingDatabase){
            if (playerName.equalsIgnoreCase(p.getName())){
                validPlayer = true;
                switch(p.getRole()){
                    case Healer:
                    if(draftCountHealer > 0){
                        userTeam.add(p);
                        chosenPlayer = p;
                        System.out.println(p.getName()+" has been added to your team!");
                        draftCountHealer--; 
                        draftCount--;
                    }else{System.out.println("You already have two healers!");return null;}
                    break;
                    case Damage:
                    if(draftCountDamage > 0){
                        userTeam.add(p);
                        chosenPlayer = p;
                        System.out.println(p.getName()+" has been added to your team!");
                        draftCountDamage--;   
                        draftCount--;
                    }else{System.out.println("You already have two damages!");return null;}
                    break;
                    case Tank:
                    if(draftCountTank > 0){
                        userTeam.add(p);
                        chosenPlayer = p;
                        System.out.println(p.getName()+" has been added to your team!");
                        draftCountTank--;   
                        draftCount--;
                    }else{System.out.println("You already have two tanks!");return null;}
                    break;
                    default:
                    System.out.println("This should not happen");
                }
            }
        }
        if(!validPlayer){
            System.out.println("Not a valid player!");
            return null;
        }
        if(draftingDatabase.remove(chosenPlayer)){
            return draftingDatabase;
        }
        return null;
    }
}