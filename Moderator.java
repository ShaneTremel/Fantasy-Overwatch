public class Moderator implements AbstractUser{
    private String userName;
    private String hashedPassword;
    public Moderator(String userName,String password){
        this.userName = userName;
    }
    @Override
    public String getUserName(){
        return userName;
    }
}