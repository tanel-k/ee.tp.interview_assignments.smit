package ee.tp.interview_assignments.smit.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringTableBuilder {
	private ArrayList<String> headers;
	private List<ArrayList<String>> rows = new LinkedList<>();
	private ArrayList<Integer> cellLengths = new ArrayList<>();

	private ArrayList<String> makeRow(String... cells) {
		ArrayList<String> row = new ArrayList<>(cells.length);
		for (int i = 0; i < cells.length; i++) {
			String cell = StringUtils.defaultString(cells[i]);
			if (i == cellLengths.size()) {
				cellLengths.add(cell.length());
			} else {
				cellLengths.set(i, Math.max(cellLengths.get(i), cell.length()));
			}
			row.add(cell);
		}
		return row;
	}

	public StringTableBuilder headers(String... headers) {
		if (this.headers == null)
			this.headers = makeRow(headers);
		return this;
	}

	public StringTableBuilder row(String... cells) {
		rows.add(makeRow(cells));
		return this;
	}

	public boolean isEmpty() {
		return rows.isEmpty();
	}

	public String build() {
		StringBuilder buf = new StringBuilder();

		if (this.headers != null) {
			StringBuilder headerBuf = new StringBuilder();
			for (int i = 0; i < this.headers.size(); i++) {
				headerBuf.append(String.format(" %-" + this.cellLengths.get(i) + "s ", this.headers.get(i)));
			}

			buf.append(headerBuf)
				.append('\n')
				.append(StringUtils.repeat("*", headerBuf.length()))
				.append('\n');
		}

		for (ArrayList<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				buf.append(String.format(" %-" + this.cellLengths.get(i) + "s ", row.get(i)));
			}

			buf.append('\n');
		}

		return buf.toString();
	}
}
