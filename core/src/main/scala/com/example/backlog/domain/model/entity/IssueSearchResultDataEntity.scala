package com.example.backlog.domain.model.entity

case class IssueSearchResultDataEntity(
    issueEntities: Seq[IssueEntity],
    searchResultDataCount: Int,
    allDataCount: Int
)