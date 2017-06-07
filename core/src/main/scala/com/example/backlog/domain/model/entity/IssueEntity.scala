package com.example.backlog.domain.model.entity

import java.time.ZonedDateTime
import com.example.backlog.domain.model.valueobject._

case class IssueEntity(
    id: Long,
    projectId: Long,
    issueKey: String,
    issueType: IssueType,
    summary: String,
    description: String,
    priority: Priority,
    status: Status,
    startDate: Option[ZonedDateTime],
    dueDate: Option[ZonedDateTime]
)