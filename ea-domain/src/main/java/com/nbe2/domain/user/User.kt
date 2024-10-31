package com.nbe2.domain.user

import com.nbe2.domain.global.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "users", indexes = [Index(name = "idx_email", columnList = "email")])
class User private constructor (
    @Column(nullable = false) var name: String,

    @Column(nullable = false) var email: String,

    var password: String,

    @Enumerated(value = EnumType.STRING) var role: UserRole,

    @Enumerated(value = EnumType.STRING) var approvalStatus: ApprovalStatus
) : BaseTimeEntity() {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne(optional = false, mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var medicalPersonInfo: MedicalPersonInfo? = null

    fun assignMedicalRole(medicalPersonInfo: MedicalPersonInfo) {
        this.medicalPersonInfo = medicalPersonInfo
        this.role = UserRole.MEDICAL_PERSON
        this.approvalStatus = ApprovalStatus.PENDING
    }

    fun approve() {
        this.approvalStatus = ApprovalStatus.APPROVED
    }

    fun update(profile: UpdateProfile) {
        this.name = profile.name
        this.email = profile.email
    }

    fun changePassword(password: String) {
        this.password = password
    }

    companion object {
        fun of(name: String, email: String, password: String) = User(
            name = name,
            email = email,
            password = password,
            role = UserRole.USER,
            approvalStatus = ApprovalStatus.APPROVED
        )
    }
}
