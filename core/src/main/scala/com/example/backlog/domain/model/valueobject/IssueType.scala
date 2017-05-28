package com.example.backlog.domain.model.valueobject

trait IssueType {
  val id: Long
  val name: String
}
object IssueType {
  def apply(id: Long, name: String): IssueType = IssueTypeImpl(id, name)
}
private case class IssueTypeImpl(id: Long, name: String) extends IssueType