package Model;

public class Utente {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private int userType;
    private int negBlock;

    public Utente() {
    }

    public Utente(int id, String nome, String cognome, String email, String password, int userType, int negBlock) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.negBlock = negBlock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getNegBlock() {
        return negBlock;
    }

    public void setNegBlock(int negBlock) {
        this.negBlock = negBlock;
    }
}
