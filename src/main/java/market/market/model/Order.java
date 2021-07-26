package market.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.market.dto.OrderItemDto;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long user;

    @OneToMany(mappedBy = "order")
//    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<OrderItem> orderItems;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order(Long user) {
        this.user = user;
    }
}
