public class Test {
    public static void main(String[] args) {
        Register register = new Register();

        try{
            register.register("studentPass123", "Emma", "Johnson", "555-1111", "1999-05-02", "2");


        }
        catch (Exception e ){
            System.out.println(e);
        }
    }
}
