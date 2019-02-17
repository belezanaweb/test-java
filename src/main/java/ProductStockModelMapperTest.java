import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Spy;

import br.com.blz.testjava.mapper.ProductStockModelMapper;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Stock;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.view.ProductStockView;

public class ProductStockModelMapperTest {
	
	@Spy
	ProductStockModelMapper productStockModelMapper;
	
	
	@Test
	public void toListSumInventoryOK() {
		List<ProductStockView> productStockView = new ArrayList<ProductStockView>();
		
		productStockView = productStockModelMapper.toList(getProductList());
		
		assertEquals(productStockView.get(0).getInventory().getQuantity(), getProductList().get(0).getInventory().getQuantity());
	}
	
	public List<Product> getProductList() {
		List<Product> productList = new ArrayList<Product>();
		
		Product product = new Product();
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		Warehouse warehouse = new Warehouse();
		Stock stock = new Stock();
		
		warehouse.setLocality("SP");
		warehouse.setQuantity(10);
		warehouse.setType("CX");
		warehouses.add(warehouse);
		
		warehouse.setLocality("RJ");
		warehouse.setQuantity(12);
		warehouse.setType("CX");
		warehouses.add(warehouse);

		warehouse.setLocality("PE");
		warehouse.setQuantity(5);
		warehouse.setType("CX");
		warehouses.add(warehouse);

		stock.setWarehouses(warehouses);

		product.setName("Produto 1");
		product.setInventory(stock);
		product.setSku(4999);
		
		productList.add(product);
		

		warehouse.setLocality("PR");
		warehouse.setQuantity(23);
		warehouse.setType("CX");
		warehouses.add(warehouse);
		
		warehouse.setLocality("GO");
		warehouse.setQuantity(5);
		warehouse.setType("CX");
		warehouses.add(warehouse);
		
		stock.setWarehouses(warehouses);
		
		product.setName("Produto 2");
		product.setInventory(stock);
		product.setSku(4565);

		productList.add(product);
		
		
		return productList;
		
	}
}
