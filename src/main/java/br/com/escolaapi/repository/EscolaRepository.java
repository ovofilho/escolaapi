package br.com.escolaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escolaapi.entity.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> { }