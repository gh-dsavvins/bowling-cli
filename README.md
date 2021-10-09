# README #

Bowling command line interface

### What is this repository for? ###

* Bowling command line interface allows user to enter knocked down pins one roll at the time and will print current status of the game
* Version 1.0

### How do I get set up? ###

* Prerequisites: git, JAVA_HOME pointing to JDK 1.8* home

* Clone the code: git clone https://github.com/gh-dsavvins/bowling-cli.git
* Open CMD/Terminal, navigate to top directory of the project, run UNIX: './gradlew clean build buildFatJar' WIN: 'gradlew  clean build buildFatJar'
* Run UNIX: '${JAVA_HOME}/bin/java -cp ./build/libs/bowling-cli-1.0-all.jar com.dsvv.games.bowling.cli.Main' WIN: '%JAVA_HOME%/bin/java -cp ./build/libs/bowling-cli-1.0-all.jar com.dsvv.games.bowling.cli.Main'
* If you want to run tests only please run UNIX: './gradlew clean test' WIN: 'gradlew clean test'

### Quick start ###

    * UNIX (tested on Mac)
    git clone https://github.com/gh-dsavvins/bowling-cli.git 
    cd bowling-cli
    ./gradlew clean build test buildFatJar
    ${JAVA_HOME}/bin/java -cp ./build/libs/bowling-cli-1.0-all.jar com.dsvv.games.bowling.cli.Main
    
    Example output after all rolls entered:
    Frame # 10, Game score: 256
     ----- ----- ----- ----- ----- ----- ----- ----- ----- -----------
    | 1| /|  | X| 9| /| 9| /|  | X|  | X|  | X|  | X|  | X| X | 9 | 9 |
     ----- ----- ----- ----- ----- ----- ----- ----- ----- -----------
    |   20|   40|   59|   79|  109|  139|  169|  199|  228|       256 |
     ----- ----- ----- ----- ----- ----- ----- ----- ----- -----------
    New Game
    Please enter knocked pin count...
    
    * WIN (tested on VirtualBox Windows 8)
    git clone https://github.com/gh-dsavvins/bowling-cli.git 
    cd bowling-cli
    ./gradlew clean build test buildFatJar
    %JAVA_HOME%/bin/java -cp ./build/libs/bowling-cli-1.0-all.jar com.dsvv.games.bowling.cli.Main

    Example output after all rolls entered:
    Frame # 10, Game score: 257 
     ----- ----- ----- ----- ----- ----- ----- ----- ----- -----------
    | 1| /|  | X|  | X|  | X|  | X|  | X|  | X|  | X| 9| -| X | X | X | 
     ----- ----- ----- ----- ----- ----- ----- ----- ----- -----------
    |   20|   50|   80|  110|  140|  170|  199|  218|  227|       257 | 
     ----- ----- ----- ----- ----- ----- ----- ----- ----- -----------
    New Game 
    Please enter knocked pin count…

### Contribution guidelines ###

* Code review: Please send comments and requests to dmitrijs.savvins@gmail.com

### Who do I talk to? ###

* Repo owner: dmitrijs.savvins@gmail.com