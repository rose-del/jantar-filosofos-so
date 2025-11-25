package modelos;

import modelos.Bomba;
import modelos.Carro;
import sincronizacao.Frentista;

import java.util.concurrent.Semaphore;

public class Posto {
    private static final int NUM_CARROS = 5;
    private static final int NUM_BOMBAS = 5;

    public static void main(String[]args) {

        //cria bombas
        Bomba[] bombas = new Bomba[NUM_BOMBAS];
        for (int i = 0; i < NUM_BOMBAS; i++) {
            bombas[i] = new Bomba(i);
        }

        // Semáforo do frentista (N - 1) → Evita deadlock
        // esta chamando a classe frentista
        Frentista frentista = new Frentista(NUM_CARROS);
       // Semaphore frentista = new Semaphore(NUM_CARROS - 1);

        // Cria e inicializa os carros como threads (Rose vai implementar)
        Carro[] carros = new Carro[NUM_CARROS];
        for (int i = 0; i < NUM_CARROS; i++) {
            Bomba esquerda = bombas[i];
            Bomba direita = bombas[(i + 1) % NUM_BOMBAS];

            carros[i] = new Carro(i, esquerda, direita,frentista );
            carros[i].start(); // Apenas starta a thread
        }
    }
}