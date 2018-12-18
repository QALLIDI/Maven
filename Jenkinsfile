pipeline {
      agent any
      tools {
          maven 'Maven 3.5.2'
          jdk 'jdk1.8.0_151'
       }
      stages {
          stage('Build') {
              steps {
                bat 'mvn install'
              }
              post {
                success {
                junit 'target/surefire-reports/**/*.xml'
                }
              }  
          }
         stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Site') {
            steps {
                bat 'mvn clean site'
            }
            post {
                success {
                    bat "echo 'site creation successed'"
                }
                failure {
                    bat "echo 'site creation failed !'"
                }
            }
        }
        stage('Cobertura') {
            steps {
                bat 'mvn cobertura:cobertura'
            }
              post {
                 success {
                    bat  "echo 'Publishing Cobertura Report successed !'"
                }
                 failure {
                     bat  "echo 'Publishing Cobertura Report failed !'"
                }
            }
        }
      } 
}       
