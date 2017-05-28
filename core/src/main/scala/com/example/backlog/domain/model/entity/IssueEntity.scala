package com.example.backlog.domain.model.entity

import com.example.backlog.domain.model.valueobject._
import org.joda.time.DateTime

case class IssueEntity(
    id: Long,
    projectId: Long,
    issueKey: String,
    issueType: IssueType,
    summary: String,
    description: String,
    priority: Priority,
    status: Status,
    startDate: Option[DateTime],
    dueDate: Option[DateTime]
)