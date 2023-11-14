import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение, содержащее два арабских числа или римских числа и арифметическую операцию (+, -, /, *). ");
        System.out.println("Данные следует вводить через пробел, например: 1 + 2 или I + II");
        System.out.println("Input: ");
        String string = s.nextLine(); // Читаем строку, введенную с клавиатуры
        String result = calc(string); // Передаем введенную строку в калькулятор
        System.out.println("Output: " + result);
    }

    public static String calc(String input) throws Exception {
        int operand1 = 0;
        int operand2 = 0;
        int isArabic = 0; // Тип входных данных в строке (0 - римские числа, 1 - арабские числа)
        int intResult = 0;
        String result = null;

        // Парсим полученную на вход строку (разделитель ' '), результаты пишем в массив
        String[] words = input.split(" ");

        // Проверяем количество элементов в строке
        if (words.length != 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию");
        }

        // Проверяем операнды на принадлежность типу Integer
        boolean isIntegerOperand1 = isNumeric(words[0].trim());
        boolean isIntegerOperand2 = isNumeric(words[2].trim());

        if (!isIntegerOperand1 && !isIntegerOperand2) {
            operand1 = romanToArabic(String.valueOf(words[0].trim())); // Выполняем преобразование операндов в число, если оба операнда не Integer
            operand2 = romanToArabic(String.valueOf(words[2].trim()));
            isArabic = 0;
        } else if (isIntegerOperand1 && isIntegerOperand2) {           // Если оба операнда Integer
            operand1 = Integer.parseInt(words[0].trim());
            operand2 = Integer.parseInt(words[2].trim());
            isArabic = 1;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        // Проверяем, что оба операнда не больше 10
        if ((operand1 < 11) && (operand2 < 11)) {

            switch (words[1]) {
                case "+":
                    intResult = operand1 + operand2;
                    break;
                case "-":
                    intResult = operand1 - operand2;
                    break;
                case "*":
                    intResult = operand1 * operand2;
                    break;
                case "/":
                    intResult = operand1 / operand2;
                    break;
                default:
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else {
            throw new Exception("Оба числа должны быть не больше 10");
        }

        if ((isArabic == 0) && (intResult > 0)) {
            result = arabicToRoman(intResult);          // Преобразуем результат в римское число
        } else if ((isArabic == 0) && (intResult < 1)) {
            throw new Exception("В римской системе нет отрицательных чисел и нуля");
        } else if (isArabic == 1){
            result = Integer.toString(intResult); // Преобразуем результат в строку
        }

        return result;
    }

    static boolean isNumeric(String str) { // Метод проверяет принадлежность строки типу Integer
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    static int romanToArabic(String romanNumeral) throws Exception { // Метод преобразует строку в арабское число

        romanNumeral = romanNumeral.toUpperCase(); // Приводим полученную строку к верхнему регистру

        int result = 0;

        RomanNumeral[] numerals = RomanNumeral.values(); // Формируем массив из элементов ENUM

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < numerals.length)) {
            RomanNumeral symbol = numerals[i];                              // Получаем i-ый элемент массива
            if (romanNumeral.startsWith(symbol.name())) {                   // Если полученная на вход строка начинается с i-го элемента
                result += symbol.getValue();                                        // Прибавляем к результату числовое значение элемента
                romanNumeral = romanNumeral.substring(symbol.name().length());      // Обрезаем начало входной строки на количество символов в i-м элементе
            } else {
                i++;                                                        // Переходим к следующему элементу массива
            }
        }

        return result;
    }

    static String arabicToRoman(int number) throws Exception { // Метод преобразует арабское число в римское

        if ((number <= 0) || (number > 4000)) {                                     // Проверяем, что на вход получено неотрицательное число не больше 4000 (ограничение для римских чисел)
            throw new Exception(number + " число меньше 0 или больше 4000)");
        }

        RomanNumeral[] romanNumerals = RomanNumeral.values(); // Формируем массив из элементов ENUM

        int i = 0;
        StringBuilder romanNumeral = new StringBuilder(); // Инициализируем переменную класса StringBuilder для использования методов для работы со строкой

        while ((romanNumerals.length > i) && (number > 0)) {
            RomanNumeral currentSymbol = romanNumerals[i];          // Получаем i-ый элемент массива

            if (currentSymbol.getValue() <= number) {               // Проверяем что числовое значение элемента меньше или равно числу, полученному на вход
                romanNumeral.append(currentSymbol.name());          // Добавляем имя i-го элемента массива к результату
                number -= currentSymbol.getValue();                 // Вычитаем из числа, полученного на вход, числовое значение i-го элемента массива
            } else {
                 i++;                                               // Переходим к следующему элементу массива
            }
        }
        return romanNumeral.toString();
    }
}



