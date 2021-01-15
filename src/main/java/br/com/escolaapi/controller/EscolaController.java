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

import br.com.escolaapi.entity.Escola;
import br.com.escolaapi.entity.Turma;
import br.com.escolaapi.repository.TurmaRepository;
import br.com.escolaapi.repository.EscolaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController // define o controller no formato Rest
public class EscolaController {
    
    @Autowired //diz pro bootspring iniciar o obj
    private EscolaRepository _escolaRepository;
    @Autowired  
    private TurmaRepository _turmaRepository;

    @ApiOperation(value = "Retorna uma lista de escolas")
    @ApiResponses(value = { //annotation do swagger e especifica os codigos de retorno no controller
        @ApiResponse(code = 200, message = "Retorna a lista de escola"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/escola", method = RequestMethod.GET, produces="application/json") //annotaton de rota
    public List<Escola> Get() {
        return _escolaRepository.findAll();
    }

    @RequestMapping(value = "/escola/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Escola> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Escola> escola = _escolaRepository.findById(id);
        if(escola.isPresent())
            return new ResponseEntity<Escola>(escola.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/escola", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    public Escola Post(@Valid @RequestBody Escola escola)
    {
        return _escolaRepository.save(escola);
    }

    @RequestMapping(value = "/escola/{id}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Escola> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Escola newEscola)
    {
        Optional<Escola> oldEscola = _escolaRepository.findById(id);
        if(oldEscola.isPresent()){
            Escola escola = oldEscola.get();
            escola.setNome(newEscola.getNome());
            _escolaRepository.save(escola);
            return new ResponseEntity<Escola>(escola, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/escola/{id}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Escola> escola = _escolaRepository.findById(id);
        if(escola.isPresent()){
            _escolaRepository.delete(escola.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/escola/{escolaid}/cadTurma", method =  RequestMethod.POST, produces="application/json")
    public ResponseEntity<Escola> CadastraTurma(@PathVariable(value = "escolaid") long idEscola, @Valid @RequestBody Turma newTurma)
            
    {
        Optional<Escola> escoladb = _escolaRepository.findById(idEscola);
        if(escoladb.isPresent()){
            Escola escola = escoladb.get();
            Turma turma = _turmaRepository.save(newTurma);
            
                escola.addTurma(turma);
                escola = _escolaRepository.save(escola);
                return new ResponseEntity<Escola>(escola, HttpStatus.OK);
            
            
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
     @RequestMapping(value = "/escola/{escolaId}/cancelar/{turmaIdTurma}", method =  RequestMethod.POST, produces="application/json")
    public ResponseEntity<Escola> CancelarTurma(@PathVariable(value = "escolaId") long idEscola, @PathVariable(value = "turmaIdTurma") long idTurma)
            
    {
        Optional<Escola> escoladb = _escolaRepository.findById(idEscola);
        if(escoladb.isPresent()){
            Escola escola = escoladb.get();
            Optional<Turma> turma = _turmaRepository.findById(idTurma);
            if(turma.isPresent()){
                escola.removeTurma(turma.get());
                _escolaRepository.save(escola);
                return new ResponseEntity<Escola>(escola, HttpStatus.OK);
            }
            
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}