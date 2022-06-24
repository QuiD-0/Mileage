package mileage.mileage_be.history.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mileage.mileage_be.review.domain.Action_type;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;
    private String reviewId;
    private String userId;
    private Action_type action_type;
    private HistoryDelta historyDelta;
    private int point;
    private String info;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public History(String reviewId, String userId, Action_type action_type, HistoryDelta historyDelta, int point, String info) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.action_type = action_type;
        this.historyDelta = historyDelta;
        this.point = point;
        this.info = info;
    }
}
