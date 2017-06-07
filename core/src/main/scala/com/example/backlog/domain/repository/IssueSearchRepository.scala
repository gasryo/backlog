package com.example.backlog.domain.repository

import com.example.backlog.domain.model.entity._

trait UsingIssueSearchRepository {
  val issueRepository: IssueSearchRepository
}

trait IssueSearchRepository {
  def getIssues(issueSearchConditionEntity: IssueSearchConditionEntity): Seq[IssueEntity]
  def getIssuesCount(issueSearchConditionEntity: IssueSearchConditionEntity): Int
  def getIssuesAllCount(issueSearchConditionEntity: IssueSearchConditionEntity): Int
}