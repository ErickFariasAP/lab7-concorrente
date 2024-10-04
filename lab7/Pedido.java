import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {

    HashMap<Long,Integer> pedido = new HashMap<Long, Integer>();
    Client cliente;
    Long id;
    public Pedido(ArrayList<Long> itens, ArrayList<Integer> quantidade, Client cliente, Long id) {
        for (Long j : itens) {
            pedido.put(j, quantidade.get(j.intValue()));
        }
        this.cliente = cliente;
        this.id = id;
    }

    public HashMap<Long,Integer> getPedido() {
        return pedido;
    }

    public Long getValor(){
        Long valorTotal = 0L;
        for(var entry: pedido.entrySet()){
            valorTotal = valorTotal + entry.getKey() * entry.getValue();
        }
        return valorTotal;
    }

    public Long getId() {
        return this.id;
    }

    public Client getClient() {
        return this.cliente;
    }
}
