public abstract class FantasyTeam{
    protected final String teamName;
    protected final int teamSize;
    
    public FantasyTeam(String teamName,int teamSize){
        this.teamName = teamName;
        this.teamSize = teamSize;
    }
    
    public String getTeamName(){
        return teamName;
    }
    
    public int getTeamSize(){
        return teamSize;
    }
    
    @Override
    public String toString(){
        return teamName;
    }
}
