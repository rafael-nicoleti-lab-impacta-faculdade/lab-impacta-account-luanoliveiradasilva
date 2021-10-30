package br.com.lab.impacta.account.model

import lombok.Data
import javax.persistence.*

@Data
@Entity
class Account (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "number")
    var number: Long,
    @Column(name = "balance")
    var balance: Double,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Person
        ) {

    constructor() : this(0,0,0.0, Person(0, "", "") ) {}


    fun debit (valueOfDebit : Double) : Boolean {
        if ( this.balance < valueOfDebit) {
            return false
        }
        return true
    }

}