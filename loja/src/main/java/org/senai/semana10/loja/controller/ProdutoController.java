package org.senai.semana10.loja.controller;

import org.senai.semana10.loja.controller.dto.ProdutoDTO;
import org.senai.semana10.loja.model.ProdutoEntity;
import org.senai.semana10.loja.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /*@GetMapping
    public String Produto(Model model){
        ProdutoEntity entity = new ProdutoEntity();
        entity.setNome("teste");
        entity.setDescricao("testedesc");
        entity.setDataLancamento("1-1-");
        entity.setPreco(12.12);
        model.addAttribute("nome", entity.getNome());
        model.addAttribute("descricao", entity.getDescricao());
        model.addAttribute("dataLancamento", entity.getDataLancamento());
        model.addAttribute("preco", entity.getPreco());


        return "produto";
    }*/
    @GetMapping
    public String chamaFormulario(ProdutoDTO produtoDTO, Model model){
        return "formulario";
    }
    @PostMapping
    public String postFormulario(
            @Validated ProdutoDTO produtoDTO,
            Model model
    ){
        produtoService.saveProduto(
                ProdutoEntity.builder()
                        .nome(produtoDTO.getNome())
                        .descricao(produtoDTO.getDescricao())
                        .dataLancamento(produtoDTO.getDataLancamento())
                        .preco(produtoDTO.getPreco())
                        .build()
        );
        return "redirect:produto/lista"; //vai para o edpoint /formulario/lista
    }

    @GetMapping("/lista")
    public String listarProdutos(
            Model model
    ){
        List<ProdutoDTO> produtoDTOs =
                produtoService.getAllProdutos()//Lista de PessoaEntity
                        .stream() //Stream de PessoaEntity, apenas a Stream tem acesso ao map
                        .map( // vai ser executado para cada item da lista de PessoaEntity
                                // e vai criar um novo item que será armazenado na lista nova
                                produtoEntity -> new ProdutoDTO(produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getDataLancamento(), produtoEntity.getPreco())
                        ).collect(Collectors.toList()); //define o tipo da lista que vamos salvar

        model.addAttribute("produtoDTOs", produtoDTOs);
        return "lista_produtos";
    }
}
