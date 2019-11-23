public final class PlayerInfo{
    private final String name;
    private final double eliminations;
    private final double deaths;
    private final Role role;
    private final int healing;
    private final int blocked;
    private final OWTeam owTeam; // overwatch team (not user team)
    
    public PlayerInfo(String name,double eliminations, double deaths, Role role, int healing,int blocked, OWTeam owTeam){
        this.name = name;
        this.eliminations = eliminations;
        this.deaths = deaths;
        this.role = role;
        this.healing = healing;
        this.blocked = blocked;
        this.owTeam = owTeam;
    }
    
    //getters
    public String getName(){
        return name;
    }
    public double getEliminations(){
        return eliminations;
    }
    public double getDeaths(){
        return deaths;
    }
    public Role getRole(){
        return role;
    }
    public int getHealing(){
        return healing;
    }
    public int getBlocked(){
        return blocked;
    }
    public OWTeam getOWTeam(){
        return owTeam;
    }
    
    @Override
    public String toString(){
        return String.format("%s(%s): Elims: %d%tDeaths:%d%tHealing:%d%tDamage Blocked:%d%tCurrent Team:%d%n",name,role,eliminations,deaths,healing,blocked,owTeam);
    }
}