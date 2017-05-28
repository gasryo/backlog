package com.example.backlog.infrastructure

import com.example.backlog.common.ApiContainer
import com.example.backlog.domain.model.entity._
import com.example.backlog.domain.model.valueobject._
import com.example.backlog.domain.repository._
import com.nulabinc.backlog4j.api.option.{GetIssuesCountParams, GetIssuesParams}
import org.joda.time.DateTime

import collection.JavaConversions._
import scala.collection.mutable._

trait MixinApiIssueSearchRepository extends UsingIssueSearchRepository {
  val issueRepository: IssueSearchRepository = ApiIssueSearchRepository
}

object ApiIssueSearchRepository extends IssueSearchRepository {
  def getIssues(issueSearchConditionEntity: IssueSearchConditionEntity)(implicit api: ApiContainer): Seq[IssueEntity] = {
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
      startDate   = if(issue.getStartDate == null) None else Some(new DateTime(issue.getStartDate)),
      dueDate     = if(issue.getDueDate == null) None else Some(new DateTime(issue.getDueDate))
    )
  }

  def getIssuesCount(issueSearchConditionEntity: IssueSearchConditionEntity)(implicit api: ApiContainer): Int = {
    val getIssuesCountParams = new GetIssuesCountParams(api.getProjectIds)
    issueSearchConditionEntity.keyword.foreach(getIssuesCountParams.keyword(_))
    api.client.getIssuesCount(getIssuesCountParams)
  }

  def getIssuesAllCount(issueSearchConditionEntity: IssueSearchConditionEntity)(implicit api: ApiContainer): Int = {
    val getIssuesCountParams = new GetIssuesCountParams(api.getProjectIds)
    api.client.getIssuesCount(getIssuesCountParams)
  }
}