package mileage.mileage_be.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mileage.mileage_be.user.domain.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Place {
    @Id
    private String placeId;
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
