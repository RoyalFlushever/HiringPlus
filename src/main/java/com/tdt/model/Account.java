package com.tdt.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
@Table(name = "Accounts")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Account implements java.io.Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -8171909253185646487L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    
    @OneToMany(mappedBy = "account")
    @JsonBackReference
    protected List<User> users;
}
