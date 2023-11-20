package com.optomi.rewards.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getTransDTM() {
        return transDTM;
    }

    public void setTransDTM(LocalDate transDTM) {
        this.transDTM = transDTM;
    }

    public Double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Double transAmt) {
        this.transAmt = transAmt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "transdtm")
    private LocalDate transDTM;
    @Column(name = "transamt")
    private Double transAmt;

    @ManyToOne
    @JoinColumn(name = "custid")
    private Customer customer;
}
