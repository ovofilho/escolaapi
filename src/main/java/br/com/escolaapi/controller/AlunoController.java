/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.escolaapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.escolaapi.entity.Aluno;
import br.com.escolaapi.repository.AlunoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController // define o controller no formato Rest
public class AlunoController {
    
    @Autowired //diz pro bootspring iniciar o obj
    private AlunoRepository _alunoRepository;

    @ApiOperation(value = "Retorna uma lista de alunos")
    @ApiResponses(value = { //annotation do swagger e especifica os codigos de retorno no controller
        @ApiResponse(code = 200, message = "Retorna a lista de aluno"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/aluno", method = RequestMethod.GET, produces="application/json") //annotaton de rota
    public List<Aluno> Get() {
        return _alunoRepository.findAll();
    }

    @RequestMapping(value = "/aluno/{idAluno}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Aluno> GetById(@PathVariable(value = "idAluno") long idAluno)
    {
        Optional<Aluno> aluno = _alunoRepository.findById(idAluno);
        if(aluno.isPresent())
            return new ResponseEntity<Aluno>(aluno.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/aluno", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    public Aluno Post(@Valid @RequestBody Aluno aluno)
    {
        return _alunoRepository.save(aluno);
    }

    @RequestMapping(value = "/aluno/{idAluno}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Aluno> Put(@PathVariable(value = "idAluno") long idAluno, @Valid @RequestBody Aluno newAluno)
    {
        Optional<Aluno> oldAluno = _alunoRepository.findById(idAluno);
        if(oldAluno.isPresent()){
            Aluno aluno = oldAluno.get();
            aluno.setNome(newAluno.getNome());
            _alunoRepository.save(aluno);
            return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/aluno/{idAluno}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<Object> Delete(@PathVariable(value = "idAluno") long idAluno)
    {
        Optional<Aluno> aluno = _alunoRepository.findById(idAluno);
        if(aluno.isPresent()){
            _alunoRepository.delete(aluno.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}