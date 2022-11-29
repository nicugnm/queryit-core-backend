package ro.nicolaemariusghergu.queryit.model

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id", nullable = false)
        @NotFound(action = NotFoundAction.IGNORE)
        val id: Long? = null,

        @Column(name = "name")
        val name: String? = null,

        @Column(name = "price")
        val price: BigDecimal? = null,

        @Column(name = "quantity")
        val quantity: Int? = null,

        @Column(name = "icon_path")
        val iconPath: String? = null,

        @ManyToOne
        @JoinColumn(name = "category_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val category: Category? = null,

        @ManyToOne(cascade = [CascadeType.MERGE])
        @NotFound(action = NotFoundAction.IGNORE)
        @JoinColumn(name = "promotion_id")
        val promotion: Promotion? = null,

        @ManyToOne
        @JoinColumn(name = "minimarket_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val miniMarket: MiniMarket? = null,

        @ManyToOne
        @JoinColumn(name = "manufacturer_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val manufacturer: Manufacturer? = null)