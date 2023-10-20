package miu.cs545.auctionsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
//@DiscriminatorValue("bidding")
public class BiddingProduct{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double bidPrice;
    private double deposit;
    private LocalDate bidDueDate;
    private LocalDate biddingPaymentDueDate;
    private boolean status;



    @OneToOne
    private BiddingSystem biddingSystem;

    @OneToOne
    private Product product;

    @ManyToOne
    private User owner;

    @ManyToOne
    private User seller;



    public BiddingProduct() {
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BiddingSystem getBiddingSystem() {
        return biddingSystem;
    }

    public void setBiddingSystem(BiddingSystem biddingSystem) {
        this.biddingSystem = biddingSystem;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBidStartingPrice() {
        return bidPrice;
    }

    public void setBidStartingPrice(double bidStartingPrice) {
        this.bidPrice = bidStartingPrice;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public LocalDate getBidDueDate() {
        return bidDueDate;
    }

    public void setBidDueDate(LocalDate bidDueDate) {
        this.bidDueDate = bidDueDate;
    }

    public LocalDate getBiddingPaymentDueDate() {
        return biddingPaymentDueDate;
    }

    public void setBiddingPaymentDueDate(LocalDate biddingPaymentDueDate) {
        this.biddingPaymentDueDate = biddingPaymentDueDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
