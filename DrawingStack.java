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

/**
 * a stack that stores the changes made by the user to the canvas
 * 
 * @author rosaliecarrow, Jiong Chen
 *
 */
public class DrawingStack implements StackADT<DrawingChange> {
  private int size;// the number of items in the stack
  private Node<DrawingChange> top;// the top node in the stack


  /**
   * add item into the stack, throws illegalargument exception if element is null
   */
  @Override
  public void push(DrawingChange element) throws IllegalArgumentException {
    // checks if element is valid
    if (element == null) {
      throw new IllegalArgumentException("Invalid element: should not be null.");
    }

    // create new node containing element to be added
    Node<DrawingChange> newNode = new Node<DrawingChange>(element, null);

    // add element if valid
    newNode.setNext(top);
    top = newNode;

    // update size
    this.size++;

  }

  /**
   * obtain and remove item in the stack
   */
  @Override
  public DrawingChange pop() {
    DrawingChange popped = null;
    if (!this.isEmpty()) {
      popped = top.getData();
      top = top.getNext();
      this.size--;
    }

    return popped;
  }

  /**
   * obtain item without remove it in the stack
   */
  @Override
  public DrawingChange peek() {
    if (this.isEmpty()) {
      return null;
    } else {
      return top.getData();
    }
  }

  /**
   * check if the top object is null
   */
  @Override
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * return the size in the object
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * create iterator for the stack
   */
  @Override
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(this.top);
  }

}
