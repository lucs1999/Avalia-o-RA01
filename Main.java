// Classe para representar uma solicitação ou cliente
class Elemento {
    String id;
    String descricao;
    String dataHora; // Apenas para solicitações

    // Construtor para criar um cliente
    Elemento(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = ""; // Clientes não têm data/hora
    }

    // Construtor para criar uma solicitação
    Elemento(String id, String descricao, String dataHora) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    // Méétodo para mostrar os detalhes do elemento como uma string
    @Override
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + (dataHora.isEmpty() ? "" : ", Data/Hora: " + dataHora);
    }
}

// Classe para representar um nó em uma lista encadeada
class Node {
    Elemento elemento;
    Node proximo; // Referência para o próximo nó

    // Construtor para criar um novo nó
    Node(Elemento elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }
}

// Classe para implementar uma pilha usando lista encadeada
class Pilha {
    private Node topo; // Referência para o topo da pilha

    // Construtor para inicializar a pilha vazia
    public Pilha() {
        topo = null;
    }

    // Adiciona um novo elemento ao topo da pilha
    public void empilhar(Elemento elemento) {
        Node novoNode = new Node(elemento);
        novoNode.proximo = topo;
        topo = novoNode;
    }

    // Remove e retorna o elemento do topo da pilha
    public Elemento desempilhar() {
        if (topo == null) {
            throw new RuntimeException("Pilha vaz");
        }
        Elemento elemento = topo.elemento;
        topo = topo.proximo;
        return elemento;
    }

    // Verifica se a pilha está vazia
    public boolean estaVazia() {
        return topo == null;
    }

    // Imprime todos os elementos da pilha, começando do topo
    public void imprimir() {
        Node atual = topo;
        while (atual != null) {
            System.out.println(atual.elemento);
            atual = atual.proximo;
        }
    }
}

// Classe para implementar uma fila usando lista encadeada
class Fila {
    private Node frente; // Referência para o início da fila
    private Node tras;   // Referência para o fim da fila

    // Construtor para inicializar a fila vazia
    public Fila() {
        frente = null;
        tras = null;
    }

    // Adiciona um novo cliente no final da fila
    public void enfileirar(Elemento elemento) {
        Node novoNode = new Node(elemento);
        if (tras == null) {
            frente = novoNode;
            tras = novoNode;
        } else {
            tras.proximo = novoNode;
            tras = novoNode;
        }
    }

    // Remove e retorna o cliente do início da fila
    public Elemento desenfileirar() {
        if (frente == null) {
            throw new RuntimeException("Fila vazia");
        }
        Elemento elemento = frente.elemento;
        frente = frente.proximo;
        if (frente == null) {
            tras = null; // Se a fila ficar vazia, tras também deve ser null
        }
        return elemento;
    }

    // Verifica se a fila está vazia
    public boolean estaVazia() {
        return frente == null;
    }

    // Imprime todos os clientes na fila, começando do início
    public void imprimir() {
        Node atual = frente;
        while (atual != null) {
            System.out.println(atual.elemento);
            atual = atual.proximo;
        }
    }
}

// Classe principal para testar a pilha e a fila
public class Main {
    public static void main(String[] args) {
        Pilha historico = new Pilha(); // Inicializa a pilha para histórico de solicitações
        Fila filaAtendimento = new Fila(); // Inicializa a fila para atendimento de clientes

        // Dados fornecidos para histórico de solicitações
        Elemento[] historicoArray = {
                new Elemento("REQ001", "Instalação de software", "2024-08-20 10:30"),
                new Elemento("REQ002", "Manutenção preventiva", "2024-08-20 11:00"),
                new Elemento("REQ003", "Atualização de sistema", "2024-08-20 11:30"),
                new Elemento("REQ004", "Suporte técnico", "2024-08-20 12:00"),
                new Elemento("REQ005", "Troca de equipamento", "2024-08-20 12:30"),
                new Elemento("REQ006", "Consulta de garantia", "2024-08-20 13:00"),
                new Elemento("REQ007", "Reparo de impressora", "2024-08-20 13:30"),
                new Elemento("REQ008", "Configuração de rede", "2024-08-20 14:00"),
                new Elemento("REQ009", "Restauração de dados", "2024-08-20 14:30"),
                new Elemento("REQ010", "Consulta técnica", "2024-08-20 15:00")
        };

        // Dados fornecidos para fila de atendimento
        Elemento[] filaAtendimentoArray = {
                new Elemento("CLI001", "Maria Silva", "Dúvida sobre produto"),
                new Elemento("CLI002", "João Souza", "Reclamação de serviço"),
                new Elemento("CLI003", "Ana Costa", "Solicitação de reembolso"),
                new Elemento("CLI004", "Pedro Alves", "Informações de entrega"),
                new Elemento("CLI005", "Carla Dias", "Agendamento de visita"),
                new Elemento("CLI006", "Lucas Martins", "Alteração de pedido"),
                new Elemento("CLI007", "Patrícia Rocha", "Cancelamento de contrato"),
                new Elemento("CLI008", "Rafael Lima", "Renovação de assinatura"),
                new Elemento("CLI009", "Fernanda Gomes", "Suporte para instalação"),
                new Elemento("CLI010", "Carlos Eduardo", "Pedido de orçamento")
        };

        // Adiciona as solicitações ao histórico
        for (Elemento e : historicoArray) {
            historico.empilhar(e);
        }

        // Adiciona os clientes à fila de atendimento
        for (Elemento e : filaAtendimentoArray) {
            filaAtendimento.enfileirar(e);
        }

        // Imprime o histórico de solicitações
        System.out.println("Histórico de Solicitações:");
        historico.imprimir();

        // Imprime a fila de atendimento
        System.out.println("\nFila de Atendimento:");
        filaAtendimento.imprimir();

        // Atende o próximo cliente da fila e o exibe
        System.out.println("\nAtendendo cliente:");
        Elemento clienteAtendido = filaAtendimento.desenfileirar();
        System.out.println(clienteAtendido);

        // Remove a última solicitação do histórico e a exibe
        System.out.println("\nRemovendo solicitação do histórico:");
        Elemento solicitacaoRemovida = historico.desempilhar();
        System.out.println(solicitacaoRemovida);

        // Imprime o histórico de solicitações atualizado
        System.out.println("\nHistórico de Solicitações Atualizado:");
        historico.imprimir();

        // Imprime a fila de atendimento atualizada
        System.out.println("\nFila de Atendimento Atualizada:");
        filaAtendimento.imprimir();
    }
}
