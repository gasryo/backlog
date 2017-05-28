package com.example.backlog.domain.model.valueobject

trait Priority {
  val id: Long
  val name: String
}
object Priority {
  def apply(id: Long, name: String): Priority = PriorityImpl(id, name)
}
private case class PriorityImpl(id: Long, name: String) extends Priority