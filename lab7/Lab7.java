import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab7 {
     public static void main(String[] args) {
        ExecutorService clientsThr = Executors.newCachedThreadPool();
        ExecutorService estoqueThr = Executors.newCachedThreadPool();
        ExecutorService workersThr = Executors.newCachedThreadPool();

        ConcurrentHashMap<Long, Integer> estoque = new ConcurrentHashMap<Long, Integer>();

        BlockingQueue<Pedido> pedidos = new LinkedBlockingDeque<>(100);

        AtomicInteger pedidosProcessados = new AtomicInteger(0);
        AtomicInteger valorTotalDasVendas = new AtomicInteger(0);
        AtomicInteger pedidosRejeitados = new AtomicInteger(0);

        try {
            for (int i = 0; i < 10; i++) {
                clientsThr.execute(new Client(pedidos));
            }
            estoqueThr.execute(new Estoque(estoque));
            workersThr.execute(new Worker(
                pedidos, 
                estoque, 
                pedidosProcessados, 
                valorTotalDasVendas, 
                pedidosRejeitados
            ));
            
        } catch (Exception e) {}

        while(true){
            try{
                System.out.println("Pedidos processados: " + pedidosProcessados);
                System.out.println("Pedidos rejeitados: " + pedidosRejeitados);
                System.out.println("Total em vendas R$" + valorTotalDasVendas);
                System.out.println();
                Thread.sleep(2000);
            }catch(Exception e){}
        }

     }

}