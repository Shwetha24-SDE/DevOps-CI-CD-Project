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

    stages {
        stage('Push to GitHub') {
            steps {
                // Use Jenkins Credentials Provider to keep your Token/Password safe
                // Create a 'Username with password' credential in Jenkins first
                withCredentials([usernamePassword(credentialsId: 'jenkins-ci-token', passwordVariable: 'GIT_TOKEN', usernameVariable: 'GIT_USER')]) {
                    sh """
                        # 1. Configure user identity
                        git config user.email "shwethap2443@gmail.com"
                        git config user.name "jenkins"
                        
                        # 2. Stage and Commit
                        git add .
                        git commit -m "clean pipeline update" || echo "No changes to commit"
                        
                        # 3. Correctly set the remote URL (This was the error in your image)
                        git remote set-url origin https://${GIT_USER}:${GIT_TOKEN}@github.com/Shwetha24-SDE/DevOps-CI-CD-Project.git
                        
                        # 4. Push to the branch (Separate command)
                        git push origin master
                    """
                }
            }
        }
    }
}


   } 
  



