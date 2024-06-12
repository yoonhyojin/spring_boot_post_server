package com.example.msyql_example.member.repository

import com.example.msyql_example.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRoleRepository : JpaRepository<MemberRole, Long?>