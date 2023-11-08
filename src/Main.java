import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Input: ");
        String string = s.nextLine(); // Метод читает строку, введенную с клавиатуры
        String result = calc(string);
        System.out.println("Output: " + result);
    }

    public static String calc(String input) {
        int index = input.indexOf('+');
        System.out.println(index);
        String[] words = input.split(" ");

        switch (words[1]) {
            case "+":
                System.out.println("Сложение");
                break;
            case "-":
                System.out.println("Вычитание");
                break;
            case "*":
                System.out.println("Умножение");
                break;
            case "/":
                System.out.println("Деление");
                break;
            default:
                System.out.println("Cтрока не является математической операцией");
        }
        return words[1];
    }


}