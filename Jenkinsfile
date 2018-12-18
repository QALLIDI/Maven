pipeline {
      agent any
      options {
            buildDiscarder(logRotator(numToKeepStr:'10'))
      }
      tools {
          maven 'Maven 3.5.2'
          jdk 'jdk1.8.0_151'
       }
      stages {
          stage('Build') {
              steps {
                bat 'mvn install'
                bat 'bundle exec rake build spec'
                archive includes: 'pkg/*.gem'
                    
                     publishHTML target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'coverage',
                        reportFiles: 'index.html',
                        reportName: 'RCov Report'
                      ]
              }
              post {
                success {
                junit 'target/surefire-reports/**/*.xml'
                }
              }  
          }
         stage('Test') {
            steps {
                bat 'bundle exec rake spec'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
      } 
}       
