package simulacao;

import modelos.Carro;
import sincronizacao.Frentista;

/* aqui executa todo o clico dos carros
 * a interaçao com o frentista e as bombas
 * rose e arthur é quem termina essa classe */
public class CicloCarro {
    private final Frentista frentista;
    private final Carro carro;

    public CicloCarro( Carro carro, Frentista frentista) {
        this.frentista = frentista;
        this.carro = carro;
    }

    public void run(){
        try {
            while (true){
                frentista.pedirPermissao(carro.getCarroId());

                // Aqui ainda falta pegar bombas, abastecer, soltar bombas (Arthur e Rose)

                frentista.liberarPermissao(carro.getCarroId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
