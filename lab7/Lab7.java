import java.util.concurrent.*;

public class Lab7 {

     public static void main(String[] args) {
        ExecutorService clientsThr = Executors.newCachedThreadPool();
        ExecutorService estoqueThr = Executors.newCachedThreadPool();
        ExecutorService workersThr = Executors.newCachedThreadPool();

        ConcurrentHashMap<Long, Integer> estoque = new ConcurrentHashMap<Long, Integer>();

        BlockingQueue<Pedido> pedidos = new LinkedBlockingDeque<>(100);

        try {
            for (int i = 0; i < 10; i++) {
                clientsThr.execute(new Client(pedidos));
            }
            estoqueThr.execute(new Estoque(estoque));
            workersThr.execute(new Worker(pedidos, estoque));
            
        } catch (Exception e) {}
     }

}