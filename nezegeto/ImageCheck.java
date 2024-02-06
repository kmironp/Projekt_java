// Java program to check valid
// image file extension using regex

import java.util.regex.*;

class ImageCheck {

    // Function to validate image file extension .
    public static boolean imageFile(String str) {
        // Regex to check valid image file extension.
        String regex = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given string
        // and regular expression.
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    // Driver code
    public static void main(String args[]) {

        // Test Case 1:
        String str1 = "abc.png";
        System.out.println(imageFile(str1));

        // Test Case 2:
        String str2 = "im.jpg";
        System.out.println(imageFile(str2));

        // Test Case 3:
        String str3 = ".gif";
        System.out.println(imageFile(str3));

        // Test Case 4:
        String str4 = "abc.mp3";
        System.out.println(imageFile(str4));

        // Test Case 5:
        String str5 = " .jpg";
        System.out.println(imageFile(str5));
    }
}
