package Model;

import java.util.*;
public class Carta {
    private String numCarta;
    private Date dataCreazione;
    private Date dataScadenza;
    private boolean block;
    private String cvv;
    private float credito;
    private int idTitolare;

    public Carta() {}

    public Carta(String numCarta, Date dataCreazione, Date dataScadenza, boolean block, String cvv, float credito, int idTitolare) {
        this.numCarta = numCarta;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.block = block;
        this.cvv = cvv;
        this.credito = credito;
        this.idTitolare = idTitolare;
    }

    public String getNumCarta() {
        return numCarta;
    }

    public void setNumCarta(String numCarta) {
        this.numCarta = numCarta;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }

    public int getIdTitolare() {
        return idTitolare;
    }

    public void setIdTitolare(int idTitolare) {
        this.idTitolare = idTitolare;
    }
}


