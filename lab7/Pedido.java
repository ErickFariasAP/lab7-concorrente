import java.util.ArrayList;

public class Pedido {

    private ArrayList<Long> itens;
    private ArrayList<Integer> quantidade;

    public Pedido(ArrayList<Long> itens, ArrayList<Integer> quantidade) {
        this.itens = itens;
        this.quantidade = quantidade;
    }

}