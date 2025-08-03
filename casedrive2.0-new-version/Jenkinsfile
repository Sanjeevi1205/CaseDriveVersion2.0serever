pipeline {
    agent any

    parameters {
        choice(
            name: 'ENV_URL',
            choices: [
                'https://dev-accounts.lezdotechmed.com',
                'https://staging-accounts.lezdotechmed.com'
            ],
            description: 'Select the Environment URL'
        )

        choice(
            name: 'CUCUMBER_TAGS',
            choices: [
                '@AccountUIRegression',
                '@ClientUIRegression',
                '@AdminUIRegression',
                '@ReviewCase',
                '@Signup',
                '@LoginClient',
                '@OrderIntake',
                '@OrderIntakeCustomLink',
                '@Casecompletion',
                '@Crm',
                '@ClientCase',
                '@AdminEstimate',
                '@AdminDrive',
                '@ClientMessage',
                '@ClientDrive',
                '@AdminMessage',
                '@AdminMyAccount',
                '@AdminPartner',
                '@AdminReport',
                '@AdminResource',
                '@AdminSettings',
                '@CaseListAction',
                '@ClientContract',
                '@ClientDashboard',
                '@ClientMyAccount',
                '@ClientReport',
                '@RetainerInvoice',
                '@ClientSettings',
                '@Teams',
                '@TeamLead',
                '@Forgotpasswd',
                '@SecondaryEmail',
                '@RetrievalOnline',
                '@Test',
                '@GenerateInvoice'
            ],
            description: 'Select the Cucumber tag to run'
        )
    }

    environment {
        MAVEN_HOME = tool 'Maven'
        LOG_DATE = new Date().format("yyyy-MM-dd")
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Cleaning previous workspace...'
                deleteDir()
                echo 'Checking out latest code...'
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                withEnv(["PATH+MAVEN=${env.MAVEN_HOME}/bin"]) {
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
    }

    post {
        always {
            script {
                def timeoutMinutes = 2
                def intervalSeconds = 10
                def elapsed = 0

                def pdfPath = "test-output/PdfReport/ExtentPdf.pdf"
                def logPath = "logs/automation-${env.LOG_DATE}.log.0"

                while (!fileExists(pdfPath) || !fileExists(logPath)) {
                    if (elapsed >= timeoutMinutes * 60) {
                        echo "Warning: PDF or log file not found after waiting ${timeoutMinutes} minutes."
                        break
                    }
                    echo "Waiting for PDF and log file... (${elapsed}s elapsed)"
                    sleep time: intervalSeconds, unit: 'SECONDS'
                    elapsed += intervalSeconds
                }

                echo "Archiving reports if available..."
            }

            archiveArtifacts artifacts: "test-output/PdfReport/ExtentPdf.pdf, logs/automation-${env.LOG_DATE}.log.0", fingerprint: true
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/surefire-reports/*.txt', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/SparkReport/**', allowEmptyArchive: true
        }

        failure {
            mail to: 'benish.s@lezdotechmed.com',
                 subject: "Build Failed: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                 body: "Check Jenkins for more details."
        }
    }
}

