pipeline
{
    agent any
    tools
    {
        maven 'Maven'
    }
    
    stages
    {
        stage('Checkout'){
            steps{
                echo "checkout"
                git branch:"master", url:"https://github.com/AnamikaSaluja/devOpsAssignment"
				//checkout scm
            }
        }
        
        stage('Build'){
            steps{
                echo "build"
                bat "mvn clean install"
            }
        }
        
        stage('sonar analysis'){
            steps{
                echo "sonar"
                withSonarQubeEnv("local sonar") 
				{
					bat "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
				}
            }
        }
		
		stage ('Upload to Artifactory')
		{
			steps
			{
				rtMavenDeployer (
                    id: 'deployer',
                    serverId: 'anamika_assignment@artifactory',
                    releaseRepo: 'Anamika_devops_assignmnet',
                    snapshotRepo: 'Anamika_devops_assignmnet'
                )
                rtMavenRun (
                    pom: 'pom.xml',
                    goals: 'clean install',
                    deployerId: 'deployer',
                )
                rtPublishBuildInfo (
                    serverId: 'anamika_assignment@artifactory',
                )
			}
		}
        
    }
}
