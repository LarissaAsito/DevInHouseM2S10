package org.senai.semana10.loja.controller;

import org.senai.semana10.loja.controller.dto.ProdutoDTO;
import org.senai.semana10.loja.model.ProdutoEntity;
import org.senai.semana10.loja.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                produtoService.getAllProdutos()
                        .stream()
                        .map(
                                produtoEntity -> new ProdutoDTO(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getDataLancamento(), produtoEntity.getPreco())
                        ).collect(Collectors.toList());

        model.addAttribute("produtoDTOs", produtoDTOs);
        return "lista_produtos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPessoa(
            @PathVariable("id") Long id,
            Model model
    ){
        produtoService.deteleProdutoById(id);

        List<ProdutoDTO> produtoDTOS =
                produtoService.getAllProdutos()
                        .stream()
                        .map(
                                produtoEntity -> new ProdutoDTO(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getDataLancamento(), produtoEntity.getPreco())
                        ).collect(Collectors.toList()); //define o tipo da lista que vamos salvar

        model.addAttribute("produtoDTOs", produtoDTOS);
        return "lista_produtos";
    }


    @GetMapping("editar/{id}")
    public String formularioEditar(@PathVariable("id") long id, Model model) {
        ProdutoEntity produto = produtoService.findProdutoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        ProdutoDTO produtoDTO =  new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getDataLancamento(), produto.getPreco());


        model.addAttribute("produtoDTO", produtoDTO);

        return "atualiza_produto";
    }

    @PostMapping("editar/{id}")
    public String atualizarProduto(@PathVariable("id") Long id, @Validated ProdutoDTO produtoDTO, BindingResult result,
                                Model model) {

        ProdutoEntity entity = produtoService
                .findProdutoById(id)
                .orElse(null);

        entity.setNome(produtoDTO.getNome());
        entity.setDescricao(produtoDTO.getDescricao());
        entity.setDataLancamento(produtoDTO.getDataLancamento());
        entity.setPreco(produtoDTO.getPreco());


        produtoService.saveProduto(entity);

        List<ProdutoDTO> produtoDTOs =
                produtoService.getAllProdutos()
                        .stream()
                        .map(
                                produtoEntity -> new ProdutoDTO(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getDataLancamento(), produtoEntity.getPreco())
                        ).collect(Collectors.toList()); //define o tipo da lista que vamos salvar

        model.addAttribute("produtoDTOs", produtoDTOs);
        return "lista_produtos";
    }

}
