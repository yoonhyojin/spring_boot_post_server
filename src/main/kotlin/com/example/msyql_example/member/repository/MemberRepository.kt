package com.example.msyql_example.member.repository

import com.example.msyql_example.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long?> {
    fun findByEmail(email : String) : Member?
}