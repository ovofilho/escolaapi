/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.escolaapi.entity;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id; //duvida aqui

/**
 *
 * @author orlando
 */
@Entity
public class Aluno {

       
    @ApiModelProperty(value = "Matrícula do Aluno")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAluno;
    
    
    //lembrar que o dono da relacao é turma, a tab oposta ao join
    /*@ManyToMany(cascade = {
    CascadeType.ALL
    })
    @JoinTable(name = "turma_aluno",
    joinColumns = @JoinColumn(name = "idAluno"),
    inverseJoinColumns = @JoinColumn(name = "id"))*/
    
    @ManyToMany(mappedBy = "alunos")
    private List<Turma> turmas = new ArrayList<>(); //lista de turmas do aluno
    
    @Column(nullable = false)
    @ApiModelProperty(value = "Nome do Aluno")
    private String nome;
    
    /*public void addTurma(Turma turma){
    turmas.add(turma);
    turma.getAluno().add(this);
    
    }
    
    public void removeTurma(Turma turma){
    turmas.remove(turma);
    turma.getAluno().remove(this);
    
    }*/
    
    
    public Long getIdAluno() {
        return idAluno;
    }
    
    public void setIdAluno(Long idAluno) {
    this.idAluno = idAluno;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
