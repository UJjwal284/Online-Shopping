pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
        JAVA_HOME = tool 'Java'
    }
    stages {
        stage('Checkout') {
            steps {
                script {        
                    def branchName = env.GIT_BRANCH ?: 'main'
                    checkout scmGit(branches: [[name: '${branchName}']], extensions: [], userRemoteConfigs: [[credentialsId: 'b37c044f-3483-42b4-ab8b-dbaae1deb520', url: 'https://github.com/UJjwal284/Online-Shopping.git']])
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
