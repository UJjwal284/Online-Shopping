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
                scannerHome=tool name: "sonar-scanner"
            }
                    steps {
                        withSonarQubeEnv('SonarQubeServer') {
                            sh "${scannerHome}/bin/sonar-scanner"
                        }
                    }
                }

        stage('Deploy to Docker') {
            steps {
                script {
                    def dockerHome = tool 'myDocker'
                    env.PATH = "${dockerHome}/bin:${env.PATH}"
                    sh "docker build -t online-shopping ."
                    sh "docker run -p 8090:8080 -d online-shopping"
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
