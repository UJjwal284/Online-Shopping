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
                        scannerHome = tool name: 'sonar-scanner'
                    }
                    steps {
                        script {
                            withSonarQubeEnv('SonarQubeServer') {
                                sh "${scannerHome}/bin/sonar-scanner"
                            }

                            timeout(time: 1, unit: 'HOURS') {
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
