pipeline{
    agent any
    
    stages{
        stage("Build"){
            steps{
                echo("Build the project")
            }
        }
        stage("Deploy to dev"){
            steps{
                echo("Deploying to dev env")
            }
        }
         stage("Deploy to qa"){
            steps{
                echo("Deploying to qa env")
            }
        }
         stage("Run regression automation testcases"){
            steps{
                echo("run regression automation testcases")
            }
        }
          stage("Deploy to stage"){
            steps{
                echo("Deploying to stage env")
            }
        }
         stage("Run sanity testcases"){
            steps{
                echo("run sanity testcases")
            }
        }
        stage("Deploy to prod"){
            steps{
                echo("Deploying to prod env")
            }
        }
        
    }
    
    
    
    
    
    
    
    
    
}