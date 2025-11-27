# **Jantar dos Filósofos — Versão Posto de Gasolina**

### Simulação em Java com Threads, Semáforos e Exclusão Mútua

---

## **Descrição do Projeto**

Este projeto é uma adaptação do clássico problema de concorrência **“Jantar dos Filósofos”** para um cenário de **posto de gasolina**, onde cada filósofo é representado por um **carro**, e cada garfo é representado por uma **bomba de combustível**.

O objetivo é **evitar deadlock, evitar starvation, garantir exclusão mútua e evitar race conditions** usando:

* **Threads** (carros rodando simultaneamente)
* **Semáforo (N-1)** — solução do garçom (*waiter solution*)
* **Sincronização (`synchronized`)** para garantir acesso seguro às bombas
* **Logs sincronizados** para acompanhar a simulação em tempo real

---

# **Objetivo da Simulação**

Reproduzir situações reais de concorrência em Sistemas Operacionais, ilustrando:

| Problema           | Explicação                                                   |
| ------------------ | ------------------------------------------------------------ |
| **Exclusão Mútua** | Duas threads não podem usar a mesma bomba simultaneamente    |
| **Race Condition** | Evitamos que dois carros peguem a mesma bomba ao mesmo tempo |
| **Deadlock**       | Impedido pelo semáforo N–1                                   |
| **Starvation**     | Evitado pela fairness do semáforo (fila justa)               |

---

# **Como o Sistema Funciona**

## 🔹 **1. Carros (Threads)**

Cada carro representa um filósofo do problema original.
Cada carro:

1. “Dirige” (pensa) por um tempo aleatório
2. Pede permissão ao **Frentista**
3. Tenta pegar **duas bombas livres**
4. Abastece
5. Devolve as bombas
6. Libera o frentista
7. Sai do posto e volta ao início

Esse ciclo acontece continuamente, simulando concorrência real.

---

## 🔹 **2. Bombas (Recursos Compartilhados)**

Cada bomba possui:

* um **id**
* um estado `emUso`
* métodos sincronizados `isEmUso()` e `setEmUso()`

Isso garante que **somente um carro por vez** pode marcar a bomba como ocupada, garantindo **exclusão mútua**.

---

## 🔹 **3. Frentista — Solução do Garçom (Waiter Solution)**

O frentista controla quantos carros podem tentar abastecer:

```java
Semaphore(numCarros - 1, true);
```

Isso evita:

### ✔ Deadlock

Nunca teremos todos os carros pegando apenas 1 bomba.
Sempre sobra uma bomba livre, desbloqueando o sistema.

### ✔ Starvation

O semáforo usa fairness (fila), garantindo atendimento justo.

---

## 🔹 **4. ControleBombas — O Cérebro da Sincronização**

Essa classe coordena:

### ✔ Procurar bombas livres

Garante que um carro só abastece se conseguir **duas bombas** ao mesmo tempo.

### ✔ Abastecer

Método sincronizado que reduz o estoque.

### ✔ Liberar bombas

Devolve as bombas ao sistema e libera o semáforo.

### ✔ Evita Race Conditions

Todos os métodos sensíveis são sincronizados.

---

## 🔹 **5. LoggerSimples — Log Seguro e Sincronizado**

A simulação usa:

```java
public static synchronized void log()
```

Isso evita que vários carros escrevam no console ao mesmo tempo, impedindo prints embaralhados.

---

## 🔹 **6. Classe Posto — Inicialização da Simulação**

Ela cria:

* 5 bombas
* 5 carros (threads)
* 1 frentista
* 1 controlador de bombas

Depois, inicia todas as threads, dando início à simulação.

---

# **Arquitetura da Solução**

```
+----------------------+
|         Posto        |
| (cria tudo e inicia) |
+----------+-----------+
           |
           v
+----------------------+
|      Frentista       |
| Semáforo N-1 (waiter)|
+----------+-----------+
           |
           v
+----------------------+
|   ControleBombas     |
|  Sincronização real  |
+----------+-----------+
           |
+----------+-----------+
|   LoggerSimples      |
|   Logs sincronizados |
+----------------------+
           |
           v
+----------------------+
|        Carros        |
|    (threads)         |
+----------------------+
           |
           v
+----------------------+
|      Bombas          |
| (recursos críticos)  |
+----------------------+
```

---

# **Conceitos de SO Demonstrados**

Este projeto demonstra na prática:

### 🟢 *Exclusão Mútua*

Bombas só podem ser usadas por 1 carro por vez.

### 🟢 *Race Condition*

Evitaria dois carros “pegarem” a mesma bomba simultaneamente.

### 🟢 *Deadlock*

Eliminado usando a abordagem do **garçom (waiter solution)**.

### 🟢 *Starvation*

Evitado usando semáforo justo (FIFO).

---

# **Como Executar**

### **Requisitos**

* Java 21+
* IDE IntelliJ

### **Resultado Esperado**

O console mostrará logs como:

```
[20:14:10] Carro 1 está dirigindo...
[20:14:12] Carro 1 aguardando frentista...
[20:14:12] Carro 1 Frentista liberou o carro
[20:14:12] Carro 1 ALOCOU as Bombas 2 e 4
[20:14:13] Carro 1 abasteceu 20L. ESTOQUE: 980L
[20:14:13] Carro 1 liberou as Bombas 2 e 4
```

Esses logs mostram claramente o comportamento paralelo.

---

# **Conclusão**

Este projeto demonstra como problemas clássicos de **concorrência e sincronização** podem ser resolvidos na prática usando:

* Threads
* Semáforos
* Exclusão mútua
* Sincronização fina
* Solução do garçom (waiter)
* Logs seguros

A solução é estável, impede deadlock, evita starvation e representa fielmente a dinâmica do problema original do Jantar dos Filósofos.

# **Autores**
- Rosenilda Santos da Silva
- Paulo Sérgio Albino
- Júlia Maria Benjamin
- Arthur Santos
