package com.nbe2.domain.auth

import com.nbe2.domain.global.ID
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

class TokenManagerTest :
        BehaviorSpec({
            val tokenRepository = mockk<TokenRepository>()

            val tokenManager = TokenManager(tokenRepository)

            Given("리프레시 토큰이 주어진 경우") {
                val token = "refresh token"
                val refreshToken = RefreshToken.of(ID, token)

                When("토큰을 저장하면") {
                    tokenManager.save(refreshToken)

                    Then("토큰을 저장한다.") {
                        verify(exactly = 1) {
                            tokenRepository.setRefreshToken(refreshToken)
                        }
                    }
                }
            }
        })
