package com.fmont.recipe.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public String getUnitOfMeasure() {
        return description;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.description = unitOfMeasure;
    }
}
