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
