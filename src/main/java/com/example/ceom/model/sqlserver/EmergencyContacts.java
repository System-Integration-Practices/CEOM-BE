package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Emergency_Contacts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContacts {
    @Id
    private Long id;

    @Column(name = "Emergency_Contact_Name")
    private String emergencyContactName;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Relationship")
    private String relationship;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_ID")
    private Person person;
}
