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
                     bat "echo 'site has been created'"     
                    }
                    failure {
                     bat "echo 'site has not been created'"     
                    }
              } 
        stage('Cobertura') {
            steps {
                bat 'mvn cobertura:cobertura'
            }
             post {
                  always {
                        cobertura coberturaReportFile: '**/target/site/cobertura/coverage.xml'
                        cleanWs()
                        }
                  }
        }
      }
      
       stage('Sonarqube analysis') {
    steps {
    script {
             scannerHome = tool 'SonarScanner';
        }
     withSonarQubeEnv('SonarQube') {
       bat "${scannerHome}\\bin\\sonar-scanner.bat" 
    }

    } 
        }
                     }
        stage("build & SonarQube analysis") {
 agent any
 steps {
 withSonarQubeEnv('My SonarQube Server') {
 sh 'mvn clean package sonar:sonar'
 }
 }
 }
  stage("Quality Gate") {
 steps {
 timeout(time: 1, unit: 'HOURS') {
 waitForQualityGate abortPipeline: true
 }
 }
 }
} 
