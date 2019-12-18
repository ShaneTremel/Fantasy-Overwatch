public class FantasyTeam{
    protected final String teamName;
    protected final int seed;
    public FantasyTeam(String teamName,int seed){
        this.teamName = teamName;
        this.seed = seed;
    }
    
    public int getSeed(){
        return seed;
    }
    
    public String getTeamName(){
        return teamName;
    }
    
    @Override
    public String toString(){
        return "("+seed + ") "+teamName;
    }
}
