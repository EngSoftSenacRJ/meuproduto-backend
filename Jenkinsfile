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

        stage('Search API Stress Test stage') {
            blazeMeterTest credentialsId:'blazemeter',
            testId:'8202338',
            notes:'',
            sessionProperties:'',
            jtlPath:'',
            junitPath:'',
            getJtl:false,
            getJunit:false
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
                sh 'cp ./docker-compose.yml /home/ubuntu/meuproduto/backend/docker-compose.yml'
            }
        }

        stage('Deploy Docker Image in Swarm Cluster stage') {
            steps {
                sh 'docker stack deploy -c /home/ubuntu/meuproduto/backend/docker-compose.yml senac_uat'
            }
        }

    }
}