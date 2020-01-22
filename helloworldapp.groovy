String basePath = 'folder'
folder(basePath) {
    description('Folder containing all jobs for folder-1')
}
   job("$basePath/Job-scm-checkout") {
    scm {
        github('amenaafreen/demo-java', 'master')
    }
   triggers {
     githubPush()
    }
   steps {
        shell('./gradlew clean build')
        }
   publishers {
       //archive the war file generated
       archiveArtifacts 'build/libs/*.war'
     steps{
       shell('cp build/libs/*.war /Users/aafreen/downloads/apache-tomcat-9.0.27/webapps')
     }
    }
     steps{

     shell ("""vagrant up &&\
           vagrant ssh &&\
           echo "APP URL" &&\
           curl http://localhost:8082/Job-scm-checkout-6.0/""")
       
     }
     
     
}
