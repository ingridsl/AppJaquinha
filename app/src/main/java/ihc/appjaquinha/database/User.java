package ihc.appjaquinha.database;

import java.util.List;

public class User {

    private String email;
    private String username;
    private String nascimento;
    private String sexo;
    private float peso;
    private int altura;
    private Diario diario;
    private Geladeira geladeira;
    private Objetivos objetivos;
    private Restricoes restricoes;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        this.diario = new Diario();
        this.geladeira = new Geladeira();
        this.objetivos = new Objetivos();
        this.restricoes = new Restricoes();
    }

    public User(String email, String username, String nascimento, String sexo, float peso, int altura) {
        this.email = email;
        this.username = username;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.diario = new Diario();
        this.geladeira = new Geladeira();
        this.objetivos = new Objetivos();
        this.restricoes = new Restricoes();
    }

    public void SetConfiguracoes(String username, String nascimento, String sexo, float peso, int altura) {
        this.username = username;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
    }

    public void SetRestricoes(boolean amendoim, boolean leite, boolean mar,
                              boolean soja, boolean trigo, boolean lactose, boolean gluten) {
        if(restricoes == null) restricoes = new Restricoes(amendoim, leite, mar, soja, trigo, lactose, gluten);
        else restricoes.SetRestricoes(amendoim, leite, mar, soja, trigo, lactose, gluten);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Geladeira getGeladeira() {
        return geladeira;
    }

    public void setGeladeira(Geladeira geladeira) {
        this.geladeira = geladeira;
    }

    public Objetivos getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(Objetivos objetivos) {
        this.objetivos = objetivos;
    }

    public Restricoes getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(Restricoes restricoes) {
        this.restricoes = restricoes;
    }
}