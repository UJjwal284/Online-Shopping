pipeline {
    agent any
    tools {
        maven 'M3'
      }
    environment{
        scannerHome=tool name: "sonar-scanner"
        SONAR_PROJECT_KEY = env.BUILD_DISPLAY_NAME.replaceAll('[^a-zA-Z0-9]', '')
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
                    steps {
                        withSonarQubeEnv('SonarQubeServer') {
                            sh "${scannerHome}/bin/sonar-scanner"
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
