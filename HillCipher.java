import java.util.Scanner;

public class HillCipher {
    static final int MOD = 26;

    // Function to calculate the modular inverse of a number
    static int modInverse(int a) {
        for (int x = 1; x < MOD; x++) {
            if (((a % MOD) * (x % MOD)) % MOD == 1) {
                return x;
            }
        }
        return 1;
    }

    //calculatig the determinant of a 2x2 key matrix 
    static int determinant(int[][] keyMatrix) {
        return (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % MOD;
    }

    // calculating the adjoint of a 2x2 matrix
    static int[][] adjoint(int[][] keyMatrix) {
        int[][] adj = new int[2][2];
        adj[0][0] = keyMatrix[1][1];
        adj[0][1] = -keyMatrix[0][1];
        adj[1][0] = -keyMatrix[1][0];
        adj[1][1] = keyMatrix[0][0];
        return adj;
    }

    //calculating the key inverse matrix
    static int[][] keyInverse(int[][] keyMatrix) {
        int det = determinant(keyMatrix);
        int detInv = modInverse(det);
        int[][] adj = adjoint(keyMatrix);
        int[][] invKeyMatrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                invKeyMatrix[i][j] = ((adj[i][j] % MOD) * (detInv % MOD)) % MOD;
                if (invKeyMatrix[i][j] < 0) {
                    invKeyMatrix[i][j] += MOD;
                }
            }
        }
        return invKeyMatrix;
    }

    static String encryption(String message, int[][] keyMatrix) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < message.length(); i += 2) {
            int[][] vector = new int[2][1];
            vector[0][0] = message.charAt(i) - 'A';
            vector[1][0] = i + 1 < message.length() ? message.charAt(i + 1) - 'A' : 'X' - 'A'; // Padding if necessary
            int[][] result = multiplyMatrix(keyMatrix, vector);
            encrypted.append((char) (result[0][0] % MOD + 'A'));
            encrypted.append((char) (result[1][0] % MOD + 'A'));
        }
        return encrypted.toString();
    }

    static String decryption(String encrypted, int[][] keyMatrix) {
        int[][] invKeyMatrix = keyInverse(keyMatrix);
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i += 2) {
            int[][] vector = new int[2][1];
            vector[0][0] = encrypted.charAt(i) - 'A';
            vector[1][0] = encrypted.charAt(i + 1) - 'A';
            int[][] result = multiplyMatrix(invKeyMatrix, vector);
            decrypted.append((char) (result[0][0] % MOD + 'A'));
            decrypted.append((char) (result[1][0] % MOD + 'A'));
        }
        return decrypted.toString();
    }

    //multiplying two given matrices
    static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int[][] result = new int[2][1];
        result[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
        result[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the key matrix from the user
        System.out.println("Enter the 2x2 key matrix:");
        int[][] keyMatrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine();

        System.out.println("Enter the plaintext you want to encrypt:");
        String message = scanner.nextLine();

        String encrypted = encryption(message, keyMatrix);
        System.out.println("The cipher text after encryption is: " + encrypted);

        String decrypted = decryption(encrypted, keyMatrix);
        System.out.println("The plain text after decryption is: " + decrypted);

        scanner.close();
    }
}
