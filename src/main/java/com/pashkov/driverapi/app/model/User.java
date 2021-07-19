package com.pashkov.driverapi.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    private String password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    private Set<Advice> likedAdvices;

    @ManyToMany
    private Set<Advice> sharedAdvices;

    @ManyToMany
    private Set<Training> completeTrainings;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Training> getCompleteTrainings() {
        return completeTrainings;
    }

    public void setCompleteTrainings(Set<Training> completeTrainings) {
        this.completeTrainings = completeTrainings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nickName) {
        this.username = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Advice> getLikedAdvices() {
        return likedAdvices;
    }

    public void setLikedAdvices(Set<Advice> likedAdvices) {
        this.likedAdvices = likedAdvices;
    }

    public Set<Advice> getSharedAdvices() {
        return sharedAdvices;
    }

    public void setSharedAdvices(Set<Advice> sharedAdvices) {
        this.sharedAdvices = sharedAdvices;
    }
}
