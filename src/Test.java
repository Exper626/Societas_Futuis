public class Test {
    public static void main(String[] args) {
        Register register = new Register();
        ClubFunc clubFunc = new ClubFunc();

        try{
            register.selectClub();
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
}
