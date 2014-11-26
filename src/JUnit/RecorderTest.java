package JUnit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import model.Core;
import model.Recorder;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import controller.memento.MementoCopy;
import controller.memento.MementoCut;
import controller.memento.MementoPaste;

public class RecorderTest {

	

	/**
	 * Core variable for testing
	 */
	@SuppressWarnings("unused")
	private final Recorder recorder;
	private final Core core;
	
	MementoCopy mCopy = new MementoCopy(this.core);
	MementoCut mCut = new MementoCut(this.core);
	MementoPaste mPaste = new MementoPaste(this.core);
	
//	MementoInsert mInsert1 = new MementoInsert(this.core);

	
	/**
	 * CoreTest constructor with parameter setting
	 * @param b String content of buffer
	 * @param p Array int with start and end position of selection
	 * @param c String content of clipboard
	 */
	public RecorderTest(final String b, final int[] p, final String c) {
		this.recorder = new Recorder(/*list<MementoCommand>*/);
		this.core = new Core(b,p,c);
	}
	
	/**
	 * Test cases
	 * @return Collection of core test cases (with buffer, positions, clipboard)
	 */
	@Parameters
	public static Collection<Object[]> parameters()
	{
		
		//Objet contient > 1 list memento / 1 core avec 1 buffer / 1 selection / 1 presse papier
		return Arrays.asList(new Object[][]{
			{/*list*/Arrays.asList(""),"",new int[]{0,0},""},
			{"test",new int[]{4,4},""},
			{"heello",new int[]{2,4},"lo"},
			{"",new int[]{4,2},"isn't"},
		});
	}
	
	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReplay() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRecord() {
		fail("Not yet implemented");
	}
}
