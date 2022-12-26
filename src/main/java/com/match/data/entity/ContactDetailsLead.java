package com.match.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_contact_details_lead")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ContactDetailsLead {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String contactType;

    private String contactValue;

    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
