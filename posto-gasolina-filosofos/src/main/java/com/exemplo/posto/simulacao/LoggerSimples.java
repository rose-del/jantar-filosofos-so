package simulacao;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoggerSimples {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static synchronized void log(String msg) {
        String tempo = LocalTime.now().format(fmt);
        System.out.println("[" + tempo + "] " + msg);
    }
}
