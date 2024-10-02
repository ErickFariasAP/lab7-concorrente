import java.util.concurrent.*;

public class Lab7 {

     public static void main(String[] args) {
        ExecutorService clients = Executors.newCachedThreadPool();
        ExecutorService estoqueThr = Executors.newCachedThreadPool();
        ExecutorService workers = Executors.newCachedThreadPool();

        ConcurrentHashMap<Long, Integer> estoque = new ConcurrentHashMap<Long, Integer>();

        BlockingQueue<Pedido> pedidos = new LinkedBlockingDeque<>(100);

        try {
            for (int i = 0; i < 10; i++) {
                clients.execute(new Client(pedidos));
            }
            estoqueThr.execute(new Estoque(estoque));
            
        } catch (Exception e) {}
     }

}