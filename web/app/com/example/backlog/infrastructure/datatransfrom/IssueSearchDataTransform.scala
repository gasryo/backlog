package com.example.backlog.infrastructure.datatransfrom

import com.example.backlog.domain.model.entity.IssueSearchConditionEntity
import com.example.backlog.infrastructure.play.form.IssueSearchForm

object IssueSearchDataTransform {
  def formToIssueSearchCondition(form: IssueSearchForm) = IssueSearchConditionEntity(
    offset  = form.iDisplayStart,
    count   = form.iDisplayLength,
    keyword = form.sSearch
  )
}
