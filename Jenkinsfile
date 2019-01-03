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
        }
        stage('Gatling') {
  steps { 
   bat 'mvn gatling:test site'
    
      gatlingArchive()
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
} 
