package br.com.exemplo.batch;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
public class Report {
    public Report(String qttClient, String qttSeller) {
        this.qttClient = qttClient;
        this.qttSeller = qttSeller;
    }

    @Getter
    @Setter
    private String qttClient;

    @Getter
    @Setter
    private String qttSeller;

    @Getter
    @Setter
    private String maxSale;

    @Getter
    @Setter
    private String salerName;

    @Override
    public String toString() {
        return "Report{" +
                "Quantidade Clientes='" + qttClient + '\'' +
                ", Quantidade Vendedores='" + qttSeller + '\'' +
                ", Id da Venda de Maior Valor='" + maxSale + '\'' +
                ", Vendedor que menos vendeu='" + salerName + '\'' +
                '}';
    }
}
