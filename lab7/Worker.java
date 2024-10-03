import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Worker implements Runnable {

    BlockingQueue<Pedido> pedidos;
    ConcurrentHashMap<Long, Integer> estoque;

    public Worker(BlockingQueue<Pedido> pedidos, ConcurrentHashMap<Long, Integer> estoque) {
        this.pedidos = pedidos;
        this.estoque = estoque;
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
      try {
        while (true) {
            Pedido pedidoAtual = pedidos.poll();
            HashMap<Long,Integer> pedidoDict = pedidoAtual.getPedido();

            ArrayList<Long> itensJaRemovidos = new ArrayList<>();
            ArrayList<Integer> itensJaRemovidosQtdd = new ArrayList<>();
            
            boolean rejected = false;

            for (Long item: pedidoDict.keySet()){
              if (!incrementEstoque(item, -pedidoDict.get(item))){
                readdItems(itensJaRemovidos, itensJaRemovidosQtdd);
                rejected = true;
                break;
              }
              itensJaRemovidos.add(item);
              itensJaRemovidosQtdd.add(pedidoDict.get(item));
            }

            Thread.sleep(10);
        }  
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
}
