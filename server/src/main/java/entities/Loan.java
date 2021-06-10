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

    private static final long serialVersionUID = -9138552347609697498L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "checkout")
    private Date checkout;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "due")
    private Date dueTo;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "returned", nullable = true)
    private Date returnedAt;

    public Loan() {
    }

    public Loan(Date dueTo, Date returnedAt) {
        this.checkout = new Date();
        this.dueTo = dueTo;
        this.returnedAt = returnedAt;
    }

    public Loan(Date checkout, Date dueTo, Date returnedAt) {
        this.checkout = checkout;
        this.dueTo = dueTo;
        this.returnedAt = returnedAt;
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

    public Date getDueTo() {
        return dueTo;
    }

    public void setDueTo(Date due) {
        this.dueTo = due;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returned) {
        this.returnedAt = returned;
    }
}
