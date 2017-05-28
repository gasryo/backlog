package com.example.backlog.application

import com.example.backlog.common.Api
import com.example.backlog.domain.model.entity._
import com.example.backlog.domain.service._
import com.example.backlog.infrastructure._

class IssueService extends Api {
  val issueSearchService = new IssueSearchService with MixinApiIssueSearchRepository

  def search(
        issueSearchConditionEntity: IssueSearchConditionEntity
      ) = issueSearchService.search(issueSearchConditionEntity)
}
