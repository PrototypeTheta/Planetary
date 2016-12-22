node {
    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
    stage('Init') {
        sh 'rm -rf build/libs/'
    }
    stage('Build') {
        sh 'chmod +x gradlew'
        sh './gradlew build'
    }
    stage('Deploy') {
        currentBuild.result == 'SUCCESS'
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
    }
}