/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.escolaapi.repository;

import br.com.escolaapi.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author orlando
 */
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> { }