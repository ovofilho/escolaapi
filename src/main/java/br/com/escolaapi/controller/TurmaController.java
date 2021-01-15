/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.escolaapi.controller;

import br.com.escolaapi.entity.Aluno;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.escolaapi.entity.Turma;
import br.com.escolaapi.repository.AlunoRepository;
import br.com.escolaapi.repository.TurmaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author orlando
 */
@RestController
public class TurmaController {
    @Autowired //diz pro bootspring iniciar o obj
    private TurmaRepository _turmaRepository;
    @Autowired  
    private AlunoRepository _alunoRepository;

    @ApiOperation(value = "Retorna uma lista de turmas")
    @ApiResponses(value = { //annotation do swagger e especifica os codigos de retorno no controller
        @ApiResponse(code = 200, message = "Retorna a lista de turma"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/turma", method = RequestMethod.GET, produces="application/json") //annotaton de rota
    public List<Turma> Get() {
        return _turmaRepository.findAll();
    }

    @RequestMapping(value = "/turma/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Turma> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Turma> turma = _turmaRepository.findById(id);
        if(turma.isPresent())
            return new ResponseEntity<Turma>(turma.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
    @RequestMapping(value = "/turma", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    public Turma Post(@Valid @RequestBody Turma turma)
    {
        return _turmaRepository.save(turma);
    }*/
/*
    @RequestMapping(value = "/turma/{id}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Turma> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Turma newTurma)
    {
        Optional<Turma> oldTurma = _turmaRepository.findById(id);
        if(oldTurma.isPresent()){
            Turma turma = oldTurma.get();
            turma.setNome(newTurma.getNome());
            _turmaRepository.save(turma);
            return new ResponseEntity<Turma>(turma, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/ //A turma só pode ser cadastrada pela escola.
    
    @RequestMapping(value = "/turma/{turmaid}/matricular/{alunoidAluno}", method =  RequestMethod.POST, produces="application/json")
    public ResponseEntity<Turma> Matricular(@PathVariable(value = "turmaid") long id, @PathVariable(value = "alunoidAluno") long idAluno)
            
    {
        Optional<Turma> turmadb = _turmaRepository.findById(id);
        if(turmadb.isPresent()){
            Turma turma = turmadb.get();
            Optional<Aluno> aluno = _alunoRepository.findById(idAluno);
            if(aluno.isPresent()){
                turma.addAluno(aluno.get());
                _turmaRepository.save(turma);
                return new ResponseEntity<Turma>(turma, HttpStatus.OK);
            }
            
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
     @RequestMapping(value = "/turma/{turmaid}/cancelar/{alunoidAluno}", method =  RequestMethod.POST, produces="application/json")
    public ResponseEntity<Turma> CancelarMatricula(@PathVariable(value = "turmaid") long id, @PathVariable(value = "alunoidAluno") long idAluno)
            
    {
        Optional<Turma> turmadb = _turmaRepository.findById(id);
        if(turmadb.isPresent()){
            Turma turma = turmadb.get();
            Optional<Aluno> aluno = _alunoRepository.findById(idAluno);
            if(aluno.isPresent()){
                turma.removeAluno(aluno.get());
                _turmaRepository.save(turma);
                return new ResponseEntity<Turma>(turma, HttpStatus.OK);
            }
            
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/turma/{id}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Turma> turma = _turmaRepository.findById(id);
        if(turma.isPresent()){
            _turmaRepository.delete(turma.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    
}
