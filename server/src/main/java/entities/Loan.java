package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "loan")
@NamedQueries({
    @NamedQuery(name = "Loan.deleteAllRows", query = "DELETE from Loan")
})
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "checkout")
    private Date checkout;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "due")
    private Date due;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "returned", nullable = true)
    private Date returned;

    public Loan() {
    }

    public Loan(Date due, Date returned) {
        this.checkout = new Date();
        this.due = due;
        this.returned = returned;
    }

    public Loan(Date checkout, Date due, Date returned) {
        this.checkout = checkout;
        this.due = due;
        this.returned = returned;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }
}
