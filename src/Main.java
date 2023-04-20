import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи выражение: ");
        System.out.println(calc(scanner.nextLine()));
    }
    public static String calc(String input) {

        HashMap<String, Integer> hashMapWithRimNumber = hashMapWithRimNumber();

        String returnStr = null;
        int firstArg = 0;
        int secondArg = 0;
        boolean firstArgIsArab;
        boolean secondArgIsArab;
        String[] inputStrToStrings = input.split(" ");

        if (inputStrToStrings.length == 3) {

            try {
                firstArg = Integer.parseInt(inputStrToStrings[0]);
                firstArgIsArab = true;
            } catch (NumberFormatException numberFormatException) {
                firstArgIsArab = false;
            }
            try {
                secondArg = Integer.parseInt(inputStrToStrings[2]);
                secondArgIsArab = true;
            } catch (NumberFormatException numberFormatException) {
                secondArgIsArab = false;
            }

            if (!firstArgIsArab && !secondArgIsArab) {

                int a = 0;
                int b = 0;
                try {
                    a = hashMapWithRimNumber.get(inputStrToStrings[0]);
                    b = hashMapWithRimNumber.get(inputStrToStrings[2]);
                } catch (NullPointerException e) {
                    returnStr = "Недопустимые числа";
                }
                int resultAb = Integer.parseInt(numberOperation(inputStrToStrings[1], a, b));

                if (resultAb <= 0) {
                    returnStr = "Недопустимая операция для этих чисел";
                } else {
                    returnStr = arabicToRoman(resultAb);
                }

            } else if (firstArgIsArab && secondArgIsArab) {

                if ((firstArg < 11 && firstArg > 0) && (secondArg < 11 && secondArg > 0)) {
                    returnStr = numberOperation(inputStrToStrings[1], firstArg, secondArg);

                } else {
                    returnStr = "Недопустимые числа";
                }

            }

        } else {
            returnStr = "Строка введена некорректно";

        }
        return returnStr;
    }

    public static HashMap<String, Integer> hashMapWithRimNumber() {

        HashMap<String, Integer> hashMapWithRimNumber = new HashMap<>();
        hashMapWithRimNumber.put("I", 1);
        hashMapWithRimNumber.put("II", 2);
        hashMapWithRimNumber.put("III", 3);
        hashMapWithRimNumber.put("IV", 4);
        hashMapWithRimNumber.put("V", 5);
        hashMapWithRimNumber.put("VI", 6);
        hashMapWithRimNumber.put("VII", 7);
        hashMapWithRimNumber.put("VIII", 8);
        hashMapWithRimNumber.put("IX", 9);
        hashMapWithRimNumber.put("X", 10);

        return hashMapWithRimNumber;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static String numberOperation(String operation, int a, int b) {

        String result;
        switch (operation) {
            case "*" -> {
                return result = String.valueOf(a * b);
            }
            case "+" -> {
                return result = String.valueOf(a + b);
            }
            case "-" -> {
                return result = String.valueOf(a - b);
            }
            case "/" -> {
                if (b == 0) {
                    result = "На 0 делить нельзя";
                } else {
                    int resultDel = (int) a / b;
                    result = String.valueOf(resultDel);
                }
                return result;
            }
            default -> {
                result = "Эта операция не поддерживается";
                return result;
            }
        }
    }
}