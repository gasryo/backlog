package com.example.backlog.common

import com.nulabinc.backlog4j.{BacklogClient, Project}

import scala.collection.JavaConversions._
import scala.collection.mutable._

case class ApiContainer(
    client: BacklogClient,
    project: Project
) {
  def getProjectIds : java.util.List[String] = ArrayBuffer(project.getIdAsString)
}