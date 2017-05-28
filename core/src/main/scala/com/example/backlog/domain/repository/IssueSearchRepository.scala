package com.example.backlog.domain.repository

import com.example.backlog.common.ApiContainer
import com.example.backlog.domain.model.entity._

trait UsingIssueSearchRepository {
  val issueRepository: IssueSearchRepository
}
trait IssueSearchRepository {
  def getIssues(issueSearchConditionEntity: IssueSearchConditionEntity)
      (implicit api: ApiContainer): Seq[IssueEntity]
  def getIssuesCount(issueSearchConditionEntity: IssueSearchConditionEntity)(implicit api: ApiContainer): Int
  def getIssuesAllCount(issueSearchConditionEntity: IssueSearchConditionEntity)(implicit api: ApiContainer): Int
}