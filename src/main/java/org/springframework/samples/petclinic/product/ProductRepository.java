package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ProductRepository  extends CrudRepository<Product,Integer>{
    List<Product> findAll();
    @Query("SELECT DISTINCT pt FROM ProductType pt")
    List<ProductType> findAllProductTypes();

    @Query("SELECT DISTINCT pt FROM ProductType pt WHERE pt.name=:name")
    ProductType getProductType(String name);

    @Query("SELECT DISTINCT pr FROM Product pr WHERE pr.price<:price")
    List<Product> findByPriceLessThan(Double price);
    Optional<Product> findById(int id);
    Product findByName(String name);
    Product save(Product p);
}
