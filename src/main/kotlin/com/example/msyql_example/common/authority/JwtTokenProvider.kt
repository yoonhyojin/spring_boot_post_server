package com.example.msyql_example.common.authority

import com.example.msyql_example.common.dto.TokenInfo
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

const val EXPIRATION_MILLISECONDS : Long = 1000 * 60 * 30L

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    private lateinit var secretKey : String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    /**
     * 토큰 생성
     */
    fun createToken(authentication : Authentication) : TokenInfo {
        val authorities : String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()

        val accessExpiration = Date(now.time + EXPIRATION_MILLISECONDS)

        val accessToken = Jwts
            .builder()
            .subject(authentication.name)
            .claim("auth", authorities)
            .issuedAt(now)
            .expiration(accessExpiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()

        return TokenInfo(grantType = "Bearer", accessToken = accessToken)
    }

    /**
     * 토큰에서 권한 추출
     */
    fun getAuthentication(token : String) : Authentication {
        val claims : Claims = getClaims(token)

        val auth = claims["auth"] ?: throw RuntimeException("유효하지 않은 토큰입니다!")

        val authorities : Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal : UserDetails = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    /**
     * 토큰 검증
     */
    fun validateToken(token : String) : Boolean {
        try {
            getClaims(token)
            return true
        } catch (e : Exception) {
            println(e.message)
        }
        return false

    }

    /**
     * 클레임 추출
     */
    private fun getClaims(token : String) : Claims =
        Jwts
            .parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
}