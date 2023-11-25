public class Test {
    public static void main(String[] args) {
        Advisor x = new Advisor("xx", "1234", "john", "doe", "123456", "123@123");

        Student y = new Student("xxx", "1234", "yah" , "yeet", "123456" , " 2021/21/2", "2021");




        System.out.println(x.getFirst_name() + " " + x.getLast_name());
        System.out.println(y.getFirst_name() + " " + y.getLast_name());
    }
}
