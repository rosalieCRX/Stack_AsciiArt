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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator implementation for Stack
 * 
 * @author rosaliecarrow, Jiong Chen
 */
public class DrawingStackIterator implements Iterator<DrawingChange> {
  private Node<DrawingChange> current;


  /**
   * Creates a DrawingStackIterator that iterates over an iteration of elements of type
   * DrawingChange
   * 
   * @param head reference to the first node in the iteration
   */
  public DrawingStackIterator(Node<DrawingChange> head) {
    this.current = head;
  }

  /**
   * checks if iterator has more element
   */
  @Override
  public boolean hasNext() {
    return this.current != null;
  }

  /**
   * returns the current data and advance to next element
   */
  @Override
  public DrawingChange next() throws NoSuchElementException {
    // throws NoSuchElementException if there is no data to return
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    // data to return
    DrawingChange data = this.current.getData();
    // advance iterator
    this.current = this.current.getNext();

    return data;
  }
}
