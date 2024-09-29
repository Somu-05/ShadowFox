package Beginner;
import java.util.Scanner;
import java.lang.Math;

public class Calculator {

    // Basic arithmetic operations
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    // Scientific operations
    public static double squareRoot(double a) {
        if (a < 0) {
            throw new ArithmeticException("Cannot calculate the square root of a negative number.");
        }
        return Math.sqrt(a);
    }

    public static double exponent(double a, double b) {
        return Math.pow(a, b);
    }

    // Temperature conversion
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    // Currency conversion (Assuming static conversion rate for simplicity)
    public static double usdToInr(double usd) {
        double rate = 82.50; // Example conversion rate
        return usd * rate;
    }

    public static double inrToUsd(double inr) {

        double rate = 82.50;
        return inr / rate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalc = true;

        while (continueCalc) {
            System.out.println("Enhanced Calculator Menu:");
            System.out.println("1. Basic Arithmetic Operations");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Basic Arithmetic Operations");
                    System.out.print("Enter first number: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();

                    System.out.println("Choose operation: 1) Add 2) Subtract 3) Multiply 4) Divide");
                    int operation = scanner.nextInt();
                    try {
                        double result;
                        switch (operation) {
                            case 1:
                                result = add(num1, num2);
                                System.out.println("Result: " + result);
                                break;
                            case 2:
                                result = subtract(num1, num2);
                                System.out.println("Result: " + result);
                                break;
                            case 3:
                                result = multiply(num1, num2);
                                System.out.println("Result: " + result);
                                break;
                            case 4:
                                result = divide(num1, num2);
                                System.out.println("Result: " + result);
                                break;
                            default:
                                System.out.println("Invalid operation.");
                        }
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Scientific Calculations");
                    System.out.println("1) Square Root 2) Exponentiation");
                    int sciOperation = scanner.nextInt();
                    System.out.print("Enter number: ");
                    double number = scanner.nextDouble();

                    try {
                        switch (sciOperation) {
                            case 1:
                                System.out.println("Square root: " + squareRoot(number));
                                break;
                            case 2:
                                System.out.print("Enter exponent: ");
                                double exp = scanner.nextDouble();
                                System.out.println("Exponentiation: " + exponent(number, exp));
                                break;
                            default:
                                System.out.println("Invalid scientific operation.");
                        }
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Unit Conversions");
                    System.out.println("1) Celsius to Fahrenheit 2) Fahrenheit to Celsius 3) USD to INR 4) INR to USD");
                    int conversionChoice = scanner.nextInt();
                    System.out.print("Enter value: ");
                    double value = scanner.nextDouble();

                    switch (conversionChoice) {
                        case 1:
                            System.out.println("Celsius to Fahrenheit: " + celsiusToFahrenheit(value));
                            break;
                        case 2:
                            System.out.println("Fahrenheit to Celsius: " + fahrenheitToCelsius(value));
                            break;
                        case 3:
                            System.out.println("USD to INR: " + usdToInr(value));
                            break;
                        case 4:
                            System.out.println("INR to USD: " + inrToUsd(value));
                            break;
                        default:
                            System.out.println("Invalid conversion choice.");
                    }
                    break;

                case 4:
                    continueCalc = false;
                    System.out.println("Exiting Calculator.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
    }
}
