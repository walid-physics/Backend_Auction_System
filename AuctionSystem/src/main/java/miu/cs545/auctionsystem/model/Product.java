package miu.cs545.auctionsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Double quantity;
    private String imageLink;
    private Double  startingPrice;
    private Double  currentPrice;
    private Double soldPrice;
    private Double deposit;
    @JsonFormat(pattern="yyyy-MM-dd")//, timezone="America/CID")
    private Date bidDueDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date paymentDueDate;

    private ProductStatus status;

    @ManyToOne
    @JsonIgnore
    private User winnerCustomer;

    @OneToOne
    private BiddingSystem biddingSystem;


    @ManyToMany//(cascade = CascadeType.MERGE)
    private List<Category> categories;
    @ManyToOne
    @JsonIgnore
    private User productOwner;





}
