//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (ASCII ART)
// Files: (DrawingStack.java, DrawingStackIterator.java, DrawingChange.java, Canvas.java,
//////////////////// AsciiArt.java, and AsciiTest.java)
// Course: (001 SUMMER 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
// Lecturer's Name: (Mouna KACEM)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (Jiong Chen)
// Partner Email: (jchen672@wisc.edu)
// Partner Lecturer's Name: (Mouna KACEM)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (NONE)
// Online Sources: (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;

/**
 * the tests for ASCII Art
 * 
 * @author rosaliecarrow, Jiong Chen
 *
 */
public class AsciiTest {
  /**
   * compare the coordinate and character in two nodes
   * 
   * @param c1 first node to compare
   * @param c2 second node to compare
   * @return the result of the comparison, true if coordinate and character equals
   */
  private static boolean compareNode(DrawingChange c1, DrawingChange c2) {
    if (c1 == null || c2 == null) {
      return false;
    }
    return (c1.x == c2.x) && (c1.y == c2.y) && (c1.prevChar == c2.prevChar)
        && (c1.newChar == c2.newChar);

  }

  /**
   * create a stack, push a DrawingChange onto the stack, and then use peek to verify that the
   * correct item is at the top of the stack
   * 
   * @return true if all tests passed, false if part of the test fails
   */
  public static boolean testStackPushPeek() {
    boolean passed = true;
    // create a stack
    DrawingStack drawStack = new DrawingStack();

    // push-test-1-null
    try {
      drawStack.push(null);
    } catch (IllegalArgumentException e1) {

    } catch (RuntimeException e2) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-1-1-fails");
    }

    // push-test-2
    DrawingChange change1 = new DrawingChange(1, 1, '0', '1');
    drawStack.push(change1);
    if (drawStack.size() != 1) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-2-1-fails");
    }
    if (!compareNode(drawStack.peek(), change1)) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-2-2-fails");
    }

    // push-test-3
    // node to compare
    DrawingChange change2 = new DrawingChange(5, 5, '4', '5');
    drawStack.push(new DrawingChange(2, 2, '1', '2'));
    drawStack.push(new DrawingChange(3, 3, '2', '3'));
    drawStack.push(new DrawingChange(4, 4, '3', '4'));
    drawStack.push(change2);

    if (drawStack.size() != 5) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-3-1-fails");
    }

    if (!compareNode(drawStack.peek(), change2)) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-3-2-fails");
    } // uses peek to verify

    // push-test-4-pop
    // save popped node
    DrawingChange change3 = drawStack.pop();
    if (drawStack.size() != 4) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-4-1-fails");
    }

    if (!compareNode(drawStack.peek(), new DrawingChange(4, 4, '3', '4'))) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-4-2-fails");
    }

    if (!compareNode(change3, change2)) {
      passed = false;
      System.out.println("testStackPushPeek()--push-test-4-3-fails");
    }


    return passed;
  }

  /**
   * test constructor method in canvas, return true if function correctly, false otherwise
   * 
   * @return return true if function correctly, false otherwise
   */
  public static boolean testCanvasConstructor() {
    boolean passed = true;
    // test illegal dimension input
    try {
      Canvas canvas = new Canvas(-1, 9);
    } catch (IllegalArgumentException e) {
      // should be caught here
    } catch (RuntimeException e) {
      passed = false;
    }

    return passed;
  }



  /**
   * test undo and undo method in canvas, return true if function correctly, false otherwise
   * 
   * @return return true if function correctly, false otherwise
   */
  public static boolean testUndoRedo() {
    boolean passed = true;
    // create a canvas
    Canvas canvas = new Canvas(7, 7);

    // UndoRedo-test-1
    // empty undoStack/canvas
    passed = !canvas.undo() && !canvas.redo();
    if (!passed) {
      System.out.println("testUndoRedo()-UndoRedo-test-1-fails");
    }

    // Undo-test
    // regular undo
    canvas.draw(0, 0, 'a');
    canvas.draw(1, 1, 'a');
    canvas.draw(3, 3, 'a');
    canvas.draw(4, 4, 'a');
    canvas.draw(2, 2, 'a');
    passed = canvas.getUndoStack().size() == 5;
    passed = canvas.undo();
    passed = compareNode(canvas.getUndoStack().peek(), new DrawingChange(4, 4, ' ', 'a'));

    if (!passed) {
      System.out.println("testUndoRedo()-Undo-test-fails");
    }

    // Redo-test
    // check if redo contains the correct change
    passed = canvas.getRedoStack().size() == 1;
    passed = compareNode(canvas.getRedoStack().peek(), new DrawingChange(2, 2, ' ', 'a'));
    if (!passed) {
      System.out.println("testUndoRedo()-Redo-test-fails");
    }

    // UndoRedo-test-2
    // making moves
    // redo once
    passed = canvas.redo()
        && compareNode(canvas.getUndoStack().peek(), new DrawingChange(2, 2, ' ', 'a'));
    // undo 5 times, redo stack should have 5 changes and undo stack should be empty
    passed = canvas.undo() && canvas.undo() && canvas.undo() && canvas.undo() && canvas.undo()
        && canvas.getRedoStack().size() == 5 && canvas.getUndoStack().size() == 0;
    passed = compareNode(canvas.getRedoStack().peek(), new DrawingChange(0, 0, ' ', 'a'));

    // redo once
    passed = canvas.redo()
        && compareNode(canvas.getUndoStack().peek(), new DrawingChange(0, 0, ' ', 'a'));
    if (!passed) {
      System.out.println("testUndoRedo()-UndoRedo-test-2-fails");
    }
    return passed;
  }

  /**
   * run multiple other test methods, return false if any of its component tests fail, and true if
   * they all succeed
   * 
   * @return the result of testing, false if any of its component tests fail, and true if they all
   *         succeed
   */
  public static boolean runStackTestSuite() {
    return testStackPushPeek() && testCanvasConstructor() && testUndoRedo();

  }

  public static void main(String[] args) {
    System.out.println("------------------testStackPushPeek() begins------------------");
    System.out.println("------------------testStackPushPeek(): " + testStackPushPeek());
    System.out.println("------------------testCanvasConstructor(): " + testCanvasConstructor());
    System.out.println("------------------testRedo(): " + testUndoRedo());
    System.out.println("------------------runStackTestSuite(): " + runStackTestSuite());
  }

}
