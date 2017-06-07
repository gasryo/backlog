package com.example.backlog.infrastructure

import java.time.ZoneId

import com.example.backlog.common.Api
import com.example.backlog.domain.model.entity._
import com.example.backlog.domain.model.valueobject._
import com.example.backlog.domain.repository._
import com.nulabinc.backlog4j.api.option.{GetIssuesCountParams, GetIssuesParams}

import collection.JavaConversions._
import scala.collection.mutable._

trait MixinApiIssueSearchRepository extends UsingIssueSearchRepository {
  val issueRepository: IssueSearchRepository = ApiIssueSearchRepository
}

object ApiIssueSearchRepository extends IssueSearchRepository with Api {
  def getIssues(issueSearchConditionEntity: IssueSearchConditionEntity): Seq[IssueEntity] = {
    val getIssuesParams = new GetIssuesParams(api.getProjectIds)
    getIssuesParams.offset(issueSearchConditionEntity.offset)
    getIssuesParams.count(issueSearchConditionEntity.count)
    issueSearchConditionEntity.keyword.foreach(getIssuesParams.keyword(_))

    for (issue <- api.client.getIssues(getIssuesParams)) yield IssueEntity(
      id          = issue.getId,
      projectId   = issue.getProjectId,
      issueKey    = issue.getIssueKey,
      issueType   = IssueType(
        id   = issue.getIssueType.getId,
        name = issue.getIssueType.getName
      ),
      summary     = issue.getSummary,
      description = issue.getDescription,
      priority    = Priority(
        id   = issue.getPriority.getId,
        name = issue.getPriority.getName
      ),
      status      = Status(
        id = issue.getStatus.getId,
        name = issue.getStatus.getName
      ),
      startDate   = if(issue.getStartDate == null) None else Some(issue.getStartDate.toInstant().atZone(ZoneId.systemDefault())),
      dueDate     = if(issue.getDueDate == null) None else Some(issue.getDueDate.toInstant().atZone(ZoneId.systemDefault()))
    )
  }

  def getIssuesCount(issueSearchConditionEntity: IssueSearchConditionEntity): Int = {
    val getIssuesCountParams = new GetIssuesCountParams(api.getProjectIds)
    issueSearchConditionEntity.keyword.foreach(getIssuesCountParams.keyword(_))
    api.client.getIssuesCount(getIssuesCountParams)
  }

  def getIssuesAllCount(issueSearchConditionEntity: IssueSearchConditionEntity): Int = {
    val getIssuesCountParams = new GetIssuesCountParams(api.getProjectIds)
    api.client.getIssuesCount(getIssuesCountParams)
  }
}