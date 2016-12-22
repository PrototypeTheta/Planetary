node {
    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
    stage('Init') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'coded-github', url: 'https://github.com/MinecraftModDevelopment/planetary/']]])
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