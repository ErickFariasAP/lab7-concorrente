import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.Random;

public class Client implements Runnable {

    private BlockingQueue<Pedido> pedidos;

    Random random = new Random();

    public Client (BlockingQueue<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public void run() {
      while(true) {
        try {
            ArrayList<Long> idItens  = new ArrayList<Long>();
            idItens.add(0L); idItens.add(1L);
            ArrayList<Integer> quantidadeItens = new ArrayList<Integer>();
            quantidadeItens.add(random.nextInt(10)); quantidadeItens.add(random.nextInt(10));
            Pedido pedidoAtual = new Pedido(idItens, quantidadeItens);
            pedidos.add(pedidoAtual);
            Thread.sleep(10);
        } catch (Exception e) {
        // e.printStackTrace();
      }
    }
  }
}