pipeline {
    agent any
    
    triggers {
        pollSCM('*/15 * * * *') // every 15 minutes
    }
    
    tools { 
        maven 'Maven 3.3.9' 
    }
    
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                echo 'This is a minimal pipeline.'
            }
        }
    }
}
