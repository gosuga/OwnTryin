package com.persistence.trial.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem extends  BaseEntity implements Serializable {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getOrderId() {
  return orderId;
 }

 public void setOrderId(String orderId) {
  this.orderId = orderId;
 }

 public String getProductId() {
  return productId;
 }

 public void setProductId(String productId) {
  this.productId = productId;
 }

 public Long getProductQuantity() {
  return productQuantity;
 }

 public void setProductQuantity(Long productQuantity) {
  this.productQuantity = productQuantity;
 }

 public String getPrice() {
  return price;
 }

 public void setPrice(String price) {
  this.price = price;
 }

 public String getProductName() {
  return productName;
 }

 public void setProductName(String productName) {
  this.productName = productName;
 }

 public Float getBasePrice() {
  return basePrice;
 }

 public void setBasePrice(Float basePrice) {
  this.basePrice = basePrice;
 }

 @NotNull
    @Column(name = "order_id")
    private String orderId;

    @NotNull
    @Column(name = "product_id")
    private String productId;


    @Column(name = "product_quantity")
    private Long productQuantity;

    @Column(name = "price")
    private String price;

    @Column(name = "pr_name")
    private String productName;

    @NotNull
    @Column(name = "base_price")
    private Float basePrice;





}
