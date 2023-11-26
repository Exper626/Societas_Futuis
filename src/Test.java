public class Test {
    public static void main(String[] args) {
        Login login = new Login();

        try{
            login.loginAuth("a0002","pass123");
        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
}
