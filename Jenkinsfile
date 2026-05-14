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
        sed -i 's/tag:.*/tag: ${BUILD_NUMBER}/' java-app/values.yaml
        """
    }
}
stage('Git Commit Changes') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'jenkins-ci-token',
                                          usernameVariable: 'GIT_USER',
                                          passwordVariable: 'GIT_PASS')]) {

            sh '''
                git config user.email "shwethap2443@gmail.com"
                git config user.name "jenkins"

                git add .

                git commit -m "clean pipeline update" || true

                git remote set-url origin https://${GIT_USER}:${GIT_PASS}@github.com/Shwetha24-SDE/DevOps-CI-CD-Project.git

                git push origin master
            '''
        }
    }
}

   } 
  }



