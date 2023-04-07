package org.senai.semana10.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @GetMapping
    public String Produto(Model model,
                          @RequestParam("nome") String nome,
                          @RequestParam("descricao") String descricao,
                          @RequestParam("dataLancamento") String dataLancamento,
                          @RequestParam("preco") String preco){
        model.addAttribute("nome", nome);
        model.addAttribute("descricao", descricao);
        model.addAttribute("dataLancamento", dataLancamento);
        model.addAttribute("preco", preco);
        return "produto";
    }
}
