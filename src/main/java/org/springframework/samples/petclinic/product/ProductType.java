package org.springframework.samples.petclinic.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name="Product_type")
public class ProductType  extends BaseEntity {

    @Column(unique=true)
    @Size(min=3,max=50)
    String name;



}
