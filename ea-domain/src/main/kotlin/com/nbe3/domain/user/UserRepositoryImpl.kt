package com.nbe3.domain.user

import com.nbe3.domain.user.QMedicalPersonInfo.medicalPersonInfo
import com.nbe3.domain.user.QUser.user
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val queryFactory: JPAQueryFactory) : UserRepositoryCustom {

    override fun findPageByApprovalStatus(
        approvalStatus: ApprovalStatus, pageable: Pageable
    ): Page<UserProfileWithLicense> {
        val content: List<UserProfileWithLicense> =
            queryFactory
                .select(
                    Projections.constructor(
                        UserProfileWithLicense::class.java,
                        user.id,
                        user.name,
                        user.email,
                        medicalPersonInfo.license.id
                    )
                )
                .from(user)
                .join(medicalPersonInfo)
                .on(medicalPersonInfo.user.id.eq(user.id))
                .where(equalApprovalStatus(approvalStatus))
                .orderBy(user.createdAt.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()
        val countQuery: JPAQuery<Long> =
            queryFactory
                .select(user.id.count())
                .from(user)
                .where(equalApprovalStatus(approvalStatus))

        return PageableExecutionUtils.getPage(
            content,
            pageable
        ) { countQuery.fetchOne()!! }
    }

    private fun equalApprovalStatus(approvalStatus: ApprovalStatus): BooleanExpression {
        return user.approvalStatus.eq(approvalStatus)
    }
}
