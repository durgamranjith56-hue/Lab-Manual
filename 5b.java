import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int specialCharacters = 0;

        // Accept string from user
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        // Check each character
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check for vowels
            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' ||
                ch == 'O' || ch == 'U') {

                vowels++;
            }

            // Check for consonants
            else if ((ch >= 'a' && ch <= 'z') ||
                     (ch >= 'A' && ch <= 'Z')) {

                consonants++;
            }

            // Check for digits
            else if (ch >= '0' && ch <= '9') {

                digits++;
            }

            // Check for special characters
            else {

                specialCharacters++;
            }
        }

        // Display the result
        System.out.println("\nResult:");
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Special Characters: " + specialCharacters);

        sc.close();
    }
}
