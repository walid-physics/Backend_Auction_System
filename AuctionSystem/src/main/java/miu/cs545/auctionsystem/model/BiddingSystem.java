package miu.cs545.auctionsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor

public class BiddingSystem {
    @Id
    private Integer id;

    private double deposit;

    @OneToOne
    private BiddingProduct biddingProduct;

    @OneToMany
    private List<User> bidders;


    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public BiddingSystem() {
        bidders=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BiddingProduct getBiddingProduct() {
        return biddingProduct;
    }

    public void setBiddingProduct(BiddingProduct biddingProduct) {
        this.biddingProduct = biddingProduct;
    }

    public List<User> getBidders() {
        return bidders;
    }

    public void addBidder(User bidder) {
        this.bidders.add(bidder);
    }
    public void setBidders(List<User> bidders) {
        this.bidders = bidders;
    }
}
