package com.expense_manager_api.expenseManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")

public class Expense {

    @Id
    private Long id;

    private String name;

    private String description;

    private BigDecimal amount;

    private String category;

    private Date date;

    private Timestamp createdAt;

    private Timestamp updatedAt;


    public void setUser(User loggedInUser) {

    }
}
