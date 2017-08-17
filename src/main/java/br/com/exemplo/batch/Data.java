package br.com.exemplo.batch;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Data {
    @Getter
    @Setter
    private String campoUm; // ID: 001 (Vendedor) | 002 (Cliente) | 003 (Venda)

    @Getter
    @Setter
    private String campoDois; // CPF | CNPJ | Id da Venda

    @Getter
    @Setter
    private String campoTres; // Nome | Nome | Id do Item

    @Getter
    @Setter
    private String campoQuatro; // Salario | Ramo Atividade | Qtd Item

    @Getter
    @Setter
    private String campoCinco; // Preço do item

    @Getter
    @Setter
    private String campoSeis; // Nome vendedor
}
