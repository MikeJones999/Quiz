Quiz
====

Quiz Repository - assignment 3 PIJ

type: rmic ServerManagerImpl  //although doesnt seem to need this anymore (locally)


Start Server with..

java -Djava.security.policy=server.policy ServerLauncher


Once connected, start SetUpClient

java -Djava.security.policy=client.policy SetUpClient

enter the ip address of the server to connect to. In this case, use local IP and Port as the security settings do not allow connection to a remote computer - Sergio confirmed this with me.

Once you have made a quiz or quizzes, run PlayerClient to play the games

type:

java -Djava.security.policy=client.policy PlayerClient

Choose from the menu for the appropriate option. 


