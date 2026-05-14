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
        stage('Docker Build') {
            steps {
                sh 'docker build -t springboot-cicd .'
    }
}
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-app:latest .'
    }
}

        stage('Update Helm Values') {
            steps {
            sh """
                 powershell -Command "(Get-Content java-app\\values.yaml) -replace 'tag:.*','tag: %BUILD_NUMBER%' | Set-Content java-app\\values.yaml"
                """            }
        }

        stage('Git Commit Changes') {
            steps {
                sh '''
                    git config user.email "jenkins@example.com"
                    git config user.name "jenkins"

                    git add .
                    git commit -m "Updated image tag %BUILD_NUMBER%"
                    git push origin main
                '''
            }
        }

        }
    }
