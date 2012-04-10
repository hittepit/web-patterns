name := "Demo-Web"

version := "1.0"

scalaVersion := "2.9.1"

seq(webSettings :_*)

libraryDependencies += "org.scalatra" %% "scalatra" % "2.0.3"

libraryDependencies += "org.scalatra" %% "scalatra-scalate" % "2.0.3"

libraryDependencies += "org.scalatra" %% "scalatra-fileupload" % "2.0.3"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.0.3.v20111011" % "container"

libraryDependencies += "javax.servlet" % "servlet-api" % "2.5" % "provided"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.0"