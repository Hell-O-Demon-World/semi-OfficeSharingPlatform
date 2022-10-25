package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; //업체식별번호

    @Column(name = "CO_NAME", length = 30)
    private String companyName;

    @Column(name = "CO_TEL", length = 22)
    private String companyTel;

    @Column(name = "CO_REGNUM", length = 12)
    private String registrationNumber;

    @Column(name = "CO_CHAIRMANNAME", length = 20)
    private String chairmanName;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", foreignKey=@ForeignKey, nullable=false)
    private Address addressId;
}
