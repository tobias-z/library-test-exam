package dtos;

import entities.Loan;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LoanDTO {

    private Integer id;
    private Date checkout;
    private Date dueTo;
    private Date returnedAt;
    private BookDTO book;

    public LoanDTO(Date checkout, Date dueTo, Date returnedAt) {
        this.checkout = checkout;
        this.dueTo = dueTo;
        this.returnedAt = returnedAt;
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.checkout = loan.getCheckout();
        this.dueTo = loan.getDueTo();
        this.returnedAt = loan.getReturnedAt();
        this.book = new BookDTO(loan.getBook());
    }

    public static List<LoanDTO> getLoanDTOSFromLoans(List<Loan> loans) {
        return loans.stream()
            .map(loan -> new LoanDTO(loan))
            .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Date getDueTo() {
        return dueTo;
    }

    public void setDueTo(Date dueTo) {
        this.dueTo = dueTo;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
