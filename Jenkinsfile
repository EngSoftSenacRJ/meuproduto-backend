pipeline {
    agent any

    stages {
        stage('Testing stage') {
            steps {
                withMaven(maven: 'maven_3_6_3') {
                    sh 'mvn test'
                }
            }
        }

        stage('Compile stage') {
            steps {
                withMaven(maven: 'maven_3_6_3') {
                    sh 'mvn -DskipTests clean install'
                }
            }
        }

        stage('Build Docker Image stage') {
            steps {
                sh 'docker build -t meuproduto:latest .'
            }
        }

        stage('Deploy stage') {
            sh 'docker stack deploy -c docker-compose.yml senac_uat'
        }

    }
}