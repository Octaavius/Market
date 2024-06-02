pipeline {
    agent any

    environment {
        DOCKER_IMAGE_TAG = "${env.BUILD_ID}"
    }

    stages {
        stage('Checkout') {
             steps {
                 // Checkout the code from your version control system
                 checkout scm
             }
         }

        stage('Build') {
            steps {
                powershell './gradlew build'
            }
        }

        stage('Docker Compose Build') {
            steps {
                script {
                    powershell './gradlew deploy'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
