package com.example.backlog.domain.model.valueobject

trait Status {
  val id: Long
  val name: String
}
object Status {
  def apply(id: Long, name: String): Status = StatusImpl(id, name)
}
private case class StatusImpl(id: Long, name: String) extends Status