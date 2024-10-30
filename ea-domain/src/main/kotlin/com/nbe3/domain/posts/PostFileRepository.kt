package com.nbe3.domain.posts

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter
import org.springframework.jdbc.core.PreparedStatementSetter
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement

@Repository
class PostFileRepository (private val jdbcTemplate: JdbcTemplate){
    fun deleteByPostId(postId: Long?) {
        jdbcTemplate.update(DELETE_BY_POST, PreparedStatementSetter { ps: PreparedStatement ->
            if (postId != null) {
                ps.setLong(1, postId )
            }
        })
    }

    fun saveAll(postFiles: List<PostFile?>) {
        jdbcTemplate.batchUpdate<PostFile>(
            SAVE_ALL,
            postFiles,
            postFiles.size,
            ParameterizedPreparedStatementSetter<PostFile> { ps: PreparedStatement, postFile: PostFile ->
                ps.setLong(1, postFile.fileMetaDataId)
                ps.setLong(2, postFile.postId)
            })
    }

    companion object {
        private const val DELETE_BY_POST = "DELETE FROM post_files WHERE post_id = ?"
        private val SAVE_ALL = """
            INSERT INTO post_files (file_id, post_id)
             VALUES (?, ?)
            """.trimIndent()
    }
}
