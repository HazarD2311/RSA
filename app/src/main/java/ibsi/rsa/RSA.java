package ibsi.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private BigInteger one = new BigInteger("1");
    private SecureRandom random = new SecureRandom();

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    private String message;
    private String encryptMessage;
    private String decryptMessage;

    // generate an N-bit (roughly) public and private key
    RSA(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");     // common value in practice = 2^16 + 1
        privateKey = publicKey.modInverse(phi);

        encryptMessage = "";
        decryptMessage = "";
    }


    public String encrypt() {
        int length = message.length();
        int letterCode;
        BigInteger bigLetterEncrypt;
        for (int i = 0; i < length; i++) {
            letterCode = message.charAt(i);
            bigLetterEncrypt = new BigInteger(new String(String.valueOf(letterCode)));

            encryptMessage = encryptMessage + bigLetterEncrypt.modPow(publicKey, modulus).toString() + "-";
        }
        return encryptMessage;
    }

    public String decrypt() {
        char buff;
        int i = 0;
        int length = encryptMessage.length();
        String toBigInteger = "";
        BigInteger bigInteger;
        int letterCode;
        char letter;
        while (i < length) {
            while (encryptMessage.charAt(i) != '-') {
                toBigInteger += encryptMessage.charAt(i);
                i++;
            }
            bigInteger = new BigInteger(toBigInteger);
            letterCode = bigInteger.modPow(privateKey, modulus).intValue(); //расшифровыем код буквы
            letter = (char)letterCode; //получаем саму букву
            decryptMessage += letter; //добавляю еще одну найденную букву к другим расшифрованным

            toBigInteger = ""; //стираем старые промежуточные данные

            i++;
        }
        return decryptMessage;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public SecureRandom getRandom() {
        return random;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEncryptMessage() {
        return encryptMessage;
    }

    public void setEncryptMessage(String encryptMessage) {
        this.encryptMessage = encryptMessage;
    }

    public String getDecryptMessage() {
        return decryptMessage;
    }
}
