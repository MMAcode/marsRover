#Readme

##Running the program and Overview of classes
Main class to execute instructions is called InstructionsProcessor. In there, run the 'main' method to see that instructions are processed correctly.

The core of the logic is handled by the class Robot and his public method 'move'.
Robot is using class World. (This class did not need to be created, but final solution could be decided after a discussion, depending on what are the reusability needs of this project etc.) 

##My approach
I used OOP as the foundation to solve this problem - "Robot" "moving" in a "World". Later I created "InstructionProcessor" to alling my code with the original form instructions/requirements.
To solve this task I followed my typical approach in a sense that I created a rough design/vision of the solution in my head first, then I wrote 'ugly' code to just get it working, and then I was refactoring the code to make it cleaner and more readable.
I also partially followed TDD when building Robot class to make sure Robot behaves as expected.

## Testing
I partially followed TDD when building the Robot class to illustrate how I would test a class and to check that my Robot works as expected. Methods in that class could be improved/could be more systematic. Eg we could test if robot can move 1 filed in every single direction, if robot can rotate from each direction 90degrees to each side, if robot can get of the map in various directions, multisteps in one direction, multi-rotations, and combinations of everything.
ToDo: implement tests for all classes; run jacoco utility to see code coverage, if any lines/code was missed during testing.

## Notes
In all classes I left notes/comments for reader to highlight my though processes. This would be of course removed from official code.

##Improvements
Add testing for all classes. Consider also edge cases/clarify with business how many robots is the maximum, what is the maximum size of the world, etc, to possibly use bigger/smaller data types.
Add data verification/sanitation to InstructionsProcessor. (Clarify wih business/others, how do we receive data first; as String? in file?,...)
Use lombok annotations to declutter the code.
If many robots will be operated, consider adding parallel processing of data for faster performance. (Check also with business: Does order of robot's outputs need to be preserved?)
Re-consider design of Robot accepting World as a parameter. Consider replacing World class with some primitive data types
(More is probably mentioned in my comments in various classes.)