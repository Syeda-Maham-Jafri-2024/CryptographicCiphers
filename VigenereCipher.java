public class VigenereCipher {
    public static void main(String[] args){
        String text = "GEEKSFORGEEKS";
        String key = "AYUSH";
        System.out.println("Text  : " + text);
        System.out.println("Shift : " + RepeatKey(text, key));
        System.out.println("Cipher: " + encryption(text, RepeatKey(text, key), AlphabetArray()));
        System.out.println("plain: " + decryption(encryption(text, RepeatKey(text, key), AlphabetArray()), RepeatKey(text, key), AlphabetArray()));

    }

   public static char[] AlphabetArray(){
      
            char[] alphabetArray = new char[26];
    
            char currentChar = 'a';
            for (int i = 0; i < 26; i++) {
                alphabetArray[i] = currentChar;
                currentChar++;
            }
          
        return alphabetArray;
    }
 
    public static String RepeatKey(String text, String key) { 
        key = key.toLowerCase();
        StringBuilder repeatedKey = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar != ' ') {
                repeatedKey.append(key.charAt(keyIndex));
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                // Append a space if the current character in text is a space
                repeatedKey.append(' ');
            }
        }
         return repeatedKey.toString();
        
    }
//GCYCZFMLYLEIM
    public static String encryption(String plaintext, String key, char[] alphabetArray){
        plaintext = plaintext.toLowerCase();

        String cipher = "";
 
        for (int i=0; i<plaintext.length(); i++)
        {   int textid = 0,alphid = 0, keyid = 0;
            if (Character.isLetter(plaintext.charAt(i))){
                while (plaintext.charAt(i) != alphabetArray[alphid]){
                textid++;alphid++;
                }
                alphid = 0;
                while (key.charAt(i) != alphabetArray[alphid]){
                keyid++;alphid++;
                }
                int ch = ((textid + keyid) % 26 );
                cipher = cipher + alphabetArray[ch];
                alphid = 0;
            }
            else {
                cipher = cipher + plaintext.charAt(i);
            }
        }
            
        
        return cipher;

    }
    public static String decryption(String ciphertext, String key, char[] alphabetArray){

        ciphertext = ciphertext.toLowerCase();

        String plain = "";
 
        for (int i=0; i<ciphertext.length(); i++)
        {   int textid = 0,alphid = 0, keyid = 0;
            if (Character.isLetter(ciphertext.charAt(i))){
                while (ciphertext.charAt(i) != alphabetArray[alphid]){
                textid++;alphid++;
                }
                alphid = 0;
                while (key.charAt(i) != alphabetArray[alphid]){
                keyid++;alphid++;
                }
                int ch = ((textid - keyid) % 26 );
                while(ch<0){
                    ch += 26;
                   }
                plain = plain + alphabetArray[ch];
                alphid = 0;
            }
            else {
                plain = plain + ciphertext.charAt(i);
            }
        }
        return plain;
    }
}