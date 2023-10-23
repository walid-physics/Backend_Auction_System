package miu.cs545.auctionsystem.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BalanceTransaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private TransactionType transactionType;

    private Date transactionDate;
    private double amount;
    @ManyToOne
    private User user;




}
