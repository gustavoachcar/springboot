package com.exemplo.thymeleaf.springboot.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.exemplo.thymeleaf.springboot.excecao.FaturaNaoEncontradaExcecao;
import com.exemplo.thymeleaf.springboot.modelo.Fatura;
import com.exemplo.thymeleaf.springboot.servico.IFaturaServico;

@Controller
@RequestMapping("/fatura")
public class FaturaControlador {

	@Autowired
	private IFaturaServico service;

	@GetMapping("/")
	public String exibirPaginaInicial() {
		return "paginaInicio";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicao() {
		return "adicionarFatura";
	}

	@PostMapping("/salvar")
	public String salvarFatura(@ModelAttribute Fatura fatura, Model modelo) {
		service.salvarFatura(fatura);
		Long id = service.salvarFatura(fatura).getId();
		String mensagem = "Fatura com id: '" + id + "' salva com sucesso!";
		modelo.addAttribute("message", mensagem);
		return "adicionarFatura";
	}

	@GetMapping("/listar")
	public String buscarTodasFaturas(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
		List<Fatura> faturas = service.buscarTodasFaturas();
		modelo.addAttribute("listagem", faturas);
		modelo.addAttribute("message", mensagem);
		return "listarFaturas";
	}

	@GetMapping("/editar")
	public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long id) {
		String pagina = null;
		try {
			Fatura fatura = service.bucarFaturaPorId(id);
			modelo.addAttribute("fatura", fatura);
			pagina = "editarFatura";
		} catch (FaturaNaoEncontradaExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
			pagina = "redirect:listar";
		}
		return pagina;
	}

	@PostMapping("/atualizar")
	public String atualizarFatura(@ModelAttribute Fatura fatura, RedirectAttributes atributos) {
		service.atualizarFatura(fatura);
		Long id = fatura.getId();
		atributos.addAttribute("message", "Fatura com id: '" + id + "' atualizada com sucesso!");
		return "redirect:listar";
	}

	@GetMapping("/deletar")
	public String deletarFatura(@RequestParam Long id, RedirectAttributes atributos) {
		try {
			service.deletarFaturaPorId(id);
			atributos.addAttribute("message", "Fatura com id: '" + id + "' excluída com sucesso!");
		} catch (FaturaNaoEncontradaExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
		}
		return "redirect:listar";
	}
}