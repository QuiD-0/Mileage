package mileage.mileage_be.history.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;
    private String reviewId;
    private String userId;
    private HistoryDelta historyDelta;
    private int point;
    private String info;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
