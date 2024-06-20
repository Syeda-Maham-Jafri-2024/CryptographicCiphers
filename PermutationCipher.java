
import java.util.ArrayList;
import java.util.List;

public class PermutationCipher{

    public static List<String> removeSpacesAndDivide(String string, int number) {
        // Remove empty spaces from the string
        string = string.replaceAll("\\s+", "");

        // Divide the string into lengths of the given number
        List<String> dividedString = new ArrayList<>();
        for (int i = 0; i < string.length(); i += number) {
            String substring = string.substring(i, Math.min(i + number, string.length()));
            // If the substring length is less than the specified length, pad with 'x'
            if (substring.length() < number) {
                StringBuilder paddedSubstring = new StringBuilder(substring);
                while (paddedSubstring.length() < number) {
                    paddedSubstring.append('x');
                }
                dividedString.add(paddedSubstring.toString());
            } else {
                dividedString.add(substring);
            }
        }

        return dividedString;
    }
//41532
    public static String swapElements(String str, int[] positions) {
        // actual jismey se positio pe ja k letter ko pick kra hai
        char[] actual = str.toCharArray();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < positions.length; i++) {
            //41532
            int pos = positions[i] - 1; // 3 ,
            char place = actual[pos];
            charArray[i] = place;
        }
        return new String(charArray);
    }
    public static String reverseElements(String str, int[] positions) {
        // actual jismey se positio pe ja k letter ko pick kra hai
        
        char[] cipherArray = str.toCharArray();
        char[] plain = str.toCharArray();
        for (int i = 0; i < positions.length; i++) {
           char reverse = cipherArray[i];// l
        //   System.out.println(reverse);
           int pos = positions[i]-1;
        //   System.out.println(pos);
           plain[pos] = reverse;
        //   for(int j = 0; j < positions.length; j++){
        //      System.out.println(plain[j]);  
        //   }
        //   System.out.println();  
        }
        return new String(plain);
    }

    public static String encryption(String string, int number, int[] positions ){
        List<String> substrings = removeSpacesAndDivide(string, number);
        
        // Print substrings
        System.out.println("Substrings:");
        for (String substring : substrings) {
            System.out.println(substring);
        }
        
        // Perform swapping and store in an array
        List<String> swappedStrings = new ArrayList<>();
        
         
        
        for (String substr : substrings) {
            String swapped = swapElements(substr, positions);
            swappedStrings.add(swapped);
        }
 
        String cipher = "";
        // Print swapped strings
        System.out.println("Swapped Strings:");
        for (String swappedString : swappedStrings) {
            cipher += swappedString;
            System.out.println(swappedString);
        }

        return cipher;
    }
    
    public static String decryption(String string, int number, int[] positions ){
        List<String> substrings = removeSpacesAndDivide(string, number);
        
        // Print substrings
        System.out.println("Substrings:");
        for (String substring : substrings) {
            System.out.println(substring);
        }
        
        // Perform swapping and store in an array
        List<String> swappedStrings = new ArrayList<>();
        
         
        
        for (String substr : substrings) {
            String swapped = reverseElements(substr, positions);
            swappedStrings.add(swapped);
        }
 
        String cipher = "";
        // Print swapped strings
        System.out.println("Swapped Strings:");
        for (String swappedString : swappedStrings) {
            cipher += swappedString;
            System.out.println(swappedString);
        }

        return cipher;
    }
    public static void main(String[] args) {
        String string = "Hello world";
        int number = 5;
        int[] positions = {4, 1, 5, 3, 2};
        
        System.out.println("Encryption:");
        String ciphertext = encryption(string, number, positions);
        System.out.println("The cipher text is: " + ciphertext);          
        System.out.println();

        System.out.println("Decryption:");
        String plaintext = decryption(ciphertext, number, positions);
        System.out.println("The plain text is: " + plaintext);
        
    }
  
  

}


























