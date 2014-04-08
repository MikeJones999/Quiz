Quiz
====

Quiz Repository - assignment 3 PIJ


Start Server with..

java -Djava.security.policy=server.policy ServerLauncher


Once connected, start SetUpClient

java -Djava.security.policy=client.policy SetUpClient

enter the ip address of the server to connect to. In this case, use local IP and Port as the security settings do not allow connection
to a remote computer - Sergio confirmed this with me.

Once you have made a quiz or quizzes, run PlayerClient to play the games

java -Djava.security.policy=client.policy PlayerClient

Choose from the menu for the appropriate option. 

***NOTE*** several quizzes and players and their scores have been left in place for demonstartion purposes. 

To wipe these simply, STOP server if running, then delete contents from both Quiz.txt and PlayerStats.txt - Do not delete the files themselves :)
