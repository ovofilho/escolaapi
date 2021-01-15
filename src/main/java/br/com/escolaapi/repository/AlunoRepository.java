/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.escolaapi.repository;

import br.com.escolaapi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author orlando
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno , Long>{
    
}
