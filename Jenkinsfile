pipeline {
    agent any
    
    triggers {
        pollSCM('*/1 * * * *') // every 1 minute fortesting
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
