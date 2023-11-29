public class Test {
    public static void main(String[] args) {
        Register register = new Register();
        ClubFunc clubFunc = new ClubFunc();
        MenuFunc menuFunc = new MenuFunc();

        try {
            System.out.println(menuFunc.isValidGrade("14"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
