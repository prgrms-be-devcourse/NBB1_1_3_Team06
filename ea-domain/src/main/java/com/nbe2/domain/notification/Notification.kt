package com.nbe2.domain.notification

import com.nbe2.domain.global.BaseTimeEntity
import com.nbe2.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "notifications")
class Notification private constructor(
    target: User,
    @Column(nullable = false) var referenceUri: String,
    @Column(
        nullable = false,
        columnDefinition = "TEXT"
    ) var title: String,
    @Enumerated(value = EnumType.STRING) val type: NotificationType
) : BaseTimeEntity() {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    val target: User = target

    val isRead = false

    companion object {
        fun of(target: User, referenceUri: String, title: String, type: NotificationType) =
            Notification(
                target = target,
                referenceUri = referenceUri,
                title = title,
                type = type
            )
    }
}
