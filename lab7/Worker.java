import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {

    BlockingQueue<Pedido> pedidos;
    ConcurrentHashMap<Long, Integer> estoque;
    AtomicInteger pedidosProcessados;
    AtomicInteger valorTotalDasVendas;
    AtomicInteger pedidosRejeitados;
    AtomicInteger workerProcessando;

    public Worker(
      BlockingQueue<Pedido> pedidos, 
      ConcurrentHashMap<Long, Integer> estoque,
      AtomicInteger pedidosProcessados,
      AtomicInteger valorTotalDasVendas,
      AtomicInteger pedidosRejeitados,
      AtomicInteger workerProcessando
    ) {
        this.pedidos = pedidos;
        this.estoque = estoque;
        this.pedidosProcessados = pedidosProcessados;
        this.valorTotalDasVendas = valorTotalDasVendas;
        this.pedidosRejeitados = pedidosRejeitados;
        this.workerProcessando = workerProcessando;
    }

    private boolean incrementEstoque(Long id, Integer quantidade){
      if (estoque.get(id) + quantidade < 0){
        return false;
      }
      estoque.put(id, estoque.get(id) + quantidade);
      return true;
    }

    private void readdItems(ArrayList<Long> itensJaRemovidos, ArrayList<Integer> itensJaRemovidosQtdd){
      for (int i = 0; i < itensJaRemovidos.size(); i++){
        incrementEstoque(itensJaRemovidos.get(i), itensJaRemovidosQtdd.get(i));
      }
    }

    @Override
    public void run() {
        workerProcessando.incrementAndGet();
        try {
            Pedido pedidoAtual = pedidos.poll();
            HashMap<Long,Integer> pedidoDict = pedidoAtual.getPedido();
            ArrayList<Long> itensJaRemovidos = new ArrayList<>();
            ArrayList<Integer> itensJaRemovidosQtdd = new ArrayList<>();

            boolean ok = true;
            for (Long item: pedidoDict.keySet()){
              if (!incrementEstoque(item, -pedidoDict.get(item))){
                readdItems(itensJaRemovidos, itensJaRemovidosQtdd);
                this.pedidosRejeitados.addAndGet(1);
                ok = false;
                break;
              }

              itensJaRemovidos.add(item);
              itensJaRemovidosQtdd.add(pedidoDict.get(item));
            }

            if(ok) {
              this.pedidosProcessados.incrementAndGet();
              this.valorTotalDasVendas.addAndGet(pedidoAtual.getValor().intValue());
              System.out.println("Pedido " + pedidoAtual.getId() + " do cliente " + pedidoAtual.getClient().getId() + " processado");
            }else{
              System.out.println("Pedido " + pedidoAtual.getId() + " do cliente " + pedidoAtual.getClient().getId() + " rejeitado");
            }
        }  catch (Exception e) {
          //e.printStackTrace();
        }
        workerProcessando.decrementAndGet();
    }    
}
