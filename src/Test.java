public class Test {
    public static void main(String[] args) {
        Register register = new Register();
        ClubFunc clubFunc = new ClubFunc();

        try{
            clubFunc.viewAllClubs();
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
}
