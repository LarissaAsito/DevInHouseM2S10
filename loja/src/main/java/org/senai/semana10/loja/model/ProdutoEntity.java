package org.senai.semana10.loja.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String dataLancamento;
    private Double preco;

}
