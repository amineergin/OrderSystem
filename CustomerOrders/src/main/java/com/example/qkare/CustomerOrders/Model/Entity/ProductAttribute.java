package com.example.qkare.CustomerOrders.Model.Entity;

import com.example.qkare.CustomerOrders.Model.Entity.Attributes.AttributeValidator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Table(name = "product_attribute")
@Entity
@Data
@RequiredArgsConstructor
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attribute_name", columnDefinition = "VARCHAR(50)")
    private String attributeName;

    @Column(name = "attribute_value", columnDefinition = "VARCHAR(50)")
    private String attributeValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Product product;

    private void validateAttribute(){
        if(!AttributeValidator.validateAttribute(this.attributeName, this.attributeValue, this.product.getCategory())) {
            throw new IllegalArgumentException("Invalid value for attribute: " + this.attributeName);
        }
    }
}
