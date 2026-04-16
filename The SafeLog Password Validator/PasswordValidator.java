import java.util.Scanner;

public class PasswordValidator {

    public static boolean checkPassword(String password) {

        boolean hasUpper = false;
        boolean hasDigit = false;

        if (password.length() < 8) {
            System.out.println("Password too short (minimum 8 characters required)");
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            }

            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }

        if (!hasUpper) {
            System.out.println("Missing uppercase letter");
        }

        if (!hasDigit) {
            System.out.println("Missing digit");
        }

        return hasUpper && hasDigit;
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            String password;

            System.out.println("=== SafeLog Password Validator ===");

            while (true) {
                System.out.print("Enter password: ");
                password = sc.nextLine();

                if (checkPassword(password)) {
                    System.out.println("Password accepted!");
                    break;
                } else {
                    System.out.println("Try again\n");
                }
            }
        }
    }
}