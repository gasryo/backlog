package com.example.backlog.common

import com.nulabinc.backlog4j.BacklogClientFactory
import com.nulabinc.backlog4j.conf.BacklogJpConfigure
import com.typesafe.config.ConfigFactory

trait Api {
  implicit val api = BacklogApi.api
}

object BacklogApi {
  val applicationConfig = ConfigFactory.load()
  val configure         = new BacklogJpConfigure(applicationConfig.getString("backlog.spaceId")).apiKey(applicationConfig.getString("backlog.apiKey"))
  val client            = new BacklogClientFactory(configure).newClient()
  val project           = client.getProject(applicationConfig.getString("backlog.projectName"))
  val api               = ApiContainer(client, project)
}