package modelos;

import simulacao.LoggerSimples;
import sincronizacao.ControleBombas;
import sincronizacao.Frentista;

import java.util.List;

public class Carro extends Thread {
    private int id;
    private Frentista frentista;
    private Bomba esquerda;
    private Bomba direita;

    private static ControleBombas controleBombas;

    public static void setControleBombas(ControleBombas controle) {
        controleBombas = controle;
    }

    public Carro(int id, Bomba esquerda, Bomba direita, Frentista frentista) {
        this.id = id;
        this.esquerda = esquerda;
        this.direita = direita;
        this.frentista = frentista;
    }

    public void run() {
        try {
            while (true) {
                LoggerSimples.log("Carro " + id + " está dirigindo / pensando...");
                Thread.sleep((long) (500 + Math.random() * 1000));

                LoggerSimples.log("Carro " + id + " está pedindo permissão ao frentista...");
                List<Bomba> bombas = controleBombas.pedirDuasBombas(this);

                LoggerSimples.log("Carro " + id + " conseguiu duas bombas. Iniciando abastecimento...");
                Thread.sleep((long) (600 + Math.random() * 1200));

                // abastecendo 20 litros, só exemplo
                controleBombas.abastecer(id, 20);

                LoggerSimples.log("Carro " + id + " finalizou abastecimento. Devolvendo bombas...");
                controleBombas.liberarDuasBombas(bombas, id);

                LoggerSimples.log("Carro " + id + " saindo do posto.\n");
                Thread.sleep((long) (800 + Math.random() * 1500));
            }
        } catch (InterruptedException e) {
            LoggerSimples.log("Carro " + id + " foi interrompido.");
        }
    }

    /*public int getId() {
        return id;
    }*/

    public int getCarroId() {
        return id;
    }
}
