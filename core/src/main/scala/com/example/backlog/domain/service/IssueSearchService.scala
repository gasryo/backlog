package com.example.backlog.domain.service

import com.example.backlog.common.ApiContainer
import com.example.backlog.domain.model.entity._
import com.example.backlog.domain.repository.UsingIssueSearchRepository

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

trait IssueSearchService extends UsingIssueSearchRepository {
  def search(issueSearchConditionEntity: IssueSearchConditionEntity)(implicit api: ApiContainer): IssueSearchResultDataEntity = {
    val issuesFuture         = Future{issueRepository.getIssues(issueSearchConditionEntity)}
    val issuesCountFuture    = Future{issueRepository.getIssuesCount(issueSearchConditionEntity)}
    val issuesAllCountFuture = Future{issueRepository.getIssuesAllCount(issueSearchConditionEntity)}

    val resultFuture = for {
      issues         <- issuesFuture
      issuesCount    <- issuesCountFuture
      issuesAllCount <- issuesAllCountFuture
    } yield IssueSearchResultDataEntity(
      issueEntities         = issues,
      searchResultDataCount = issuesCount,
      allDataCount          = issuesAllCount
    )

    Await.result(resultFuture, Duration.Inf)
  }
}