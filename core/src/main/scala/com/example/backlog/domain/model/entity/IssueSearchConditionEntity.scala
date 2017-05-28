package com.example.backlog.domain.model.entity

case class IssueSearchConditionEntity(
  offset: Long,
  count: Int,
  keyword: Option[String]
)
