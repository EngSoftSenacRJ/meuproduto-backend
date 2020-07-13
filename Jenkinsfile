pipeline {
    environment {
        registry = "rderoci/meuproduto"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
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
                    sh 'mvn -DskipTests clean install -o'
                }
            }
        }

        stage('Build Docker Image stage') {
            steps {
                script {
                    dockerImage = docker.build registry + ":latest"
                }
            }
        }

        stage('Push Docker Image stage') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Create docker-compose file to deploy') {
            steps {
                sh 'cp ./docker-compose_meuproduto.yml /home/ubuntu/meuproduto/backend/docker-compose_meuproduto.yml'
            }
        }

        stage('Deploy Docker Image in Swarm Cluster stage') {
            steps {
                sh 'docker stack deploy -c /home/ubuntu/meuproduto/backend/docker-compose_meuproduto.yml meuproduto'
            }
        }

    }
}