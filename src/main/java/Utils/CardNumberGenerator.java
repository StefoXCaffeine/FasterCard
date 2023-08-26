package Utils;
import com.mifmif.common.regex.Generex;

//Classe utility che tramite la libreria Generex permette di generare una stringa che rispetta la regex passata nel costruttore.
//Questa classe verrà utilizzata per generare il numero della carta di credito durante la fase di creazione di una carta da parte dell'admin
public class CardNumberGenerator {

    public CardNumberGenerator(){}

    public String generateCardNumber(){
        String cardNumber;
        Generex generex = new Generex("(5[1-5][0-9]{14})");
        cardNumber=generex.random();
        return cardNumber;
    }

    public String generateCvv(){
        String cvv;
        Generex generex = new Generex("[0-9]{3}");
        cvv = generex.random();
        return cvv;
    }

}
