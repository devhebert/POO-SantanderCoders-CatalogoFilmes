package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filme {
    private String nome;
    private String dataLancamento = "00-00-0000";
    private double orcamento = 0.0;
    private String descricao = "Exemplo descrição";
    private Diretor diretor = new Diretor("Exemplo nome diretor");
    private List<Ator> atores = new ArrayList<>();

    public Filme() {}

    public Filme(String nome, String dataLancamento, double orcamento, String descricao, Diretor diretor) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.descricao = descricao;
        this.diretor = diretor != null ? diretor : new Diretor("Exemplo");
        this.atores = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Diretor getDiretor() {return diretor;}

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public void adicionarAtor(Ator ator) {
        atores.add(ator);
    }

    public String getNome() {
        return nome;
    }
}


