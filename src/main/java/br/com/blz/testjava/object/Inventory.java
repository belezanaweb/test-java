package br.com.blz.testjava.object;

import java.util.List;
import br.com.blz.testjava.object.Warehouses;

 public class Inventory {

         private List<Warehouses> warehouses;

         public Inventory(List<Warehouses> warehouses) {
            this.warehouses = warehouses;
        }

         public Inventory() {
        }

         public int getQuantity() {
            int quantity = 0;
            if (warehouses == null || warehouses.isEmpty()) {
                return quantity;
            }
            for (Warehouses warehouse : warehouses) {
                quantity += warehouse.getQuantity();
            }
            return quantity;
        }

        public List<Warehouses> getWarehouses() {
            return warehouses;
        }
        
        public void setWarehouses (List<Warehouses> warehouses) {
            this.warehouses = warehouses;
        }


 }