pipeline {
    agent any

    environment {
        DOCKER_IMAGE_TAG = "${env.BUILD_ID}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from GitHub
                git branch: 'main', url: 'https://github.com/Octaavius/Market.git', credentialsId: 'your-credentials-id'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Gradle
                sh './gradlew build'
            }
        }

        stage('Docker Compose Build') {
            steps {
                script {
                    sh 'docker-compose build'
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('Docker Compose Push') {
            steps {
                script {
                    // Push the Docker images to a Docker registry (optional)
                    docker.withRegistry('', 'docker-registry-credentials-id') {
                        sh 'docker-compose push'
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Clean up: stop and remove containers, networks, images, and volumes
                sh 'docker-compose down'
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
