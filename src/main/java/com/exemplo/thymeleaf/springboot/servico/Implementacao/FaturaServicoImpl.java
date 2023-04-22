package com.exemplo.thymeleaf.springboot.servico.Implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.thymeleaf.springboot.excecao.FaturaNaoEncontradaExcecao;
import com.exemplo.thymeleaf.springboot.modelo.Fatura;
import com.exemplo.thymeleaf.springboot.repositorio.FaturaRepositorio;
import com.exemplo.thymeleaf.springboot.servico.IFaturaServico;
 
@Service
public class FaturaServicoImpl implements IFaturaServico {
 
	@Autowired
	private FaturaRepositorio repositorio;
 
	@Override
	public Fatura salvarFatura(Fatura fatura) {
		return repositorio.save(fatura);
	}
 
	@Override
	public List<Fatura> buscarTodasFaturas() {
		return repositorio.findAll();
	}
 
	@Override
	public Fatura bucarFaturaPorId(Long id) {
		Optional<Fatura> opcional = repositorio.findById(id);
		if (opcional.isPresent()) {
			return opcional.get();
		} else {
			throw new FaturaNaoEncontradaExcecao("Fatura com id: " + id + " não encontrada.");
		}
	}
 
	@Override
	public void deletarFaturaPorId(Long id) {
		repositorio.delete(bucarFaturaPorId(id));
	}
 
	@Override
	public void atualizarFatura(Fatura fatura) {
		repositorio.save(fatura);
	}
}