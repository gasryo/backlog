package com.example.backlog.infrastructure.play.form

case class IssueSearchForm(
  iDisplayStart: Long,
  iDisplayLength: Int,
  sSearch: Option[String],
  sEcho: String
)
