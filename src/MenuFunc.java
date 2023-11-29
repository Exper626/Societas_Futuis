import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuFunc {

    //validate password
    public boolean isValidPassword(String password){
        //password format
        String regex = "^(?=.*[0-9])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{5,20}$";

        Pattern p = Pattern.compile(regex);

        String msg = "Invalid password. Please try again";

        if (password == null){
            System.out.println(msg + "(Empty password)");
            return false;
        }

        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();

    }

    //validate date
    public boolean isValidDate(String dateStr){
        //date correct format
        String dateFormat = "yyyy-mm-dd";

        //validate format
        //create simpleDateFormat obj with the correct date format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        //set lenient mode to false to apply strict parsing rules
        simpleDateFormat.setLenient(false);
        try{
            // parse the user input to date object
            Date date = simpleDateFormat.parse(dateStr);

            //validate date, month and year
            //get year, month, day values
            String[] dateData = dateStr.split("-");
            int year = Integer.parseInt(dateData[0]);
            int month = Integer.parseInt(dateData[1]);
            int day = Integer.parseInt(dateData[2]);

            //Check year range (adjust the range as needed)
            if (year < 1900 || year > 2100) {
                return false;
            }

            //Check month range (1 to 12)
            if (month < 1 || month > 12) {
                return false;
            }

            // check days in month based on month and year
            if (day < 1 || day > daysInMonth(year, month)) {
                return false;
            }

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean isValidGrade(String grade){
        int gradeInt = Integer.parseInt(grade);
        if (gradeInt > 0 && gradeInt <14){
            System.out.println("valid grade");
            return true;
        }
        return false;
    }

    public UserType getUserType(String username){
        String userType;
        if (username.charAt(0) == 'a'){
            return UserType.ADVISOR;
        } else if (username.charAt(0) == 's') {
            return UserType.STUDENT;
        }else {
            return UserType.INVALID;
        }
    }

    //check if a year is a leap year or not
    private boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //get days for each month
    private int daysInMonth(int year, int month){
        switch (month){
            //months with 31 days
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;

            //months with 30 days
            case 4: case 6: case 9: case 11:
                return 30;

            //february
            case 2:
                if (isLeapYear(year)){
                    return 29;
                }else {
                    return 28;
                }

            default:
                return -1;
        }
    }

}
