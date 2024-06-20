import java.util.Scanner;

public class AffineCipher{
    public static void main(String[] args) {
        System.out.print("Enter a : ");  
        Scanner sc = new Scanner(System.in); 
        int a = sc.nextInt();

        System.out.print("Enter  b: ");   
        int b = sc.nextInt();

        String text = "AFFINE CIPHER";
        System.out.println("Cipher: " + encryption(text, AlphabetArray(), a , b ));
        System.out.println("Plain: " + decryption(encryption(text, AlphabetArray(), a , b ), AlphabetArray(), a , b ));
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

  public static int gcd(int a, int m)
  { int i;
      if (a < m)
          i = a;
      else
          i = m;
      for (i = i; i > 1; i--) {
          if (a % i == 0 && m % i == 0)
              return i;
      }
      return 1;
  }
  static int modInverse(int A, int M)
  { for (int X = 1; X < M; X++)
          if (((A % M) * (X % M)) % M == 1)
              return X;
      return 1;
  }
  public static String encryption(String plaintext, char[] alphabetArray, int a, int b ){
    plaintext = plaintext.toLowerCase();
    String cipher = "";
    if (gcd(a,26 )==1){
        for (int i=0; i<plaintext.length(); i++)
        {   int j = 0;
            if (Character.isLetter(plaintext.charAt(i))){
                while (plaintext.charAt(i) != alphabetArray[j]){
                    j++;
                   }
                   int ch = (((a * j) + b ) % 26 );
                   cipher = cipher + alphabetArray[ch];
                   j = 0;
            }
            else {
                cipher = cipher + plaintext.charAt(i);}
        }
    }
    else{
        System.out.println(" a and m are not coprime ,their gcd isnt 1. ");
    }
    return cipher;
  }
  public static String decryption(String ciphertext, char[] alphabetArray, int a, int b ){
    ciphertext = ciphertext.toLowerCase();
    
    String plain = "";
    if (gcd(a,26 )==1){
        int modularinv = modInverse(a, 26);
        System.out.println(modularinv);
        for (int i=0; i<ciphertext.length(); i++)
        {   int j = 0;
            if (Character.isLetter(ciphertext.charAt(i))){
                while (ciphertext.charAt(i) != alphabetArray[j]){
                    j++;
                   }
                   int ch = ((modularinv * (j - b) ) % 26 );
                   while(ch<0){
                    ch += 26;
                   }
                   plain = plain + alphabetArray[ch];
                   j = 0;
            }
            else {
                plain = plain + ciphertext.charAt(i);
            }
        }
    }
    else{
        System.out.println(" a and m are not coprime ,their gcd isnt 1. ");
    }
    return plain;
  }

  }
