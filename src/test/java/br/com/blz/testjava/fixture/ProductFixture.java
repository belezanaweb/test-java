package br.com.blz.testjava.fixture;

import br.com.blz.testjava.business.domain.Inventory;
import br.com.blz.testjava.business.domain.Product;
import br.com.blz.testjava.business.domain.Warehouse;
import br.com.blz.testjava.business.domain.WarehouseType;
import br.com.blz.testjava.dto.ProductDto;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ProductFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Product.class).addTemplate("valid-product", new Rule() {{
			add("sku", 1987l);
			add("name", "esmalte");
			add("inventory", one(Inventory.class, "valid-inventory"));
		}});
		
		Fixture.of(Inventory.class).addTemplate("valid-inventory", new Rule() {{
			add("id", 33l);
		}});
		
		Fixture.of(Warehouse.class).addTemplate("valid-warehouse-sp", new Rule() {{
			add("id", 14l);
			add("locality", "sp");
		}});
		
		Fixture.of(Warehouse.class).addTemplate("valid-warehouse-curitiba", new Rule() {{
			add("id", 12l);
			add("locality", "curitiba");
		}});
		
		Fixture.of(Product.class).addTemplate("valid-product-all", new Rule() {{
			add("sku", 1987l);
			add("name", "esmalte");
			add("isMarketable", true);
			add("inventory", one(Inventory.class, "valid-inventory-all"));
		}});
		
		Fixture.of(Inventory.class).addTemplate("valid-inventory-all", new Rule() {{
			add("id", 33l);
			add("quantity", 5);
			add("warehouses", has(1).of(Warehouse.class, "valid-warehouse-all"));
		}});
		
		Fixture.of(Warehouse.class).addTemplate("valid-warehouse-all", new Rule() {{
			add("id", 12l);
			add("locality", "ribeirao");
			add("quantity", 5);
			add("type", WarehouseType.ECOMMERCE);
		}});
		
		Fixture.of(ProductDto.class).addTemplate("valid-productdto", new Rule() {{
			add("sku", 1987);
			add("name", "esmalte");
		}});
	}
}
