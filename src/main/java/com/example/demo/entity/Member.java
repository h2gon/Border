package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Member{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;
    
    private String userName;

    private String userPassword;
}