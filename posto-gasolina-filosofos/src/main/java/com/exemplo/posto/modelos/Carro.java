package modelos;

import sincronizacao.Frentista;



// Arquivo base para Rose completar
public class Carro  {
    private int id;
    private Frentista frentista;

    public Carro(int id, Bomba esquerda, Bomba direita, Frentista frentista) {
        // Rose vai implementar
    }

    // não faço nada aqui dentro, pertence a simulação. responsável por Rose
    public void run() {
        // Rose vai implementar o loop do carro
    }
    public int getId(){
        return id;
    }

}
