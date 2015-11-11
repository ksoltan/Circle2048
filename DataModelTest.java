import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataModelTest {
	DataModel dm = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } });

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDoesNotHaveEmptyCells() {
		DataModel d = new DataModel(new int[][] { { 2, 4, 2, 2, 2, 2, 2, 2 },
				{ 4, 2, 1, 3, 2, 2, 4, 4 }, { 2, 2, 2, 4, 4, 4, 2, 2 },
				{ 2, 2, 4, 4, 4, 4, 4, 2 } });
		assertEquals(false, d.hasEmptyCell());
	}

	@Test
	public void testHasOneEmptyCells() {
		DataModel d = new DataModel(new int[][] { { 2, 4, 2, 2, 2, 2, 2, 2 },
				{ 4, 2, 1, 3, 2, 2, 4, 4 }, { 2, 2, 2, 4, 4, 4, 2, 2 },
				{ 2, 2, 4, 4, 4, 4, 0, 2 } });
		assertEquals(true, d.hasEmptyCell());
	}

	@Test
	public void testHasEmptyCells() {
		assertEquals(true, dm.hasEmptyCell());
	}

	@Test
	public void testRowDoesContainVal() {
		assertEquals(true, dm.rowContains(2, 0));
	}

	@Test
	public void testRowDoesNotContainVal() {
		assertEquals(false, dm.rowContains(2, 1));
	}

	@Test
	public void testGameNotOverWithMostEmptyCells() {
		assertEquals(false, dm.gameIsOver());
	}

	@Test
	public void testGameNotOverWithEmptyCell() {
		DataModel d = new DataModel(new int[][] { { 2, 4, 2, 2, 2, 2, 2, 2 },
				{ 4, 2, 1, 3, 2, 2, 4, 4 }, { 2, 2, 2, 4, 4, 4, 2, 2 },
				{ 2, 2, 4, 4, 4, 4, 0, 2 } });

		assertEquals(false, d.gameIsOver());
	}

	@Test
	public void testGameNotOverWithSameValInRow() {
		DataModel d = new DataModel(new int[][] { { 2, 4, 2, 2, 2, 2, 2, 2 },
				{ 4, 2, 1, 3, 2, 2, 4, 4 }, { 2, 2, 2, 4, 4, 4, 2, 2 },
				{ 2, 2, 4, 4, 4, 4, 2, 2 } });

		assertEquals(false, d.gameIsOver());
	}

	@Test
	public void testGameNotOverValInLastRow() {
		DataModel d = new DataModel(new int[][] { { 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 8, 8, 8, 8, 8, 8, 8, 8 }, { 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 4, 4, 4, 4, 4, 4, 4, 2 } });
		assertEquals(false, d.gameIsOver());
	}

	@Test
	public void testGameOver() {
		DataModel d = new DataModel(new int[][] { { 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 8, 8, 8, 8, 8, 8, 8, 8 }, { 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 4, 4, 4, 4, 4, 4, 4, 4 } });
		assertEquals(true, d.gameIsOver());
	}

	@Test
	public void testGameNotOverWithMostEmptyCellsK() {
		assertEquals(false, dm.isGameOver());
	}

	@Test
	public void testGameNotOverWithEmptyCellK() {
		DataModel d = new DataModel(new int[][] { { 2, 4, 2, 2, 2, 2, 2, 2 },
				{ 4, 2, 1, 3, 2, 2, 4, 4 }, { 2, 2, 2, 4, 4, 4, 2, 2 },
				{ 2, 2, 4, 4, 4, 4, 0, 2 } });

		assertEquals(false, d.isGameOver());
	}

	@Test
	public void testGameNotOverWithSameValInRowK() {
		DataModel d = new DataModel(new int[][] { { 2, 4, 2, 2, 2, 2, 2, 2 },
				{ 4, 2, 1, 3, 2, 2, 4, 4 }, { 2, 2, 2, 4, 4, 4, 2, 2 },
				{ 2, 2, 4, 4, 4, 4, 2, 2 } });

		assertEquals(false, d.isGameOver());
	}

	@Test
	public void testGameNotOverValInLastRowK() {
		DataModel d = new DataModel(new int[][] { { 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 8, 8, 8, 8, 8, 8, 8, 8 }, { 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 4, 4, 4, 4, 4, 4, 4, 2 } });
		assertEquals(false, d.isGameOver());
	}

	@Test
	public void testGameOverK() {
		DataModel d = new DataModel(new int[][] { { 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 8, 8, 8, 8, 8, 8, 8, 8 }, { 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 4, 4, 4, 4, 4, 4, 4, 4 } });
		assertEquals(true, d.isGameOver());
	}

	@Test
	public void testGetGreatestValueNone() {
		DataModel model = new DataModel(new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } });
		assertEquals(0, model.getLargestCellText());
	}

	@Test
	public void testGetGreatestValueDm() {
		assertEquals(2, dm.getLargestCellText());
	}

	// @Test
	// public void testScoreZero() {
	// DataModel model = new DataModel(new int[][] {
	// { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
	// { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } });
	// model.combineIn();
	// assertEquals(0, model.getScore());
	// }
	//
	// @Test
	// public void testScoreOneCombine() {
	// DataModel model = new DataModel(new int[][] {
	// { 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0 },
	// { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } });
	// model.combineIn();
	// assertEquals(0, model.getScore());
	// }
	//
	// @Test
	// public void testScoreEverything() {
	// DataModel d = new DataModel(new int[][] { { 2, 0, 4, 0, 0, 0, 0, 0 },
	// { 2, 8, 0, 0, 0, 0, 0, 0 }, { 2, 0, 4, 0, 0, 0, 0, 0 },
	// { 2, 0, 4, 0, 0, 0, 0, 0 } });
	// d.combineIn();
	// assertEquals(28, d.getScore());
	// }

	@Test
	public void testGetGreatestValueScattered() {
		DataModel model = new DataModel(new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 2048, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 4096 } });
		assertEquals(4096, model.getLargestCellText());
	}

	@Test
	public void testRotate1() {
		int[][] a = { { 1, 2, 3, 4, 5, 6, 7, 8 }, {}, {}, {} };
		dm.rotateCW(0, a);
		assertArrayEquals(
				new int[][] { { 8, 1, 2, 3, 4, 5, 6, 7 }, {}, {}, {} }, a);
	}

	@Test
	public void testRotate2() {
		int[][] a = { { 1, 2, 3, 4, 5, 6, 7, 8 }, {}, {}, {} };
		dm.rotateCW(0, a);
		dm.rotateCW(0, a);
		assertArrayEquals(
				new int[][] { { 7, 8, 1, 2, 3, 4, 5, 6 }, {}, {}, {} }, a);
	}

	@Test
	public void testRotate3() {
		int[][] a = { { 1, 2, 3, 4, 5, 6, 7, 8 }, {}, {}, {} };
		dm.rotateCW(0, a);
		dm.rotateCW(0, a);
		dm.rotateCW(0, a);
		assertArrayEquals(
				new int[][] { { 6, 7, 8, 1, 2, 3, 4, 5 }, {}, {}, {} }, a);
	}

	@Test
	public void testRotateRow1() {
		int[][] a = { { 1, 2, 3, 4, 5, 6, 7, 8 }, { 1, 2, 3, 4, 5, 6, 7, 8 },
				{}, {} };
		dm.rotateCW(1, a);
		dm.rotateCW(1, a);
		dm.rotateCW(1, a);
		assertArrayEquals(new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8 },
				{ 6, 7, 8, 1, 2, 3, 4, 5 }, {}, {} }, a);
	}

	@Test
	public void testRotateRow2() {
		int[][] a = { { 1, 2, 3, 4, 5, 6, 7, 8 }, { 1, 2, 3, 4, 5, 6, 7, 8 },
				{ 6, 7, 8, 1, 2, 3, 4, 5 }, {} };
		dm.rotateCW(2, a);
		dm.rotateCW(2, a);
		dm.rotateCW(2, a);
		assertArrayEquals(new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8 },
				{ 1, 2, 3, 4, 5, 6, 7, 8 }, { 3, 4, 5, 6, 7, 8, 1, 2 }, {} }, a);
	}

	@Test
	public void testCombineInNoneMove() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnIn(0);
		assertArrayEquals(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());

	}

	@Test
	public void testCombineInOuter() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnIn(0);
		assertArrayEquals(new int[][] { { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());
	}

	@Test
	public void testCombineInOuterWithMatchingAfter() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnIn(0);
		assertArrayEquals(new int[][] { { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());
	}

	@Test
	public void testCombineOutOuterWithMatchingAfter() {
		DataModel d = new DataModel(new int[][] { { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineOut();
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());
	}

	@Test
	public void testCombine3InInARow() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnIn(0);
		assertArrayEquals(new int[][] { { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());
	}

	@Test
	public void testCombineInEverything() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 4, 0, 0, 0, 0, 0 },
				{ 2, 8, 0, 0, 0, 0, 0, 0 }, { 2, 0, 4, 0, 0, 0, 0, 0 },
				{ 2, 0, 4, 0, 0, 0, 0, 0 } });
		d.combineIn();
		assertArrayEquals(new int[][] { { 4, 8, 8, 0, 0, 0, 0, 0 },
				{ 4, 0, 4, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());

	}

	@Test
	public void testCombineOutNoneMove() {
		DataModel d = new DataModel(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnOut(0);
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());

	}

	@Test
	public void testCombineOutOuter() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnOut(0);
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());

	}

	@Test
	public void testCombine3OutInARow() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		d.combineColumnOut(0);
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 0, 0 } }, d.getData());

	}

	@Test
	public void testCombineOutEverything() {
		DataModel d = new DataModel(new int[][] { { 2, 0, 4, 0, 0, 0, 0, 0 },
				{ 2, 8, 0, 0, 0, 0, 0, 0 }, { 2, 0, 4, 0, 0, 0, 0, 0 },
				{ 2, 0, 4, 0, 0, 0, 0, 0 } });
		d.combineOut();
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, 0, 4, 0, 0, 0, 0, 0 },
				{ 4, 8, 8, 0, 0, 0, 0, 0 } }, d.getData());

	}

	@Test
	public void testIsValidLocationNo() {
		DataModel d = new DataModel(new int[][] { { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });

		assertEquals(false, d.isValidLocation(0, 0, 2));

	}

	@Test
	public void testIsValidLocationSpaceBetweenNo() {
		DataModel d = new DataModel(new int[][] { { 4, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } });
		assertEquals(false, d.isValidLocation(0, 0, 2));
	}
}
