import java.util.concurrent.*;

public class Lab7 {

     public static void main(String[] args) {
        ExecutorService clients = Executors.newCachedThreadPool();
        ExecutorService estoque = Executors.newCachedThreadPool();
        ExecutorService workers = Executors.newCachedThreadPool();
        
        BlockingQueue<Pedido> estoqueItem1 = new LinkedBlockingDeque<>(100);
        BlockingQueue<Pedido> estoqueItem2 = new LinkedBlockingDeque<>(100);

        BlockingQueue<Pedido> pedidos = new LinkedBlockingDeque<>(100);

        try {
            clients.execute(new Client(pedidos));
            
        } catch (Exception e) {}
     }

}