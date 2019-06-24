package ee.tp.interview_assignments.smit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility class for building simple left-aligned string tables.
 */
public class StringTableBuilder {
	private static ArrayList<String> varArgsToList(String... cells) {
		return new ArrayList<>(Arrays.asList(cells));
	}

	private static void updateMaxCellWidths(ArrayList<String> cells, ArrayList<Integer> maxCellWidths) {
		for (int i = 0; i < cells.size(); i++) {
			if (i < maxCellWidths.size()) {
				maxCellWidths.set(i, Math.max(maxCellWidths.get(i), cells.get(i).length()));
			} else {
				maxCellWidths.add(cells.get(i).length());
			}
		}
	}

	private ArrayList<String> headers;
	private List<ArrayList<String>> rows = new LinkedList<>();
	private ArrayList<Integer> maxCellWidths = new ArrayList<>();

	public StringTableBuilder() { }

	/**
	 * Sets the header row for the table.
	 * @param headers string in the header row
	 * @return this instance
	 */
	public StringTableBuilder headers(String... headers) {
		this.headers = varArgsToList(headers);
		// should not adjust max cell widths since the header can change
		return this;
	}

	/**
	 * Adds a row to the table.
	 * @param cells strings in the new row
	 * @return this instance
	 */
	public StringTableBuilder row(String... cells) {
		ArrayList<String> row;
		rows.add(row = varArgsToList(cells));
		// No row removals - okay to assume max cell widths do not change
		updateMaxCellWidths(row, this.maxCellWidths);
		return this;
	}

	public boolean isEmpty() {
		return rows.isEmpty();
	}

	/**
	 * @return the string table encoded in this instance
	 */
	public String build() {
		StringBuilder tableBuf = new StringBuilder();
		ArrayList<Integer> maxCellWidths = this.maxCellWidths;

		if (this.headers != null) {
			maxCellWidths = new ArrayList<>(maxCellWidths);
			// adjust max cell widths given the presence of a header:
			updateMaxCellWidths(this.headers, maxCellWidths);

			StringBuilder headerBuf = new StringBuilder();
			for (int i = 0; i < this.headers.size(); i++) {
				headerBuf.append(' ')
					.append(StringUtils.padRight(this.headers.get(i), maxCellWidths.get(i)))
					.append(' ');
			}

			tableBuf.append(headerBuf)
				.append('\n')
				// header separator:
				.append(StringUtils.repeat("*", headerBuf.length()))
				.append('\n');
		}

		for (ArrayList<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				tableBuf.append(' ')
					.append(StringUtils.padRight(row.get(i), maxCellWidths.get(i)))
					.append(' ');
			}
			tableBuf.append('\n');
		}

		return tableBuf.toString();
	}
}
