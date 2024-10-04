import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Client implements Runnable {

    private BlockingQueue<Pedido> pedidos;

    Random random = new Random();
    Long id;
    Long nextPedido;
    public Client (BlockingQueue<Pedido> pedidos, Long id) {
        this.pedidos = pedidos;
        this.id = id;
        this.nextPedido = 1L;
    }

    @Override
    public void run() {
        try {
            ArrayList<Long> idItens  = new ArrayList<Long>();
            for(long i = 0; i < 3; i++) idItens.add(i);

            ArrayList<Integer> quantidadeItens = new ArrayList<Integer>();
            for(long i = 0; i < 3; i++) quantidadeItens.add(random.nextInt(10));

            Pedido pedidoAtual = new Pedido(idItens, quantidadeItens, this, nextPedido++);
            pedidos.add(pedidoAtual);
        } catch (Exception e) {
        // e.printStackTrace();
    }
  }

  public Long getId() {
    return this.id;
  }
}
