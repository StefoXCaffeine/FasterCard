package Utils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

//Classe utility che genera un hashing crittografico SHA-256 della stringa passata come input.
//Viene utilizzata per effettuare l'hashing delle password(di cui è stato effettuato un primo hash MD5 lato frontend)prima di salvarle nel database
public class PasswordEncrypt {
    String password;

    public PasswordEncrypt(){}

    public String getPassword(){

        return this.password;
    }

    public void setPassword(String password){

        this.password=password;
    }

    public String hashing(String password){
        try {
            //Oggetto MessageDigest che implementa l'algoritmo passato come parametro
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Codifica la stringa in una sequenza di byte secondo il charset passato come parametro
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            //Ogni carattere dell'hash viene trasformato come un integer unsigned 16 e lo appende alla stringa
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
