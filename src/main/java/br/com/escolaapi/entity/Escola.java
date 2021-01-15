package br.com.escolaapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import javax.persistence.OneToMany;

@Entity
public class Escola
{
    @ApiModelProperty(value = "Código da escola")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEscola;

    @ApiModelProperty(value = "Nome da escola")
    @Column(nullable = false)
    private String nome;
    
    @OneToMany(cascade = {
    CascadeType.ALL
    })
    @JoinTable(name = "escola_turma",
    joinColumns = @JoinColumn(name = "idEscola"),
    inverseJoinColumns = @JoinColumn(name = "idTurma"))
    private List<Turma> turmas= new ArrayList<>(); //lista com os alunos da turma*/
    
    
    public void addTurma(Turma turma){
    turmas.add(turma);
    //alunos.getTurma().add(this);
    
    }
    
    public void removeTurma(Turma turma){
    turmas.remove(turma);
    //alunos.getTurma().remove(this);
    
    }

    public long getId() {
        return idEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.idEscola = id;
    }
}