package br.com.lab.impacta.account.model

import lombok.Data
import javax.persistence.*

@Data
@Entity
class Person () {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "document")
    var document: String = ""


}