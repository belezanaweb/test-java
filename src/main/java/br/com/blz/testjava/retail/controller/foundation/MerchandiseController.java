package br.com.blz.testjava.retail.controller.foundation;

//import org.springframework.beans.factory.annotation.Autowired; // gave up for now... 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.retail.model.foundation.Item;

//import br.com.blz.testjava.retail.repository.foundation.MerchandiseRepository;  // too much for now.. 

// Os produtos devem ficar em memória, não é necessário persistir os dados. Não utilize h2
// ""Memoria"" :) Fazendo o que dá para já... 
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    //@Autowired 
    //private MerchandiseRepository merchandiseRepository; // too much for now..
    
    // The beautiful empty memory.. for not long.. 
    private List<Item> itemList = null; 
    

    @GetMapping(value = "/retrieveItem/{SKU}")        
    public Item retrieveItem (@PathVariable String SKU)  throws Exception {
        if (itemList != null ) { 
     
                // poor way of searching data... I am not building a rocket anyway 
                for (Item itemRecord : itemList) 
                {
                    System.out.println("SKU: " + itemRecord.getSKU() + " vs SKU Requested: " +SKU);
                    if (itemRecord.getSKU().equals(SKU)) {
                        System.out.println("Found you...... Not so friendly business message.. who cares now?");
                        return itemRecord;
                    } // else should return that Item/SKU does not exits... 

                }
                return new Item(); // must return.. must return... not elegant at all
        } else {

            System.out.println("This SKU does not exist.. Not so friendly business message.. who cares now?");
            return new Item(); // must return.. must return... not elegant at all
                //throw new Exception ("This SKU does not exist. Item: " + SKU + " ... Not so friendly business message.. but who cares now?"); // keeping simple for now...
         }


    }

    // Having fun to get them all.. 
    @GetMapping(value = "/retrieveAllItems")        
    public List<Item> retrieveAllItems ()  throws Exception {
        if (itemList != null ) {                   
            System.out.println("There ARE merchandise to return.. Not so friendly business message.. who cares now?");
            
            return itemList; // must return.. must return... not elegant at all
        } else {
            System.out.println("There are not merchandise to return.. Not so friendly business message.. who cares now?");
            return new ArrayList<Item>(); // must return.. must return... not elegant at all
                
         }


    }
     
     
   // the output/return could be more elaborated to return better messages after deleting...
    @PostMapping(value = "/deleteItem", consumes = "application/json", produces = "application/json")
    public String deleteItem (@RequestBody Item item)  throws Exception {


        if (itemList != null ) { 

            Item deleteItem = null;
     
            // poorest way of searching data... I am not building a rocket anyway 
            for (Item itemRecord : itemList) {
                System.out.println("SKU: " + itemRecord.getSKU() + " vs SKU Requested: " + item.getSKU());
                if (itemRecord.getSKU().equals(item.getSKU())) {
                    System.out.println("Found you darn it. Let fry you now! ... Not so friendly business message.. who cares now?");
                    deleteItem = itemRecord;                                       
                } 
            }
            if (deleteItem != null) {
                System.out.println("Founded and Fried!");
                itemList.remove(deleteItem); 
                return "Founded and Fried!";
            } else { 
                System.out.println("This SKU does not exist.. Not so friendly business message.. who cares now?");
                return "This SKU does not exist.. Found nothing to kill!";     
            }                    
        } else {
            System.out.println("Memory still empty.. Nothing to Kill!");
            return "Memory still empty.. Nothing to Kill!";            
                
        }

    }

    //@GetMapping(value = "/deleteItem")
    @DeleteMapping(value = "/deleteItem/{SKU}")
    public ResponseEntity<HttpStatus> deleteItem (@PathVariable String SKU)  throws Exception {
        
        Item deleteItem = null;

        if (itemList != null ) { 
     
                // poor way of searching data... I am not building a rocket anyway 
                for (Item itemRecord : itemList) 
                {
                    System.out.println("SKU: " + itemRecord.getSKU() + " vs SKU Requested: " +SKU);
                    if (itemRecord.getSKU().equals(SKU)) {
                        System.out.println("Found you darn it. Let fry you now! ... Not so friendly business message.. who cares now?");
                        deleteItem = itemRecord;
                    } 
                }

                if (deleteItem != null) {
                    System.out.println("Founded and Fried!");
                    itemList.remove(deleteItem); 
                    //return "Founded and Fried!";
                    return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED); 
                } else { 
                    System.out.println("This SKU does not exist.. Not so friendly business message.. who cares now?");
                    //return "This SKU does not exist.. Found nothing to kill!";    
                    return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
                }         

        } else {

            System.out.println("I have no memory yet...");
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND); 
         }
    }
        

    @PostMapping(value = "/createItem", consumes = "application/json", produces = "application/json")    
    public ResponseEntity<Item> createItem(@RequestBody Item item)  throws Exception {      
        if (itemList == null) {
          System.out.println("New Merchandise Memory 'Buffer' ... ");
          this.itemList = new ArrayList<Item>(); 
        }
        
        // Caso um produto já existente em memória tente ser criado com o mesmo sku uma exceção deverá ser lançada
        // poor way of searching data... I am building a rocket anyway 
        for (Item itemRecord : itemList) 
        {
            System.out.println("Old SKU: " + itemRecord.getSKU() + " vs New SKU: " + item.getSKU());
            if (itemRecord.getSKU().equals(item.getSKU())) {
                System.out.println("This SKU already exists.. Not so friendly business message.. who cares now?");
                throw new Exception ("This SKU already exists. Item: " + item.getSKU() + " ... Not so friendly business message.. but who cares now?"); // keeping simple for now...
            } // else should return that Item/SKU does not exits... 

        }

        System.out.println("Freshly New Awesome Merchandise being created... Oven is hot... " + item);
        itemList.add(item);
        
        return new ResponseEntity<Item>(itemList.get(itemList.size() -1), HttpStatus.CREATED);        

     }


    @PutMapping(value = "/updateItem", consumes = "application/json", produces = "application/json")    
    public ResponseEntity<Item> updateItem(@RequestBody Item item)  throws Exception { 
      
        if (itemList == null) {
           System.out.println("I don´t have memory yet, come back later! ");
                throw new Exception ("I don´t have memory yet, come back later!  " + item.getSKU() + " ... Not so friendly business message.. but who cares now?"); // keeping simple for now...
        } 
        

        // poor way of searching data... I am building a rocket anyway 
        for (Item itemRecord : itemList) 
        {
             
            System.out.println("Old SKU: " + itemRecord.getSKU() + " vs New SKU: " + item.getSKU());
            if (itemRecord.getSKU().equals(item.getSKU())) {
                // just ot play with 'updates'
                // simulating 
                System.out.println("This SKU already exists.. Lets update this bastard!");
                itemRecord.setInventory(item.getInventory());
                itemRecord.setName(item.getName());

                item= itemRecord; // swap objects to return 
            } // else should return that Item/SKU does not exits... 
            else 
            {
                System.out.println("This SKU does not exist.. Not so friendly business message.. who cares now?");
                throw new Exception ("This SKU does not exist. Item: " + item.getSKU() + " ... Not so friendly business message.. but who cares now?"); // keeping simple for now...
            }

        }

        //Response.setHeader("Item", ServletUriComponentsBuilder.fromCurrentContextPath().path("/findItem/" + item.getSKU()).toUriString());
        return new ResponseEntity<Item>(item, HttpStatus.OK);
              

     }


    // playing with regular url parameters type .. too ugly
    /*
    @PostMapping
    public Item add(String SKU, String Name, String locality, Long quantity) {
        if (SKU == null) {
            SKU = new String("New Item");
        } 
        if (SKU.equals("")) { 
            SKU = new String("New Item");
        } 
      

        if (itemList == null) {
          this.itemList = new ArrayList<Item>(); 
        }
        itemList.add(new Item(SKU, Name, locality, quantity));
        
        return itemList.get(itemList.size() -1);               

     }
     */

}