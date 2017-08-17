package br.com.exemplo.batch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

@SpringBootApplication
public class BatchApplication {

    private final static Logger LOGGER = Logger.getLogger(BatchApplication.class.getName());
    public static final String DADOS_IN = "C://Desenvolvimento//workspace//jobs//git//batch//src//main//resources//dados//in";
    public static final String DADOS_OUT = "C://Desenvolvimento//workspace//jobs//git//batch//src//main//resources//dados//out";

    public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            try {
                WatchService watcher = FileSystems.getDefault().newWatchService();
                Path dir = Paths.get(DADOS_IN);
                dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                LOGGER.info("Observando o diretorio: " + dir.getFileName());

                while (true) {
                    WatchKey key;

                    try {
                        key = watcher.take();
                    } catch (InterruptedException ex) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        @SuppressWarnings("unchecked")
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path fileName = ev.context();

                        LOGGER.info("Ação:" + kind.name() + " - Arquivo: " + fileName);

                        if (kind == ENTRY_CREATE && fileName.toString().indexOf(".dat") != -1) {
                            LOGGER.info("Arquivo .DAT para processar...");
                            newFile(fileName);
                        }
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
		};
	}

    private void newFile(Path fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(DADOS_IN + "//" + fileName)).useDelimiter("\\||\\n");
        if (null == scanner) {
            LOGGER.info("Arquivo vazio!");
        } else {
            List<Data> full = new ArrayList<Data>();
            Process process = new Process();
            Result result = new Result();
            while (scanner.hasNext()) {
                String theLine = scanner.next();
                Scanner line = new Scanner(theLine).useDelimiter(";");
                LOGGER.info("Linha: " + theLine);

                if (null != line) {
                    Data data =  new Data();
                    while (line.hasNext()) {
                        process.dataProcess(line, data);
                    }
                    full.add(data);
                }
            }

            createReport(fileName, full, result);
        }
    }

    private void deleteCurrentFile(Path fileName) {
        try {
            Files.delete(Paths.get(DADOS_IN + "//" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createReport(Path fileName, List<Data> full, Result result) {
        try {
            Files.write(Paths.get(DADOS_OUT + "//" + fileName + ".proc"), result.makeResult(full).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}