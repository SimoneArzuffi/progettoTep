public class Personaggio {

    private String nome;
    private String genere;
    private String coloreCapelli;
    private Boolean occhiali;
    private boolean cappello;
    private boolean barba;

    // Costruttore
    public Personaggio(String nome, String genere, String coloreCapelli, boolean barba,boolean occhiali, boolean cappello) {
        this.nome = nome;
        this.genere = genere;
        this.coloreCapelli = coloreCapelli;
        this.barba = barba;
        this.occhiali = occhiali;
        this.cappello = cappello;

    }

    // Metodi getter per ottenere le informazioni sul personaggio
    public String getNome() {
        return nome;
    }

    public String getGenere() {
        return genere;
    }

    public String getColoreCapelli() {
        return coloreCapelli;
    }

    public boolean getOcchiali() {
        return occhiali;
    }

    public boolean getCappello() {
        return cappello;
    }

    public boolean getBarba() {
        return barba;
    }

    // Metodo toString per ottenere una rappresentazione stringa del personaggio
    @Override
    public String toString() {
        return "Personaggio{" +
                "nome='" + nome + '\'' +
                ", genere='" + genere + '\'' +
                ", coloreCapelli='" + coloreCapelli + '\'' +
                ", occhiali='" + occhiali + '\'' +
                ", cappello='" + cappello + '\'' +
                ", barba='" + barba + '\'' +
                '}';
    }
}
