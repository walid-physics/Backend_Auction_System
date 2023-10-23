package miu.cs545.auctionsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements UserDetails {
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

    @OneToMany(mappedBy = "user")
    private List<BalanceTransaction> balanceTransaction;

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

    @ManyToMany(cascade =  CascadeType.MERGE)
    @JoinTable(name = "users_roles")
    private List<Role> roles = new ArrayList<>();;
    private String email;

    private String password;

    private Boolean active;
    @Embedded
    private Address address;
    private Integer licenseNumber;

    private Double  balance;







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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles!=null)
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
    public List<String> getAuthoritiesList() {
        if(roles!=null)
        return roles.stream().map(role ->  role.getName())
                .collect(Collectors.toList());
        return null;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
