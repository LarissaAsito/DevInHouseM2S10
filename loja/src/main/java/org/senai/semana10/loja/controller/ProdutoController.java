package org.senai.semana10.loja.controller;

import org.senai.semana10.loja.controller.dto.ProdutoDTO;
import org.senai.semana10.loja.model.ProdutoEntity;
import org.senai.semana10.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String Produto(Model model){
        ProdutoEntity entity = new ProdutoEntity();
        entity.setNome("teste");
        entity.setDescricao("testedesc");
        entity.setDataLancamento("1-1-");
        entity.setPreco(12.12);
        //repository.save(entity);
        model.addAttribute("nome", entity.getNome());
        model.addAttribute("descricao", entity.getDescricao());
        model.addAttribute("dataLancamento", entity.getDataLancamento());
        model.addAttribute("preco", entity.getPreco());
        /*Optional<ProdutoEntity> dto = repository.findById(1L);
        model.addAttribute("produtoDto", dto);*/

        return "produto";
    }
}
