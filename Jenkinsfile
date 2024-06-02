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
        always {
            script {
                // Clean up: stop and remove containers, networks, images, and volumes
                powershell 'docker-compose down'
            }
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
