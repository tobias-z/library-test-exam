package dtos;

import entities.Loan;
import java.util.Date;

public class LoanDTO {

    private Integer id;
    private Date checkout;
    private Date dueTo;
    private Date returnedAt;

    public LoanDTO(Date checkout, Date dueTo, Date returnedAt) {
        this.checkout = checkout;
        this.dueTo = dueTo;
        this.returnedAt = returnedAt;
    }

    private LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.checkout = loan.getCheckout();
        this.dueTo = loan.getDueTo();
        this.returnedAt = loan.getReturnedAt();
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
}