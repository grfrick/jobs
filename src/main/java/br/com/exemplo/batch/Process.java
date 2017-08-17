package br.com.exemplo.batch;

import java.util.Scanner;
import java.util.logging.Logger;

public class Process {

    private final static Logger LOGGER = Logger.getLogger(Process.class.getName());

    public Process() {
    }

    public void dataProcess(Scanner line, Data data) {
        data.setCampoUm(line.next());

        switch (data.getCampoUm()) {
            case "003" :
                LOGGER.info("Caso 003");
                theThree(line, data);
                data.setCampoCinco(line.next());
                data.setCampoSeis(line.next());
                break;
            default:
                theThree(line, data);
                break;
        }

        LOGGER.info("a: " + data.getCampoUm());
        LOGGER.info("b: " + data.getCampoDois());
        LOGGER.info("c: " + data.getCampoTres());
        LOGGER.info("d: " + data.getCampoQuatro());
        LOGGER.info("e: " + data.getCampoCinco());
        LOGGER.info("f: " + data.getCampoSeis());
    }

    private void theThree(Scanner line, Data data) {
        data.setCampoDois(line.next());
        data.setCampoTres(line.next());
        data.setCampoQuatro(line.next());
    }
}
