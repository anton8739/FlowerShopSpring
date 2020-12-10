package by.yurovski.dto;

import by.yurovski.entity.Foto;
import by.yurovski.entity.OrderItem;
import by.yurovski.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderItemDTO {
    private int id;
    private List<String> productFotoUrlList;
    private String productTitle;
    private String productDescription;
    private double productCost;
    private int orderItemAmount;
    private String orderItemSize;
    private String orderItemNote;
    public OrderItemDTO(){

    }
    public  OrderItemDTO (OrderItem orderItem){
        this.productFotoUrlList=new ArrayList<>();
        for (Foto foto : orderItem.getProduct().getFotos()){
            this.productFotoUrlList.add(foto.getURL());
        }

        this.productTitle=orderItem.getProduct().getTitle();
        this.productDescription=orderItem.getProduct().getDescription();
        this.productCost=orderItem.getProduct().getCost();
        this.orderItemAmount=orderItem.getAmount();
        this.orderItemSize=orderItem.getSize();
        this.orderItemNote=orderItem.getNote();
        this.id=orderItem.getId();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getProductFotoUrlList() {
        return productFotoUrlList;
    }

    public void setProductFotoUrlList(List<String> productFotoUrlList) {
        this.productFotoUrlList = productFotoUrlList;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public int getOrderItemAmount() {
        return orderItemAmount;
    }

    public void setOrderItemAmount(int orderItemAmount) {
        this.orderItemAmount = orderItemAmount;
    }

    public String getOrderItemSize() {
        return orderItemSize;
    }

    public void setOrderItemSize(String orderItemSize) {
        this.orderItemSize = orderItemSize;
    }

    public String getOrderItemNote() {
        return orderItemNote;
    }

    public void setOrderItemNote(String orderItemNote) {
        this.orderItemNote = orderItemNote;
    }
}
