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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 *
 * @author orlando
 */
@Entity
public class Turma {
    @ApiModelProperty(value = "Código da Turma")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTurma;
    
    /*@ManyToMany(mappedBy = "turmas")
    private List<Aluno> alunos = new ArrayList<>(); //lista com os alunos da turma*/
    
    @ManyToMany(cascade = {
    CascadeType.ALL
    })
    @JoinTable(name = "turma_aluno",
    joinColumns = @JoinColumn(name = "idTurma"),
    inverseJoinColumns = @JoinColumn(name = "idAluno"))
    private List<Aluno> alunos = new ArrayList<>(); //lista com os alunos da turma*/
    
    @ApiModelProperty(value = "Nome da Turma")
    @Column(nullable = false)
    private String nome;
    
    public void addAluno(Aluno aluno){
    alunos.add(aluno);
    //alunos.getTurma().add(this);
    
    }
    
    public void removeAluno(Aluno aluno){
    alunos.remove(aluno);
    //alunos.getTurma().remove(this);
    
    }
    
    @ManyToOne()
    private Escola escola = new Escola(); //lista de turmas do aluno

    public Long getId() {
        return idTurma;
    }

    public void setId(Long id) {
        this.idTurma = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    
}
