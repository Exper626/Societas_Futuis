public class Test {
    public static void main(String[] args) {
        Login login = new Login();

        try{

            Advisor loggedAdvisor = (Advisor) login.loginAuth("a0002","pass123");
            System.out.println(loggedAdvisor.getEmail());
            if (login.getUserType().equals("a")){
                System.out.println("advisor boi");
            }

        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
}
