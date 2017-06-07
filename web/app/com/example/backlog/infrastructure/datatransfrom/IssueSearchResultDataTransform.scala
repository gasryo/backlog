package com.example.backlog.infrastructure.datatransfrom

import java.time.format.DateTimeFormatter
import com.example.backlog.domain.model.entity._
import play.api.libs.json.Json
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future

object IssueSearchResultDataTransform {
  def toJson(
        issueSearchResultDataEntityFuture: Future[IssueSearchResultDataEntity],
        sEcho: String
      ) = issueSearchResultDataEntityFuture.map(
    issueSearchResultDataEntity => Json.obj(
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
          issueEntity.startDate.map(_.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))).getOrElse(null),
          issueEntity.dueDate.map(_.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))).getOrElse(null)
        )
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