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
                                checkout([$class: 'GitSCM',
                                          branches: [[name: "${branchName}"]],
                                          doGenerateSubmoduleConfigurations: false,
                                          extensions: [[$class: 'CleanBeforeCheckout']],
                                          userRemoteConfigs: [[url: 'https://github.com/yourusername/your-repo.git']]])
                    }
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
