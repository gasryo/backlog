package com.example.backlog.infrastructure.datatransfrom

import com.example.backlog.domain.model.entity._
import play.api.libs.json.Json

object IssueSearchResultDataTransform {
  def toJson(issueSearchResultDataEntity: IssueSearchResultDataEntity, sEcho: String) = Json.obj(
    "iTotalRecords"        -> issueSearchResultDataEntity.allDataCount,
    "iTotalDisplayRecords" -> issueSearchResultDataEntity.searchResultDataCount,
    "sEcho"                -> sEcho,
    "aaData"               -> issueSearchResultDataEntity.issueEntities.map(
      issueEntity => Json.arr(
        issueEntity.issueType.name,
        issueEntity.issueKey,
        issueEntity.summary,
        issueEntity.priority.name,
        issueEntity.status.name,
        issueEntity.startDate.map(_.toString("yyyy/MM/dd")).getOrElse(null),
        issueEntity.dueDate.map(_.toString("yyyy/MM/dd")).getOrElse(null)
      )
    )
  )

  def errorToJson = Json.obj(
    "iTotalRecords"        -> 0,
    "iTotalDisplayRecords" -> 0,
    "sEcho"                -> 0,
    "aaData"               -> Json.arr()
  )
}