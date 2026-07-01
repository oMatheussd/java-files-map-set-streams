package entities;

public class Loan {

	private String readersName;
	private String bookTitle;
	private String genre;
	private Integer loanDays;

	public Loan(String readersName, String bookTitle, String genre, Integer loanDays) {
		this.readersName = readersName;
		this.bookTitle = bookTitle;
		this.genre = genre;
		this.loanDays = loanDays;
	}

	public String getReadersName() {
		return readersName;
	}

	public void setReadersName(String readersName) {
		this.readersName = readersName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	@Override
	public String toString() {
		return "Loan [readersName=" + readersName
				+ ", bookTitle=" + bookTitle
				+ ", genre=" + genre
				+ ", loanDays=" + loanDays + "]";
	}
}