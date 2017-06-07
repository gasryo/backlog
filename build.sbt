import sbt.Keys._

version := "1.0"

name := "backlog"

scalaVersion := "2.11.8"

lazy val configMode = sys.props.getOrElse("CONFIG_MODE", default = "development")

lazy val core = (project in file("core"))
  .disablePlugins (plugins.JUnitXmlReportPlugin)
  .settings(commonSettings)

lazy val web = (project in file("web")).dependsOn(core)
  .enablePlugins(PlayScala)
  .disablePlugins (plugins.JUnitXmlReportPlugin)
  .settings(commonSettings)
  .settings(logSettingsForWeb)
  .settings(
    unmanagedResourceDirectories in Compile += baseDirectory.value / "../conf",

    libraryDependencies ++= coreDeps,
    routesGenerator := InjectedRoutesGenerator
  )

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  libraryDependencies ++= coreDeps,
  resolvers ++= Seq("snapshots", "releaes").map(Resolver.sonatypeRepo),
  resolvers += Resolver.jcenterRepo,

  fork := true,

  parallelExecution in Test := false,

  javaOptions += "-Dconfig.file=" + baseDirectory.value / "../conf/application.conf",

  unmanagedResourceDirectories in Compile += baseDirectory.value / "../conf",

  bashScriptExtraDefines := Seq(
    """
      |CONFIG_MODE="${CONFIG_MODE:-development}"
      |addJava "-Dconfig.file=${app_home}/../conf/$CONFIG_MODE.conf"
    """.stripMargin,
    """addJava "-Dapp.home=${app_home}/../""""
  )
)

lazy val logSettings = Seq(
  javaOptions in Compile += "-Dlogback.configurationFile=" + baseDirectory.value / "../conf/logback-%s-%s.xml".format(name.value, configMode),
  bashScriptExtraDefines += """addJava "-Dlogback.configurationFile=${app_home}/../conf/logback-%s-$CONFIG_MODE.xml"""".format(name.value)
)

lazy val logSettingsForWeb = Seq(
  javaOptions in Compile += "-Dlogger.file=" + baseDirectory.value / "../conf/logback-%s-%s.xml".format(name.value, configMode),
  bashScriptExtraDefines +=  """addJava "-Dlogger.file=${app_home}/../conf/logback-%s-$CONFIG_MODE.xml"""".format(name.value)
)

lazy val playVersion            = "2.5.15"
lazy val config_mode            = sys.props.getOrElse("CONFIG_MODE", "development")

lazy val coreDeps = Seq(
  // Config & Log
  Seq(
    "com.typesafe"                   %  "config"          % "1.3.0",
    "com.iheart"                     %% "ficus"           % "1.2.3",
    "org.slf4j"                      %  "slf4j-api"       % "1.7.21",
    "ch.qos.logback"                 %  "logback-classic" % "1.1.7"
  ),
  // web
  Seq(
    "com.typesafe.play"                %% "play-cache"      % playVersion
  ),
  // backlog
  Seq(
    "com.nulab-inc" % "backlog4j" % "2.2.0"
  )
).flatten

onLoadMessage <<= (name,crossVersion,scalaVersion,scalaBinaryVersion,onLoadMessage){
  (name,crossV,scalaV,binaryV,currentMessage) =>
    println("\n" + name + "\nscalaVersion = "+ scalaV + ", crossVersion = " + crossV + ", binaryVersion = " + binaryV + "\n")
    currentMessage
}
