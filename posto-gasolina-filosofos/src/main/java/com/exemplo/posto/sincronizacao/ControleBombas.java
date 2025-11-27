package sincronizacao;

import modelos.Bomba;
import modelos.Carro;
import simulacao.LoggerSimples;

import java.util.List;

public class ControleBombas {

    private Frentista frentista;
    private final List<Bomba> bombas;
    public ControleBombas(int numBombas, List<Bomba> bombas) {
        this.frentista = new Frentista(numBombas);
        this.bombas = bombas;
        LoggerSimples.log("Controle de Bombas iniciado com " + numBombas + " bombas. Frentista ativado (N-1).");
    }

    public synchronized List<Bomba> procurarBombasLivres(Carro carro) {
        Bomba bomba1 = null;
        Bomba bomba2 = null;

        for (Bomba bomba : this.bombas) {
            if (!bomba.isEmUso()) {
                if (bomba1 == null) {
                    bomba1 = bomba;
                } else {
                    bomba2 = bomba;
                    break;
                }
            }
        }

        if (bomba1 != null && bomba2 != null) {
            bomba1.setEmUso(true);
            bomba2.setEmUso(true);

            LoggerSimples.log("Carro " + carro.getCarroId() + " ALOCOU as Bombas " + bomba1.getId() + " e " + bomba2.getId());
            return List.of(bomba1, bomba2);
        }
        return null;
    }

    public List<Bomba> pedirDuasBombas(Carro carro) throws InterruptedException {
        while (true) {
            frentista.pedirPermissao(carro.getCarroId());

            List<Bomba> bombasAlocadas = procurarBombasLivres(carro);

            if (bombasAlocadas != null) {
                return bombasAlocadas;
            } else {
                frentista.liberarPermissao(carro.getCarroId());

                LoggerSimples.log("Carro " + carro.getCarroId() + " não encontrou par livre. Esperando e tentando novamente.");

                Thread.sleep(100);
            }
        }
    }

    public void liberarDuasBombas(List<Bomba> bombasLiberadas, int idCarro) {
        synchronized (this) {
            bombasLiberadas.get(0).setEmUso(false);
            bombasLiberadas.get(1).setEmUso(false);
            LoggerSimples.log("Carro " + idCarro + " liberou as Bombas " + bombasLiberadas.get(0).getId() + " e " + bombasLiberadas.get(1).getId());
        }

        frentista.liberarPermissao(idCarro);
    }

    private int estoqueGasolina = 1000;

    public synchronized void abastecer(int idCarro, int litros) {
        if (estoqueGasolina >= litros) {
            estoqueGasolina -= litros;
            LoggerSimples.log("Carro " + idCarro + " abasteceu " + litros + "L. ESTOQUE: " + estoqueGasolina + "L.");
        } else {
            LoggerSimples.log("Carro " + idCarro + ": Estoque insuficiente.");
        }
    }

    public Frentista getFrentista() {
        return frentista;
    }

    public List<Bomba> getBombas() {
        return bombas;
    }
    // Removendo os métodos setFrentista, getSemaphore, setSemaphore, setBombas (não são padrões para um controlador)
}