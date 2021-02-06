pipeline {

  agent none

  environment {
    DOCKER_IMAGE = "phuonght/elearning-be"
  }

  stages {
    stage("Test") {
      agent {
          docker {
            image 'openjdk:11'
            args '-u 0:0 -v /tmp:/root/.cache'
          }
      }
      steps {
        sh "mvn clean"
        sh "mvn install"
        sh "mvn package -Dmaven.test.skip=true"
      }
    }
  }

  post {
    success {
      echo "SUCCESSFUL"
    }
    failure {
      echo "FAILED"
    }
  }
}