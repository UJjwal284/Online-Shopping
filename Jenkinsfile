pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
        JAVA_HOME = tool 'Java'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/yourusername/your-spring-boot-project.git'
            }
        }
        
        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }
        stage('Test') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }
        stage('Deploy') {
            steps {

            }
        }
    }

    post {
        success {
            echo 'Build successful. Deploying...'
        }
        failure {
            echo 'Build failed. Please check the build logs for details.'
        }
    }
}
