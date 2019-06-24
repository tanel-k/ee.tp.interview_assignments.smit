package ee.tp.interview_assignments.smit.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringTableBuilder {
	private ArrayList<String> headers;
	private List<ArrayList<String>> rows = new LinkedList<>();
	private ArrayList<Integer> cellLengths = new ArrayList<>();

	private ArrayList<String> constructRow(String... cells) {
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
			this.headers = constructRow(headers);
		return this;
	}

	public StringTableBuilder row(String... cells) {
		rows.add(constructRow(cells));
		return this;
	}

	public boolean isEmpty() {
		return rows.isEmpty();
	}

	public String build() {
		StringBuilder tableBuf = new StringBuilder();

		if (this.headers != null) {
			StringBuilder headerBuf = new StringBuilder();
			for (int i = 0; i < this.headers.size(); i++) {
				headerBuf.append(' ')
					.append(StringUtils.padRight(this.headers.get(i), this.cellLengths.get(i)))
					.append(' ');
			}

			tableBuf.append(headerBuf)
				.append('\n')
				.append(StringUtils.repeat("*", headerBuf.length()))
				.append('\n');
		}

		for (ArrayList<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				tableBuf.append(' ')
					.append(StringUtils.padRight(row.get(i), this.cellLengths.get(i)))
					.append(' ');
			}

			tableBuf.append('\n');
		}

		return tableBuf.toString();
	}
}
