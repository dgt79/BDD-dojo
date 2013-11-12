# BDD Dojo with JBehave

## Demo
This should first be run by the presenter who will show the basic operations of the SimpleFixture running, and locating the `story` file.  Show the reported outcome which will contain the code for the steps classes.

Talk about the importance of outside in thinking and the use of the Driver class to delegate to.

## Dojo
Take one of the following scenarios and create a test, fixture, driver and other domain/utility
classes to drive out the solution (focus on the readability of the code and the separation of concerns):
### Clicking on a `File ...` button opens an open File Dialog
    test --> fixture --> DSL --> page object --> driver
### Tennis scores
    given 15/15 when server wins then 30/15
### Bank account
    given 0 balance when deposit 10 then balance is 10

## Questions for the audience
1. How did they find outside in approach to ATDD?
2. Whats a better name for the `Driver' class
3. How well abstracted is it all?

## Pre-requisites
1. Intellij
2. Plugins: TestDox, JBehave Support
3. JVM
4. A network connection