public class CeaserCipher {
    public static void main(String[] args){
        String text = "ATTACKATONCE";
        int key = 4;
        System.out.println("Text  : " + text);
        System.out.println("Shift : " + key);
        System.out.println("Cipher: " + encryption(text, key, AlphabetArray()));
        System.out.println("plain: " + decryption(encryption(text, key, AlphabetArray()), key, AlphabetArray()));

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
 
    public static String encryption(String plaintext,  int key, char[] alphabetArray){
        plaintext = plaintext.toLowerCase();

        String cipher = "";
 
        for (int i=0; i<plaintext.length(); i++)
        {   int alphid = 0;
            if (Character.isLetter(plaintext.charAt(i))){
                while (plaintext.charAt(i) != alphabetArray[alphid]){
                alphid++;
                }
                int ch = ((alphid+ key) % 26 );
                cipher = cipher + alphabetArray[ch];
                alphid = 0;
            }
            else {
                cipher = cipher + plaintext.charAt(i);
            }
        }
            
        
        return cipher;

    }
    public static String decryption(String ciphertext,  int key, char[] alphabetArray){

        ciphertext = ciphertext.toLowerCase();

        String plain = "";
        for (int i=0; i<ciphertext.length(); i++)
        {   int alphid = 0;
            if (Character.isLetter(ciphertext.charAt(i))){
                while (ciphertext.charAt(i) != alphabetArray[alphid]){
                alphid++;
                }
                int ch = ((alphid - key ) % 26 );
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