package com.exemplo.posto.modelos;

public class Bomba {
    private final int id;
    private boolean emUso = false;

    public Bomba(int id) {
        this.id = id;
    }

    // getters — Arthur que vai implementar o controle de pegar/soltar
    public int getId() {
        return id;
    }

    public synchronized boolean isEmUso() {
        return emUso;
    }

    public synchronized void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }
}
// --- FIM DA PARTE DO PAULO ---
