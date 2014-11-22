/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package JUnit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import model.Core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CoreTest {

	/**
	 * Core variable for testing
	 */
	private final Core core;
	
	/**
	 * CoreTest constructor with parameter setting
	 * @param b String content of buffer
	 * @param p Array int with start and end position of selection
	 * @param c String content of clipboard
	 */
	public CoreTest(final String b, final int[] p, final String c) {
		this.core = new Core(b,p,c);
	}
	
	/**
	 * Test cases
	 * @return Collection of core test cases (with buffer, positions, clipboard)
	 */
	@Parameters
	public static Collection<Object[]> parameters()
	{
		return Arrays.asList(new Object[][]{
			{"",new int[]{0,0},""},
			{"test",new int[]{4,4},""},
			{"heello",new int[]{2,4},"lo"},
			{"",new int[]{4,2},"isn't"},
		});
	}
	
	/**
	 * tests function to insert text into buffer
	 * @prerequisite start position <= end position
	 * @prerequisite end position <= buffer length (implies that start position <= buffer length too)
	 * @prerequisite start position and end position >= 0
	 * @postrequisite buffer = buffer + text in parameters
	 * @postrequisite start and end positions = old start position + insert length
	 * @postrequisite clipboard is the same
	 */
	@Test
	public void testInsert() {
		// Insertion string
		String insertion = "String";
		// Get all data before testing
		String bufferPreTest = this.core.getBuffer().getText();
		int[] selectionPreTest = this.core.getSelection().getSelection();
		String clipBoardPreTest = this.core.getClipBoard().getContent();
		boolean execute = true;
		// Test prerequisite
		// For start position
		if (selectionPreTest[0] > bufferPreTest.length()) {
			assertTrue("Start position is out of bounds", selectionPreTest[0] > bufferPreTest.length());
			execute = false;
		} else if (selectionPreTest[0] < 0 ){
			assertTrue("Start position is negative", selectionPreTest[0] < 0 );
			execute = false;
		}
		// Test end position
		if (selectionPreTest[1] < selectionPreTest[0]) {
			assertTrue("End position is smaller than start position", selectionPreTest[1] < selectionPreTest[0]); 
			execute = false;
		} else if (selectionPreTest[1] > bufferPreTest.length()) {
			assertTrue("End position is out of bounds", selectionPreTest[1] > bufferPreTest.length());
			execute = false;
		} 
		if (execute) {
			// Execute insert command with insertion variable
			this.core.insert(insertion);
			// Test
			assertEquals("The buffer content is different than the excepted result", bufferPreTest.substring(0, selectionPreTest[0])+insertion+bufferPreTest.substring(selectionPreTest[1]), this.core.getBuffer().getText());
			assertEquals("The start position of the selection isn't the excepted position",selectionPreTest[0]+insertion.length(), this.core.getSelection().getSelection()[0]);
			assertEquals("The end position of the selection isn't the excepted position",selectionPreTest[0]+insertion.length(), this.core.getSelection().getSelection()[1]);
			assertEquals("The clipboard has changed during the test simulation", clipBoardPreTest, this.core.getClipBoard().getContent());				
		}
	}

	/**
	 * tests function to select text in buffer
	 * @postrequisite start position <= end position
	 * @postrequisite end position <= buffer length (implies that start position <= buffer length too)
	 * @postrequisite start position and end position >= 0
	 * @postrequisite start and end position are defined in Selection of Core
	 * @postrequisite buffer and clipboard are still the same
	 */
	@Test
	public void testSelect() {
		// New selection
		int[] selection = new int[2];
		selection[0] = 2;
		selection[1] = 5;
		// Get all data before testing
		String bufferPreTest = this.core.getBuffer().getText();
		String clipBoardPreTest = this.core.getClipBoard().getContent();
		// Execute insert command with insertion variable
		this.core.select(selection);
		// Test buffer and clip board
		assertEquals("The buffer content has changed during the test simulation", bufferPreTest, this.core.getBuffer().getText());
		assertEquals("The clipboard has changed during the test simulation", clipBoardPreTest, this.core.getClipBoard().getContent());				
		// Test start position
		if (selection[0] > bufferPreTest.length()) {
			assertEquals("The start position of the selection isn't the excepted position",bufferPreTest.length(), this.core.getSelection().getSelection()[0]);
		} else if (selection[0] < 0 ){
			assertEquals("The start position of the selection isn't the excepted position",0, this.core.getSelection().getSelection()[0]);
		} else {
			assertEquals("The start position of the selection isn't the excepted position",selection[0], this.core.getSelection().getSelection()[0]);
		}
		// Test end position
		if (selection[1] < selection[0]) {
			assertEquals("The end position of the selection isn't the excepted position",selection[0], this.core.getSelection().getSelection()[1]);
		} else if (selection[1] > bufferPreTest.length()) {
			assertEquals("The end position of the selection isn't the excepted position",bufferPreTest.length(), this.core.getSelection().getSelection()[1]);
		} else {
			assertEquals("The end position of the selection isn't the excepted position",selection[1], this.core.getSelection().getSelection()[1]);
		}
	}

	/**
	 * tests function to copy text from buffer into clipboard
	 * @prerequisite start position <= end position
	 * @prerequisite end position <= buffer length (implies that start position <= buffer length too)
	 * @prerequisite start position and end position >= 0
	 * @postrequisite buffer and selection haven't changed
	 * @postrequisite text between start & end in clipboard
	 */
	@Test
	public void testCopy() {
		// Get all data before testing
		String bufferPreTest = this.core.getBuffer().getText();
		int[] selectionPreTest = this.core.getSelection().getSelection();
		String clipBoardPreTest = this.core.getClipBoard().getContent();
		boolean execute = true;
		// Test prerequisite
		// For start position
		if (selectionPreTest[0] > bufferPreTest.length()) {
			assertTrue("Start position is out of bounds", selectionPreTest[0] > bufferPreTest.length());
			execute = false;
		} else if (selectionPreTest[0] < 0 ){
			assertTrue("Start position is negative", selectionPreTest[0] < 0 );
			execute = false;
		}
		// Test end position
		if (selectionPreTest[1] < selectionPreTest[0]) {
			assertTrue("End position is smaller than start position", selectionPreTest[1] < selectionPreTest[0]); 
			execute = false;
		} else if (selectionPreTest[1] > bufferPreTest.length()) {
			assertTrue("End position is out of bounds", selectionPreTest[1] > bufferPreTest.length());
			execute = false;
		} 
		if (execute) {
			// Execute copy commands
			this.core.copy();
			// Common test
			assertEquals("The buffer content has changed during the test simulation", bufferPreTest, this.core.getBuffer().getText());
			assertEquals("The start position of the selection has changed during the test simulation",selectionPreTest[0], this.core.getSelection().getSelection()[0]);
			assertEquals("The end position of the selection has changed during the test simulation",selectionPreTest[1], this.core.getSelection().getSelection()[1]);
			// Test if start and end position have the same value
			if (selectionPreTest[0] == selectionPreTest[1]) {
				assertEquals("Clipboard content shouldn't have changed during the test simulation", clipBoardPreTest , this.core.getClipBoard().getContent());
			} else {
				assertEquals("Clipboard content isn't the expected result", this.core.getBuffer().getSelection(selectionPreTest[0], selectionPreTest[1]), this.core.getClipBoard().getContent());
			}
		}
	}

	/**
	 * tests function to cut text from buffer into clipboard
	 * @prerequisite start position <= end position
	 * @prerequisite end position <= buffer length (implies that start position <= buffer length too)
	 * @prerequisite start position and end position >= 0
	 * @postrequisite text between start & end not in buffer anymore
	 * @postrequisite text between start & end in clipboard
	 * @postrequisite end position is set to the old start position and start position doesn't change
	 */
	@Test
	public void testCut() {
		// Get all data before testing
		String bufferPreTest = this.core.getBuffer().getText();
		int[] selectionPreTest = this.core.getSelection().getSelection();
		String clipBoardPreTest = this.core.getClipBoard().getContent();
		boolean execute = true;
		// Test prerequisite
		// For start position
		if (selectionPreTest[0] > bufferPreTest.length()) {
			assertTrue("Start position is out of bounds", selectionPreTest[0] > bufferPreTest.length());
			execute = false;
		} else if (selectionPreTest[0] < 0 ){
			assertTrue("Start position is negative", selectionPreTest[0] < 0 );
			execute = false;
		}
		// Test end position
		if (selectionPreTest[1] < selectionPreTest[0]) {
			assertTrue("End position is smaller than start position", selectionPreTest[1] < selectionPreTest[0]); 
			execute = false;
		} else if (selectionPreTest[1] > bufferPreTest.length()) {
			assertTrue("End position is out of bounds", selectionPreTest[1] > bufferPreTest.length());
			execute = false;
		} 
		if (execute) {
			// Execute cut commands
			this.core.cut();
			//  Test if start and end position have the same value
			// (Nothing happens)
			if (selectionPreTest[0] == selectionPreTest[1]) {
				assertEquals("Clipboard content shouldn't have changed during the test simulation", clipBoardPreTest , this.core.getClipBoard().getContent());
				assertEquals("The buffer content has changed during the test simulation", bufferPreTest, this.core.getBuffer().getText());
				assertEquals("The start position of the selection has changed during the test simulation",selectionPreTest[0], this.core.getSelection().getSelection()[0]);
				assertEquals("The end position of the selection has changed during the test simulation",selectionPreTest[1], this.core.getSelection().getSelection()[1]);
			} else {
				assertEquals("Clipboard content isn't the expected result", bufferPreTest.substring(selectionPreTest[0], selectionPreTest[1]), this.core.getClipBoard().getContent());
				assertEquals("Buffer content isn't the expected result", bufferPreTest.substring(0, selectionPreTest[0])+bufferPreTest.substring(selectionPreTest[1]), this.core.getBuffer().getText());
				assertEquals("Start position isn't the expected result",selectionPreTest[0] , this.core.getSelection().getSelection()[0]);
				assertEquals("End position isn't the expected result",selectionPreTest[0] , this.core.getSelection().getSelection()[1]);
			}
		}
	}

	/**
	 * tests function to paste text from clipboard into buffer
	 * @prerequisite start position <= end position
	 * @prerequisite end position <= buffer length (implies that start position <= buffer length too)
	 * @prerequisite start position and end position >= 0
	 * @postrequisite text between start & end replaced by content in clipboard
	 * @postrequisite start & end position reset to old start + clipboard size
	 * @postrequisite clipboard doesn't change
	 */
	@Test
	public void testPaste() {
		// Get all data before testing
		String bufferPreTest = this.core.getBuffer().getText();
		int[] selectionPreTest = this.core.getSelection().getSelection();
		String clipBoardPreTest = this.core.getClipBoard().getContent();
		boolean execute = true;
		// Test prerequisite
		// For start position
		if (selectionPreTest[0] > bufferPreTest.length()) {
			assertTrue("Start position is out of bounds", selectionPreTest[0] > bufferPreTest.length());
			execute = false;
		} else if (selectionPreTest[0] < 0 ){
			assertTrue("Start position is negative", selectionPreTest[0] < 0 );
			execute = false;
		}
		// Test end position
		if (selectionPreTest[1] < selectionPreTest[0]) {
			assertTrue("End position is smaller than start position", selectionPreTest[1] < selectionPreTest[0]); 
			execute = false;
		} else if (selectionPreTest[1] > bufferPreTest.length()) {
			assertTrue("End position is out of bounds", selectionPreTest[1] > bufferPreTest.length());
			execute = false;
		} 
		if (execute) {
			// Execute paste command
			this.core.paste();
			// Test if clipBoard is empty
			assertEquals("Clipboard content shouldn't have changed during the test simulation", clipBoardPreTest , this.core.getClipBoard().getContent());
			if (clipBoardPreTest.equals("")) {
				assertEquals("The buffer content has changed during the test simulation", bufferPreTest, this.core.getBuffer().getText());
				assertEquals("The start position of the selection has changed during the test simulation",selectionPreTest[0], this.core.getSelection().getSelection()[0]);
				assertEquals("The end position of the selection has changed during the test simulation",selectionPreTest[1], this.core.getSelection().getSelection()[1]);
			} else {
				assertEquals("The buffer content is different than the excepted result", bufferPreTest.substring(0, selectionPreTest[0])+clipBoardPreTest+bufferPreTest.substring(selectionPreTest[1]), this.core.getBuffer().getText());
				assertEquals("The start position of the selection isn't the excepted position",selectionPreTest[0]+clipBoardPreTest.length(), this.core.getSelection().getSelection()[0]);
				assertEquals("The end position of the selection isn't the excepted position",selectionPreTest[0]+clipBoardPreTest.length(), this.core.getSelection().getSelection()[1]);
			}
		}
	}

}
