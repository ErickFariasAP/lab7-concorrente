import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import static java.util.concurrent.TimeUnit.*;

public class Lab7 {
     public static void main(String[] args) {
        ScheduledExecutorService clientsThr = Executors.newScheduledThreadPool(10);
        ScheduledExecutorService estoqueThr = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService workersThr = Executors.newScheduledThreadPool(5);
        ConcurrentHashMap<Long, Integer> estoque = new ConcurrentHashMap<Long, Integer>();

        BlockingQueue<Pedido> pedidos = new LinkedBlockingDeque<>(100);

        AtomicInteger workersProcessando = new AtomicInteger(0);
        AtomicInteger pedidosProcessados = new AtomicInteger(0);
        AtomicInteger valorTotalDasVendas = new AtomicInteger(0);
        AtomicInteger pedidosRejeitados = new AtomicInteger(0);

        try {
            for (long i = 0; i < 10; i++) {
                clientsThr.scheduleAtFixedRate(new Client(pedidos, i), 0, 4, SECONDS);
            }
            for(int i = 0; i < 5; i++) {
                workersThr.scheduleAtFixedRate(new Worker(
                    pedidos, 
                    estoque, 
                    pedidosProcessados, 
                    valorTotalDasVendas, 
                    pedidosRejeitados,
                    workersProcessando
                ), 0, 1, SECONDS);
            }

            estoqueThr.scheduleAtFixedRate(new Estoque(estoque), 0, 2, SECONDS);            
        } catch (Exception e) {}

        while(true){
            try{
                while (workersProcessando.get() > 0);

                System.out.println("Pedidos processados: " + pedidosProcessados);
                System.out.println("Pedidos rejeitados: " + pedidosRejeitados);
                System.out.println("Total em vendas R$" + valorTotalDasVendas);
                System.out.println();
                Thread.sleep(6000);
            }catch(Exception e){}
        }
    }
}
