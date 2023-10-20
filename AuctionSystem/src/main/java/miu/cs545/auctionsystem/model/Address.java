package miu.cs545.auctionsystem.model;

import jakarta.persistence.*;

@Embeddable
public class Address {
    private String state;
    private String city;

    private String zipCode;
}
