package br.com.exemplo.batch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Result {
    private final static Logger LOGGER = Logger.getLogger(Result.class.getName());

    public Result() {
    }

    public Report makeResult(List<Data> full) {
        LOGGER.info("Processamento dos dados...");

        Report end = new Report("0", "0");
        HashMap<String, BigDecimal> maxSale = new HashMap<>();
        Map.Entry<String, BigDecimal> maxEntry = null;
        HashMap<String, BigDecimal> lowSaler = new HashMap<>();
        Map.Entry<String, BigDecimal> minEntry = null;

        lookingFor(full, end, maxSale, lowSaler);

        for (Map.Entry<String, BigDecimal> entry : maxSale.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        for (Map.Entry<String, BigDecimal> entry : lowSaler.entrySet()) {
            if (minEntry == null || entry.getValue().compareTo(maxEntry.getValue()) < 0) {
                minEntry = entry;
            }
        }

        end.setMaxSale(maxEntry.getKey());
        end.setSalerName(minEntry.getKey());

        LOGGER.info("Qtd cliente: " +  end.getQttClient());
        LOGGER.info("Qtd vendedor: " + end.getQttSeller());
        LOGGER.info("Id maior venda: " + end.getMaxSale());
        LOGGER.info("Nome pior vendedor: " + end.getSalerName());
        return end;
    }

    private void lookingFor(List<Data> full, Report end, HashMap<String, BigDecimal> maxSale, HashMap<String, BigDecimal> lowSaler) {
        for (Data theData : full) {
            BigDecimal itemQtt = BigDecimal.ZERO;
            BigDecimal itemVal = BigDecimal.ZERO;

            if ("001".equalsIgnoreCase(theData.getCampoUm())) {
                end.setQttClient(String.valueOf(Long.parseLong(end.getQttClient()) + 1));
            } else if ("002".equalsIgnoreCase(theData.getCampoUm())) {
                end.setQttSeller(String.valueOf(Long.parseLong(end.getQttSeller()) + 1));
            } else {
                String id = theData.getCampoDois();
                String name = theData.getCampoSeis();
                BigDecimal qtt = BigDecimal.valueOf(Double.parseDouble(theData.getCampoQuatro()));
                BigDecimal val = BigDecimal.valueOf(Double.parseDouble(theData.getCampoCinco()));
                BigDecimal total = qtt.multiply(val);

                maxSale.put(id, total);

                if (lowSaler.containsKey(name)) {
                    BigDecimal now = lowSaler.get(name);
                    lowSaler.replace(name, now.add(total));
                } else {
                    lowSaler.put(name, total);
                }
            }
        }
    }
}
