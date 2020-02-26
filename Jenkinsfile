pipeline {
    agent {
        node  {
            label 'helmtools'
            customWorkspace "workspace/${env.JOB_NAME}/${env.BUILD_NUMBER}"
        }
    } 
    stages {
        stage('Build image') {
            steps {
                container('docker'){
                    script {
                        sh "docker build --network=host -t taco-registry:5000/admin/java-app-example:jaesang ."
                    }
                }
            }
        }
        stage('Push image') {
            steps {
                container('docker'){
                    script {
                         sh """
                             tail -f
                             docker login -u admin -p password 192.168.51.18:5000
                             docker push taco-registry:5000/admin/java-app-example:jaesang
                             docker push taco-registry:5000/admin/java-app-example:${env.BUILD_NUMBER}
                         """
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                container('helm-kubectl'){
                    script {
                        sh "tail -f"
                    }
                }
            }
        }
    }
}
