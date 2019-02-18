
package br.com.blz.testjava.respositories;

import br.com.blz.testjava.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    
}
