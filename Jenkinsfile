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
                // Build the project using Gradle
                powershell './gradlew build'
            }
        }

        stage('Docker Compose Build') {
            steps {
                script {
                    powershell 'docker-compose build'
                    powershell 'docker-compose up -d'
                }
            }
        }

        stage('Docker Compose Pupowershell') {
            steps {
                script {
                    // Pupowershell the Docker images to a Docker registry (optional)
                    docker.withRegistry('', 'docker-registry-credentials-id') {
                        powershell 'docker-compose pupowershell'
                    }
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
            // Clean up workspace
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
