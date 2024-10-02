import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {

    HashMap<Long,Integer> pedido = new HashMap<Long, Integer>();

    public Pedido(ArrayList<Long> itens, ArrayList<Integer> quantidade) {
        for (Long j : itens) {
            pedido.put(j, quantidade.get(j.intValue()));
        }
    }

    public HashMap<Long,Integer> getPedido() {
        return pedido;
    }

}