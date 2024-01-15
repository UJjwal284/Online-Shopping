pipeline {
    agent any
    tools {
        maven 'M3'
      }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }
        
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome=tool name: "Sonar S"
            }
                    steps {
                        withSonarQubeEnv('SonarQubeServer') {
                            sh "${scannerHome}/bin/sonar-scanner"
                        }
                    }
                }

              stage('Quality Gate') {
                          steps {
                              timeout(time: 1, unit: 'HOURS') {
                                  script {
                                      def qg = waitForQualityGate()
                                      if (qg.status != 'OK') {
                                          error "SonarQube Quality Gate failed: ${qg.status}"
                                      }
                                  }
                              }
                          }
                      }
    }

    post {
        success {
            echo 'Build successful.'
        }
        
        failure {
            echo 'Build failed. Please check the build logs for details.'
        }
    }
}
