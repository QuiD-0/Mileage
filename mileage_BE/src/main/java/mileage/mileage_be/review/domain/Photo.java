package mileage.mileage_be.review.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(indexes = @Index(columnList = "attachedPhotoIDs"))
public class Photo implements Serializable {

    @EmbeddedId
    private PhotoEmbededId photoEmbededId;

}
