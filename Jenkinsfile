pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'develop-test', url:'https://github.com/stream2stream/pingme_demo.git'
            }
        }
        stage('Maven Build') {
            steps {
                // Get some code from a GitHub repository
                sh "mvn clean package"
            }
        }
        stage('Build') {
            environment {
                scannerHome= tool 'sonarqube-version'
            }
            steps {
                withSonarQubeEnv('sonarqube-server'){
                  sh "${scannerHome}/bin/sonar-scanner"
                }
            }
        }
    }
}
