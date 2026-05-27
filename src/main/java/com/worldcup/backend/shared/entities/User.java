package com.worldcup.backend.shared.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_USERS")
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private Double points = 100.00;

    // Constructor 
    public User() {}

    @PrePersist
    protected void onCreate() {
        if(this.points == null) {
            this.points = 100.00;
        }
        if(this.username != null) {
            this.username = this.username.trim().toLowerCase();
        }
    }

    // Getters & Setters
    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public String getUsername() { return username;}
    public void setUsername(String username) { this.username = username;}

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password;}

    public Double getPoints() { return points; }
    public void setPoints(Double points) { this.points = points; }
    
}
