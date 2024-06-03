package com.example.msyql_example.member.entity

import com.example.msyql_example.common.enums.Gender
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_email", columnNames = ["email"])]
)
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long?,

    @Column(nullable = false, length = 100, updatable = false)
    val email : String,

    @Column(nullable = false, length = 100)
    val password : String,

    @Column(nullable = false, length = 10)
    val name : String,

    @Column(nullable = false, length = 30)
    @Temporal(TemporalType.DATE)
    val birthday : LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    val gender : Gender,
)