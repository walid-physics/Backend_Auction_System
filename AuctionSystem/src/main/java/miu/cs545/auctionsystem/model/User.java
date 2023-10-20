package miu.cs545.auctionsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<BiddingProduct> biddingProductOwned = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<BiddingProduct> biddingProductSold = new ArrayList<>();

    @OneToMany(mappedBy = "productOwner")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private BiddingSystem biddingSystem;

    public BiddingSystem getBiddingSystem() {
        return biddingSystem;
    }

    public void setBiddingSystem(BiddingSystem biddingSystem) {
        this.biddingSystem = biddingSystem;
    }

    public List<BiddingProduct> getBiddingProductOwned() {
        return biddingProductOwned;
    }

    public void setBiddingProductOwned(List<BiddingProduct> biddingProductOwned) {
        this.biddingProductOwned = biddingProductOwned;
    }

    public List<BiddingProduct> getBiddingProductSold() {
        return biddingProductSold;
    }

    public void setBiddingProductSold(List<BiddingProduct> biddingProductSold) {
        this.biddingProductSold = biddingProductSold;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @JoinTable(name = "users_roles")
    private List<Role> roles;
    private String email;

    private String password;

    private Boolean active;
    @Embedded
    private Address address;
    private Integer licenseNumber;



    public User() {
        roles = new ArrayList<>();
    }

    public String bcryptEncoderPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRoles(Role role) {
        this.roles.add(role);
    }


    public void setRole(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

}
