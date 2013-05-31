grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    inherits("global") {
        excludes 'grails-plugin-log4j', 'log4j', "slf4j-jdk14"
    }
    log "warn"

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        compile 'org.grails.plugins:logback:0.3.1'

        compile "com.google.guava:guava:14.0.1",
                "org.yaml:snakeyaml:1.12",
                "com.yammer.metrics:metrics-core:2.2.0",
                "org.slf4j:slf4j-api:1.7.4",
                "org.slf4j:jul-to-slf4j:1.7.4",
                "org.slf4j:log4j-over-slf4j:1.7.4",
                "ch.qos.logback:logback-classic:1.0.13"

        compile("com.yammer.metrics:metrics-jetty:2.2.0",
                "com.yammer.metrics:metrics-servlet:2.2.0") {
            exclude 'javax.servlet'
            exclude "jetty-server"
        }

        //explicitly depend on servlet-api to workaround issue with Ivy and orbit dependencies
        compile ("javax.servlet:javax.servlet-api:3.0.1")
		compile ('org.eclipse.jetty.aggregate:jetty-all:8.1.11.v20130520') {
            exclude 'javax.servlet'
		}
    }

    plugins {
        build(":release:2.2.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }
    }
}

System.setProperty("logback.configurationFile", "${basedir}/grails-app/conf/logback.xml")