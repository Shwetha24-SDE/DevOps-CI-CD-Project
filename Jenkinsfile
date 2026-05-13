pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        SCANNER_HOME = tool 'SonarScanner'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                url: 'https://github.com/Shwetha24-SDE/DevOps-CI-CD-Project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {

                    sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=springboot-cicd \
                    -Dsonar.projectName=springboot-cicd
                    '''
                }
            }
        }
        }
    }
}