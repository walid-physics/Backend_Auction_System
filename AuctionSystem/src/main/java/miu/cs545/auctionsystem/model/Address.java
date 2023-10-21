package miu.cs545.auctionsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String state;
    private String city;

    private String zipCode;
}
