package com.exemplo.thymeleaf.springboot.servico;

import java.util.List;
import com.exemplo.thymeleaf.springboot.modelo.Fatura;

public interface IFaturaServico {

	public Fatura salvarFatura(Fatura fatura);

	public List<Fatura> buscarTodasFaturas();

	public Fatura bucarFaturaPorId(Long id);

	public void deletarFaturaPorId(Long id);

	public void atualizarFatura(Fatura fatura);

}