import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Estoque {

    BlockingQueue<Pedido> est1;
    BlockingQueue<Pedido> est2;

    public Estoque(BlockingQueue<Pedido> est1, BlockingQueue<Pedido> est2) {
        this.est1 = est1;
        this.est2 = est2;
    }

    @Override
    public void run() {
      try {
        while(true) {
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
}
