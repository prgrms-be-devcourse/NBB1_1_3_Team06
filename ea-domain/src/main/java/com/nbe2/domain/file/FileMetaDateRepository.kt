package com.nbe2.domain.file

import org.springframework.data.jpa.repository.JpaRepository

interface FileMetaDateRepository : JpaRepository<FileMetaData, Long>
