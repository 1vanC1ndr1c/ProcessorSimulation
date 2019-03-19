package project.logic;

public class HexHandler {
    //code found here:
    //https://stackoverflow.com/questions/8548586/adding-binary-numbers

    public static String addNumbers(String number1, String number2) {
        Integer num1 = Integer.parseInt(number1, 16);
        Integer num2 = Integer.parseInt(number2, 16);
        Integer result = num1 + num2;
        return resultAsString(result);
    }

    public static String andNumbers(String number1, String number2) {
        //check:
        //https://www.rapidtables.com/calc/math/binary-calculator.html
        Integer num1 = Integer.parseInt(number1, 16);
        Integer num2 = Integer.parseInt(number2, 16);
        Integer result = num1 & num2;
        return resultAsString(result);
    }

    public static String shift(String number) {
        Integer num = Integer.parseInt(number, 16);
        Integer result = num >> 1;
        return resultAsString(result);
    }

    public static String complement(String number) {
        Integer num = Integer.parseInt(number, 16);
        Integer result = ~num;
        return resultAsString(result);
    }

    public static String increaseValueByOne(String number1) {
        Integer num1 = Integer.parseInt(number1, 16);
        Integer result = num1 + 1;
        String resultAsString = Integer.toHexString(result);
        while (resultAsString.length() < 6)
            resultAsString = "0" + resultAsString;
        return resultAsString;
    }

    private static String resultAsString(Integer result) {
        String resultAsString = Integer.toHexString(result);
        while (resultAsString.length() < 8)
            resultAsString = "0" + resultAsString;
        return resultAsString;
    }
}
