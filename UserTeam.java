public class UserTeam extends FantasyTeam{
    
    public UserTeam(String teamName,int teamSize){
        super(teamName,teamSize);
    }
    
    
    
    @Override
    public String toString(){
        return teamName;
    }
}