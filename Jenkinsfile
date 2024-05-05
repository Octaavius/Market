pipeline {
    agent any

    tools {
        gradle 'Gradle 8.1'
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                git 'https://github.com/Octaavius/Market.git'
            }
        }
        stage('Build') {
            steps {
                // Build the project with Gradle
                sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                // Run the unit tests
                sh './gradlew test'
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    def image = docker.build('octaavius/market', '-f Dockerfile .')
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        image.push()
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build succeeded'
        }
        failure {
            echo 'Build failed'
        }
    }
}
