pipeline {
      agent any
      tools {
          maven 'Maven 3.6.0'
          jdk 'jdk1.8.0_144'
       }
       
       stages (
        
          stage('build') {
              stops {
                bat 'mvn install'
              }
              post {
                success {
                junit 'target/surefire-reports/**/*.xml'
                }
              }  
          }
       )  
}       
