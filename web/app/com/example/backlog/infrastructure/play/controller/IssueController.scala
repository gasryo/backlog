package com.example.backlog.infrastructure.play.controller

import javax.inject.Inject

import com.example.backlog.application._
import com.example.backlog.infrastructure.datatransfrom._
import com.example.backlog.infrastructure.play.form._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import com.example.backlog.infrastructure.play.views.html._

class IssueController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  val issueService = new IssueService
  val issueSearchForm = Form(
    mapping(
      "iDisplayStart"  -> longNumber,
      "iDisplayLength" -> number(min = 1, max = 100),
      "sSearch"        -> optional(text),
      "sEcho"          -> text
    )(IssueSearchForm.apply)(IssueSearchForm.unapply)
  )

  def index = Action {
    Ok(issue.index())
  }

  def search = Action { implicit request =>
    issueSearchForm.bindFromRequest.fold(
      formWithErrors => {
        Ok(IssueSearchResultDataTransform.errorToJson)
      },
      formData => {
        val issueSearchConditionEntity = IssueSearchDataTransform.formToIssueSearchCondition(formData)
        Ok(IssueSearchResultDataTransform.toJson(issueService.search(issueSearchConditionEntity), formData.sEcho))
      }
    )
  }
}
