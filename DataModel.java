import java.util.HashSet;
import java.util.Set;

public class DataModel {
	static final int NUM_CIRCLES = 4;
	static final int NUM_SECTORS = 8;
	final int[] score;

	private final int[][] data;

	public char[] getCellText(int x, int y) {
		if (data[x][y] == 0) {
			return "".toCharArray();
		}
		return new Integer(data[x][y]).toString().toCharArray();
	}

	public int getCellValue(int x, int y) {
		return data[x][y];
	}

	public int getLargestCellText() {
		int greatestValue = 0;
		for (int i = 0; i < NUM_CIRCLES; i++) {
			for (int j = 0; j < NUM_SECTORS; j++) {
				int val = getCellValue(i, j);
				if (val > greatestValue) {
					greatestValue = val;
				}
			}
		}
		return greatestValue;
	}

	int[][] getData() {
		return data;
	}

	public DataModel() {
		data = new int[NUM_CIRCLES][NUM_SECTORS];
		score = new int[] { 0 };
	}

	public DataModel(int[][] model) {
		data = model;
		score = new int[] { 0 };
	}

	/**
	 * Fill in a "2" or a "4" in a random available position.
	 */
	public void randomStep() {
		// TODO(ksoltan) test the heck out of this!!!
		Set<Integer> visitedXs = new HashSet<>();
		while (visitedXs.size() < NUM_CIRCLES) {
			int x = (int) (Math.random() * NUM_CIRCLES);
			if (visitedXs.contains(x)) {
				continue;
			}
			Set<Integer> visitedYs = new HashSet<>();
			while (visitedYs.size() < NUM_SECTORS) {
				int y = (int) (Math.random() * NUM_SECTORS);
				if (visitedYs.contains(y)) {
					continue;
				} else if (data[x][y] != 0) {
					visitedYs.add(y);
					continue;
				}
				 data[x][y] = 2;
//				int[] powers = { 1, 2 };
//				data[x][y] = (int) Math.pow(2,
//						powers[(int) (Math.random() * powers.length)]);
				return;
			}
			visitedXs.add(x);
		}
	}

	public void rotate(int[] a, int n) {
		if (n % a.length == 0) {
			return;
		}
		int startIdx = 0;
		int shifts = 0;
		int currIdx = startIdx;
		int currTemp = a[currIdx];
		while (shifts < a.length) {
			int nextIdx = getNextIndex(a, currIdx, n);
			if (nextIdx == startIdx) {
				a[nextIdx] = currTemp;
				startIdx++;
				currIdx = startIdx;
				currTemp = a[currIdx];
			} else {
				int temp = a[nextIdx];
				a[nextIdx] = currTemp;
				currTemp = temp;
				currIdx = nextIdx;
			}
			shifts++;
		}
	}

	private int getNextIndex(int[] a, int idx, int shift) {
		// If shift is negative:int shift;

		if (shift < 0) {
			shift = a.length + shift % a.length;
		}
		return (idx + shift) % a.length;
	}

	/**
	 * 
	 * @param row
	 *            Shifts the circle by 1 clockwise
	 */
	public void rotateCW(int row, int[][] a) {
		int temp = a[row][0];
		int currIdx = 0;
		while (currIdx < NUM_SECTORS - 1) {
			int newTemp = a[row][currIdx + 1];
			a[row][currIdx + 1] = temp;
			temp = newTemp;
			currIdx++;
		}
		a[row][0] = temp;
	}

	/**
	 * 
	 * @param row
	 *            Shifts the circle by 1 counter clockwise
	 */
	public void rotateCCW(int row, int[][] a) {
		int currIdx = a.length - 1;
		int temp = a[row][currIdx];
		while (currIdx > 0) {
			int newTemp = a[row][currIdx - 1];
			a[row][currIdx] = temp;
			temp = newTemp;
			currIdx--;
		}
		a[row][a.length] = temp;
	}

	public void rotateData(int row, int dir) {
		rotate(data[row], dir);
	}

	public void combineIn() {
		for (int i = 0; i < NUM_SECTORS; i++) {
			combineColumnIn(i);
		}
	}

	public void combineColumnIn(int col) {
		int outerMostUntouchedRow = -1;
		for (int i = 0; i < NUM_CIRCLES; i++) {
			int val = data[i][col];
			int outermostValidRow = -1;
			// Find row into which to merge in
			// Keep searching until the first invalid row.
			for (int j = i - 1; j >= 0 && j > outerMostUntouchedRow; j--) {
				if (!isValidLocation(j, col, data[i][col])) {
					break;
				}
				outermostValidRow = j;
			}
			// If there is a position, add them.
			if (outermostValidRow != -1) {
				if (data[outermostValidRow][col] != 0) {
					outerMostUntouchedRow = outermostValidRow;
					score[0] += data[outermostValidRow][col];
				}
				data[outermostValidRow][col] += val;
				data[i][col] = 0;

			}
		}
	}

	protected boolean hasEmptyCell() {
		for (int i = 0; i < NUM_CIRCLES; i++) {
			for (int j = 0; j < NUM_SECTORS; j++) {
				if (data[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean rowContains(int val, int row) {
		for (int i = 0; i < NUM_SECTORS; i++) {
			if (data[row][i] == val) {
				return true;
			}
		}
		return false;
	}

	public boolean isGameOver() {
		for (int row = 0; row < NUM_CIRCLES - 1; row++) {
			for (int col = 0; col < NUM_SECTORS; col++) {
				for (int col2 = 0; col2 < NUM_SECTORS; col2++) {
					if (getCellValue(row, col) == 0
							|| getCellValue(row, col) == getCellValue(row + 1,
									col2)) {
						return false;
					}
				}
			}
		}
		return true;

	}

	protected boolean gameIsOver() {
		if (hasEmptyCell()) {
			return false;
		}
		for (int i = 0; i < NUM_CIRCLES - 1; i++) {
			for (int j = 0; j < NUM_SECTORS; j++) {
				if (rowContains(data[i][j], i + 1)) {
					return false;
				}
			}
		}
		return true;
	}

	public int getScore() {
		return score[0];
	}

	public void combineOut() {
		for (int i = 0; i < NUM_SECTORS; i++) {
			combineColumnOut(i);
		}
	}

	public void combineColumnOut(int col) {
		int outerMostUntouchedRow = NUM_CIRCLES;
		for (int i = NUM_CIRCLES - 1; i >= 0; i--) {
			int val = data[i][col];
			int outermostValidRow = -1;
			// Find row into which to merge in
			// Keep searching until the first invalid row.
			for (int j = i + 1; j < NUM_CIRCLES && j < outerMostUntouchedRow; j++) {
				if (!isValidLocation(j, col, data[i][col])) {
					break;
				}
				outermostValidRow = j;
			}
			// If there is a position, add them.
			if (outermostValidRow != -1) {
				if (data[outermostValidRow][col] != 0) {
					outerMostUntouchedRow = outermostValidRow;
				}
				data[outermostValidRow][col] += val;
				data[i][col] = 0;
			}
		}
	}

	public boolean isValidLocation(int row, int col, int value) {
		if (row >= 0 && row <= NUM_CIRCLES && row >= 0 && row <= NUM_SECTORS) {
			int v = data[row][col];
			if (v == 0 || v == value) {
				return true;
			}
		}
		return false;

	}
}
