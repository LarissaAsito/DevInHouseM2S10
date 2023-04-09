package org.senai.semana10.loja.service;

import org.senai.semana10.loja.model.ProdutoEntity;
import org.senai.semana10.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> getAllProdutos(){
        return this.produtoRepository.findAll();
    }

    public ProdutoEntity saveProduto(ProdutoEntity produto){
        return this.produtoRepository.save(produto);
    }

    public Optional<ProdutoEntity> findProdutoById (Long id){
        return this.produtoRepository.findById(id);
    }

    public void deteleProdutoById(Long id){
        this.produtoRepository.deleteById(id);
    }
}
