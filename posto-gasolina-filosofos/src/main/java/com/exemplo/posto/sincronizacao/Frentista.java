package sincronizacao;

import simulacao.LoggerSimples;

import java.util.concurrent.Semaphore;

public class Frentista {
    private final Semaphore semaforo;
    /**
     * Construtor:
     * Recebe o número total de carros e cria um semáforo com (N - 1) permissões.
     * Isso impede deadlock — padrão do "garçom" (waiter) do problema dos filósofos.
     * aqui o construtor recebe o numero de bombas já definido por Paulo;
     */

    public Frentista (int numCarros) {
        this.semaforo = new Semaphore(numCarros -1, true);
    }

    public void pedirPermissao (int id) throws InterruptedException{
        LoggerSimples.log("Carro " + id + "aguardando frentista... ");
        semaforo.acquire();
        LoggerSimples.log("Carro " + id + "Frentista liberou o carro");
    }

    public void liberarPermissao(int id) {
        semaforo.release();
        LoggerSimples.log("Carro "+ id + " liberou o frentista.");
    }

}
