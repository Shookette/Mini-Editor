package JUnit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import model.Core;
import model.Recorder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import view.HMI;
import controller.Recordable;
import controller.recordable.Cut;
import controller.recordable.Paste;

@RunWith(Parameterized.class)
public class RecorderTest {
	
	/**
	 * Core variable for testing
	 */
	private final Recorder recorder;
	private final Core core;
	private final HMI hmi;
	
	/**
	 * CoreTest constructor with parameter setting
	 * @param b String content of buffer
	 * @param p Array int with start and end position of selection
	 * @param c String content of clipboard
	 */
	public RecorderTest(final String b, final int[] p, final String c) {
		this.core = new Core(b,p,c);
		this.recorder = new Recorder();
		this.hmi = new HMI();
	}
	
	/**
	 * Test cases
	 * @return Collection of core test cases (with buffer, positions, clipboard)
	 */
	@Parameters
	public static Collection<Object[]> parameters()
	{
		return Arrays.asList(new Object[][]{
			{"", new int[]{0,0}, ""},
			{"test", new int[]{4,4}, ""},
			{"heello", new int[]{2,4}, "lo"},
			{"", new int[]{4,2}, "isn't"}
		});
	}
	
	@Test
	public void testStart() {
		//Beginning start test
		assertEquals("Recording was already started before start command",false,this.recorder.getRecording());
		this.recorder.start();
		assertEquals("An insert wasn't done after the start like excepted",0,this.recorder.getListMemento().size());
		assertEquals("Recording wasn't started after start command",true,this.recorder.getRecording());
	}

	@Test
	public void testStop() {
		//init memento
		Recordable cut = new Cut(this.core, this.hmi, this.recorder);
		Recordable paste = new Paste(this.core, this.hmi, this.recorder);

		this.recorder.start();
		this.recorder.record(cut);
		assertEquals("An cut wasn't recorded after the start like excepted",1,this.recorder.getListMemento().size());
		assertEquals("Recording wasn't started after start command",true,this.recorder.getRecording());
	
		this.recorder.stop();
		this.recorder.record(paste);
		assertEquals("Recording wasn't stoped after stop command",false,this.recorder.getRecording());
		assertEquals("An past was recorded after the stop command",1,this.recorder.getListMemento().size());
	}

	@Test
	public void testRecord() {
		//init memento
		Recordable cut = new Cut(this.core, this.hmi, this.recorder);
		Recordable paste = new Paste(this.core, this.hmi, this.recorder);
		
		//Beginning stop test
		this.recorder.record(cut);
		assertEquals("An cut was recorded before the start",0,this.recorder.getListMemento().size());
		assertEquals("Recording was already started before start command",false,this.recorder.getRecording());
		
		this.recorder.start();
		this.recorder.record(cut);
		assertEquals("An cut wasn't recorded after the start like excepted",1,this.recorder.getListMemento().size());
		assertEquals("Recording wasn't started after start command",true,this.recorder.getRecording());
		
		this.recorder.stop();
		this.recorder.record(paste);
		assertEquals("An paste was recorded after the stop command",1,this.recorder.getListMemento().size());
		assertEquals("Recording wasn't stoped after stop command",false,this.recorder.getRecording());
	}
	
	@Test
	public void testReplay() {
		Recordable paste = new Paste(this.core, this.hmi, this.recorder);
		paste.setMemento();
		String oldBuffer = this.core.getBuffer().getText();
		String oldClipBoard = this.core.getClipBoard().getContent();
		int[] oldSelection = this.core.getSelection().getSelection();
		boolean execute = true;
		
		
		this.recorder.start();
		this.recorder.record(paste);
		this.recorder.stop();
		
		if (oldSelection[0] > oldBuffer.length()) {
			assertTrue("Start position is out of bounds", oldSelection[0] > oldBuffer.length());
			execute = false;
		} else if (oldSelection[0] < 0 ){
			assertTrue("Start position is negative", oldSelection[0] < 0 );
			execute = false;
		}
		// Test end position
		if (oldSelection[1] < oldSelection[0]) {
			assertTrue("End position is smaller than start position", oldSelection[1] < oldSelection[0]); 
			execute = false;
		} else if (oldSelection[1] > oldBuffer.length()) {
			assertTrue("End position is out of bounds", oldSelection[1] > oldBuffer.length());
			execute = false;
		} 
		
		if (execute) {
			this.recorder.replay();
			assertEquals("The buffer isn't the expected result",oldBuffer.substring(0, oldSelection[0])+oldClipBoard+oldBuffer.substring(oldSelection[1]),this.core.getBuffer().getText());
		}
	}
}
