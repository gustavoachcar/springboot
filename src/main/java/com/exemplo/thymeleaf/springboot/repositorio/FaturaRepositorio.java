package com.exemplo.thymeleaf.springboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.thymeleaf.springboot.modelo.Fatura;

public interface FaturaRepositorio extends JpaRepository<Fatura, Long> {

}