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
/**
 * the canvas for the project, contains all stacks of drawing
 * 
 * @author rosaliecarrow, Jiong Chen
 *
 */
public class Canvas {
  private final int width; // width of the canvas
  private final int height; // height of the canvas

  private char[][] drawingArray; // 2D character array to store the drawing

  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

  /**
   * Constructor.setup the width and height of the canvas. Throws IllegalArgumentException if width
   * or height is 0 or negative. A Canvas is initially blank (use the space ' ' character)
   * 
   * @param width  width of the canvas
   * @param height height of the canvas
   */
  public Canvas(int width, int height) {
    // check if the input of width and height is valid, throws IllegalArgumentException if the input
    // is invalid
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException(
          "Invalid width or height input: should be greater than 0.");
    }


    // assigh the input of width and height to the variable in the object
    this.width = width;
    this.height = height;

    // create an 2D array as the canvas
    drawingArray = new char[height][width];


    // set initial value of the canvas to " "
    for (int i = 0; i < drawingArray.length; i++) {
      java.util.Arrays.fill(drawingArray[i], ' ');
    }


    // create new object of DrawingStack to store the undo and redo
    undoStack = new DrawingStack();
    redoStack = new DrawingStack();
  }


  /**
   * Draw a character at the given position. This method should throw an IllegalArgumentException if
   * the drawing position is outside the canvas. If that position is already marked with a different
   * character, overwrite it. After making a new change, add a matching DrawingChange to the
   * undoStack so that we can undo if needed. After making a new change, the redoStack should be
   * empty.
   * 
   * @param row row of the character
   * @param col col of the character
   * @param c   the character
   */
  public void draw(int row, int col, char c) {
    // checks validity of input, throw an IllegalArgumentException if the drawing position is
    // outside the canvas
    if (row > height || row < 0 || col < 0 || col > width) {
      throw new IllegalArgumentException("Invalid position.");
    }

    // saves previous char at that position
    char prev = drawingArray[row][col];

    // makes change
    drawingArray[row][col] = c;

    // add change to undoStack
    undoStack.push(new DrawingChange(col, row, prev, c));

    // clear redoStack
    while (!redoStack.isEmpty()) {
      redoStack.pop();
    }

  }


  /**
   * return width of the canvas
   * 
   * @return the width
   */
  public int getWidth() {
    return width;
  }



  /**
   * return height of the canvas
   * 
   * @return the height
   */
  public int getHeight() {
    return height;
  }



  /**
   * return the undoStack in the canvas
   * 
   * @return the undoStack
   */
  public DrawingStack getUndoStack() {
    return undoStack;
  }



  /**
   * return the redoStack in the canvas
   * 
   * @return the redoStack
   */
  public DrawingStack getRedoStack() {
    return redoStack;
  }


  /**
   * Undo the most recent drawing change. Return true if successful. False otherwise. An undone
   * DrawingChange should be added to the redoStack so that we can redo if needed.
   * 
   * @return true if undo successfully, false otherwise
   */
  public boolean undo() {
    // the change
    DrawingChange undo;

    // check if change is valid
    if ((undo = undoStack.pop()) == null) {
      return false;
    }

    // make the change
    drawingArray[undo.y][undo.x] = undo.prevChar;

    // add change to redoStack
    redoStack.push(undo);
    return true;
  }

  /**
   * Redo the most recent undone drawing change. Return true if successful. False otherwise. A
   * redone DrawingChange should be added (back) to the undoStack so that we can undo again if
   * needed.
   * 
   * @return true if redo successfully, false otherwise
   */
  public boolean redo() {
    // the change
    DrawingChange redo;
    // check if change is valid
    if ((redo = redoStack.pop()) == null) {
      return false;
    }

    // make the change
    drawingArray[redo.y][redo.x] = redo.newChar;

    // add change to undoStack
    undoStack.push(redo);

    return true;
  }

  /**
   * Return a printable string version of the Canvas.
   * 
   * Format example: [_ is blank. Use System.lineSeparator() to put a newline character between
   * rows] X___X _X_X_ __X__ _X_X_ X___X
   */
  public String toString() {
    String result = "";
    // traverse through each element in the array
    for (int i = 0; i < drawingArray.length; i++) {
      for (int j = 0; j < drawingArray[i].length; j++) {
        result += drawingArray[i][j];
      }
      result += System.lineSeparator();
    }

    return result;
  }

  /**
   * prints the Canvas's string representation to System.out.
   */
  public void printDrawing() {
    System.out.print(toString());
  }

  /**
   * prints a record of the changes that are stored on the undoStack
   */
  public void printHistory() {
    // the index of the changes in the stack
    int index = this.getUndoStack().size();

    // traverse down the stack
    DrawingStackIterator iterator = (DrawingStackIterator) this.getUndoStack().iterator();
    while (iterator.hasNext()) {
      DrawingChange data = iterator.next();
      System.out
          .println(index + ". draw '" + data.newChar + "' on (" + data.x + "," + data.y + ")");

      index--;
    }
  }
}

