# Tetris Engine
Steven Hicks 

http://stevenkhicks.de

## About 

Tetris is a game about matching tile based shapes into solid blocks. Once a solid line has been made, the line can dissapear. 

https://en.wikipedia.org/wiki/Tetris


## Requirements 

You will need the following:

 - JDK 8+
  - JRE 8+ or better to run
 - Maven 
 
## How to build 

To build go to the source listing and execute 

     mvn clean package
      
This will build a fully bundled JAR within the target package. 

## How to run 

To execute this application run the following command from the source directory (after building)

     java -jar target/tetris-engine-1.0-SNAPSHOT-allinone.jar
     
From the run you should receive a request for your block selections. 


## Additional Parameters

Command line argument: 

**-v** This shows the board game state at every piece being added.  

#### Optionally

While running this application:

 * Turn up your speakers+volume to full blast
 * Open up a browser in the background to: https://www.youtube.com/watch?v=9Fv5cuYZFC0

### Input

The requests for blocks are made via a string of comma seperated block requests and the placements.

Each block request is represented by the letter of a block and it's placement on the grid. 

Also a note: Based on specification, the board is assumed to be 10 units wide. 

For example: I0, I4,Q4

### Blocks 

The following blocks are available:

Letter Representing | Size | Shape 
 ---  | --- | ---- 
 I | 4x1 | Single line  
 Z | 3x2 | Z shape 
 S | 3x2 | S shape
 T | 3x2 | Upside down triangle
 L | 2x3 | A large L 3 blocks high
 J | 2x3 | A backwards L 
 Q | 2x2 | cube



## Output 

After each line request, you should receive a number back. This number represent the height of all items on the board. 
 
If you are using the verbose mode (-v command line argument) you will see the game state at every piece being added.

## Notes

### Going beyond the specifications
  
A few things where this went beyond the ask of the requirements: 

  * The game board (TetrisBoard) does not have a height limit. 
  * The game board does not have a fixed width
  * The game board takes in custom types. (This means you can use any type, as long as your shapes are using the same type)
  * You can add custom shapes via modifying the Shapes object and mappings.
  * Viewing the state of the board. (We nearly have a full tetris game, just need the game loop and UI!) 
  
### Extension

  * Tetris board is a generic one that is capabilities of handling different object types. The only assumption that it makes is that  Null is used for an empty space.
  * The total lines handled by this application are solely bounded by memory. There is no set limit.
  
### Possible improvements to be made

  * The removal of blank lines can be optimized to only be within the top few rows. 
  * The removal/check of full lines could be optimized to be limited to the rows where the new piece was just placed.
  * Input validation. At the moment there is no validation to prevent input placing a shape beyond the limits of the game board.
  * Using the color codes for the terminal to color code the output.     

### Why isn't this using Scala 2.12.5? 

Scala 2.12.5 has capability issues dealing with Macros and Java 9+. See the following bug report: https://github.com/scala/scala-dev/issues/480


