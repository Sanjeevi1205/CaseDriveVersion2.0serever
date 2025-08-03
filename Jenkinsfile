pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile'
            dir '.'
            label 'docker-agent'
        }
    }

    parameters {
        choice(name: 'ENV_URL', choices: ['https://dev-accounts.lezdotechmed.com', 'https://staging-accounts.lezdotechmed.com'], description: 'Select the Environment URL')
        choice(name: 'CUCUMBER_TAGS', choices: ['@ClientDrive', '@OrderIntake', '@LoginClient', '@Test'], description: 'Select the Cucumber tag to run')
    }

    environment {
        LOG_DATE = new Date().format("yyyy-MM-dd")
    }

    stages {
        stage('Run Tests in Docker') {
            steps {
                sh """
                    mvn clean test \
                        -Dtest=runner.RunnerClass2 \
                        -Dcucumber.filter.tags=${params.CUCUMBER_TAGS} \
                        -Denv.url=${params.ENV_URL} \
                        -Dheadless=true \
                        -Dmaven.test.failure.ignore=true
                """
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: "test-output/PdfReport/ExtentPdf.pdf, logs/automation-${env.LOG_DATE}.log.0", fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}